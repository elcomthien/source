package com.elcom.ehotel.esmile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.elcom.ehotel.esmile.dao.TabletDao;
import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.util.Encryptor;

public class TabletServiceImpl implements TabletService {

	@Autowired
	private TabletDao tabletDao;

	@Override
	public void addDataRaw(String table, String idbu) {
		tabletDao.addDataRaw(table, idbu);
	}

	@Override
	public List<HashMap<String, String>> getListTable(Params params) {
		return tabletDao.getListTable(params);
	}

	@Override
	public HashMap<String, String> setLocation(Params params) {
		return tabletDao.setLocation(params);
	}

	@Override
	public HashMap<String, String> setStatusRequest(Params params) {
		return tabletDao.setStatusRequest(params);
	}

	@Override
	public HashMap<String, Object> getRatingTime(Params params) {
		return tabletDao.getRatingTime(params);
	}

	@Override
	public HashMap<String, String> setNotify(Params params) {
		return tabletDao.setNotify(params);
	}

	@Override
	public List<HashMap<String, String>> getNotify(Params params) {
		return tabletDao.getNotify(params);
	}

	@Override
	public HashMap<String, Object> getTables(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> listtable = new ArrayList<HashMap<String, String>>();
		HashMap<String, Object> rating = new HashMap<String, Object>();
		listtable = tabletDao.getListTable(params);
		rating = tabletDao.getRatingTime(params);
		map.put("tables", listtable);
		map.put("rating", rating);
		return map;
	}

	@Override
	public List<HashMap<String, String>> getButton() {
		return tabletDao.getButton();
	}

	@Override
	public List<HashMap<String, String>> getEmployee() {
		return tabletDao.getEmployee();
	}

	@Override
	public HashMap<String, String> login(Params params) {
		params.setPassword(Encryptor.encryptPassword(params.getPassword()));
		return tabletDao.login(params);
	}

	@Override
	public HashMap<String, String> registerDevice(Params params) {
		return tabletDao.registerDevice(params);
	}

	@Override
	public HashMap<String, String> getStoreInfo(Params params) {
		return tabletDao.getStoreInfo(params);
	}
	
	@Override
	public List<HashMap<String, String>> getStore(){
		return tabletDao.getStore();
	}
}
