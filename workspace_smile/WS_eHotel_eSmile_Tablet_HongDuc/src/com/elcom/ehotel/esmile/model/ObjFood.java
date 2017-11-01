package com.elcom.ehotel.esmile.model;

public class ObjFood {
	String id, name, detail, image, price, unit;
	
	public ObjFood(String id,String name,String detail,String image,String price,String unit)
	{
		this.id=id;
		this.name=name;
		this.detail=detail;
		this.price=price;
		this.unit=unit;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
