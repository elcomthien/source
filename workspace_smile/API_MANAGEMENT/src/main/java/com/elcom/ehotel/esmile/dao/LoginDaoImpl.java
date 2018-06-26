package com.elcom.ehotel.esmile.dao;

import java.util.HashMap;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.SQL;

public class LoginDaoImpl implements LoginDao {

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> checkLogin(Params obj) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(obj.getUser_name()), 0);
		params.add(in);
		in = new SubProParam(new String(obj.getPassword()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.LOGIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.LOGIN, params, "username,password", outParam.size());
		map.put("status_code", outParam.get(0));
		map.put("message", outParam.get(1));
		if(outParam.get(1).equals("OK")){
			map.put("user_id", outParam.get(2));
			map.put("user_name", outParam.get(3));
			map.put("folder_name", outParam.get(4));
			map.put("folder_size", outParam.get(5));
			map.put("user_image", outParam.get(6));
			map.put("user_role_id", outParam.get(7));
			map.put("user_role", outParam.get(8));
			map.put("session_id", outParam.get(9));
			map.put("user_mail", outParam.get(10));
		}
		return map;
	}
	
	
	public static void main(String[] args) {
		LoginDaoImpl l = new LoginDaoImpl();
		Params obj = new Params();
		obj.setUser_name("admin");
		obj.setPassword("202cb962ac59075b964b07152d234b70");
		System.out.println(l.checkLogin(obj));
	}
}
