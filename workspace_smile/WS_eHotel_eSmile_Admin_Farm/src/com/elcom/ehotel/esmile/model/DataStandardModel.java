package com.elcom.ehotel.esmile.model;

public class DataStandardModel {
	private String store_id = "";
	private String store_name = "";
	private String employee_id = "";
	private String employee_name = "";
	private String smile_id = "";
	private String smile_name = "";
	private String rating = "";
	private String date = "";
	private String rating_child = "";

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getSmile_id() {
		return smile_id;
	}

	public void setSmile_id(String smile_id) {
		this.smile_id = smile_id;
	}

	public String getSmile_name() {
		return smile_name;
	}

	public void setSmile_name(String smile_name) {
		this.smile_name = smile_name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DataStandardModel(String store_id, String store_name, String employee_id, String employee_name, String smile_id,
			String smile_name, String rating, String date, String rating_child) {
		super();
		this.store_id = store_id;
		this.store_name = store_name;
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.smile_id = smile_id;
		this.smile_name = smile_name;
		this.rating = rating;
		this.date = date;
		this.rating_child = rating_child;
	}

	public String getRating_child() {
		return rating_child;
	}

	public void setRating_child(String rating_child) {
		this.rating_child = rating_child;
	}

	@Override
	public String toString() {
		return "DataStandardModel [store_id=" + store_id + ", store_name=" + store_name + ", employee_id=" + employee_id
				+ ", employee_name=" + employee_name + ", smile_id=" + smile_id + ", smile_name=" + smile_name + ", rating=" + rating
				+ ", date=" + date + ", rating_child=" + rating_child + "]";
	}

	public DataStandardModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataStandardModel(String store_id, String store_name, String employee_id, String employee_name, String smile_id,
			String smile_name, String rating, String date) {
		super();
		this.store_id = store_id;
		this.store_name = store_name;
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.smile_id = smile_id;
		this.smile_name = smile_name;
		this.rating = rating;
		this.date = date;
	}

}
