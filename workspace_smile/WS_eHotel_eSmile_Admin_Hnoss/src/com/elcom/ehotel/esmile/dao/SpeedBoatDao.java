package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.BoatTimeModel;
import com.elcom.ehotel.esmile.model.SpeedBoatModel;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.SQL;

public class SpeedBoatDao {
	@SuppressWarnings("unchecked")
	public List<SpeedBoatModel> getSpeedBoatSchedule() {
		List<SpeedBoatModel> list = new ArrayList<SpeedBoatModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SPEED_BOAT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(DiningDao.class.toString(), SQL.GET_SPEED_BOAT, params, "none", outParam.size());
		System.out.println(outParam);
		int i = 0;
		int size = 0;
		while (i < outParam.size()) {
			SpeedBoatModel boat = new SpeedBoatModel();
			boat.setId(outParam.get(i));
			boat.setName(outParam.get(i + 1));
			boat.setInvisible(outParam.get(i + 2));
			boat.setIndex(outParam.get(i + 3));
			size = ConvertUtil.convertToInteger(outParam.get(i + 4)) * 4;
			i = i + 5;
			List<BoatTimeModel> times = new ArrayList<>();
			for (int j = 0; j < size; j = j + 4) {
				BoatTimeModel time = new BoatTimeModel();
				time.setIdboat(boat.getId());
				time.setId(outParam.get(i + j));
				time.setTime(outParam.get(i + j + 1));
				time.setInvisible(outParam.get(i + j + 2));
				time.setIndex(outParam.get(i + j + 3));
				times.add(time);
			}
			boat.setTimes(times);
			list.add(boat);
			i = i + size;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> addSpeedBoat(String name, String listtime) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(listtime), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_SPEED_BOAT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.ADD_SPEED_BOAT, params, "name,listtime", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editSpeedBoat(String id, String name, String invisible, String index) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(invisible), 0);
		params.add(in);
		in = new SubProParam(new String(index), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_SPEED_BOAT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_SPEED_BOAT, params, "id,name,invisible,index", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deleteSpeedBoat(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_SPEED_BOAT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_SPEED_BOAT, params, "id", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> addBoatTime(String idboat, String time) {
		String rs = "-1";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(idboat), 0);
		params.add(in);
		in = new SubProParam(new String(time), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_BOAT_TIME, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String[] arr = rs.split(",");
		int status = ConvertUtil.convertToInteger(arr[0]);
		LogUtil.logDao(eSmileDao.class.toString(), SQL.ADD_BOAT_TIME, params, "idboat,time", status);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(status));
		if (status == 1) {
			hm.put("message", "OK");
			hm.put("id", arr[1]);
			hm.put("time", time);
		} else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editBoatTime(String id, String time, String invisible, String index) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(time), 0);
		params.add(in);
		in = new SubProParam(new String(invisible), 0);
		params.add(in);
		in = new SubProParam(new String(index), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_BOAT_TIME, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_BOAT_TIME, params, "id,time,invisible,index", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deleteBoatTime(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_BOAT_TIME, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_BOAT_TIME, params, "id", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	public static void main(String[] args) {
		SpeedBoatDao s = new SpeedBoatDao();
		System.out.println(s.getSpeedBoatSchedule());
	}
}
