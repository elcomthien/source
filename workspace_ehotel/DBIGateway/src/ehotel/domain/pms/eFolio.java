package ehotel.domain.pms;

public class eFolio {
	private int folioNum;
	private String folioCode;
	private String folioType;
	private String smartCard;

	private int status;

	public eFolio() {
	}

	public String toString() {
		return "eFolio[folioNum=" + folioNum + ",smartcard=" + smartCard
				+ ",status=" + status + "]";
	}

	public String getSmartCard() {
		return smartCard;
	}

	public void setSmartCard(String smartCard) {
		this.smartCard = smartCard;
	}

	public int getFolioNum() {
		return folioNum;
	}

	public void setFolioNum(int folioNum) {
		this.folioNum = folioNum;
	}

	public String getFolioCode() {
		return folioCode;
	}

	public void setFolioCode(String folioCode) {
		this.folioCode = folioCode;
	}

	public String getFolioType() {
		return folioType;
	}

	public void setFolioType(String folioType) {
		this.folioType = folioType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
