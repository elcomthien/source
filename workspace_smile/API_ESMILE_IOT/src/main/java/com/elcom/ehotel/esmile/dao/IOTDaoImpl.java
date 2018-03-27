package com.elcom.ehotel.esmile.dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.elcom.ehotel.esmile.dbi.ConnectionMySQL;

public class IOTDaoImpl implements IOTDao {

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
}
