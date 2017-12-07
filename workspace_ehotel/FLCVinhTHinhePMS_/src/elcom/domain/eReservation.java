package elcom.domain;

public class eReservation {
	private String smartcard;
	private int sId = 0;// IMainMenu : xac dinh la dich vu (serviceId)
	private int menuId = 0; // su dung loai dich v\u1EE5 phòng nào(ROMID,
							// HOTELID,SPAID,GYMID....)option
	private String note;
	private int guestNum = 1;
	private int childNum = 0;
	private String seatView;// vi tri ngoi
	private String date; // dd/mm/yyyy
	private String opentTime;// hh:mm:ss
	private int warningTime = 0;
	private String pinCode;
	private String smartCard;

	public eReservation() {
	}

	public String getSmartCard() {
		return smartCard;
	}

	public void setSmartCard(String smartCard) {
		this.smartCard = smartCard;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getGuestNum() {
		return guestNum;
	}

	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public String getSeatView() {
		return seatView;
	}

	public void setSeatView(String seatView) {
		this.seatView = seatView;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSmartcard() {
		return smartcard;
	}

	public void setSmartcard(String smartcard) {
		this.smartcard = smartcard;
	}

	public String getOpentTime() {
		return opentTime;
	}

	public void setOpentTime(String opentTime) {
		this.opentTime = opentTime;
	}

	public int getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(int warningTime) {
		this.warningTime = warningTime;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

}
