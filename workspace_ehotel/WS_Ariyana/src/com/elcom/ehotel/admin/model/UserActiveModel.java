package com.elcom.ehotel.admin.model;

public class UserActiveModel {

	private String id = "";
	private String description = "";
	private String datetime = "";
	private String username = "";
	private String subject = "";

	@Override
	public String toString() {
		return "UserActivityModel [id=" + id + ", description=" + description + ", datetime=" + datetime + ", username=" + username
				+ ", subject=" + subject + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
