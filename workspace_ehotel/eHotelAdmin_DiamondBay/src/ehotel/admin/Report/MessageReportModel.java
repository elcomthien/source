package ehotel.admin.Report;

public class MessageReportModel {
	private String room = "";
	private String guest = "";
	private String checkin = "";
	private String checkout = "";
	private String create = "";
	private String read = "";
	private String sender = "";
	private String content = "";

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

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MessageReportModel [room=" + room + ", guest=" + guest
				+ ", checkin=" + checkin + ", checkout=" + checkout
				+ ", create=" + create + ", read=" + read + ", sender="
				+ sender + ", content=" + content + "]";
	}
}
