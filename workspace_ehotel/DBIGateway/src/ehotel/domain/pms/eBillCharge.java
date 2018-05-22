package ehotel.domain.pms;

public class eBillCharge {
	private int Id;
	private String code;
	private int quantity;
	private String price;
	private String amount;
	private String recordType;
	private String dateTime;
	private String IUnit;

	public eBillCharge() {

	}

	public String toString() {
		return "eBillCharge[id=" + Id + ",itemCode=" + code + ",Qty="
				+ quantity + ",price=" + price + ",amount=" + amount + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getIUnit() {
		return IUnit;
	}

	public void setIUnit(String iUnit) {
		IUnit = iUnit;
	}

}
