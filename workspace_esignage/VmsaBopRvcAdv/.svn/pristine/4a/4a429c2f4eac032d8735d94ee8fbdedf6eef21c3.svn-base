package com.elcom.adcenter.rvcadv.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.adcenter.rvcadv.broker.IMBroker;
import com.elcom.adcenter.rvcadv.broker.SubProParam;
import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;
import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.common.VoContent;
import com.elcom.adcenter.rvcadv.common.VoItem;
import com.elcom.adcenter.rvcadv.util.DateHelper;
import com.elcom.adcenter.rvcadv.util.XmlAnalysis;
import com.elcom.adcenter.rvcav.content.ServiceContentDao;

public class SqlCore {
	// Refers the DB broker object
	private static IMBroker broker = IMBroker.getInstance();
	private static Configuration config = null;
	// To log application
	private static Logger log = Logger.getLogger(SqlCore.class);
	// Read configuration params
	static {
		try {
			ConfigurationLoader loader = ConfigurationLoader.getInstance();
			config = loader.getConfiguration();
			Properties props = new Properties();
			FileInputStream fileinputstream = new FileInputStream(
					"Config/log4j.properties");
			props.load(fileinputstream);
			PropertyConfigurator.configure(props);
		} catch (IOException ex) {
			log.error(null, ex);
		}
	}

	// -------------------------------------------------------
	public static String getStringGenerals(String query, Vector paramiv,
			int paramout) {
		String result = "";
		int size = paramiv.size();
		SubProParam out_data;
		log.info("query: " + query + " | param size: " + size);
		System.out.println("query: " + query + " | param size: " + size);
		// broker.executeQuery("SET character_set_results=utf8");
		for (int ii = 0; ii < paramiv.size(); ii++) {
			log.info("pram " + ii + " = " + paramiv.get(ii));
		}

		try {
			Vector<SubProParam> params;
			if (paramout == 1) {
				params = new Vector<SubProParam>(size + 1);
			} else
				params = new Vector<SubProParam>(size);
			SubProParam param = null;
			for (int i = 0; i < size; i++) {
				String para = (String) paramiv.get(i);
				param = new SubProParam(new String(para), SubProParam.IN);
				params.add(i, param);
			}
			if (paramout == 1) {
				out_data = new SubProParam(new String(), SubProParam.OUT);
				params.add(paramiv.size(), out_data);
			}

			params = broker.executeSubPro(query, params);

			if (paramout == 1) {
				out_data = (SubProParam) params.get(size);
				result = (String) out_data.getValue();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return result;
	}

	// ----------------------------------------------------

	public static String getListContent(String idconnets) {
		String result = "";

		Vector param = new Vector(1);
		param.add(0, idconnets);
		result = getStringGenerals(SQL.sp_adminToolGetListContent, param, 1);

		return result;
	}

	// -------------------------------------------------------
	public static void setContentIntoStb(String sessionid, String conentid) {
		Vector param = new Vector(2);
		param.add(0, sessionid);
		param.add(1, conentid);
		getStringGenerals(SQL.sp_adminSetContentIntoStb, param, 0);
	}

	// -------------------------------------------------------
	public static void adminDelContentStb(String sessionid, String conentid) {
		Vector param = new Vector(2);
		param.add(0, sessionid);
		param.add(1, conentid);
		getStringGenerals(SQL.sp_adminDelContentStb, param, 0);
	}

	// ==========================================================
	public static void adminDelConntentStbWar(String sessionid) {
		Vector param = new Vector(1);
		param.add(0, sessionid);
		getStringGenerals(SQL.sp_adminDelConntentStbWar, param, 0);
	}

	// =======================================================
	/* Schedule */
	public static String admingetScheduleDailyGroup(String idgroup) {
		String result = "";

		Vector param = new Vector(1);
		param.add(0, idgroup);
		result = getStringGenerals(SQL.sp_admingetScheduleDailyGroup, param, 1);

		return result;
	}

	// -------------------------------------------------------
	public static String admingetSchedulePeriGroup(String idgroup) {
		String result = "";

		Vector param = new Vector(1);
		param.add(0, idgroup);
		result = getStringGenerals(SQL.sp_admingetSchedulePeriGroup, param, 1);

		return result;
	}

	// -------------------------------------------------------
	public static String adminDelSchedulePeriItem(String scheduleid) {
		String result = "";

		Vector param = new Vector(1);
		param.add(0, scheduleid);
		result = getStringGenerals(SQL.sp_adminDelSchedulePeriItem, param, 1);

		return result;
	}

	// -------------------------------------------------------
	public static String adminDelSchedulePeri(String scheduleid) {
		String result = "";

		Vector param = new Vector(1);
		param.add(0, scheduleid);
		result = getStringGenerals(SQL.sp_adminDelSchedulePeri, param, 1);

		return result;
	}

	// --------------------------------------------------------
	public static String adminUpdateSchedulePeri(String scheduleid,
			String schedulename, String timestart, String timestop) {
		String result = "";

		Vector param = new Vector(4);
		param.add(0, scheduleid);
		param.add(1, schedulename);
		param.add(2, timestart);
		param.add(3, timestop);

		result = getStringGenerals(SQL.sp_adminUpdateSchedulePeri, param, 1);

		return result;
	}

	// --------------------------------------------------------

	public static String admingetItemScheduleDailyForPerio(String idschedule) {
		String result = "";

		Vector param = new Vector(1);
		param.add(0, idschedule);
		result = getStringGenerals(SQL.sp_admingetItemScheduleDailyForPerio,
				param, 1);

		return result;
	}

	// --------------------------------------------------------
	public static String adminNewScheduleDaily(VoItem item) {
		Vector param = new Vector();
		param.add(0, (String) item.getP0());
		param.add(1, (String) item.getP1());
		param.add(2, (String) item.getP2());
		param.add(3, (String) item.getP3());
		param.add(4, (String) item.getP4());

		return getStringGenerals(SQL.sp_adminNewScheduleDaily, param, 1);
	}

	// ---------------------------------------------------------
	public static void adminUpdateScheduleDailyTime(String xmlparamter) {

		VoItem item;
		Vector<VoItem> items = XmlAnalysis.getListPlayListItem(xmlparamter);
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			Vector param = new Vector(4);
			String scheduleitemid = (String) item.getP0();
			String startime = (String) item.getP1();
			String stoptime = (String) item.getP2();
			String name = (String) item.getP3();
			log.info("id:" + scheduleitemid);
			log.info("starttime:" + startime);
			log.info("stoptime:" + stoptime);
			log.info("name:" + name);
			param.add(0, scheduleitemid);
			param.add(1, startime);
			param.add(2, stoptime);
			param.add(3, name);
			getStringGenerals(SQL.sp_adminUpdateScheduleDailyTime, param, 0);
		}
	}

	// ---------------------------------------------------------
	public static void adminNewSchedulePeri(VoItem item) {
		Vector param = new Vector(2);
		param.add(0, (String) item.getP0());
		param.add(1, (String) item.getP1());

		getStringGenerals(SQL.sp_adminNewSchedulePeri, param, 0);
	}

	// =======================================================
	public static String adminNewSchedulePeriName(VoItem item) {
		Vector param = new Vector(4);
		param.add(0, (String) item.getP0());
		param.add(1, (String) item.getP1());
		param.add(2, (String) item.getP2());
		param.add(3, (String) item.getP3());
		return getStringGenerals(SQL.sp_adminNewSchedulePeriName, param, 1);
	}

	// =======================================================
	public static String admingetScheduleDailyNamGroup(String creator, String parentCreator) {
		String result = "";
		Vector param = new Vector();
		param.add(0, creator);
		param.add(1, parentCreator);
		result = getStringGenerals(SQL.sp_admingetScheduleDailyNamGroup, param,
				1);
		return result;
	}

	// =======================================================
	public static String adminNewScheduleDailyName(String groupid,
			String namedailyin, String descin, String creator, String pcreator) {
		Vector param = new Vector();
		param.add(0, groupid);
		param.add(1, namedailyin);
		param.add(2, descin);
		param.add(3, creator);
		param.add(4, pcreator);
		return getStringGenerals(SQL.sp_adminNewScheduleDailyName, param, 1);
	}

	// =======================================================
	public static String adminDelSchduledailyTime(String scheduleitemid) {
		Vector param = new Vector(1);
		param.add(0, scheduleitemid);
		return getStringGenerals(SQL.sp_adminDelSchduledailyTime, param, 1);
	}

	// =======================================================
	public static String  adminDelScheduleDailyName(String iddailyname) {
		Vector param = new Vector(1);
		param.add(0, iddailyname);
		return getStringGenerals(SQL.sp_adminDelScheduleDailyName, param, 1);
	}

	// =======================================================
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void adminNewContentText(String NameContent,
			String UrlContent, String subjectContent, String ColorText, String FontText,
			String SizeText, String DirecRoll, String typeContent, String username, String pcreator) {
		Vector param = new Vector();
		param.add(NameContent);
		param.add(UrlContent);
		param.add(subjectContent);
		param.add(ColorText);
		param.add(FontText);
		param.add(SizeText);
		param.add(DirecRoll);
		param.add(typeContent);
		param.add(username);
		param.add(pcreator);

		getStringGenerals(SQL.sp_adminNewContentText, param, 0);
	}

	// =======================================================
	public static void adminNewContent(String contentname, String contentype,
			String urlcontent) {
		Vector param = new Vector(3);
		param.add(contentname);
		param.add(contentype);
		param.add(urlcontent);
		getStringGenerals(SQL.sp_adminNewContent, param, 0);
	}

	// ======================================================= bo sung 28.2
	public static void adminNewContentWithDuration(String contentname,
			String contentype, String urlcontent, String duration) {
		Vector param = new Vector(4);
		param.add(duration);
		param.add(contentname);
		param.add(contentype);
		param.add(urlcontent);
		getStringGenerals(SQL.sp_adminNewContentWithDuration, param, 0);
	}

	// =======================================================
	public static void adminUpdateContentText(VoContent item) {
		Vector param = new Vector(7);
		param.add(item.getId_Content());
		param.add(item.getNameContent());
		param.add(item.getUrlContent());
		param.add(item.getColorText());
		param.add(item.getFontText());
		param.add(item.getSizeText());
		param.add(item.getDirecRoll());
		getStringGenerals(SQL.sp_adminUpdateContentText, param, 0);
	}

	// =======================================================
	public static void adminDelContentText(String contentid) {
		Vector param = new Vector(1);
		param.add(contentid);
		getStringGenerals(SQL.sp_adminDelContentText, param, 0);
	}

	// =======================================================
	public static void adminUpdateScheduleDailyName(String iddailyname,
			String namedailyin, String desc) {
		Vector param = new Vector(3);
		param.add(0, iddailyname);
		param.add(1, namedailyin);
		param.add(2, desc);
		getStringGenerals(SQL.sp_adminUpdateScheduleDailyName, param, 0);
	}

	// =======================================================
	public static void main(String[] arg) {
		System.out.println(getListContent("12"));
	}

	public static String abopDeleteContentFromSTB(String idstb, String idcontent) {
		Vector param = new Vector(2);
		param.add(0, idstb);
		param.add(1, idcontent);
		return getStringGenerals(SQL.sp_abopDeleteContentFromSTB, param, 1);
	}

	public static String abopSetMonitoring(String flag, String idstb) {
		Vector param = new Vector(2);
		param.add(0, flag);
		param.add(1, idstb);
		return getStringGenerals(SQL.sp_abopSetMonitoring, param, 0);
	}

	public static String abopGetUser(String username, String pass) {
		Vector param = new Vector(2);
		param.add(0, username);
		param.add(1, pass);
		return getStringGenerals(SQL.sp_abopGetUser, param, 1);
	}
	public static String abopGetAllBox(String creator, String pcreator) {
		Vector param = new Vector();
		param.add(0, creator);
		param.add(1, pcreator);
		return getStringGenerals(SQL.sp_abopGetAllBox, param, 1);
	}
	public static String abopGetListUser(String creator, String pcreator) {
		Vector param = new Vector();
		param.add(0, creator);
		param.add(1, pcreator);
		return getStringGenerals(SQL.sp_abopGetListUser, param, 1);
	}

	public static String abopCreateUser(String xmlparamter) {
		Vector param = new Vector(1);
		param.add(0, xmlparamter);
		return getStringGenerals(SQL.sp_abopCreateUser, param, 1);
	}

	public static String abopGetRole() {
		Vector param = new Vector(0);

		return getStringGenerals(SQL.abopGetRole, param, 1);
	}

	public static String abopCheckUser(String username) {
		Vector param = new Vector(1);
		param.add(0, username);
		return getStringGenerals(SQL.sp_abopCheckUser, param, 1);
	}

	public static String abopUpdateUser(String xmlparamter) {
		Vector param = new Vector(1);
		param.add(0, xmlparamter);
		return getStringGenerals(SQL.sp_abopUpdateUser, param, 1);
	}
	public static String abopAddStbUser(String xmlparamter) {
		Vector param = new Vector(1);
		param.add(0, xmlparamter);
		return getStringGenerals(SQL.sp_abopAddStbUser, param, 1);
	}
	public static String abopDeleteUser(String xmlparamter) {
		Vector param = new Vector(1);
		param.add(0, xmlparamter);
		return getStringGenerals(SQL.sp_abopDeleteUser, param, 1);
	}
}