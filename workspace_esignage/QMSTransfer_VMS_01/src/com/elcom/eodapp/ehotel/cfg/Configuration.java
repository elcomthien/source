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
  public int portPMS = 1234;
  public String pmsHostSocket = "";
  public String server = "localhost";
  public int portEHotel = 9999;
  public String pathsaveimage = "";
  public int remoteviewport = -1;
  public String eHotel_wsdl = ""; 
  public int conenct_oracle = 0;
  
  
  public Configuration()
  {
	  
  }
}

