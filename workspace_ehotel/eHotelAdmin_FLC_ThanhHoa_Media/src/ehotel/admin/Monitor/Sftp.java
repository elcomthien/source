package ehotel.admin.Monitor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


public class Sftp {
	 private String hostname; 
	 private String username;
	 private String password;
	 private int port;
	 private String savefile;
	 private String remotedir;
	 private String filename;
	 public Sftp(String _hostname,String _username,String _password,
			 int _port,String _savefile,String _remotedir){
		 this.hostname =_hostname;
		 this.username=_username;
		 this.password=_password;
		 this.port=_port;
		 this.savefile =_savefile;
		 this.remotedir =_remotedir;
	 }
	public boolean downloadFile(String filename) throws SftpException, IOException{
		FileReader reader =null;
        BufferedReader buffer = null;
        Channel channel = null;
		JSch jsch = new JSch();
	    Session session = null;
	    boolean kq =false;
	    try {
	    	
			session = jsch.getSession(username, hostname,port);
			 session.setConfig("StrictHostKeyChecking", "no");
			    session.setPassword(password);
			    session.connect();
			    channel = session.openChannel("sftp");
			    channel.connect();
			    ChannelSftp sftpChannel = (ChannelSftp) channel;
			    sftpChannel.cd(remotedir);
			    File file = new File(savefile);
			    if(!file.exists()){
			    	file.createNewFile();
			    }
			    sftpChannel.get(filename,savefile);
			    sftpChannel.exit();
		        session.disconnect();
		        kq =true;
		} catch (JSchException e) {
			 kq =false;
			//e.printStackTrace();
		}
			return kq;
	}

}
