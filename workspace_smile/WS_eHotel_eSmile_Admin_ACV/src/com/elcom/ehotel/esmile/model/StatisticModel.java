package com.elcom.ehotel.esmile.model;

public class StatisticModel {
	private String date = "";
	private String excellent = "";
	private String good = "";
	private String average = "";
	private String poor = "";
	private String verypoor = "";
	private String other = "";

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExcellent() {
		return excellent;
	}

	public void setExcellent(String excellent) {
		this.excellent = excellent;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public String getPoor() {
		return poor;
	}

	public void setPoor(String poor) {
		this.poor = poor;
	}

	public String getVerypoor() {
		return verypoor;
	}

	public void setVerypoor(String verypoor) {
		this.verypoor = verypoor;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "StatisticModel [date=" + date + ", excellent=" + excellent + ", good=" + good + ", average=" + average + ", poor=" + poor
				+ ", verypoor=" + verypoor + ", other=" + other + "]";
	}

}
