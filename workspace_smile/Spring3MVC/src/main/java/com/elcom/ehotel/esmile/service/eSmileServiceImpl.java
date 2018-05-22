package com.elcom.ehotel.esmile.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.elcom.ehotel.esmile.dao.eSmileDao;
import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.util.Config;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.Encryptor;
import com.elcom.ehotel.esmile.util.FileUtil;

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
	public HashMap<String, Object> getListZone(Params params) {
		return esmileDao.getListZone(params);
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
		return esmileDao.editDevice(params);
	}

	@Override
	public HashMap<String, Object> deleteDevice(Params params) {
		return esmileDao.deleteDevice(params);
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

	@Override
	public HashMap<String, Object> updateStatusZone(Params params) {
		return esmileDao.updateStatusZone(params);
	}

	@Override
	public HashMap<String, Object> getListLayout(Params params) {
		return esmileDao.getListLayout(params);
	}

	@Override
	public HashMap<String, Object> saveReport(Params params) {
		Config config = new Config();
		params.setRepeat("once");
		params.setIschedule("save");
		params.setIsfinish("1");
		params.setUrl(config.getLinkjson());
		params.setPathsave(config.getPathsave());
		HashMap<String, Object> map = esmileDao.saveReport(params);
		if (ConvertUtil.convertToInteger(map.get("status_code").toString()) != 200) {
			return map;
		}
		HashMap<String, Object> data = new HashMap<String, Object>();
		if (params.getReport_type().equals("overview"))
			data = esmileDao.getReportOverview(params);
		else if (params.getReport_type().equals("feedback"))
			data = esmileDao.getReportFeedback(params);
		else if (params.getReport_type().equals("response"))
			data = esmileDao.getReportResponse(params);
		FileUtil.writeFileJson(params.getPathsave() + map.get("report_id") + ".json", data);
		return map;
	}

	@Override
	public HashMap<String, Object> scheduleReport(Params params) {
		params.setIschedule("schedule");
		params.setIsfinish("0");
		return esmileDao.saveReport(params);
	}

	@Override
	public HashMap<String, Object> getSaveReport(Params params) {
		return esmileDao.getSaveReport(params);
	}

	@Override
	public HashMap<String, Object> deleteScheduleReport(Params params) {
		return esmileDao.deleteScheduleReport(params);
	}

	@Override
	public HashMap<String, Object> getScheduleReport(Params params) {
		return esmileDao.getScheduleReport(params);
	}

	@Override
	public HashMap<String, Object> getRespondent(Params params) {
		return esmileDao.getRespondent(params);
	}

	@Override
	public HashMap<String, Object> addTagsRespondent(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < params.getData().size(); i++) {
			map = esmileDao.addTagsRespondent(params.getSession_id(), params.getData().get(i));
		}
		return map;
	}

	@Override
	public HashMap<String, Object> updateFlag(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < params.getData().size(); i++) {
			map = esmileDao.updateFlag(params.getSession_id(), params.getData().get(i));
		}
		return map;
	}

	@Override
	public HashMap<String, Object> updateImportant(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < params.getData().size(); i++) {
			map = esmileDao.updateImportant(params.getSession_id(), params.getData().get(i));
		}
		return map;
	}

	@Override
	public HashMap<String, Object> deleteRespondent(Params params) {
		return esmileDao.deleteRespondent(params);
	}

	@Override
	public HashMap<String, Object> getReportDevice(Params params) {
		return esmileDao.getReportDevice(params);
	}

	@Override
	public HashMap<String, Object> getRespondentDetail(Params params) {
		return esmileDao.getRespondentDetail(params);
	}

	@Override
	public HashMap<String, Object> getZone(Params params) {
		return esmileDao.getZone(params);
	}

	@Override
	public HashMap<String, Object> getGeoLocationDevice(Params params) {
		return esmileDao.getGeoLocationDevice(params);
	}

	@Override
	public HashMap<String, Object> getDashboardDevice(Params params) {
		return esmileDao.getDashboardDevice(params);
	}

	@Override
	public HashMap<String, Object> getDashboardOverview(Params params) {
		return esmileDao.getDashboardOverview(params);
	}

	@Override
	public HashMap<String, Object> renewalSession(Params params) {
		return esmileDao.renewalSession(params);
	}

	@Override
	public HashMap<String, Object> boxGetInfo(Params params) {
		return esmileDao.boxGetInfo(params);
	}

	@Override
	public HashMap<String, Object> setBackground(Params params) {
		return esmileDao.setBackground(params);
	}

	@Override
	public HashMap<String, Object> getNotification(Params params) {
		return esmileDao.getNotification(params);
	}
	
	@Override
	public HashMap<String, Object> getLayoutEmail(Params params) {
		return esmileDao.getLayoutEmail(params);
	}
	
	@Override
	public HashMap<String, Object> setSendEmail(Params params) {
		return esmileDao.setSendEmail(params);
	}
}
