package com.elcom.eodapp.ehotel.main;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

import com.elcom.eodapp.ehotel.cfg.ConfigurationLoader;
import com.elcom.eodapp.ehotel.conn.QMSConnection;


public class MainProcess { 
	
	
	public MainProcess() {
		PropertyConfigurator.configure("Config/log4j.properties");
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		MainObject.config = loader.getConfiguration();
	}
	
	public void RunProgram() {
		StartQMSConnection();
		StartCommandToQms();
	}
	
	
	public static void StartQMSConnection() {
		Thread thread = new QMSConnection();	
		thread.setName("Thread QMS connection");
		thread.start();
	}	
	
	public static void StartCommandToQms() {
		Thread thread = new MainSendComand();	
		thread.setName("Thread Send Command");
		thread.start();
	}

}
