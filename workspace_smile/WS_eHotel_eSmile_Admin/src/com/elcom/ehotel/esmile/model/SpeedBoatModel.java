package com.elcom.ehotel.esmile.model;

import java.util.List;

public class SpeedBoatModel {
	private String id = "";
	private String name = "";
	private String invisible = "";
	private String index = "";
	private List<BoatTimeModel> times = null;

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

	public List<BoatTimeModel> getTimes() {
		return times;
	}

	public void setTimes(List<BoatTimeModel> times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return "SpeedBoatModel [id=" + id + ", name=" + name + ", invisible=" + invisible + ", index=" + index + ", times=" + times + "]";
	}

}
