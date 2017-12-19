package com.elcom.ehotel.esmile.dao;

import java.util.HashMap;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.util.SQL;

public class LayoutDaoImpl implements LayoutDao {

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addLayout(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getName_layout()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_direction()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_LAYOUT_URL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		map.put("layout_id", rs);
		return map;
	}

	@Override
	public HashMap<String, Object> editLayout(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("layout_id", "1");
		return map;
	}

	@Override
	public HashMap<String, Object> deleteLayout(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("layout_id", "1");
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getLayout() {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LAYOUT_ID, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		map.put("layout_id", rs);
		return map;
	}
	
	public static void main(String[] args) {
		LayoutDaoImpl l = new LayoutDaoImpl();
		System.out.println(l.getLayout());
	}
}
