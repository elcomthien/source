package com.elcom.ehotel.esmile.model;

public class ObjLoginStatus {
	String isLogin, key, name;

	public ObjLoginStatus(String isLogin, String key, String name) {
		this.isLogin = isLogin;
		this.key = key;
		this.name = name;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
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
}
