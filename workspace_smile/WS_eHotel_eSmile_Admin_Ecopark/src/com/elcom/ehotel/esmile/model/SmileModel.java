package com.elcom.ehotel.esmile.model;

import java.util.ArrayList;
import java.util.List;

public class SmileModel {
	private String id = "";
	private String name = "";
	private String department_id = "";
	private String department_name = "";
	private String image = "";
	private String num = "";
	private String sum = "";
	private List<RatingModel> rating = new ArrayList<>();

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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "SmileModel [id=" + id + ", name=" + name + ", department_id=" + department_id + ", department_name=" + department_name
				+ ", image=" + image + ", num=" + num + ", sum=" + sum + ", rating=" + rating + "]";
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public SmileModel(String id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public SmileModel() {
		super();
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public List<RatingModel> getRating() {
		return rating;
	}

	public void setRating(List<RatingModel> rating) {
		this.rating = rating;
	}

}
