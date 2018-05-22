package ehotel.admin.util;

import it.sauronsoftware.ftp4j.FTPClient;

public class Ftp4jClient {
	private String Host = "";
	private int Port = 21;
	private String User = "";
	private String Pass = "";
	FTPClient client = new FTPClient();

	public Ftp4jClient(String host, int port, String user, String pass) {
		super();
		Host = host;
		Port = port;
		User = user;
		Pass = pass;
	}

	public void connect() {
		try {
			client.connect(this.Host, this.Port);
			client.login(User, Pass);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteFileFTPVod(String filename, String folder) {
		boolean flag = true;
		try {
			client.changeDirectory(folder);
			client.deleteFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean deleteFileFTPMod(String filename, String folder) {
		boolean flag = true;
		try {
			client.changeDirectory(folder);
			client.deleteFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public void close() {
		try {
			client.disconnect(false);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		Ftp4jClient f = new Ftp4jClient("172.16.9.222", 1622, "ftpmedia", "ftpmedia");
		f.connect();
		f.deleteFileFTPVod("mv.mp4", "vod");
		f.close();
	}
}
