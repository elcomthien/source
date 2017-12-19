package com.elcom.ehotel.esmile.dao;

import java.util.HashMap;

import com.elcom.ehotel.esmile.model.Params;

public interface LoginDao {
	public HashMap<String, Object> checkLogin(Params params);
}
