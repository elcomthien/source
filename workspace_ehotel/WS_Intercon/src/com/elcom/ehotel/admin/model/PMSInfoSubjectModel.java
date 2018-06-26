package com.elcom.ehotel.admin.model;

public class PMSInfoSubjectModel {
	private String subjectId = "";
	private String subjectName = "";
	private String serviceId = "";
	private String invisible = "";
	private String image = "";
	private String imageIC = "";
	private String type = "";
	private String index = "";
	private String langId = "";
	private String idgroup = "";

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
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

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	@Override
	public String toString() {
		return "PMSInfoSubjectModel [subjectId=" + subjectId + ", subjectName=" + subjectName + ", serviceId=" + serviceId + ", invisible="
				+ invisible + ", image=" + image + ", imageIC=" + imageIC + ", type=" + type + ", index=" + index + ", langId=" + langId
				+ ", idgroup=" + idgroup + "]";
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getImageIC() {
		return imageIC;
	}

	public void setImageIC(String imageIC) {
		this.imageIC = imageIC;
	}

	public String getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(String idgroup) {
		this.idgroup = idgroup;
	}

}
