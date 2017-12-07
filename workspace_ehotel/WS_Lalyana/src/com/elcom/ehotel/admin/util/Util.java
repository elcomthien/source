package com.elcom.ehotel.admin.util;

import java.util.HashMap;
import java.util.Map;

public class Util {
	public static Map<String, Object> resultToMap(int rs) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs == -1) {
			map.put("status", rs);
			map.put("message", "Error");
		} else if (rs == -2) {
			map.put("status", rs);
			map.put("message", "Duplicate");
		} else {
			map.put("status", rs);
			map.put("message", "Success");
		}
		return map;
	}
}
