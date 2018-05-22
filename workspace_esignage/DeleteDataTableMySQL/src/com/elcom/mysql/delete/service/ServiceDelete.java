package com.elcom.mysql.delete.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.elcom.mysql.delete.dao.ConnectionDB;
import com.elcom.mysql.delete.util.ConfigModel;
import com.elcom.mysql.delete.util.UtilBasic;

public class ServiceDelete {
	private ConnectionDB connectionDB = new ConnectionDB();
	private ConfigModel configModel = new ConfigModel();
	private static final Logger log = Logger.getLogger(ServiceDelete.class);

	public boolean deleteDataTable(String table) {
		boolean flag = true;
		Connection connection = null;
		connection = connectionDB.getConnection();
		String query = "TRUNCATE TABLE " + table;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public boolean deleteService() {
		log.info("Begin delete data table");
		boolean flag = true;
		String temp = configModel.getDbtable();
		String[] arr = temp.split(",");
		for (int i = 0; i < arr.length; i++) {
			log.info("Delete data of table: " + arr[i]);
			flag = deleteDataTable(arr[i]);
		}
		flag = createDataDefault();
		UtilBasic.updateDateReset();
		return flag;
	}

	public boolean createDataDefault() {
		log.info("Add data default qms");
		boolean flag = true;
		CallableStatement callableStatement = null;
		String sql = "{call sp_addDefaultDataQMS()}";
		Connection connection = null;
		connection = connectionDB.getConnection();
		try {
			callableStatement = connection.prepareCall(sql);
			callableStatement.executeUpdate();
			callableStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
