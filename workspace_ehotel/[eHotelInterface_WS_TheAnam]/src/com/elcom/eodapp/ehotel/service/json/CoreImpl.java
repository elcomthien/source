package com.elcom.eodapp.ehotel.service.json;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.processor.JSONParser_SoKha;
import com.elcom.eodapp.ehotel.service.sokha.CoreSoKhaInterface;
import com.elcom.eodapp.ehotel.utils.ConstantVariable;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.ACK_SOKHA;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.SOAP_PARAM;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.DataHelper;



@WebService(endpointInterface="com.elcom.eodapp.ehotel.service.json.CoreInterface")  
public class CoreImpl implements CoreInterface{
	@Resource
    WebServiceContext wsctx;
	
	Logger logger = Logger.getLogger(CoreImpl.class) ;
	JSONParser_SoKha parser = DAOFactory.getInstance().getJsonParser_SK();	
	CoreSoKhaInterface core = DAOFactory.getInstance().getCoreSoKhaInterface();
	//CoreSoKhaInterfacePortType core = MainObject.eHotelWebService;
	
	public CoreImpl() 
		  {
		    super();
		  }//constructor
	
	private boolean CheckUser() {
		return true;
	}
	
	private boolean CheckUser1() {
		MessageContext mctx = wsctx.getMessageContext();

		//get detail from request headers
		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
		List userList = (List) http_headers.get("Username");
		List passList = (List) http_headers.get("Password");

		String username = "";
		String password = "";

		if(userList!=null){
			username = userList.get(0).toString();
		}

		if(passList!=null){			
			password = passList.get(0).toString();
		}

		if (username.equals(MainObject.config.publish_username) && password.equals(MainObject.config.publish_username)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public Integer GuestCheckin(String message) {
		boolean valid = CheckUser(); 
		if(valid == false)
			return ConstantVariable.rs_INVALID;
				
		message = DataHelper.RebuildMessage(message);
		
		Hashtable<String,String> data =  parser.ParseCheckIn(message);
		String GUEST_ARV_DATE = data.get(SOAP_PARAM.GUEST_ARV_DATE.value);
		String GUEST_DEPT_DATE = data.get(SOAP_PARAM.GUEST_DEPT_DATE.value);
		String GUEST_FIRST_NAME = data.get(SOAP_PARAM.GUEST_FIRST_NAME.value);		
		String GUEST_LAST_NAME = data.get(SOAP_PARAM.GUEST_LAST_NAME.value);
		String GUEST_NAME = data.get(SOAP_PARAM.GUEST_NAME.value);
		String GUEST_RESERVATION = data.get(SOAP_PARAM.GUEST_RESERVATION.value);
		String GUEST_ROOM = data.get(SOAP_PARAM.GUEST_ROOM.value);
		String GUEST_LANGUAGE = data.get(SOAP_PARAM.GUEST_LANGUAGE.value);
		String GUEST_GROUP = data.get(SOAP_PARAM.GUEST_GROUP.value);
		String GUEST_TITLE = data.get(SOAP_PARAM.GUEST_TITLE.value);
		String GUEST_VIP_STATUS = data.get(SOAP_PARAM.GUEST_VIP_STATUS.value);
		String GUEST_TV_RIGHT = data.get(SOAP_PARAM.GUEST_TV_RIGHT.value);
		String GUEST_VIDEO_RIGHT = data.get(SOAP_PARAM.GUEST_VIDEO_RIGHT.value);
		String GUEST_BIRTHDAY = data.get(SOAP_PARAM.GUEST_BIRTHDAY.value);
		String ROOM_SHARE_FLAG = data.get(SOAP_PARAM.ROOM_SHARE_FLAG.value);
		Boolean succ = core.GuestCheckin(GUEST_ARV_DATE, GUEST_DEPT_DATE, GUEST_FIRST_NAME, GUEST_LAST_NAME, GUEST_NAME, GUEST_RESERVATION, GUEST_ROOM, GUEST_LANGUAGE, GUEST_GROUP, GUEST_TITLE, GUEST_VIP_STATUS, GUEST_TV_RIGHT, GUEST_VIDEO_RIGHT, GUEST_BIRTHDAY, ROOM_SHARE_FLAG);
		if(succ) 
			return ACK_SOKHA.SUCC.value;
		else 
			return ACK_SOKHA.FAIL.value;
	}

	@Override
	public Integer GuestCheckout(String message) {
		boolean valid = CheckUser(); 
		if(valid == false)
			return ConstantVariable.rs_INVALID;
		
		message = DataHelper.RebuildMessage(message);
		
		Hashtable<String,String> data =  parser.ParseCheckIn(message);
		String GUEST_RESERVATION = data.get(SOAP_PARAM.GUEST_RESERVATION.value);
		String GUEST_ROOM = data.get(SOAP_PARAM.GUEST_ROOM.value);
		String ROOM_SHARE_FLAG = data.get(SOAP_PARAM.ROOM_SHARE_FLAG.value);
		
		boolean succ = core.GuestCheckout(GUEST_RESERVATION, GUEST_ROOM, ROOM_SHARE_FLAG);
		if(succ) 
			return ACK_SOKHA.SUCC.value;
		else 
			return ACK_SOKHA.FAIL.value;
	}

	@Override
	public Integer GuestRoomMove(String message) {
		boolean valid = CheckUser(); 
		if(valid == false)
			return ConstantVariable.rs_INVALID;
		
		message = DataHelper.RebuildMessage(message);
		
		Hashtable<String,String> data =  parser.ParseGuestMove(message);
		String GUEST_EXROOM = data.get(SOAP_PARAM.GUEST_EXROOM.value);
		String GUEST_RESERVATION = data.get(SOAP_PARAM.GUEST_RESERVATION.value);
		String GUEST_ROOM = data.get(SOAP_PARAM.GUEST_ROOM.value);
		String ROOM_EXSHARE_FLAG = data.get(SOAP_PARAM.ROOM_EXSHARE_FLAG.value);
		String ROOM_SHARE_FLAG = data.get(SOAP_PARAM.ROOM_SHARE_FLAG.value);
		String GUEST_ARV_DATE = data.get(SOAP_PARAM.GUEST_ARV_DATE.value);
		String GUEST_DEPT_DATE = data.get(SOAP_PARAM.GUEST_DEPT_DATE.value);
		String GUEST_FIRST_NAME = data.get(SOAP_PARAM.GUEST_FIRST_NAME.value);
		String GUEST_LAST_NAME = data.get(SOAP_PARAM.GUEST_LAST_NAME.value);
		String GUEST_NAME = data.get(SOAP_PARAM.GUEST_NAME.value);
		String GUEST_LANGUAGE = data.get(SOAP_PARAM.GUEST_LANGUAGE.value);
		String GUEST_GROUP = data.get(SOAP_PARAM.GUEST_GROUP.value);
		String GUEST_TITLE = data.get(SOAP_PARAM.GUEST_TITLE.value);
		String GUEST_VIP_STATUS = data.get(SOAP_PARAM.GUEST_VIP_STATUS.value);
		String GUEST_TV_RIGHT = data.get(SOAP_PARAM.GUEST_TV_RIGHT.value);
		String GUEST_VIDEO_RIGHT = data.get(SOAP_PARAM.GUEST_VIDEO_RIGHT.value);
		
		boolean succ = core.GuestRoomAndChangeMove(GUEST_EXROOM, GUEST_RESERVATION, GUEST_ROOM, ROOM_EXSHARE_FLAG, ROOM_SHARE_FLAG, 
				GUEST_ARV_DATE, GUEST_DEPT_DATE, GUEST_FIRST_NAME, GUEST_LAST_NAME, GUEST_NAME, GUEST_LANGUAGE, GUEST_GROUP, GUEST_TITLE, GUEST_VIP_STATUS, GUEST_TV_RIGHT, GUEST_VIDEO_RIGHT);
		if(succ) 
			return ACK_SOKHA.SUCC.value;
		else 
			return ACK_SOKHA.FAIL.value;
	}

	@Override
	public Integer GuestMessageWaiting(String message) {
		
		message = DataHelper.RebuildMessage(message);
		
		logger.info("GuestMessageWaiting <== " + message);
		synchronized (MainObject.ListMessReq) {
			if(!MainObject.ListMessReq.contains(message))
				MainObject.ListMessReq.add(message);
		}
		return ACK_SOKHA.SUCC.value;
	}
}
	
