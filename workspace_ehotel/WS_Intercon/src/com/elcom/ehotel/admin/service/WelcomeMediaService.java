package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.WelcomeMediaDao;
import com.elcom.ehotel.admin.model.WelcomeMediaModel;

public class WelcomeMediaService {
	private WelcomeMediaDao welcomeMediaDao = new WelcomeMediaDao();

	public List<WelcomeMediaModel> getWelcomeMedia(String type) {
		return welcomeMediaDao.getWelcomeMedia(type);
	}

	public int addWelcomeMedia(WelcomeMediaModel wlc) {
		return welcomeMediaDao.addWelcomeMedia(wlc);
	}

	public int editWelcomeMedia(WelcomeMediaModel wlc) {
		return welcomeMediaDao.editWelcomeMedia(wlc);
	}

	public int deleteWelcomeMedia(String id) {
		return welcomeMediaDao.deleteWelcomeMedia(id);
	}

	public List<WelcomeMediaModel> getWelcomeMediaGroup(String type, String idgroup) {
		return welcomeMediaDao.getWelcomeMediaGroup(type, idgroup);
	}

	public int addWelcomeMediaGroup(WelcomeMediaModel wlc) {
		return welcomeMediaDao.addWelcomeMediaGroup(wlc);
	}

	public int editWelcomeMediaGroup(WelcomeMediaModel wlc) {
		return welcomeMediaDao.editWelcomeMediaGroup(wlc);
	}

	public int deleteWelcomeMediaGroup(String id, String idgroup) {
		return welcomeMediaDao.deleteWelcomeMediaGroup(id, idgroup);
	}

}
