package com.elcom.ehotel.esmile.model;

public class PMSDiningSubjectModel {
	private String id = "";
	private String name = "";
	private String invisible = "";
	private String menuno = "";
	private String image = "";
	private String index = "";
	private String parent = "";
	private String langId = "";

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

	public String getActive() {
		return invisible;
	}

	public void setActive(String active) {
		this.invisible = active;
	}

	public String getMenuno() {
		return menuno;
	}

	public void setMenuno(String menuno) {
		this.menuno = menuno;
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	@Override
	public String toString() {
		return "PMSDiningSubjectModel [id=" + id + ", name=" + name + ", active=" + invisible + ", menuno=" + menuno + ", image=" + image + ", index="
				+ index + ", parent=" + parent + ", langId=" + langId + "]";
	}

}
