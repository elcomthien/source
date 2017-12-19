package com.elcom.ehotel.esmile.model;

import java.util.List;

public class ObjShipSchedule {
	String id, name;
	List<String> times;

	public ObjShipSchedule(String id, String name, List<String> times) {
		this.id = id;
		this.name = name;
		this.times = times;
	}

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

	public List<String> getTimes() {
		return times;
	}

	public void setTimes(List<String> times) {
		this.times = times;
	}

}
