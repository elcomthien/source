package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.SQL;

public class ExchangeDao {
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getExchange() {
		List<HashMap<String, String>> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ExchangeDao.class.toString(), SQL.GET_EXCHANGE, params, "none", outParam.size() / 10);
		for (int i = 0; i < outParam.size(); i = i + 10) {
			HashMap<String, String> hm = new HashMap<>();
			hm.put("id", outParam.get(i));
			hm.put("name", outParam.get(i + 1));
			hm.put("code", outParam.get(i + 2));
			hm.put("desc", outParam.get(i + 3));
			hm.put("image", outParam.get(i + 4));
			hm.put("buy", outParam.get(i + 5));
			hm.put("transfer", outParam.get(i + 6));
			hm.put("sale", outParam.get(i + 7));
			hm.put("status", outParam.get(i + 8));
			hm.put("index", outParam.get(i + 9));
			list.add(hm);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> addExchange(String name, String def, String image, String buy, String transfer, String sale,
			String status) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(def), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(buy), 0);
		params.add(in);
		in = new SubProParam(new String(transfer), 0);
		params.add(in);
		in = new SubProParam(new String(sale), 0);
		params.add(in);
		in = new SubProParam(new String(status), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ExchangeDao.class.toString(), SQL.ADD_EXCHANGE, params, "name,def,image,buy,transfer,sale,status", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editExchange(String id, String name, String def, String image, String buy, String transfer, String sale,
			String status) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(def), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(buy), 0);
		params.add(in);
		in = new SubProParam(new String(transfer), 0);
		params.add(in);
		in = new SubProParam(new String(sale), 0);
		params.add(in);
		in = new SubProParam(new String(status), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ExchangeDao.class.toString(), SQL.EDIT_EXCHANGE, params, "id,name,def,image,buy,transfer,sale,status", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deleteExchange(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ExchangeDao.class.toString(), SQL.DELETE_EXCHANGE, params, "id", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}
}
