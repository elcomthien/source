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

	private String path21_01_01 = "/core/ehotel01/service/app/apache-tomcat-7.0.34/webapps/ePMS2.0/elcom/resources/image/";
	private String path21_01_02 = "/core/ehotel01/service/apache-loadbalance-02/webapps/ePMS2.0/elcom/resources/image/";
	private String path21_01_03 = "/core/ehotel01/service/apache-loadbalance-03/webapps/ePMS2.0/elcom/resources/image/";

	private String path22_02_01 = "/core/ehotel02/service/app/loadbalance/apache-tomcat-7.0.34-01/webapps/ePMS2.0/elcom/resources/image/";
	private String path22_02_02 = "/core/ehotel02/service/app/loadbalance/apache-tomcat-7.0.34-02/webapps/ePMS2.0/elcom/resources/image/";
	private String path22_02_03 = "/core/ehotel02/service/app/loadbalance/apache-tomcat-7.0.34-03/webapps/ePMS2.0/elcom/resources/image/";

	private String path23_02_01 = "/core/ehotel02/service/app/loadbalance/apache-tomcat-7.0.34-01/webapps/ePMS2.0/elcom/resources/image/";
	private String path23_02_02 = "/core/ehotel02/service/app/loadbalance/apache-tomcat-7.0.34-02/webapps/ePMS2.0/elcom/resources/image/";
	private String path23_02_03 = "/core/ehotel02/service/app/loadbalance/apache-tomcat-7.0.34-03/webapps/ePMS2.0/elcom/resources/image/";
	private String path23_02_04 = "/core/ehotel02/service/app/loadbalance/apache-tomcat-7.0.34-04/webapps/ePMS2.0/elcom/resources/image/";

	@Override
	public String toString() {
		return "TransferImageFLC [host01=" + host01 + ", port01=" + port01 + ", user01=" + user01 + ", pass01=" + pass01 + ", host03="
				+ host03 + ", port03=" + port03 + ", user03=" + user03 + ", pass03=" + pass03 + ", path21_01_01=" + path21_01_01
				+ ", path21_01_02=" + path21_01_02 + ", path21_01_03=" + path21_01_03 + ", path22_02_01=" + path22_02_01
				+ ", path22_02_02=" + path22_02_02 + ", path22_02_03=" + path22_02_03 + ", path23_02_01=" + path23_02_01
				+ ", path23_02_02=" + path23_02_02 + ", path23_02_03=" + path23_02_03 + ", path23_02_04=" + path23_02_04 + "]";
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

		path21_01_01 = prop.getProperty("path21_01_01");
		path21_01_02 = prop.getProperty("path21_01_02");
		path21_01_03 = prop.getProperty("path21_01_03");
		
		path22_02_01 = prop.getProperty("path22_02_01");
		path22_02_02 = prop.getProperty("path22_02_02");
		path22_02_03 = prop.getProperty("path22_02_03");
		
		path23_02_01 = prop.getProperty("path23_02_01");
		path23_02_02 = prop.getProperty("path23_02_02");
		path23_02_03 = prop.getProperty("path23_02_03");
		path23_02_04 = prop.getProperty("path23_02_04");

		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(toString());
	}

	public boolean transferImageeHotel(String host, int port, String user, String pass, String filepathcurrent, String pathsave) {
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

	public void transferImageServer01eHotel01LoadBalance01(String filepath, String folder) {
		transferImageeHotel(host01, port01, user01, pass01, filepath, path21_01_01 + folder + "/");
	}

	public void transferImageServer01eHotel01LoadBalance02(String filepath, String folder) {
		transferImageeHotel(host01, port01, user01, pass01, filepath, path21_01_02 + folder + "/");
	}

	public void transferImageServer01eHotel01LoadBalance03(String filepath, String folder) {
		transferImageeHotel(host01, port01, user01, pass01, filepath, path21_01_03 + folder + "/");
	}

	// public void transferImageServer01eHotel02(String filepath, String folder) {
	// transferImageeHotel(host01, port01, user01, pass01, filepath, path02 + folder + "/");
	// }

	public void transferImageServer02eHotel02LoadBalance01(String filepath, String folder) {
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
		ManagerFile file = new ManagerFile();
		file.copy(filepath, path22_02_01 + folder + "/" + filename);
	}

	public void transferImageServer02eHotel02LoadBalance02(String filepath, String folder) {
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
		ManagerFile file = new ManagerFile();
		file.copy(filepath, path22_02_02 + folder + "/" + filename);
	}

	public void transferImageServer02eHotel02LoadBalance03(String filepath, String folder) {
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
		ManagerFile file = new ManagerFile();
		file.copy(filepath, path22_02_03 + folder + "/" + filename);
	}

	public void transferImageServer03eHotel01LoadBalance01(String filepath, String folder) {
		transferImageeHotel(host03, port03, user03, pass03, filepath, path23_02_01 + folder + "/");
	}
	
	public void transferImageServer03eHotel01LoadBalance02(String filepath, String folder) {
		transferImageeHotel(host03, port03, user03, pass03, filepath, path23_02_02 + folder + "/");
	}
	
	public void transferImageServer03eHotel01LoadBalance03(String filepath, String folder) {
		transferImageeHotel(host03, port03, user03, pass03, filepath, path23_02_03 + folder + "/");
	}
	
	public void transferImageServer03eHotel01LoadBalance04(String filepath, String folder) {
		transferImageeHotel(host03, port03, user03, pass03, filepath, path23_02_04 + folder + "/");
	}

//	public void transferImageServer03eHotel02(String filepath, String folder) {
//		transferImageeHotel(host03, port03, user03, pass03, filepath, path02 + folder + "/");
//	}

	public void transferImageMain(String filepath, String folder) {
		transferImageServer01eHotel01LoadBalance01(filepath, folder);
		transferImageServer01eHotel01LoadBalance02(filepath, folder);
		transferImageServer01eHotel01LoadBalance03(filepath, folder);
		
		transferImageServer02eHotel02LoadBalance01(filepath, folder);
		transferImageServer02eHotel02LoadBalance02(filepath, folder);
		transferImageServer02eHotel02LoadBalance03(filepath, folder);
		
		transferImageServer03eHotel01LoadBalance01(filepath, folder);
		transferImageServer03eHotel01LoadBalance02(filepath, folder);
		transferImageServer03eHotel01LoadBalance03(filepath, folder);
		transferImageServer03eHotel01LoadBalance04(filepath, folder);
	}

	public static void main(String[] args) {
		String filepath = "/core/ehotel01/service/app/apache-tomcat-7.0.34/webapps/ePMS2.0/elcom/resources/image/abd.png";
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
		System.out.println(filename);
	}
}
