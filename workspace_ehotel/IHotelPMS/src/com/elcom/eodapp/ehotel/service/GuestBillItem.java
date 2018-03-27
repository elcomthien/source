package com.elcom.eodapp.ehotel.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "roomnumer", "reservationnumer", "transactionid", "transactioncode", "itemamount", "currency",
		"itemdesc", "balanceamount", "displayflag", "date", "time" })
@XmlRootElement(name = "guestBillItem")
public class GuestBillItem {

	@XmlElementRef(name = "ROOM_NUMER", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> roomnumer;
	@XmlElementRef(name = "RESERVATION_NUMER", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> reservationnumer;
	@XmlElementRef(name = "TRANSACTION_ID", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> transactionid;
	@XmlElementRef(name = "TRANSACTION_CODE", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> transactioncode;
	@XmlElementRef(name = "ITEM_AMOUNT", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> itemamount;
	@XmlElementRef(name = "CURRENCY", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> currency;
	@XmlElementRef(name = "ITEM_DESC", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> itemdesc;
	@XmlElementRef(name = "BALANCE_AMOUNT", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> balanceamount;
	@XmlElementRef(name = "DISPLAY_FLAG", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> displayflag;
	@XmlElementRef(name = "DATE", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> date;
	@XmlElementRef(name = "TIME", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
	protected JAXBElement<String> time;

	public JAXBElement<String> getROOMNUMER() {
		return roomnumer;
	}

	public void setROOMNUMER(JAXBElement<String> value) {
		this.roomnumer = value;
	}

	public JAXBElement<String> getRESERVATIONNUMER() {
		return reservationnumer;
	}

	public void setRESERVATIONNUMER(JAXBElement<String> value) {
		this.reservationnumer = value;
	}

	public JAXBElement<String> getITEMDESC() {
		return itemdesc;
	}

	public void setITEMDESC(JAXBElement<String> value) {
		this.itemdesc = value;
	}

	public JAXBElement<String> getITEMAMOUNT() {
		return itemamount;
	}

	public void setITEMAMOUNT(JAXBElement<String> value) {
		this.itemamount = value;
	}

	public JAXBElement<String> getDISPLAYFLAG() {
		return displayflag;
	}

	public void setDISPLAYFLAG(JAXBElement<String> value) {
		this.displayflag = value;
	}

	public JAXBElement<String> getDATE() {
		return date;
	}

	public void setDATE(JAXBElement<String> value) {
		this.date = value;
	}

	public JAXBElement<String> getTIME() {
		return time;
	}

	public void setTIME(JAXBElement<String> value) {
		this.time = value;
	}

	public JAXBElement<String> getTRANSACTIONID() {
		return transactionid;
	}

	public void setTRANSACTIONID(JAXBElement<String> value) {
		this.transactionid = value;
	}

	public JAXBElement<String> getTRANSACTIONCODE() {
		return transactioncode;
	}

	public void setTRANSACTIONCODE(JAXBElement<String> value) {
		this.transactioncode = value;
	}

	public JAXBElement<String> getCURRENCY() {
		return currency;
	}

	public void setCURRENCY(JAXBElement<String> value) {
		this.currency = value;
	}

	public JAXBElement<String> getBALANCEAMOUNT() {
		return balanceamount;
	}

	public void setBALANCEAMOUNT(JAXBElement<String> value) {
		this.balanceamount = value;
	}

}
