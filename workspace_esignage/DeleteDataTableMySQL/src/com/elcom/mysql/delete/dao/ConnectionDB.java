package com.elcom.mysql.delete.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.elcom.mysql.delete.util.ConfigModel;

public class ConnectionDB {
	public Connection getConnection() {
		String host = "";
		String port = "";
		String db = "";
		String user = "";
		String pwd = "";
		ConfigModel config = new ConfigModel();
		host = config.getDbhost();
		port = config.getDbport();
		db = config.getDbname();
		user = config.getDbuser();
		pwd = config.getDbpass();

		Connection dbConnect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConnect = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConnect;
	}
}
