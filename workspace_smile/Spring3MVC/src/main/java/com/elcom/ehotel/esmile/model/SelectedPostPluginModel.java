package com.elcom.ehotel.esmile.model;

public class SelectedPostPluginModel {
	String id = "";
	String name = "";
	String image = "";

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

	@Override
	public String toString() {
		return "SelectedPostPluginModel [id=" + id + ", name=" + name + ", image=" + image + "]";
	}

}
