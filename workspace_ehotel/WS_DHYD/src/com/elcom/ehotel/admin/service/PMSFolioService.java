package com.elcom.ehotel.admin.service;

import java.util.HashMap;
import java.util.List;

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

	public List<HashMap<String, String>> getListPopup(String type) {
		return pmsFolioDao.getListPopup(type);
	}

	public int addPopup(String name, String def, String timeout, String type) {
		return pmsFolioDao.addPopup(name, def, timeout, type);
	}

	public int editPopup(String id, String name, String def, String timeout) {
		return pmsFolioDao.editPopup(id, name, def, timeout);
	}

	public int deletePopup(String id) {
		return pmsFolioDao.deletePopup(id);
	}

	public int postPopup(String id, String listroom) {
		return pmsFolioDao.postPopup(id, listroom);
	}
}
