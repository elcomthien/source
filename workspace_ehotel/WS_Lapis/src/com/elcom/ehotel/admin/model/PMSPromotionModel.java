package com.elcom.ehotel.admin.model;

public class PMSPromotionModel {
	private String id = "";
	private String name = "";
	private String def = "";
	private String image = "";
	private String url = "";
	private String invisible = "";
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

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
		return "PMSPromotionModel [id=" + id + ", name=" + name + ", def=" + def + ", image=" + image + ", url=" + url + ", invisible=" + invisible
				+ ", langid=" + langid + "]";
	}

}
