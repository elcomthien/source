package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.AdvertisePMS;
import ehotel.domain.pms.eAdvertise;

public class TestAdvertise {
	AdvertisePMS ehotel;

	public TestAdvertise() {
		ehotel = new AdvertisePMS();
	}

	public void getAdvTypeList() {
		Vector<String> advTypeList = ehotel.getAdvTypeList(2);
		for (String a : advTypeList) {
			System.out.println(a);
		}
	}

	public void getImageAdverties() {
		Vector<eAdvertise> advTypeList = ehotel.getImageAdverties(2, 1, 10);
		for (eAdvertise a : advTypeList) {
			System.out.println(a);
		}
	}

	public void getAdvertiseInfo() {
		eAdvertise adv = ehotel.getAdvertiseInfo(2, 2);
		if (adv != null)
			System.out.println(adv.toString());
	}

	public void editAdvertise() {
		eAdvertise adv = new eAdvertise();
		adv.setId(799);
		adv.setName("Hoavk");
		adv.setType("MUSIC");
		ehotel.editAdvertise(adv, 2);
	}

	public void removeAdv() {
		ehotel.removeAdverties("(800)");
	}

	public void changeAdv() {
		ehotel.changeStatus(801);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestAdvertise test = new TestAdvertise();
		test.getAdvTypeList();
		test.getAdvertiseInfo();
		test.getImageAdverties();
		test.editAdvertise();
	}

}
