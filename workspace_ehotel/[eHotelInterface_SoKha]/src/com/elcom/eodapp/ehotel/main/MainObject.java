package com.elcom.eodapp.ehotel.main;

import java.util.ArrayList;
import java.util.Hashtable;

import com.elcom.eodapp.ehotel.cfg.Configuration;
import com.elcom.eodapp.ehotel.sokha.CoreSoKhaInterfacePortType;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.SOAP_PARAM;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_OUT_PARAM;


public class MainObject {
	public static Configuration config;
	public static ArrayList<String> ListBillReq = new ArrayList<String>();
	public static ArrayList<String> ListMessReq = new ArrayList<String>();
	//public static CoreSoKhaInterfacePortType eHotelWebService = null;
	
	
	public static Hashtable<String , String> mapCheckIn = new Hashtable<String, String>() 
			{ {
				put(SOAP_PARAM.GUEST_ARV_DATE.value,"");
				put(SOAP_PARAM.GUEST_DEPT_DATE.value,"");				
				put(SOAP_PARAM.GUEST_FIRST_NAME.value, "");
				put(SOAP_PARAM.GUEST_LAST_NAME.value, "");
				put(SOAP_PARAM.GUEST_NAME.value, "");
				put(SOAP_PARAM.GUEST_RESERVATION.value, "");
				put(SOAP_PARAM.GUEST_ROOM.value, "");
				put(SOAP_PARAM.GUEST_LANGUAGE.value, "");
				put(SOAP_PARAM.GUEST_GROUP.value, "");
				put(SOAP_PARAM.GUEST_TITLE.value, "");
				put(SOAP_PARAM.GUEST_VIP_STATUS.value, "");
				put(SOAP_PARAM.GUEST_TV_RIGHT.value, "");
				put(SOAP_PARAM.GUEST_VIDEO_RIGHT.value, "");
				put(SOAP_PARAM.GUEST_BIRTHDAY.value, "");
				put(SOAP_PARAM.ROOM_SHARE_FLAG.value, "");				
			}};
			
	public static Hashtable<String , String> mapCheckOut = new Hashtable<String, String>()
			{ {
				put(SOAP_PARAM.GUEST_RESERVATION.value ,"");
				put(SOAP_PARAM.GUEST_ROOM.value,"");
				put(SOAP_PARAM.ROOM_SHARE_FLAG.value, "");							
			}};
			
	public static Hashtable<String , String> mapGuestMove = new Hashtable<String, String>()
			{{
				put(SOAP_PARAM.GUEST_LAST_NAME.value,"");
				put(SOAP_PARAM.GUEST_GROUP.value,"");
				put(SOAP_PARAM.GUEST_VIP_STATUS.value,"");
				put(SOAP_PARAM.GUEST_RESERVATION.value,"");
				put(SOAP_PARAM.GUEST_NAME.value,"");
				put(SOAP_PARAM.GUEST_FIRST_NAME.value,"");
				put(SOAP_PARAM.GUEST_DEPT_DATE.value,"");
				put(SOAP_PARAM.ROOM_SHARE_FLAG.value,"");
				put(SOAP_PARAM.GUEST_LANGUAGE.value,"");
				put(SOAP_PARAM.GUEST_TITLE.value,"");
				put(SOAP_PARAM.GUEST_ARV_DATE.value,"");
				put(SOAP_PARAM.GUEST_ROOM.value,"");
				put(SOAP_PARAM.GUEST_TV_RIGHT.value,"");
				put(SOAP_PARAM.GUEST_EXROOM.value,"");
				put(SOAP_PARAM.GUEST_VIDEO_RIGHT.value,"");
				put(SOAP_PARAM.ROOM_EXSHARE_FLAG.value, "");
			}};
			
	public static Hashtable<String , String> mapWaitMess = new Hashtable<String, String>()
			{{				
				put(SOAP_PARAM.GUEST_RESERVATION.value,"");				
				put(SOAP_PARAM.GUEST_ROOM.value,"");				
			}};

	public static Hashtable<String , String> mapGuestMessage = new Hashtable<String, String>()
			{{				
				put(XPOS_OUT_PARAM.GUEST_ROOM.value,"");
				put(XPOS_OUT_PARAM.GUEST_RESERVATION.value,"");				
				put(XPOS_OUT_PARAM.MESSAGE_ID.value,"");
				put(XPOS_OUT_PARAM.MESSAGE_TEXT.value,"");
				put(XPOS_OUT_PARAM.LAST_UPDATE.value,"");
			}};
			
	public static Hashtable<String , String> mapGuestBill = new Hashtable<String, String>()
			{{				
				put(XPOS_OUT_PARAM.GUEST_ROOM.value,"");
				put(XPOS_OUT_PARAM.GUEST_RESERVATION.value,"");				
				put(XPOS_OUT_PARAM.ITEM.value,"");
				put(XPOS_OUT_PARAM.AMOUNT.value,"");
				put(XPOS_OUT_PARAM.DEP_CODE.value,"");				
				put(XPOS_OUT_PARAM.FOLIO_NUMBER.value,"");
				put(XPOS_OUT_PARAM.ITEM_DATE.value,"");
				put(XPOS_OUT_PARAM.ITEM_TIME.value,"");
				put(XPOS_OUT_PARAM.ITEM_FLAG.value,"");
			}};
}
