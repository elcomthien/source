package com.elcom.ehotel.esmile.model;

import java.util.HashMap;
import java.util.List;

public class SurveyModel {
	private List<HashMap<String, String>> smile = null;
	private List<HashMap<String, String>> survey = null;

	public List<HashMap<String, String>> getSmile() {
		return smile;
	}

	public void setSmile(List<HashMap<String, String>> smile) {
		this.smile = smile;
	}

	public List<HashMap<String, String>> getSurvey() {
		return survey;
	}

	public void setSurvey(List<HashMap<String, String>> survey) {
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "SurveyModel [smile=" + smile + ", survey=" + survey + "]";
	}

}
