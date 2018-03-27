package com.elcom.ehotel.esmile.model;

import java.util.List;

public class InfoModel {
	String logo;
	List<ObjLang> arrlang;
	List<ObjSmile> arrsmile;
	List<ObjRating> arrrating;
	List<ObjWelcome> arrwelcome;
	List<String> arrbackground;
	List<ObjPromotion> arrpromotion;

	public InfoModel(String logo, List<ObjLang> arrlang, List<ObjSmile> arrsmile, List<ObjRating> arrrating,
			List<ObjWelcome> arrwelcome, List<String> arrbackground, List<ObjPromotion> arrpromotion) {
		this.logo = logo;
		this.arrlang = arrlang;
		this.arrsmile = arrsmile;
		this.arrrating = arrrating;
		this.arrwelcome = arrwelcome;
		this.arrbackground = arrbackground;
		this.arrpromotion=arrpromotion;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<ObjLang> getArrlang() {
		return arrlang;
	}

	public void setArrlang(List<ObjLang> arrlang) {
		this.arrlang = arrlang;
	}

	public List<ObjSmile> getArrsmile() {
		return arrsmile;
	}

	public void setArrsmile(List<ObjSmile> arrsmile) {
		this.arrsmile = arrsmile;
	}

	public List<ObjRating> getArrrating() {
		return arrrating;
	}

	public void setArrrating(List<ObjRating> arrrating) {
		this.arrrating = arrrating;
	}

	public List<ObjWelcome> getArrwelcome() {
		return arrwelcome;
	}

	public void setArrwelcome(List<ObjWelcome> arrwelcome) {
		this.arrwelcome = arrwelcome;
	}

	public List<String> getArrbackground() {
		return arrbackground;
	}

	public void setArrbackground(List<String> arrbackground) {
		this.arrbackground = arrbackground;
	}

	@Override
	public String toString() {
		return "InfoModel [logo=" + logo + ", arrlang=" + arrlang + ", arrsmile=" + arrsmile + ", arrrating="
				+ arrrating + ", arrwelcome=" + arrwelcome + ", arrbackround=" + arrbackground 
				+ ", arrpromotion=" + arrpromotion+ "]";
	}

	public List<ObjPromotion> getArrpromotion() {
		return arrpromotion;
	}

	public void setArrpromotion(List<ObjPromotion> arrpromotion) {
		this.arrpromotion = arrpromotion;
	}
	
	
}
