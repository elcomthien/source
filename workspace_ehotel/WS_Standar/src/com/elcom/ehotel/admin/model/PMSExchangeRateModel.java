package com.elcom.ehotel.admin.model;

public class PMSExchangeRateModel {
	private String id = "";
	private String name = "";
	private String code = "";
	private String buy = "";
	private String sell = "";
	private String transfer = "";
	private String image = "";
	private String icon = "";
	private String invisible = "";
	private String index = "";

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getSell() {
		return sell;
	}

	public void setSell(String sell) {
		this.sell = sell;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "PMSExchangeRateModel [id=" + id + ", name=" + name + ", code=" + code + ", buy=" + buy + ", sell=" + sell + ", transfer=" + transfer
				+ ", image=" + image + ", icon=" + icon + ", invisible=" + invisible + ", index=" + index + "]";
	}
}
