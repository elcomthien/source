package com.elcom.ehotel.esmile.model;

public class FacultyModel {
	String id = "";
	String name = "";
	String image = "";
	String index = "";
	String status = "";

	public FacultyModel(String id, String name, String image, String index, String status) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.index = index;
		this.status = status;
	}

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

	@Override
	public String toString() {
		return "FacultyModel [id=" + id + ", name=" + name + ", image=" + image + ", index=" + index + ", status=" + status + "]";
	}

}
