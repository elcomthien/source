package com.elcom.ehotel.esmile.model;

public class ObjPromotion {
	String id, name, url ,image;

	public ObjPromotion(String id, String name,String url, String image) {
		this.id = id;
		this.name = name;
		this.url=url;
		this.image = image;
	}

	public String getid() {
		return id;
	}

	public void setIdb(String idb) {
		this.id = idb;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setid(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Promotion Object [id=" + id + ", url=" + url + ", name=" + name+", url=" + url+ "]";
	}
}
