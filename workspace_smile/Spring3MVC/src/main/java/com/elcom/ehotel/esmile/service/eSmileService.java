package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import com.elcom.ehotel.esmile.model.Params;

public interface eSmileService {
	
	public HashMap<String, Object> addLayout(Params params);

	public HashMap<String, Object> editLayout(Params params);
	
	public HashMap<String, Object> updateStatusLayout(Params params);

	public HashMap<String, Object> deleteLayout(Params params);
	
	public HashMap<String, Object> getLayoutId();
	
	public HashMap<String, Object> getLayout(String userid, String layoutid, String sessionid);
	
	public HashMap<String, Object> addPage(Params params);
	
	public HashMap<String, Object> addObjectContent(Params params);
	
	public HashMap<String, Object> addObject(Params params);
	
	public HashMap<String, Object> getSubjectTemplate(Params params);
	
	public HashMap<String, Object> getTemplate(Params params);
	
	public HashMap<String, Object> addAccount(Params params);
	
	public HashMap<String, Object> editAccount(Params params);
	
	public HashMap<String, Object> postRating(Params params);
	
	public HashMap<String, Object> getZone(Params params);
	
	public HashMap<String, Object> resetPassword(Params params);
	
	public HashMap<String, Object> deleteAccount(Params params);
	
	public HashMap<String, Object> getSession(Params params);
	
	public HashMap<String, Object> updateSession(Params params);
	
	public HashMap<String, Object> getInfoFilter(Params params);
	
	public HashMap<String, Object> addDevice(Params params);
	
	public HashMap<String, Object> getRole(Params params);
	
	public HashMap<String, Object> getFeedbackLayout(Params params);
	
	public HashMap<String, Object> addPlugin(Params params);
	
	public HashMap<String, Object> getPlugin(Params params);
	
	public HashMap<String, Object> editPlugin(Params params);
	
	public HashMap<String, Object> deletePlugin(Params params);
	
	public HashMap<String, Object> addPluginEmail(Params params);
	
	public HashMap<String, Object> boxLogin(Params params);
	
	public HashMap<String, Object> boxGetLink(Params params);
	
	public HashMap<String, Object> postPlugin(Params params);
	
	public HashMap<String, Object> getFeedbackPluginDeatail(Params params);
	
	public HashMap<String, Object> getFeedbackPlugin(Params params);
	
	public HashMap<String, Object> getPluginEmail(Params params);
	
	public HashMap<String, Object> getPluginById(Params params);
	
	public HashMap<String, Object> getReportOverview(Params params);
	
	public HashMap<String, Object> getReportResponse(Params params);
	
	public HashMap<String, Object> getReportFeedback(Params params);
	
	public HashMap<String, Object> getListDevice(Params params);
	
	public HashMap<String, Object> editDevice(Params params);
	
	public HashMap<String, Object> deleteDevice(Params params);
	
	public HashMap<String, Object> addLayoutEmail(Params params);
	
	public HashMap<String, Object> getListLocation(Params params);
	
	public HashMap<String, Object> addRole(Params params);
	
	public HashMap<String, Object> editRole(Params params);
	
	public HashMap<String, Object> getListAccount(Params params);
	
	public HashMap<String, Object> getListActivity(Params params);
	
	public HashMap<String, Object> addZone(Params params);
	
	public HashMap<String, Object> editZone(Params params);
	
	public HashMap<String, Object> deleteZone(Params params);

}
