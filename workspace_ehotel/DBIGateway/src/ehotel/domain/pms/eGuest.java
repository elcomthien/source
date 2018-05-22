package ehotel.domain.pms;

public class eGuest {
	private int guestId;
	private String name;
	private String folioNum;
	private String arrivalDate;
	private String departmentDate;
	private int roomshare;// 0=mainguest,1=guest share
	private int status;// 1=inhouse,2=checkout

	public eGuest() {
	}

	public String toString() {
		return "eGuest[guestId=" + guestId + ",name=" + name + ",roomshare="
				+ roomshare + "]";
	}

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolioNum() {
		return folioNum;
	}

	public void setFolioNum(String folioNum) {
		this.folioNum = folioNum;
	}

	public int getRoomshare() {
		return roomshare;
	}

	public void setRoomshare(int roomshare) {
		this.roomshare = roomshare;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepartmentDate() {
		return departmentDate;
	}

	public void setDepartmentDate(String departmentDate) {
		this.departmentDate = departmentDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
