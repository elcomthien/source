package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = { "guestId", "name", "arrival", "depature", "roomCode",
		"pinCode", "guestTitle", "company", "langCode", "phone","folioName",
		"idPhieuDatPhong" }, name = "item_guest")
@XmlRootElement(name = "item")
public class Guest {
	private String guestId;
	private String name; // ten
	private String company; // ma doan
	private String arrival; // ngay den
	private String depature; // ngay di
	private String phone; // so dien thoai
	private String roomCode; // ma phong
	private String idPhieuDatPhong;
	private String pinCode;
	private String guestTitle;
	private String langCode;
	private String folioName;

	public Guest() {

	}
    
	public String getFolioName() {
		return folioName;
	}

	public void setFolioName(String folioName) {
		this.folioName = folioName;
	}

	public Guest(String name, String roomCode, String pinCode) {
		this.name = name;
		this.roomCode = roomCode;
		this.pinCode = pinCode;
	}

	public String toString() {
		return "Guest[name=" + name + "]";
	}

	@XmlAttribute(name = "id")
	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getDepature() {
		return depature;
	}

	public void setDepature(String depature) {
		this.depature = depature;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlAttribute(name = "room_code")
	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getIdPhieuDatPhong() {
		return idPhieuDatPhong;
	}

	public void setIdPhieuDatPhong(String idPhieuDatPhong) {
		this.idPhieuDatPhong = idPhieuDatPhong;
	}

	@XmlElement(name = "pin_code")
	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@XmlElement(name = "guest_title")
	public String getGuestTitle() {
		return guestTitle;
	}

	public void setGuestTitle(String guestTitle) {
		this.guestTitle = guestTitle;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	// --------------
	private static class Adapter extends XmlAdapter {
		@Override
		public Object unmarshal(Object v) throws Exception {
			// TODO Auto-generated method stub
			return v;
		}

		@Override
		public Object marshal(Object v) throws Exception {
			// TODO Auto-generated method stub
			if (v == null)
				return "<![CDATA[  ]]>";
			else
				return "<![CDATA[" + v + "]]>";
		}

	}
}
