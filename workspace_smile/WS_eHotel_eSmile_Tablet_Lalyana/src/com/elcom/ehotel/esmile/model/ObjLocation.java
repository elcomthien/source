package com.elcom.ehotel.esmile.model;

public class ObjLocation {
	String key, name, ip;
	public ObjLocation(String key,String name,String ip)
	{
		this.key=key;
		this.name=name;
		this.ip=ip;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}	
