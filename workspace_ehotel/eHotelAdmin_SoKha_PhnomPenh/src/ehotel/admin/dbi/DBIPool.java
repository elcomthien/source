package ehotel.admin.dbi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.Naming;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.DBI.DBI;

public class DBIPool {
	// Log application
	private static final Logger logger = LogManager.getLogger(DBIPool.class);

	@SuppressWarnings("rawtypes")
	private LinkedList pool = new LinkedList();
	// Mutable exclusive object
	private Object mutex = new Object();
	private int max_pool_size = 10;

	// Get configuration object

	// Read configuration params
	/*
	 * static { ConfigurationLoader loader = ConfigurationLoader.getInstance();
	 * config = loader.getConfiguration(); }
	 */

	public DBIPool(int poolsizemax) {
		this.max_pool_size = poolsizemax;
	}

	public DBI getDBI() throws Exception, ClassNotFoundException {
		DBI dbi = null;
		// Get a connection from the Pool
		dbi = getDBIFromPool();
		// The Pool is empty, get new a connection
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
		
//		Properties prop = new Properties();
//		InputStream is;
//		try {
//			String path = DBIPool.class.getResource("").getPath();
//			path = path.substring(0, path.lastIndexOf("classes"));
//			is = new FileInputStream(path + "philao.properties");
//			prop.load(is);
//			host = prop.getProperty("ehotel.philao.getchannel.host");
//			port = prop.getProperty("ehotel.philao.getchannel.port");
//			dbiservicename = prop.getProperty("ehotel.philao.getchannel.dbiservicename");
//			is.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		
		int attemptNo = 1;
		while (attemptNo < 2) {
			try {
				dbi = (DBI) Naming.lookup("rmi://" + host + ":" + port + "/"
						+ dbiservicename);
			} catch (Exception ex) {
				ex.printStackTrace();
				String msg = "Connect to the DBI failed in times: " + attemptNo;
				logger.error(msg, ex);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex1) {
					ex1.printStackTrace();
				}
			}
			attemptNo++;
		}
		return dbi;
	}
}