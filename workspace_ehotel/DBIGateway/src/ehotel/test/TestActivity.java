package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.HotelActivityPMS;
import ehotel.domain.pms.eActivity;
import ehotel.domain.pms.eMenu;

public class TestActivity {

	HotelActivityPMS hotel = null;

	public TestActivity() {
		hotel = new HotelActivityPMS();
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
		hotel.addActiMenu(menu);
	}

	public void getItemInfo() {// ok
		eActivity image = hotel.getItemInfo(-1, 1);
		if (image != null)
			System.out.println(image.toString());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestActivity test = new TestActivity();
		test.getItemInfo();
	}

}
