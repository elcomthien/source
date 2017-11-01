package com.elcom.ehotel.esmile.model;


public class SmileModel {
	private String id = "";
	private String name = "";
	private String num = "";
	private String sum = "";

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
		return "SmileModel [id=" + id + ", name=" + name + ", num=" + num + ", sum=" + sum + "]";
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

}
