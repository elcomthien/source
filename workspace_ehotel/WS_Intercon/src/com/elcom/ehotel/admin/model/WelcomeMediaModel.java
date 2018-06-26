package com.elcom.ehotel.admin.model;

public class WelcomeMediaModel {
	private String id = "";
	private String name = "";
	private String filename = "";
	private String index = "";
	private String status = "";
	private String type = "";
	private String idgroup = "";

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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(String idgroup) {
		this.idgroup = idgroup;
	}

	@Override
	public String toString() {
		return "WelcomeMediaModel [id=" + id + ", name=" + name + ", filename=" + filename + ", index=" + index + ", status=" + status
				+ ", type=" + type + ", idgroup=" + idgroup + "]";
	}

}
