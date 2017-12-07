package com.elcom.ehotel.esmile.model;

public class ObjWelcome {
	String id, langid, name, fid;

	public ObjWelcome(String id, String langid, String name, String fid) {
		this.id = id;
		this.langid = langid;
		this.name = name;
		this.fid = fid;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	@Override
	public String toString() {
		return "ObjWelcome [id=" + id + ", langid=" + langid + ", name=" + name + ", fid=" + fid + "]";
	}

}
