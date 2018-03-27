package com.elcom.ehotel.esmile.model;

import java.util.ArrayList;
import java.util.List;

public class DataPostRatingModel {
	private String page_id = "";
	private List<SelectedPostRatingModel> selected = new ArrayList<SelectedPostRatingModel>();

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

	@Override
	public String toString() {
		return "DataPostRatingModel [page_id=" + page_id + ", selected=" + selected + "]";
	}

}
