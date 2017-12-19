package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import com.elcom.ehotel.esmile.model.Params;

public interface LoginService {

	public HashMap<String, Object> checkLogin(Params params);
}
