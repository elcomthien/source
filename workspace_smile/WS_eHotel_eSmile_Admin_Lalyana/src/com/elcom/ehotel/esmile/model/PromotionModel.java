package com.elcom.ehotel.esmile.model;

public class PromotionModel {
	private String id = "";
	private String name = "";
	private String url = "";
	private String image = "";
	private String status = "";
	private String modify = "";

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	@Override
	public String toString() {
		return "PromotionModel [id=" + id + ", name=" + name + ", url=" + url + ", image=" + image + ", status=" + status + ", modify="
				+ modify + "]";
	}

}
