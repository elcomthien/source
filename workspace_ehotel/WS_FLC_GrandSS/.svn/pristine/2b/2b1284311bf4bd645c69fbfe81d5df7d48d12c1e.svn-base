package com.elcom.ehotel.admin.service;

import java.util.List;

import com.elcom.ehotel.admin.dao.PMSExchangeRateDao;
import com.elcom.ehotel.admin.model.PMSExchangeRateModel;
import com.elcom.ehotel.admin.util.ConvertUtil;

public class PMSExchangeRateService {
	private PMSExchangeRateDao pmsExchangeRateDao = new PMSExchangeRateDao();

	public List<PMSExchangeRateModel> getListExchangeRate() {
		return pmsExchangeRateDao.getListExchangeRate();
	}

	public int addExchangeRate(PMSExchangeRateModel ex) {
		return pmsExchangeRateDao.addExchangeRate(ex);
	}

	public int editExchangeRate(PMSExchangeRateModel ex) {
		return pmsExchangeRateDao.editExchangeRate(ex);
	}

	public int deleteExchangeRate(String id) {
		return pmsExchangeRateDao.deleteExchangeRate(ConvertUtil.convertToInteger(id));
	}
}
