package com.elcom.ehotel.esmile.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.elcom.ehotel.esmile.dbi.ConnectionMySQL;
import com.elcom.ehotel.esmile.model.Params;

public class TabletDaoImpl implements TabletDao {

	@Override
	public void addDataRaw(String table, String idbu) {
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_iotAddDataRaw(?,?)}";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, table);
			callableStatement.setString(2, idbu);
			callableStatement.executeUpdate();
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<HashMap<String, String>> getListTable(Params params) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_getListTables(?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getArea_id());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(2);
			callableStatement.close();
			dbConnection.close();
			// System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arr = rs.split(",");
		for (int i = 0; i < arr.length; i += 18) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", arr[i]);
			map.put("name", arr[i + 1]);
			map.put("no", arr[i + 2]);
			map.put("area", arr[i + 3]);
			map.put("seq1", arr[i + 4]);
			map.put("seq2", arr[i + 5]);
			map.put("image", arr[i + 6]);
			map.put("posX", arr[i + 7]);
			map.put("posY", arr[i + 8]);
			map.put("width", arr[i + 9]);
			map.put("height", arr[i + 10]);
			map.put("staff", arr[i + 11]);
			map.put("count1", arr[i + 12]);
			map.put("count2", arr[i + 13]);
			map.put("like", arr[i + 14]);
			map.put("unlike", arr[i + 15]);
			map.put("time01", arr[i + 16]);
			map.put("time02", arr[i + 17]);
			list.add(map);
		}
		return list;
	}

	@Override
	public HashMap<String, String> setLocation(Params params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String rs = "-1";
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_table_setLocation(?,?,?,?,?,?)}";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getTable_id());
			callableStatement.setString(2, params.getPosx());
			callableStatement.setString(3, params.getPosy());
			callableStatement.setString(4, params.getWidth());
			callableStatement.setString(5, params.getHeight());
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(6);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.equals("1")) {
			map.put("status_code", "200");
			map.put("message", "OK");
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, String> setStatusRequest(Params params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String rs = "-1";
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_setStatusRequest(?,?,?)}";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getTable_id());
			callableStatement.setString(2, params.getButton_id());
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(3);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.equals("1")) {
			map.put("status_code", "200");
			map.put("message", "OK");
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getRatingTime(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_getRatingTime(?,?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getFrom_date());
			callableStatement.setString(2, params.getTo_date());
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(3);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(rs);
		String[] arr = rs.split(",");
		String countlike = arr[0];
		String countunlike = arr[1];
		List<HashMap<String, String>> listlike = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> listunlike = new ArrayList<HashMap<String, String>>();
		for (int i = 2; i < arr.length; i += 4) {
			HashMap<String, String> rate = new HashMap<String, String>();
			rate.put("id", arr[i]);
			rate.put("name", arr[i + 1]);
			rate.put("button", arr[i + 2]);
			rate.put("time", arr[i + 3]);
			if (arr[i + 2].equals("3"))
				listlike.add(rate);
			else
				listunlike.add(rate);
		}
		map.put("count_like", countlike);
		map.put("count_unlike", countunlike);
		map.put("list_like", listlike);
		map.put("list_unlike", listunlike);
		return map;
	}

	@Override
	public HashMap<String, String> setNotify(Params params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String rs = "-1";
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_setNotify(?,?,?)}";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getTable_id());
			callableStatement.setString(2, params.getEmp_id());
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(3);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.equals("1")) {
			map.put("status_code", "200");
			map.put("message", "OK");
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public List<HashMap<String, String>> getNotify(Params params) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_getNotify(?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getEmp_id());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(2);
			callableStatement.close();
			dbConnection.close();
			System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			for (int i = 0; i < arr.length; i += 10) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("table_id", arr[i]);
				map.put("table_name", arr[i + 1]);
				map.put("area_id", arr[i + 2]);
				map.put("area_name", arr[i + 3]);
				map.put("no", arr[i + 4]);
				map.put("seq1", arr[i + 5]);
				map.put("seq2", arr[i + 6]);
				map.put("count1", arr[i + 7]);
				map.put("count2", arr[i + 8]);
				map.put("note", arr[i + 9]);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<HashMap<String, String>> getButton() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_getButton(?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.registerOutParameter(1, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(1);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arr = rs.split(",");
		for (int i = 0; i < arr.length; i += 6) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", arr[i]);
			map.put("name", arr[i + 1]);
			map.put("color", arr[i + 2]);
			map.put("image", arr[i + 3]);
			map.put("function", arr[i + 4]);
			map.put("stype", arr[i + 5]);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<HashMap<String, String>> getEmployee() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_getEmployee(?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.registerOutParameter(1, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(1);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arr = rs.split(",");
		for (int i = 0; i < arr.length; i += 4) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", arr[i]);
			map.put("name", arr[i + 1]);
			map.put("area_id", arr[i + 2]);
			map.put("area_name", arr[i + 3]);
			list.add(map);
		}
		return list;
	}

	@Override
	public HashMap<String, String> login(Params params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String rs = "-1";
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call proce_tablet_loginEmployee(?,?,?)}";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getUsername());
			callableStatement.setString(2, params.getPassword());
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(3);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arr = rs.split(",");
		if (arr[0].equals("-1")) {
			map.put("status_code", "500");
			map.put("message", "Username not exist");
		} else if (arr[0].equals("-2")) {
			map.put("status_code", "500");
			map.put("message", "Invalid password");
		} else {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("emp_id", arr[0]);
			map.put("area_id", arr[1]);
		}
		return map;
	}

	@Override
	public HashMap<String, String> registerDevice(Params params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String rs = "-1";
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_registerDevice(?,?,?)}";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getDevice_id());
			callableStatement.setString(2, params.getStore_id());
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(3);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arr = rs.split(",");
		if (arr[0].equals("-1")) {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		} else if (arr[0].equals("-2")) {
			map.put("status_code", "500");
			map.put("message", "Store not exist");
		} else {
			map.put("status_code", "200");
			map.put("message", "OK");
		}
		return map;
	}

	@Override
	public HashMap<String, String> getStoreInfo(Params params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String rs = "-1";
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_getStoreInfo(?,?)}";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getDevice_id());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(2);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arr = rs.split(",");
		if (arr[0].equals("-1")) {
			map.put("status_code", "500");
			map.put("message", "Device not exist");
		} else {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("store_id", arr[0]);
			map.put("store_name", arr[1]);
			map.put("hotline", arr[2]);
			map.put("address", arr[3]);
			map.put("image", arr[4]);
		}
		return map;
	}

	@Override
	public List<HashMap<String, String>> getStore() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_tablet_getStore(?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.registerOutParameter(1, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(1);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arr = rs.split(",");
		for (int i = 0; i < arr.length; i += 2) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("store_id", arr[i]);
			map.put("store_name", arr[i + 1]);
			list.add(map);
		}
		return list;
	}

	public static void main(String[] args) {
		TabletDaoImpl i = new TabletDaoImpl();
		Params params = new Params();
		params.setFrom_date("2018-1-8");
		params.setTo_date("2018-1-8");
		params.setEmp_id("1");
		params.setArea_id("-1");
		params.setStore_id("1");
		// System.out.println(i.getRatingTime(params));
		System.out.println(i.getStore());
		// System.out.println(i.getListTable(params));
		// System.out.println(i.getButton());

	}
}
