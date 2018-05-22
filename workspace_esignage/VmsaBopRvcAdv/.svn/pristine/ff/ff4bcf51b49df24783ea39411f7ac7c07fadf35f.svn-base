package com.elcom.adcenter.rvcadv.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import com.elcom.adcenter.rvcadv.common.VoStbSession;

public class DataSocketSesionStb {
	private DataInputStream Sinput;          // to read the socker
	private DataOutputStream Soutput;        // towrite on the socket
	private Socket socket;
    private Vector<VoStbSession> StbSessions;
    
    public DataSocketSesionStb(String Server,int port)
    {
    	try {
            socket = new Socket(Server, port);
    	}
    	catch(Exception e) {
            System.out.println("Error connectiong to server:" + e);
            return;
    	}
    	//System.out.println("Connection accepted " + socket.getInetAddress() + ":" +  socket.getPort());
    	
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
   
    //-----------------------------------------------
    public void sendCommand(int thongtin) throws Exception
    {       	
    	Soutput.write(UntilSocket.get_int_arr(thongtin)); 
    	Soutput.flush();     	
    }    
    //-----------------------------------------------
    private String resultAcceptSesions()
    {    	
    	// read back the answer from the server
    	String xml = "<STB>\r\n";
        int len = 0,errorcode = 0;
        String ip;
        int count = 1;
        String sesionid,serial_num,mac;
        StbSessions = new Vector<VoStbSession>();
        VoStbSession item;
        try {       	    
        		byte[] int_bytes = new byte[4];
        		byte[] char_byte;
        		Sinput.read(int_bytes);
        		len = UntilSocket.get_int(int_bytes);
        		int pos = 0;        		
        		while( len > 0)
        		{        			
        			item = new VoStbSession();
        			char_byte = new byte[len];
        			Sinput.read(char_byte,0,len);
        			sesionid = new String(char_byte);
        			
        			int_bytes = new byte[4];
        			Sinput.read(int_bytes);
        			ip = (int_bytes[0]&0xFF) + "." + (int_bytes[1]&0xFF) + "." + (int_bytes[2]&0xFF) + "." + (int_bytes[3]&0xFF);
            		//ip = UntilSocket.get_int(int_bytes);        			
            		
            		int_bytes = new byte[4];
            		Sinput.read(int_bytes);
            		len = UntilSocket.get_int(int_bytes);
            		
            		
            		char_byte = new byte[len];
            		Sinput.read(char_byte,0,len);
            		serial_num = new String(char_byte);            		
            		
            		int_bytes = new byte[4];
            		Sinput.read(int_bytes);
            		len = UntilSocket.get_int(int_bytes);
            		
            		
            		char_byte = new byte[len];
            		Sinput.read(char_byte,0,len);
            		mac = new String(char_byte);            		
            		
            		int_bytes = new byte[4];
            		Sinput.read(int_bytes);
            		len = UntilSocket.get_int(int_bytes);
            		errorcode = len;            		          		
            		
            		xml = xml + "<item id=" + count + ">\r\n";
            		xml = xml + "<session_id>" + sesionid + "</session_id>\r\n";
            		xml = xml + "<ip>" + ip + "</ip>\r\n";
            		xml = xml + "<serial_num>" + serial_num + "</serial_num>\r\n";
            		xml = xml + "<mac>" + mac + "</mac>\r\n";
            		xml = xml + "</item>\r\n";
            		count++;		
            		item.setMac(mac);
            		item.setSerial_num(serial_num);
            		item.setSesionid(sesionid);
            		StbSessions.add(item);
        		}
        }
        catch(Exception e) {
                e.printStackTrace();
        }
        try{
                Sinput.close();
                Soutput.close();
        }        
        catch(Exception e) {} 
        xml = xml + "</STB>\r\n";
        return xml;
    }
    //----------------------------------------------
    public String getListSessionStb(DataSocketSesionStb data,int command)
    {
    	String sessionstbs = "";
    	try{    		
    		data.sendCommand(command);
    		sessionstbs = data.resultAcceptSesions();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return sessionstbs;
    }
    //-----------------------------------------------
    public Vector<VoStbSession> getStbSession()
    {
    	return StbSessions;
    }
    //-----------------------------------------------
    public static void main(String[] arg)
    {
    	//dev.elcomhcm.com
    	DataSocketSesionStb data = new DataSocketSesionStb("172.16.4.140", 2235);
    	try{    		
    		System.out.println(data.getListSessionStb(data,6));	
    	}catch(Exception ex){ex.printStackTrace();}
    }
}