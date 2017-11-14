package com.elcom.ehotel.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elcom.ehotel.admin.dao.FlightScheduleDao;
import com.elcom.ehotel.admin.model.AirportModel;
import com.elcom.ehotel.admin.util.Util;

public class FlightScheduleService {

	private FlightScheduleDao flightScheduleDao = new FlightScheduleDao();

	public Map<String, Object> getListAirport(String langid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<AirportModel> list = new ArrayList<AirportModel>();
		list = flightScheduleDao.getListAirport(langid);
		if (list.size() > 0) {
			map.put("status", 1);
			map.put("data", list);
		} else {
			map.put("status", -1);
			map.put("message", "Data null or function error???");
		}
		return map;
	}

	public Map<String, Object> addAirport(AirportModel air) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = flightScheduleDao.addAirport(air);
		map = Util.resultToMap(rs);
		return map;
	}

	public Map<String, Object> editAirport(AirportModel air) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = flightScheduleDao.editAirport(air);
		map = Util.resultToMap(rs);
		return map;
	}

	public Map<String, Object> deleteAirport(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = flightScheduleDao.deleteAirport(id);
		map = Util.resultToMap(rs);
		return map;
	}

}
