package ehotel.domain.report;

public class DataReport {
	private String name;
	private int quanlity;
	private String price;
	private String amount;

	public DataReport() {

	}

	public String toString() {
		return "DataReport[Name=" + name + ",Qty=" + quanlity + ",price="
				+ price + ",amount=" + amount + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String filmName) {
		this.name = filmName;
	}

	public int getQuanlity() {
		return quanlity;
	}

	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
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

}
