package com.elcom.ehotel.esmile.model;

public class ObjItemInfo {

	String id, name, url;

	public ObjItemInfo(String id, String name, String url) {
		this.id=id;
		this.name = name;
		this.url = url;
	}
	
	

	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
