package com.elcom.ehotel.esmile.dao;

import java.util.HashMap;

import com.elcom.ehotel.esmile.model.Params;

public class LoginDaoImpl implements LoginDao {

	@Override
	public HashMap<String, Object> checkLogin(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status_code", "200");
		map.put("message", "success");
		map.put("user_id", "1");
		map.put("user_role", "1");
		map.put("user_image", "avatar.png");
		return map;
	}
}
