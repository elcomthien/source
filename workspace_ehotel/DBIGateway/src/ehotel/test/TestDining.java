package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.DiningPMS;
import ehotel.domain.pms.eItem;
import ehotel.domain.pms.eMenu;
import ehotel.domain.pms.eRestaurant;

public class TestDining {
	DiningPMS hotel = null;

	public TestDining() {
		hotel = new DiningPMS();
	}

	public void getMenus() {// ok
		Vector<eMenu> vMenu = hotel.getMenus(2);
		for (eMenu aimage : vMenu) {
			System.out.println(aimage.toString());
		}
	}

	public void getSubMenus() {// ok
		Vector<eMenu> vMenu = hotel.getSubMenus(-9012, 2);
		for (eMenu aimage : vMenu) {
			System.out.println(aimage.toString());
		}
	}

	public void addMenu() {
		eMenu menu = new eMenu();
		menu.setMenuName("demo");
		menu.setUrlImage("urldemo.jpg");
		menu.setUrlBg("urldemo.jpg");
		hotel.addDiningMenu(-9012, menu);
	}

	public void editMenu() {
		eMenu menu = new eMenu();
		menu.setMenuId(-9012);
		menu.setMenuName("demo");
		menu.setUrlImage("urldemo.jpg");
		menu.setUrlBg("urldemo.jpg");
		hotel.editDiningMenu(menu, 2);
	}

	public void removeMenu() {
		hotel.removeDiningMenu(504);
	}

	public void getMenuInfo() {// ok
		eMenu menu = hotel.getMenuInfo(1, 1);
		if (menu != null)
			System.out.println(menu.toString());
	}

	public void addMenuDining() {
		// eMenu menu = new
	}

	public void addItem() {
		eItem item = new eItem();
		item.setName("Coca cola");
		item.setDef("Coca Cola");
		item.setCurrency("3.5");
		item.setIUnit("USD");
		item.setUrlImage("cocacola.jpg");
		hotel.addRoomSvcItem(item, 1);
	}

	public void editItem() {
		eItem item = new eItem();
		item.setICode(629);
		item.setName("Coca cola00012");
		item.setDef("Coca Cola");
		item.setCurrency("3.5");
		item.setIUnit("USD");
		item.setUrlImage("cocacola.jpg");
		hotel.editRoomSvcItem(item, 2);
	}

	public void removeItem() {
		hotel.removeRoomSvcItem(631);
	}

	public void getItems() {
		Vector<eItem> vItems = hotel.getRoomSvcItems(1, 2, 1, 6);
		for (eItem aimage : vItems) {
			System.out.println(aimage.toString());
		}
	}

	public void getResItems() {
		Vector<eRestaurant> vItems = hotel.getRestaurantItems(39, 2, 1, 10);
		for (eRestaurant aimage : vItems) {
			System.out.println(aimage.toString());
		}
	}

	public void editResItem() {
		eRestaurant res = new eRestaurant();
		res.setId(1);
		res.setName("Demo Restaurant");
		res.setDef("demo restaurant");
		hotel.editRestaurantItem(res, 2);
	}

	public void changeItemStatus() {
		hotel.changeItemStatus(90);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestDining test = new TestDining();
		// test.getMenus();
		// test.getSubMenus();
		// test.removeMenu();
		// test.getMenuDiningInfo();
		// test.getItems();
		test.changeItemStatus();
		// test.getSubMenus();
	}
}
