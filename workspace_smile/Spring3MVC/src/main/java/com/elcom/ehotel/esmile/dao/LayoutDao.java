package com.elcom.ehotel.esmile.dao;

import java.util.HashMap;

import com.elcom.ehotel.esmile.model.Params;

public interface LayoutDao {
	
	public HashMap<String, Object> addLayout(Params params);
	
	public HashMap<String, Object> editLayout(Params params);
	
	public HashMap<String, Object> deleteLayout(Params params);
	
	public HashMap<String, Object> getLayout();
}
