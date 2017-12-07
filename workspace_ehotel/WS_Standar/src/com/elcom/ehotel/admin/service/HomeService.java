package com.elcom.ehotel.admin.service;

import com.elcom.ehotel.admin.dao.HomeDao;
import com.elcom.ehotel.admin.util.Encryptor;

public class HomeService {

	private HomeDao homeDao = new HomeDao();

	public int checkLogin(String username, String password) {
		return homeDao.checkLogin(username, Encryptor.encryptPassword(password));
	}
}
