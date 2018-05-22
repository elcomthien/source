package com.elcom.ehotel.admin.service;

import java.util.HashMap;
import java.util.List;

import com.elcom.ehotel.admin.dao.SystemDao;
import com.elcom.ehotel.admin.model.SystemAdvertiseModel;
import com.elcom.ehotel.admin.model.SystemServiceModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class SystemService {

	private SystemDao systemDao = new SystemDao();

	public List<SystemServiceModel> getListService(String langId) {
		return systemDao.getListService(ConvertUtil.convertToInteger(langId));
	}

	public int editSystemService(SystemServiceModel service, String langid) {
		service.setName(UnicodeConverter.encodeUnicode(service.getName()));
		return systemDao.editSystemService(service, ConvertUtil.convertToInteger(langid));
	}

	public int updateStatusSystemSerivce(String idService) {
		return systemDao.updateStatusSystemSerivce(ConvertUtil.convertToInteger(idService));
	}

	public HashMap<String, String> getTextWelcome() {
		return systemDao.getTextWelcome();
	}

	public int updateTextWelcome(String line01, String line02, String logo, String logosmall, String logo02, String logosmall02,String welcome0, String welcome1) {
		line01 = UnicodeConverter.encodeUnicode(line01);
		line02 = UnicodeConverter.encodeUnicode(line02);
		welcome0 = UnicodeConverter.encodeUnicode(welcome0);
		welcome1 = UnicodeConverter.encodeUnicode(welcome1);
		return systemDao.updateTextWelcome(line01, line02, logo, logosmall, logo02, logosmall02, welcome0,  welcome1);
	}

	public List<SystemAdvertiseModel> getListAdvertise(String type) {
		return systemDao.getListAdvertise(type);
	}

	public int addAdvertise(SystemAdvertiseModel adv) {
		return systemDao.addAdvertise(adv);
	}

	public int editAdvertise(SystemAdvertiseModel adv) {
		return systemDao.editAdvertise(adv);
	}

	public int deleteAdvertise(String advid) {
		return systemDao.deleteAdvertise(ConvertUtil.convertToInteger(advid));
	}

	public List<SystemServiceModel> getListServiceForParent(String langid, String parentid) {
		return systemDao.getServiceForParent(ConvertUtil.convertToInteger(langid), ConvertUtil.convertToInteger(parentid));
	}
}
