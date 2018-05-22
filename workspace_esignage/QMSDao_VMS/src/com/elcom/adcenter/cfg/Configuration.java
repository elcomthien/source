package com.elcom.adcenter.cfg;

public class Configuration
{
  
  public String connectString = "jdbc:mysql://localhost";
  public String database = "dbAdcenter";
  public String user = "adcenter";
  public String pass = "adcenter"; 
  public int conn_tries_numbers = 2;
  public int pool_size_max = 2;

  public Configuration()
  {
	  
  }
}


