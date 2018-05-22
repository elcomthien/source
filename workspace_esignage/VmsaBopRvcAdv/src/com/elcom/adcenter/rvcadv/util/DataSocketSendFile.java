package com.elcom.adcenter.rvcadv.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.elcom.adcenter.rvcav.content.ServiceContentDao;

public class DataSocketSendFile {
	private DataInputStream Sinput;          // to read the socker
	private DataOutputStream Soutput;        // towrite on the socket
	private Socket socket;
	private static Logger log = Logger.getLogger(ServiceContentDao.class);
	public DataSocketSendFile(String Server,int port)
    {
    	try {
            socket = new Socket(Server, port);
    	}
    	catch(Exception e) {
            System.out.println("Error connectiong to server:" + e);
            return;
    	}
    	System.out.println("Connection accepted " + socket.getInetAddress() + ":" +  socket.getPort());
    	
        try
        {
                Sinput  = new DataInputStream(socket.getInputStream());
                Soutput = new DataOutputStream(socket.getOutputStream());              
        }
        catch (IOException e) {
                System.out.println("Exception creating new Input/output Streams: " + e);
                return;
        }
    }
	//----------------------------------------------
		public void sendCommandRemove(String session_id,String src_file) throws Exception
	    {    	
			int request_type = 3;
			int commad_type = 1;
			int len_session_id = session_id.length();
			int len_src_file = 0;
			int len_des_file = 0;
			byte[] sesion_id;
			String setter_ip  = "127.0.0.1";    	
	    	
	    	Soutput.write(UntilSocket.get_int_arr(request_type));    //gui req      	
	    	Soutput.write(UntilSocket.get_int_arr(len_session_id));  // gui len session
	    	sesion_id = session_id.getBytes();     
	    	Soutput.write(sesion_id);                                //gui session
	    	
	    	Soutput.write(UntilSocket.get_int_arr(commad_type));     // gui lenh 1
	    	Soutput.write(UntilSocket.get_int_arr(4 + len_src_file + 4 + len_des_file));  //gui tong chieu dai của 4 phan tu
	    	Soutput.write(UntilSocket.get_int_arr(len_src_file));    // gui chieu dai src_file
	    	Soutput.write(src_file.getBytes());                      // gui noi dung cua src_file
	    	Soutput.write(UntilSocket.get_int_arr(len_des_file));    // gui chieu dai des_file
	    	//Soutput.write(des_file.getBytes());                      // gui noi dung cua des_file
	    	Soutput.write(UntilSocket.get_int_arr(setter_ip.length())); // gui len setter_ip
	    	Soutput.write(setter_ip.getBytes());                        // gui noi dung setter_ip
	    	commad_type = 0;
	    	Soutput.write(UntilSocket.get_int_arr(commad_type));      // gui lenh 0
	    	
	    	Soutput.flush();     	
	    }  
	//----------------------------------------------
	public void sendCommand(String session_id,String src_file,String des_file) 
    {    	
		
		int request_type = 3;
		int commad_type = 1;
		int len_session_id = session_id.length();
		int len_src_file = src_file.length();
		int len_des_file = des_file.length();
		byte[] sesion_id;
		String setter_ip  = "127.0.0.1";    	
		log.info("DataSocketSendFile.sendCommanf(" + session_id  + "," + src_file + "," + des_file + ")");
		System.out.println("DataSocketSendFile.sendCommanf(" + session_id  + "," + src_file + "," + des_file + ")");
		try{
	    	Soutput.write(UntilSocket.get_int_arr(request_type));    //gui req      	
	    	Soutput.write(UntilSocket.get_int_arr(len_session_id));  // gui len session
	    	sesion_id = session_id.getBytes();     
	    	Soutput.write(sesion_id);                                //gui session
	    	
	    	Soutput.write(UntilSocket.get_int_arr(commad_type));     // gui lenh 1
	    	Soutput.write(UntilSocket.get_int_arr(4 + len_src_file + 4 + len_des_file));  //gui tong chieu dai của 4 phan tu
	    	Soutput.write(UntilSocket.get_int_arr(len_src_file));    // gui chieu dai src_file
	    	Soutput.write(src_file.getBytes());                      // gui noi dung cua src_file
	    	Soutput.write(UntilSocket.get_int_arr(len_des_file));    // gui chieu dai des_file
	    	Soutput.write(des_file.getBytes());                      // gui noi dung cua des_file
	    	Soutput.write(UntilSocket.get_int_arr(setter_ip.length())); // gui len setter_ip
	    	Soutput.write(setter_ip.getBytes());                        // gui noi dung setter_ip
	    	commad_type = 0;
	    	Soutput.write(UntilSocket.get_int_arr(commad_type));      // gui lenh 0
	    	
	    	Soutput.flush();
		}catch(Exception ex){
			ex.printStackTrace();
		}
    } 	
	
	//----------------------------------------------
	public static void main(String[] arg)
    {
		DataSocketSendFile data = new DataSocketSendFile("172.16.4.140", 2235);
    	try{    		
    		String session_id = "6a56c9ea-41c2-9b6a-4b9d-8e337082c0a5";
    		String src_file = "elc://172.16.4.140:2236///root/dund/GioiThieuSJC_0.mp4";
    		String des_file = "/data/local/source/test/";
    		data.sendCommand(session_id, src_file, des_file);    		
    	}catch(Exception ex){ex.printStackTrace();}
    }
}
