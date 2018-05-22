package com.elcom.eodapp.ehotel.utils;

import com.elcom.adcenter.store.ProgDao;
import com.elcom.eodapp.ehotel.core.CommandProcessor;
import com.elcom.eodapp.ehotel.core.CoreInterface;
import com.elcom.eodapp.ehotel.store.ProgDaoOracle;


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
	  
	  public ProgDao getProgDao()
	  {
	    return new ProgDao();
	  }
	  
	  public ProgDaoOracle getProgDaoOracle()
	  {
	    return new ProgDaoOracle();
	  }
	  
	  public CoreInterface getCoreInterface()
	  {
	    return new CoreInterface();
	  }
	  
	  public CommandProcessor getCommandProcessor() {
		  return new CommandProcessor();
	  }
	  
}
