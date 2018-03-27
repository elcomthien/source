package com.elcom.ehotel.esmile.model;

public class ObjRegister {
	String status;
	String message;
	String key;

	public ObjRegister(String status, String message, String key) {
		this.status = status;
		this.message = message;
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	

}
