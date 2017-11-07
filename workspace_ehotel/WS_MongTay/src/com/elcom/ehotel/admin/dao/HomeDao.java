package com.elcom.ehotel.admin.dao;

import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class HomeDao {

	@SuppressWarnings("unchecked")
	public int checkLogin(String username, String password) {
		int rs = -1;
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
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		LogUtil.logDao(HomeDao.class.toString(), SQL.CHECK_LOGIN, params, "uaername,password", rs);
		
		return rs;
	}
}
