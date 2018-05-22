package com.elcom.ehotel.admin.dao;

import java.util.HashMap;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class InterafaceDao {
	@SuppressWarnings("unchecked")
	public int checkinGuest(HashMap<String, String> guest) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(guest.get("folionum")), 0);
		params.add(in);
		in = new SubProParam(new String(guest.get("name")), 0);
		params.add(in);
		in = new SubProParam(new String(guest.get("guestid")), 0);
		params.add(in);
		in = new SubProParam(new String(guest.get("reservation")), 0);
		params.add(in);
		in = new SubProParam(new String(guest.get("arrival")), 0);
		params.add(in);
		in = new SubProParam(new String(guest.get("departure")), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.INTERFACE_CHECKIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.INTERFACE_CHECKIN, params, "room,name,guestid,reservation,arrival,departure", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int checkoutGuest(HashMap<String, String> guest) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(guest.get("folionum")), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.INTERFACE_CHECKOUT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.INTERFACE_CHECKOUT, params, "room", rs);
		return rs;
	}
}
