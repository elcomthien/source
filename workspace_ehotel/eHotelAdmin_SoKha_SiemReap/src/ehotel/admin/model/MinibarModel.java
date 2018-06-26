package ehotel.admin.model;

public class MinibarModel {
	private String id = "";
	private String name = "";
	private String code = "";
	private String amount = "";
	private String price = "";
	private String status = "";
	private String modifydate = "";
	private String usermodify = "";

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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public String getUsermodify() {
		return usermodify;
	}

	public void setUsermodify(String usermodify) {
		this.usermodify = usermodify;
	}

	@Override
	public String toString() {
		return "MinibarModel [id=" + id + ", name=" + name + ", code=" + code
				+ ", amount=" + amount + ", price=" + price + ", status="
				+ status + ", modifydate=" + modifydate + ", usermodify="
				+ usermodify + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
