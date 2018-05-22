package com.elcom.adcenter.rvcadv.cfg;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.elcom.adcenter.rvcadv.cfg.*;

public class ConfigurationLoader
{
  static Logger log = Logger.getLogger(ConfigurationLoader.class);

  private static ConfigurationLoader instance = null;
  private static Configuration configuration = null;

  private ConfigurationLoader()
  {
    configuration = new Configuration();

    build();
  }

  public static synchronized ConfigurationLoader getInstance()
  {
    if (instance == null)
    {
      instance = new ConfigurationLoader();
    }

    return instance;
  }

  public Configuration getConfiguration()
  {
    return configuration;
  }

  private void build()
  {
    try{
      String currentDir = System.getProperty("user.dir");
      System.out.println("Current folder "+ currentDir);	
      Properties prop = new Properties();
      FileInputStream inStream = new FileInputStream("Config/adcenter.properties");
      prop.load(inStream);

      configuration.connectString = prop.getProperty("adcenterservice.connectstring","SessionManager");
      configuration.database = prop.getProperty("adcenterservice.database");
      configuration.user = prop.getProperty("adcenterservice.user","adcenter");
      configuration.pass = prop.getProperty("adcenterservice.pass", "adcenter");
      configuration.pathfile = prop.getProperty("adcenterservice.pathfile", "c:\\Java\\apache-tomcat-6.0.35\\bin\\Config\\file\\");  
      configuration.server_syn = prop.getProperty("adcenterservice.server_syn","localhost");
      configuration.server_port = prop.getProperty("adcenterservice.server_port","1234");
      configuration.server_src_file_syn = prop.getProperty("adcenterservice.server_src_file_syn","1234");
      configuration.server_ftp_gateway = prop.getProperty("adcenterservice.server_ftp_gateway","localhost");
      configuration.server_ftp_gateway_port = Integer.parseInt(prop.getProperty("adcenterservice.server_ftp_gateway_port","0000"));
      configuration.server_ftp_gateway_service = prop.getProperty("adcenterservice.server_ftp_gateway_service","elc_ftpgateway");
      
      inStream.close();
    } catch (Throwable t){
      // We have to catch Throwable, otherwise we will miss
      // NoClassDefFoundError and other subclasses of Error
      log.error("Building ConfigurationLoader failed.", t);
      throw new RuntimeException(t);
    }
  }
}

