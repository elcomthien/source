package com.elcom.ehotel.esmile.dbi;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMySQL {
	static Connection dbConnect = null;
	ResultSet dbResult = null;
	String query = "";

	public static Connection getConnection() throws ClassNotFoundException, SQLException, RemoteException {
		Class.forName("com.mysql.jdbc.Driver");
		String host = null, db = null, user = null, pwd = null;
		host = "172.16.9.183";
		db = "ESMILEIOT";
		user = "esmileiot";
		pwd = "esmileiot";
		dbConnect = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + db, user, pwd);
		return dbConnect;
	}
	
	public static void main(String[] args) {
		Connection dbConnect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String host = "172.16.9.188";
			String db = "ESMILEIOT";
			String user = "esmileiot";
			String pwd = "esmileiot";
			dbConnect = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + db, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		CallableStatement callableStatement = null;
		String sql = "{call pro_iotAddDataRaw(?,?)}";
		try {
			callableStatement = dbConnect.prepareCall(sql);
			callableStatement.setString(1, "1");
			callableStatement.setString(2, "2");
			callableStatement.executeUpdate();
			callableStatement.close();
			dbConnect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}