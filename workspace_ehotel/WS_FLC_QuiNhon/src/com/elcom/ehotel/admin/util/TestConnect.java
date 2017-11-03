package com.elcom.ehotel.admin.util;

import java.math.BigDecimal;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

public class TestConnect {
	@SuppressWarnings("unchecked")
	public void getHostBill() {
		String rs = "";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro("BEGIN GET_HOT_BILL(?); END;", params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(rs);
	}

	@SuppressWarnings("unchecked")
	public void getTest(int packMonthId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(packMonthId), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro("BEGIN PKG_SERVICE_EHOME.GET_PKGMONTH_FROM_ID(?,?); END;", params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(outParam);
	}

	public static void main(String[] args) {
		TestConnect t = new TestConnect();
		t.getTest(61);
	}
}
