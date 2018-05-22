package ehotel.admin.pms;

public class GuestModel {
	private int folionum;
	private String firstname;
	private String lastname;
	private String clientname;
	private String arrivaltime;
	private String departmenttime;
	private int clientstatus;
	private int clientid;
	private int pincode;
	private int roomshare;

	public int getFolionum() {
		return folionum;
	}

	public void setFolionum(int folionum) {
		this.folionum = folionum;
	}

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

	public int getClientstatus() {
		return clientstatus;
	}

	public void setClientstatus(int clientstatus) {
		this.clientstatus = clientstatus;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getRoomshare() {
		return roomshare;
	}

	public void setRoomshare(int roomshare) {
		this.roomshare = roomshare;
	}

	public GuestModel(int folionum, String firstname, String lastname, String clientname, String arrivaltime, String departmenttime,
			int clientstatus, int clientid, int pincode, int roomshare) {
		super();
		this.folionum = folionum;
		this.firstname = firstname;
		this.lastname = lastname;
		this.clientname = clientname;
		this.arrivaltime = arrivaltime;
		this.departmenttime = departmenttime;
		this.clientstatus = clientstatus;
		this.clientid = clientid;
		this.pincode = pincode;
		this.roomshare = roomshare;
	}

	public GuestModel() {
		super();
	}

	@Override
	public String toString() {
		return "GuestModel [folionum=" + folionum + ", firstname=" + firstname + ", lastname=" + lastname + ", clientname=" + clientname
				+ ", arrivaltime=" + arrivaltime + ", departmenttime=" + departmenttime + ", clientstatus=" + clientstatus + ", clientid="
				+ clientid + ", pincode=" + pincode + ", roomshare=" + roomshare + "]";
	}

}
