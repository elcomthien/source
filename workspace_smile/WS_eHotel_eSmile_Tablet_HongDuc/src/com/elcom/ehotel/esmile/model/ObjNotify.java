package com.elcom.ehotel.esmile.model;

public class ObjNotify {
	String id, name, status, time, location, folio, guest, checkin, checkout, speciality_id, speciality_name;

	public ObjNotify(String id, String name, String status, String time, String location, String folio, String guest, String checkin,
			String checkout, String speciality_id, String speciality_name) {
		this.id = id;
		this.name = name == null ? "" : name;
		this.status = status == null ? "" : status;
		this.time = time == null ? "" : time;
		this.location = location == null ? "" : location;
		this.folio = folio == null ? "" : folio;
		this.guest = guest == null ? "" : guest;
		this.checkin = checkin == null ? "" : checkin;
		this.checkout = checkout == null ? "" : checkout;
		this.speciality_id = speciality_id == null ? "" : speciality_id;
		this.speciality_name = speciality_name == null ? "" : speciality_name;

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

	public String getSpeciality_id() {
		return speciality_id;
	}

	public void setSpeciality_id(String speciality_id) {
		this.speciality_id = speciality_id;
	}

	public String getSpeciality_name() {
		return speciality_name;
	}

	public void setSpeciality_name(String speciality_name) {
		this.speciality_name = speciality_name;
	}

	@Override
	public String toString() {
		return "ObjNotify [id=" + id + ", name=" + name + ", status=" + status + ", time=" + time + ", location=" + location + ", folio="
				+ folio + ", guest=" + guest + ", checkin=" + checkin + ", checkout=" + checkout + ", speciality_id=" + speciality_id
				+ ", speciality_name=" + speciality_name + "]";
	}

}
