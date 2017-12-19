package com.elcom.eodapp.ehotel.service.xpo;



import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.encoding.XMLType;
import org.apache.log4j.Logger;
import com.elcom.eodapp.ehotel.processor.JSONParser_SoKha;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.ws.XpossoapPortType;


public class XposProcessor {
	
	Logger logger = Logger.getLogger(XposProcessor.class) ;	
	JSONParser_SoKha parser = DAOFactory.getInstance().getJsonParser_SK();
	
	private static final String WS_URL = "http://203.155.100.231/ifc/WebServiceServer.php?wsdl";
	XpossoapPortType xpoServer;
	javax.xml.rpc.Service service;
	public XposProcessor() 
	{		
		try {	    
			URL url	 = new URL(WS_URL);
			QName qname = new QName ("http://203.155.100.231/soap/xpossoap", "xpossoap");
			ServiceFactory factory = ServiceFactory.newInstance();
            service = factory.createService(url, qname);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Integer guestMessage(String GUEST_ROOM_IN, String GUEST_RESERVATION_IN) {
		QName portName = new QName("http://203.155.100.231/ifc/WebServiceServer.php", "xpossoapPortType");
		QName operationName = new QName("http://203.155.100.231/ifc/WebServiceServer.php", "guestMessage");
		Call call;
		try {
			call = service.createCall();
			
			// set port and operation name  
			call.setPortTypeName(portName);
	        call.setOperationName(operationName);
	        
	        // add parameters
	        call.addParameter("sRoomNo",XMLType.XSD_STRING, String.class, ParameterMode.IN);
	        call.addParameter("sGuestNo",XMLType.XSD_STRING, String.class,ParameterMode.IN);            
	        call.addParameter("responseCode",XMLType.XSD_STRING, String.class,ParameterMode.OUT);
	        call.addParameter("responseMessage",XMLType.XSD_STRING, String.class,ParameterMode.OUT);
	        call.addParameter("responseData",XMLType.XSD_STRING, String.class,ParameterMode.OUT);
	        
	        call.setProperty(Call.OPERATION_STYLE_PROPERTY,"rpc");
	        call.setProperty(Call.ENCODINGSTYLE_URI_PROPERTY,"http://schemas.xmlsoap.org/soap/encoding/");
	        
	        //call.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "String"));	        
	        call.setReturnType(XMLType.SOAP_STRING, String.class);
	        // set end point address
	        call.setTargetEndpointAddress("http://203.155.100.231/ifc/WebServiceServer.php");
	        
	        Object[] actualArgs = new Object[]{GUEST_ROOM_IN, GUEST_RESERVATION_IN};
	        try {	        	
				call.invoke(actualArgs);
				Map out = call.getOutputParams();
				Object[] key = out.keySet().toArray();
				for(Object o : key) {
					System.out.println((String)out.get(o));					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
	        
	        /*System.out.println(result == null);*/
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return -1;
		
	}


	
	public Integer guestBill(String GUEST_ROOM, String GUEST_RESERVATION) {
		// TODO Auto-generated method stub
		return null;
	}	
}
	
