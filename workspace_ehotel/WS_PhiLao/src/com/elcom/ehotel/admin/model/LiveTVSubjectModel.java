package com.elcom.ehotel.admin.model;

public class LiveTVSubjectModel {
	private String idSubject = "";
	private String name = "";
	private String image = "";
	private String langId = "";
	private String status = "";
	private String modifyDate = "";
	private String oderby = "";
	private String listadd = "";
	private String listremove = "";

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getOderby() {
		return oderby;
	}

	public void setOderby(String oderby) {
		this.oderby = oderby;
	}

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

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LiveTVSubjectModel [idSubject=" + idSubject + ", name=" + name + ", image=" + image + ", langId=" + langId + ", status=" + status
				+ ", modifyDate=" + modifyDate + ", oderby=" + oderby + ", listadd=" + listadd + ", listremove=" + listremove + "]";
	}

	public String getListadd() {
		return listadd;
	}

	public void setListadd(String listadd) {
		this.listadd = listadd;
	}

	public String getListremove() {
		return listremove;
	}

	public void setListremove(String listremove) {
		this.listremove = listremove;
	}

}
