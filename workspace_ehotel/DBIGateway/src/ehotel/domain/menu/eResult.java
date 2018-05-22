package ehotel.domain.menu;

public class eResult {
	private int intValue;
	private boolean booleanValue;
	private String comment;

	public eResult() {

	}

	public eResult(int intValue, boolean booleanValue, String comment) {
		this.intValue = intValue;
		this.booleanValue = booleanValue;
		this.comment = comment;
	}

	public String toString() {
		return "eResult[intValue=" + intValue + ",booleanValue=" + booleanValue
				+ ",comment=" + comment + "]";
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
