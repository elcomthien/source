package com.elcom.ehotel.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.UserManagerModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class UserManagerDao {

	@SuppressWarnings("unchecked")
	public List<UserManagerModel> getListUser() {
		List<UserManagerModel> list = new ArrayList<UserManagerModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_USER, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserManagerDao.class.toString(), SQL.GET_LIST_USER, params, "none", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			UserManagerModel user = new UserManagerModel();
			user.setId(outParam.get(i));
			user.setUser(outParam.get(i + 1));
			// user.setPass(outParam.get(i + 2));
			user.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 2)));
			user.setAddress(outParam.get(i + 3));
			user.setDepartment(outParam.get(i + 4));
			user.setActive(outParam.get(i + 5));
			list.add(user);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addUser(UserManagerModel user) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(user.getUser()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getPass()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getAddress()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getDepartment()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getActive()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_USER, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserManagerDao.class.toString(), SQL.ADD_USER, params, "user,pass,name,address,department,active", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editUser(UserManagerModel user) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(user.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getAddress()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getDepartment()), 0);
		params.add(in);
		in = new SubProParam(new String(user.getActive()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_USER, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserManagerDao.class.toString(), SQL.EDIT_USER, params, "id,name,address,department,active", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int changePass(String id, String newpass, String oldpass) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(newpass), 0);
		params.add(in);
		in = new SubProParam(new String(oldpass), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CHANGE_PASS, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserManagerDao.class.toString(), SQL.CHANGE_PASS, params, "id,newpass,oldpass", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteUser(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_USER, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserManagerDao.class.toString(), SQL.DELETE_USER, params, "id", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int changePassAdmin(String id, String newpass) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(newpass), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CHANGE_PASS, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserManagerDao.class.toString(), SQL.CHANGE_PASS, params, "id,newpass", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListRold(String langid) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_ROLE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserManagerDao.class.toString(), SQL.GET_LIST_ROLE, params, "langid", outParam.size() / 3);
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", outParam.get(i));
			map.put("name", outParam.get(i + 1));
			map.put("value", outParam.get(i + 2));
			list.add(map);
		}
		return list;
	}
	

}
