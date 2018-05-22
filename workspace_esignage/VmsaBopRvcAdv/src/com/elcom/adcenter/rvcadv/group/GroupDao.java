package com.elcom.adcenter.rvcadv.group;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;

import com.elcom.adcenter.rvcadv.broker.IMBroker;
import com.elcom.adcenter.rvcadv.broker.SubProParam;
import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;
import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.common.VoContentGroup;
import com.elcom.adcenter.rvcadv.common.VoItem;
import com.elcom.adcenter.rvcadv.common.VoStbSession;
import com.elcom.adcenter.rvcadv.layout.LayoutDao;
import com.elcom.adcenter.rvcadv.sql.SqlCore;
import com.elcom.adcenter.rvcadv.util.DataSocketSesionStb;
import com.elcom.adcenter.rvcadv.util.DateHelper;
import com.elcom.adcenter.rvcadv.util.XPathReader;
import com.elcom.adcenter.rvcadv.util.XmlAnalysis;
import com.elcom.adcenter.rvcav.content.ServiceContentDao;

import org.apache.axiom.om.impl.dom.ParentNode;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GroupDao {
	// Refers the DB broker object
	private static IMBroker broker = IMBroker.getInstance();
	private static Configuration config = null;
	// To log application
	private static Logger log = Logger.getLogger(GroupDao.class);
	// Read configuration params
	static {
		try {
			ConfigurationLoader loader = ConfigurationLoader.getInstance();
			config = loader.getConfiguration();
			Properties props = new Properties();
			FileInputStream fileinputstream = new FileInputStream("Config/log4j.properties");
			props.load(fileinputstream);
			PropertyConfigurator.configure(props);
		} catch (IOException ex) {
			log.error(null, ex);
		}
	}

	private static final String pattern = "MM/dd/yyyy HH:mm:ss";

	// -------------------------------------------------------
	public String getStringGeneral(String query, String parami) {
		String result = "";
		log.info("query: " + query + " | param: " + parami);
		try {
			Vector<SubProParam> params = new Vector<SubProParam>(2);
			SubProParam param = null;
			param = new SubProParam(new String(parami), SubProParam.IN);
			params.add(0, param);

			SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
			params.add(1, out_data);

			params = broker.executeSubPro(query, params);
			out_data = (SubProParam) params.get(1);
			result = (String) out_data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return result;
	}

	// -------------------------------------------------------
	public String getStringGenerals(String query, Vector paramiv, int paramout) {
		String result = "";
		int size = paramiv.size();
		SubProParam out_data;
		log.info("query: " + query + " | param size: " + size);
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
			log.error(ex.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @param groupid
	 *            '<group><groupid>-1</groupid><creator></creator></group>'
	 * @return
	 */
	public String getContentGroup(String xmlparamter) {
		String strGroup = "";
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		if (groupid.equals("-1")){
			Vector param = new Vector();
			param.add(groupid);
			param.add(creator);
			param.add(pcreator);
			strGroup = getStringGenerals(SQL.sp_admingetContent, param ,1);
		} else {
			Vector param = new Vector();
			param.add(0, groupid);
			param.add(1, creator);
			param.add(2, pcreator);
			strGroup = getStringGenerals(SQL.sp_admingetContentGroup, param, 1);
		}
		return strGroup;
	}

	/**
	 * 
	 * @param groupid
	 *            '<group><typecontent>1</typecontent><subjectcontent>" + idsubject + "</subjectcontent><creator>khong
	 *            biet</creator></group>'
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAllContent(String xmlparamter) {
		String strContent = "";
		String typecontent = DateHelper.utilStringXml(xmlparamter, "typecontent");
		String idsubject = DateHelper.utilStringXml(xmlparamter, "subjectcontent");
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		Vector param = new Vector();
		param.add(0, typecontent);
		param.add(1, idsubject);
		param.add(2, creator);
		param.add(3, pcreator);
		strContent = getStringGenerals(SQL.sp_adminGetAllContent, param, 1);
		return strContent;
	}

	@SuppressWarnings({ "rawtypes" })
	public String getAllContentMedia(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector();
		String idsubject = DateHelper.utilStringXml(xmlparamter, "subjectcontent");
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(0, idsubject);
		param.add(1, creator);
		param.add(2, pcreator);
		result = getStringGenerals(SQL.sp_adminGetAllContentMedia, param, 1);
		return result;
	}

	public String admingetContentNoGroup(String xmlparamter) {
		String strGroup = "";
		String groupid = DateHelper.utilStringXml(xmlparamter, "groupid");

		strGroup = getStringGeneral(SQL.sp_admingetContentNoGroup, groupid);
		return strGroup;
	}

	/**
	 * Lay ten danh sach cua nhom
	 * 
	 * @param xmlparamter
	 * @return
	 */
	public String getListGroup(String xmlparamter) {
		String strGroup = "";
		String username = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String parentCreator =  DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		Vector param = new Vector();
		param.add(0, "-1");
		param.add(1, username);
		param.add(2, parentCreator);
		strGroup = getStringGenerals(SQL.sp_admingetListGroup, param, 1);
		return strGroup;
	}

	/**
	 * Lay ten danh sach cua nhom
	 * 
	 * @param xmlparamter
	 * @return
	 */
	public String getListGroups(String xmlparamter) {
		/*
		 * String strGroup = ""; String groupids = DateHelper.utilStringXml(xmlparamter, SQL.groupid); strGroup =
		 * getStringGeneral(SQL.sp_admingetListGroups,groupids); return strGroup;
		 */

		String strGroup = "";
		Vector param = new Vector(2);
		String groupids = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		param.add(0, groupids);
		param.add(1, creator);
		strGroup = getStringGenerals(SQL.sp_admingetListGroups, param, 1);
		return strGroup;
	}

	/**
	 * Tao ra nhom moi
	 * 
	 * @param xmlparamter
	 * @return
	 */
	public String adminNewGroup(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector();
		String groupname = DateHelper.utilStringXml(xmlparamter, SQL.groupname);
		String parentgroupid = DateHelper.utilStringXml(xmlparamter, SQL.parentgroupid);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(0, parentgroupid);
		param.add(1, groupname);
		param.add(2, creator);
		param.add(3, pcreator);
		result = getStringGenerals(SQL.sp_adminNewGroup, param, 1);
		return result;
	}

	/**
	 * Xoa mot nhom nao do
	 * 
	 * @param xmlparamter
	 * 
	 * @return
	 */
	public String adminDelGroup(String xmlparamter) {
		String result = "1";
		Vector param = new Vector(1);
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);

		param.add(0, groupid);

		result = getStringGenerals(SQL.sp_adminDelGroup, param, 1);
		return result;
	}

	/**
	 * 
	 * @param xmlparamter
	 * @return
	 */
	public String adminDelStb(String xmlparamter) {
		/*
		 * <parammeter><stbid>123</stbid></parammeter>
		 */
		String result = "OK";
		Vector param = new Vector(1);
		String stbid = DateHelper.utilStringXml(xmlparamter, SQL.stbid);

		param.add(0, stbid);

		getStringGenerals(SQL.sp_adminDelStb, param, 0);
		return result;
	}
	public String adminRemoveBox(String xmlparamter) {
		/*
		 * <parammeter><stbid>123</stbid></parammeter>
		 */
		Vector param = new Vector();
		String stbid = DateHelper.utilStringXml(xmlparamter, SQL.stbid);
		param.add(0, stbid);
		String result=getStringGenerals(SQL.sp_adminRemoveBox, param, 1);
		return result;
	}

	/**
	 * 
	 * @param xmlparamter
	 * @return
	 */
	public String adminUpdateGroup(String xmlparamter) {
		String result = "1";
		Vector param = new Vector();
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String groupname = DateHelper.utilStringXml(xmlparamter, SQL.groupname);

		param.add(0, groupid);
		param.add(1, groupname);

		result = getStringGenerals(SQL.sp_AdminUpdateGroup, param, 1);
		return result;
	}

	/**
	 * 
	 * @return
	 */
	private String getItemGroup() {
		String strGroup = "";
		strGroup = getStringGeneral(SQL.sp_admingetListGroup, "");
		return strGroup;
	}

	/**
	 * 
	 * @return
	 */
	public String admingetListStbAlls(String xmlparamter) {
		/*
		 * String strGroup = "";
		 * 
		 * String groupid = ""; strGroup = getStringGeneral(SQL.sp_admingetListStbAlls,groupid); return strGroup;
		 */

		String strGroup = "";
		// getSession(config.server_syn,new Integer(config.server_port).intValue());
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		Vector param = new Vector(2);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		param.add(0, groupid);
		param.add(1, creator);

		strGroup = getStringGenerals(SQL.sp_admingetListStbAlls, param, 1);
		return strGroup;
	}

	/**
	 * 
	 * @param groupid
	 * @return
	 */
	public String getScheduleGroup(String xmlparamter) {
		String strGroup = "";
		return strGroup;
	}

	/**
	 * 
	 * @param groupid
	 * @return
	 */
	public String getLayoutGroup(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector(2);
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String layoutnameid = DateHelper.utilStringXml(xmlparamter, SQL.layoutnameid);
		param.add(0, groupid);
		param.add(1, layoutnameid);

		result = getStringGenerals(SQL.sp_admingetLayoutGroup, param, 1);
		return result;
	}

	/**
	 * 
	 * @param groupid
	 * @return
	 */
	public String getStbGroup(String xmlparamter) {
		String strGroup = "";
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		Vector<Object> param = new Vector<Object>();
		param.add(groupid);
		param.add(creator);
		param.add(pcreator);
		strGroup = getStringGenerals(SQL.sp_getadminListStbGroup, param, 1);
		return strGroup;
	}

	/**
	 * 
	 * @param xmlparamter
	 * @return
	 */
	public String admingetListStbAll(String xmlparamter) {
		String strGroup = "";

		getSession(config.server_syn, new Integer(config.server_port).intValue());

		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		strGroup = getStringGeneral(SQL.sp_admingetListStbAll, groupid);
		return strGroup;
	}

	/**
	 * Ham lay danh sach cac set tup box chua su dung vao nho nao het
	 * 
	 * @param xmlparamter
	 * @return
	 */
	private String getListStb() {
		String strGroup = "";
		String groupid = "";
		strGroup = getStringGeneral(SQL.sp_admingetListStb, groupid);
		return strGroup;
	}

	/**
	 * 
	 * @param groupname
	 * @param idScreenresolution
	 * @param idScreenorientation
	 * @param idLayout
	 * @return
	 */
	public int updateGroup(String groupname, int idScreenresolution, int idScreenorientation, int idLayout) {
		int result = 0;
		return result;
	}

	/**
	 * 
	 * @param idgroup
	 * @return
	 */
	public int deleteGroup(String xmlparamter) {
		int result = 0;
		return result;
	}

	/**
	 * 
	 * @param idstb
	 * @param idgroup
	 */
	public String adminAddStbIntoGroup(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector(2);
		String stbid = DateHelper.utilStringXml(xmlparamter, SQL.stbid);
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		param.add(0, stbid);
		param.add(1, groupid);

		result = getStringGenerals(SQL.sp_adminAddStbIntoGroup, param, 0);

		return result;
	}

	/**
	 * 
	 * @param idstb
	 * @param idgroup
	 * @return
	 */
	public String adminRemoveStbGroup(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector(2);
		String stbid = DateHelper.utilStringXml(xmlparamter, SQL.stbid);
		String groupid = "-1";
		param.add(0, stbid);
		param.add(1, groupid);

		result = getStringGenerals(SQL.sp_abopRemoveStbFromGroup, param, 0);
		return result;
	}

	// ------------------------------------------------------------------------------------------
	private void adminUpdateInfoStb(String serinum, String mac, String session, String statusonoff) {
		Vector param = new Vector(4);
		param.add(0, serinum);
		param.add(1, mac);
		param.add(2, session);
		param.add(3, statusonoff);

		getStringGenerals(SQL.sp_adminUpdateInfoStb, param, 0);
	}

	/*
	 * sp_adminUpdateStbDefaultHome 10.10 input: mac, seriNumber, defaultHome
	 */
	public void adminUpdateStbDefaultHome(String xmlparamter) {
		Vector param = new Vector();
		String mac = DateHelper.utilStringXml(xmlparamter, "mac");
		String seriNumber = DateHelper.utilStringXml(xmlparamter, "seriNumber");
		String defaultHome = DateHelper.utilStringXml(xmlparamter, "defaultHome");
		param.add(0, mac);
		param.add(1, seriNumber);
		param.add(2, defaultHome);

		getStringGenerals(SQL.sp_adminUpdateStbDefaultHome, param, 0);
	}

	// -------------------------------------------------------------------------------------------
	private void getSession(String Server, int port) {
		VoStbSession item;
		DataSocketSesionStb sessionitem = new DataSocketSesionStb(Server, port);
		sessionitem.getListSessionStb(sessionitem, 6);
		Vector<VoStbSession> stbsessions = sessionitem.getStbSession();
		adminUpdateInfoStb("-1", "-1", "-1", "-1");
		for (int i = 0; i < stbsessions.size(); i++) {
			item = (VoStbSession) stbsessions.get(i);
			adminUpdateInfoStb(item.getSerial_num(), item.getMac(), item.getSesionid(), "Active");
		}
	}

	// -------------------------------------------------------------------------------------------
	public String admingetListStb(String xmlparamter) {
		// <group><groupid>3</groupid><namegroup>khong biet</namegroup></group>
		String result = "OK";
		getSession(config.server_syn, new Integer(config.server_port).intValue());

		Vector param = new Vector(1);
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		param.add(0, groupid);

		result = getStringGenerals(SQL.sp_admingetListStb, param, 1);

		return result;
	}

	// ------------------------------------------------------------------------------
	public String admingetConfig() {
		String result = "OK";
		Vector param = new Vector(1);
		String key = "";
		param.add(0, key);
		result = getStringGenerals(SQL.sp_admingetConfig, param, 1);
		return result;
	}

	// ------------------------------------------------------------------------------

	public void adminUpdateConfig_(String keyin, String valuesin) {
		Vector param = null;
		param = new Vector(2);
		param.add(0, keyin);
		param.add(1, valuesin);
		getStringGenerals(SQL.sp_adminUpdateConfig, param, 0);
	}

	public void adminUpdateConfig(String xmlparamter) {
		String result = "OK";
		String key = "", values = "";

		key = SQL.serverhostftp;
		values = DateHelper.utilStringXml(xmlparamter, SQL.serverhostftp);
		adminUpdateConfig_(key, values);

		key = SQL.serverportftp;
		values = DateHelper.utilStringXml(xmlparamter, SQL.serverportftp);
		adminUpdateConfig_(key, values);

		key = SQL.serveruserftp;
		values = DateHelper.utilStringXml(xmlparamter, SQL.serveruserftp);
		adminUpdateConfig_(key, values);

		key = SQL.serverpassftp;
		values = DateHelper.utilStringXml(xmlparamter, SQL.serverpassftp);
		adminUpdateConfig_(key, values);

		key = SQL.srcfile;
		values = DateHelper.utilStringXml(xmlparamter, SQL.srcfile);
		adminUpdateConfig_(key, values);

		key = SQL.desc_host;
		values = DateHelper.utilStringXml(xmlparamter, SQL.desc_host);
		adminUpdateConfig_(key, values);

		key = SQL.local_file;
		values = DateHelper.utilStringXml(xmlparamter, SQL.local_file);
		adminUpdateConfig_(key, values);

		key = SQL.timeout;
		values = DateHelper.utilStringXml(xmlparamter, SQL.timeout);
		adminUpdateConfig_(key, values);
	}

	// ------------------------------------------------------------------------------
	public String admingetContentStb(String xmlparamter) {
		// <paramter><stbid>3</stbid></paramter>
		String result = "OK";
		Vector param = new Vector(1);
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.stbid);
		param.add(0, groupid);

		result = getStringGenerals(SQL.sp_admingetContentStb, param, 1);

		return result;
	}

	// ------------------------------------------------------------------------------
	public void pushContentIntoStb(String xmlparamter) {
		System.out.println("pushContentIntoStb: " + config.pathfile + SQL.filestb);
		Vector<VoStbSession> vecstb = XmlAnalysis.getListStb(xmlparamter);
		ServiceContentDao content = new ServiceContentDao();
		content.sendMultiFile(vecstb);
		adminUpdatePushInfoStb(vecstb);
	}

	// -------------------------------------------------------------------------------
	public void adminUpdatePushInfoStb(Vector<VoStbSession> vecstb) {
		for (int i = 0; i < vecstb.size(); i++) {
			VoStbSession item = vecstb.get(i);
			String ser = item.getSerial_num();
			String mac = item.getMac();

			Vector param = new Vector(2);
			param.add(0, ser);
			param.add(1, mac);
			getStringGenerals(SQL.sp_adminUpdatePushInfoStb, param, 0);
		}
	}

	// -------------------------------------------------------------------------------
	public int removeContentStb(String xmlparamter) {
		Vector<VoStbSession> vecstb = XmlAnalysis.getListStb(xmlparamter);
		ServiceContentDao content = new ServiceContentDao();
		content.removeContentStb(vecstb);
		return 1;
	}

	// -------------------------------------------------------------------------------each 09052013
	public int removeContentStbEach(String xmlparamter, String mac) {
		Vector<VoStbSession> vecstb = XmlAnalysis.getListStb(xmlparamter);
		ServiceContentDao content = new ServiceContentDao();
		content.removeContentStbEach(vecstb, mac);
		return 1;
	}

	// --------------------------------------------------------------------------------
	public void adminInserContentIntoGroup(String xmlparamter) {
		Vector<VoContentGroup> veccontentgroup = XmlAnalysis.getListContentGroup(xmlparamter);
		VoContentGroup item;
		for (int i = 0; i < veccontentgroup.size(); i++) {
			item = veccontentgroup.get(i);
			Vector param = new Vector(2);
			String groupid = item.getGroupid() + "";
			String contentid = item.getContentid() + "";
			param.add(0, groupid);
			param.add(1, contentid);
			getStringGenerals(SQL.sp_adminInserContentIntoGroup, param, 0);
		}

	}

	// -------------------------------------------------------------------------------
	public void adminDeleteContentIntoGroup(String xmlparamter) {
		Vector<VoContentGroup> veccontentgroup = XmlAnalysis.getListContentGroup(xmlparamter);
		VoContentGroup item;
		for (int i = 0; i < veccontentgroup.size(); i++) {
			item = veccontentgroup.get(i);
			Vector param = new Vector(2);
			String groupid = item.getGroupid() + "";
			String contentid = item.getContentid() + "";
			param.add(0, groupid);
			param.add(1, contentid);
			getStringGenerals(SQL.sp_adminDeleteContentIntoGroup, param, 0);
		}

	}

	// -------------------------------------------------------------------------------
	public String adminNewContent(String xmlparamter) {
		// <parameter><contentname></contentname><contentype></contentype><urlcontent></urlcontent><durationContent></durationContent></parameter>
		String result = "OK";
		Vector param = new Vector(3);
		String contentname = DateHelper.utilStringXml(xmlparamter, SQL.contentname);
		String contentype = DateHelper.utilStringXml(xmlparamter, SQL.contentype);
		String urlcontent = DateHelper.utilStringXml(xmlparamter, SQL.urlcontent);
		String duration = DateHelper.utilStringXml(xmlparamter, "durationContent");
		param.add(0, contentname);
		param.add(1, contentype);
		param.add(2, urlcontent);
		param.add(3, duration);
		result = getStringGenerals(SQL.sp_adminNewContent, param, 0);

		return result;
	}

	// -------------------------------------------------------------------------------
	public String adminUpdateDirecGroup(String xmlparamter) {
		// <parameter><groupid></groupid></direction><direction></parameter>
		String result = "OK";
		Vector param = new Vector(2);
		String contentname = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String direction = DateHelper.utilStringXml(xmlparamter, SQL.direction);

		param.add(0, contentname);
		param.add(1, direction);

		getStringGenerals(SQL.sp_adminUpdateDirecGroup, param, 0);

		return "OK";
	}

	// -------------------------------------------------------------------------------
	public String sp_adminCheckStb(String xmlparamter) {
		// //<parameter><stbid>0</stbid></parameter>
		String result = "1";
		Vector param = new Vector(1);
		String stbid = DateHelper.utilStringXml(xmlparamter, SQL.stbid);

		param.add(0, stbid);

		result = getStringGenerals(SQL.sp_adminCheckStb, param, 1);

		return result;
	}

	// -------------------------------------------------------------------------------
	public String adminMoveGroup(String xmlparamter) {
		// //<parameter><groupid>0</groupid><groupmoveid>1</groupmoveid></parameter>
		String result = "OK";
		Vector param = new Vector(2);
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String groupmoveid = DateHelper.utilStringXml(xmlparamter, SQL.groupmoveid);

		param.add(0, groupid);
		param.add(1, groupmoveid);

		result = getStringGenerals(SQL.sp_adminMoveGroup, param, 0);

		return result;
	}

	// -------------------------------------------------------------------------------

	public String adminUdateStb(String xmlparamter) {
		// //<parameter><stbid>0</stbid><name>haha</name><urlserver></urlserver><savelocalmedia></savelocalmedia><savelocalfile></savelocalfile></parameter>
		String result = "OK";
		Vector param = new Vector();
		String stbid = DateHelper.utilStringXml(xmlparamter, SQL.stbid);
		String name = DateHelper.utilStringXml(xmlparamter, SQL.name);
		String urlserver = DateHelper.utilStringXml(xmlparamter, SQL.urlserver);
		String note = DateHelper.utilStringXml(xmlparamter, "note");
		param.add(0, stbid);
		param.add(1, name);
		param.add(2, urlserver);
		param.add(3, note);
		getStringGenerals(SQL.sp_adminUdateStb, param, 0);

		return result;
	}

	// -------------------------------------------------------------------------------
	public String adminStbPush(String xmlparamter) {
		/*
		 * <parameter> <item> <stbid>1</stbid> <groupid></groupid> </item> </parameter>
		 */
		String result = "OK";
		Vector<VoItem> items = XmlAnalysis.getListStbPush(xmlparamter, config.pathfile + SQL.filestbpush);
		VoItem item;
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			Vector param = new Vector(2);
			String layoutid = item.getP0() + "";
			String groudid = item.getP1() + "";
			param.add(0, layoutid);
			param.add(1, groudid);
			getStringGenerals(SQL.sp_adminStbPush, param, 0);
		}
		return result;
	}

	/*
	 * <parameter> <dailyid></dailyid> </parameter>
	 */
	public String getPlaylistIdByDaily(String xml) {
		String result = "";
		String id_daily = DateHelper.utilStringXml(xml, "dailyid");
		Vector param = new Vector(1);
		param.add(0, id_daily);
		result = getStringGenerals(SQL.sp_adminGetPlaylistIDByDaily, param, 1);
		return result;
	}

	/*
	 * <parameter><playlistid></playlistid></parameter>
	 */
	public String getContentByPlaylist(String xml) {
		String result = "";
		String id_playlist = DateHelper.utilStringXml(xml, "playlistid");
		Vector param = new Vector(1);
		param.add(0, id_playlist);
		result = getStringGenerals(SQL.sp_adminGetContentIDByPlaylist, param, 1);
		return result;
	}

	/*
	 * <parameter><groupid></groupid></parameter>
	 */
	public String getListStbInGroup(String xml) {
		String result = "";
		String groupid = DateHelper.utilStringXml(xml, SQL.groupid);
		Vector param = new Vector(1);
		param.add(0, groupid);
		result = getStringGenerals(SQL.sp_adminGetIdSTBInGroup, param, 1);
		return result;
	}

	public List<String> parseXmlDaily(String xml) {
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("playlistid").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);
				list.add(nValue.getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> parseXmlPlaylist(String xml, boolean flag) {
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlContent = eElement.getElementsByTagName("contentid").item(0).getChildNodes();
				Node vCotnent = (Node) nlContent.item(0);
				list.add(vCotnent.getNodeValue());
				if (flag) {
					NodeList nlType = eElement.getElementsByTagName("contenttype").item(0).getChildNodes();
					Node vType = (Node) nlType.item(0);
					list.add(vType.getNodeValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> parseXmlSTB(String xml) {
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("stbid").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);
				list.add(nValue.getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int checkExists(String contentid, List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equalsIgnoreCase(contentid)) {
				return 1;
			}
		}
		return 0;
	}

	// <parameter><dailyid>83</dailyid><groupid>111</groupid></parameter>
	public String pushContentIntoSTBAuto(String xml) {
		// String iddaily = DateHelper.utilStringXml(xml, "dailyid");
		// String idgroup = DateHelper.utilStringXml(xml, SQL.groupid);
		String iddaily = "33";
		String idgroup = "143";
		String xmldaily = "<parameter><dailyid>" + iddaily + "</dailyid></parameter>";
		String xmlgroup = "<parameter><groupid>" + idgroup + "</groupid></parameter>";
		List<String> listPlaylist = parseXmlDaily(getPlaylistIdByDaily(xmldaily));
		List<String> listContent = new ArrayList<String>();
		for (int i = 0; i < listPlaylist.size(); i++) {
			String xmlplaylist = "<parameter><playlistid>" + listPlaylist.get(i) + "</playlistid></parameter>";
			List<String> listtemp = parseXmlPlaylist(getContentByPlaylist(xmlplaylist), true);
			for (int j = 0; j < listtemp.size() - 1; j = j + 2) {
				if (listtemp.get(j + 1).equalsIgnoreCase("8")) {
					String xmlslide = "<parameter><id_Content>" + listtemp.get(j) + "</id_Content></parameter>";
					List<String> listContentSlide = parseXmlContentSlide(adminGetContentIDByForSlide(xmlslide));
					for (int y = 0; y < listContentSlide.size(); y++) {
						listContent.add(listContentSlide.get(y));
					}
				}
				listContent.add(listtemp.get(j));
			}
		}
		List<String> listStb = parseXmlSTB(getListStbInGroup(xmlgroup));
		for (int i = 0; i < listStb.size(); i++) {
			Vector param1 = new Vector(1);
			param1.add(0, listStb.get(i));
			String xmlContentSTB = getStringGenerals(SQL.sp_admingetContentStb, param1, 1);
			List<String> listOldContent = parseXmlPlaylist(xmlContentSTB, false);
			for (int j = 0; j < listOldContent.size(); j++) {
				if (checkExists(listOldContent.get(j), listContent) == 0) {
					Vector param = new Vector(2);
					param.add(0, listStb.get(i));
					param.add(1, listOldContent.get(j));
					getStringGenerals(SQL.sp_adminDelContentStb, param, 0);
				}
			}
			for (int j = 0; j < listContent.size(); j++) {
				Vector param = new Vector(2);
				param.add(0, listStb.get(i));
				param.add(1, listContent.get(j));
				getStringGenerals(SQL.sp_adminPushContentIntoSTB, param, 0);
			}
			Vector param = new Vector(1);
			param.add(0, listStb.get(i));
			getStringGenerals(SQL.sp_adminUpdateDownloadStatusSTB, param, 0);
		}
		return "OK";
	}

	public String adminAddSubjectContent(String xmlparamter) {
		// <parameter><namesubject></namesubject><descriptionsubject></descriptionsubject></parameter>
		String result = "OK";
		Vector param = new Vector();
		String subjectname = DateHelper.utilStringXml(xmlparamter, "namesubject");
		String subjectdescription = DateHelper.utilStringXml(xmlparamter, "descriptionsubject");
		String username = DateHelper.utilStringXml(xmlparamter, "username");
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(0, subjectname);
		param.add(1, subjectdescription);
		param.add(2, username);
		param.add(3, pcreator);

		result = getStringGenerals(SQL.sp_adminAddSubjectContent, param, 0);

		return result;
	}

	// <parameter><creator>admin</creator></parameter>
	public String adminGetAllSubjectContent(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector();
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(0, creator);
		param.add(1, pcreator);
		result = getStringGenerals(SQL.sp_adminGetAllSubjectContent, param, 1);
		return result;
	}

	// <parameter><idsubject></idsubject><namesubject></namesubject><descriptionsubject></descriptionsubject></parameter>
	public String adminUpdateSubjectContent(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector(3);
		String id = DateHelper.utilStringXml(xmlparamter, "idsubject");
		String name = DateHelper.utilStringXml(xmlparamter, "namesubject");
		String des = DateHelper.utilStringXml(xmlparamter, "descriptionsubject");
		param.add(0, id);
		param.add(1, name);
		param.add(2, des);
		getStringGenerals(SQL.sp_adminUpdateSubjectContent, param, 0);
		return result;
	}

	// <parameter><idsubject></idsubject></parameter>
	public String adminDeleteSubjectContent(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector(1);
		String id = DateHelper.utilStringXml(xmlparamter, "idsubject");
		param.add(0, id);
		getStringGenerals(SQL.sp_adminDeleteSubjectContent, param, 0);
		return result;
	}

	// <parameter><groupid></groupid></parameter>
	public String adminDeleteAllContentAllSTBInGroup(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector(1);
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		param.add(0, groupid);
		getStringGenerals(SQL.sp_adminDeleteAllContentAllSTBInGroup, param, 0);
		return result;
	}

	/*
	 * <parameter> <dailyid></dailyid> </parameter>
	 */
	public String adminGetPlaylistIdByGroup(String groupid) {
		String result = "";
		Vector param = new Vector(1);
		param.add(0, groupid);
		result = getStringGenerals(SQL.sp_adminGetPlaylistIdByGroupId, param, 1);
		return result;
	}

	// <parameter><groupid></groupid><liststb></liststb></parameter>
	public String adminAddSTBIntoGroup(String xmlparamter) {
		String result = "OK";
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String list = DateHelper.utilStringXml(xmlparamter, "liststb");
		String[] listStb = list.split(";");
		List<String> listPlaylist = parseXmlDaily(adminGetPlaylistIdByGroup(groupid));
		List<String> listContent = new ArrayList<String>();
		for (int i = 0; i < listPlaylist.size(); i++) {
			String xmlplaylist = "<parameter><playlistid>" + listPlaylist.get(i) + "</playlistid></parameter>";
			List<String> listtemp = parseXmlPlaylist(getContentByPlaylist(xmlplaylist), true);
			for (int j = 0; j < listtemp.size(); j = j + 2) {
				if (listtemp.get(j + 1).equalsIgnoreCase("8")) {
					String xmlslide = "<parameter><id_Content>" + listtemp.get(j) + "</id_Content></parameter>";
					List<String> listContentSlide = parseXmlContentSlide(adminGetContentIDByForSlide(xmlslide));
					for (int y = 0; y < listContentSlide.size(); y++) {
						listContent.add(listContentSlide.get(y));
					}
				}
				listContent.add(listtemp.get(j));
			}
		}

		for (int i = 0; i < listStb.length; i++) {
			for (int j = 0; j < listContent.size(); j++) {
				Vector param = new Vector(2);
				param.add(0, listStb[i]);
				param.add(1, listContent.get(j));
				getStringGenerals(SQL.sp_adminPushContentIntoSTB, param, 0);
			}
			Vector param = new Vector(1);
			param.add(0, listStb[i]);
			getStringGenerals(SQL.sp_adminUpdateDownloadStatusSTB, param, 0);
			String xml = "<parameter><groupid>" + groupid + "</groupid><stbid>" + listStb[i] + "</stbid></parameter>";
			adminAddStbIntoGroup(xml);
		}
		return result;
	}

	// <parameter><groupid></groupid></parameter>
	public String adminGetPlaylistByGroupId(String xmlparamter) {
		String result = "OK";
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		result = adminGetPlaylistIdByGroup(groupid);
		return result;
	}

	// <parameter><nameContent>test.mkv</nameContent><urlContent>test.mkv</urlContent>
	// <typeContent>1</typeContent><durationContent>00:00:00</durationContent>
	// <subjectcontent>5</subjectcontent></parameter>
	public String adminAddNewContentMedia(String xmlparamter) {
		String result = "OK";
		String name = DateHelper.utilStringXml(xmlparamter, "nameContent");
		String url = DateHelper.utilStringXml(xmlparamter, "urlContent");
		String type = DateHelper.utilStringXml(xmlparamter, "typeContent");
		String length = DateHelper.utilStringXml(xmlparamter, "durationContent");
		String subject = DateHelper.utilStringXml(xmlparamter, "subjectcontent");
		String creator = DateHelper.utilStringXml(xmlparamter, "creator");
		String pcreator = DateHelper.utilStringXml(xmlparamter, "parentcreator");
		Vector param = new Vector();
		param.add(0, name);
		param.add(1, url);
		param.add(2, type);
		param.add(3, length);
		param.add(4, subject);
		param.add(5, creator);
		param.add(6, pcreator);
		getStringGenerals(SQL.sp_adminAddNewContentMedia, param, 0);
		return result;
	}

	// <parameter><liststb></liststb></parameter>
	public String adminRemoveSTBOutToGroup(String xmlparamter) {
		String result = "OK";
		String liststb = DateHelper.utilStringXml(xmlparamter, "liststb");
		String[] arr = liststb.split(";");
		for (int i = 0; i < arr.length; i++) {
			Vector param = new Vector(1);
			param.add(0, arr[i]);
			getStringGenerals(SQL.sp_adminRemoveSTBOutToGroup, param, 0);
		}
		return result;
	}

	// <parameter><subjectContent></subjectContent><nameContent></nameContent><effectContent></effectContent>
	// <listOrder></listOrder><listContent></listContent><listTime></listTime></parameter>
	public String adminAddNewSlideContent(String xmlparamter) {
		String result = "OK";
		String idsubject = DateHelper.utilStringXml(xmlparamter, "subjectContent");
		String name = DateHelper.utilStringXml(xmlparamter, "nameContent");
		String effect = DateHelper.utilStringXml(xmlparamter, "effectContent");
		String lstodr = DateHelper.utilStringXml(xmlparamter, "listOrder");
		String lstcnt = DateHelper.utilStringXml(xmlparamter, "listContent");
		String lsttime = DateHelper.utilStringXml(xmlparamter, "listTime");
		String username = DateHelper.utilStringXml(xmlparamter, "username");
		String[] listorder = lstodr.split(";");
		String[] listcontent = lstcnt.split(";");
		String[] listtime = lsttime.split(";");
		String lengthContent = setlengthContent(lsttime);
		String idcontent = adminInsertSlideIntoContent(idsubject, name, effect, lengthContent, "8", username);
		String idslide = adminInsertSlideIntoSlide(idcontent, effect, "0");
		for (int i = 0; i < listcontent.length; i++) {
			adminInsertSlideIntoImageSlide(listcontent[i], idslide, listtime[i], listorder[i]);
		}
		return result;
	}

	public String adminInsertSlideIntoContent(String idsubject, String name, String effect, String length, String type,String username) {
		Vector param = new Vector();
		param.add(idsubject);
		param.add(name);
		param.add(effect);
		param.add(length);
		param.add(type);
		param.add(username);
		String result = getStringGenerals(SQL.sp_adminNewContentSlide, param, 1);
		return result;
	}

	public String adminInsertSlideIntoSlide(String idcontent, String effect, String delaytime) {
		Vector param = new Vector();
		param.add(idcontent);
		param.add(effect);
		param.add(delaytime);
		String result = getStringGenerals(SQL.sp_adminNewSlide, param, 1);
		return result;
	}

	public String adminInsertSlideIntoImageSlide(String idcontent, String idslide, String timeslide, String orderslide) {
		Vector param = new Vector(4);
		param.add(idcontent);
		param.add(idslide);
		param.add(timeslide);
		param.add(orderslide);
		getStringGenerals(SQL.sp_adminNewImageSlide, param, 0);
		return "OK";
	}

	public String setlengthContent(String str) {
		String[] list = str.split(";");
		long t = 0;
		for (int i = 0; i < list.length; i++) {
			t += Long.parseLong(list[i]);
		}

		int hours = (int) t / 3600;
		int minutes = (int) (t % 3600) / 60;
		int seconds = (int) t % 60;
		String hh = "" + hours;
		String mm = "" + minutes;
		String ss = "" + seconds;
		if (hours < 10)
			hh = "0" + hours;
		if (minutes < 10)
			mm = "0" + minutes;
		if (seconds < 10)
			ss = "0" + seconds;
		return hh + ":" + mm + ":" + ss;
	}

	/* <parameter><id_Content></id_Content></parameter> */
	public String adminDeleteSlideContent(String xmlparamter) {
		String result = "OK";
		String idcontent = DateHelper.utilStringXml(xmlparamter, "id_Content");
		Vector param = new Vector(1);
		param.add(idcontent);
		getStringGenerals(SQL.sp_adminDeleteSlideContent, param, 0);
		return result;
	}

	// <parameter><id_Content></id_Content></parameter>
	public String adminGetContentIDByForSlide(String xmlparamter) {
		String result = "";
		String idcontent = DateHelper.utilStringXml(xmlparamter, "id_Content");
		Vector param = new Vector(1);
		param.add(idcontent);
		result = getStringGenerals(SQL.sp_adminGetContentIDByForSlide, param, 1);
		return result;
	}

	public String adminUpdateSlideContent(String xmlparamter) {
		String result = "OK";
		String idcontent = DateHelper.utilStringXml(xmlparamter, "subjectContent");
		String name = DateHelper.utilStringXml(xmlparamter, "nameContent");
		String effect = DateHelper.utilStringXml(xmlparamter, "effectContent");
		String lstodr = DateHelper.utilStringXml(xmlparamter, "listOrder");
		String lstcnt = DateHelper.utilStringXml(xmlparamter, "listContent");
		String lsttime = DateHelper.utilStringXml(xmlparamter, "listTime");
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		String[] listorder = lstodr.split(";");
		String[] listcontent = lstcnt.split(";");
		String[] listtime = lsttime.split(";");
		String lengthContent = setlengthContent(lsttime);
		String idslide = updateSlideContent(idcontent, name, effect, lengthContent);
		for (int i = 0; i < listcontent.length; i++) {
			adminInsertSlideIntoImageSlide(listcontent[i], idslide, listtime[i], listorder[i]);
		}
		return result;
	}

	public String updateSlideContent(String idcontent, String name, String effect, String length) {
		Vector param = new Vector(4);
		param.add(idcontent);
		param.add(name);
		param.add(effect);
		param.add(length);
		String result = getStringGenerals(SQL.sp_adminUpdateSlideContent, param, 1);
		return result;
	}

	public List<String> parseXmlContentSlide(String xml) {
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;
				list.add(eElement.getAttributeNode("id").getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// -------------------------------------------------------------------------------

	public static void main(String[] arg) {
		GroupDao g = new GroupDao();
//		String xml = "<parameter><id_Content>816</id_Content></parameter>";
//		String rs = g.adminGetContentIDByForSlide(xml);
//		System.out.println(rs);
//		List<String> listContentSlide = g.parseXmlContentSlide(rs);
//		System.out.println(listContentSlide);
		System.out.println(g.pushContentIntoSTBAuto(""));
	}

}