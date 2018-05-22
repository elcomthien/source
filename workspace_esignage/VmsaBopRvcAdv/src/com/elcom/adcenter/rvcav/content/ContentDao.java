package com.elcom.adcenter.rvcav.content;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;
import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.common.VoContent;
import com.elcom.adcenter.rvcadv.common.VoItem;
import com.elcom.adcenter.rvcadv.group.GroupDao;
import com.elcom.adcenter.rvcadv.schedule.ScheduleDao;
import com.elcom.adcenter.rvcadv.sql.SqlCore;
import com.elcom.adcenter.rvcadv.util.DateHelper;
import com.elcom.adcenter.rvcadv.util.XmlAnalysis;

public class ContentDao {
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

	// --------------------------------------------------------------------
	public void adminUpdateContentText(String xmlparam) {
		/**
		 * <parameter> <id_Content></id_Content> <nameContent></nameContent>
		 * <urlContent></urlContent> <colorText></colorText>
		 * <fontText></fontText> <sizeText></sizeText> <direcRoll></direcRoll>
		 * </parameter>
		 */
		VoContent item = new VoContent();
		String id_Content = DateHelper.utilStringXml(xmlparam, SQL.id_Content);
		String nameContent = DateHelper
				.utilStringXml(xmlparam, SQL.nameContent);
		String urlContent = DateHelper.utilStringXml(xmlparam, SQL.urlContent);
		String colorText = DateHelper.utilStringXml(xmlparam, SQL.colorText);
		String fontText = DateHelper.utilStringXml(xmlparam, SQL.fontText);
		String sizeText = DateHelper.utilStringXml(xmlparam, SQL.sizeText);
		String direcRoll = DateHelper.utilStringXml(xmlparam, SQL.direcRoll);

		item.setId_Content(id_Content);
		item.setNameContent(nameContent);
		item.setUrlContent(urlContent);
		item.setColorText(colorText);
		item.setFontText(fontText);
		item.setSizeText(sizeText);
		item.setDirecRoll(direcRoll);

		SqlCore.adminUpdateContentText(item);
	}

	// -------------------------------------------------------------------
	public void adminDelContentText(String xmlparam) {
		/**
		 * <parameter> <id_Content></id_Content> </parameter>
		 */
		String id_Content = DateHelper.utilStringXml(xmlparam, SQL.id_Content);
		SqlCore.adminDelContentText(id_Content);
	}

	// -------------------------------------------------------------------
	public void adminNewContentText(String xmlparam) {
		/**
		 * <parameter> <nameContent></nameContent> <urlContent></urlContent>
		 * <subjectContent></subjectContent><colorText></colorText>
		 * <fontText></fontText> <sizeText></sizeText> <direcRoll></direcRoll>
		 * <typeContent></typeContent> </parameter>
		 */

		String nameContent = DateHelper
				.utilStringXml(xmlparam, SQL.nameContent);
		String urlContent = DateHelper.utilStringXml(xmlparam, SQL.urlContent);
		String subjectContent = DateHelper.utilStringXml(xmlparam,
				SQL.subjectContent);
		String colorText = DateHelper.utilStringXml(xmlparam, SQL.colorText);
		String fontText = DateHelper.utilStringXml(xmlparam, SQL.fontText);
		String sizeText = DateHelper.utilStringXml(xmlparam, SQL.sizeText);
		String direcRoll = DateHelper.utilStringXml(xmlparam, SQL.direcRoll);
		String typeContent = DateHelper
				.utilStringXml(xmlparam, SQL.typeContent);
		String username = DateHelper
				.utilStringXml(xmlparam, SQL.username);
		String pcreator = DateHelper
		.utilStringXml(xmlparam, SQL.parentcreator);
		SqlCore.adminNewContentText(nameContent, urlContent, subjectContent,
				colorText, fontText, sizeText, direcRoll, typeContent,username, pcreator);
	}

	// --------------------------------------------------------------------
	public String adminNewContent(String xmlparam) {
		/**
		 * <parameter> <nameContent></nameContent> <urlContent></urlContent>
		 * <typeContent></typeContent> </parameter>
		 */

		String nameContent = DateHelper
				.utilStringXml(xmlparam, SQL.nameContent);
		String urlContent = DateHelper.utilStringXml(xmlparam, SQL.urlContent);
		String typeContent = DateHelper
				.utilStringXml(xmlparam, SQL.typeContent);

		SqlCore.adminNewContent(nameContent, typeContent, urlContent);
		return "OK";
	}

	// --------------------------------------------------------------------
	public String adminNewContentWithDuration(String xmlparam) {
		/**
		 * <parameter> <nameContent></nameContent> <urlContent></urlContent>
		 * <typeContent></typeContent> <lengthContent></lengthContent>
		 * </parameter>
		 */

		String nameContent = DateHelper
				.utilStringXml(xmlparam, SQL.nameContent);
		String urlContent = DateHelper.utilStringXml(xmlparam, SQL.urlContent);
		String typeContent = DateHelper
				.utilStringXml(xmlparam, SQL.typeContent);
		String duration = DateHelper.utilStringXml(xmlparam, SQL.lengthContent);

		SqlCore.adminNewContentWithDuration(nameContent, typeContent,
				urlContent, duration);
		return "OK";
	}

	// ------------------------------------------------------------
	public static void main(String[] arg) {
		// String xmlparam = "<Scheduledaily>\r\n" + "<item> \r\n"
		// + "<dailynameid>1</dailynameid>\r\n"
		// + "<playlistid>1</playlistid>\r\n"
		// + "<namesdaily>PlayList01_86_1</namesdaily>\r\n"
		// + "<starttime>00:00:00</starttime>\r\n"
		// + "<stoptime>15:00:00</stoptime>\r\n" + "</item>\r\n"
		// + "<item>\r\n" + "<dailynameid>1</dailynameid>\r\n"
		// + "<playlistid>1</playlistid>\r\n"
		// + "<namesdaily>PlayList01_86_2</namesdaily>\r\n"
		// + "<starttime>15:00:00</starttime>\r\n"
		// + "<stoptime>20:00:00</stoptime>\r\n" + "</item>\r\n"
		// + "</Scheduledaily>\r\n";
		// ScheduleDao dao = new ScheduleDao();
		// dao.adminNewScheduleDaily(xmlparam);
		ContentDao c = new ContentDao();
		String xml = "<parameter><nameContent>thiện</nameContent><urlContent>thiện</urlContent> <subjectContent>7</subjectContent> <colorText></colorText> <fontText></fontText> <sizeText>12</sizeText> <direcRoll>1</direcRoll> <typeContent>6</typeContent> </parameter>";
		c.adminNewContentText(xml);
	}

	public static String abopDeleteContentFromSTB(String xmlparamter) {
		String idstb = DateHelper

		.utilStringXml(xmlparamter, SQL.idstb);
		log.info("id stb:" + idstb);
		String idcontent = DateHelper.utilStringXml(xmlparamter, SQL.idcontent);
		log.info("id content:" + idcontent);
		String result = SqlCore.abopDeleteContentFromSTB(idstb, idcontent);
		return result;
	}

	public String abopSetMonitoring(String xmlparamter) {
		String flag = DateHelper.utilStringXml(xmlparamter, SQL.monitoring);
		String idstb = DateHelper.utilStringXml(xmlparamter, SQL.idstb);
		String result = SqlCore.abopSetMonitoring(flag, idstb);
		return result;
	}
	
	

}