package com.elcom.ehotel.esmile.model;

import java.util.Arrays;

public class SelectedPostRatingModel {
	private String object_id = "";
	private String object_type = "";
	private String[] selected_id = null;
	private String[] selected_value = null;
	private String[] point = null;

	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public String[] getSelected_id() {
		return selected_id;
	}

	public void setSelected_id(String[] selected_id) {
		this.selected_id = selected_id;
	}

	public String[] getSelected_value() {
		return selected_value;
	}

	public void setSelected_value(String[] selected_value) {
		this.selected_value = selected_value;
	}

	public String[] getPoint() {
		return point;
	}

	public void setPoint(String[] point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "SelectedPostRatingModel [object_id=" + object_id + ", object_type=" + object_type + ", selected_id="
				+ Arrays.toString(selected_id) + ", selected_value=" + Arrays.toString(selected_value) + ", point="
				+ Arrays.toString(point) + "]";
	}

}
