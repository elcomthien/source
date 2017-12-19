package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import com.elcom.ehotel.esmile.model.Params;

public interface LayoutService {
	
	public HashMap<String, Object> addLayout(Params params);

	public HashMap<String, Object> editLayout(Params params);

	public HashMap<String, Object> deleteLayout(Params params);
	
	public HashMap<String, Object> getLayout();
}
