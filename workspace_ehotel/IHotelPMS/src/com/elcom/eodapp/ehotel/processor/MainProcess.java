package com.elcom.eodapp.ehotel.processor;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.WebServer;

import com.elcom.eodapp.ehotel.service.CoreInterface;

import elcom.com.cfg.ConfigurationLoader;
import elcom.com.cfg.ReaderProperties;
import elcom.com.webserver.eHotelPMSServer;

public class MainProcess {
	Logger log = Logger.getLogger(MainObject.class);

	public MainProcess() {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		MainObject.config = loader.getConfiguration();
	}

	public void RunProgram() {
		log.info("Start run program");
		InitWebService();
		StartPMSServer();
	}

	private void InitWebService() {
		URL url;
		try {
			url = new URL(MainObject.config.eHotel_wsdl);
			CoreInterface cr = new CoreInterface(url);
			MainObject.eHotelWebService = cr.getCoreInterfaceHttpSoap11Endpoint();
			log.info("Connect web service " + MainObject.config.eHotel_wsdl + " is success!");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			log.error("Connect web service " + MainObject.config.eHotel_wsdl + " error.");
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void StartPMSServer() {
		try {
			ReaderProperties configReader = new ReaderProperties();
			int port = Integer.parseInt(configReader.getProperty("xmlrcp.webserver.port", "60006"));
			log.info("Attempting to start XML-RPC Server port:" + port + ".....");
			WebServer server = new WebServer(port);
			server.addHandler("ehotelPms", new eHotelPMSServer());
			server.start();
			log.info("Started successfully.");
			log.info("Accepting requests. (Halt program to stop.)");
		} catch (Exception exception) {
			log.error("eHotelPMSServer: " + exception);
		}
	}
}
