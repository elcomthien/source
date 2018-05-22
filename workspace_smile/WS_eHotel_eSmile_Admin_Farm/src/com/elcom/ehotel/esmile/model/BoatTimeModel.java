package com.elcom.ehotel.esmile.model;

public class BoatTimeModel {
	private String id = "";
	private String idboat = "";
	private String time = "";
	private String invisible = "";
	private String index = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdboat() {
		return idboat;
	}

	public void setIdboat(String idboat) {
		this.idboat = idboat;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "BoatTimeModel [id=" + id + ", idboat=" + idboat + ", time=" + time + ", invisible=" + invisible + ", index=" + index + "]";
	}

}
