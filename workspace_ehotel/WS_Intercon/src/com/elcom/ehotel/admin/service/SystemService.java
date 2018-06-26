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

	public int updateTextWelcome(String line01, String line02, String logo, String logosmall) {
		line01 = UnicodeConverter.encodeUnicode(line01);
		line02 = UnicodeConverter.encodeUnicode(line02);
		return systemDao.updateTextWelcome(line01, line02, logo, logosmall);
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

	public HashMap<String, String> getTextWelcomeGroup(String idgroup) {
		return systemDao.getTextWelcomeGroup(idgroup);
	}

	public int updateTextWelcomeGroup(String line01, String line02, String logo, String logosmall, String idgroup) {
		line01 = UnicodeConverter.encodeUnicode(line01);
		line02 = UnicodeConverter.encodeUnicode(line02);
		return systemDao.updateTextWelcomeGroup(line01, line02, logo, logosmall, idgroup);
	}

	public List<SystemAdvertiseModel> getListAdvertiseGroup(String type, String idgroup) {
		return systemDao.getListAdvertiseGroup(type, idgroup);
	}

	public int addAdvertiseGroup(SystemAdvertiseModel adv) {
		return systemDao.addAdvertiseGroup(adv);
	}
	
	public int editAdvertiseGroup(SystemAdvertiseModel adv) {
		return systemDao.editAdvertiseGroup(adv);
	}

	public List<SystemServiceModel> getListServiceGroup(String langId, String idgroup) {
		return systemDao.getListServiceGroup(langId, idgroup);
	}
	
	public int deleteAdvertiseGroup(String advid, String idgroup) {
		return systemDao.deleteAdvertiseGroup(advid, idgroup);
	}
	
	public int editSystemServiceGroup(SystemServiceModel service, String langid) {
		return systemDao.editSystemServiceGroup(service, langid);
	}
	
	public int updateStatusSystemSerivceGroup(String idservice, String idgroup) {
		return systemDao.updateStatusSystemSerivceGroup(idservice, idgroup);
	}
	
	public List<SystemServiceModel> getListServiceForParentGroup(String langid, String parentid, String idgroup) {
		return systemDao.getServiceForParentGroup(ConvertUtil.convertToInteger(langid), ConvertUtil.convertToInteger(parentid),idgroup);
	}
}
