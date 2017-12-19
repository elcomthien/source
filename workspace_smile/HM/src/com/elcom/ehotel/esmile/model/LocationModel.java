package com.elcom.ehotel.esmile.model;

public class LocationModel {
	private String id = "";
	private String name = "";
	private String state = "";
	private String key = "";
	private String ip = "";
	private String langid = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	@Override
	public String toString() {
		return "LocationModel [id=" + id + ", name=" + name + ", state=" + state + ", key=" + key + ", ip=" + ip + ", langid=" + langid
				+ "]";
	}

}
