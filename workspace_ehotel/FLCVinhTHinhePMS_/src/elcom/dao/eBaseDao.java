package elcom.dao;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.DBI.DBI;
import com.elcom.DBI.SubProParam;

import elcom.connect.ConfigReader;
import elcom.connect.PmsConnection;
import elcom.utils.Utils;

public class eBaseDao {
	private static Logger log = Logger.getLogger(eBaseDao.class);
	//public static Vector<String> outParam = new Vector<String>();

	static {
		try {
			PropertyConfigurator.configure(ConfigReader.getLog4jConfigReader()
					.getProperties());
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			log.error(null, ex);
		}
	}

	private static PmsConnection con;
	public static DBI dbi;

	public eBaseDao() {
		initSystem();
	}

	private static void initSystem() {
		if (dbi == null) {
			if (con == null) {
				con = new PmsConnection();
			}
			dbi = con.getDBICore();
		}

	}

	public static DBI getDBIService() {
		if (dbi == null) {
			if (con == null) {
				con = new PmsConnection();
			}
			dbi = con.getDBICore();
		}
		return dbi;
	}

	protected static Object getSequence(String sequenceName)
			throws RemoteException, SQLException {
		String query = "select " + sequenceName + ".nextval from dual";
		Vector result = executeSelect(query);
		if (result != null && result.size() > 2) {
			result = (Vector) result.get(2);
			if (result != null && result.size() > 0) {
				return result.get(0);
			}
		}
		return null;
	}

	protected static int count(String query) throws RemoteException,
			SQLException {
		return count(query, null);
	}

	protected static int count(String query, Vector<SubProParam> params)
			throws RemoteException, SQLException {
		Vector result = executeSelect(query, params);
		if (result != null && result.size() > 2) {
			result = (Vector) result.get(2);
			if (result != null && result.size() > 0) {
				return Utils.parseInt2(result.get(0));
			}
		}
		return 0;
	}

	protected static Vector<SubProParam> executeSubPro(String query,
			Vector<SubProParam> params) throws RemoteException, SQLException {
		if (query != null) {
			initSystem();
			log.info(query);
			return dbi.executeSubPro(query, params);
		}
		return null;
	}

	protected static Vector executeSelect(String query,
			Vector<SubProParam> params) throws RemoteException, SQLException {
		if (query != null) {
			initSystem();
			log.info(query);
			return dbi.executeSelect(query, params, -1, -1);
		}
		return null;
	}

	protected static Vector executeSelect(String query) throws RemoteException,
			SQLException {
		return executeSelect(query, null);
	}

	protected static int executeChangeData(String query,
			Vector<SubProParam> params) throws RemoteException, SQLException {
		if (query != null) {
			initSystem();
			log.info(query);
			return dbi.executeChangeData(query, params);
		}
		return 0;
	}

	protected static int executeChangeData(String query)
			throws RemoteException, SQLException {
		return executeChangeData(query, null);
	}

	public static int getCheckNo(int homeServiceId) {
		int ret = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				homeServiceId), SubProParam.IN);
		params.add(subPro);

		SubProParam subPro1 = new SubProParam(new String(), SubProParam.OUT);
		params.add(subPro1);

		String outScreen = "[getCheckNo with params:homeServiceId="
				+ homeServiceId + "]";
		log.info(outScreen);
		try {
			params = executeSubPro(ePMSSql.sqlGetCheckNo, params);

			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				String tmp = (paramOUT.getString()).trim();
				if (tmp != null) {
					ret = Integer.parseInt(tmp);
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info(outScreen + " :returnValue checkNo=" + ret);
		return ret;
	}

}
