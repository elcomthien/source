package com.elcom.ehotel.esmile.model;

public class ObjRating {
	String id, langid, name, nextpid, avatar, pid, subjid;

	public ObjRating(String id, String langid, String name, String nextpid, String avatar, String pid, String subjid) {
		this.id = id;
		this.langid = langid;
		this.name = name;
		this.nextpid = nextpid;
		this.avatar = avatar;
		this.pid = pid;
		this.subjid = subjid;
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

	public String getNextpid() {
		return nextpid;
	}

	public void setNextpid(String nextpid) {
		this.nextpid = nextpid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSubjid() {
		return subjid;
	}

	public void setSubjid(String subjid) {
		this.subjid = subjid;
	}

	// this.id=id;
	// this.langid=langid;
	// this.name=name;
	// this.nextpid=nextpid;
	// this.avatar=avatar;
	// this.pid=pid;
	// this.subjid=subjid;

	@Override
	public String toString() {
		return "Rating Object [id=" + id + ", langid=" + langid + ", name=" + name + ", nextpid=" + nextpid
				+ ", avatar=" + avatar + ", pid=" + pid + ", subjid=" + subjid + "]";
	}

}
