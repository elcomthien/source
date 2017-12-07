package elcom.connect;

import java.rmi.*;

import org.apache.log4j.Logger;

import com.elcom.DBI.DBI;

public class DBIManager {
	private static Logger log = Logger.getLogger(DBIManager.class);

	private String url;

	DBI dbiCore;

	public DBIManager(String url) {
		this.url = url;
	}

	public DBI getConn() {
		return (DBI) mkConnectDBI();

	}

	private synchronized java.rmi.Remote mkConnectDBI() {
		return lookup();
	}

	private DBI lookup() {
		try {
			// Clients requests service object reference
			log.info("url =" + url);
			dbiCore = (DBI) Naming.lookup(url);
		} catch (NotBoundException ex) {
			log.error("Url is not currently bound", ex);
		} catch (java.net.MalformedURLException ex) {
			log.error("Name is not an appropriately formatted URL", ex);
		} catch (RemoteException ex) {
			log.error("registry could not be contacted", ex);
		}

		return (dbiCore);
	}
}
