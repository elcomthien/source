package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.PMSExchangeRateModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class PMSExchangeRateDao {
	@SuppressWarnings("unchecked")
	public List<PMSExchangeRateModel> getListExchangeRate() {
		List<PMSExchangeRateModel> list = new ArrayList<PMSExchangeRateModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSExchangeRateDao.class.toString(), SQL.GET_LIST_EXCHANGE, params, "none", outParam.size() / 10);
		for (int i = 0; i < outParam.size(); i = i + 10) {
			PMSExchangeRateModel ex = new PMSExchangeRateModel();
			ex.setId(outParam.get(i));
			ex.setName(outParam.get(i + 1));
			ex.setCode(outParam.get(i + 2));
			ex.setBuy(outParam.get(i + 3));
			ex.setSell(outParam.get(i + 4));
			ex.setTransfer(outParam.get(i + 5));
			ex.setImage(outParam.get(i + 6));
			ex.setIcon(outParam.get(i + 7));
			ex.setInvisible(outParam.get(i + 8));
			ex.setIndex(outParam.get(i + 9));
			list.add(ex);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addExchangeRate(PMSExchangeRateModel ex) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(ex.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getBuy()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getSell()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getTransfer()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getIcon()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LogUtil.logDao(PMSExchangeRateDao.class.toString(), SQL.ADD_EXCHANGE, params, "code,name,buy,sell,transfer,image,icon,invisible,index", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editExchangeRate(PMSExchangeRateModel ex) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(ex.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getBuy()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getSell()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getTransfer()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getIcon()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(ex.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(10);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LogUtil.logDao(PMSExchangeRateDao.class.toString(), SQL.EDIT_EXCHANGE, params, "id,code,name,buy,sell,transfer,image,icon,invisible,index",
				rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteExchangeRate(int id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LogUtil.logDao(PMSExchangeRateDao.class.toString(), SQL.DELETE_EXCHANGE, params, "id", rs);

		return rs;
	}

	public static void main(String[] args) {
		PMSExchangeRateDao p = new PMSExchangeRateDao();
		System.out.println(p.getListExchangeRate());
	}
}
