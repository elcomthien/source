package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.LocalAttractionPMS;
import ehotel.domain.pms.eAttraction;
import ehotel.domain.pms.eMenu;

public class TestAttraction {
	LocalAttractionPMS hotel = null;

	public TestAttraction() {
		hotel = new LocalAttractionPMS();
	}

	public void getMenus() {// ok
		Vector<eMenu> menus = hotel.getMenus(2);
		for (eMenu aimage : menus) {
			System.out.println(aimage.toString());
		}
	}

	public void addMenu() {// ok
		eMenu menu = new eMenu();
		menu.setMenuName("HoaVK");
		menu.setUrlImage("Hotel/hoavk.png");
		menu.setUrlBg("Hotel/bg/hoavk.png");
		hotel.addAttractionMenu(menu);
	}

	public void editMenu() {// ok
		eMenu menu = new eMenu();
		menu.setMenuId(585);
		menu.setMenuName("hoavk1");
		hotel.editAttractionMenu(menu, 2);
	}

	public void removeMenu() {// ok
		hotel.removeAttractionMenu(585);
	}

	public void getItems() {// ok
		Vector<eAttraction> vAttraction = hotel.getItems(9, 2, 1, 10);
		for (eAttraction aimage : vAttraction) {
			System.out.println(aimage.toString());
		}
	}

	public void addItem() {
		eAttraction att = new eAttraction();
		att.setName("vuongkieuhoa");
		att.setDef("vuong kieu hoa test admin");
		att.setAddress("162/12 Binh Loi");
		hotel.addItem(9, att);
	}

	public void removeItem() {
		hotel.removeItem("(16,17)");
	}

	public void editItem() {
		eAttraction att = new eAttraction();
		att.setId(1);
		att.setName("Bandara_hoa");
		att.setDef("Welcome to Bandera - Atight knit of restaurents united under a banner of casual sophisticaition and quality service.");
		att.setAddress("535 North Michigan Avenue");
		hotel.editItem(att, 2);
	}

	public static void main(String[] args) {
		TestAttraction test = new TestAttraction();
		// test.removeItem();
		test.editItem();

	}
}
