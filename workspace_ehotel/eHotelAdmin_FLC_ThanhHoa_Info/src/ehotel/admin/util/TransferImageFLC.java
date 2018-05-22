package ehotel.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class TransferImageFLC {

	private String host01 = "";
	private int port01 = 22;
	private String user01 = "";
	private String pass01 = "";

	private String host03 = "";
	private int port03 = 22;
	private String user03 = "";
	private String pass03 = "";

	private String path01 = "";
	private String path02 = "";

	@Override
	public String toString() {
		return "TransferImageFLC [host01=" + host01 + ", port01=" + port01
				+ ", user01=" + user01 + ", pass01=" + pass01 + ", host03="
				+ host03 + ", port03=" + port03 + ", user03=" + user03
				+ ", pass03=" + pass03 + ", path01=" + path01 + ", path03="
				+ path02 + "]";
	}

	public TransferImageFLC() {
		String path = TransferImageFLC.class.getResource("").getPath();
		path = path.substring(0, path.lastIndexOf("classes"));
		path = path + "ConfigMediaFLC.properties";

		Properties prop = new Properties();
		InputStream input = null;
		try {
			System.out.println("get ConfigMediaFLC.properties");
			input = new FileInputStream(path);
			prop.load(input);
		} catch (Exception e) {
			System.out.println("get ConfigMediaFLC.properties error \n" + e);
		}

		host01 = prop.getProperty("host01");
		port01 = Integer.parseInt(prop.getProperty("port01"));
		user01 = prop.getProperty("user01");
		pass01 = prop.getProperty("pass01");

		host03 = prop.getProperty("host03");
		port03 = Integer.parseInt(prop.getProperty("port03"));
		user03 = prop.getProperty("user03");
		pass03 = prop.getProperty("pass03");

		path01 = prop.getProperty("path01");
		path02 = prop.getProperty("path02");

		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(toString());
	}

	public boolean transserImageeHotel(String host, int port, String user,
			String pass, String filepathcurrent, String pathsave) {
		boolean flag = true;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(user, host, port);
			session.setPassword(pass);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(pathsave);
			File f = new File(filepathcurrent);
			String fileName = f.getName();
			channelSftp.put(new FileInputStream(f), fileName);
			channel.disconnect();
			session.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public void transferImageServer01eHotel01(String filepath, String folder) {
		transserImageeHotel(host01, port01, user01, pass01, filepath, path01
				+ folder + "/");
	}

	public void transferImageServer01eHotel02(String filepath, String folder) {
		transserImageeHotel(host01, port01, user01, pass01, filepath, path02
				+ folder + "/");
	}

	public void transferImageServer03eHotel01(String filepath, String folder) {
		transserImageeHotel(host03, port03, user03, pass03, filepath, path01
				+ folder + "/");
	}

	public void transferImageServer03eHotel02(String filepath, String folder) {
		transserImageeHotel(host03, port03, user03, pass03, filepath, path02
				+ folder + "/");
	}

	public void transferImageServer02eHotel02(String filepath, String folder) {
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1,
				filepath.length());
		ManagerFile file = new ManagerFile();
		file.copy(filepath, path02 + folder + "/" + filename);
	}

	public void transferImageMain(String filepath, String folder) {
		transferImageServer01eHotel01(filepath,folder);
		transferImageServer01eHotel02(filepath, folder);
		transferImageServer02eHotel02(filepath, folder);
		transferImageServer03eHotel01(filepath, folder);
		transferImageServer03eHotel02(filepath, folder);
	}

	public static void main(String[] args) {
		String filepath = "/core/ehotel01/service/app/apache-tomcat-7.0.34/webapps/ePMS2.0/elcom/resources/image/abd.png";
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1,
				filepath.length());
		System.out.println(filename);
	}
}
