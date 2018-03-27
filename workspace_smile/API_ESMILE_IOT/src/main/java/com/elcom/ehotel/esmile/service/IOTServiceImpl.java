package com.elcom.ehotel.esmile.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.elcom.ehotel.esmile.dao.IOTDao;

public class IOTServiceImpl implements IOTService {

	@Autowired
	private IOTDao iotDao;

	public void addDataRaw(String table, String idbu) {
		iotDao.addDataRaw(table, idbu);
	}

}
