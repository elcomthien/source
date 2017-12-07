package com.elcom.ehotel.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.AirportModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class FlightScheduleDao {

	@SuppressWarnings("unchecked")
	public List<AirportModel> getListAirport(String langid) {
		List<AirportModel> list = new ArrayList<AirportModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_AIRPORT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(FlightScheduleDao.class.toString(), SQL.GET_LIST_AIRPORT, params, "langid", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			AirportModel air = new AirportModel(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
					outParam.get(i + 4), outParam.get(i + 5), langid);
			list.add(air);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addAirport(AirportModel air) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(air.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(air.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(air.getImage()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_AIRPORT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(FlightScheduleDao.class.toString(), SQL.ADD_AIRPORT, params, "name,code,image", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editAirport(AirportModel air) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(air.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(air.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(air.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(air.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(air.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(air.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(air.getLangid()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_AIRPORT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(FlightScheduleDao.class.toString(), SQL.EDIT_AIRPORT, params, "id,name,code,image,status,index,langid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteAirport(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_AIRPORT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(FlightScheduleDao.class.toString(), SQL.DELETE_AIRPORT, params, "id", rs);
		return rs;
	}

	public static void main(String[] args) {
		FlightScheduleDao f = new FlightScheduleDao();
		AirportModel air = new AirportModel("HA NOI", "HAN", "image.png");
		System.out.println(f.addAirport(air));
	}
}
