package com.elcom.ehotel.esmile.model;

import java.util.List;

public class ObjInfo {
	String logo;
	List<ObjLang> lang;
	List<ObjSmile> smile;
	List<ObjRatingStaff> rating;
	List<ObjRatingStaff> ratring_child;
	List<ObjPage> title;
	List<ObjWelcome> welcome;
	List<String> background;
	List<ObjPromotion> promotion;
	List<ObjRatingDetail> rating_detail;

	public ObjInfo(String logo, List<ObjLang> arrlang, List<ObjSmile> arrsmile, List<ObjRatingStaff> rating,
			List<ObjRatingStaff> ratring_child, List<ObjPage> title, List<ObjWelcome> arrwelcome,
			List<String> background, List<ObjPromotion> arrpromotion,List<ObjRatingDetail> rating_detail) {
		this.logo = logo;
		this.lang = arrlang;
		this.smile = arrsmile;
		this.rating = rating;
		this.ratring_child = ratring_child;
		this.title = title;
		this.welcome = arrwelcome;
		this.promotion=arrpromotion;
		this.background = background;
		this.rating_detail=rating_detail;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<ObjLang> getArrlang() {
		return lang;
	}

	public void setArrlang(List<ObjLang> arrlang) {
		this.lang = arrlang;
	}

	public List<ObjSmile> getArrsmile() {
		return smile;
	}

	public void setArrsmile(List<ObjSmile> arrsmile) {
		this.smile = arrsmile;
	}

	public List<ObjRatingStaff> getRating() {
		return rating;
	}

	public void setRating(List<ObjRatingStaff> rating) {
		this.rating = rating;
	}

	public List<ObjRatingStaff> getRatring_child() {
		return ratring_child;
	}

	public void setRatring_child(List<ObjRatingStaff> ratring_child) {
		this.ratring_child = ratring_child;
	}

	public List<ObjPage> getTitle() {
		return title;
	}

	public void setTitle(List<ObjPage> title) {
		this.title = title;
	}

	public List<ObjWelcome> getArrwelcome() {
		return welcome;
	}

	public void setArrwelcome(List<ObjWelcome> arrwelcome) {
		this.welcome = arrwelcome;
	}

	public List<String> getArrbackground() {
		return background;
	}

	public void setArrbackground(List<String> arrbackground) {
		this.background = arrbackground;
	}

	public List<ObjPromotion> getArrpromotion() {
		return promotion;
	}

	public void setArrpromotion(List<ObjPromotion> arrpromotion) {
		this.promotion = arrpromotion;
	}

	public List<ObjRatingDetail> getRating_detail() {
		return rating_detail;
	}

	public void setRating_detail(List<ObjRatingDetail> rating_detail) {
		this.rating_detail = rating_detail;
	}
	
	
}
