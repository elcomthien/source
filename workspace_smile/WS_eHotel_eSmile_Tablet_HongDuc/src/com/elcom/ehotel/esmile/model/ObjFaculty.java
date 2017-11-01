package com.elcom.ehotel.esmile.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjFaculty {
	String id, name, image, modify;
	private List<HashMap<String, String>> location = new ArrayList<>();

	public ObjFaculty(String id, String name, String image, String modify) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.modify = modify;
	}

	public ObjFaculty(String id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public ObjFaculty(String id, String name, String image, String modify, List<HashMap<String, String>> location) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.modify = modify;
		this.location = location;
	}

	public ObjFaculty() {
		super();
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

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public List<HashMap<String, String>> getLocation() {
		return location;
	}

	public void setLocation(List<HashMap<String, String>> location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ObjFaculty [id=" + id + ", name=" + name + ", image=" + image + ", modify=" + modify + ", location=" + location + "]";
	}
}
