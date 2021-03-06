package com.elcom.ehotel.admin.model;

public class SystemServiceModel {
	private String id = "";
	private String name = "";
	private String index = "";
	private String image = "";
	private String invisible = "";

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

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	@Override
	public String toString() {
		return "SystemServiceModel [id=" + id + ", name=" + name + ", index="
				+ index + ", image=" + image + ", invisible=" + invisible + "]";
	}

}
