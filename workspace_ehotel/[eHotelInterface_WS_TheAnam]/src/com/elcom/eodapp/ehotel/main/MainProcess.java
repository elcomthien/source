package com.elcom.eodapp.ehotel.main;

import javax.xml.ws.Endpoint;

import org.apache.log4j.PropertyConfigurator;

import com.elcom.eodapp.ehotel.cfg.Configuration;
import com.elcom.eodapp.ehotel.cfg.ConfigurationLoader;
import com.elcom.eodapp.ehotel.processor.BillProcessor;
import com.elcom.eodapp.ehotel.processor.JSONParser_SoKha;
import com.elcom.eodapp.ehotel.service.conn.eHotelConnection;
import com.elcom.eodapp.ehotel.service.json.CoreImpl;
import com.elcom.eodapp.ehotel.service.json.MessageProcessor;
import com.elcom.eodapp.ehotel.service.xpo.XposProcessor;
import com.elcom.eodapp.ehotel.sokha.CoreSoKhaInterface;
import com.elcom.eodapp.ehotel.sokha.CoreSoKhaInterfacePortType;
import com.elcom.eodapp.ehotel.utils.DAOFactory;

public class MainProcess { 
	
	
	public MainProcess() {
		PropertyConfigurator.configure("Config/log4j.properties");
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		MainObject.config = loader.getConfiguration();
	}
	
	public void RunProgram() {
		//StartWebService();
//		StartBillProcess();
//		StartMessageProcess();
//		StartEHotelConn();
		PublishWS();	
	}
	
	private void StartWebService() {
		/*CoreSoKhaInterface core = new CoreSoKhaInterface();
		MainObject.eHotelWebService = core.getCoreSoKhaInterfaceHttpSoap11Endpoint();
		*/
	}
	
	public static void PublishWS() {
		Thread thread = new Thread("Thread publish WS") 
		{
			@Override
			public void run() {
				Endpoint.publish(MainObject.config.publish_url, new CoreImpl());
			}
		};
		thread.start();		
	}
	
	public static void StartEHotelConn() {
		Thread conn = new eHotelConnection();
		conn.setName("Thread eHotel Connect");
		conn.start();
	}
	
	public static void StartBillProcess() {
		Thread thread = new BillProcessor();	
		thread.setName("Thread process bill");
		thread.start();
	}
	
	public static void StartMessageProcess() {
		Thread thread = new MessageProcessor();	
		thread.setName("Thread process message");
		thread.start();
	}

}
