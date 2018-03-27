package com.elcom.eodapp.ehotel.service.conn;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.apache.log4j.Logger;

import com.elcom.ehotel.variables.Common;
import com.elcom.ehotel.variables.Common.EHOTEL_REQ;
import com.elcom.ehotel.variables.Common.EXECUTE_STATUS;
import com.elcom.eodapp.ehotel.common.oBill;
import com.elcom.eodapp.ehotel.common.oPost;
import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.service.xpo.XposProcessor;
import com.elcom.eodapp.ehotel.service.xpo.XposXmlProcessor;
import com.elcom.eodapp.ehotel.service.sokha.CoreSoKhaInterface;
import com.elcom.eodapp.ehotel.utils.DAOFactory;

public class eHotelConnection extends Thread{
	
	Logger logger = Logger.getLogger(eHotelConnection.class);
	ServerSocket serverSocket = null;	
	Socket clientSocket = null; 
	DataOutputStream sendToClient = null;
	BufferedReader fromClient = null;
	boolean connected = false;
	
	XposXmlProcessor process = DAOFactory.getInstance().getXposXmlProcessor();
	CoreSoKhaInterface core = DAOFactory.getInstance().getCoreSoKhaInterface();
	
	public void run()  
	{ 		 
		CreateServer();	
		while(true) {
			if(connected == false) 
				CreateConnection();
			ProcessData();			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	
	
	public void CreateServer() {
		try { 
			String host = InetAddress.getLocalHost().getHostAddress();
			serverSocket = new ServerSocket(MainObject.config.portEHotel);			
			logger.info("Server " + host + " started on port "+ serverSocket.getLocalPort());
			logger.info("Waiting for connection.....");
		} 
		catch (IOException e) 
		{ 
			logger.error("Could not listen on port: " + MainObject.config.portEHotel); 			 
		}    
	}
	
	private void CreateConnection() {
		try {
			clientSocket = serverSocket.accept();
			System.out.println(clientSocket.isConnected());
		} 
		catch (IOException e) 
		{ 
			logger.error("Accept failed.", e); 			
		} 
		System.out.println ("Connection successful");
		connected = true;
	}
	
	public void ProcessData() {
		try {
			String receiveData = "";
			
			fromClient = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
			sendToClient = new DataOutputStream(clientSocket.getOutputStream());
			try {					
				char buffer[] = new char[65792];
				fromClient.read(buffer);

				for (char c : buffer)
            	{            		
            		if(c == Common.ETX) {
            			logger.info("from ehotel:" + receiveData);
            			sendToClient.writeBytes(EXECUTE_STATUS.OK.value);
    					sendToClient.flush();
    					logger.info("Reply to client: " + receiveData + "-" + EXECUTE_STATUS.OK.value);
    					ProcessRequire(receiveData.trim());
    					receiveData = "";
            		}
            		else if(c == Common.STX)
            			receiveData = "";
            		else 
            			receiveData += Character.toString(c);
            	}					
			}  
			catch (Exception e) { 
				logger.error("Process data from eHotel socket error.", e);					
				connected = false;				
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block			
			connected = false;
		} 
	}
	
	private void ProcessRequire(String receiveData) {			
		int cmdType = Integer.parseInt(receiveData.toString().trim());
//		if(cmdType  == EHOTEL_REQ.BILL.value) 
		{
			if(MainObject.ListBillReq.size() > 0) 
			{
				return;
			}
			oBill[] listBill = core.guestBillReq("");
			logger.info("BillReq count:" + listBill.length);
			synchronized (MainObject.ListBillReq) {
				for(oBill bill : listBill) {
					String GUEST_ROOM = bill.getROOM_NUMER();
					String GUEST_RESERVATION = bill.getRESERVATION_NUMER();
					String item = GUEST_ROOM + "-" + GUEST_RESERVATION;

					if(!MainObject.ListBillReq.contains(item))
						MainObject.ListBillReq.add(item);
				}
				logger.info("Bill in array:" + MainObject.ListBillReq.size());
			}
//		}
//		else if(cmdType  == EHOTEL_REQ.POST.value) {
			oPost[] listPost = core.guestPostReq("");
			logger.info("PostReq count :" + listPost.length);
			for(oPost post : listPost) {
				String GUEST_ROOM = post.getROOM_NUMBER();
				String POST_AMOUNT = post.getPOST_AMOUNT();
				String GUEST_RESERVATION = post.getDURATION();
				String CHARGE_CODE = post.getPOSTING_TYPE();
				process.guestPost(GUEST_ROOM, GUEST_RESERVATION, POST_AMOUNT, CHARGE_CODE);
			}
//		}
//		else if(cmdType  == EHOTEL_REQ.DBSYNC.value) {
			//process.dbSwap();
		}		
	}
}
