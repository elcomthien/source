package com.elcom.ehotel.admin.model;

public class SystemServiceModel {
	private String id = "";
	private String name = "";
	private String index = "";
	private String image = "";
	private String invisible = "";
	private String apkpackage = "";
	private String apkpath = "";
	private String langid = "";

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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getApkpackage() {
		return apkpackage;
	}

	public void setApkpackage(String apkpackage) {
		this.apkpackage = apkpackage;
	}

	public String getApkpath() {
		return apkpath;
	}

	public void setApkpath(String apkpath) {
		this.apkpath = apkpath;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	@Override
	public String toString() {
		return "SystemServiceModel [id=" + id + ", name=" + name + ", index=" + index + ", image=" + image + ", invisible=" + invisible
				+ ", apkpackage=" + apkpackage + ", apkpath=" + apkpath + ", langid=" + langid + "]";
	}

}
