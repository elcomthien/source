package com.elcom.eodapp.ehotel.common;

public class oPost {
	private String DATE; // DA
	private String TIME; // TI
	private String DIALED_DIGITS; // DD
	private String DURATION;// DU
	private String MINIBAR_ARTICLE;// MA
	private String NUMOF_ARTICLE;// M#
	private String TAX_PULSE;// MP
	private String POSTING_TYPE;// PT
	private String ROOM_NUMBER;// RN
	private String SALES_OUTLET;// SO
	private String POST_AMOUNT;// TA
	private String ROOM_STATUS;

	private String POST_SEQ_NUMBER;// P#
	private String CLEAR_TEXT;// CT
	private String POST_CALL_TYPE;// PC

	// Getter
	public String getDATE() {
		return DATE;
	}

	public String getTIME() {
		return TIME;
	}

	public String getDIALED_DIGITS() {
		return DIALED_DIGITS;
	}

	public String getDURATION() {
		return DURATION;
	}

	public String getMINIBAR_ARTICLE() {
		return MINIBAR_ARTICLE;
	}

	public String getNUMOF_ARTICLE() {
		return NUMOF_ARTICLE;
	}

	public String getTAX_PULSE() {
		return TAX_PULSE;
	}

	public String getPOSTING_TYPE() {
		return POSTING_TYPE;
	}

	public String getROOM_NUMBER() {
		return ROOM_NUMBER;
	}

	public String getSALES_OUTLET() {
		return SALES_OUTLET;
	}

	public String getPOST_AMOUNT() {
		return POST_AMOUNT;
	}

	public String getPOST_SEQ_NUMBER() {
		return POST_SEQ_NUMBER;
	}

	public String getCLEAR_TEXT() {
		return CLEAR_TEXT;
	}

	public String getPOST_CALL_TYPE() {
		return POST_CALL_TYPE;
	}

	// Setter
	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public void setTIME(String tIME) {
		TIME = tIME;
	}

	public void setDIALED_DIGITS(String dIALED_DIGITS) {
		DIALED_DIGITS = dIALED_DIGITS;
	}

	public void setDURATION(String dURATION) {
		DURATION = dURATION;
	}

	public void setMINIBAR_ARTICLE(String mINIBAR_ARTICLE) {
		MINIBAR_ARTICLE = mINIBAR_ARTICLE;
	}

	public void setNUMOF_ARTICLE(String nUMOF_ARTICLE) {
		NUMOF_ARTICLE = nUMOF_ARTICLE;
	}

	public void setTAX_PULSE(String tAX_PULSE) {
		TAX_PULSE = tAX_PULSE;
	}

	public void setPOSTING_TYPE(String pOSTING_TYPE) {
		POSTING_TYPE = pOSTING_TYPE;
	}

	public void setROOM_NUMBER(String rOOM_NUMBER) {
		ROOM_NUMBER = rOOM_NUMBER;
	}

	public void setSALES_OUTLET(String sALES_OUTLET) {
		SALES_OUTLET = sALES_OUTLET;
	}

	public void setPOST_AMOUNT(String pOST_AMOUNT) {
		POST_AMOUNT = pOST_AMOUNT;
	}

	public void setPOST_SEQ_NUMBER(String pOST_SEQ_NUMBER) {
		POST_SEQ_NUMBER = pOST_SEQ_NUMBER;
	}

	public void setCLEAR_TEXT(String cLEAR_TEXT) {
		CLEAR_TEXT = cLEAR_TEXT;
	}

	public void setPOST_CALL_TYPE(String pOST_CALL_TYPE) {
		POST_CALL_TYPE = pOST_CALL_TYPE;
	}

	// For Smile PMS
	private String TOTAL_AMOUNT;// TA
	private String POST_TEXT;// PT
	private String CURRENCY_TYPE;// CU

	public String getTOTAL_AMOUNT() {
		return TOTAL_AMOUNT;
	}

	public void setTOTAL_AMOUNT(String tOTAL_AMOUNT) {
		TOTAL_AMOUNT = tOTAL_AMOUNT;
	}

	public String getPOST_TEXT() {
		return POST_TEXT;
	}

	public void setPOST_TEXT(String pOST_TEXT) {
		POST_TEXT = pOST_TEXT;
	}

	public String getCURRENCY_TYPE() {
		return CURRENCY_TYPE;
	}

	public void setCURRENCY_TYPE(String cURRENCY_TYPE) {
		CURRENCY_TYPE = cURRENCY_TYPE;
	}

	public String getROOM_STATUS() {
		return ROOM_STATUS;
	}

	public void setROOM_STATUS(String rOOM_STATUS) {
		ROOM_STATUS = rOOM_STATUS;
	}
}
