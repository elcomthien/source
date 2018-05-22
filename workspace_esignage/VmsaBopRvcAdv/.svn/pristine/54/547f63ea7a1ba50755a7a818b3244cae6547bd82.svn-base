package com.elcom.adcenter.rvcadv.playlist;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Vector;

import javax.xml.xpath.XPathConstants;

import org.apache.axis2.wsdl.codegen.schema.TopElement;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.elcom.adcenter.rvcadv.broker.IMBroker;
import com.elcom.adcenter.rvcadv.broker.SubProParam;
import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;
import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.common.VoItem;
import com.elcom.adcenter.rvcadv.group.GroupDao;
import com.elcom.adcenter.rvcadv.util.DateHelper;
import com.elcom.adcenter.rvcadv.util.XPathReader;
import com.elcom.adcenter.rvcadv.util.XmlAnalysis;

public class PlaylistDao {

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
			FileInputStream fileinputstream = new FileInputStream(
					"Config/log4j.properties");
			props.load(fileinputstream);
			PropertyConfigurator.configure(props);
		} catch (IOException ex) {
			log.error(null, ex);
		}
	}

	// -------------------------------------------------------
	private String getStringGeneral(String query, String parami) {
		String result = "";
		log.info("query: " + query + " | param: " + parami);
		try {
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam param = null;
			param = new SubProParam(new String(parami), SubProParam.IN);
			params.add(0, param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
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
			return "";
		}
		return result;
	}

	/**
	 * 
	 * @param groupid
	 * @return
	 */
	public String getPlayListGroup(String xmlparamter) {
		String strGroup = "";
		Vector param = new Vector();
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		param.add(0,creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(1,pcreator);
		strGroup = getStringGenerals(SQL.sp_admingetPlayListGroup, param, 1);
		return strGroup;
	}

	// ------------------------------------------------------
	public String admingetPlayList(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector(1);
		String playlistid = DateHelper.utilStringXml(xmlparamter,
				SQL.playlistid);
		param.add(0, playlistid);
		result = getStringGenerals(SQL.sp_admingetPlayList, param, 1);
		return result;
	}

	// ------------------------------------------------------
	public String admingetPlayListLayout(String xmlparamter) {
		/**
		 * Lay danh sach item cua playlist chay trong bo cuc <parameter>
		 * <playlistid>12</playlistid> </parameter>
		 */
		String result = "OK";
		Vector param = new Vector(1);
		String playlistid = DateHelper.utilStringXml(xmlparamter,
				SQL.playlistid);
		param.add(0, playlistid);
		result = getStringGenerals(SQL.sp_admingetPlayListLayout, param, 1);
		return result;
	}

	// ------------------------------------------------------
	public int adminNewPlaylistName(String xmlparamter) {
		/**
		 * <Playlist> <item> <groupid></groupid> <nameplaylist></nameplaylist>
		 * <desc></desc> </item> </Playlist>
		 */
		Vector param = new Vector();
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String nameplaylist = DateHelper.utilStringXml(xmlparamter,
				SQL.nameplaylist);
		String desc = DateHelper.utilStringXml(xmlparamter, SQL.desc);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(0, groupid);
		param.add(1, nameplaylist);
		param.add(2, desc);
		param.add(3, creator);
		param.add(4, pcreator);
		String result = getStringGenerals(SQL.sp_adminNewPlaylistName, param, 1);
		return Integer.parseInt(result);
	}

	// ------------------------------------------------------
	public String admingetPlaylistItem(String xmlparamter) {
		/**
		 * <parameter><playlistid></playlistid></parameter>
		 */
		String result = "OK";
		Vector param = new Vector(1);
		String playlistid = DateHelper.utilStringXml(xmlparamter,
				SQL.playlistid);
		param.add(0, playlistid);
		result = getStringGenerals(SQL.sp_admingetPlaylistItem, param, 1);
		return result;
	}

	// -----------------------------------------------------
	public String adminUpdatePlayListName(String xmlparamter) {
		/**
		 * <parameter><playlistid></playlistid><nameplaylist></nameplaylist></
		 * parameter>
		 */
		String result = "OK";
		Vector param = new Vector(3);
		String playlistid = DateHelper.utilStringXml(xmlparamter,
				SQL.playlistid);
		String nameplaylist = DateHelper.utilStringXml(xmlparamter,
				SQL.nameplaylist);
		String des = DateHelper.utilStringXml(xmlparamter, SQL.desc);
		param.add(0, playlistid);
		param.add(1, nameplaylist);
		param.add(2, des);
		getStringGenerals(SQL.sp_adminUpdatePlayListName, param, 0);
		return result;
	}

	// -----------------------------------------------------
	public String adminUpdatePlaylistTime(String xmlparamter) {
		/**
		 * <parameter><item><playlistitemid></playlistitemid><startdate></
		 * startdate><stopdate></stopdate><item></parameter>
		 */
		String result = "OK";
		VoItem item;
		Vector<VoItem> items = XmlAnalysis.getListPlayListTime(xmlparamter);
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			Vector param = new Vector(3);
			String playlistitemid = (String) item.getP0();
			String startdate = (String) item.getP1();
			String stopdate = (String) item.getP2();

			param.add(0, playlistitemid);
			param.add(1, startdate);
			param.add(2, stopdate);

			getStringGenerals(SQL.sp_adminUpdatePlaylistTime, param, 0);
		}
		return result;
	}

	/**
	 * 
	 * @param xmlparamter
	 *            :
	 *            "<parameter><itemplaylistid>1;2;3</itemplaylistid></parameter>"
	 *            ;
	 * @return
	 */
	public String abopDeletePlaylistItems(String xmlparamter) {
		String result = "OK";
		String playlistitemid = DateHelper.utilStringXml(xmlparamter,
				SQL.itemplaylistid);
		if (playlistitemid.length() > 0) {
			String[] arrId = playlistitemid.split(";");
			int size = arrId.length;
			for (int i = 0; i < size; i++) {
				Vector param = new Vector(1);
				param.add(0, arrId[i]);
				getStringGenerals(SQL.sp_abopDeletePlaylistItems, param, 0);
			}
		}
		return result;
	}

	// -----------------------------------------------------
	public String adminDelItemPlaylist(String xmlparamter) {
		/**
		 * <parameter><item><playlistitemid></playlistitemid><item></parameter>
		 */
		String result = "OK";
		VoItem item;
		Vector<VoItem> items = XmlAnalysis.getListPlayListItemDel(xmlparamter,
				config.pathfile + SQL.filesplaylistitemdel);
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			Vector param = new Vector(1);
			String playlistitemid = (String) item.getP0();

			param.add(0, playlistitemid);

			getStringGenerals(SQL.sp_adminDelItemPlaylist, param, 0);
		}
		return result;
	}

	// -----------------------------------------------------
	public String adminDelPlaylist(String xmlparamter) {
		/**
		 * <parameter><playlistid></playlistid></parameter>
		 */
		String result = "";
		Vector param = new Vector(1);
		String playlistid = DateHelper.utilStringXml(xmlparamter,
				SQL.playlistid);
		param.add(0, playlistid);
		result = getStringGenerals(SQL.sp_adminDelPlaylist, param, 1);
		return result;
	}

	// ------------------------------------------------------
	public int adminInsertItemPlaylist(String xmlparamter) {
		/**
		 * <playlistitem> <item> <playlistid></playlistid>
		 * <layoutitemid></layoutitemid> <contentid></contentid> <name></name>
		 * <starttime></starttime> <stoptime></stoptime> </item> </playlistitem>
		 */
		String playlistid, layoutid, contentid, name, starttime, stoptime;
		XPathReader reader = new XPathReader(xmlparamter);
		NodeList nodes = (NodeList) reader.read("/playlistitem/item",
				XPathConstants.NODESET);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();
			playlistid = nodelist.item(1).getTextContent();
			log.info("Node id:" + playlistid);
			layoutid = nodelist.item(3).getTextContent();
			log.info("Node layoutid:" + layoutid);
			contentid = nodelist.item(5).getTextContent();
			log.info("Node content id:" + contentid);
			name = nodelist.item(7).getTextContent();
			log.info("Node name:" + name);
			starttime = nodelist.item(9).getTextContent();
			log.info("Node start time:" + starttime);
			stoptime = nodelist.item(11).getTextContent();
			log.info("Node stop time:" + stoptime);

			int iii = adminInsertPlaylistCore(playlistid, layoutid, contentid,
					name, starttime, stoptime);

		}
		return 1;
	}

	private int adminInsertPlaylistCore(String playlistid, String layoutid,
			String contentid, String name, String starttime, String stoptime) {
		int i = 0;
		try {
			Vector<SubProParam> params = new Vector<SubProParam>(7);
			SubProParam param = null;
			param = new SubProParam(new String(playlistid), SubProParam.IN);
			params.add(0, param);

			param = new SubProParam(new String(layoutid), SubProParam.IN);
			params.add(1, param);

			param = new SubProParam(new String(contentid), SubProParam.IN);
			params.add(2, param);

			param = new SubProParam(new String(name), SubProParam.IN);
			params.add(3, param);

			param = new SubProParam(new String(starttime), SubProParam.IN);
			params.add(4, param);

			param = new SubProParam(new String(stoptime), SubProParam.IN);
			params.add(5, param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(6, out_data);
			log.info(playlistid + " | " + layoutid + " | " + contentid + " | "
					+ name + " | " + starttime + " | " + stoptime);
			params = broker.executeSubPro(SQL.sp_adminInsertItemPlaylist,
					params);
			out_data = (SubProParam) params.get(6);
			String result = (String) out_data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public static void main(String[] arg) {
		String xmlparamter = "<playlistitem>\r\n" + "<item>\r\n"
				+ "<playlistid>5</playlistid>\r\n"
				+ "<layoutid>6</layoutid>\r\n" + "<contentid>6</contentid>\r\n"
				+ "<name>web</name>\r\n"
				+ "<starttime>00:00:01</starttime>\r\n"
				+ "<stoptime>00:11:22</stoptime>\r\n" + "</item>\r\n"
				+ "<item>\r\n" + "<playlistid>5</playlistid>\r\n"
				+ "<layoutid>30</layoutid>\r\n"
				+ "<name>Tangphamvang</name>\r\n"
				+ "<starttime>00:22:31</starttime>\r\n"
				+ "<stoptime>00:31:41</stoptime>\r\n" + "</item>\r\n"
				+ "<item>\r\n" + "<playlistid>5</playlistid>\r\n"
				+ "<layoutid>34</layoutid>\r\n"
				+ "<contentid>2</contentid>\r\n"
				+ "<name>vang sjc 9999</name>\r\n"
				+ "<starttime>00:33:44</starttime>\r\n"
				+ "<stoptime>00:44:55</stoptime>\r\n" + "</item>\r\n"
				+ "</playlistitem>";
		PlaylistDao listdao = new PlaylistDao();
		int i = listdao.adminInsertItemPlaylist(xmlparamter);

		System.out.println(i);
	}

	
}
