package com.elcom.ehotel.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.WorldClockModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class WorldClockDao {
	@SuppressWarnings("unchecked")
	public List<WorldClockModel> getWorldClock() {
		List<WorldClockModel> list = new ArrayList<WorldClockModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_WORLDCLOCK, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(WorldClockDao.class.toString(), SQL.GET_LIST_WORLDCLOCK, params, "none", outParam.size() / 7);
		for (int i = 0; i < outParam.size(); i += 7) {
			WorldClockModel clock = new WorldClockModel(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
					outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6));
			list.add(clock);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addWorldClock(WorldClockModel clock) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(clock.getCity()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getNational()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getTimezone()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getImage()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_WORLDCLOCK, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(WorldClockDao.class.toString(), SQL.ADD_WORLDCLOCK, params, "city,national,timezone,image", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editWorldClock(WorldClockModel clock) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(clock.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getCity()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getNational()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getTimezone()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(clock.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_WORLDCLOCK, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(WorldClockDao.class.toString(), SQL.EDIT_WORLDCLOCK, params, "id,city,national,timezone,image,status,index", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteWorldClock(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_WORLDCLOCK, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(WorldClockDao.class.toString(), SQL.DELETE_WORLDCLOCK, params, "id", rs);
		return rs;
	}

	public static void main(String[] args) {
		WorldClockDao w = new WorldClockDao();
		System.out.println(w.getWorldClock());
	}
}
