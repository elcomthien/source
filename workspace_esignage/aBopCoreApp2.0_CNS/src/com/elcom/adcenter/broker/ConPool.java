package com.elcom.adcenter.broker;

import java.sql.Connection;
import java.util.LinkedList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.adcenter.cfg.Configuration;
import com.elcom.adcenter.cfg.ConfigurationLoader;

public class ConPool {
	private static final Logger logger = LogManager.getLogger(ConPool.class);

	@SuppressWarnings("rawtypes")
	private LinkedList pool = new LinkedList();
	// Mutable exclusive object
	private Object mutex = new Object();
	private int max_pool_size = 10;
	// Get configuration object
	private static Configuration config = null;

	// Read configuration params
	static {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		config = loader.getConfiguration();
	}

	public ConPool(int poolsizemax) {
		Connection conn = null;
		this.max_pool_size = poolsizemax;
		for (int i = 0; i < this.max_pool_size; i++) {
			conn = getNewConn();
			putConn(conn);
		}
	}

	public Connection getConn() {
		Connection conn = null;
		// Get a connection from the Pool
		conn = getConnFromPool();
		// The Pool is empty, get new a connection
		if (conn == null) {
			conn = getNewConn();
			putConn(conn);
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

	private Connection getConnFromPool() {
		synchronized (mutex) {
			Connection conn = null;
			if (pool.size() != 0)
				conn = (Connection) pool.removeLast();

			logger.info("ConPool.getConnFromPool = " + conn + " - pool: " + pool.size());
			return conn;
		}
	}

	private Connection getNewConn() {
		Connection conn = null;

		int attemptNo = 1;
		String sconn = "";
		// While connect to the DBI module failed!
		// Trying in config.conn_tries_number times
		while (attemptNo < config.conn_tries_numbers) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				sconn = "jdbc:mysql://" + config.host + ":" + config.port + "/" + config.database + "?user=" + config.user + "&password="
						+ config.pass + "&noAccessToProcedureBodies=true&characterEncoding=utf8";
//				 sconn = "jdbc:mysql://172.16.9.188:3306/ABOPDB_NEXUS?user=adcenter&password=adcenter&noAccessToProcedureBodies=true&characterEncoding=utf8";
				System.out.println("Chuoi ket noi: " + sconn);
				conn = java.sql.DriverManager.getConnection(sconn);
				break;

			} catch (Exception ex) {
				// If connect to the DBI failed, sleep for a while, then retrying
				String msg = "Connect to the Database failed in times: " + attemptNo;
				ex.printStackTrace();
				logger.error(msg, ex);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex1) {
				}
				attemptNo++;
			}
		}

		return conn;
	}
}
