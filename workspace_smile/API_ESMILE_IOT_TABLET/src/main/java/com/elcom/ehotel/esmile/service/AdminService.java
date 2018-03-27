package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import com.elcom.ehotel.esmile.model.Params;

public interface AdminService {
	
	public HashMap<String, Object> getStore(Params params);
	
	public HashMap<String, Object> getRealTime(Params params);
	
	public HashMap<String, Object> getRealTimeDetail(Params params);
	
	public HashMap<String, Object> getTable(Params params);
	
	public HashMap<String, Object> login(Params params);
	
	public HashMap<String, Object> getSumLike(Params params);
	
	public HashMap<String, Object> getSumWait(Params params);
	
	public HashMap<String, Object> postDone(Params params);
	
	public HashMap<String, Object> getInfoTable(Params params);
	
	public HashMap<String, Object> getQuickStats(Params params);
	
	public HashMap<String, Object> getHistory(Params params);
	
}
