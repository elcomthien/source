package com.elcom.ehotel.esmile.model;

public class ObjExchange {
	String id, name, desc, image, buy, transfer, sale, status, code;

	public ObjExchange(String id, String name, String code, String desc, String image, String buy, String transfer,
			String sale, String status) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.image = image;
		this.buy = buy;
		this.transfer = transfer;
		this.sale = sale;
		this.status = status;
		this.code = code;
	}

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
