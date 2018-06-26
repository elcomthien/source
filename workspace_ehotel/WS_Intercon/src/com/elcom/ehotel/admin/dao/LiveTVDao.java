package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.LiveTVChannelModel;
import com.elcom.ehotel.admin.model.LiveTVSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class LiveTVDao {
	@SuppressWarnings("unchecked")
	public List<LiveTVSubjectModel> getListSubjectLiveTV(int langId) {
		List<LiveTVSubjectModel> list = new ArrayList<LiveTVSubjectModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_LIVETV_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_LIST_LIVETV_SUBJECT, params, "langid", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			LiveTVSubjectModel sub = new LiveTVSubjectModel();
			sub.setIdSubject(outParam.get(i));
			sub.setName(outParam.get(i + 1));
			sub.setImage(outParam.get(i + 2));
			sub.setStatus(outParam.get(i + 3));
			sub.setModifyDate(outParam.get(i + 4));
			sub.setOderby(outParam.get(i + 5));
			sub.setLangId(Integer.toString(langId));
			list.add(sub);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addNewSubjectLiveTV(LiveTVSubjectModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getListadd()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_LIVETV_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_LIVETV_SUBJECT, params, "name,image,status,listadd", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editSubjectLiveTV(LiveTVSubjectModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(livetv.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getOderby()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLangId()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getListadd()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getListremove()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_LIVETV_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(LiveTVDao.class.toString(), SQL.EDIT_LIVETV_SUBJECT, params,
				"idSubject,name,image,index,langid,status,listadd,listremove", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteSubjectLiveTV(int id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_LIVETV_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.DELETE_LIVETV_SUBJECT, params, "idSubject", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<LiveTVChannelModel> getListChannel(int idSubject) {
		List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_LIVETV_CHANNEL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_LIST_LIVETV_CHANNEL, params, "idSubject", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			LiveTVChannelModel ch = new LiveTVChannelModel();
			ch.setIdChannel(outParam.get(i));
			ch.setName(outParam.get(i + 1));
			ch.setCode(outParam.get(i + 2));
			ch.setLink(outParam.get(i + 3));
			ch.setStatus(outParam.get(i + 4));
			ch.setImage(outParam.get(i + 5));
			ch.setMaxindex(outParam.get(i + 6));
			ch.setLanguage(outParam.get(i + 7));
			ch.setSubtitle(outParam.get(i + 8));
			list.add(ch);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addNewChannel(LiveTVChannelModel livetv, String idSubject) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLanguage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getSubtitle()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_LIVETV_CHANNEL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_LIVETV_CHANNEL, params,
				"idSubject,name,code,link,image,status,language,subtitle", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editChannel(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(livetv.getIdChannel()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLanguage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getSubtitle()), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_LIVETV_CHANNEL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.EDIT_LIVETV_CHANNEL, params,
				"idChannel,name,code,link,image,status,language,subtitle", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteChannel(int id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_LIVETV_CHANNEL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.DELETE_LIVETV_CHANNEL, params, "idChannel", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<LiveTVChannelModel> getListChannelAdvertise() {
		List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_CHANNEL_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_LIST_CHANNEL_ADVERTISE, params, "idSubject", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			LiveTVChannelModel ch = new LiveTVChannelModel();
			ch.setIdChannel(outParam.get(i));
			ch.setName(outParam.get(i + 1));
			ch.setCode(outParam.get(i + 2));
			ch.setLink(outParam.get(i + 3));
			ch.setStatus(outParam.get(i + 4));
			ch.setImage(outParam.get(i + 5));
			ch.setMaxindex(outParam.get(i + 6));
			ch.setLanguage(outParam.get(i + 7));
			ch.setSubtitle(outParam.get(i + 8));
			list.add(ch);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addChannelAdvertise(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_CHANNEL_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_CHANNEL_ADVERTISE, params, "name,link,image,status", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editChannelAdvertise(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(livetv.getIdChannel()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_CHANNEL_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_CHANNEL_ADVERTISE, params, "channelId,name,link,image,status", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteChannelAdvertise(int id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_CHANNEL_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.DELETE_CHANNEL_ADVERTISE, params, "idChannel", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public String getListChannel() {
		String result = "";
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro("BEGIN ELIVETV.getLinkChannelAdvertise(?); END;", params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i++) {
			if (outParam.get(i).length() > 1)
				result += outParam.get(i);
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getNotify(String langId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(langId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_NOTIFY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_NOTIFY, params, "langid", outParam.size());
		HashMap<String, String> noti = new HashMap<String, String>();
		noti.put("size", outParam.get(0));
		noti.put("color", outParam.get(1));
		noti.put("text", UnicodeConverter.decodeUnicode(outParam.get(2)));
		noti.put("status", outParam.get(3));
		return noti;
	}

	@SuppressWarnings("unchecked")
	public int editNotify(String size, String color, String status, String text, String langid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(size), 0);
		params.add(in);
		in = new SubProParam(new String(color), 0);
		params.add(in);
		in = new SubProParam(new String(status), 0);
		params.add(in);
		in = new SubProParam(new String(text), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_NOTIFY, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.EDIT_NOTIFY, params, "size,color,status,text,langid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getNotifyGroup(String langId, String idGroup) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(langId), 0);
		params.add(in);
		in = new SubProParam(new String(idGroup), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_NOTIFY_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_NOTIFY_GROUP, params, "langid,idgroup", outParam.size());
		HashMap<String, String> noti = new HashMap<String, String>();
		noti.put("size", outParam.get(0));
		noti.put("color", outParam.get(1));
		noti.put("text", UnicodeConverter.decodeUnicode(outParam.get(2)));
		noti.put("status", outParam.get(3));
		return noti;
	}

	@SuppressWarnings("unchecked")
	public int editNotifyGroup(String size, String color, String status, String text, String langid, String idGroup) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(size), 0);
		params.add(in);
		in = new SubProParam(new String(color), 0);
		params.add(in);
		in = new SubProParam(new String(status), 0);
		params.add(in);
		in = new SubProParam(new String(text), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		in = new SubProParam(new String(idGroup), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_NOTIFY_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.EDIT_NOTIFY_GROUP, params, "size,color,status,text,langid,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<LiveTVChannelModel> getListChannelAdvertiseGroup(String idgroup) {
		List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(idgroup), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_CHANNEL_ADVERTISE_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_LIST_CHANNEL_ADVERTISE_GROUP, params, "idgroup", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			LiveTVChannelModel ch = new LiveTVChannelModel();
			ch.setIdChannel(outParam.get(i));
			ch.setName(outParam.get(i + 1));
			ch.setCode(outParam.get(i + 2));
			ch.setLink(outParam.get(i + 3));
			ch.setStatus(outParam.get(i + 4));
			ch.setImage(outParam.get(i + 5));
			ch.setMaxindex(outParam.get(i + 6));
			ch.setLanguage(outParam.get(i + 7));
			ch.setSubtitle(outParam.get(i + 8));
			list.add(ch);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addChannelAdvertiseGroup(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getIdgroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_CHANNEL_ADVERTISE_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_CHANNEL_ADVERTISE_GROUP, params, "name,link,image,status,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editChannelAdvertiseGroup(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(livetv.getIdChannel()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getIdgroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_CHANNEL_ADVERTISE_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_CHANNEL_ADVERTISE_GROUP, params, "channelId,name,link,image,status,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteChannelAdvertiseGroup(int id, String idgroup) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new String(idgroup), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_CHANNEL_ADVERTISE_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.DELETE_CHANNEL_ADVERTISE_GROUP, params, "idChannel,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<LiveTVChannelModel> getListChannelGroup(int idSubject, String idGroup) {
		List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		in = new SubProParam(new String(idGroup), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_LIVETV_CHANNEL_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_LIST_LIVETV_CHANNEL_GROUP, params, "idSubject,idgroup", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			LiveTVChannelModel ch = new LiveTVChannelModel();
			ch.setIdChannel(outParam.get(i));
			ch.setName(outParam.get(i + 1));
			ch.setCode(outParam.get(i + 2));
			ch.setLink(outParam.get(i + 3));
			ch.setStatus(outParam.get(i + 4));
			ch.setImage(outParam.get(i + 5));
			ch.setMaxindex(outParam.get(i + 6));
			ch.setLanguage(outParam.get(i + 7));
			ch.setSubtitle(outParam.get(i + 8));
			list.add(ch);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addNewChannelGroup(LiveTVChannelModel livetv, String idSubject) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLanguage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getSubtitle()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getIdgroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_LIVETV_CHANNEL_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_LIVETV_CHANNEL_GROUP, params,
				"idSubject,name,code,link,image,status,language,subtitle,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editChannelGroup(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(livetv.getIdChannel()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLanguage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getSubtitle()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getIdgroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_LIVETV_CHANNEL_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.EDIT_LIVETV_CHANNEL_GROUP, params,
				"idChannel,name,code,link,image,status,language,subtitle,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteChannelGroup(int id, String idGroup) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new String(idGroup), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_LIVETV_CHANNEL_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.DELETE_LIVETV_CHANNEL_GROUP, params, "idChannel,idgroup", rs);
		return rs;
	}

	public static void main(String[] args) {
		LiveTVDao l = new LiveTVDao();
		System.out.println(l.getListChannel());
	}
}
