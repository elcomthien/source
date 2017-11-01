package com.elcom.ehotel.esmile.model;

public class RatingSurveyModel {
	private String id = "";
	private String name = "";
	private String poor = "";
	private String average = "";
	private String good = "";
	private String excellent = "";
	private String sum = "";

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

	public String getPoor() {
		return poor;
	}

	public void setPoor(String poor) {
		this.poor = poor;
	}

	public String getAverge() {
		return average;
	}

	public void setAverge(String averge) {
		this.average = averge;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getExcellent() {
		return excellent;
	}

	public void setExcellent(String excellent) {
		this.excellent = excellent;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "RatingSurveyModel [id=" + id + ", name=" + name + ", poor=" + poor + ", averge=" + average + ", good=" + good
				+ ", excellent=" + excellent + ", sum=" + sum + "]";
	}

}
