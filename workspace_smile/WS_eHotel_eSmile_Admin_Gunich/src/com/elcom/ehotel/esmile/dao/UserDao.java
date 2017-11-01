package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.UserModel;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.SQL;

public class UserDao {
	@SuppressWarnings("unchecked")
	public List<UserModel> getListUser() {
		List<UserModel> list = new ArrayList<>();
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
		LogUtil.logDao(UserDao.class.toString(), SQL.GET_LIST_USER, params, "none", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			UserModel user = new UserModel();
			user.setId(outParam.get(i));
			user.setUsername(outParam.get(i + 1));
			user.setName(outParam.get(i + 2));
			user.setImage(outParam.get(i + 3));
			user.setRole_id(outParam.get(i + 4));
			user.setRole_name(outParam.get(i + 5));
			if (user.getRole_name().equalsIgnoreCase("USER")) {
				int count = 0;
				count = ConvertUtil.convertToInteger(outParam.get(i + 6));
				if (count > 0) {
					int size = i + 7 + (count * 2);
					List<HashMap<String, String>> liststore = new ArrayList<>();
					for (int j = i + 7; j < size; j = j + 2) {
						HashMap<String, String> hm = new HashMap<>();
						hm.put("id", outParam.get(j));
						hm.put("name", outParam.get(j + 1));
						liststore.add(hm);
					}
					user.setStore(liststore);
					i = size - 6;
				}

			}
			list.add(user);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> adduser(String username, String password, String name, String image, String roleid, String liststore) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(username), 0);
		params.add(in);
		in = new SubProParam(new String(password), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(roleid), 0);
		params.add(in);
		in = new SubProParam(new String(liststore), 0);
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
		LogUtil.logDao(UserDao.class.toString(), SQL.ADD_USER, params, "username,password,fullname,image,roleid,liststore", rs);
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
	public HashMap<String, String> editUser(String userid, String username, String password, String fullname, String image, String roleid,
			String liststore) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(userid), 0);
		params.add(in);
		in = new SubProParam(new String(username), 0);
		params.add(in);
		in = new SubProParam(new String(password), 0);
		params.add(in);
		in = new SubProParam(new String(fullname), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(roleid), 0);
		params.add(in);
		in = new SubProParam(new String(liststore), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_USER, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserDao.class.toString(), SQL.EDIT_USER, params, "userid,username,password,fullname,image,roleid,liststore", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deleteUser(String userid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(userid), 0);
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
		LogUtil.logDao(UserDao.class.toString(), SQL.DELETE_USER, params, "userid", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> changePassword(String username, String currentpass, String newpassword, String name, String image) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(username), 0);
		params.add(in);
		in = new SubProParam(new String(currentpass), 0);
		params.add(in);
		in = new SubProParam(new String(newpassword), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CHANGE_PASSWORD, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserDao.class.toString(), SQL.CHANGE_PASSWORD, params, "username,currentpassword,newpassword,fullname,image", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListStore() {
		List<HashMap<String, String>> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_STORE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(UserDao.class.toString(), SQL.GET_LIST_STORE, params, "none", outParam.size() / 3);
		for (int i = 0; i < outParam.size(); i = i + 3) {
			HashMap<String, String> store = new HashMap<>();
			store.put("id", outParam.get(i));
			store.put("name", outParam.get(i + 1));
			store.put("image", outParam.get(i + 2));
			list.add(store);
		}
		return list;
	}

	public static void main(String[] args) {
		UserDao u = new UserDao();
		System.out.println(u.getListUser());
	}
}
