package com.elcom.ehotel.esmile.model;

import java.util.List;

public class StaffSmileModel {
	private String id = "";
	private String name = "";
	private List<SmileModel> smile = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "LocationSmileModel [id=" + id + ", name=" + name + ", smile=" + smile + "]";
	}

}
