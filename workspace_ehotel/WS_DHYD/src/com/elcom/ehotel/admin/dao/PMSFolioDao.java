package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.PMSFolioGuestModel;
import com.elcom.ehotel.admin.model.PMSFolioMessageModel;
import com.elcom.ehotel.admin.model.PMSFolioRoomModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSFolioDao {
	@SuppressWarnings("unchecked")
	public List<PMSFolioRoomModel> getListFolio() {
		List<PMSFolioRoomModel> list = new ArrayList<PMSFolioRoomModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_FOLIO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.GET_LIST_FOLIO, params, "none", outParam.size() / 3);
		for (int i = 0; i < outParam.size(); i = i + 3) {
			PMSFolioRoomModel folio = new PMSFolioRoomModel();
			folio.setRoom(outParam.get(i));
			folio.setType(outParam.get(i + 1));
			folio.setStatus(outParam.get(i + 2));
			list.add(folio);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<PMSFolioGuestModel> getListGuestForFolio(int folionum) {
		List<PMSFolioGuestModel> list = new ArrayList<PMSFolioGuestModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(folionum), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_GUEST, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.GET_LIST_GUEST, params, "folionum", outParam.size() / 10);
		for (int i = 0; i < outParam.size(); i = i + 10) {
			PMSFolioGuestModel folio = new PMSFolioGuestModel();
			folio.setFolionum(outParam.get(i));
			folio.setFirstname(outParam.get(i + 1));
			folio.setLastname(outParam.get(i + 2));
			folio.setClientname(outParam.get(i + 3));
			folio.setClientid(outParam.get(i + 4));
			folio.setPincode(outParam.get(i + 5));
			folio.setIdreservation(outParam.get(i + 6));
			folio.setArrival(outParam.get(i + 7));
			folio.setDepartment(outParam.get(i + 8));
			folio.setRoomsharer(outParam.get(i + 9));
			list.add(folio);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addOrUpdateGuest(PMSFolioGuestModel guest) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(guest.getFolionum()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(guest.getClientid()), 0);
		params.add(in);
		in = new SubProParam(new String(guest.getFirstname()), 0);
		params.add(in);
		in = new SubProParam(new String(guest.getLastname()), 0);
		params.add(in);
		in = new SubProParam(new String(guest.getClientname()), 0);
		params.add(in);
		in = new SubProParam(new String(guest.getRoomsharer()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_OR_UPDATE_GUEST, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.ADD_OR_UPDATE_GUEST, params,
				"folionum,clientid,firstname,lastname,clientname,roomsharer", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteGuest(String folionum, String clientid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(folionum), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(clientid), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_GUEST, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.DELETE_GUEST, params, "folionum,clientid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<PMSFolioMessageModel> getListMessageForRoom(String folioNum, String langId) {
		List<PMSFolioMessageModel> list = new ArrayList<PMSFolioMessageModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(folioNum), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_MESSAGE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.GET_LIST_MESSAGE, params, "folioNum,langId", outParam.size() / 10);
		for (int i = 0; i < outParam.size(); i = i + 10) {
			PMSFolioMessageModel mess = new PMSFolioMessageModel();
			mess.setRoomId(outParam.get(i));
			mess.setMessageId(outParam.get(i + 1));
			mess.setTitle(UnicodeConverter.decodeUnicode(outParam.get(i + 2)));
			mess.setContent(UnicodeConverter.decodeUnicode(outParam.get(i + 3)));
			mess.setTop(UnicodeConverter.decodeUnicode(outParam.get(i + 4)));
			mess.setBottom(UnicodeConverter.decodeUnicode(outParam.get(i + 5)));
			mess.setSender(UnicodeConverter.decodeUnicode(outParam.get(i + 6)));
			mess.setIsRead(outParam.get(i + 7));
			mess.setDateSend(outParam.get(i + 8));
			mess.setDateRead(outParam.get(i + 9));
			list.add(mess);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addMessageFolio(PMSFolioMessageModel mess) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(mess.getRoomId()), 0);
		params.add(in);
		in = new SubProParam(new String(mess.getTitle()), 0);
		params.add(in);
		in = new SubProParam(new String(mess.getContent()), 0);
		params.add(in);
		in = new SubProParam(new String(mess.getSender()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_MESSAGE_FOLIO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.ADD_MESSAGE_FOLIO, params, "folionum,title,content,sender", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteMessage(int folionum, int messId) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(folionum), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(messId), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_MESSAGE_FOLIO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.DELETE_MESSAGE_FOLIO, params, "folionum,messageId", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getSmartcardFolio(String folionum) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(folionum), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SMARTCARD_FOLIO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.GET_SMARTCARD_FOLIO, params, "folioNum", outParam.size() / 2);
		for (int i = 0; i < outParam.size(); i = i + 2) {
			HashMap<String, String> hmap = new HashMap<String, String>();
			hmap.put("serinumber", outParam.get(i));
			hmap.put("idaddress", outParam.get(i + 1));
			list.add(hmap);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int deleteSmartcard(String serinumber) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(serinumber), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_SMARTCARD_FOLIO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.DELETE_SMARTCARD_FOLIO, params, "serinumber", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListSmartcard() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_SMARTCARD, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.GET_LIST_SMARTCARD, params, "none", outParam.size() / 3);
		for (int i = 0; i < outParam.size(); i = i + 3) {
			HashMap<String, String> hmap = new HashMap<String, String>();
			hmap.put("serinumber", outParam.get(i));
			hmap.put("idaddress", outParam.get(i + 1));
			hmap.put("room", outParam.get(i + 2));
			list.add(hmap);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListPopup(String type) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(type), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_POPUP, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.GET_LIST_POPUP, params, "type", outParam.size() / 7);
		for (int i = 0; i < outParam.size(); i += 7) {
			HashMap<String, String> hmap = new HashMap<String, String>();
			hmap.put("id", outParam.get(i));
			hmap.put("name", UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			hmap.put("url", outParam.get(i + 2));
			hmap.put("timeout", outParam.get(i + 3));
			hmap.put("type", outParam.get(i + 4));
			hmap.put("ispopup", outParam.get(i + 5));
			hmap.put("date", outParam.get(i + 6));
			list.add(hmap);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addPopup(String name, String def, String timeout, String type) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(def), 0);
		params.add(in);
		in = new SubProParam(new String(timeout), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_POPUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.ADD_POPUP, params, "name,url,timeout,type", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editPopup(String id, String name, String def, String timeout) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(def), 0);
		params.add(in);
		in = new SubProParam(new String(timeout), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_POPUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.EDIT_POPUP, params, "id,name,url,timeout", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deletePopup(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_POPUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.DELETE_POPUP, params, "id", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int postPopup(String id, String listroom) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(listroom), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_POPUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSFolioDao.class.toString(), SQL.POST_POPUP, params, "id,listroom", rs);
		return rs;
	}

	public static void main(String[] args) {
		PMSFolioDao p = new PMSFolioDao();
		System.out.println(p.getListSmartcard());
	}
}
