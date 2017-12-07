package elcom.domain;

public class OrderRes {
	private int checkNo;// -1 order false; >0 success
	private int alertFlag;// 0 = alert not open; 1=alert is open

	public OrderRes() {

	}

	public int getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}

	public int getAlertFlag() {
		return alertFlag;
	}

	public void setAlertFlag(int alertFlag) {
		this.alertFlag = alertFlag;
	}

}
