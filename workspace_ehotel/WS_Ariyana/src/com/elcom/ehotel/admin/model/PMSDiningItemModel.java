package com.elcom.ehotel.admin.model;

public class PMSDiningItemModel {
	private String id = "";
	private String name = "";
	private String def = "";
	private String active = "";
	private String price = "";
	private String iunit = "";
	private String index = "";
	private String image = "";
	private String langid = "";
	private String subjectId = "";
	private String detail = "";

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

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIunit() {
		return iunit;
	}

	public void setIunit(String iunit) {
		this.iunit = iunit;
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

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "PMSDiningItemModel [id=" + id + ", name=" + name + ", def=" + def + ", active=" + active + ", price=" + price + ", iunit="
				+ iunit + ", index=" + index + ", image=" + image + ", langid=" + langid + ", subjectId=" + subjectId + ", detail="
				+ detail + "]";
	}

}
