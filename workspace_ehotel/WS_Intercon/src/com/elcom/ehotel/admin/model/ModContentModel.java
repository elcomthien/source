package com.elcom.ehotel.admin.model;

public class ModContentModel {
	private String idSubject = "";
	private String idContent = "";
	private String name = "";
	private String url = "";
	private String langid = "";
	private String invisible = "";
	private String idGroup = "";

	public String getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(String idSubject) {
		this.idSubject = idSubject;
	}

	public String getIdContent() {
		return idContent;
	}

	public void setIdContent(String idContent) {
		this.idContent = idContent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}

	@Override
	public String toString() {
		return "ModContentModel [idSubject=" + idSubject + ", idContent=" + idContent + ", name=" + name + ", url=" + url + ", langid="
				+ langid + ", invisible=" + invisible + ", idGroup=" + idGroup + "]";
	}

}
