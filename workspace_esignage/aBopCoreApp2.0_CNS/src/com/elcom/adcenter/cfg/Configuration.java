package com.elcom.adcenter.cfg;

public class Configuration
{
  public String host = "localhost";
  public String port = "3306";
  public String database = "dbAdcenter";
  public String user = "adcenter";
  public String pass = "adcenter"; 
  public int conn_tries_numbers = 2;
  public int pool_size_max = 2;

  public Configuration()
  {
	  
  }
}


