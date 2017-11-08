package com.elcom.ehotel.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.UserActiveModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class UserActiveDao {
	@SuppressWarnings("unchecked")
	public int addUserActive(String username, String subject, String description) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(username), 0);
		params.add(in);
		in = new SubProParam(new String(subject), 0);
		params.add(in);
		in = new SubProParam(new String(description), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_USER_ACTIVE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.ADD_USER_ACTIVE, params, "username,subject,description", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<UserActiveModel> getListUserActive(String from, String to) {
		List<UserActiveModel> list = new ArrayList<UserActiveModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);

		try {
			params = SQL.broker.executeSubPro(SQL.GET_USER_ACTIVE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserActiveDao.class.toString(), SQL.GET_USER_ACTIVE, params, "from,to", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			UserActiveModel usr = new UserActiveModel();
			usr.setId(outParam.get(i));
			usr.setDescription(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			usr.setDatetime(outParam.get(i + 2));
			usr.setUsername(outParam.get(i + 3));
			usr.setSubject(UnicodeConverter.decodeUnicode(outParam.get(i + 4)));
			list.add(usr);
		}
		return list;
	}
	
	public static void main(String[] args) {
		UserActiveDao u = new UserActiveDao();
		System.out.println(u.getListUserActive("30/3/2017", "31/3/2017"));
	}
}
