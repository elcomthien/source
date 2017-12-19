package com.elcom.ehotel.esmile.model;

public class ObjLang {
	String langid, langname, icon, status;

	public ObjLang(String langid, String langname, String icon, String status) {
		this.langid = langid;
		this.langname = langname;
		this.icon = icon;
		this.status = status;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getLangname() {
		return langname;
	}

	public void setLangname(String langname) {
		this.langname = langname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Lang Object [id=" + langid + ", name=" + langname + ", icon=" + icon + ", status=" + status + "]";
	}

}
