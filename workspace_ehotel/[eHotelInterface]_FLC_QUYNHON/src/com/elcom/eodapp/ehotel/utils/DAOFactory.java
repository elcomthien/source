package com.elcom.eodapp.ehotel.utils;

import org.apache.log4j.PropertyConfigurator;

import com.elcom.eodapp.ehotel.service.CoreDao;
import com.elcom.eodapp.ehotel.smile.CoreSmileDao;
import com.elcom.eodapp.ehotel.sokha.CoreSoKhaDao;

public class DAOFactory {
	private static DAOFactory instance = null;
	private DAOFactory()
	{
		PropertyConfigurator.configure("Config/log4j.properties");
	}
	//-------------------------------------------------------
	public static synchronized DAOFactory getInstance()
	{
		if (instance == null)
			instance = new DAOFactory();

		return instance;
	}
	//-------------------------------------------------------
	public CoreDao getCoreDao()
	{
		return new CoreDao();
	}
	//-------------------------------------------------------
	public CoreSoKhaDao getCoreSoKhaDao()
	{
		return new CoreSoKhaDao();
	}	 
	//-------------------------------------------------------
	public CoreSmileDao getCoreSmileDao()
	{
		return new CoreSmileDao();
	}
	
}
