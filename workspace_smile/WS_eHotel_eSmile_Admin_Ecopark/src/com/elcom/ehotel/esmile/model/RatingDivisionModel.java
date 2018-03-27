package com.elcom.ehotel.esmile.model;

import java.util.HashMap;
import java.util.List;

public class RatingDivisionModel {

	private List<RatingModel> rating_good = null;
	private List<RatingModel> rating_bad = null;
	private List<HashMap<String, String>> comment = null;
	private String count = "";

	public List<RatingModel> getRating_good() {
		return rating_good;
	}

	public void setRating_good(List<RatingModel> rating_good) {
		this.rating_good = rating_good;
	}

	public List<RatingModel> getRating_bad() {
		return rating_bad;
	}

	public void setRating_bad(List<RatingModel> rating_bad) {
		this.rating_bad = rating_bad;
	}

	public List<HashMap<String, String>> getComment() {
		return comment;
	}

	public void setComment(List<HashMap<String, String>> comment) {
		this.comment = comment;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "RatingDivisionModel [rating_good=" + rating_good
				+ ", rating_bad=" + rating_bad + ", comment=" + comment
				+ ", count=" + count + "]";
	}

}
