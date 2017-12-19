package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.elcom.ehotel.esmile.dao.LoginDao;
import com.elcom.ehotel.esmile.model.Params;


public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public HashMap<String, Object> checkLogin(Params params){
		return loginDao.checkLogin(params);
	}
}
