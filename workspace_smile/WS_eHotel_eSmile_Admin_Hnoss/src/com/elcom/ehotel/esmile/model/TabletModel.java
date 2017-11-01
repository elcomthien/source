package com.elcom.ehotel.esmile.model;


public class TabletModel {
	private String id = "";
	private String name = "";
	private String image = "";
	private String isLogin = "";
	private String login_by = "";
	private String user_id = "";

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getLogin_by() {
		return login_by;
	}

	public void setLogin_by(String login_by) {
		this.login_by = login_by;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "TabletModel [id=" + id + ", name=" + name + ", image=" + image + ", isLogin=" + isLogin + ", login_by=" + login_by
				+ ", user_id=" + user_id + "]";
	}

}
