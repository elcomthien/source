package com.elcom.ehotel.admin.model;

public class PMSFolioGuestModel {
	private String folionum = "";
	private String firstname = "";
	private String lastname = "";
	private String clientname = "";
	private String clientid = "";
	private String pincode = "";
	private String idreservation = "";
	private String arrival = "";
	private String department = "";
	private String roomsharer = "";

	public String getFolionum() {
		return folionum;
	}

	public void setFolionum(String folionum) {
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

	public String getIdreservation() {
		return idreservation;
	}

	public void setIdreservation(String idreservation) {
		this.idreservation = idreservation;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRoomsharer() {
		return roomsharer;
	}

	public void setRoomsharer(String roomsharer) {
		this.roomsharer = roomsharer;
	}

	@Override
	public String toString() {
		return "PMSFolioGuestModel [folionum=" + folionum + ", firstname="
				+ firstname + ", lastname=" + lastname + ", clientname="
				+ clientname + ", clientid=" + clientid + ", pincode="
				+ pincode + ", idreservation=" + idreservation + ", arrival="
				+ arrival + ", department=" + department + ", roomsharer="
				+ roomsharer + "]";
	}

}
