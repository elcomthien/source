package ehotel.admin.Report;

public class ServiceMenuModel {
	private String id = "";
	private String name = "";
	private String datetime = "";
	private String rate = "";
	private String curency = "";
	private String updateby = "";

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

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCurency() {
		return curency;
	}

	public void setCurency(String curency) {
		this.curency = curency;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	@Override
	public String toString() {
		return "ServiceMenuModel [id=" + id + ", name=" + name + ", datetime="
				+ datetime + ", rate=" + rate + ", cuurency=" + curency
				+ ", updateby=" + updateby + "]";
	}

}
