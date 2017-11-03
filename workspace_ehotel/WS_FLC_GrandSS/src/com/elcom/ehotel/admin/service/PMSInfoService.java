package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.PMSInfoDao;
import com.elcom.ehotel.admin.model.PMSInfoContentModel;
import com.elcom.ehotel.admin.model.PMSInfoSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSInfoService {
	private PMSInfoDao pmsInfoDao = new PMSInfoDao();

	public List<PMSInfoSubjectModel> getListSubjectInfo(String serviceId, String langId) {
		return pmsInfoDao.getListSubjectInfo(ConvertUtil.convertToInteger(serviceId), ConvertUtil.convertToInteger(langId));
	}

	public int addSubjectInfo(PMSInfoSubjectModel info) {
		return pmsInfoDao.addSubjectInfo(info);
	}

	public int editSubjectInfo(PMSInfoSubjectModel info) {
		return pmsInfoDao.editSubjectInfo(info);
	}

	public int deleteSubjectInfo(String serviceId, String subjectId) {
		return pmsInfoDao.deleteSubjectInfo(ConvertUtil.convertToInteger(serviceId), ConvertUtil.convertToInteger(subjectId));
	}

	public List<PMSInfoContentModel> getListContentInfo(String subjectId, String langId) {
		return pmsInfoDao.getListContentInfo(ConvertUtil.convertToInteger(subjectId), ConvertUtil.convertToInteger(langId));
	}

	public int addContentInfo(PMSInfoContentModel info) {
		info.setName(UnicodeConverter.encodeUnicode(info.getName()));
		return pmsInfoDao.addContentInfo(info);
	}

	public int editContentInfo(PMSInfoContentModel info) {
		info.setName(UnicodeConverter.encodeUnicode(info.getName()));
		return pmsInfoDao.editContentInfo(info);
	}

	public int deleteContentInfo(String contentId) {
		return pmsInfoDao.deleteContentInfo(ConvertUtil.convertToInteger(contentId));
	}

}
