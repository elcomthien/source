package com.elcom.ehotel.admin.model;

import java.util.HashMap;
import java.util.List;

public class UserManagerModel {
	private String id = "";
	private String user = "";
	private String pass = "";
	private String name = "";
	private String address = "";
	private String department = "";
	private String active = "";
	private String roleid = "";
	private List<HashMap<String, String>> role = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public List<HashMap<String, String>> getRole() {
		return role;
	}

	public void setRole(List<HashMap<String, String>> role) {
		this.role = role;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "UserManagerModel [id=" + id + ", user=" + user + ", pass=" + pass + ", name=" + name + ", address=" + address
				+ ", department=" + department + ", active=" + active + ", roleid=" + roleid + ", role=" + role + "]";
	}

}
