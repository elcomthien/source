package com.elcom.eodapp.ehotel.common;

public class oMessageDel {
	private String ROOM_NUMBER; //RN
	private String RESERVATION_NUMER; //G#
	private String MESSAGE_ID; //ID
	
	
	public String getROOM_NUMBER() {
		return ROOM_NUMBER;
	}
	public String getRESERVATION_NUMER() {
		return RESERVATION_NUMER;
	}
	public String getMESSAGE_ID() {
		return MESSAGE_ID;
	}
	
	
	public void setROOM_NUMBER(String rOOM_NUMBER) {
		ROOM_NUMBER = rOOM_NUMBER;
	}
	public void setRESERVATION_NUMER(String rESERVATION_NUMER) {
		RESERVATION_NUMER = rESERVATION_NUMER;
	}
	public void setMESSAGE_ID(String mESSAGE_ID) {
		MESSAGE_ID = mESSAGE_ID;
	}
	
}
