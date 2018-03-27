package com.elcom.ehotel.esmile.model;

public class ObjBackground {

	String id, url, status;

	public ObjBackground(String id, String url, String status) {
		this.id = id;
		this.url = url;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String idb) {
		this.id = idb;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Backgroun Object [id=" + id + ", url=" + url + ", status=" + status + "]";
	}
}
