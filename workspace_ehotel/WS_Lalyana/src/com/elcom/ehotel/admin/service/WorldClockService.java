package com.elcom.ehotel.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elcom.ehotel.admin.dao.WorldClockDao;
import com.elcom.ehotel.admin.model.WorldClockModel;
import com.elcom.ehotel.admin.util.Util;

public class WorldClockService {
	private WorldClockDao worldClockDao = new WorldClockDao();

	public Map<String, Object> getListWorldClock() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorldClockModel> list = new ArrayList<WorldClockModel>();
		list = worldClockDao.getWorldClock();
		if (list.size() > 0) {
			map.put("status", 1);
			map.put("data", list);
		} else {
			map.put("status", -1);
			map.put("message", "Data null or function error???");
		}
		return map;
	}

	public Map<String, Object> addWorldClock(WorldClockModel clock) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = worldClockDao.addWorldClock(clock);
		map = Util.resultToMap(rs);
		return map;
	}

	public Map<String, Object> editWorldClock(WorldClockModel clock) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = worldClockDao.editWorldClock(clock);
		map = Util.resultToMap(rs);
		return map;
	}

	public Map<String, Object> deleteWorldClock(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = worldClockDao.deleteWorldClock(id);
		map = Util.resultToMap(rs);
		return map;
	}
}
