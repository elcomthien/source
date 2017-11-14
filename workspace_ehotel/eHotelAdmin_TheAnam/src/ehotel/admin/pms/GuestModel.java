package ehotel.admin.pms;

public class GuestModel {
	private String folionum;
	private String firstname;
	private String lastname;
	private String clientname;
	private String arrivaltime;
	private String departmenttime;
	private String clientstatus;
	private String clientid;
	private String pincode;
	private String roomshare;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getDepartmenttime() {
		return departmenttime;
	}

	public void setDepartmenttime(String departmenttime) {
		this.departmenttime = departmenttime;
	}

	public String getFolionum() {
		return folionum;
	}

	public void setFolionum(String folionum) {
		this.folionum = folionum;
	}

	public String getClientstatus() {
		return clientstatus;
	}

	public void setClientstatus(String clientstatus) {
		this.clientstatus = clientstatus;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getRoomshare() {
		return roomshare;
	}

	public void setRoomshare(String roomshare) {
		this.roomshare = roomshare;
	}

	public GuestModel() {
		super();
	}

	@Override
	public String toString() {
		return "GuestModel [folionum=" + folionum + ", firstname=" + firstname + ", lastname=" + lastname + ", clientname=" + clientname
				+ ", arrivaltime=" + arrivaltime + ", departmenttime=" + departmenttime + ", clientstatus=" + clientstatus + ", clientid=" + clientid
				+ ", pincode=" + pincode + ", roomshare=" + roomshare + "]";
	}

}
