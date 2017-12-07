package ehotel.admin.pms;

public class FolioModel {
	private String folionum = "";
	private String foliocode = "";
	private String type = "";
	private String status = "";
	private String fee = "";
	private String free = "";
	private String charge = "";
	private String xxx = "";

	public String getFolionum() {
		return folionum;
	}

	public void setFolionum(String folionum) {
		this.folionum = folionum;
	}

	public String getFoliocode() {
		return foliocode;
	}

	public void setFoliocode(String foliocode) {
		this.foliocode = foliocode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getXxx() {
		return xxx;
	}

	public void setXxx(String xxx) {
		this.xxx = xxx;
	}

	@Override
	public String toString() {
		return "FolioModel [folionum=" + folionum + ", foliocode=" + foliocode
				+ ", type=" + type + ", status=" + status + ", fee=" + fee
				+ ", free=" + free + ", charge=" + charge + ", xxx=" + xxx
				+ "]";
	}

}
