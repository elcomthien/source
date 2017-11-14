package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.PMSDiningDao;
import com.elcom.ehotel.admin.model.PMSDiningItemModel;
import com.elcom.ehotel.admin.model.PMSDiningSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSDiningService {
	private PMSDiningDao pmsDiningDao = new PMSDiningDao();

	public List<PMSDiningSubjectModel> getSubjectDining(String parentId, String langId) {
		return pmsDiningDao.getSubjectDining(ConvertUtil.convertToInteger(parentId), ConvertUtil.convertToInteger(langId));
	}

	public int addSubjectDining(PMSDiningSubjectModel sub) {
		sub.setName(UnicodeConverter.encodeUnicode(sub.getName()));
		return pmsDiningDao.addSubjectDining(sub);
	}

	public int editSubjectDining(PMSDiningSubjectModel sub) {
		System.out.println("editdiningservice");
		sub.setName(UnicodeConverter.encodeUnicode(sub.getName()));
		System.out.println(sub.toString());
		return pmsDiningDao.editSubjectDining(sub);
	}

	public int deleteSubjectDining(String subId) {
		return pmsDiningDao.deleteSubjectDining(subId);
	}

	public List<PMSDiningItemModel> getItemDining(String subjectId, String langId) {
		return pmsDiningDao.getItemDining(subjectId, langId);
	}

	public int addItemDining(PMSDiningItemModel item) {
		item.setName(UnicodeConverter.encodeUnicode(item.getName()));
		item.setDef(UnicodeConverter.encodeUnicode(item.getDef()));
		return pmsDiningDao.addItemDining(item);
	}

	public int editItemDining(PMSDiningItemModel item) {
		item.setName(UnicodeConverter.encodeUnicode(item.getName()));
		item.setDef(UnicodeConverter.encodeUnicode(item.getDef()));
		return pmsDiningDao.editItemDining(item);
	}

	public int deleteItemDining(String itemId) {
		return pmsDiningDao.deleteItemDining(itemId);
	}
}
