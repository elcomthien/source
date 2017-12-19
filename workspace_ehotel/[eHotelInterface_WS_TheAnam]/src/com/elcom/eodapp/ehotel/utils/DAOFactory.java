package com.elcom.eodapp.ehotel.utils;

import com.elcom.eodapp.ehotel.processor.BillProcessor;
import com.elcom.eodapp.ehotel.processor.JSONParser_SoKha;
import com.elcom.eodapp.ehotel.processor.PostXML;
import com.elcom.eodapp.ehotel.processor.SOAPParser_SoKha;
import com.elcom.eodapp.ehotel.processor.XposConverter;
import com.elcom.eodapp.ehotel.service.xpo.XposProcessor;
import com.elcom.eodapp.ehotel.service.xpo.XposXmlProcessor;
import com.elcom.eodapp.ehotel.service.sokha.CoreSoKhaDao;
import com.elcom.eodapp.ehotel.service.sokha.CoreSoKhaInterface;

public class DAOFactory {
	private static DAOFactory instance = null;
	 private DAOFactory()
	 {}
//-------------------------------------------------------
	 public static synchronized DAOFactory getInstance()
	 {
	   if (instance == null)
	     instance = new DAOFactory();

	   return instance;
	 }
	 
//-------------------------------------------------------
	 public JSONParser_SoKha getJsonParser_SK()
	 {
		 return new JSONParser_SoKha();
	 }
	 
	 public SOAPParser_SoKha getSoapParser_SK()
	 {
		 return new SOAPParser_SoKha();
	 }
	 
	 //-------------------------------------------------------
	 public XposProcessor getXposProcessor()
	 {
		 return new XposProcessor();
	 }
	 
	 public XposXmlProcessor getXposXmlProcessor()
	 {
		 return new XposXmlProcessor();
	 }	
	 
	 public PostXML getPostXML()
	 {
		 return new PostXML();
	 }	
	 
	 public CoreSoKhaDao getCoreSoKhaDao()
	  {
	    return new CoreSoKhaDao();
	 }	
	 
	 public CoreSoKhaInterface getCoreSoKhaInterface()
	  {
	    return new CoreSoKhaInterface();
	 }	
	 
	 public BillProcessor getBillProcessor()
	  {
	    return new BillProcessor();
	 }
	 
	 public XposConverter getXposConverter() {
		 return new XposConverter();
	 }
	 
}
