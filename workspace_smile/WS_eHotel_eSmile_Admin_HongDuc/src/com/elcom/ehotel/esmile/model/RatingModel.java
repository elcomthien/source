package com.elcom.ehotel.esmile.model;

import java.util.HashMap;

public class RatingModel {
	private String id = "";
	private String num = "";
	private String name = "";
	private String sum = "";
	private String image = "";
	private HashMap<String, String> comments = null;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, String> getComments() {
		return comments;
	}

	public void setComments(HashMap<String, String> comment) {
		this.comments = comment;
	}

	@Override
	public String toString() {
		return "RatingModel [id=" + id + ", num=" + num + ", name=" + name + ", sum=" + sum + ", comments=" + comments + "]";
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

	public RatingModel(String id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public RatingModel() {
		super();
	}

	public RatingModel(String id, String name, String num, String sum) {
		super();
		this.id = id;
		this.num = num;
		this.name = name;
		this.sum = sum;
	}

	
	
}
