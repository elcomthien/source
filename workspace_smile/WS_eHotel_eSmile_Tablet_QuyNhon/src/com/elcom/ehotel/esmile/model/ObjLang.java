package com.elcom.ehotel.esmile.model;

public class ObjLang {
	String id, name, image, status;

	public ObjLang(String id, String name, String icon, String status) {
		this.id = id;
		this.name = name;
		this.image = icon;
		this.status = status;
	}

	public String getLangid() {
		return id;
	}

	public void setLangid(String langid) {
		this.id = langid;
	}

	public String getLangname() {
		return name;
	}

	public void setLangname(String langname) {
		this.name = langname;
	}

	public String getIcon() {
		return image;
	}

	public void setIcon(String icon) {
		this.image = icon;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Lang Object [id=" + id + ", name=" + name + ", icon=" + image + ", status=" + status + "]";
	}

}
