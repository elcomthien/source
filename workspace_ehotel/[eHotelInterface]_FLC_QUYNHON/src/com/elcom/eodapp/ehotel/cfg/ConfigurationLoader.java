package com.elcom.eodapp.ehotel.cfg;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

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
      FileInputStream inStream = new FileInputStream("Config/config.properties");
      prop.load(inStream);

      configuration.smservicename = prop.getProperty("ehoteldbi.smservicename","SessionManager");
      configuration.dbihostname = prop.getProperty("ehoteldbi.dbihostname","localhost");
      configuration.dbiport = Integer.parseInt(prop.getProperty("ehoteldbi.dbiport"));
      configuration.dbiservicename = prop.getProperty("ehoteldbi.dbiservicename", "DBInterface");
      configuration.pool_size_max = Integer.parseInt(prop.getProperty("ehoteldbi.pool_size_max","10"));
      configuration.syncInterval = Integer.parseInt(prop.getProperty("ehoteldbi.syncinterval","5"));
      configuration.conn_tries_numbers = Integer.parseInt(prop.getProperty("ehoteldbi.conn_tries_numbers", "10"));
      configuration.certificate_vas = Integer.parseInt(prop.getProperty("ehoteldbi.certificate_vas", "1"));
      configuration.ipserverpmsdb = prop.getProperty("ehoteldbi.ipserverpmsdb","25.100.148.203");
      configuration.databasenamepmsdb = prop.getProperty("ehoteldbi.databasenamepmsdb","IPTVDATA");
      configuration.usepmsdb = prop.getProperty("ehoteldbi.usepmsdb","pms");
      configuration.passpmsdb = prop.getProperty("ehoteldbi.passpmsdb","pms");
      configuration.serverpms = prop.getProperty("ehoteldbi.serverpms","localhost");
      configuration.portpms = Integer.parseInt(prop.getProperty("ehoteldbi.portpms","9999"));
      configuration.server = prop.getProperty("ehoteldbi.server","localhost");
      configuration.port = Integer.parseInt(prop.getProperty("ehoteldbi.port","9999"));
      configuration.pathsaveimage = prop.getProperty("ehoteldbi.pathsaveimage","localhost");
      configuration.remoteviewport = Integer.parseInt(prop.getProperty("ehoteldbi.remoteviewport","9999"));
      
      configuration.publish_url = prop.getProperty("publish.url","localhost");
      configuration.publish_username = prop.getProperty("publish.username","");     
      configuration.publish_password = prop.getProperty("publish.password","");
      
      inStream.close();
    } catch (Throwable t){
      // We have to catch Throwable, otherwise we will miss
      // NoClassDefFoundError and other subclasses of Error
      log.error("Building ConfigurationLoader failed.", t);
      throw new RuntimeException(t);
    }
  }
}
