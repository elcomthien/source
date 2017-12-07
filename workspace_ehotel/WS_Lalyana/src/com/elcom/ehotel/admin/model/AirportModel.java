package com.elcom.ehotel.admin.model;

public class AirportModel {
	private String id = "";
	private String name = "";
	private String code = "";
	private String status = "";
	private String index = "";
	private String image = "";
	private String langid = "";

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	@Override
	public String toString() {
		return "AirportModel [id=" + id + ", name=" + name + ", code=" + code + ", status=" + status + ", index=" + index + ", image="
				+ image + ", langid=" + langid + "]";
	}

	public AirportModel() {
		super();
	}

	public AirportModel(String id, String name, String code, String status, String index, String image, String langid) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.status = status;
		this.index = index;
		this.image = image;
		this.langid = langid;
	}

	public AirportModel(String name, String code, String image) {
		super();
		this.name = name;
		this.code = code;
		this.image = image;
	}

	
}
