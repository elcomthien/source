package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.ModDao;
import com.elcom.ehotel.admin.model.ModContentModel;
import com.elcom.ehotel.admin.model.ModSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;

public class ModService {

	private ModDao modDao = new ModDao();

	public List<ModSubjectModel> getListSubjectMod(String langid) {
		return modDao.getListSubjectMod(ConvertUtil.convertToInteger(langid));
	}

	public int addSubjectMod(ModSubjectModel sub) {
		return modDao.addSubjectMod(sub);
	}

	public int editSubjectMod(ModSubjectModel sub) {
		return modDao.editSubjectMod(sub);
	}

	public int deleteSubjectMod(String idMod) {
		return modDao.deleteSubjectMod(ConvertUtil.convertToInteger(idMod));
	}

	public List<ModContentModel> getListContent(String idSubject, String langid) {
		return modDao.getListContent(ConvertUtil.convertToInteger(idSubject), ConvertUtil.convertToInteger(langid));
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

	// ///////////////Group//////////////////////////////
	public List<ModSubjectModel> getListSubjectModGroup(String langid, String idGroup) {
		return modDao.getListSubjectModGroup(ConvertUtil.convertToInteger(langid), idGroup);
	}

	public int addSubjectModGroup(ModSubjectModel sub) {
		return modDao.addSubjectModGroup(sub);
	}

	public int editSubjectModGroup(ModSubjectModel sub) {
		return modDao.editSubjectModGroup(sub);
	}

	public int deleteSubjectModGroup(String idMod, String idGroup) {
		return modDao.deleteSubjectModGroup(ConvertUtil.convertToInteger(idMod), idGroup);
	}

	public List<ModContentModel> getListContentGroup(String idSubject, String langid, String idGroup) {
		return modDao.getListContentGroup(ConvertUtil.convertToInteger(idSubject), ConvertUtil.convertToInteger(langid), idGroup);
	}

	public int addModGroup(ModContentModel con) {
		return modDao.addModGroup(con);
	}

	public int editModGroup(ModContentModel con) {
		return modDao.editModGroup(con);
	}

	public int deleteModGroup(String idMod, String idGroup) {
		return modDao.deleteModGroup(ConvertUtil.convertToInteger(idMod), idGroup);
	}
}
