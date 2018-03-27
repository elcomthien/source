package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.elcom.ehotel.esmile.dao.AdminDao;
import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.util.Encryptor;

public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Override
	public HashMap<String, Object> getStore(Params params) {
		return adminDao.getStore(params);
	}

	@Override
	public HashMap<String, Object> getRealTime(Params params) {
		return adminDao.getRealTime(params);
	}

	@Override
	public HashMap<String, Object> getRealTimeDetail(Params params) {
		return adminDao.getRealTimeDetail(params);
	}

	@Override
	public HashMap<String, Object> getTable(Params params) {
		return adminDao.getTable(params);
	}

	@Override
	public HashMap<String, Object> login(Params params) {
		params.setPassword(Encryptor.encryptPassword(params.getPassword()));
		return adminDao.login(params);
	}

	@Override
	public HashMap<String, Object> getSumLike(Params params) {
		return adminDao.getSumLike(params);
	}

	@Override
	public HashMap<String, Object> getSumWait(Params params) {
		return adminDao.getSumWait(params);
	}

	@Override
	public HashMap<String, Object> postDone(Params params) {
		return adminDao.postDone(params);
	}

	@Override
	public HashMap<String, Object> getInfoTable(Params params) {
		return adminDao.getInfoTable(params);
	}

	@Override
	public HashMap<String, Object> getQuickStats(Params params) {
		return adminDao.getQuickStats(params);
	}

	@Override
	public HashMap<String, Object> getHistory(Params params) {
		return adminDao.getHistory(params);
	}
}
