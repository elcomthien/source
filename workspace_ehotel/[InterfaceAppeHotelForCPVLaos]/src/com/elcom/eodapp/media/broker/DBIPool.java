package com.elcom.eodapp.media.broker;

import java.util.*;
import java.rmi.*;

import com.elcom.DBI.*;
import com.elcom.eodapp.media.cfg.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class DBIPool {
	// Log application
	private static final Logger logger = LogManager.getLogger(DBIPool.class);

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

	public DBIPool(int poolsizemax) {
		this.max_pool_size = poolsizemax;
	}

	public DBI getDBI() {
		DBI dbi = null;
		// Get a connection from the Pool
		dbi = getDBIFromPool();
		// The Pool is empty, get new a connection
		if (dbi == null)
			dbi = getNewDBI();

		return dbi;
	}

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

	// dbi = (DBI) Naming.lookup("rmi://" + config.dbihostname + ":" + config.dbiport + "/" + config.dbiservicename);
	private DBI getNewDBI() {
		String host = "10.5.80.7", port = "10002", dbiservicename = "DBInterface";
		DBI dbi = null;
		int attemptNo = 1;
		while (attemptNo < 2) {
			try {
				System.out.println("rmi://" + host + ":" + port + "/" + dbiservicename);
				dbi = (DBI) Naming.lookup("rmi://" + host + ":" + port + "/" + dbiservicename);
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
}
