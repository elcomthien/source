package com.elcom.ehotel.admin.service;

import java.util.HashMap;

import com.elcom.ehotel.admin.dao.HomeDao;
import com.elcom.ehotel.admin.util.Encryptor;

public class HomeService {

	private HomeDao homeDao = new HomeDao();

	public HashMap<String, String> checkLogin(String username, String password) {
		return homeDao.checkLogin(username, Encryptor.encryptPassword(password));
	}
}
