package com.elcom.ehotel.esmile.model;

import java.util.List;

public class SmileRatingModel {
	private String id = "";
	private String name = "";
	private List<RatingModel> rating = null;

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

	public List<RatingModel> getRating() {
		return rating;
	}

	public void setRating(List<RatingModel> rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "SmileRatingModel [id=" + id + ", name=" + name + ", rating=" + rating + "]";
	}

}
