package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.SystemAdvertiseModel;
import com.elcom.ehotel.admin.model.SystemServiceModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class SystemDao {
	@SuppressWarnings("unchecked")
	public List<SystemServiceModel> getListService(int langId) {
		List<SystemServiceModel> list = new ArrayList<SystemServiceModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_SERVICE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.GET_LIST_SERVICE, params, "langid", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			SystemServiceModel service = new SystemServiceModel();
			service.setId(outParam.get(i));
			service.setName(outParam.get(i + 1));
			service.setImage(outParam.get(i + 2));
			service.setIndex(outParam.get(i + 3));
			service.setInvisible(outParam.get(i + 4));
			list.add(service);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int editSystemService(SystemServiceModel service, int langid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(service.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(service.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(service.getImage()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langid), 0);
		params.add(in);
		in = new SubProParam(new String(service.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(service.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(service.getColor()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_SYSTEM_SERVICE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.EDIT_SYSTEM_SERVICE, params, "idservice,name,image,langid,index,invisible,color", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int updateStatusSystemSerivce(int idservice) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idservice), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_STATUS_SERVICE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.UPDATE_STATUS_SERVICE, params, "idservice", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getTextWelcome() {
		HashMap<String, String> hash = new HashMap<String, String>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_TEXT_WELCOME, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.GET_TEXT_WELCOME, params, "none", outParam.size());
		hash.put("line01", outParam.get(0));
		hash.put("line02", outParam.get(1));
		hash.put("logo", outParam.get(2));
		hash.put("logosmall", outParam.get(3));
		return hash;
	}

	@SuppressWarnings("unchecked")
	public int updateTextWelcome(String line01, String line02, String logo, String logosmall) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(line01), 0);
		params.add(in);
		in = new SubProParam(new String(line02), 0);
		params.add(in);
		in = new SubProParam(new String(logo), 0);
		params.add(in);
		in = new SubProParam(new String(logosmall), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_TEXT_WELCOME, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.UPDATE_TEXT_WELCOME, params, "line01,line02,logo,logosmall", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<SystemAdvertiseModel> getListAdvertise(String type) {
		List<SystemAdvertiseModel> list = new ArrayList<SystemAdvertiseModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(type), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.GET_LIST_ADVERTISE, params, "type", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			SystemAdvertiseModel adv = new SystemAdvertiseModel();
			adv.setId(outParam.get(i));
			adv.setName(outParam.get(i + 1));
			adv.setImage(outParam.get(i + 2));
			adv.setType(outParam.get(i + 3));
			adv.setBackground(outParam.get(i + 4));
			adv.setInvisible(outParam.get(i + 5));
			list.add(adv);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addAdvertise(SystemAdvertiseModel adv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(adv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(adv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(adv.getType()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(adv.getBackground()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(adv.getInvisible()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.ADD_ADVERTISE, params, "name,image,type,background,invisible", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editAdvertise(SystemAdvertiseModel adv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(adv.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(adv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(adv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(adv.getType()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(adv.getBackground()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(adv.getInvisible()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.EDIT_ADVERTISE, params, "idadvertise,name,image,type,background,invisible", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteAdvertise(int advid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(advid), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.DELETE_ADVERTISE, params, "idadvertise", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<SystemServiceModel> getServiceForParent(int langId, int parentid) {
		List<SystemServiceModel> list = new ArrayList<SystemServiceModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(parentid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SERVICE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.GET_SERVICE, params, "langid,parentid", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			SystemServiceModel service = new SystemServiceModel();
			service.setId(outParam.get(i));
			service.setName(outParam.get(i + 1));
			service.setImage(outParam.get(i + 2));
			service.setIndex(outParam.get(i + 3));
			service.setInvisible(outParam.get(i + 4));
			// service.setColor(outParam.get(i + 5));
			// service.setType(outParam.get(i + 6));
			System.out.println(service.toString());
			list.add(service);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getBackgroundMain() {
		HashMap<String, String> map = new HashMap<String, String>();
		String rs = "-1";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_BACKGOUND_MAIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.GET_BACKGOUND_MAIN, params, "none", 1);
		map.put("image", rs);
		return map;
	}

	@SuppressWarnings("unchecked")
	public int updateBackgroundMain(String image) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(image), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_BACKGROUND_MAIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(SystemDao.class.toString(), SQL.UPDATE_BACKGROUND_MAIN, params, "image", rs);
		return rs;
	}
	
	public static void main(String[] args) {
		SystemDao s = new SystemDao();
		System.out.println(s.getServiceForParent(2, -1));
	}
}
