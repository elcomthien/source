package ehotel.admin.Report;

public class ProductionReportModel {
	private String title = "";
	private String times = "";
	private String currency = "";
	private String rate = "";
	private String amount = "";
	private String unique = "";
	private String pincode = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUnique() {
		return unique;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "ProductionReportModel [title=" + title + ", times=" + times
				+ ", currency=" + currency + ", rate=" + rate + ", amount="
				+ amount + ", unique=" + unique + ", pincode=" + pincode + "]";
	}
}
