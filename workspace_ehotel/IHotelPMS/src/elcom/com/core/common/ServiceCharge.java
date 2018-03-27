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
public class ServiceCharge {
    private int    tranID;
    private String desciption;
    private String amount;
    private String amountUnit;
    private String pinCode;
    private String folioNum;
    private String guestId;
    private String idReservation;
    private String tranDate;
    private String tranTime;
    public ServiceCharge() {
    }

    public String getAmount() {
        return amount;
    }

    public String getAmountUnit() {
        return amountUnit;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getTranDate() {
        return tranDate;
    }

    public int getTranID() {
        return tranID;
    }

    public String getTranTime() {
        return tranTime;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getFolioNum() {
        return folioNum;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public void setTranID(int tranID) {
        this.tranID = tranID;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public void setFolioNum(String folioNum) {
        this.folioNum = folioNum;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }
}
