package com.elcom.adcenter.rvcadv.schedule;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.adcenter.rvcadv.broker.IMBroker;
import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;
import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.common.VoItem;
import com.elcom.adcenter.rvcadv.group.GroupDao;
import com.elcom.adcenter.rvcadv.sql.SqlCore;
import com.elcom.adcenter.rvcadv.util.DateHelper;
import com.elcom.adcenter.rvcadv.util.XmlAnalysis;

public class ScheduleDao {

	// Refers the DB broker object
	private static Configuration config = null;
	// To log application
	private static Logger log = Logger.getLogger(GroupDao.class);
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

	private static final String pattern = "MM/dd/yyyy HH:mm:ss";

	// ------------------------------------------------------------

	// ------------------------------------------------------------
	public String admingetSchedulePeriGroup(String xmlparam) {
		String resul = "";
		String groupid = DateHelper.utilStringXml(xmlparam, SQL.groupid);
		resul = SqlCore.admingetSchedulePeriGroup(groupid);
		return resul;
	}

	// -----------------------------------------------------------
	public String adminNewScheduleDaily(String xmlparam) {

		VoItem item;
		Vector<VoItem> items = XmlAnalysis.getListScheduleDaily(xmlparam);
		// Delete all dailyitem
//		Vector param = new Vector(1);
//		param.add(0, (String) items.get(0).getP0());
//		SqlCore.getStringGenerals(SQL.sp_abopDeleteAllDailyItem, param, 0);
		String result = "";
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			result = result + SqlCore.adminNewScheduleDaily(item) + ";";
		}
		return result;
	}

	// ----------------------------------------------------------
	public void adminNewSchedulePeri(String xmlparam) {
		/*
		 * <Scheduleperio> <item> <dailynameid>1</dailynameid>
		 * <scheduleid>1</scheduleid> </item>
		 * 
		 * </Scheduleperio>
		 */
		VoItem item = new VoItem();
		item.setP0(DateHelper.utilStringXml(xmlparam, SQL.dailynameid));
		item.setP1(DateHelper.utilStringXml(xmlparam, SQL.scheduleid));

		SqlCore.adminNewSchedulePeri(item);
	}

	// -------------------------------------------------------------
	public String adminNewSchedulePeriName(String xmlparam) {
		/**
		 * <Scheduleperio> <item> <groupid></groupid>
		 * <nameschedule></nameschedule> <startdate></startdate>
		 * <stopdate></stopdate> </item> </Scheduleperio>
		 */
		VoItem item = new VoItem();
		item.setP0(DateHelper.utilStringXml(xmlparam, SQL.groupid));
		item.setP1(DateHelper.utilStringXml(xmlparam, SQL.nameschedule));
		item.setP2(DateHelper.utilStringXml(xmlparam, SQL.startdate));
		item.setP3(DateHelper.utilStringXml(xmlparam, SQL.stopdate));
		return SqlCore.adminNewSchedulePeriName(item);
	}

	// -------------------------------------------------------------
	public String admingetItemScheduleDailyForPerio(String xmlparam) {
		String resul = "";
		String scheduleid = DateHelper.utilStringXml(xmlparam, SQL.scheduleid);
		resul = SqlCore.admingetItemScheduleDailyForPerio(scheduleid);
		return resul;
	}

	// -------------------------------------------------------------
	public String admingetScheduleDailyNamGroup(String xmlparam) {
		/*
		 * <paramater> <item> <groupid></groupid> * </item> </paramater>
		 */
		String resul = "";
		String creator = DateHelper.utilStringXml(xmlparam, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparam, SQL.parentcreator);
		resul = SqlCore.admingetScheduleDailyNamGroup(creator, pcreator);
		return resul;
	}

	// -------------------------------------------------------------
	public String admingetScheduleDailyGroup(String xmlparam) {
		/*
		 * <paramater> <item> <dailynameid></dailynameid> * </item> </paramater>
		 */
		String resul = "";
		String dailynameid = DateHelper
				.utilStringXml(xmlparam, SQL.dailynameid);
		resul = SqlCore.admingetScheduleDailyGroup(dailynameid);
		return resul;
	}

	// -------------------------------------------------------------
	public String adminNewScheduleDailyName(String xmlparam) {
		/*
		 * <paramater> <item> <groupid></groupid> <namedaily></namedaily>
		 * <desc></desc> </item> </paramater>
		 */

		String groupid = DateHelper.utilStringXml(xmlparam, SQL.groupid);
		String namedaily = DateHelper.utilStringXml(xmlparam, SQL.namedaily);
		String desc = DateHelper.utilStringXml(xmlparam, SQL.desc);
		String creator = DateHelper.utilStringXml(xmlparam, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparam, SQL.parentcreator);
		return SqlCore.adminNewScheduleDailyName(groupid, namedaily, desc, creator, pcreator);
	}

	// -------------------------------------------------------------
	public String adminDelScheduleDailyName(String xmlparam) {
		/*
		 * <parameter> <item> <dailynameid></dailynameid> * </item> </parameter>
		 */
		String dailynameid = DateHelper
				.utilStringXml(xmlparam, SQL.dailynameid);
		return SqlCore.adminDelScheduleDailyName(dailynameid);
	}

	// ------------------------------------------------------------
	public String adminDelSchedulePeriItem(String xmlparam) {
		/*
		 * <parameter> <scheduleid></scheduleid> </parameter>
		 */
		String scheduleid = DateHelper.utilStringXml(xmlparam, SQL.scheduleid);
		return SqlCore.adminDelSchedulePeriItem(scheduleid);
	}

	// ------------------------------------------------------------
	public String adminDelSchedulePeri(String xmlparam) {
		/*
		 * <parameter> <scheduleid></scheduleid> </parameter>
		 */
		String scheduleid = DateHelper.utilStringXml(xmlparam, SQL.scheduleid);
		return SqlCore.adminDelSchedulePeri(scheduleid);
	}

	// -----------------------------------------------------------
	public String adminUpdateSchedulePeri(String xmlparam) {
		/*
		 * <parameter> <scheduleid></scheduleid> <nameschedule></nameschedule>
		 * <stopdate></stopdate> <startdate></startdate> </parameter>
		 */
		String scheduleid = DateHelper.utilStringXml(xmlparam, SQL.scheduleid);
		String nameschedule = DateHelper.utilStringXml(xmlparam,
				SQL.nameschedule);
		String stopdate = DateHelper.utilStringXml(xmlparam, SQL.stopdate);
		String startdate = DateHelper.utilStringXml(xmlparam, SQL.startdate);

		return SqlCore.adminUpdateSchedulePeri(scheduleid, nameschedule,
				stopdate, startdate);
	}

	// -----------------------------------------------------------
	public String adminDelSchduledailyTime(String xmlparam) {
		/*
		 * <parameter> <scheduleitemid></scheduleitemid> </parameter>
		 */
		String scheduleitemid = DateHelper.utilStringXml(xmlparam,
				SQL.scheduleitemid);
		return SqlCore.adminDelSchduledailyTime(scheduleitemid);
	}

	// ------------------------------------------------------------

	public String adminUpdateScheduleDailyTime(String xmlparam) {
		/*
		 * <parameter> <item> <scheduleitemid></scheduleitemid>
		 * <starttime></starttime> <stoptime></stoptime> <name></name> </item>
		 * </parameter>
		 */

		SqlCore.adminUpdateScheduleDailyTime(xmlparam);
		return "OK";
	}

	// ------------------------------------------------------------
	public void adminUpdateScheduleDailyName(String xmlparam) {
		/*
		 * <paramater> <item> <dailynameid></dailynameid>
		 * <namedaily></namedaily> <desc></desc> </item> </paramater>
		 */
		String dailynameid = DateHelper.utilStringXml(xmlparam, SQL.scheduleid);
		String namedaily = DateHelper.utilStringXml(xmlparam, SQL.namedaily);
		String desc = DateHelper.utilStringXml(xmlparam, SQL.desc);
		SqlCore.adminUpdateScheduleDailyName(dailynameid, namedaily, desc);
	}

	// ------------------------------------------------------------
	public static void main(String[] arg) {
		String xmlparam = "<Scheduledaily>\r\n" + "<item> \r\n"
				+ "<dailynameid>1</dailynameid>\r\n"
				+ "<playlistid>1</playlistid>\r\n"
				+ "<namesdaily>PlayList01_86_1</namesdaily>\r\n"
				+ "<starttime>00:00:00</starttime>\r\n"
				+ "<stoptime>15:00:00</stoptime>\r\n" + "</item>\r\n"
				+ "<item>\r\n" + "<dailynameid>1</dailynameid>\r\n"
				+ "<playlistid>1</playlistid>\r\n"
				+ "<namesdaily>PlayList01_86_2</namesdaily>\r\n"
				+ "<starttime>15:00:00</starttime>\r\n"
				+ "<stoptime>20:00:00</stoptime>\r\n" + "</item>\r\n"
				+ "</Scheduledaily>\r\n";
		ScheduleDao dao = new ScheduleDao();
		dao.adminNewScheduleDaily(xmlparam);

	}
}
