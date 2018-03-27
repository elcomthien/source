package elcom.com.apiconnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class FTPConnect {
	static FTPClient ftp;
	static boolean connect = false;
	static String IPHost;
	private static Logger logger = LogManager.getLogger(FTPConnect.class);

	public FTPConnect() {

	}

	/* download a specify file */
	@SuppressWarnings("static-access")
	public boolean download(String host, String port, String username, String password, String localFileName, String remoteFileName) {
		boolean download = false;
		try {
			ftp.setFileType(ftp.BINARY_FILE_TYPE);
			File file = new File(localFileName);
			FileOutputStream localfile = new FileOutputStream(file);
			ftp.retrieveFile(remoteFileName, localfile); // download file
			localfile.close();
			ftp.logout();
			download = true;
		} catch (FTPConnectionClosedException e) {
			System.err.println("Server closed connection.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					System.out.println("Not closed connet");
					f.printStackTrace();
				}
			}
		}

		return download;
	}

	/* up a file to FTP */
	@SuppressWarnings("static-access")
	public static boolean upload(FTPClient ftp, String localFilename, String remoteFile) {
		boolean upload = false;
		try {
			ftp.changeWorkingDirectory(remoteFile);
			ftp.setFileType(ftp.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			File localFile = new File(localFilename);
			FileInputStream in = new FileInputStream(localFile);
			// up load file
			boolean up = ftp.storeFile(remoteFile, in);
			if (up == true) {
				in.close();
				localFile.delete(); // xoa file o local
				logger.info("Delete local file .This   " + localFile.getName() + " file up success " + "\n");
			}
			ftp.logout();

		} catch (FTPConnectionClosedException e) {

			logger.info("Cause : " + e.fillInStackTrace() + "\n");
		} catch (IOException e) {
			logger.info("Error: could not open local file for reading." + "\n");
			logger.info("Cause : " + e.fillInStackTrace() + "\n");
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					logger.info("Not can close connect. Cause : " + f.fillInStackTrace() + "\n");
				}
			}
		}
		return upload;

	}

	@SuppressWarnings("unused")
	public static FTPClient connect(String server, String port, String username, String password) {
		System.out.println(server + " - " + username + " - " + password);
		ftp = new FTPClient();
		boolean isconnect = false;
		int reply;
		ftp = new FTPClient();
		try {
			ftp.connect(server);
		} catch (SocketException ex) {
			logger.info("Not connect to FTP server !");
			logger.info("Cause not connect to server : " + ex.fillInStackTrace());
		} catch (IOException ex) {
			logger.info("Cause not connect to server : " + ex.fillInStackTrace());
		}
		reply = ftp.getReplyCode();
		logger.info("FTP server reply connect :" + reply);
		if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
			try {
				ftp.disconnect();
			} catch (IOException ex1) {
				logger.info("FTP server disconnect ");
				logger.info("Cause : " + ex1.fillInStackTrace());
			}
		}
		/*
		 * can reciever 3 pararam : usser,pass, port neu ko port vao thi lay port mac dinh la 21
		 */
		try {
			isconnect = ftp.login(username, password, port);
		} catch (IOException ex2) {
			logger.info("Not login FTP ");
			logger.info("Cause :  " + ex2.fillInStackTrace());
		}

		return ftp;
	}

	@SuppressWarnings("static-access")
	public static File checkExistFile(FTPClient ftp, String remoteFile, String localFile) {
		boolean isexist = false;
		File aFile = null;
		try {

			ftp.setFileType(ftp.BINARY_FILE_TYPE);
			aFile = new File(localFile);
			FileOutputStream out = new FileOutputStream(aFile);
			isexist = ftp.retrieveFile(remoteFile, out); // download file
			System.out.println("remoteFile=[" + remoteFile + "] is exist=" + isexist);
			if (!isexist) {
				aFile.createNewFile();
				isexist = true;
			}
			out.flush();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return aFile;
	}

	// xu ly move file from one dir to other
	public static void copy(String fromFileName, String toFileName) throws IOException {
		System.out.println("Begin Move File!");
		File fromFile = new File(fromFileName);
		File toFile = new File(toFileName);

		if (!fromFile.exists()) {
			throw new IOException("FileCopy: " + "no such source file: " + fromFileName);
		}
		if (!fromFile.isFile()) {
			throw new IOException("FileCopy: " + "can't copy directory: " + fromFileName);
		}
		if (!fromFile.canRead()) {
			throw new IOException("FileCopy: " + "source file is unreadable: " + fromFileName);
		}

		if (toFile.isDirectory()) {
			toFile = new File(toFile, fromFile.getName());
		}

		if (toFile.exists()) {
			String parent = toFile.getParent();
			if (parent == null) {
				parent = System.getProperty("user.dir");
			}
			File dir = new File(parent);
			if (!dir.exists()) {
				throw new IOException("FileCopy: " + "destination directory doesn't exist: " + parent);
			}
			if (dir.isFile()) {
				throw new IOException("FileCopy: " + "destination is not a directory: " + parent);
			}
			if (!dir.canWrite()) {
				throw new IOException("FileCopy: " + "destination directory is unwriteable: " + parent);
			}
		}

		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(fromFile);
			to = new FileOutputStream(toFile);

			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1) {
				to.write(buffer, 0, bytesRead); // write
			}
			System.out.println("Move File completed");
		} finally {
			if (from != null) {
				try {
					from.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			if (to != null) {
				try {
					to.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
