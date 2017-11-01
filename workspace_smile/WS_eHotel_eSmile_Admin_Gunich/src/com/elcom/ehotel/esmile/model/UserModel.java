package com.elcom.ehotel.esmile.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserModel {
	private String id = "";
	private String username = "";
	private String password = "";
	private String name = "";
	private String image = "";
	private String role_id = "";
	private String role_name = "";
	private List<HashMap<String, String>> store = new ArrayList<>();

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<HashMap<String, String>> getStore() {
		return store;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public void setStore(List<HashMap<String, String>> store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", image=" + image
				+ ", role_id=" + role_id + ", role_name=" + role_name + ", store=" + store + "]";
	}

}
