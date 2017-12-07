package ehotel.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import ehotel.admin.Vod.ConfigService;

public class TestJava {
	private ConfigService configService = new ConfigService();

	public boolean exportFileData(String filename) {
		boolean flag = true;
		String pathwrite = "D:/config-addchannel.properties";
		String pathread = "D:/data.txt";
		String text = configService.ReadFile(pathread);
		String[] arr = text.split(",");
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(pathwrite);
			prop.setProperty("serverSrc", arr[0]);
			prop.setProperty("portSrc", arr[1]);
			prop.setProperty("userSrc", arr[2]);
			prop.setProperty("passSrc", arr[3]);
			prop.setProperty("filename", filename);
			prop.store(output, null);
			System.out.println(arr[3]);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return flag;
	}
	public boolean deleteFileVideoAdvertise(String filename) {
		System.out.println("Delete file on server 12: "+ filename);
		boolean flag = true;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession("root", "172.16.9.188", 22);
			session.setPassword("elcom#@");
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.rm("/home/data/loop_video/" + filename);
			channel.disconnect();
			session.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args) {
		TestJava t = new TestJava();
		System.out.println(t.deleteFileVideoAdvertise("abc.mp4"));
		
		
	}
}
