package ehotel.impl;

import ehotel.dao.eUserDao;
import ehotel.inter.ILOGIN;

public class AMDLogin implements ILOGIN {
	private eUserDao eDao;

	public AMDLogin() {
		eDao = new eUserDao();
	}

	@Override
	public boolean login(String account, String passwd, String markAddress) {
		// TODO Auto-generated method stub
		return eDao.login(account, passwd, markAddress);
	}

	@Override
	public void logout(String markAddress) {
		eDao.logout(markAddress);
	}

	@Override
	public boolean checkRoleUser(String markAddress, String role) {
		return eDao.checkRoleUser(markAddress, role);
	}

	@Override
	public String getLoginUserName(String markAddress) {
		// TODO Auto-generated method stub
		return eDao.getLoginUserName(markAddress);
	}

	@Override
	public boolean isLogedinByAnotherUser(String markAddress) {
		// TODO Auto-generated method stub
		return eDao.isLogedinByAnotherUser(markAddress);
	}

	@Override
	public boolean isAdmin(String markAdrress) {
		// TODO Auto-generated method stub
		return eDao.isAdmin(markAdrress);
	}

	@Override
	public String getHrefPageOfUser(String markAddress) {
		// TODO Auto-generated method stub
		return null;
	}

}
