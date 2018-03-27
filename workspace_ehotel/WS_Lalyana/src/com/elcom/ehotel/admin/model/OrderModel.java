package com.elcom.ehotel.admin.model;

public class OrderModel {

	private String id = "";
	private String service = "";
	private String name = "";
	private String amount = "";
	private String price = "";
	private String room = "";
	private String orderdate = "";
	private String implementation = "";
	private String status = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getImplementation() {
		return implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderModel [id=" + id + ", service=" + service + ", name=" + name + ", amount=" + amount + ", price=" + price + ", room="
				+ room + ", orderdate=" + orderdate + ", implementation=" + implementation + ", status=" + status + "]";
	}

	public OrderModel(String id, String service, String name, String amount, String price, String room, String orderdate,
			String implementation, String status) {
		super();
		this.id = id;
		this.service = service;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.room = room;
		this.orderdate = orderdate;
		this.implementation = implementation;
		this.status = status;
	}

	public OrderModel() {
		super();
	}

}
