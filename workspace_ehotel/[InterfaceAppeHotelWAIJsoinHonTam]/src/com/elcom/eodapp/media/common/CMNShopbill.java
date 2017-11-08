package com.elcom.eodapp.media.common;

public class CMNShopbill {
	private int ICode;
	private int mainMenuId = -1;
	private int menuID;
	private String name;
	private int IQty;
	private String sizeType;
	private String orderTime;
	private String orderDate;
	// gia tri phuc vu xu ly order
	private int checkNo = -1;
	//
	private String guestName;

	public String toString() {
		return "CMNShopbill[Icode=" + ICode + ",name=" + name + ",IQty=" + IQty
				+ ",orderTime=" + orderTime + ",orderDate=" + orderDate + "]";
	}

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public CMNShopbill() {
	}

	public int getICode() {
		return ICode;
	}

	public void setICode(int iCode) {
		ICode = iCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIQty() {
		return IQty;
	}

	public void setIQty(int iQty) {
		IQty = iQty;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public int getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}

	public int getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(int mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

}
