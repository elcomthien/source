package com.elcom.abopuser.rvcadv.user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDBAbopUser {
	private Connection connection = null;

	public Connection getConnectionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String host = "172.16.9.188";
			String db = "ABOPDB";
			String user = "adcenter";
			String pwd = "adcenter";
			connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + db, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void disConnectDB() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getListUser(){
		getConnectionDB();
		String result = "";
		CallableStatement callableStatement = null;
		String sql = "{call abop.sp_adminGetUser(?)}";
		try {
			callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, result);
			callableStatement.executeUpdate();
			String rs = callableStatement.getNString(1);
			System.out.println(rs);
			callableStatement.close();
			disConnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		ConnectDBAbopUser c = new ConnectDBAbopUser();
		c.getListUser();
	}
}
