package com.elcom.ehotel.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elcom.ehotel.admin.dao.PMSFolioDao;
import com.elcom.ehotel.admin.model.PMSFolioGuestModel;
import com.elcom.ehotel.admin.model.PMSFolioMessageModel;
import com.elcom.ehotel.admin.model.PMSFolioRoomModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSFolioService {
	private PMSFolioDao pmsFolioDao = new PMSFolioDao();

	public List<PMSFolioRoomModel> getListFolio() {
		return pmsFolioDao.getListFolio();
	}

	public List<PMSFolioGuestModel> getListGuestForFolio(String folionum) {
		return pmsFolioDao.getListGuestForFolio(ConvertUtil.convertToInteger(folionum));
	}

	public int addOrUpdateGuest(PMSFolioGuestModel guest) {
		return pmsFolioDao.addOrUpdateGuest(guest);
	}

	public int deleteGuest(String folionum, String clientid) {
		return pmsFolioDao.deleteGuest(folionum, clientid);
	}

	public List<PMSFolioMessageModel> getListMessageForRoom(String folioNum, String langId) {
		return pmsFolioDao.getListMessageForRoom(folioNum, langId);
	}

	public int addMessageFolio(PMSFolioMessageModel mess) {
		mess.setTitle(UnicodeConverter.encodeUnicode(mess.getTitle()));
		mess.setContent(UnicodeConverter.encodeUnicode(mess.getContent()));
		mess.setSender(UnicodeConverter.encodeUnicode(mess.getSender()));
		return pmsFolioDao.addMessageFolio(mess);
	}

	public int deleteMessageFolio(String folionum, String messId) {
		return pmsFolioDao.deleteMessage(ConvertUtil.convertToInteger(folionum), ConvertUtil.convertToInteger(messId));
	}

	public List<HashMap<String, String>> getSmartcardFolio(String folionum) {
		return pmsFolioDao.getSmartcardFolio(folionum);
	}

	public int deleteSmartcard(String serinumber) {
		return pmsFolioDao.deleteSmartcard(serinumber);
	}

	public List<HashMap<String, String>> getListSmartcard() {
		return pmsFolioDao.getListSmartcard();
	}

	public Map<String, Object> getListFolioPreview() {
		return pmsFolioDao.getListFolioPreview();
	}

	public int updatePreview(String room, String ispreview) {
		return pmsFolioDao.updatePreview(room, ispreview);
	}

	public int publicPreview() {
		return pmsFolioDao.publicPreview();
	}

	public List<HashMap<String, String>> getListGroup() {
		return pmsFolioDao.getListGroup();
	}

	public List<PMSFolioRoomModel> getListFolioGroup(String id) {
		return pmsFolioDao.getListFolioGroup(id);
	}

	public int addGroup(String name) {
		return pmsFolioDao.addGroup(name);
	}

	public int changeFolioGroup(String leftGroup, String listLeft, String rightGroup, String listRight) {
		return pmsFolioDao.changeFolioGroup(leftGroup, listLeft, rightGroup, listRight);
	}

	public int editGroup(String id, String name) {
		return pmsFolioDao.editGroup(id, name);
	}

	public int deleteGroup(String id) {
		return pmsFolioDao.deleteGroup(id);
	}
}
