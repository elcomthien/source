package com.elcom.ehotel.esmile.model;

public class ObjectPluginModel {
	String object_id = "";
	String object_name = "";
	String object_image = "";

	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public String getObject_image() {
		return object_image;
	}

	public void setObject_image(String object_image) {
		this.object_image = object_image;
	}

	@Override
	public String toString() {
		return "ObjectPluginModel [object_id=" + object_id + ", object_name=" + object_name + ", object_image=" + object_image + "]";
	}

}
