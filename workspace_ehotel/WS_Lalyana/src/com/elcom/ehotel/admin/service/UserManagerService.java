package com.elcom.ehotel.admin.service;

import java.util.HashMap;
import java.util.List;

import com.elcom.ehotel.admin.dao.UserManagerDao;
import com.elcom.ehotel.admin.model.UserManagerModel;
import com.elcom.ehotel.admin.util.Encryptor;

public class UserManagerService {
	private UserManagerDao userManagerDao = new UserManagerDao();

	public List<UserManagerModel> getListUser() {
		return userManagerDao.getListUser();
	}

	public int addUser(UserManagerModel user) {
		user.setPass(Encryptor.encryptPassword(user.getPass()));
		return userManagerDao.addUser(user);
	}

	public int editUser(UserManagerModel user) {
		user.setPass(Encryptor.encryptPassword(user.getPass()));
		return userManagerDao.editUser(user);
	}

	public int changePass(String id, String newpass, String oldpass) {
		newpass = Encryptor.encryptPassword(newpass);
		oldpass = Encryptor.encryptPassword(oldpass);
		return userManagerDao.changePass(id, newpass, oldpass);
	}

	public int deleteUser(String id) {
		return userManagerDao.deleteUser(id);
	}

	public int changePassAdmin(String id, String newpass) {
		newpass = Encryptor.encryptPassword(newpass);
		return userManagerDao.changePassAdmin(id, newpass);
	}

	public List<HashMap<String, String>> getListRold() {
		return userManagerDao.getListRold();
	}
}
