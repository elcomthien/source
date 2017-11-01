package com.elcom.ehotel.esmile.model;

public class ObjSubjectInfo {
	String id, name, image;

	public ObjSubjectInfo(String menuno, String menuname, String urlimage) {
		this.id = menuno;
		this.name = menuname;
		this.image = urlimage;
	}

	public String getMenuno() {
		return id;
	}

	public void setMenuno(String menuno) {
		this.id = menuno;
	}

	public String getMenuname() {
		return name;
	}

	public void setMenuname(String menuname) {
		this.name = menuname;
	}

	public String getUrlimage() {
		return image;
	}

	public void setUrlimage(String urlimage) {
		this.image = urlimage;
	}

	@Override
	public String toString() {
		return "ObjSubjectInfo [id=" + id + ", name=" + name + ", image=" + image + "]";
	}

}
