package com.elcom.eodapp.ehotel.processor;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.service.sokha.CoreSoKhaInterface;
import com.elcom.eodapp.ehotel.sokha.CoreSoKhaInterfacePortType;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_ACK;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_OUT_PARAM;
import com.elcom.eodapp.ehotel.utils.DataHelper;
import com.sun.xml.rpc.tools.wscompile.Main;

public class SOAPParser_SoKha {
	
	Logger logger = Logger.getLogger(SOAPParser_SoKha.class);	
	JSONParser_SoKha parser = DAOFactory.getInstance().getJsonParser_SK();
	CoreSoKhaInterface core = DAOFactory.getInstance().getCoreSoKhaInterface();
	//CoreSoKhaInterfacePortType core = MainObject.eHotelWebService;
	
	public Integer ParseSoapData_GuestMessage(String soapObjectString) {
		logger.info(soapObjectString);
		int rs = XPOS_ACK.FAIL.value;
		try {
			    MessageFactory factory = MessageFactory.newInstance();
			    SOAPMessage msg = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(soapObjectString.getBytes(Charset.forName("UTF-8"))));
			    msg.saveChanges();
			    SOAPBody soapBody = msg.getSOAPBody();
			    List<Element> listElm =elements(soapBody.getElementsByTagName("responseCode"));			    
			    String code = listElm.get(0).getTextContent();
			    listElm = elements(soapBody.getElementsByTagName("responseMessage"));	
			    String message = listElm.get(0).getTextContent();
			    listElm = elements(soapBody.getElementsByTagName("responseData"));
			    String messData = listElm.get(0).getTextContent();
		    	if(Integer.parseInt(code) == XPOS_ACK.SUCC.value)
		    	{
		    		ArrayList<XposMessageObj> arrayMess = parser.ParseGuestMessage(messData);
		    		for(XposMessageObj data : arrayMess)  
		    		{
		    			String GUEST_ROOM = data.getGUEST_ROOM();
		    			String GUEST_RESERVATION = data.getGUEST_RESERVATION();
		    			String MESSAGE_ID = data.getMESSAGE_ID();
		    			String MESSAGE_TEXT = data.getMESSAGE_TEXT();		    			
		    			String LAST_UPDATE = data.getLAST_UPDATE();			    	

		    			boolean result = core.GuestMessageTextOnline(GUEST_ROOM, GUEST_RESERVATION, MESSAGE_ID, MESSAGE_TEXT, LAST_UPDATE);
		    			logger.info("process soap guestMessage id:" + MESSAGE_ID + " " + result);
		    		}
		    	}
		    	logger.info("guestMessage" + message);
			    rs = Integer.parseInt(code);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return rs;
	}
	
	public Integer ParseSOAPData_Bill(String roomNUMER, String reservationNUMER, String soapObjectString) {
		logger.info(soapObjectString);
		int rs = XPOS_ACK.FAIL.value;
		try {
			    MessageFactory factory = MessageFactory.newInstance();
			    SOAPMessage msg = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(soapObjectString.getBytes(Charset.forName("UTF-8"))));
			    msg.saveChanges();
			    SOAPBody soapBody = msg.getSOAPBody();
			    List<Element> listElm =elements(soapBody.getElementsByTagName("responseCode"));			    
			    String code = listElm.get(0).getTextContent();
			    listElm = elements(soapBody.getElementsByTagName("responseMessage"));	
			    String message = listElm.get(0).getTextContent();
			    listElm = elements(soapBody.getElementsByTagName("responseData"));
			    String messData = listElm.get(0).getTextContent();			    
			    if(Integer.parseInt(code) == XPOS_ACK.SUCC.value)
			    {
			    	core.DeleteBillItem(roomNUMER, reservationNUMER);
			    	
			    	ArrayList<XposBillObj> arrayBill = parser.ParseGuestBill(messData);			    	
			    	for(XposBillObj data : arrayBill) {
			    		String GUEST_ROOM = data.getGUEST_ROOM();
			    		String GUEST_RESERVATION = data.getGUEST_RESERVATION();
			    		String ITEM = data.getITEM();
			    		String AMOUNT = data.getAMOUNT();
			    		String DEP_CODE = data.getDEP_CODE();			    	
			    		String FOLIO_NUMBER = data.getFOLIO_NUMBER();
			    		String ITEM_DATE = data.getITEM_DATE();
			    		String ITEM_TIME = data.getITEM_TIME();
			    		String ITEM_FLAG = data.getITEM_FLAG();
			    		
			    		String strDateTime = ITEM_DATE + " " + ITEM_TIME;
			    		Date rsDate = DataHelper.parseDate(strDateTime, "yyyy-MM-dd HH:mm:ss");
			    		if(rsDate == null) {
			    			rsDate = new Date();
			    			ITEM_DATE = DataHelper.format(rsDate, "yyyy-MM-dd");
			    			ITEM_TIME = DataHelper.format(rsDate, "HH:mm:ss");
			    		}
			    		/*if(ITEM_FLAG == "Y") ITEM_FLAG = "1";
			    		else ITEM_FLAG = "0";
			    		*/
			    		boolean result = core.GuestBillItem(GUEST_ROOM, GUEST_RESERVATION, ITEM, AMOUNT, ITEM_FLAG, ITEM_DATE, ITEM_TIME);
			    		logger.info("process guestbill (GUEST_ROOM:" + GUEST_ROOM +",GUEST_RESERVATION:" + GUEST_RESERVATION + ",ITEM:" + ITEM + ") " + result);
			    	}
			    }	
			    logger.info("process soap bill:" + message);
			    rs = Integer.parseInt(code);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return rs;
	}
	
	public Integer ParseSOAPData_Post(String soapObjectString) {
		logger.info(soapObjectString);
		int rs = XPOS_ACK.FAIL.value;
		try {
			    MessageFactory factory = MessageFactory.newInstance();
			    SOAPMessage msg = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(soapObjectString.getBytes(Charset.forName("UTF-8"))));
			    msg.saveChanges();
			    SOAPBody soapBody = msg.getSOAPBody();
			    List<Element> listElm =elements(soapBody.getElementsByTagName("responseCode"));			    
			    String code = listElm.get(0).getTextContent();
			    listElm = elements(soapBody.getElementsByTagName("responseMessage"));	
			    String message = listElm.get(0).getTextContent();
			    listElm = elements(soapBody.getElementsByTagName("responseData"));
			    String messData = listElm.get(0).getTextContent();			    
			    if(Integer.parseInt(code) == XPOS_ACK.SUCC.value)
			    {	
			    	ArrayList<XposBillObj> arrayBill = parser.ParseGuestBill(messData);
			    }
			    logger.info("process soap post:" + message);
			    rs = Integer.parseInt(code);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return rs;
	}
	
	public Integer ParseSOAPData_DBSwap(String soapObjectString) {
		logger.info(soapObjectString);
		int rs = XPOS_ACK.FAIL.value;
		try {
			    MessageFactory factory = MessageFactory.newInstance();
			    SOAPMessage msg = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(soapObjectString.getBytes(Charset.forName("UTF-8"))));
			    msg.saveChanges();
			    SOAPBody soapBody = msg.getSOAPBody();
			    List<Element> listElm =elements(soapBody.getElementsByTagName("responseCode"));			    
			    String code = listElm.get(0).getTextContent();
			    listElm = elements(soapBody.getElementsByTagName("responseMessage"));	
			    String message = listElm.get(0).getTextContent();
			    listElm = elements(soapBody.getElementsByTagName("responseData"));
			    
			    logger.info("process soap post:" + message);
			    rs = Integer.parseInt(code);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return rs;
	}
	
/**========================SOAP parser utilities=======================**/
	private  List<Element> elements(NodeList nodes) {
		List<Element> result = new ArrayList<Element>(nodes.getLength());
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node instanceof Element)
				result.add((Element)node);
		}
		return result;
	}

	private Element named(Element elem, String name) {
		if (!elem.getNodeName().equals(name))
			throw new IllegalArgumentException("Expected " + name + ", got " + elem.getNodeName());
		return elem;
	}
	
	/*public static void main(String[] args) {
		String test = "Y";
		boolean check = test == "Y";
		return;
		String xml = "<SOAP-ENV:Envelope SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"><SOAP-ENV:Body><ns1:billResponse xmlns:ns1=\"http://schemas.xmlsoap.org/soap/envelope/\"><responseCode xsi:type=\"xsd:string\">1</responseCode><responseMessage xsi:type=\"xsd:string\">operation success</responseMessage><responseData xsi:type=\"xsd:string\">{\"0\" : {" +
										"\"roomno\" : \"2000\"," +
										"\"guestno\" : \"123456\"," +
										"\"item\" : \"Breakfast Charge\"," +
										"\"amount\" : \"0.31\"," +
										"\"dep_code\" : \"\"," +
										"\"folio_number\" : \"1\"," +
										"\"item_date\" : \"0000-00-00\"," +
										"\"item_time\" : \"16:02:20\"," +
										"\"item_flag\" : \"Y\"" +
									"}, \"1\" : {" +
										"\"roomno\" : \"2000\"," +
										"\"guestno\" : \"123456\"," +
										"\"item\" : \"Room Charge (ROM)\"," +
										"\"amount\" : \"1.00\"," +
										"\"dep_code\" : \"\"," +
										"\"folio_number\" : \"1\"," +
										"\"item_date\" : \"0000-00-00\"," +
										"\"item_time\" : \"16:02:20\"," +
										"\"item_flag\" : \"Y\"" +
									"}, \"2\" : {" +
										"\"roomno\" : \"2000\"," +
										"\"guestno\" : \"123456\"," +
										"\"item\" : \"Payment Payment Payment Folio#146350 (1)\"," +
										"\"amount\" : \"0.80\"," +
										"\"dep_code\" : \"\"," +
										"\"folio_number\" : \"1\"," +
										"\"item_date\" : \"0000-00-00\"," +
										"\"item_time\" : \"16:05:57\"," +
										"\"item_flag\" : \"Y\"" +
									"}, \"3\" : {" +
										"\"roomno\" : \"2000\"," +
										"\"guestno\" : \"123456\"," +
										"\"item\" : \"Breakfast Charge\"," +
										"\"amount\" : \"0.31\"," +
										"\"dep_code\" : \"\"," +
										"\"folio_number\" : \"1\"," +
										"\"item_date\" : \"0000-00-00\"," +
										"\"item_time\" : \"16:05:57\"," +
										"\"item_flag\" : \"Y\"" +
									"}, \"4\" : {" +
										"\"roomno\" : \"2000\"," +
										"\"guestno\" : \"123456\"," +
										"\"item\" : \"Room Charge (ROM)\"," +
										"\"amount\" : \"1.00\"," +
										"\"dep_code\" : \"\"," +
										"\"folio_number\" : \"1\"," +
										"\"item_date\" : \"0000-00-00\"," +
										"\"item_time\" : \"16:05:57\"," +
										"\"item_flag\" : \"Y\"" +
									"}}</responseData></ns1:billResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
		SOAPParser_SoKha p = new SOAPParser_SoKha();
		p.ParseSOAPData_Bill(xml);
		
	}*/
}
