package com.elcom.ehotel.ping.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.ping.model.TVModel;
import com.elcom.ehotel.ping.util.ConvertUtil;
import com.elcom.ehotel.ping.util.SQL;

public class PingDao {

	@SuppressWarnings("unchecked")
	public List<TVModel> getListIp() {
		List<TVModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_IP, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 3) {
			TVModel tv = new TVModel();
			tv.setKey(outParam.get(i));
			tv.setIp(outParam.get(i + 1));
			tv.setStatus(ConvertUtil.convertToInteger(outParam.get(i + 2)));
			list.add(tv);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int updateStatus(String serinumber, int status) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(serinumber), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(status), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_STATUS, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args) {
		PingDao p = new PingDao();
		System.out.println(p.getListIp());
	}

}
