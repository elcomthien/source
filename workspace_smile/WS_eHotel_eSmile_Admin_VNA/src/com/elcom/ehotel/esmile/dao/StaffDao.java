package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.StaffModel;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.SQL;

public class StaffDao {
	@SuppressWarnings("unchecked")
	public List<StaffModel> getListStaff() {
		List<StaffModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(StaffDao.class.toString(), SQL.GET_LIST_STAFF, params, "none", outParam.size() / 7);
		for (int i = 0; i < outParam.size(); i = i + 7) {
			StaffModel staff = new StaffModel();
			staff.setId(outParam.get(i));
			staff.setUsername(outParam.get(i + 1));
			staff.setName(outParam.get(i + 2));
			staff.setImage(outParam.get(i + 3));
			staff.setIsLogin(outParam.get(i + 4));
			staff.setIsActive(outParam.get(i + 5));
			staff.setLocation(outParam.get(i + 6));
			list.add(staff);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> addStaff(String username, String password, String fullname, String image) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(username), 0);
		params.add(in);
		in = new SubProParam(new String(password), 0);
		params.add(in);
		in = new SubProParam(new String(fullname), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(StaffDao.class.toString(), SQL.ADD_STAFF, params, "username,password,fullname,image", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		if (rs > 0) {
			hm.put("status", String.valueOf(1));
			hm.put("id", String.valueOf(rs));
			hm.put("message", "OK");
		} else {
			hm.put("status", String.valueOf(rs));
			hm.put("message", "ERROR");
		}
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editStaff(String staffid, String password, String fullname, String image) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(staffid), 0);
		params.add(in);
		in = new SubProParam(new String(password), 0);
		params.add(in);
		in = new SubProParam(new String(fullname), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(StaffDao.class.toString(), SQL.EDIT_STAFF, params, "staffid,password,fullname,image", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deleteStaff(String staffid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(staffid), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(StaffDao.class.toString(), SQL.DELETE_STAFF, params, "staffid", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getHistory(String liststaff, String listlocation, String from, String to) {
		List<HashMap<String, String>> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_HISTORY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(StaffDao.class.toString(), SQL.GET_HISTORY, params, "liststaff,listlocation,from,to", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			HashMap<String, String> his = new HashMap<>();
			his.put("employee_id", outParam.get(i));
			his.put("employee_name", outParam.get(i+1));
			his.put("location_id", outParam.get(i+2));
			his.put("location_name", outParam.get(i+3));
			his.put("login", outParam.get(i+4));
			his.put("logout", outParam.get(i+5));
			list.add(his);
		}
		return list;
	}

}
