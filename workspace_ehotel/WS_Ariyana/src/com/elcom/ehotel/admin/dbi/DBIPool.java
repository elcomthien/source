package com.elcom.ehotel.admin.dbi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.Naming;
import java.util.LinkedList;
import java.util.Properties;

import com.elcom.DBI.DBI;

public class DBIPool {
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
		String host = "localhost", port = "10002", dbiservicename = "DBInterface";

		Properties prop = new Properties();
		InputStream is;
		try {
			String path = System.getProperty("user.dir");
			is = new FileInputStream(path + "/Config/eodapp.properties");
			prop.load(is);
			host = prop.getProperty("ehoteldbi.dbihostname");
			port = prop.getProperty("ehoteldbi.dbiport");
			dbiservicename = prop.getProperty("ehoteldbi.dbiservicename");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int attemptNo = 1;
		while (attemptNo < 2) {
			try {
				dbi = (DBI) Naming.lookup("rmi://" + host + ":" + port + "/"
						+ dbiservicename);
			} catch (Exception ex) {
				ex.printStackTrace();
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex1) {
				}
			}
			attemptNo++;
		}
		return dbi;
	}

	public static void main(String[] args) {
	}
}