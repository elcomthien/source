package com.elcom.ehotel.esmile.dbi;

import java.rmi.Naming;
import java.util.LinkedList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.DBI.DBI;
import com.elcom.ehotel.esmile.util.Config;

public class DBIPool {
	private static final Logger logger = LogManager.getLogger(DBIPool.class);

	@SuppressWarnings("rawtypes")
	private LinkedList pool = new LinkedList();
	private Object mutex = new Object();
	private int max_pool_size = 10;

	public DBIPool(int poolsizemax) {
		this.max_pool_size = poolsizemax;
	}

	public DBI getDBI() throws Exception, ClassNotFoundException {
		DBI dbi = null;
		dbi = getDBIFromPool();
		if (dbi == null)
			dbi = getNewDBI();
		return dbi;
	}

	@SuppressWarnings("unchecked")
	public void putDBI(DBI dbi) {
		synchronized (mutex) {
			if (dbi != null && pool.size() < max_pool_size - 1) {
				pool.addFirst(dbi);
			}
		}
	}

	public void clearPool() {
		synchronized (mutex) {
			pool.clear();
		}
	}

	private DBI getDBIFromPool() {
		synchronized (mutex) {
			DBI dbi = null;
			if (pool.size() != 0)
				dbi = (DBI) pool.removeLast();
			return dbi;
		}
	}

	private DBI getNewDBI() throws Exception {
		DBI dbi = null;
		String host = "172.16.9.100", port = "10002";
//		Config config = new Config();
//		host = config.getDbiHost();
//		port = config.getDbiPort();
		int attemptNo = 1;
		while (attemptNo < 2) {
			try {
				System.out.println("rmi://" + host + ":" + port + "/DBInterface");
				dbi = (DBI) Naming.lookup("rmi://" + host + ":" + port + "/DBInterface");
				// System.out.println("thuc hien xong viec ket noi ORACLE " + dbi);
			} catch (Exception ex) {
				ex.printStackTrace();
				String msg = "Connect to the DBI failed in times: " + attemptNo;
				logger.error(msg, ex);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex1) {
				}
			}
			attemptNo++;
		}
		return dbi;
	}
}