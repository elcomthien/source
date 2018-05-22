package com.elcom.adcenter.rvcav.content;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.adcenter.rvcadv.broker.IMBroker;
import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;
import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.common.VoContent;
import com.elcom.adcenter.rvcadv.common.VoStbSession;
import com.elcom.adcenter.rvcadv.layout.LayoutDao;
import com.elcom.adcenter.rvcadv.sql.SqlCore;
import com.elcom.adcenter.rvcadv.util.DataSocketSendFile;
import com.elcom.adcenter.rvcadv.util.DateHelper;

public class ServiceContentDao {
	  private static DataSocketSendFile data;
	  private String server;
	  private int server_port;
	  //Refers the DB broker object
	  private static IMBroker broker = IMBroker.getInstance();
	  private static Configuration config = null;
	  //To log application	 
	  private static Logger log = Logger.getLogger(ServiceContentDao.class);
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
	//---------------------------------------------------------------  
	/*public ServiceContentDao()
	{
		server = config.server_syn;
		server_port = new Integer(config.server_port);
		System.out.println(server + ":" + server_port);
		//data = new DataSocketSendFile(server, server_port);
	}*/
	//---------------------------------------------------------------
	public void sendOneFile(String sesionid,String src_file,String des_file)
	{
		try{	
			server = config.server_syn;
			server_port = new Integer(config.server_port).intValue();
			data = new DataSocketSendFile(server, server_port);
			data.sendCommand(sesionid, src_file, des_file);
			//Sau khi goi lenh chuyen file xong thi insert vao DB de cho DB quan ly.
			
		}
		catch(Exception ex){ex.printStackTrace();}
	}
	//---------------------------------------------------------------
	public void removeOneFile(String sesionid,String src_file)
	{
		try{	
			server = config.server_syn;
			server_port = new Integer(config.server_port).intValue();
			data = new DataSocketSendFile(server, server_port);
			data.sendCommandRemove(sesionid, src_file);			
			
		}
		catch(Exception ex){ex.printStackTrace();}
	}
	//---------------------------------------------------------------
	public void sendMultiFile(Vector<VoStbSession> liststb)
	{
		VoStbSession item;
		Vector<String> vnamefile;
		Vector<Integer> vidfile;
		//String namefile;
		int id;
		for (int i = 0 ; i < liststb.size() ; i++)
		{
			item = liststb.get(i);
			vnamefile = item.getSrcfiles();	
			vidfile = item.getIdsrcfile();
			if (!item.getSesionid().equals('0')){
				for (int ii = 0 ; ii < vnamefile.size() ; ii++)
				{
					//namefile = config.server_src_file_syn + vnamefile.get(ii);	
					id = vidfile.get(ii);
					System.out.println("id: " + id + " - item.getSesionid(): " + item.getSesionid());
					//sendOneFile(item.getSesionid(),namefile,item.getDesc_file());  //tam thoi vo hieu hoa thay bang truyen file bang hhtt
					SqlCore.setContentIntoStb(item.getSesionid(),id + "");
				}
			}
		}
	}	
	//--------------------------------------------------------------------
	public int removeContentStb(Vector<VoStbSession> liststb)
	{
		VoStbSession item;
		Vector<String> vnamefile;
		Vector<Integer> vidfile;
		String namefile;
		int id;
		for (int i = 0 ; i < liststb.size() ; i++)
		{
			item = liststb.get(i);
			vnamefile = item.getSrcfiles();	
			vidfile = item.getIdsrcfile();
			if (!item.getSesionid().equals('0')){
				for (int ii = 0 ; ii < vnamefile.size() ; ii++)
				{
					namefile = config.server_src_file_syn + vnamefile.get(ii);	
					id = vidfile.get(ii);
					//removeOneFile(item.getSesionid(),namefile);
					SqlCore.adminDelContentStb(item.getSesionid(),id + "");
				}
				SqlCore.adminDelConntentStbWar(item.getSesionid());
			}
		}
		return 1;
	}
	//--------------------------------------------------------------------
	public int removeContentStbEach(Vector<VoStbSession> liststb, String mac)
	{
		VoStbSession item;
		Vector<String> vnamefile;
		Vector<Integer> vidfile;
		String vmacfile;
		int id;
		for (int i = 0 ; i < liststb.size() ; i++)
		{
			item = liststb.get(i);
			vnamefile = item.getSrcfiles();	
			vidfile = item.getIdsrcfile();
			vmacfile = item.getMac();
			//giong mac number moi xoa du lieu 09052013
			if (vmacfile.equals(mac)) {
				if (!item.getSesionid().equals('0')){
					for (int ii = 0 ; ii < vnamefile.size() ; ii++)
					{
						id = vidfile.get(ii);
						SqlCore.adminDelContentStb(item.getSesionid(),id + "");
					}
					SqlCore.adminDelConntentStbWar(item.getSesionid());
				}
			}
			else {
				//ko lam gi ca
			}
		}
		return 1;
	}	
	//-------------------------------------------------------
	public static void main(String[] arg)
	{
		String xml = "<parameter>\r\n" 
+ "<nameContent>SONLAMTEXT 2</nameContent>\r\n"
+ "<urlContent>SONLAMTEXT 2</urlContent>\r\n"
+ "<colorText>yellow</colorText>\r\n"
+ "<fontText>georgia</fontText>\r\n"
+ "<sizeText>6</sizeText>\r\n"
+ "<direcRoll>1</direcRoll>\r\n"
+ "</parameter>";
		ServiceContentDao dao = new ServiceContentDao();
		
	}
	
}
