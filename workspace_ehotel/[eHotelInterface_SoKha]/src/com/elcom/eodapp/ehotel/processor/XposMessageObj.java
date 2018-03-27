package com.elcom.eodapp.ehotel.processor;

public class XposMessageObj {
	private String GUEST_ROOM ="";
	private String GUEST_RESERVATION ="";				
	private String MESSAGE_ID ="";
	private String MESSAGE_TEXT ="";
	private String  LAST_UPDATE = "";
	public String getGUEST_ROOM() {
		return GUEST_ROOM;
	}
	public void setGUEST_ROOM(String gUEST_ROOM) {
		GUEST_ROOM = gUEST_ROOM;
	}
	public String getGUEST_RESERVATION() {
		return GUEST_RESERVATION;
	}
	public void setGUEST_RESERVATION(String gUEST_RESERVATION) {
		GUEST_RESERVATION = gUEST_RESERVATION;
	}
	public String getMESSAGE_ID() {
		return MESSAGE_ID;
	}
	public void setMESSAGE_ID(String mESSAGE_ID) {
		MESSAGE_ID = mESSAGE_ID;
	}
	public String getMESSAGE_TEXT() {
		return MESSAGE_TEXT;
	}
	public void setMESSAGE_TEXT(String mESSAGE_TEXT) {
		MESSAGE_TEXT = mESSAGE_TEXT;
	}
	public String getLAST_UPDATE() {
		return LAST_UPDATE;
	}
	public void setLAST_UPDATE(String lAST_UPDATE) {
		LAST_UPDATE = lAST_UPDATE;
	}
}
