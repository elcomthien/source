package com.elcom.ehotel.esmile.model;

import java.util.List;

public class ObjReRegister {
	String id, name;
	List<ObjLocation> device;
	public ObjReRegister(String id,String name,
	List<ObjLocation> device)
	{
		this.id=id;
		this.name=name;
		this.device=device;
	}
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
	public List<ObjLocation> getDevice() {
		return device;
	}
	public void setDevice(List<ObjLocation> device) {
		this.device = device;
	}
	
	
}
