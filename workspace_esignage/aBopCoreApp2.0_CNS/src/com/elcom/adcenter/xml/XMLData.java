package com.elcom.adcenter.xml;

public class XMLData {
	private String tagName;
	private String tagValue;
	private boolean isCDATA;
	private boolean isAttribute;

	public XMLData() {

	}

	public XMLData(String tagName, String tagValue, boolean isCData,
			boolean isAttribute) {
		this.tagName = tagName;
		this.tagValue = tagValue;
		this.isCDATA = isCData;
		this.isAttribute = isAttribute;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public boolean isCDATA() {
		return isCDATA;
	}

	public void setCDATA(boolean isCDATA) {
		this.isCDATA = isCDATA;
	}

	public boolean isAttribute() {
		return isAttribute;
	}

	public void setAttribute(boolean isAttribute) {
		this.isAttribute = isAttribute;
	}

}
