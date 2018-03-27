package com.elcom.ehotel.esmile.model;

public class EmailModel {
	private String id = "";
	private String name = "";
	private String email = "";
	private String status = "";

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmailModel [id=" + id + ", name=" + name + ", email=" + email + ", status=" + status + "]";
	}

	public EmailModel(String id, String name, String email, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.status = status;
	}

	public EmailModel() {
		super();
	}

}
