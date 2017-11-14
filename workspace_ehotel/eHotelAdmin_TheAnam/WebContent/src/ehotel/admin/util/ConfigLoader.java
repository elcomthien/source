package ehotel.admin.util;


import java.io.FileInputStream;
import java.util.Properties;




import org.apache.log4j.PropertyConfigurator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class ConfigLoader {
	
	private static ConfigLoader instance = null;
	
	
	static {PropertyConfigurator.configure("Config/log4j.properties");}
	
	public static ConfigLoader getInstance() {
		if (instance == null) {
			instance = new ConfigLoader();
		}
		return instance;
	}		
	public void load()
	{
		//LoadXml();
		
	}	
	public Config getConfig() 
	{
		Config config = null;
		if (config == null) config = new Config();
		try {
			Properties prop = new Properties();
			System.out.println("Load config:"+Def._config);
			prop.load(new FileInputStream(Def._config));
			config._pathImage = prop.getProperty("pathImages", "");	
			config._pathImageVod=prop.getProperty("pathImagesVod", "");	
			config._temp  = prop.getProperty("pathTemp", "");	
			config._HostSubtitle=prop.getProperty("hostSubtitle", "");
			config._passSubtitle=prop.getProperty("passSubtitle", "");
			config._PortSubtitle=Integer.parseInt(prop.getProperty("portSubtitle", ""));
			config._UserSubtitle=prop.getProperty("userSubtitle", "");
			
			config._HostFtpMovie=prop.getProperty("hostFtpMovie", "");
			config._passFtpMovie=prop.getProperty("passFtpMovie", "");
			config._PortFtpMovie=Integer.parseInt(prop.getProperty("portFtpMovie", ""));
			config._UserFtpMovie=prop.getProperty("userFtpMovie", "");
			//ftp EAS
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		return config;
	}
	public ConfigEod getConfigEod() 
	{
		ConfigEod config = null;
		if (config == null) config = new ConfigEod();
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("config/eodapp.properties"));
			config.ehotel_ftpeas_pass= prop.getProperty("ehotel.ftpeas.pass", "");	
			config.ehotel_ftpeas_storage=prop.getProperty("ehotel.ftpeas.storage", "");	
			config.ehotel_ftpeas_port	  = Integer.parseInt(prop.getProperty("ehotel.ftpeas.port", ""));
			config.ehotel_ftpeas_user=prop.getProperty("ehotel.ftpeas.user", "");	
			//ftp EAS
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		return config;
	}

}
