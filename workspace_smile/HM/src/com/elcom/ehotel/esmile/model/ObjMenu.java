package com.elcom.ehotel.esmile.model;

public class ObjMenu {
	String id, name, image, type,typename;

	public ObjMenu(String id, String name, String image, String type, String typename) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.type = type;
		this.typename = typename;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ObjMenu [id=" + id + ", name=" + name + ", image=" + image + ", type=" + type + ", typename=" + typename + "]";
	}
	
	
}
