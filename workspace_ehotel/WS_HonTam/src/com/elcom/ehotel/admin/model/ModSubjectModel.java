package com.elcom.ehotel.admin.model;

public class ModSubjectModel {
	private String idSubject = "";
	private String name = "";
	private String image = "";
	private String imageIC = "";
	private String index = "";
	private String invisible = "";
	private String langid = "";

	public String getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(String idSubject) {
		this.idSubject = idSubject;
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getImageIC() {
		return imageIC;
	}

	public void setImageIC(String imageIC) {
		this.imageIC = imageIC;
	}

	@Override
	public String toString() {
		return "ModSubjectModel [idSubject=" + idSubject + ", name=" + name + ", image=" + image + ", imageIC=" + imageIC + ", index=" + index
				+ ", invisible=" + invisible + ", langid=" + langid + "]";
	}

}
