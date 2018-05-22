package ehotel.admin.Report;

public class MovieDetailModel {
	private String id = "";
	private String title = "";
	private String upload = "";
	private String countsub = "";
	private String langsub = "";
	private String price = "";
	private String currency = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getCountsub() {
		return countsub;
	}

	public void setCountsub(String countsub) {
		this.countsub = countsub;
	}

	public String getLangsub() {
		return langsub;
	}

	public void setLangsub(String langsub) {
		this.langsub = langsub;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "MovieDetailModel [id=" + id + ", title=" + title + ", upload="
				+ upload + ", countsub=" + countsub + ", langsub=" + langsub
				+ ", price=" + price + ", currency=" + currency + "]";
	}
}
