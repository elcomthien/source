package com.elcom.eodapp.ehotel.common;

public class oRoomData {
	private String ROOM_NUMBER; //RN
	private String CLEAR_TEXT; //CT
	private String USERID; //ID
	private String ROOM_STATUS; //RS
	private String VOICE_MAIL;//VM
	
	//Getter
	public String getROOM_NUMBER() {
		return ROOM_NUMBER;
	}
	public String getCLEAR_TEXT() {
		return CLEAR_TEXT;
	}
	public String getUSERID() {
		return USERID;
	}
	public String getROOM_STATUS() {
		return ROOM_STATUS;
	}
	public String getVOICE_MAIL() {
		return VOICE_MAIL;
	}
	
	
	//Setter
	public void setROOM_NUMBER(String rOOM_NUMBER) {
		ROOM_NUMBER = rOOM_NUMBER;
	}
	public void setCLEAR_TEXT(String cLEAR_TEXT) {
		CLEAR_TEXT = cLEAR_TEXT;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public void setROOM_STATUS(String rOOM_STATUS) {
		ROOM_STATUS = rOOM_STATUS;
	}
	public void setVOICE_MAIL(String vOICE_MAIL) {
		VOICE_MAIL = vOICE_MAIL;
	}
	
}
