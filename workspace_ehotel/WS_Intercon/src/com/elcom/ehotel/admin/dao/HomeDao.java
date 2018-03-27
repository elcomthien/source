package com.elcom.ehotel.admin.dao;

import java.util.HashMap;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class HomeDao {

	@SuppressWarnings("unchecked")
	public HashMap<String, String> checkLogin(String username, String password) {
		String rs = "-1";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(username), 0);
		params.add(in);
		in = new SubProParam(new String(password), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CHECK_LOGIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(HomeDao.class.toString(), SQL.CHECK_LOGIN, params, "user,pass", 1);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] arr = rs.split(",");
		if (arr[0].equals("-1")) {
			map.put("status", arr[0]);
			map.put("message", "user not exists");
		} else if (arr[0].equals("-2")) {
			map.put("status", arr[0]);
			map.put("message", "invalid password");
		} else {
			map.put("status", "1");
//			map.put("message", "OK");
			map.put("id", arr[0]);
			map.put("role", arr[1]);
		}
		System.out.println(map);
		return map;
	}
}
