package ehotel.admin.Report;

public class RoomReportModel {
	private String room = "";
	private String title = "";
	private String date = "";
	private String qty = "";
	private String currency = "";
	private String rate = "";

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "RoomReportModel [room=" + room + ", title=" + title + ", date="
				+ date + ", qty=" + qty + ", currency=" + currency + ", rate="
				+ rate + "]";
	}
}
