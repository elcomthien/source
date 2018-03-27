package com.elcom.eodapp.ehotel.service.json;

import java.util.ArrayList;
import java.util.Hashtable;





import org.apache.log4j.Logger;

import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.processor.JSONParser_SoKha;
import com.elcom.eodapp.ehotel.service.xpo.XposXmlProcessor;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.SOAP_PARAM;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_ACK;

public class MessageProcessor extends Thread {
	
	Logger logger = Logger.getLogger(MessageProcessor.class);
	XposXmlProcessor xpo = DAOFactory.getInstance().getXposXmlProcessor();	
	JSONParser_SoKha parser = DAOFactory.getInstance().getJsonParser_SK();
	
	
	@Override
	public void run() {
		try {
			ArrayList<String> copyData = new ArrayList<String>();
			while(true) {					
				if(MainObject.ListMessReq.size() > 0) {					
					//dataProcessing = true;
					synchronized (MainObject.ListMessReq) {
						copyData.addAll(MainObject.ListMessReq);
						MainObject.ListMessReq.clear();
					}
					for(String message : copyData) {
						Hashtable<String,String> data =  parser.ParseWaitMessage(message);
						String GUEST_ROOM = data.get(SOAP_PARAM.GUEST_ROOM.value);
						String GUEST_RESERVATION = data.get(SOAP_PARAM.GUEST_RESERVATION.value);		
						Integer rs = xpo.guestMessage(GUEST_ROOM, GUEST_RESERVATION);						
						if(rs.equals(XPOS_ACK.SUCC.value))
							logger.info("GuestMessage (GUEST_ROOM:" + GUEST_ROOM + ",GUEST_RESERVATION:" + GUEST_RESERVATION + ") SUCC");	
						else if(rs.equals(XPOS_ACK.FAIL.value))
							logger.info("GuestMessage (GUEST_ROOM:" + GUEST_ROOM + ",GUEST_RESERVATION:" + GUEST_RESERVATION + ") FAIL");
						else if(rs.equals(XPOS_ACK.EXECUTING.value))
							logger.info("GuestMessage (GUEST_ROOM:" + GUEST_ROOM + ",GUEST_RESERVATION:" + GUEST_RESERVATION + ") EXECUTING");
					}
					copyData.clear();
				}
				Thread.sleep(MainObject.config.processInterval);
			}				
		}
		catch(Exception ex) 
		{	
			logger.error(ex);
		}
	}
}
