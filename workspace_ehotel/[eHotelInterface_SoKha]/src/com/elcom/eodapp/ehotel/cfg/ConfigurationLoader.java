package com.elcom.eodapp.ehotel.cfg;

import java.io.*;
import java.sql.*;
import java.util.*;
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
      configuration.processInterval = Integer.parseInt(prop.getProperty("ehotel.sokha.interval(s)","5"));
      configuration.conn_tries_numbers = Integer.parseInt(prop.getProperty("ehoteldbi.conn_tries_numbers", "10"));
      configuration.certificate_vas = Integer.parseInt(prop.getProperty("ehoteldbi.certificate_vas", "1"));
      configuration.ipserverpmsdb = prop.getProperty("ehoteldbi.ipserverpmsdb","25.100.148.203");
      configuration.databasenamepmsdb = prop.getProperty("ehoteldbi.databasenamepmsdb","IPTVDATA");
      configuration.usepmsdb = prop.getProperty("ehoteldbi.usepmsdb","pms");
      configuration.passpmsdb = prop.getProperty("ehoteldbi.passpmsdb","pms");
      configuration.serverpms = prop.getProperty("ehoteldbi.serverpms","localhost");
      configuration.portpms = Integer.parseInt(prop.getProperty("ehoteldbi.portpms","9999"));
      configuration.server = prop.getProperty("ehoteldbi.server","localhost");
      configuration.portEHotel = Integer.parseInt(prop.getProperty("ehotel.server.port","9999"));
      configuration.pathsaveimage = prop.getProperty("ehoteldbi.pathsaveimage","localhost");
      configuration.remoteviewport = Integer.parseInt(prop.getProperty("ehoteldbi.remoteviewport","9999"));
      configuration.eHotel_wsdl = prop.getProperty("ehotelws.wsdl","http://172.16.9.159:8383/eHotel/services/CoreSoKhaInterface?wsdl");
      configuration.publish_url = prop.getProperty("publish.url","localhost");
      configuration.publish_username = prop.getProperty("publish.username","");     
      configuration.publish_password = prop.getProperty("publish.password","");
      configuration.xpos_ipaddress = prop.getProperty("xpos.ipaddress","203.155.100.231");
      configuration.xpos_port = Integer.parseInt(prop.getProperty("xpos.port","8080"));
      configuration.xpos_path = prop.getProperty("xpos.path","");
      configuration.xpos_bill_process = Integer.parseInt(prop.getProperty("xpos.bill.process(s)", "60"));
      configuration.xpos_bill_recv_delay = Integer.parseInt(prop.getProperty("xpos.bill.recev.delay(s)", "60"));
      
      inStream.close();
    } catch (Throwable t){
      // We have to catch Throwable, otherwise we will miss
      // NoClassDefFoundError and other subclasses of Error
      log.error("Building ConfigurationLoader failed.", t);
      throw new RuntimeException(t);
    }
  }
}
