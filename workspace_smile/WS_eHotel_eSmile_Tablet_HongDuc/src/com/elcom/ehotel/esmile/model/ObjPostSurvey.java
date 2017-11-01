package com.elcom.ehotel.esmile.model;

public class ObjPostSurvey {
	String id_survey, id_smile;
	
	public ObjPostSurvey(String id_survey, String id_smile)
	{
		this.id_survey=id_survey;
		this.id_smile=id_smile;
	}

	public String getId_survey() {
		return id_survey;
	}

	public void setId_survey(String id_survey) {
		this.id_survey = id_survey;
	}

	public String getId_smile() {
		return id_smile;
	}

	public void setId_smile(String id_smile) {
		this.id_smile = id_smile;
	}
	
	
}
