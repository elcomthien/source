package com.elcom.ehotel.admin.model;

public class SystemAdvertiseModel {
	private String id = "";
	private String name = "";
	private String image = "";
	// 0: show, 1: hide
	private String invisible = "";
	// type = "WELCOME", type = "MUSIC"
	private String type = "";
	// 0: is slide, 1: is bg
	private String background = "";
	private String idGroup = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	@Override
	public String toString() {
		return "SystemAdvertiseModel [id=" + id + ", name=" + name + ", image=" + image + ", invisible=" + invisible + ", type=" + type
				+ ", background=" + background + ", idGroup=" + idGroup + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}

}
