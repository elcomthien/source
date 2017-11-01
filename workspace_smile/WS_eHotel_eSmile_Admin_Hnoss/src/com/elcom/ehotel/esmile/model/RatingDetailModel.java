package com.elcom.ehotel.esmile.model;

public class RatingDetailModel {
	private String id = "";
	private String rating = "";
	private String status = "";
	private String index = "";
	private String langid = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RatingDetailModel [id=" + id + ", rating=" + rating + ", langid=" + langid + ", index=" + index + ", status=" + status
				+ "]";
	}

	public RatingDetailModel(String id, String rating, String status, String index, String langid) {
		super();
		this.id = id;
		this.rating = rating;
		this.status = status;
		this.index = index;
		this.langid = langid;
	}

	public RatingDetailModel() {
		super();
	}

}
