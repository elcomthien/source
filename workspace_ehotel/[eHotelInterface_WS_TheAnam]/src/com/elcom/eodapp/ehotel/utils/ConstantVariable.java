package com.elcom.eodapp.ehotel.utils;

import javax.jws.WebParam;
import javax.jws.WebParam.Mode;

import com.elcom.ehotel.variables.FieldVariables.IField;

public class ConstantVariable {
	
	public enum ACK_SOKHA {
		SUCC(0),
		FAIL(1),
		INVALID(2);
		public int value;
		private ACK_SOKHA(int value) {
			this.value = value;
		}		
		@Override public String toString() { return "" + value;}
	}
	
	public enum OPERATION_NAME {
		GUEST_MESSAGE("guestMessage"),
		READ_MESSAGE("readMessage"),
		POST_CHARGE("postCharge"),
		BILL("bill"),
		DB_SWAP("dbSwap");
		
		public String value;
		private OPERATION_NAME(String value) {
			this.value = value;
		}		
		@Override public String toString() { return value;}
	}
	
	public enum XPOS_ACK {
		SUCC(1),
		FAIL(2),
		EXECUTING(3);
		public int value;
		private XPOS_ACK(int value) {
			this.value = value;
		}		
		@Override public String toString() { return "" + value;}
	}
	
	public static int rs_SUCC = 0;
	public static int rs_FAIL = 1;
	public static int rs_INVALID = 2;
			
	public enum SOAP_PARAM  {
		GUEST_ARV_DATE("Guest-ArrivalDate"), 
		GUEST_DEPT_DATE("Guest-DepartureDate"), 
		GUEST_FIRST_NAME("Guest-FirstName"), 
		GUEST_LAST_NAME("Guest-LastName"),
		GUEST_NAME("Guest-Name"), 
		GUEST_RESERVATION("Guest-Reservation"),
		GUEST_ROOM ("Guest-Room"),
		GUEST_LANGUAGE("Guest-Langauge"),
		GUEST_GROUP("Guest-Group"),
		GUEST_TITLE("Guest-Title"), 
		GUEST_VIP_STATUS ("Guest-VIPStatus"),
		GUEST_TV_RIGHT ("Guest-TVRights"),
		GUEST_VIDEO_RIGHT("Guest-VideoRights"),
		GUEST_BIRTHDAY ("Guest-DOB"),
		ROOM_SHARE_FLAG ("Room-ShareFlag"),
		GUEST_EXROOM("Guest-ExRoom"),		
		ROOM_EXSHARE_FLAG("Room-ExShareFlag");
		
		public String value;
		private SOAP_PARAM(String value) {
			this.value = value;
		}		
		@Override public String toString() { return value;}
	}
	
	public enum XPOS_OUT_PARAM  {
		GUEST_ROOM("roomno"), 
		GUEST_RESERVATION("guestno"),
		MESSAGE_ID("message_id"),
		MESSAGE_TEXT("message_text"),
		LAST_UPDATE("last_update"),
		ITEM("item"),
		AMOUNT("amount"),
		DEP_CODE("dep_code"),
		FOLIO_NUMBER("folio_number"),
		ITEM_DATE("item_date"),
		ITEM_TIME("item_time"),
		ITEM_FLAG("item_flag")
		;
		public String value;
		private XPOS_OUT_PARAM(String value) {
			this.value = value;
		}		
		@Override public String toString() { return value;}
	}
	
	public enum XPOS_IN_PARAM
	{		
		GUEST_ROOM("sRoomNo"),
		GUEST_RESERVATION("sGuestNo"),
		POST_AMOUNT("sAmount"),		
		MESSAGE_ID("sMessageId"),	
		CHARGE_CODE("sChargeCode")
		;
		public String value;
		private XPOS_IN_PARAM(String value) {
			this.value = value;
		}		
		@Override public String toString() { return value;}
	}
}


