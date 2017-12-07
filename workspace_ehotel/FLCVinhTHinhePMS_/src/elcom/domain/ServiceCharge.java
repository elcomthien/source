package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "item")
public class ServiceCharge {
	private int id;
	private String Folio;
	private String code;
	private String price;
	private String quanlity;
	private String startDate;
	private String startTime;

	private String amount;
	private String serviceType;
	private String cashCard; // loai hinh thuc thanh toan dich vu
	private String unit;

	public ServiceCharge() {
	}

	public String toString() {
		return "ServiceCharge[code=" + code + ",price=" + price + ",unit="
				+ unit + "]";
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "room")
	public String getFolio() {
		return Folio;
	}

	public void setFolio(String folio) {
		Folio = folio;
	}
	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuanlity() {
		return quanlity;
	}

	public void setQuanlity(String quanlity) {
		this.quanlity = quanlity;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getCashCard() {
		return cashCard;
	}

	public void setCashCard(String cashCard) {
		this.cashCard = cashCard;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
