package com.elcom.ehotel.esmile.model;

public class ObjWelcome {
	String id, langid, name;

	public ObjWelcome(String id, String langid, String name) {
		this.id = id;
		this.langid = langid;
		this.name = name;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Welcome Object [id=" + id + ", langid=" + langid + ", name=" + name + "]";
	}

}
