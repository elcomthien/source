package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.UserActiveDao;
import com.elcom.ehotel.admin.model.UserActiveModel;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class UserActiveService {
	private UserActiveDao userActiveDao = new UserActiveDao();
	
	public int addUserActive(String username, String subject, String description) {
		return userActiveDao.addUserActive(username,UnicodeConverter.encodeUnicode(subject), UnicodeConverter.encodeUnicode(description));
	}
	
	public List<UserActiveModel> getListUserActive(String from, String to) {
		return userActiveDao.getListUserActive(from, to);
	}
}
