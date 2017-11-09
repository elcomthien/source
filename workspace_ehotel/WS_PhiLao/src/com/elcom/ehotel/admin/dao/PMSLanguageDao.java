package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.PMSLanguageModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class PMSLanguageDao {
	@SuppressWarnings("unchecked")
	public List<PMSLanguageModel> getListLanguage() {
		List<PMSLanguageModel> list = new ArrayList<PMSLanguageModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_LANGUAGE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSLanguageDao.class.toString(), SQL.GET_LIST_LANGUAGE, params, "none", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			PMSLanguageModel lang = new PMSLanguageModel();
			lang.setIdLang(outParam.get(i));
			lang.setName(outParam.get(i + 1));
			lang.setCode(outParam.get(i + 2));
			lang.setInvisible(outParam.get(i + 3));
			lang.setFlagimage(outParam.get(i + 4));
			list.add(lang);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addNewLanguage(PMSLanguageModel lang) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(lang.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(lang.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(lang.getFlagimage()), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_NEW_LANGUAGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSLanguageDao.class.toString(), SQL.ADD_NEW_LANGUAGE, params, "name,code,image", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editLanguage(PMSLanguageModel lang) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(lang.getIdLang()), 0);
		params.add(in);
		in = new SubProParam(new String(lang.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(lang.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(lang.getFlagimage()), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_LANGUAGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSLanguageDao.class.toString(), SQL.EDIT_LANGUAGE, params, "langid,name,code,image", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteLanguage(int id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_LANGUAGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSLanguageDao.class.toString(), SQL.DELETE_LANGUAGE, params, "langid", rs);
		return rs;
	}
}
