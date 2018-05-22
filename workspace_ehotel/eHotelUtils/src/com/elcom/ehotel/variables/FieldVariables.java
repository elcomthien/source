package com.elcom.ehotel.variables;


public class FieldVariables {
		
	public interface IField {}
	
	public enum FID implements IField {
		FIELD_LIST("FL"), 
		RECORD_INDICATOR("RI"), 
		VERSION("V#"), 
		INTERFACE("IF"),
		DATE("DA"), 
		TIME("TI"),
		DEPARTURE_TIME ("DT"),
		DIALED_DIGITS("DD"),
		DURATION("DU"),
		ANWSER_STATUS("AS"), 
		CLEAR_TEXT ("CT"),
		CLASS_OF_SERVICE ("CS"),
		DO_NOT_DISTURB_STATUS("DN"),
		NUMOF_PERS ("PU"),
		VOICE_MAIL ("VM"),
		WORKSTATION_ID("WS"),
		MINIBAR_ARTICEL("MA"),
		NUMOF_ARTICEL("M#"), 
		TAX_PULSE ("MP"),
		SALE_OUTLET ("SO"),
		USER_ID ("ID"),
		DEPARTMENT_CODE("DC"),
		POS_TYPE("PT"), 
		TOTAL_POS_AMOUNT("TA"),
		POS_SEQ_NUM("P#"), 
		POS_ROUTE("PX"),
		POS_CALL_TYPE("PC"),
		INQUIRY_DATA("PI"),
		POS_CHECK_NUMBER("C#"),
		BALANCE_AMOUNT("BA"),
		PAYMENT_METHOD ("PM"),
		ITEM_DESCRIPTION ("BD"), 
		ITEM_AMOUNT ("BI"),
		ITEM_DISPLAY_FLAG ("FD"),
		ROOM_NUMER ("RN"), 
		OLD_ROOM_NUMER ("RO"),
		ROOM_MAID_STATUS ("RS"),
		SWAP_FLAG ("SF"),		
		SHARE_FLAG ("GS"),
		NO_POST_FLAG ("NP"),
		RESERVATION_NUMER("G#"),
		GUEST_FIRST_NAME ("GF"),
		GUEST_NAME ("GN"),
		GUEST_LANGUAGE ("GL"),
		GUEST_VIP_STATUS ("GV"),		 
		GUEST_GROUP_NUMBER ("GG"),
		GUEST_TITLE ("GT"),
		GUEST_ARV_DATE("GA"),
		GUEST_DEPT_DATE("GD"),
		GUEST_PIN("GP"),
		EQUIP_NUMBER ("EN"),		 
		EQUIP_POOLID ("EP"),
		EQUIP_STATUS ("ES"),
		EQUIP_NUMBER_OLD_ROOM ("EO"),
		EQUIP_STATUS_OLD_ROOM ("ET"),
		EQUIP_POOLID_OLD_ROOM ("EI"),
		MESSAGE_ID ("MI"),		 
		MESSAGE_TEXT ("MT"),
		TRANSACTION_NUMBER ("$J"),		
		MESSAGE_LIGHT_STATUS ("ML"),
		MINIBAR_RIGHT ("MR"),
		TV_RIGHT ("TV")
		;
		public String value;
		private FID(String value) {
			this.value = value;
		}		
		@Override public String toString() { return value;}
	}
	
	 public static class FieldUtils {
			public static boolean compare(IField fd, String s) {				
				return fd.toString().equals(s);
			}
		}
}
