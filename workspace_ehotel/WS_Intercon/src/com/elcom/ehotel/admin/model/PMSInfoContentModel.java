package com.elcom.ehotel.admin.model;

public class PMSInfoContentModel {
	private String subjectId = "";
	private String contentId = "";
	private String name = "";
	private String description = "";
	private String image = "";
	private String invisible = "";
	private String langId = "";
	private String index = "";

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "PMSInfoContentModel [subjectId=" + subjectId + ", contentId=" + contentId + ", name=" + name + ", description="
				+ description + ", image=" + image + ", invisible=" + invisible + ", langId=" + langId + ", index=" + index + "]";
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

}
