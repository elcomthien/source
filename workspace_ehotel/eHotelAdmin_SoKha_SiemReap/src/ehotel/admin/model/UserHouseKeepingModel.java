package ehotel.admin.model;

public class UserHouseKeepingModel {
	private String id = "";
	private String username = "";
	private String password = "";
	private String fullname = "";
	private String status = "";
	private String modifydate = "";
	private String usermodify = "";
	private String address = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserHouseKeepingModel [id=" + id + ", username=" + username
				+ ", password=" + password + ", fullname=" + fullname
				+ ", status=" + status + ", modifydate=" + modifydate
				+ ", usermodify=" + usermodify + ", address=" + address + "]";
	}
}
