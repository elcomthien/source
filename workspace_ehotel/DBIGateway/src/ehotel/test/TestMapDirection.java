package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.MapDirectionPMS;
import ehotel.domain.pms.eLocationMap;
import ehotel.domain.pms.eMenu;

public class TestMapDirection {
	MapDirectionPMS ehotel = null;

	public TestMapDirection() {
		ehotel = new MapDirectionPMS();
	}

	public void getMenus() {
		Vector<eMenu> menus = ehotel.getMenus(2);
		for (eMenu aimage : menus) {
			System.out.println(aimage.toString());
		}
	}

	public void getMyLocation() {
		eLocationMap mylocation = ehotel.getMyLocation(2);
		if (mylocation != null)
			System.out.println(mylocation.toString());
	}

	public void getLocations() {
		Vector<eLocationMap> vLocation = ehotel.getLocations(27, 2, 1, 10);
		for (eLocationMap aimage : vLocation) {
			System.out.println(aimage.toString());
		}
	}

	public void addLocaltionMap() {
		eLocationMap aLoc = new eLocationMap();
		aLoc.setName("VBank");
		aLoc.setDistance("10 km");
		aLoc.setAddress("Nguyen Huu Tho");
		aLoc.setX("10.834099235590479");
		aLoc.setY("106.70497757775502");
		ehotel.addLocaltionMap(aLoc, 27);
	}

	public void editLocationMap() {
		eLocationMap aLoc = new eLocationMap();
		aLoc.setId(1);
		aLoc.setName("ELCOM Luxury Hotel");
		// aLoc.setDistance("10 km");
		aLoc.setAddress("162/12/13 Binh Loi Street,13 Ward,Binh Thanh Dist. Ho Chi Minh,Vietnam");
		aLoc.setX("10.795043503812323");
		aLoc.setY("106.67573731738275");
		ehotel.editLocationMap(aLoc, 2);
	}

	public void editCoordinate() {
		ehotel.editCoordinate(5, "10.834099235590479", "106.70497757775503");
	}

	public void countItem() {
		// ehotel.countItem(27);
		ehotel.countItemSearch(26, "C");
	}

	public void searchLocation() {
		Vector<eLocationMap> vLocations = ehotel.searchLocation(28, "hệ", 2, 1,
				10);
		for (eLocationMap aimage : vLocations) {
			System.out.println(aimage.toString());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMapDirection test = new TestMapDirection();
		// test.getMenus();
		// test.getMyLocation();
		// test.addLocaltionMap();
		test.editLocationMap();
	}
}
