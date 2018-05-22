package com.elcom.ehotel.admin.model;

public class PMSDiningDetailModel {
	private String id = "";
	private String item_id = "";
	private String detail = "";
	private String invisible = "";
	private String index = "";
	private String langid = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	@Override
	public String toString() {
		return "PMSDiningDetailModel [id=" + id + ", item_id=" + item_id + ", detail=" + detail + ", invisible=" + invisible + ", index="
				+ index + ", langid=" + langid + "]";
	}

	public PMSDiningDetailModel(String id, String item_id, String detail, String invisible, String index, String langid) {
		super();
		this.id = id;
		this.item_id = item_id;
		this.detail = detail;
		this.invisible = invisible;
		this.index = index;
		this.langid = langid;
	}

	public PMSDiningDetailModel() {
		super();
	}

}
