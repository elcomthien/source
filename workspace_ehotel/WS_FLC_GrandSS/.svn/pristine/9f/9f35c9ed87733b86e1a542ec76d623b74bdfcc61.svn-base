package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.PMSLanguageDao;
import com.elcom.ehotel.admin.model.PMSLanguageModel;

public class PMSLanguageService {
	private PMSLanguageDao pmsLanguageDao = new PMSLanguageDao();

	public List<PMSLanguageModel> getListLanguage() {
		return pmsLanguageDao.getListLanguage();
	}

	public int addNewLanguage(PMSLanguageModel lang) {
		return pmsLanguageDao.addNewLanguage(lang);
	}

	public int editLanguage(PMSLanguageModel lang) {
		return pmsLanguageDao.editLanguage(lang);
	}

	public int delteLanguage(int langId) {
		return pmsLanguageDao.deleteLanguage(langId);
	}
}
