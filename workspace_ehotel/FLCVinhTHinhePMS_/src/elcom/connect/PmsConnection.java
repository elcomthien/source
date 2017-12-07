package elcom.connect;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.DBI;

import elcom.config.ReaderProperties;

public class PmsConnection {
	private static Logger log = Logger.getLogger(PmsConnection.class);
	public static String dbiHost;
	public static String dbiPort;
	public static String dbiServiceName;
	public static DBIManager dbiManager;
	public static ReaderProperties configReader = null;

	public PmsConnection() {
		readerInfo();
	}

	private void readerInfo() {
		try {
			configReader = new ReaderProperties();
			dbiHost = configReader.getProperty("ehoteldbi.dbihostname",
					"192.168.0.141");
			dbiPort = configReader.getProperty("ehoteldbi.dbiport", "9999");
			dbiServiceName = configReader.getProperty(
					"ehoteldbi.dbiservicename", "DBISQL");
			String dbiURL = "rmi://" + dbiHost + ":" + dbiPort + "/"
					+ dbiServiceName;
			dbiManager = new DBIManager(dbiURL);
			System.out.println("====================>> " + dbiURL);
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

	public static void main(String[] args) {
		PmsConnection con = new PmsConnection();
		DBI dbi = con.getDBICore();
		String sql = "select * from BCSUBJECTS";
		try {
			Vector v = dbi.executeSelect(sql, null, -1, -1);
			System.out.println(v.size());
			for (int i = 0; i < v.size(); i++) {
				System.out.println(v.get(i));
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
