package elcom.com.apiconnect;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

import com.elcom.DBI.DBI;

public class eConnection {
	public static String dbiHost;
	public static String dbiPort;
	public static String dbiServiceName;
	public static DBIManager dbiManager;
	public static com.elcom.Config.Reader config = null;

	public eConnection() {
		readerInfo();
	}

	private void readerInfo() {
		try {
			config = new com.elcom.Config.Reader("Config/eodapp.properties");
			dbiHost = config.getProperty("sqlapp.dbihostname", "11002");
			dbiPort = config.getProperty("sqlapp.dbiport", "9999");
			dbiServiceName = config.getProperty("sqlapp.dbiservicename", "DBInterface");
			String dbiURL = "rmi://" + dbiHost + ":" + dbiPort + "/" + dbiServiceName;
			dbiManager = new DBIManager(dbiURL);
			System.out.println("Connect DBI dbiURL [" + dbiURL + "]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DBI getDBICore() {
		DBI dbi = null;
		try {
			dbi = dbiManager.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbi;
	}

	@SuppressWarnings("rawtypes")
	public static void main(String arg[]) throws RemoteException, SQLException {
		eConnection con = new eConnection();
		DBI dbi = con.getDBICore();
		String sql = "select * from BCSUBJECTS";
		Vector v = dbi.executeSelect(sql, null, -1, -1);
		System.out.println(v.size());
		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}

	}
}
