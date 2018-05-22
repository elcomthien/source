package com.elcom.adcenter.rvcav.content;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.UUID;

import ehotel.core.*;

public class Test {
	public static String HOSTNAME = "localhost";
	public static int PORT = 2099;//10121;
	public static String ServiceName = "elc_ftpgateway";//"ROService";
	
	public static void main(String[] arg)
	{
		String desc_host = "localhost", local_file = "c:\\tmp\\Des\\abc.mp4";
		int timeout = 10;
		FTPServerStruct src_ftpserver = new FTPServerStruct("localhost",21,"adcenter","123","Src\\abc.mp4");
		try{
			FTPGatewayImpl_Stub gw =  (FTPGatewayImpl_Stub)Naming.lookup("rmi://" + HOSTNAME + ":"
				+ PORT + "/" + ServiceName);
			UUID sesionid = gw.download(src_ftpserver, desc_host, local_file, timeout);
			long size = gw.fileSize(sesionid);
			long i = 0;
			System.out.println("Size file: " + size);
			while(i < size)
			{
				System.out.println("i= " + i);
				i = gw.transfered(sesionid);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
