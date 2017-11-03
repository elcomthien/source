package com.elcom.ehotel.admin.model;

public class VodPercentModel {
	private String filename = "";
	private String seq = "";
	private String nameview = "";
	private String uuid = "";
	private String status = "";

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "VodPercentModel [filename=" + filename + ", seq=" + seq
				+ ", nameview=" + nameview + ", uuid=" + uuid + "]";
	}

	public VodPercentModel() {
		super();
	}

	public String getNameview() {
		return nameview;
	}

	public void setNameview(String nameview) {
		this.nameview = nameview;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
