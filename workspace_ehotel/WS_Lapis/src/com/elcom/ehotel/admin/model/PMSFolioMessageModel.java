package com.elcom.ehotel.admin.model;

public class PMSFolioMessageModel {
	private String roomId = "";
	private String messageId = "";
	private String clientId = "";
	private String title = "";
	private String content = "";
	private String top = "";
	private String bottom = "";
	private String sender = "";
	private String dateSend = "";
	private String dateRead = "";
	private String isRead = "";
	private String status = "";
	private String langId = "";

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDateSend() {
		return dateSend;
	}

	public void setDateSend(String dateSend) {
		this.dateSend = dateSend;
	}

	public String getDateRead() {
		return dateRead;
	}

	public void setDateRead(String dateRead) {
		this.dateRead = dateRead;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	@Override
	public String toString() {
		return "PMSFolioMessageModel [roomId=" + roomId + ", messageId=" + messageId + ", clientId=" + clientId + ", title=" + title + ", content="
				+ content + ", top=" + top + ", bottom=" + bottom + ", sender=" + sender + ", dateSend=" + dateSend + ", dateRead=" + dateRead
				+ ", isRead=" + isRead + ", status=" + status + ", langId=" + langId + "]";
	}

}
