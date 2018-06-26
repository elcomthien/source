package com.elcom.eodapp.media.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class eItemDining {
	String itemCode;
	String itemNname;
	String printName;
	String itemDef;
	String menuno;
	String itemCurrency;
	String currencySmall;
	String currencyLagre;
	String itemUnit;
	String urlImage;
	String urlBg;
	String urlIcon;
	List<HashMap<String, String>> detail = new ArrayList<HashMap<String, String>>();

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemNname() {
		return itemNname;
	}

	public void setItemNname(String itemNname) {
		this.itemNname = itemNname;
	}

	public String getPrintName() {
		return printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
	}

	public String getItemDef() {
		return itemDef;
	}

	public void setItemDef(String itemDef) {
		this.itemDef = itemDef;
	}

	public String getMenuno() {
		return menuno;
	}

	public void setMenuno(String menuno) {
		this.menuno = menuno;
	}

	public String getItemCurrency() {
		return itemCurrency;
	}

	public void setItemCurrency(String itemCurrency) {
		this.itemCurrency = itemCurrency;
	}

	public String getCurrencySmall() {
		return currencySmall;
	}

	public void setCurrencySmall(String currencySmall) {
		this.currencySmall = currencySmall;
	}

	public String getCurrencyLagre() {
		return currencyLagre;
	}

	public void setCurrencyLagre(String currencyLagre) {
		this.currencyLagre = currencyLagre;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlBg() {
		return urlBg;
	}

	public void setUrlBg(String urlBg) {
		this.urlBg = urlBg;
	}

	public String getUrlIcon() {
		return urlIcon;
	}

	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}

	public List<HashMap<String, String>> getDetail() {
		return detail;
	}

	public void setDetail(List<HashMap<String, String>> detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "eItemDining [itemCode=" + itemCode + ", itemNname=" + itemNname + ", printName=" + printName + ", itemDef=" + itemDef
				+ ", menuno=" + menuno + ", itemCurrency=" + itemCurrency + ", currencySmall=" + currencySmall + ", currencyLagre="
				+ currencyLagre + ", itemUnit=" + itemUnit + ", urlImage=" + urlImage + ", urlBg=" + urlBg + ", urlIcon=" + urlIcon
				+ ", detail=" + detail + "]";
	}

}
