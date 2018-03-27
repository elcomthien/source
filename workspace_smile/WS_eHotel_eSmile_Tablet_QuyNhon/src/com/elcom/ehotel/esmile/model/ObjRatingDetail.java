package com.elcom.ehotel.esmile.model;

public class ObjRatingDetail {

	String id, langid, rating;

	public ObjRatingDetail(String id, String langid, String rating) {
		this.id = id;
		this.langid = langid;
		this.rating = rating;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	

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

	@Override
	public String toString() {
		return "Rating Detail Object [id=" + id + ", langid=" + langid + ", rating=" + rating + "]";
	}

}
