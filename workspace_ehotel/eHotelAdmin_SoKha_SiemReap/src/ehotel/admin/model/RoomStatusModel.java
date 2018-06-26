package ehotel.admin.model;

public class RoomStatusModel {
	private String id = "";
	private String statusName = "";
	private String invisible = "";
	private String modifyDate = "";
	private String userModify = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUserModify() {
		return userModify;
	}

	public void setUserModify(String userModify) {
		this.userModify = userModify;
	}

	@Override
	public String toString() {
		return "RoomStatusModel [id=" + id + ", statusName=" + statusName
				+ ", invisible=" + invisible + ", modifyDate=" + modifyDate
				+ ", userModify=" + userModify + "]";
	}

}
