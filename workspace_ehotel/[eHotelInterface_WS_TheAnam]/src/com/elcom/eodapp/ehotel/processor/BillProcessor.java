package com.elcom.eodapp.ehotel.processor;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

import org.apache.log4j.Logger;

import com.elcom.ehotel.variables.CommandVariables.CMD_LINKCTRL;
import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.OPERATION_NAME;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_ACK;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_IN_PARAM;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.ACK_SOKHA;
import com.elcom.eodapp.ehotel.utils.DataHelper;
import com.sun.xml.messaging.saaj.packaging.mime.util.OutputUtil;

public class BillProcessor extends Thread {
	
	Logger logger = Logger.getLogger(BillProcessor.class);
	SOAPParser_SoKha parser = DAOFactory.getInstance().getSoapParser_SK();
	
	String hostname = MainObject.config.xpos_ipaddress;
	int port = MainObject.config.xpos_port;
	String path = MainObject.config.xpos_path;
	int receive_delay = MainObject.config.xpos_bill_recv_delay;
	int process_time = MainObject.config.xpos_bill_process;
	Socket sock;
		
	ArrayList<String> ListParam = new ArrayList<String>();	

	@Override
	public void run() {		

		ArrayList<String> copyData = new ArrayList<String>();
		while(true) {
			try {
				if(MainObject.ListBillReq.size() > 0) {						
					//dataProcessing = true;
					synchronized (MainObject.ListBillReq) {
						copyData.addAll(MainObject.ListBillReq);							
					}
					
					logger.info("Start bill sequence");
					for(String sentence : copyData) {
						String GUEST_ROOM = sentence.split("-")[0];
						String GUEST_RESERVATION = sentence.split("-")[1];
						logger.info("send bill:" + sentence);
						PostData(GUEST_ROOM, GUEST_RESERVATION);						
					}
					copyData.clear();
					MainObject.ListBillReq.clear();	
					logger.info("End bill sequence");						
					//sock.close();	
				}
				else {
					logger.info("No bill need to process");
				}
				Thread.sleep(5 * 1000);
			}
			catch(Exception ex) 
			{		
				System.out.println(ex.getMessage());				
				logger.error(ex);
			}
		}				

	}

	private boolean ConnectServer() {
		//Create socket		
		try {
			InetAddress addr  = InetAddress.getByName(hostname);
			sock = new Socket(addr, port);
			logger.info("Connect " + hostname + ":" + port + " is success!");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Connect " + hostname + ":" + port + " error");
			logger.error(e);
			return false;
		}
	}
	
	public Integer PostData(String GUEST_ROOM, String GUEST_RESERVATION) {
		AddParam(XPOS_IN_PARAM.GUEST_ROOM.value, "xsd:string", GUEST_ROOM);
		AddParam(XPOS_IN_PARAM.GUEST_RESERVATION.value, "xsd:string", GUEST_RESERVATION); 
		String xmldata  = CreateXML(OPERATION_NAME.BILL.value);		
		logger.info(xmldata);
		try {			
			logger.info("Start receive bill(GUEST_ROOM:" + GUEST_ROOM + ",GUEST_RESERVATION:" + GUEST_RESERVATION + ")");
			int timeTotal = 0;
			int responseCode = XPOS_ACK.EXECUTING.value;
			while(responseCode == XPOS_ACK.EXECUTING.value && timeTotal <= process_time) {
				
				boolean connected = ConnectServer();
				if(connected == false) {
					Thread.sleep(1000);
					continue;
				}
				BufferedWriter  wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(),"UTF-8"));
				wr.write(xmldata);
				wr.flush();
				
				Thread.sleep(receive_delay * 1000);
				timeTotal += receive_delay;
				logger.info("time processed = " + timeTotal);
				
				//String outData = ReceiveOutData();
				BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				StringBuffer outData = new StringBuffer();
				String line;
				while((line = rd.readLine()) != null)
					outData.append(line + "\r\n");	
				
				if(outData.length() == 0) 
					logger.info("process bill(GUEST_ROOM:" + GUEST_ROOM + ",GUEST_RESERVATION:" + GUEST_RESERVATION + ") out empty");			
				else {	
					String message = DataHelper.RebuildMessage(outData.toString(), false);
					responseCode = ProcessData(GUEST_ROOM, GUEST_RESERVATION, outData.toString());
				}
				sock.close();
			}			
			logger.info("End receive bill(GUEST_ROOM:" + GUEST_ROOM + ",GUEST_RESERVATION:" + GUEST_RESERVATION + ")");
			return responseCode;
		} 
		catch (Exception e) {
			e.printStackTrace();
			logger.info("process bill(GUEST_ROOM:" + GUEST_ROOM + ",GUEST_RESERVATION:" + GUEST_RESERVATION + ") fail." );
			logger.error(e);
			return XPOS_ACK.FAIL.value;
		}		
	}
	
	private String ReceiveOutData() {
		// Response
		StringBuffer outData = new StringBuffer();
		try {
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String line;
			while((line = rd.readLine()) != null)
				outData.append(line + "\r\n");			
		}
		catch(Exception ex) {
			logger.error(ex);
		}
		return outData.toString();
	}
	
	private Integer ProcessData(String GUEST_ROOM, String GUEST_RESERVATION, String outData) {
		if(outData.length() == 0) 
			return -1;
		int index = outData.indexOf("SOAP-ENV");
		String soapObjectString = outData.substring(index-1);
		Integer rs = parser.ParseSOAPData_Bill(GUEST_ROOM,GUEST_RESERVATION,soapObjectString);
		return rs;
	}	
	
	public String CreateXML(String operationName) {
		String xmldata = "<?xml version='1.0' encoding='UTF-8' ?>" +       
    			"<S:Envelope xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'>" + 
    			"<S:Header/> " + 
    			"<S:Body> " + 
    			"<" + operationName + " soapenv:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'> "+
    			GetParamString() + 
    			"</" + operationName + "> "+
    			"</S:Body> " +
    			"</S:Envelope> ";
		
		String txtPOST = "POST " + path + " HTTP/1.0\r\n" + 
					  "Host: " + hostname + "\r\n" + 
					  "Content-Length: " + xmldata.length() + "\r\n" + 
					  "Content-Type: text/xml; charset=\"utf-8\"\r\n" + 
					  "\r\n" + 
					  xmldata;				
		return txtPOST;
	}
	
	public void AddParam(String name, String type, String value) {
		String param = "<" + name + " xsi:type='" + type + "'>" + value + "</" + name + ">";
		ListParam.add(param);
	}
	
	private String GetParamString() {
		String result = "";
		for(String p : ListParam) {
			result += p;
		}
		ListParam.clear();
		return result;
	}
}
