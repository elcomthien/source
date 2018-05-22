package com.elcom.ehotel.esmile.model;

public class StaffModel {
	private String id = "";
	private String username = "";
	private String name = "";
	private String isLogin = "";
	private String image = "";
	private String location = "";
	private String isActive = "";

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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "StaffModel [id=" + id + ", username=" + username + ", name=" + name + ", isLogin=" + isLogin + ", image=" + image
				+ ", location=" + location + ", isActive=" + isActive + "]";
	}

}
