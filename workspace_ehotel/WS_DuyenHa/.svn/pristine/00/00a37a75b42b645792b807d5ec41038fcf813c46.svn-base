package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.PMSPromotionDao;
import com.elcom.ehotel.admin.model.PMSPromotionModel;
import com.elcom.ehotel.admin.util.ConvertUtil;

public class PMSPromotionService {
	private PMSPromotionDao pmsPromotionDao = new PMSPromotionDao();

	public List<PMSPromotionModel> getListPromotion(String langId) {
		return pmsPromotionDao.getListPromotion(ConvertUtil.convertToInteger(langId));
	}

	public int addPromotion(PMSPromotionModel pro) {
		return pmsPromotionDao.addPromotion(pro);
	}

	public int editPromotion(PMSPromotionModel pro) {
		return pmsPromotionDao.editPromotion(pro);
	}

	public int deletePromotion(String proId) {
		return pmsPromotionDao.deletePromotion(ConvertUtil.convertToInteger(proId));
	}
}
