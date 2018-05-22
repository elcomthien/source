package ehotel.connect;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.elcom.DBI.DBI;

import ehotel.config.ReaderProperties;

public class DBIConnection {
	private static Logger log = Logger.getLogger(DBIConnection.class);
	public static String dbiHost;
	public static String dbiPort;
	public static String dbiServiceName;
	public static DBIManager dbiManager;
	public static ReaderProperties configReader = null;

	public DBIConnection() {
		readerInfo();
	}

	private void readerInfo() {
		try {
			configReader = new ReaderProperties("eodapp.properties");
			dbiHost = configReader.getProperty("ehoteldbi.dbihostname",
					"192.168.0.141");
			dbiPort = configReader.getProperty("ehoteldbi.dbiport", "9999");
			dbiServiceName = configReader.getProperty(
					"ehoteldbi.dbiservicename", "DBISQL");
			String dbiURL = "rmi://" + dbiHost + ":" + dbiPort + "/"
					+ dbiServiceName;
			dbiManager = new DBIManager(dbiURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(null, e);
		}
	}

	public DBI getDBICore() {
		DBI dbi = null;
		try {
			dbi = dbiManager.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(null, e);
		}
		return dbi;
	}

}
