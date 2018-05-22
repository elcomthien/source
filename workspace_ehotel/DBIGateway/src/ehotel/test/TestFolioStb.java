package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.FolioStbPMS;
import ehotel.domain.pms.eBillCharge;
import ehotel.domain.pms.eFolio;
import ehotel.domain.pms.eGuest;
import ehotel.domain.pms.eMessage;
import ehotel.domain.pms.eSTB;

public class TestFolioStb {
	FolioStbPMS ehotel;

	public TestFolioStb() {
		ehotel = new FolioStbPMS();
	}

	public void getFolioList() {// ok
		Vector<eFolio> vFolio = ehotel.getFolioList(7, 12);
		for (eFolio aimage : vFolio) {
			System.out.println(aimage.toString());
		}
	}

	public void getGuests() {
		Vector<eGuest> vGuest = ehotel.getGuests(111);
		for (eGuest aimage : vGuest) {
			System.out.println(aimage.toString());
		}
	}

	public void getSTBListOut() {// 0k
		Vector<eSTB> vSTB = ehotel.getSTBListOut(1, 1, 10);
		for (eSTB aimage : vSTB) {
			System.out.println(aimage.toString());
		}
	}

	public void getSTBListIn() {// 0k
		System.out.println(System.currentTimeMillis());
		Vector<eSTB> vSTB = ehotel.getSTBListIn(111);
		for (eSTB aimage : vSTB) {
			System.out.println("getSTBListIn--" + aimage.toString());
		}
		System.out.println(System.currentTimeMillis());
	}

	public void addSTBIntoFolio() {// ok
		ehotel.addSTBIntoFolio("FA3610D0036840", 111);

	}

	public void removeSTBOutFolio() {// ok
		ehotel.removeSTBOutFolio("FA3610D0036840");
	}

	public void editSTBIntoFolio() {// ok
		ehotel.editSTBIntoFolio("FA3610D0036840", 111);
	}

	public void countFolio() {// ok
		System.out.println(ehotel.countFolio());
	}

	public void countSTB() {// ok
		System.out.println(ehotel.countSTB(-1));

		System.out.println(ehotel.countSTB(111));
	}

	public void getMessages() {// ok
		Vector<eMessage> vMessage = ehotel.getMessages(111, -1, -1);
		for (eMessage aimage : vMessage) {
			System.out.println("getSTBListIn--" + aimage.toString());
		}
	}

	public void getBillCharges() {// ok
		Vector<eBillCharge> vMessage = ehotel.getBillCharges(111, 1, 5);
		for (eBillCharge aimage : vMessage) {
			System.out.println("getBillCharges--" + aimage.toString());
		}
	}

	public void searchSTB() {// ok
		Vector<eSTB> vSTB = ehotel.searchSTB(1, "", -1, -1);
		for (eSTB aimage : vSTB) {
			System.out.println("searchSTB--" + aimage.toString());
		}
	}

	public void searchFolio() {//
		Vector<eFolio> vFolio = ehotel.searchFolio("12315", 7, 12);
		for (eFolio aimage : vFolio) {
			System.out.println(aimage.toString());
		}
	}

	public void countSearchSTB() {
		ehotel.countSearchSTB(111, "2");
	}

	public void resetPincode() {
		ehotel.resetPincode(111, 1234, "hoavk");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestFolioStb folioStb = new TestFolioStb();
		// folioStb.getFolioList();
		// folioStb.getGuests();
		// folioStb.addSTBIntoFolio();
		System.out.println(System.currentTimeMillis());
		folioStb.searchSTB();
		folioStb.countSearchSTB();
		System.out.println(System.currentTimeMillis());
		folioStb.getSTBListOut();
		// folioStb.getSTBListIn();
	}
}
