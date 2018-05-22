package com.elcom.eodapp.ehotel.conn;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.omg.CORBA.ExceptionList;


import com.elcom.Log.FileLog;
import com.elcom.eodapp.ehotel.core.CommandProcessor;
import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.utils.DAOFactory;



public class QMSConnection extends Thread {
	
	Logger logger = Logger.getLogger(QMSConnection.class);
	//private static final FileLog logger_ = new FileLog();

	CommandProcessor cmdProcessor = DAOFactory.getInstance().getCommandProcessor();
	ServerSocket serverSocket = null;	
	
	boolean connected = false;
	int sleepInterval = 1000;
	
	static ArrayList<String> listReceiveData = new ArrayList<String>();		
		
	@Override
    public void run() 
    { 
    	//CreateServer();
		while(true) {
			System.out.println("------> connected: " + connected);
			if(connected == false)  {				
				CreateConnection();	
			}
			else 
				ProcessData();			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}       
    }    
    
    
    private void CreateConnection() {
		try {		
			MainObject.pmsSocket = null;
      
            
			//String host = InetAddress.getLocalHost().getHostAddress();
			String host = MainObject.config.pmsHostSocket;
			serverSocket = new ServerSocket(MainObject.config.portPMS);			
			logger.info("Server <QMSTransfer>  started on " + host + ", port "+ serverSocket.getLocalPort());
			logger.info("Waiting for connection.....");
			
			MainObject.pmsSocket = serverSocket.accept();			
			logger.info("Connection successful");
			connected = true;
		} 
		catch (IOException e) 
		{ 			
			logger.error("Accept failed.", e);			
			connected = false;
		} 
		
	}
    
    public void sendDataToAqs(String sentence,DataOutputStream data) throws IOException
    {
    	data.writeUTF(sentence);
    	data.flush();
    }
    
    public void ProcessData() 
    { 
    	try { 
    		MainObject.sendToClient = new DataOutputStream(MainObject.pmsSocket.getOutputStream());// Tao output stream 
            DataInputStream fromClient;
            fromClient = new DataInputStream(MainObject.pmsSocket.getInputStream());
            while (true) 
            {   
            	String sentence = "";
            	sentence = fromClient.readUTF();
            	
            	/*int byte_ = fromClient.read();
            	while (byte_ != -1)
            	{
            		System.out.print((char)byte_);
            		byte_ = fromClient.read();
            	}*/
            	System.out.println(sentence);
            	logger.info(sentence);
            	MainObject.logger_.write("AQS <= " + sentence);
            	
            	MainObject.logger_.flush();
            	String reverseSentence= cmdProcessor.ProcessReceiveCommands(sentence);
            	System.out.println("ACK: " + reverseSentence);
            	MainObject.logger_.write("ACK => " + reverseSentence);
            	sendDataToAqs(reverseSentence,MainObject.sendToClient);
            	MainObject.logger_.flush();
            }             
        } 
        catch (Exception e) 
        {    
        	try {
				logger.error("getKeepAlive: " + MainObject.pmsSocket.getKeepAlive() + " - isClosed: " + MainObject.pmsSocket.isClosed());
			
        	if(!MainObject.pmsSocket.isClosed())
        	{ 
        		connected = false;
        		serverSocket.close();
        		MainObject.sendToClient.close();
        		MainObject.sendToClient = null;
        		serverSocket = null;
        	} 
            logger.error("PMS - Process Data error:" + e); 
        	} catch(Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } 
    }
    
   
     
}
