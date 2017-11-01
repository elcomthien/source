package com.elcom.ehotel.esmile.model;

import java.util.HashMap;
import java.util.List;

public class AllRatingModel {
	private String smile_id = "";
	private String name = "";
	private List<RatingModel> rating = null;
	private List<HashMap<String, String>> comment = null;

	public List<HashMap<String, String>> getComment() {
		return comment;
	}

	public void setComment(List<HashMap<String, String>> comment) {
		this.comment = comment;
	}

	public String getSmile_id() {
		return smile_id;
	}

	public void setSmile_id(String smile_id) {
		this.smile_id = smile_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RatingModel> getRating() {
		return rating;
	}

	public void setRating(List<RatingModel> rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "AllRatingModel [smile_id=" + smile_id + ", name=" + name + ", rating=" + rating + ", comment=" + comment + "]";
	}

}
