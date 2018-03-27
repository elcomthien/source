package com.elcom.ehotel.esmile.model;

public class CommentModel {
	private String id = "";
	private String room = "";
	private String guest = "";
	private String comment = "";
	private String time = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public CommentModel(String id, String room, String guest, String comment, String time) {
		super();
		this.id = id;
		this.room = room;
		this.guest = guest;
		this.comment = comment;
		this.time = time;
	}

	public CommentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CommentModel [id=" + id + ", room=" + room + ", guest=" + guest + ", comment=" + comment + ", time=" + time + "]";
	}

}
