package ehotel.test;

import java.util.Vector;

import ehotel.domain.menu.Group;
import ehotel.domain.menu.Menu;
import ehotel.inter.AMDMenu;
import ehotel.render.DBIGateway;

public class TestMenu {
	static AMDMenu admMenu;

	public static void getGroupMenu() {
		Vector<Group> groups = admMenu.getGroups(2, "172.16.9.36");
		for (Group g : groups) {
			System.out.println(g.toString());
		}
	}

	public static void getSubMenu() {
		Vector<Menu> menus = admMenu.getMenuList(4, 2);
		for (Menu g : menus) {
			System.out.println(g.toString());
		}
	}

	public static void main(String[] args) {
		admMenu = DBIGateway.getAMDMenu();
		// TestMenu.getGroupMenu();
		TestMenu.getGroupMenu();
	}
}
