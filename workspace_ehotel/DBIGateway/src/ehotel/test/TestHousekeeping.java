package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.HousekeepingPMS;
import ehotel.domain.pms.eHousekeeping;
import ehotel.domain.pms.eMenu;

public class TestHousekeeping {
	HousekeepingPMS hs;

	public TestHousekeeping() {
		hs = new HousekeepingPMS();
	}

	public void getMenus() {
		Vector<eMenu> vMenu = hs.getMenus(2);
		for (eMenu aimage : vMenu) {
			System.out.println(aimage.toString());
		}
	}

	public void getItems() {// ok
		Vector<eHousekeeping> vItems = hs.getItems(-9015, 2, 1, 10);
		for (eHousekeeping aimage : vItems) {
			System.out.println(aimage.toString());
		}
	}

	public void getInfo() {// ok
		eHousekeeping e = hs.getItemInfo(3, 2);
		System.out.println(e.toString());
	}

	public void addItem() {// ok
		eHousekeeping item = new eHousekeeping("demo", "2.5", "hoavk.jpg");
		item.setIunit("USD");
		hs.addItem(-9014, item);
	}

	public void editItem() {
		eHousekeeping item = new eHousekeeping("Extra", "2.5", "hoavk1.jpg");
		item.setIunit("VND");
		item.setId(24);
		hs.editItem(item, 2);
	}

	public void removeItem() {// ok
		hs.removeItem("(3)");
	}

	public static void main(String[] args) {
		TestHousekeeping test = new TestHousekeeping();
		test.getMenus();
		// test.addItem();
		// test.editItem();

		// test.editItem();
		test.getItems();
	}

}
