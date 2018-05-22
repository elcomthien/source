package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.TransportationPMS;
import ehotel.domain.pms.eMenu;
import ehotel.domain.pms.eUrlAirline;

public class TestTransportation {
	TransportationPMS hotel = null;

	public TestTransportation() {
		hotel = new TransportationPMS();
	}

	public void getMenus() {// ok
		Vector<eMenu> menus = hotel.getMenus(2);
		for (eMenu aimage : menus) {
			System.out.println(aimage.toString());
		}
	}

	public void getItemMenu() {
		Vector<eMenu> submenus = hotel.getItemMenu(-9010, 2, 1, 10);
		for (eMenu aimage : submenus) {
			System.out.println(aimage.toString());
		}
	}

	public void addMenu() {// ok
		eMenu menu = new eMenu();
		menu.setMenuName("HoaVK");
		menu.setUrlImage("Hotel/hoavk.png");
		menu.setUrlBg("Hotel/bg/hoavk.png");
		hotel.addItemMenu(-9010, menu);
	}

	public void editItemMenu() {// ok
		eMenu menu = new eMenu();
		menu.setMenuId(-9010);
		menu.setMenuName("Ground Transportation");
		menu.setUrlImage("Transportion/GroundTransportation.png");
		menu.setUrlBg("Transportion/GroundTransportation.png");
		hotel.editItemMenu(menu, 2);
	}

	public void getItemInfo() {// ok
		eMenu menu = hotel.getItemMenuInfo(44, 2);
		if (menu != null)
			System.out.println(menu.toString());
	}

	public void removeItem() {
		hotel.removeItemMenu(463);
	}

	public void getUrlAirlines() {
		Vector<eUrlAirline> v = hotel.getUrlAirlines(1, 20);
		for (eUrlAirline aimage : v) {
			System.out.println(aimage.toString());
		}
	}

	public void addUrlAirline() {
		eUrlAirline airline = new eUrlAirline();
		airline.setName("Demo test dbi2");
		airline.setUrlImage("demo.jpg");
		airline.setUrlLink("demo...");
		hotel.addUrlAirline(airline);
	}

	public void editUrlAirline() {
		eUrlAirline url = new eUrlAirline();
		url.setId(1);
		url.setName("Demo test dbi");
		url.setUrlImage("demo1.jpg");
		url.setUrlLink("demo1...");
		hotel.editUrlAirline(url);
	}

	public void getUrlAirlineInfo() {
		eUrlAirline url = hotel.geteUrlAirlineInfo(17);
		if (url != null)
			System.out.println(url.toString());
	}

	public void removeUrlAirline() {
		hotel.removeUrlAirline(2);
	}

	public void countItemMenu() {
		System.out.println(hotel.countItemMenu(-9010));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestTransportation test = new TestTransportation();
		// test.addMenu();
		test.editItemMenu();
		// test.getUrlAirlines();

	}
}
