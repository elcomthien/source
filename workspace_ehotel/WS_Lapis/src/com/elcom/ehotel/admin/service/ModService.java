package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.ModDao;
import com.elcom.ehotel.admin.model.ModContentModel;
import com.elcom.ehotel.admin.model.ModSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class ModService {

	private ModDao modDao = new ModDao();

	public List<ModSubjectModel> getListSubjectMod(String langid) {
		return modDao.getListSubjectMod(ConvertUtil.convertToInteger(langid));
	}

	public int addSubjectMod(ModSubjectModel sub) {
		sub.setName(UnicodeConverter.encodeUnicode(sub.getName()));
		return modDao.addSubjectMod(sub);
	}

	public int editSubjectMod(ModSubjectModel sub) {
		sub.setName(UnicodeConverter.encodeUnicode(sub.getName()));
		return modDao.editSubjectMod(sub);
	}

	public int deleteSubjectMod(String idMod) {
		return modDao.deleteSubjectMod(ConvertUtil.convertToInteger(idMod));
	}

	public List<ModContentModel> getListContent(String idSubject, String langid) {
		return modDao.getListContent(ConvertUtil.convertToInteger(idSubject),
				ConvertUtil.convertToInteger(langid));
	}

	public int addMod(ModContentModel con) {
		return modDao.addMod(con);
	}

	public int editMod(ModContentModel con) {
		return modDao.editMod(con);
	}

	public int deleteMod(String idMod) {
		return modDao.deleteMod(ConvertUtil.convertToInteger(idMod));
	}
}
