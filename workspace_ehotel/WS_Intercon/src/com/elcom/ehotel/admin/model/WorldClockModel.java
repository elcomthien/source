package com.elcom.ehotel.admin.model;

public class WorldClockModel {
	private String id = "";
	private String city = "";
	private String national = "";
	private String timezone = "";
	private String status = "";
	private String index = "";
	private String image = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "WorldClockModel [id=" + id + ", city=" + city + ", national=" + national + ", timezone=" + timezone + ", status=" + status
				+ ", index=" + index + ", image=" + image + "]";
	}

	public WorldClockModel(String id, String city, String national, String timezone, String status, String index, String image) {
		super();
		this.id = id;
		this.city = city;
		this.national = national;
		this.timezone = timezone;
		this.status = status;
		this.index = index;
		this.image = image;
	}

	public WorldClockModel(String city, String national, String timezone, String image) {
		super();
		this.city = city;
		this.national = national;
		this.timezone = timezone;
		this.image = image;
	}

	public WorldClockModel() {
		super();
	}

}
