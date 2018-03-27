package com.elcom.ehotel.esmile.model;


public class ObjMobileNotify {
	String id, name, location;
	String[] user_id;

	public ObjMobileNotify(String id, String name, String location, String[] user_id) {
		this.id = id;
		this.name = name == null ? "" : name;
		this.location = location == null ? "" : location;
		this.user_id = user_id;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String[] getUser_id() {
		return user_id;
	}

	public void setUser_id(String[] user_id) {
		this.user_id = user_id;
	}

}
