package com.elcom.ehotel.esmile.model;

import java.util.List;

public class SurveyModel {
	List<ObjSmile> smile;
	List<ObjRating> survey;
	
	public SurveyModel(List<ObjSmile> arrsmile, List<ObjRating> arrrating) {
		this.smile = arrsmile;
		this.survey = arrrating;
	}

	public List<ObjSmile> getArrsmile() {
		return smile;
	}

	public void setArrsmile(List<ObjSmile> arrsmile) {
		this.smile = arrsmile;
	}

	public List<ObjRating> getArrrating() {
		return survey;
	}

	public void setArrrating(List<ObjRating> arrrating) {
		this.survey = arrrating;
	}
	
	
}
