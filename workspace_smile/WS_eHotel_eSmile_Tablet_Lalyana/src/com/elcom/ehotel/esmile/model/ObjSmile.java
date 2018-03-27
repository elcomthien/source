package com.elcom.ehotel.esmile.model;

public class ObjSmile {
	String id, langid, name, nextpid, image;

	public ObjSmile(String id, String langid, String name, String nextpid, String image) {
		this.id = id;
		this.langid = langid;
		this.name = name;
		this.nextpid = nextpid;
		this.image = image;
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

	public String getnextpid() {
		return nextpid;
	}

	public void setnextpid(String nextpid) {
		this.nextpid = nextpid;
	}

	public String getimage() {
		return image;
	}

	public void setimage(String image) {
		this.image = image;
	}

	// this.id = id;
	// this.langid=langid;
	// this.name=name;
	// this.nextpid=nextpid;
	// this.image=image;
	@Override
	public String toString() {
		return "Smile Object [id=" + id + ", langid=" + langid + ", name=" + name + ", nextpid=" + nextpid + ", image="
				+ image + "]";
	}

}
