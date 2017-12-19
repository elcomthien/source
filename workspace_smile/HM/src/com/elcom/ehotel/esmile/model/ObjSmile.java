package com.elcom.ehotel.esmile.model;

public class ObjSmile {
	String id, langid, name, nextpage, avatar,fid;

	public ObjSmile(String id, String langid, String name, String nextpage, String avatar, String fid) {
		this.id = id;
		this.langid = langid;
		this.name = name;
		this.nextpage = nextpage;
		this.avatar = avatar;
		this.fid=fid;
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

	public String getnextpage() {
		return nextpage;
	}

	public void setnextpage(String nextpage) {
		this.nextpage = nextpage;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNextpage() {
		return nextpage;
	}

	public void setNextpage(String nextpage) {
		this.nextpage = nextpage;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	@Override
	public String toString() {
		return "ObjSmile [id=" + id + ", langid=" + langid + ", name=" + name + ", nextpage=" + nextpage + ", avatar=" + avatar + ", fid="
				+ fid + "]";
	}

}
