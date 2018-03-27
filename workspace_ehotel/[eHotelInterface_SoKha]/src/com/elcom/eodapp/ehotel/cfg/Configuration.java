package com.elcom.eodapp.ehotel.cfg;


public class Configuration
{
  //A interval period for the system do synchronizing with the DB
  public int processInterval = 5; // in minutes
  public int pool_size_max = 5;
  public String smservicename = "SMManager";
  public int certificate_vas = 1;
  public String dbihostname = "192.168.51.1";
  public int dbiport = 10001;
  public String dbiservicename = "DBIInterface";
  public int conn_tries_numbers = 5;
  public String ipserverpmsdb = "";
  public String databasenamepmsdb = "";
  public String usepmsdb = "";
  public String passpmsdb = "";
  public String serverpms = "";
  public int portpms = 1234;
  public String server = "localhost";
  public int portEHotel = 9999;
  public String pathsaveimage = "";
  public int remoteviewport = -1;
  public String eHotel_wsdl = "";
  
  public String publish_url = "";
  public String publish_username = "";
  public String publish_password = "";
  
  public String pms_url = "";
  public String pms_username = "";
  public String pms_password = "";
  
  public String xpos_ipaddress = "";
  public int xpos_port = -1;
  public String xpos_path = "";
  public int xpos_bill_process = -1;
  public int xpos_bill_recv_delay = -1;
  
  public Configuration()
  {
	  
  }
}

