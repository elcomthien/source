package com.elcom.eodapp.ehotel.common;

public class oPost {
	private String DATE; //DA
	private String TIME; //TI
	private String ROOM_NUMBER;//RN	
	private String TOTAL_AMOUNT;//TA
	private String CURRENCY_TYPE;//CU
	private String POST_SEQ_NUMBER;//P#
	private String POST_TEXT;//PT	
	
	//Getter
	public String getDATE() {
		return DATE;
	}
	public String getTIME() {
		return TIME;
	}
	public String getCURRENCY_TYPE() {
		return CURRENCY_TYPE;
	}
	public String getROOM_NUMBER() {
		return ROOM_NUMBER;
	}	
	public String getTOTAL_AMOUNT() {
		return TOTAL_AMOUNT;
	}	
	public String getPOST_SEQ_NUMBER() {
		return POST_SEQ_NUMBER;
	}
	public String getPOST_TEXT() {
		return POST_TEXT;
	}
	
	//Setter
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}		
	public void setCURRENCY_TYPE(String cURRENCY_TYPE) {
		CURRENCY_TYPE = cURRENCY_TYPE;
	}
	public void setROOM_NUMBER(String rOOM_NUMBER) {
		ROOM_NUMBER = rOOM_NUMBER;
	}	
	public void setTOTAL_AMOUNT(String tOTAL_AMOUNT) {
		TOTAL_AMOUNT = tOTAL_AMOUNT;
	}
	public void setPOST_SEQ_NUMBER(String pOST_SEQ_NUMBER) {
		POST_SEQ_NUMBER = pOST_SEQ_NUMBER;
	}
	public void setPOST_TEXT(String pOST_TEXT) {
		POST_TEXT = pOST_TEXT;
	}	
}
