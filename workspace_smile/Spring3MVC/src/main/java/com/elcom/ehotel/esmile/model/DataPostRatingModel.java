package com.elcom.ehotel.esmile.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataPostRatingModel {
	private String page_id = "";
	private List<SelectedPostRatingModel> selected = new ArrayList<SelectedPostRatingModel>();
	private String respondent_id = "";
	private String[] tags = {};
	private String flag = "";
	private String important = "";

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	public List<SelectedPostRatingModel> getSelected() {
		return selected;
	}

	public void setSelected(List<SelectedPostRatingModel> selected) {
		this.selected = selected;
	}

	public String getRespondent_id() {
		return respondent_id;
	}

	public void setRespondent_id(String respondent_id) {
		this.respondent_id = respondent_id;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getImportant() {
		return important;
	}

	public void setImportant(String important) {
		this.important = important;
	}

	@Override
	public String toString() {
		return "DataPostRatingModel [page_id=" + page_id + ", selected=" + selected + ", respondent_id=" + respondent_id + ", tags="
				+ Arrays.toString(tags) + ", flag=" + flag + ", important=" + important + "]";
	}

}
