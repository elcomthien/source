package ehotel.admin.Report;

public class RoomServiceOrderModel {
	private String id = "";
	private String name = "";
	private String orderdate = "";
	private String quantity = "";
	private String currency = "";
	private String amount = "";
	private String guest = "";
	private String room = "";
	private String checkin = "";
	private String checkout = "";

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

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
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

	@Override
	public String toString() {
		return "RoomServiceOrderModel [id=" + id + ", name=" + name
				+ ", orderdate=" + orderdate + ", quantity=" + quantity
				+ ", currency=" + currency + ", amount=" + amount + ", guest="
				+ guest + ", room=" + room + ", checkin=" + checkin
				+ ", checkout=" + checkout + "]";
	}

}
