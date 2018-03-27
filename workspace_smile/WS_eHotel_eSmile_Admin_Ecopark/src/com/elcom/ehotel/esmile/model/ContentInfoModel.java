package com.elcom.ehotel.esmile.model;

public class ContentInfoModel {
	private String subid = "";
	private String id = "";
	private String name = "";
	private String url = "";
	private String invisible = "";
	private String langid = "";

	public String getSubid() {
		return subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	@Override
	public String toString() {
		return "ContentInfoModel [subid=" + subid + ", id=" + id + ", name=" + name + ", url=" + url + ", invisible=" + invisible
				+ ", landid=" + langid + "]";
	}

}
