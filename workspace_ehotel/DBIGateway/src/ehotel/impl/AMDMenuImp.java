package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eMenuDAO;
import ehotel.domain.menu.Group;
import ehotel.domain.menu.Menu;
import ehotel.inter.AMDMenu;

public class AMDMenuImp implements AMDMenu {
	private eMenuDAO eDao;

	public AMDMenuImp() {
		eDao = new eMenuDAO();
	}

	@Override
	public Vector<Group> getGroups(int langid, String addressIp) {
		return eDao.getGroups(langid, addressIp);
	}

	@Override
	public Vector<Menu> getMenuList(int groupId, int langid) {
		return eDao.getMenuList(groupId, langid);
	}

	@Override
	public Vector<Menu> getSubMenuList(int menuId, int langid) {
		return eDao.getSubMenuList(menuId, langid);
	}

}
