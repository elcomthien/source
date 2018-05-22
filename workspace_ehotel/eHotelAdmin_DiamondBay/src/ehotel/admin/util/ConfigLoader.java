package ehotel.admin.util;


import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;


public class ConfigLoader {
	
	private static ConfigLoader instance = null;
	
	
	static {PropertyConfigurator.configure("config/log4j.properties");}
	
	public static ConfigLoader getInstance() {
		if (instance == null) {
			instance = new ConfigLoader();
		}
		return instance;
	}
	public void load()
	{
	}	
	public Config getConfig() 
	{
		Config config = null;
		if (config == null) config = new Config();
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(Def._config));
			config._pathImage = prop.getProperty("pathImages", "");	
			config._pathImageVod=prop.getProperty("pathImagesVod", "");	
			config._temp  = prop.getProperty("pathTemp", "");	
			
			//server 1 vod
			config._HostFtpServer=prop.getProperty("ftp.server.host", "");
			config._PassFtpServer=prop.getProperty("ftp.server.pass", "");
			config._PortFtpServer=Integer.parseInt(prop.getProperty("ftp.server.port", ""));
			config._UserFtpServer=prop.getProperty("ftp.server.user", "");
			config._FolderMovie=prop.getProperty("ftp.folder.movie","");
			config._FolderMusic=prop.getProperty("ftp.folder.music", "");
			config._FilePathserver = prop.getProperty("ftp.server.filepath","");
			

			
			config._ftpserver=prop.getProperty("ftp.server.pms.host", "");
			config._ftpserver_port=util.parseInt(prop.getProperty("ftp.server.pms.port", "21"));
			config._ftpserver_user=prop.getProperty("ftp.server.pms.user", "");
			config._ftpserver_pass=prop.getProperty("ftp.server.pms.pass", "");
			
			//src vod
//			config._HostFtpSrc=prop.getProperty("ftp.src.host", "");
//			config._PassFtpSrc=prop.getProperty("ftp.src.pass", "");
//			config._PortFtpSrc=Integer.parseInt(prop.getProperty("ftp.src.port", ""));
//			config._UserFtpSrc=prop.getProperty("ftp.src.user", "");
//			config._FilePathserver = prop.getProperty("ftp.server.filepath","");
			
			// server 2 vod
//			config._HostSubtitle=prop.getProperty("hostSubtitle", "");
//			config._passSubtitle=prop.getProperty("passSubtitle", "");
//			config._PortSubtitle=Integer.parseInt(prop.getProperty("portSubtitle", ""));
//			config._UserSubtitle=prop.getProperty("userSubtitle", "");
			
//			config._HostFtpMovie=prop.getProperty("hostFtpMovie", "");
//			config._passFtpMovie=prop.getProperty("passFtpMovie", "");
//			config._PortFtpMovie=Integer.parseInt(prop.getProperty("portFtpMovie", ""));
//			config._UserFtpMovie=prop.getProperty("userFtpMovie", "");
			
			config._pathliveTV	=prop.getProperty("image.dir.liveTV", "");
			config._pathMovies	=config._pathImage+prop.getProperty("image.dir.movie", "");
			config._pathMOD		=config._pathImage+prop.getProperty("image.dir.movie", "");
			
			//bo sung link cho url cua image 17.1
			config._linksaveimage  	= prop.getProperty("linksaveimage", "");
			
			config._movie			= prop.getProperty("image.dir.movie", "");
			config._music			= prop.getProperty("image.dir.music", "");
			
			//bo sung config cho hotel 21.1
			config._hotel = prop.getProperty("image.dir.hotelinfo", "");
			config._dining = prop.getProperty("image.dir.dining", "");
			config._transportation = prop.getProperty("image.dir.transportion", "");
			config._activities = prop.getProperty("image.dir.activities", "");
			config._housekeeping = prop.getProperty("image.dir.housekp", "");
			config._promotion = prop.getProperty("image.dir.promotion", "");
			config._exchange = prop.getProperty("image.dir.exchange", "");
			config._advertise = prop.getProperty("image.dir.welcome", "");
			
			//bo sung config cho hotel pms attraction 19.2
			config._attraction = prop.getProperty("image.dir.localhotel", "");
			config._main = prop.getProperty("image.dir.main", "");
//			System.out.println("----------load thong tin tu file config.properties-------------------------------------");
			
			config._file_exchange=prop.getProperty("path.excel.exchangerate",""); 
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
			config.serverURL=prop.getProperty("serverURL");
			//ftp EAS
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		return config;
	}

}
