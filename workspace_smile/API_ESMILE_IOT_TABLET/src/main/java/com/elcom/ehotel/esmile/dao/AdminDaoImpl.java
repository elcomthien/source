package com.elcom.ehotel.esmile.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.elcom.ehotel.esmile.dbi.ConnectionMySQL;
import com.elcom.ehotel.esmile.model.Params;

public class AdminDaoImpl implements AdminDao {

	@Override
	public HashMap<String, Object> getStore(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getStore(?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getUser_id());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(2);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			for (int i = 0; i < arr.length; i += 2) {
				HashMap<String, String> store = new HashMap<String, String>();
				store.put("store_id", arr[i]);
				store.put("store_name", arr[i + 1]);
				list.add(store);
			}
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getRealTime(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getRealTime(?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getStore_id());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(2);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			for (int i = 0; i < arr.length; i += 10) {
				HashMap<String, String> store = new HashMap<String, String>();
				store.put("table_id", arr[i]);
				store.put("table_name", arr[i + 1]);
				store.put("call_order", arr[i + 2]);
				store.put("call_bill", arr[i + 3]);
				store.put("call_time_order", arr[i + 4]);
				store.put("call_time_bill", arr[i + 5]);
				store.put("wait_time_order", arr[i + 6]);
				store.put("wait_time_bill", arr[i + 7]);
				store.put("like", arr[i + 8]);
				store.put("dislike", arr[i + 9]);
				list.add(store);
			}
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getRealTimeDetail(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getRealTime(?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getStore_id());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(2);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			for (int i = 0; i < arr.length; i += 10) {
				HashMap<String, String> order = new HashMap<String, String>();
				HashMap<String, String> bill = new HashMap<String, String>();
				if (!arr[i + 6].equals("00:00:00")) {
					order.put("table_id", arr[i]);
					order.put("table_name", arr[i + 1]);
					order.put("service_name", "ORDER");
					order.put("call_num", arr[i + 2]);
					order.put("call_time", arr[i + 4]);
					order.put("wait_time", arr[i + 6]);
					order.put("like", arr[i + 8]);
					order.put("dislike", arr[i + 9]);
					list.add(order);
				}
				if (!arr[i + 7].equals("00:00:00")) {
					bill.put("table_id", arr[i]);
					bill.put("table_name", arr[i + 1]);
					bill.put("service_name", "BILL");
					bill.put("call_num", arr[i + 3]);
					bill.put("call_time", arr[i + 5]);
					bill.put("wait_time", arr[i + 7]);
					bill.put("like", arr[i + 8]);
					bill.put("dislike", arr[i + 9]);
					list.add(bill);
				}
			}
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getTable(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getTable(?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getStore_id());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(2);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			for (int i = 0; i < arr.length; i += 4) {
				HashMap<String, String> store = new HashMap<String, String>();
				store.put("table_id", arr[i]);
				store.put("table_name", arr[i + 1]);
				store.put("position", arr[i + 2]);
				store.put("area_id", arr[i + 3]);
				list.add(store);
			}
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> login(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_login(?,?,?)}";
		String rs = "";
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
			map.put("user_id", arr[0]);
			map.put("role_id", arr[1]);
			map.put("role_name", arr[2]);
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getSumLike(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getLike(?,?,?,?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getStore_id());
			callableStatement.setString(2, params.getTable_id());
			callableStatement.setString(3, params.getFrom());
			callableStatement.setString(4, params.getTo());
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(5);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			for (int i = 0; i < arr.length; i += 3) {
				HashMap<String, String> sum = new HashMap<String, String>();
				sum.put("date_time", arr[i]);
				sum.put("like", arr[i + 1]);
				sum.put("dislike", arr[i + 2]);
				list.add(sum);
			}
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getSumWait(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getWait(?,?,?,?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getStore_id());
			callableStatement.setString(2, params.getTable_id());
			callableStatement.setString(3, params.getFrom());
			callableStatement.setString(4, params.getTo());
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(5);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			for (int i = 0; i < arr.length; i += 2) {
				HashMap<String, String> sum = new HashMap<String, String>();
				sum.put("date_time", arr[i]);
				sum.put("wait_time", arr[i + 1]);
				list.add(sum);
			}
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> postDone(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_postDone(?,?,?,?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getStore_id());
			callableStatement.setString(2, params.getTable_id());
			callableStatement.setString(3, params.getStatus());
			callableStatement.setString(4, params.getService_name());
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(5);
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
	public HashMap<String, Object> getInfoTable(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getInfoTable(?,?,?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getTable_id());
			callableStatement.setString(2, params.getFrom());
			callableStatement.setString(3, params.getTo());
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(4);
			callableStatement.close();
			dbConnection.close();
			// System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			// for (int j = 0; j < arr.length; j++)
			// System.out.println(j + "----" + arr[j]);
			int count = Integer.parseInt(arr[0]);

			String today = "";
			HashMap<String, Object> day = new HashMap<String, Object>();
			List<HashMap<String, Object>> listtb = new ArrayList<HashMap<String, Object>>();
			for (int i = 1; i < arr.length; i += (count * 5) + 1) {
				listtb = new ArrayList<HashMap<String, Object>>();
				day = new HashMap<String, Object>();
				// if ((i - 1) % 11 == 0) {
				today = arr[i];
				for (int j = i + 1; j < i + 1 + (count * 5); j += 5) {
					HashMap<String, Object> table = new HashMap<String, Object>();
					table.put("table_id", arr[j]);
					table.put("table_name", arr[j + 1]);
					table.put("wait_time", arr[j + 2]);
					table.put("like", arr[j + 3]);
					table.put("dislike", arr[j + 4]);
					listtb.add(table);
				}
				day.put("date_time", today);
				day.put("tables", listtb);
				list.add(day);
				// }
			}
			// System.out.println(list);
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getQuickStats(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getQuickStats(?,?,?,?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getUser_id());
			callableStatement.setString(2, params.getStore_id());
			callableStatement.setString(3, params.getFrom());
			callableStatement.setString(4, params.getTo());
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(5);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arr = rs.split(",");
		if (arr[0].equals("-1")) {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		} else {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("waiting", arr[0]);
			map.put("wait_time", arr[1]);
			map.put("service_order", arr[2]);
			map.put("service_bill", arr[3]);
			map.put("like", arr[4]);
			map.put("dislike", arr[5]);
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getHistory(Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		String sql = "{call pro_admin_getHistory(?,?,?,?)}";
		String rs = "";
		try {
			dbConnection = ConnectionMySQL.getConnection();
			callableStatement = dbConnection.prepareCall(sql);
			callableStatement.setString(1, params.getFrom());
			callableStatement.setString(2, params.getTo());
			callableStatement.setString(3, params.getTable_id());
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.execute();
			rs = callableStatement.getString(4);
			callableStatement.close();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(rs.length());
		if (rs.length() > 1) {
			String[] arr = rs.split(",");
			for (int i = 0; i < arr.length; i += 4) {
				HashMap<String, String> his = new HashMap<String, String>();
				his.put("table_id", arr[i]);
				his.put("table_name", arr[i + 1]);
				his.put("date_time", arr[i + 2]);
				his.put("service_name", arr[i + 3]);
				list.add(his);
			}
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
		} else {
			map.put("status_code", "500");
			map.put("message", "500 Internal Server Error");
		}
		return map;
	}

	public static void main(String[] args) {
		AdminDaoImpl a = new AdminDaoImpl();
		Params params = new Params();
		params.setStore_id("-1");
		params.setUser_id("1");
		params.setFrom("01/01/2018 00:00");
		params.setTo("24/01/2018 20:00");
		params.setEmp_id("1");
		params.setArea_id("-1");
		params.setTable_id("111111,111112");
		// System.out.println(a.getTable(params));
		// a.getInfoTable(params);
//		System.out.println(a.getQuickStats(params));
//		params.setStore_id("1");
		System.out.println(a.getHistory(params));
	}
}
