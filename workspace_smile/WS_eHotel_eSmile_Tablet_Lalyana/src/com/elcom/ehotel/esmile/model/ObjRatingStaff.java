package com.elcom.ehotel.esmile.model;

public class ObjRatingStaff {
	String id, name, pid, nextpid, image;

	public ObjRatingStaff(String id, String name, String pid, String nextpid, String image) {
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.nextpid = nextpid;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getNextpid() {
		return nextpid;
	}

	public void setNextpid(String nextpid) {
		this.nextpid = nextpid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
