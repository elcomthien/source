package com.elcom.ehotel.esmile.model;

public class ObjNotify {
	String id, name, status, time, location, folio, guest, checkin, checkout;

	public ObjNotify(String id, String name, String status, String time, String location, String folio, String guest,
			String checkin, String checkout) {
		this.id = id;
		this.name = name == null ? "" : name;
		this.status = status == null ? "" : status;
		this.time = time == null ? "" : time;
		this.location = location == null ? "" : location;
		this.folio = folio == null ? "" : folio;
		this.guest = guest == null ? "" : guest;
		this.checkin = checkin == null ? "" : checkin;
		this.checkout = checkout == null ? "" : checkout;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
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

}
