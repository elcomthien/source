package com.elcom.ehotel.esmile.model;

import java.util.List;

public class StaffVoteModel {
	private String user_id = "";
	private String name = "";
	private List<SmileModel> smile = null;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public List<SmileModel> getSmile() {
		return smile;
	}

	public void setSmile(List<SmileModel> smile) {
		this.smile = smile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StaffVoteModel [user_id=" + user_id + ", name=" + name + ", smile=" + smile + "]";
	}

}
