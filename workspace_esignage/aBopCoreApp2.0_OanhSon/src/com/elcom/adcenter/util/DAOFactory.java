package com.elcom.adcenter.util;

import com.elcom.adcenter.store.ProgDao;



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
	  
}
