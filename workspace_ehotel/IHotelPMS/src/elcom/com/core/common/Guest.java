package elcom.com.core.common;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
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

    public Guest() {}


    public String getArrival() {
        return arrival;
    }

    public String getCompany() {
        return company;
    }

    public String getDepature() {
        return depature;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getIdPhieuDatPhong() {
        return idPhieuDatPhong;
    }

    public String getGuestTitle() {
        return guestTitle;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDepature(String depature) {
        this.depature = depature;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public void setIdPhieuDatPhong(String idPhieuDatPhong) {
        this.idPhieuDatPhong = idPhieuDatPhong;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setGuestTitle(String guestTitle) {
        this.guestTitle = guestTitle;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String toString() {
        return this.name + ":" + this.company + ":" + this.arrival + ":"
               +"department="+ this.depature + " phone=" + this.phone;

    }

}
