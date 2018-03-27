package com.elcom.eodapp.ehotel.utils;

import com.elcom.eodapp.ehotel.core.CommandBuilder;
import com.elcom.eodapp.ehotel.core.CommandProcessor;
import com.elcom.eodapp.ehotel.core.DataProcessor;


public class DAOFactory {
	private static DAOFactory instance = null;

	  private DAOFactory()
	  {
	  }

	  public static synchronized DAOFactory getInstance()
	  {
	    if (instance == null)
	      instance = new DAOFactory();

	    return instance;
	  }
	  
	  public CommandProcessor getCommandProcessor() {
		  return new CommandProcessor();
	  }
	  
	  public CommandBuilder getCommandBuilder() {
		  return new CommandBuilder();
	  }
	  
	  public DataProcessor getDataProcessor() 
	  {
		  return new DataProcessor();
	  }
}
