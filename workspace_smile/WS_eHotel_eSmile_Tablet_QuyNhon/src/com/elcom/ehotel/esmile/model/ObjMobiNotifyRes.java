package com.elcom.ehotel.esmile.model;

import java.util.List;

public class ObjMobiNotifyRes {
	String status, message;
	List<ObjMobileNotify> data;

	public ObjMobiNotifyRes(String status, String message, List<ObjMobileNotify> data) {
		this.status = status;
		this.message = message;
		this.data = data;
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

	public List<ObjMobileNotify> getData() {
		return data;
	}

	public void setData(List<ObjMobileNotify> data) {
		this.data = data;
	}

}
