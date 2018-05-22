package ehotel.dao;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.DBI.DBI;
import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;

import ehotel.connect.ConfigReader;
import ehotel.connect.DBIConnection;
import ehotel.utils.Utils;

public class eBaseDao {
	private static Logger log = Logger.getLogger(eBaseDao.class);
	protected String CONTENT = "CONTENT";
	protected String SERVICE = "SERVICE";
	protected int RVSServiceID = 0;
	protected String SqlQuery = "";
	public static Vector<String> outParam = new Vector<String>();

	static {
		try {
			PropertyConfigurator.configure(ConfigReader.getLog4jConfigReader()
					.getProperties());
		} catch (IOException ex) {
			log.error(null, ex);
		}
	}

	private static DBIConnection con;
	public static DBI dbi;

	public eBaseDao() {
		initSystem();
	}

	private static void initSystem() {
		if (dbi == null) {
			if (con == null) {
				con = new DBIConnection();
			}
			dbi = con.getDBICore();
		}
	}

	public static DBI getDBIService() {
		if (dbi == null) {
			if (con == null) {
				con = new DBIConnection();
			}
			dbi = con.getDBICore();
		}
		return dbi;
	}

	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("unchecked")
	protected static Vector<SubProParam> executeSubPro(String query,
			Vector<SubProParam> params) throws RemoteException, SQLException {
		if (query != null) {
			initSystem();
			log.info(query);
			return dbi.executeSubPro(query,
					UnicodeConverter.changeFontForDBI(params));
		}
		return null;
	}

	protected static Vector<SubProParam> executePro(String query,
			Vector<SubProParam> params) throws RemoteException, SQLException {
		if (query != null) {
			initSystem();
			log.info(query);
			return dbi.executeSubPro(query, params);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected static Vector<String> executeSelect(String query,
			Vector<SubProParam> params) throws RemoteException, SQLException {
		if (query != null) {
			initSystem();
			log.info(query);
			return dbi.executeSelect(query,
					UnicodeConverter.changeFontForDBI(params), -1, -1);
		}
		return null;
	}

	protected static Vector<String> executeSelect(String query)
			throws RemoteException, SQLException {
		return executeSelect(query, null);
	}

	protected static int executeChangeData(String query,
			Vector<SubProParam> params) throws RemoteException, SQLException {
		if (query != null) {
			initSystem();
			log.info(query);
			return dbi.executeChangeData(query,
					UnicodeConverter.changeFontForDBI(params));
		}
		return 0;
	}

	protected static int executeChangeData(String query)
			throws RemoteException, SQLException {
		return executeChangeData(query, null);
	}

	public static String displayString(Object text, int index, boolean isHtml,
			boolean removeTag) {
		if (text != null && !text.equals("null")) {
			String htmlString = text.toString();
			if (isHtml) {
				htmlString = UnicodeConverter.decodeUnicode(htmlString);
			}
			htmlString = htmlString.trim();
			if (removeTag) {
				htmlString = removeHtmlTag(htmlString);
			}
			if (htmlString.length() > index) {
				return htmlString.substring(0, index).trim() + "...";
			} else {
				return htmlString;
			}
		}
		return "";
	}

	public static String displayString(String text, int index, boolean isHtml) {
		if (text != null && !text.equals("null")) {
			if (isHtml) {
				text = UnicodeConverter.decodeUnicode(text);
			}
			text = text.trim();
			if (text.length() > index) {
				return text.substring(0, index).trim() + "...";
			} else {
				return text;
			}
		}
		return "";
	}

	public static String removeHtmlTag(String htmlString) {
		return htmlString.replaceAll("\\<.*?\\>", "");
	}

	public boolean checkExistFile(String aFilePath) {
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");
		File aFolder = new File(path + "/" + aFilePath);
		if (!aFolder.exists()) {
			return true;
		}
		return false;
	}

	public boolean createFolder(String pathfolder) {
		boolean ismkdir = false;
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");
		String currentFolder = null;
		currentFolder = path;
		File aFolder = new File(currentFolder + "/" + pathfolder);
		if (!aFolder.exists()) {
			if (pathfolder.indexOf("/") == -1) {
				aFolder.mkdir();
				ismkdir = true;
			} else {
				StringTokenizer token = new StringTokenizer(pathfolder, "/");
				while (token.hasMoreTokens()) {
					String aToken = token.nextToken();
					currentFolder += "/" + aToken;
					aFolder = new File(currentFolder);
					if (!aFolder.exists()) {
						aFolder.mkdir();
					}
				}
				ismkdir = true;
			}
		}
		return ismkdir;
	}

}
