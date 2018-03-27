package com.elcom.ehotel.esmile.model;

public class ObjStaff {
	String id, username, name, isLogin, image, location;

	public ObjStaff(String id, String username, String name, String isLogin, String image, String location) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.isLogin = isLogin;
		this.image = image;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
