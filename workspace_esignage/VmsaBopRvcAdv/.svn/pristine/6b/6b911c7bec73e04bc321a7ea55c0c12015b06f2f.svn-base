package com.elcom.adcenter.rvcav.content;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.adcenter.rvcadv.broker.IMBroker;
import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;
import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.util.DataSocketSendFile;
import com.elcom.adcenter.rvcadv.util.DateHelper;

import ehotel.core.FTPGatewayImpl_Stub;
import ehotel.core.FTPServerStruct;

public class ContentCore {	  
	  private FTPGatewayImpl_Stub ftpgw;
	  //Refers the DB broker object	  
	  private static Configuration config = null;
	  //To log application	 
	  private static Logger log = Logger.getLogger(ContentCore.class);
	  //Read configuration params
	  static
	  {
		  try{			 
			 ConfigurationLoader loader = ConfigurationLoader.getInstance();
			 config = loader.getConfiguration();
			 Properties props = new Properties();
			 FileInputStream fileinputstream = new FileInputStream("Config/log4j.properties");
			 props.load(fileinputstream);
			 PropertyConfigurator.configure(props);
		  }catch (IOException ex) {
			  log.error(null, ex);
		  }
	  }
	
	//=====================================================================  
	public ContentCore()
	{
		String HOSTNAME = config.server_ftp_gateway;
		int PORT = config.server_ftp_gateway_port;
		String ServiceName = config.server_ftp_gateway_service;
		try{
			ftpgw =  (FTPGatewayImpl_Stub)Naming.lookup("rmi://" + HOSTNAME + ":"
				+ PORT + "/" + ServiceName);
		}catch(Exception ex){ex.printStackTrace();}
	}
	//=======================================================================
	public UUID download(String xmlparamter) throws RemoteException
	{
		/**
		 * <transfer>
		 * 		<serverhostftp>localhost</serverhostftp>
		 * 		<serverportftp>21</serverportftp>
		 * 		<serveruserftp>adcenter</serveruserftp>
		 * 	    <serverpassftp>adcenter</serverpassftp>
		 *      <srcfile>Src\\abc.mp4</srcfile>
		 *      <desc_host>localhost</desc_host>
		 *      <local_file>c:\\tmp\\Des\\abc.mp4</local_file>
		 *      <timeout>10<timeout>
		 * </transfer>
		 */
		UUID sessionuuid = null;
		FTPServerStruct ftpserverstruct;
		
		int timeout = new Integer(DateHelper.utilStringXml(xmlparamter,SQL.timeout));;
		String serverhostftp = DateHelper.utilStringXml(xmlparamter,SQL.serverhostftp);
		int serverportftp =  new Integer(DateHelper.utilStringXml(xmlparamter,SQL.serverportftp));
		String serveruserftp = DateHelper.utilStringXml(xmlparamter,SQL.serveruserftp);
		String serverpassftp = DateHelper.utilStringXml(xmlparamter,SQL.serverpassftp);
		String srcfile = DateHelper.utilStringXml(xmlparamter,SQL.srcfile);
		String desc_host = DateHelper.utilStringXml(xmlparamter,SQL.desc_host);
		String local_file = DateHelper.utilStringXml(xmlparamter, SQL.local_file);
		
		ftpserverstruct = new FTPServerStruct(serverhostftp,serverportftp,serveruserftp,serverpassftp,srcfile);
		
		sessionuuid = ftpgw.download(ftpserverstruct, desc_host, local_file, timeout);
		return sessionuuid;
	}
	//----------------------------------------------------
	public long transfered(UUID sesionid) throws RemoteException
	{
		return ftpgw.transfered(sesionid);
	}
}
