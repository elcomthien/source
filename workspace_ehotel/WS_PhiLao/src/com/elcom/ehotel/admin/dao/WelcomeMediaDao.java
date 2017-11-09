package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.WelcomeMediaModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class WelcomeMediaDao {
	@SuppressWarnings("unchecked")
	public List<WelcomeMediaModel> getWelcomeMedia(String type) {
		List<WelcomeMediaModel> list = new ArrayList<WelcomeMediaModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(type), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_WELCOME_MEDIA, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(WelcomeMediaDao.class.toString(), SQL.GET_WELCOME_MEDIA, params, "type", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			WelcomeMediaModel wlc = new WelcomeMediaModel();
			wlc.setId(outParam.get(i));
			wlc.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			wlc.setFilename(outParam.get(i + 2));
			wlc.setIndex(outParam.get(i + 3));
			wlc.setStatus(outParam.get(i + 4));
			wlc.setType(type);
			list.add(wlc);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addWelcomeMedia(WelcomeMediaModel wlc) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(wlc.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(wlc.getFilename()), 0);
		params.add(in);
		in = new SubProParam(new String(wlc.getType()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_WELCOME_MEDIA, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(WelcomeMediaDao.class.toString(), SQL.ADD_WELCOME_MEDIA, params, "name,filename,type", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editWelcomeMedia(WelcomeMediaModel wlc) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(wlc.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(wlc.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(wlc.getFilename()), 0);
		params.add(in);
		in = new SubProParam(new String(wlc.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(wlc.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_WELCOME_MEDIA, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(WelcomeMediaDao.class.toString(), SQL.EDIT_WELCOME_MEDIA, params, "id,name,filename,index,status", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteWelcomeMedia(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_WELCOME_MEDIA, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(WelcomeMediaDao.class.toString(), SQL.DELETE_WELCOME_MEDIA, params, "id", rs);
		return rs;
	}
	
	public static void main(String[] args) {
		WelcomeMediaDao w = new WelcomeMediaDao();
		System.out.println(w.getWelcomeMedia("AUDIO"));
	}
}
