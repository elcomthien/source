package com.elcom.ehotel.variables;

public class Common {
	
	public static String PMSFormatDate = "yyMMdd";
    public static String PMSFormatTime = "HHmmss";
	public static String eHotelRegex = ";";
	public static String PMSRegex = "|";
	public static char STX = (char)Integer.parseInt("02", 16);
	public static char ETX = (char)Integer.parseInt("03", 16);
	
	public enum EXECUTE_STATUS 
	{ 
		 /*OK {@Override public String toString() { return "OK";}},
		 NOT {@Override public String toString() { return "NOT";}},
		 */
		OK("OK"), NOT("NOT");
		public String value;
		private EXECUTE_STATUS(String value) {
			this.value = value;
		}
	}
	
	public enum EHOTEL_REQ
	{ 
		KILL_APP(-1), UPDATECMD (0), DBSYNC (1) , BILL (2), POST(3), ROOM_EQUIP(4), MSG_DEL(5);
		public int value;
		private EHOTEL_REQ(int value) {
			this.value = value;
		}
	}
	
}
