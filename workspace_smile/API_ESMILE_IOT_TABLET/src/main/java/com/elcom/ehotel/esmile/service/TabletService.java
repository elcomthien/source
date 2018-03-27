package com.elcom.ehotel.esmile.service;

import java.util.HashMap;
import java.util.List;

import com.elcom.ehotel.esmile.model.Params;

public interface TabletService {
	
	public void  addDataRaw(String table, String idbu);
	
	public List<HashMap<String, String>>  getListTable(Params params);
	
	public HashMap<String, String>  setLocation(Params params);
	
	public HashMap<String, String>  setStatusRequest(Params params);
	
	public HashMap<String , Object> getRatingTime(Params params);
	
	public HashMap<String, String> setNotify(Params params);
	
	public List<HashMap<String, String>>  getNotify(Params params);
	
	public HashMap<String , Object> getTables(Params params);
	
	public List<HashMap<String, String>> getButton();
	
	public List<HashMap<String, String>> getEmployee();
	
	public HashMap<String, String> login(Params params);
	
	public HashMap<String, String > registerDevice(Params params);
	
	public HashMap<String, String > getStoreInfo(Params params);
	
	public List<HashMap<String, String>> getStore();
}
