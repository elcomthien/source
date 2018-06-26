package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.elcom.ehotel.esmile.dao.LoginDao;
import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.util.Encryptor;


public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public HashMap<String, Object> checkLogin(Params params){
		params.setPassword(Encryptor.encryptPassword(params.getPassword()));
		return loginDao.checkLogin(params);
	}
}
