package com.elcom.adcenter.rvcadv.broker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;

import config.ConfigDB;

public class ConPool {
//	private static final Logger logger = LogManager.getLogger(ConPool.class);
	@SuppressWarnings("rawtypes")
	private LinkedList pool = new LinkedList();
	// Mutable exclusive object
	private Object mutex = new Object();
	private int max_pool_size = 10;
	// Get configuration object
	@SuppressWarnings("unused")
	private static Configuration config = null;

	// Read configuration params
	static {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		config = loader.getConfiguration();
	}

	public ConPool(int poolsizemax) {
		this.max_pool_size = poolsizemax;
	}

	public Connection getConn() {
		Connection conn = null;
		// Get a connection from the Pool
		// conn = getConnFromPool();
		// The Pool is empty, get new a connection
		if (conn == null) {
			conn = getNewConn();
		}
		return conn;
	}

	@SuppressWarnings("unchecked")
	public void putConn(Connection conn) {
		synchronized (mutex) {
			if (conn != null && pool.size() < max_pool_size - 1) {
				pool.addFirst(conn);
			}
		}
	}

	public void clearPool() {
		synchronized (mutex) {
			pool.clear();
		}
	}

	@SuppressWarnings("unused")
	private Connection getConnFromPool() {
		synchronized (mutex) {
			Connection conn = null;
			if (pool.size() != 0) {
				conn = (Connection) pool.removeLast();
				if (conn == null)
					conn = getNewConn();
			}
			return conn;
		}
	}

	private Connection getNewConn() {
		// Connection conn = null;
		//
		// int attemptNo = 1;
		// String sconn = "";
		// while (attemptNo < config.conn_tries_numbers)
		// {
		// try{
		//
		// Class.forName("com.mysql.jdbc.Driver").newInstance();
		// // sconn = /*"jdbc:mysql://" + */config.connectString + ":3306/" + "ABOPDB" + "?" + "user=" + "adcenter" + "&password=" +
		// "adcenter" + "&useUnicode=true&characterEncoding=utf8";
		// sconn = /*"jdbc:mysql://" + */"172.16.9.188:3306/" + "ABOPDB" + "?" + "user=" + "adcenter" + "&password=" + "adcenter" +
		// "&useUnicode=true&characterEncoding=utf8";
		// System.out.println("ConnPool.getNewConn(): " + sconn);
		// conn = java.sql.DriverManager.getConnection(sconn);
		// break;
		//
		// } catch (Exception ex){
		// String msg = "Connect to the Database failed in times: "+ attemptNo;
		// ex.printStackTrace();
		// logger.error(msg, ex);
		// try{Thread.sleep(500);} catch (InterruptedException ex1){}
		// attemptNo++;
		// }
		// }
		//
		// return conn;
		// System.out.println("PATH = = = = == = = =  = = =  = = =  =  = = = = = = "+System.getProperty("user.dir"));
//		String filepath = ConPool.class.getResource("").getPath();
//		System.out.println(filepath);
		// String path = System.getProperty("user.dir")+"/Config/configdb.properties";
//		String path = filepath + "configdb.properties";
		String host = "";
		String port = "";
		String db = "";
		String user = "";
		String pwd = "";
		ConfigDB configDB = new ConfigDB();
		host = configDB.getHost();
		port = configDB.getPort();
		db = configDB.getDatabase();
		user = configDB.getUser();
		pwd = configDB.getPassword();
		
		// Properties prop = new Properties();
		// InputStream input = null;
		Connection dbConnect = null;
		try {
			// input = new FileInputStream("/aBop/service/app/service_admin/webapps/aBop/data/configdb.properties");
			// VMS
			// input = new FileInputStream(path);

			// prop.load(input);

			// host = prop.getProperty("hostdb");
			// port = prop.getProperty("portdb");
			// db = prop.getProperty("dbname");
			// user = prop.getProperty("userdb");
			// pwd = prop.getProperty("passdb");
//			System.out.println(host + ":" + port + "/" + db + "@" +user+ "/" +pwd);
			Class.forName("com.mysql.jdbc.Driver");
//			dbConnect = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, pwd);
			dbConnect = DriverManager.getConnection("jdbc:mysql://172.16.9.188:3306/ThienTest", "adcenter", "adcenter");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			if(dbConnect != null)
//				try {
//					dbConnect.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
		}

		// CallableStatement callableStatement = null;
		// String sql = "{call dbadcenter.sp_getadminListStbGroup(?,?)}";
		// try {
		// callableStatement = dbConnect.prepareCall(sql);
		// callableStatement.setInt(1, 105);
		// callableStatement.setString(2, "");
		// callableStatement.executeUpdate();
		// String rs = callableStatement.getNString(2);
		// System.out.println(rs);
		// callableStatement.close();
		// callableStatement.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return dbConnect;
	}
}
