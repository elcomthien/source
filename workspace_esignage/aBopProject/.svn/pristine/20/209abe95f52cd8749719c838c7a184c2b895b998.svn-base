package elcom.abop.util;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import elcom.abop.bean.ObjectABOP;

public class Ftp4jClientUitil {
	private String Host = "";
	private int Port = 21;
	private String User = "";
	private String Pass = "";
	FTPClient client = new FTPClient();
	private final static Logger logger = Logger.getLogger(Ftp4jClientUitil.class);

	public Ftp4jClientUitil(String host, int port, String user, String pass) {
		super();
		Host = host;
		Port = port;
		User = user;
		Pass = pass;
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

	public void close() {
		try {
			// logger.info("Disconnect ftp server " + this.Host);
			client.disconnect(false);
		} catch (Exception e) {
			logger.error("Disconnect ftp server " + this.Host + " error", e);
		}
	}

	public ArrayList<ObjectABOP> getFile(String path) {
		ArrayList<ObjectABOP> listfile = new ArrayList<ObjectABOP>();
		try {
			if (!path.equalsIgnoreCase("")) {
				String text = path.substring(0, 1);
				if (text.equalsIgnoreCase("/"))
					path = path.substring(1, path.length());
			}
			FTPFile[] arr = client.list(path);
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].getType() == 0) {
					ObjectABOP temp = new ObjectABOP();
					temp.setType("0");
					temp.setName(path + "/" + arr[i].getName());
					temp.setSize("" + arr[i].getSize());
					listfile.add(temp);
					// getFolder(path + "/" + arr[i].getName());
				}
				if (arr[i].getType() == 1) {
					ObjectABOP temp = new ObjectABOP();
					temp.setType("1");
					temp.setName(path + "/" + arr[i].getName());
					temp.setSize("...");
					listfile.add(temp);
				}
			}
		} catch (Exception e) {
			logger.error("Get list file " + path + " error", e);
		}
		return listfile;
	}

	public static ArrayList<ObjectABOP> getListFile(String host, int port, String user, String pass, String path) {
		ArrayList<ObjectABOP> list = new ArrayList<ObjectABOP>();
		Ftp4jClientUitil f = new Ftp4jClientUitil(host, port, user, pass);
		f.connect();
		list = f.getFile(path);
		f.close();
		return list;
	}

	public static void main(String[] args) {
		Ftp4jClientUitil f = new Ftp4jClientUitil("172.16.9.65", 21, "vod", "vod");
		f.connect();
		ArrayList<ObjectABOP> list = f.getFile("/Movie");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
		}
		f.close();

	}
}
