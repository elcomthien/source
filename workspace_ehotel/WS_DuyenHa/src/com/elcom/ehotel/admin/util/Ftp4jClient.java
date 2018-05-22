package com.elcom.ehotel.admin.util;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.elcom.ehotel.admin.model.FileModel;

public class Ftp4jClient {
	private String Host = "";
	private int Port = 21;
	private String User = "";
	private String Pass = "";
	FTPClient client = new FTPClient();
	private static List<String> listfolder;
	private static List<FileModel> listfile;
	private final static Logger logger = Logger.getLogger(Ftp4jClient.class);

	@Override
	public String toString() {
		return "Ftp4jClient [Host=" + Host + ", Port=" + Port + ", User="
				+ User + ", Pass=" + Pass + "]";
	}

	public Ftp4jClient(String host, int port, String user, String pass) {
		super();
		Host = host;
		Port = port;
		User = user;
		Pass = pass;
		listfolder = new ArrayList<String>();
		listfile = new ArrayList<FileModel>();
		System.out.println(toString());
	}

	public void connect() {
		try {
			logger.info("Connect ftp server " + this.Host);
			client.connect(this.Host, this.Port);
			client.login(User, Pass);
			logger.info("Connect ftp server " + this.Host + " success");
		} catch (Exception e) {
			logger.error("Connect ftp error", e);
		}
	}

	public boolean deleteFile(String filepath) {
		logger.info("Delete file in ftp server " + filepath);
		boolean flag = true;
		try {
			client.deleteFile(filepath);
		} catch (Exception e) {
			logger.error("Delete file " + filepath + " error", e);
			flag = false;
		}
		return flag;
	}

	public boolean deleteFolder(String folderpath) {
		logger.info("Delete folder in ftp server " + folderpath);
		boolean flag = true;
		try {
			client.deleteDirectory(folderpath);
		} catch (Exception e) {
			logger.error("Delete folder " + folderpath + " error", e);
			flag = false;
		}
		return flag;
	}

	public boolean createDirectoryFtp(String foldername) {
		boolean flag = true;
		logger.info("create folder: " + foldername);
		try {
			client.createDirectory(foldername);
		} catch (Exception e) {
			logger.error("Create folder " + foldername + " error", e);
		}
		return flag;
	}

	public void close() {
		try {
			// logger.info("Disconnect ftp server " + this.Host);
			client.disconnect(false);
		} catch (Exception e) {
			logger.error("Disconnect ftp server " + this.Host + " error", e);
		}
	}

	public void getFolder(String path) {
		try {
			FTPFile[] arr = client.list(path);
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].getType() == 1
						&& !arr[i].getName().equalsIgnoreCase("ftp_client")
						&& !arr[i].getName().equalsIgnoreCase("ftp_gateway")
						&& !arr[i].getName()
								.equalsIgnoreCase("KillJavaProgram")) {
					String temp = path + "/" + arr[i].getName();
					listfolder.add(temp);
					// logger.info(temp);
					getFolder(path + "/" + arr[i].getName());
				}
			}
		} catch (Exception e) {
			logger.error("Get list folder " + path + " error", e);
		}
	}

	public void getFile(String path) {
		try {
			if (!path.equalsIgnoreCase("")) {
				String text = path.substring(0, 1);
				if (text.equalsIgnoreCase("/"))
					path = path.substring(1, path.length());
			}
			FTPFile[] arr = client.list(path);
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].getType() == 0) {
					if (path.indexOf("ftp_client") < 0
							&& path.indexOf("ftp_gateway") < 0
							&& path.indexOf("KillJavaProgram") < 0) {
						FileModel temp = new FileModel();
						temp.setFilename(path + "/" + arr[i].getName());
						temp.setSize(arr[i].getSize());
						listfile.add(temp);
						// logger.info(temp);
						// getFolder(path + "/" + arr[i].getName());
					}
				}
				if (arr[i].getType() == 1) {
					getFile(path + "/" + arr[i].getName());
				}
			}
		} catch (Exception e) {
			logger.error("Get list file " + path + " error", e);
		}
	}
	
	public void getFileFilter(String path, String type) {
		try {
			if (!path.equalsIgnoreCase("")) {
				String text = path.substring(0, 1);
				if (text.equalsIgnoreCase("/"))
					path = path.substring(1, path.length());
			}
			FTPFile[] arr = client.list(path);
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].getType() == 0) {
					if (FileUtil.checkFileType(arr[i].getName()).equals(type)) {
						FileModel temp = new FileModel();
						temp.setPath(path);
						temp.setFilename(arr[i].getName());
						temp.setSize(arr[i].getSize());
						listfile.add(temp);
						// logger.info(temp);
						// getFolder(path + "/" + arr[i].getName());
					}
				}
				if (arr[i].getType() == 1) {
					getFileFilter(path + "/" + arr[i].getName(), type);
				}
			}
		} catch (Exception e) {
			logger.error("Get list file " + path + " error", e);
		}
	}

	public List<String> getListFolder(String path) {
		listfolder = new ArrayList<String>();
		getFolder(path);
		return listfolder;
	}

	public List<FileModel> getListFile(String path) {
		listfile = new ArrayList<FileModel>();
		getFile(path);
		return listfile;
	}
	
	public List<FileModel> getListFileFilterType(String path,String type) {
		listfile = new ArrayList<FileModel>();
		getFileFilter("",type);
		return listfile;
	}

	public static void main(String[] args) {
		Ftp4jClient f = new Ftp4jClient("172.16.9.188", 1622, "ftpmp3", "ftpmp3");
		// Ftp4jClient f = new Ftp4jClient("172.16.9.65", 21, "vod", "vod");
		f.connect();
		try {
//			f.getFolder("");
			f.getListFileFilterType("","video");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(listfile);
		System.out.println(listfolder);
		f.close();
	}

}
