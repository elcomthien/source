package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.elcom.ehotel.esmile.dao.eSmileDao;
import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.util.Encryptor;

public class eSmileServiceImpl implements eSmileService {
	@Autowired
	private eSmileDao esmileDao;

	@Override
	public HashMap<String, Object> addLayout(Params params) {
		return esmileDao.addLayout(params);
	}

	@Override
	public HashMap<String, Object> editLayout(Params params) {
		return esmileDao.editLayout(params);
	}

	@Override
	public HashMap<String, Object> updateStatusLayout(Params params) {
		return esmileDao.updateStatusLayout(params);
	}

	@Override
	public HashMap<String, Object> deleteLayout(Params params) {
		return esmileDao.deleteLayout(params);
	}

	@Override
	public HashMap<String, Object> getLayoutId() {
		return esmileDao.getLayoutId();
	}

	@Override
	public HashMap<String, Object> getLayout(String userid, String layoutid, String sessionid) {
		return esmileDao.getLayout(userid, layoutid, sessionid);
	}

	@Override
	public HashMap<String, Object> addPage(Params params) {
		return esmileDao.addPage(params);
	}

	@Override
	public HashMap<String, Object> addObjectContent(Params params) {
		return esmileDao.addObjectContent(params);
	}

	@Override
	public HashMap<String, Object> addObject(Params params) {
		return esmileDao.addObject(params);
	}

	@Override
	public HashMap<String, Object> getSubjectTemplate(Params params) {
		return esmileDao.getSubjectTemplate(params);
	}

	@Override
	public HashMap<String, Object> getTemplate(Params params) {
		return esmileDao.getTemplate(params);
	}

	@Override
	public HashMap<String, Object> addAccount(Params params) {
		params.setPassword(Encryptor.encryptPassword(params.getPassword()));
		return esmileDao.addAccount(params);
	}

	@Override
	public HashMap<String, Object> editAccount(Params params) {
		params.setPassword(Encryptor.encryptPassword(params.getPassword()));
		return esmileDao.editAccount(params);
	}

	@Override
	public HashMap<String, Object> postRating(Params params) {
		return esmileDao.postRating(params);
	}

	@Override
	public HashMap<String, Object> getZone(Params params) {
		return esmileDao.getZone(params);
	}

	@Override
	public HashMap<String, Object> resetPassword(Params params) {
		params.setPassword(Encryptor.encryptPassword(params.getPassword()));
		params.setConfirm_password(Encryptor.encryptPassword(params.getConfirm_password()));
		return esmileDao.resetPassword(params);
	}

	@Override
	public HashMap<String, Object> deleteAccount(Params params) {
		String listuser = "";
		for (int i = 0; i < params.getList_user().length; i++)
			listuser = params.getList_user()[i] + ",";
		listuser = listuser.substring(0, listuser.length() - 1);
		return esmileDao.deleteAccount(params, listuser);
	}

	@Override
	public HashMap<String, Object> getSession(Params params) {
		return esmileDao.getSession(params);
	}

	@Override
	public HashMap<String, Object> updateSession(Params params) {
		return esmileDao.updateSession(params);
	}

	@Override
	public HashMap<String, Object> getInfoFilter(Params params) {
		return esmileDao.getInfoFilter(params);
	}

	@Override
	public HashMap<String, Object> addDevice(Params params) {
		return esmileDao.addDevice(params);
	}

	@Override
	public HashMap<String, Object> getRole(Params params) {
		return esmileDao.getRole(params);
	}

	@Override
	public HashMap<String, Object> getFeedbackLayout(Params params) {
		return esmileDao.getFeedbackLayout(params);
	}

	@Override
	public HashMap<String, Object> addPlugin(Params params) {
		return esmileDao.addPlugin(params);
	}

	@Override
	public HashMap<String, Object> getPlugin(Params params) {
		return esmileDao.getPlugin(params);
	}

	@Override
	public HashMap<String, Object> editPlugin(Params params) {
		return esmileDao.editPlugin(params);
	}

	@Override
	public HashMap<String, Object> deletePlugin(Params params) {
		return esmileDao.deletePlugin(params);
	}

	@Override
	public HashMap<String, Object> addPluginEmail(Params params) {
		return esmileDao.addPluginEmail(params);
	}

	@Override
	public HashMap<String, Object> boxLogin(Params params) {
		return esmileDao.boxLogin(params);
	}

	@Override
	public HashMap<String, Object> boxGetLink(Params params) {
		return esmileDao.boxGetLink(params);
	}

	@Override
	public HashMap<String, Object> postPlugin(Params params) {
		return esmileDao.postPlugin(params);
	}

	@Override
	public HashMap<String, Object> getFeedbackPluginDeatail(Params params) {
		return esmileDao.getFeedbackPluginDeatail(params);
	}

	@Override
	public HashMap<String, Object> getFeedbackPlugin(Params params) {
		return esmileDao.getFeedbackPlugin(params);
	}

	@Override
	public HashMap<String, Object> getPluginEmail(Params params) {
		return esmileDao.getPluginEmail(params);
	}

	@Override
	public HashMap<String, Object> getPluginById(Params params) {
		return esmileDao.getPluginById(params);
	}

	@Override
	public HashMap<String, Object> getReportOverview(Params params) {
		return esmileDao.getReportOverview(params);
	}

	@Override
	public HashMap<String, Object> getReportResponse(Params params) {
		return esmileDao.getReportResponse(params);
	}

	@Override
	public HashMap<String, Object> getReportFeedback(Params params) {
		return esmileDao.getReportFeedback(params);
	}

	@Override
	public HashMap<String, Object> getListDevice(Params params) {
		return esmileDao.getListDevice(params);
	}

	@Override
	public HashMap<String, Object> editDevice(Params params) {
		return esmileDao.getListDevice(params);
	}

	@Override
	public HashMap<String, Object> deleteDevice(Params params) {
		return esmileDao.getListDevice(params);
	}

	@Override
	public HashMap<String, Object> addLayoutEmail(Params params) {
		return esmileDao.addLayoutEmail(params);
	}

	@Override
	public HashMap<String, Object> getListLocation(Params params) {
		return esmileDao.getListLocation(params);
	}

	@Override
	public HashMap<String, Object> addRole(Params params) {
		return esmileDao.addRole(params);
	}

	@Override
	public HashMap<String, Object> editRole(Params params) {
		return esmileDao.editRole(params);
	}

	@Override
	public HashMap<String, Object> getListAccount(Params params) {
		return esmileDao.getListAccount(params);
	}
	
	@Override
	public HashMap<String, Object> getListActivity(Params params) {
		return esmileDao.getListActivity(params);
	}
	
	@Override
	public HashMap<String, Object> addZone(Params params) {
		return esmileDao.addZone(params);
	}
	
	@Override
	public HashMap<String, Object> editZone(Params params) {
		return esmileDao.editZone(params);
	}
	
	@Override
	public HashMap<String, Object> deleteZone(Params params) {
		return esmileDao.deleteZone(params);
	}
}
