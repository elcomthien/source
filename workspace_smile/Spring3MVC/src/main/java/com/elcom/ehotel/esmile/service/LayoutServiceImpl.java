package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.elcom.ehotel.esmile.dao.LayoutDao;
import com.elcom.ehotel.esmile.model.Params;

public class LayoutServiceImpl implements LayoutService {
	@Autowired
	private LayoutDao layoutDao;
	
	@Override
	public HashMap<String, Object> addLayout(Params params) {
		return layoutDao.addLayout(params);
	}
	
	@Override
	public HashMap<String, Object> editLayout(Params params) {
		return layoutDao.editLayout(params);
	}
	
	@Override
	public HashMap<String, Object> deleteLayout(Params params) {
		return layoutDao.deleteLayout(params);
	}
	
	@Override
	public HashMap<String, Object> getLayout() {
		return layoutDao.getLayout();
	}
}
