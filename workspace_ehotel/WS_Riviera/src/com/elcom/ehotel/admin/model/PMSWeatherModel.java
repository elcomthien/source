package com.elcom.ehotel.admin.model;

public class PMSWeatherModel {
	private String id = "";
	private String name = "";
	private String def = "";
	private String country = "";
	private String iscurrent = "";
	private String location = "";
	private String index = "";
	private String image = "";
	private String status = "";

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

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIscurrent() {
		return iscurrent;
	}

	public void setIscurrent(String iscurrent) {
		this.iscurrent = iscurrent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PMSWeatherModel [id=" + id + ", name=" + name + ", def=" + def + ", country=" + country + ", iscurrent=" + iscurrent + ", location="
				+ location + ", index=" + index + ", image=" + image + ", status=" + status + "]";
	}

}
