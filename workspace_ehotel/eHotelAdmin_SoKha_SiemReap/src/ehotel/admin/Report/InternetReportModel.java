package ehotel.admin.Report;

public class InternetReportModel {
	private String id = "";
	private String room = "";
	private String serinumber = "";
	private String price = "";
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

	public String getSerinumber() {
		return serinumber;
	}

	public void setSerinumber(String serinumber) {
		this.serinumber = serinumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "InternetReportModel [id=" + id + ", room=" + room + ", serinumber=" + serinumber + ", price=" + price + ", time=" + time
				+ "]";
	}

}
