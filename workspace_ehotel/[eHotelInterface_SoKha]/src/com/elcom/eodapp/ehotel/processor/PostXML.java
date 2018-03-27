package com.elcom.eodapp.ehotel.processor;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

import org.apache.log4j.Logger;

import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.utils.DataHelper;

public class PostXML {
	
	Logger logger = Logger.getLogger(PostXML.class);
	String hostname = MainObject.config.xpos_ipaddress;
	int port = MainObject.config.xpos_port;
	String path = MainObject.config.xpos_path;
	Socket sock;
	
	ArrayList<String> ListParam = new ArrayList<String>();
	
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
	
	public String PostData(String xmldata) {
		boolean connect = ConnectServer();
		if(connect == false) 
		{
			return "";
		}
		StringBuffer outData = new StringBuffer();		
		try {
			
			BufferedWriter  wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(),"UTF-8"));	      
			wr.write(xmldata);
			wr.flush();
			
			Thread.sleep(MainObject.config.xpos_bill_recv_delay * 1000);
			
			// Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String line;
			while((line = rd.readLine()) != null)
				outData.append(line + "\r\n");
			sock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = DataHelper.RebuildMessage(outData.toString(), false);
		return message;
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
