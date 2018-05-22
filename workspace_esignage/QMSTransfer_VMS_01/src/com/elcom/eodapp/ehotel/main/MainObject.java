package com.elcom.eodapp.ehotel.main;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import com.elcom.Log.FileLog;
import com.elcom.eodapp.ehotel.cfg.Configuration;



public class MainObject {
	public static Configuration config;
	public static ArrayList<String> ListBillReq = new ArrayList<String>();
	public static ArrayList<String> ListMessReq = new ArrayList<String>();
	
	public static Socket pmsSocket = null;
	public static DataOutputStream sendToClient = null;
	public static FileLog logger_ = new FileLog();
	
	
}
