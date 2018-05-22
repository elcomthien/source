package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.HotelInfoPMS;
import ehotel.domain.pms.eImage;
import ehotel.domain.pms.eMenu;

public class TestHotelInfo {
	HotelInfoPMS hotel = null;

	public TestHotelInfo() {
		hotel = new HotelInfoPMS();
	}

	public void addMenu() {// ok
		hotel.addHotelMenu("HoaVK", "Hotel/hoavk.png", "Hotel/bg/hoavk.png");
	}

	public void editMenu() {// ok
		eMenu menu = new eMenu();
		menu.setMenuId(584);
		menu.setMenuName("hoavk");
		hotel.editHotelMenu(menu, 2);
	}

	public void removeMenu() {// ok
		hotel.removeHotelMenu(584);
	}

	public void getItemsOfHotel() {// ok
		Vector<eImage> images = hotel.getItemsOfHotelInfo(1, 2);
		for (eImage aimage : images) {
			System.out.println(aimage.toString());
		}
	}

	public void getItemInfo() {// ok
		eImage image = hotel.getItemInfo(1, 2);
		if (image != null)
			System.out.println(image.toString());

	}

	public void addItem() {// ok
		hotel.addItemHotel(1, "demo", "demo test dbi", "Hotel/hoavk.png",
				"Hotel/bg/hoavk.png");
	}

	public void editItem() {// ok
		eImage image = new eImage();
		image.setId(687);
		image.setName("Demo");
		image.setDef("DBI Test");
		hotel.editItem(image, 2);
	}

	public void removeItem() {// ok
		hotel.removeItem(687);
	}

	public void changeSubjectOfItem() {// ok
		hotel.changeSubjectOfItem(1, "(1)");
	}

	public void getMenus() {// ok
		Vector<eMenu> menus = hotel.getMenus(2);
		for (eMenu aimage : menus) {
			System.out.println(aimage.toString());
		}
	}

	public static void main(String[] args) {
		TestHotelInfo test = new TestHotelInfo();
		test.getMenus();
	}
}
