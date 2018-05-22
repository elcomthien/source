package com.elcom.adcenter.rvcadv.layout;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.xml.xpath.XPathConstants;

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

public class LayoutDao {
	// Refers the DB broker object
	private static IMBroker broker = IMBroker.getInstance();
	private static Configuration config = null;
	// To log application
	private static Logger log = Logger.getLogger(LayoutDao.class);
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

	/**
	 * @return
	 */
	public String getListLayOut() {
		String tmpString = "";
		return tmpString;
	}

	/**
	 * @param xml
	 *            file cau truc dinh nghia cac bo cuc
	 * @return
	 */
	public int newLayout(String xml) {
		int result = 0;
		return result;
	}

	/**
	 * @param idlayout
	 * @param idgroup
	 * @return
	 */
	public int setLayoutforGroup(int idlayout, int idgroup) {
		int result = 0;
		return result;
	}

	/**
	 * 
	 * @param idlayout
	 * @param idgroup
	 * @return
	 */
	public int removeLayoutforGroup(int idlayout, int idgroup) {
		int result = 0;
		return result;
	}

	/**
	 * @param xmlparamter
	 */
	public int adminInsertLayout(String xmlparamter) {
		String name, groupid, type, desc, x, y, width, heght, order;

		XPathReader reader = new XPathReader(xmlparamter);
		NodeList names = (NodeList) reader.read("/Layout/@*",
				XPathConstants.NODESET);
		String nameatri = (names.item(0).getNodeValue());
		log.info("params namelayoutkey:" + nameatri);
		String namelayout = (names.item(1).getNodeValue());

		NodeList nodes = (NodeList) reader.read("/Layout/item",
				XPathConstants.NODESET);

		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();
			name = nodelist.item(1).getTextContent();
			groupid = nodelist.item(3).getTextContent();
			type = nodelist.item(5).getTextContent();
			desc = nodelist.item(7).getTextContent();
			x = nodelist.item(9).getTextContent();
			y = nodelist.item(11).getTextContent();
			width = nodelist.item(13).getTextContent();
			heght = nodelist.item(15).getTextContent();
			order = nodelist.item(17).getTextContent();
			log.info("params name:" + name);
			log.info("params groupid:" + groupid);
			log.info("params type:" + type);
			log.info("params des:" + desc);
			log.info("params x:" + x);
			log.info("params y:" + y);
			log.info("params w:" + width);
			log.info("params h:" + heght);
			log.info("params order:" + order);
			log.info("Insert Start");
			adminInsertLayoutCore(name, nameatri, groupid, type,
					desc, x, y, width, heght, order);
			log.info("Insert End");

		}
		// Tao ten cua layout
		/*
		 * String xmlp = "<paramter>\r\n<layoutnameid>" + nameatri +
		 * "</layoutnameid>\r\n<layoutname>" + namelayout +
		 * "</layoutname>\r\n</paramter>"; adminNewLayoutName(xmlp);
		 */
		return 1;
	}

	/**
	 * @param name
	 * @param groupid
	 * @param type
	 * @param desc
	 * @param x
	 * @param y
	 * @param width
	 * @param heght
	 * @param order
	 * @return
	 */
	public String adminInsertLayoutCore(String name, String namelayoutkey,
			String groupid, String type, String desc, String x, String y,
			String width, String heght, String order) {
		String result = "";
		try {
			Vector<SubProParam> params = new Vector<SubProParam>(12);
			SubProParam param = null;
			param = new SubProParam(new String(name), SubProParam.IN);
			params.add(0, param);

			param = new SubProParam(new String(namelayoutkey), SubProParam.IN);
			params.add(1, param);

			param = new SubProParam(new String(groupid), SubProParam.IN);
			params.add(2, param);

			param = new SubProParam(new String(type), SubProParam.IN);
			params.add(3, param);

			param = new SubProParam(new String(desc), SubProParam.IN);
			params.add(4, param);

			param = new SubProParam(new String(x), SubProParam.IN);
			params.add(5, param);

			param = new SubProParam(new String(y), SubProParam.IN);
			params.add(6, param);

			param = new SubProParam(new String(width), SubProParam.IN);
			params.add(7, param);

			param = new SubProParam(new String(heght), SubProParam.IN);
			params.add(8, param);

			param = new SubProParam(new String(order), SubProParam.IN);
			params.add(9, param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(10, out_data);

			params = broker.executeSubPro(SQL.sp_adminInsertLayout, params);
			out_data = (SubProParam) params.get(10);
			result = (String) out_data.getValue();
			log.info("out data:" + result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return result;
		}
		return result;
	}

	// ==============================================
	public String getStringGenerals(String query, Vector paramiv, int paramout) {
		String result = "";
		int size = paramiv.size();
		SubProParam out_data;
		log.info("query: " + query + " | param size: " + size);
		try {
			Vector<SubProParam> params;
			if (paramout == 1) {
				params = new Vector<SubProParam>(size + 1);
			} else
				params = new Vector<SubProParam>(size);
			SubProParam param = null;
			for (int i = 0; i < size; i++) {
				String para = (String) paramiv.get(i);
				System.out.println(para);
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

	// ==============================================
	public String admingetLayoutName(String xmlparamter) {
		String result = "1";
		Vector param = new Vector();
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(0, groupid);
		param.add(1, creator);
		param.add(2, pcreator);
		result = getStringGenerals(SQL.sp_admingetLayoutName, param, 1);
		log.info("result:" + result);
		return result;
	}

	// =============================================
	public String admingetLayoutNameType(String xmlparamter) {
		String result = "1";
		Vector param = new Vector();
		String type = DateHelper.utilStringXml(xmlparamter, SQL.typelayout);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(0, type);
		param.add(1, creator);
		param.add(2, pcreator);
		result = getStringGenerals(SQL.sp_admingetLayoutNameType, param, 1);
		return result;
	}

	// =============================================
	/**
	 * 
	 * @param xmlparamter
	 *            <parameter><layoutid>1</layoutid></parameter>
	 * @return
	 */
	public String adminDelLayOutItem(String xmlparamter) {
		String result = "OK";
		Vector param = new Vector(1);
		String layoutid = DateHelper.utilStringXml(xmlparamter, SQL.layoutid);
		param.add(0, layoutid);

		result = getStringGenerals(SQL.sp_adminDelLayOutItem, param, 1);
		return result;
	}

	// =============================================
	public String adminNewLayoutName(String xmlparamter) {
		/*
		 * <paramter> <layoutname>Con khi</layoutname> </paramter>
		 */
		String result = "1";
		// TODO
		Vector param = new Vector();
		String groupid = DateHelper.utilStringXml(xmlparamter, SQL.groupid);
		String layoutname = DateHelper.utilStringXml(xmlparamter,
				SQL.layoutname);
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String direction = DateHelper.utilStringXml(xmlparamter, SQL.direction);
		String size_screen = DateHelper.utilStringXml(xmlparamter, SQL.size_screen);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		param.add(0, groupid);
		param.add(1, layoutname);
		param.add(2, creator);
		param.add(3, direction);
		param.add(4, size_screen);
		param.add(5, pcreator);

		result = getStringGenerals(SQL.sp_adminNewLayoutName, param, 1);
		return result;
	}

	// ============================================
	public String adminUpdateLayoutName(String xmlparamter) {
		/*
		 * <paramter> <layoutnameid>Con khi</layoutnameid> <layoutname>Con
		 * khi</layoutname> </paramter>
		 */
		String result = "OK";
		Vector param = new Vector(2);
		String layoutnameid = DateHelper.utilStringXml(xmlparamter,
				SQL.layoutnameid);
		String layoutname = DateHelper.utilStringXml(xmlparamter,
				SQL.layoutname);
		String size = DateHelper.utilStringXml(xmlparamter,
				SQL.size_screen);
		param.add(0, layoutnameid);
		param.add(1, layoutname);
		param.add(2, size);

		result = getStringGenerals(SQL.sp_adminUpdateLayoutName, param, 1);
		return "1";
	}

	// ============================================
	public String adminDelLayoutName(String xmlparamter) {
		/*
		 * <paramter> <layoutnameid>Con khi</layoutnameid> <layoutname>Con
		 * khi</layoutname> </paramter>
		 */
		String result = "1";
		Vector param = new Vector();
		String layoutnameid = DateHelper.utilStringXml(xmlparamter,
				SQL.layoutnameid);
		param.add(layoutnameid);

		result = getStringGenerals(SQL.sp_adminDelLayoutName, param, 1);
		return "1";
	}

	// ============================================
	public String adminUpdateLayoutCoor(String xmlparamter) {
		/*
		 * <parameter> <item> <layoutid>1</layoutid> <xcoor></xcoor>
		 * <ycoor></ycoor> <width></width> <height></height> </item>
		 * </parameter>
		 */

		VoItem item;
		log.info("start update");
		Vector<VoItem> items = XmlAnalysis.getListLayoutUpdate(xmlparamter);
		String result = "OK";
		for (int i = 0; i < items.size(); i++) {
			Vector param = new Vector(6);
			item = items.get(i);
			String layoutname = (String) item.getP0();
			String layoutid = (String) item.getP1();
			String xcoor = (String) item.getP2();
			String ycoor = (String) item.getP3();
			String width = (String) item.getP4();
			String height = (String) item.getP5();

			param.add(0, layoutname);
			log.info("name layout:" + layoutname);
			param.add(1, layoutid);
			log.info("id layout:" + layoutid);
			param.add(2, xcoor);
			log.info("left layout:" + xcoor);
			param.add(3, ycoor);
			log.info("top layout:" + ycoor);
			param.add(4, width);
			log.info("width layout:" + width);
			param.add(5, height);
			log.info("height layout:" + height);

			getStringGenerals(SQL.sp_adminUpdateLayoutCoor, param, 0);
		}
		return "OK";
	}

	// =============================================
	public String admingetTypeLayout() {
		String result = "OK";
		Vector param = new Vector(1);
		param.add(0, "0");
		result = getStringGenerals(SQL.sp_admingetTypeLayout, param, 1);
		return result;
	}

	// ============================================
	public String adminNewLayoutContent(String xmlparamter) {
		/**
		 * <layoutcontent> <playlistitemid></playlistitemid>
		 * <contentid></contentid> <layoutid></layoutid> </layoutcontent>
		 */
		String result = "OK";
		Vector param = new Vector(2);
		String layoutid = DateHelper.utilStringXml(xmlparamter, SQL.layoutid);
		String contentid = DateHelper.utilStringXml(xmlparamter, SQL.contentid);
		String playlistitemid = DateHelper.utilStringXml(xmlparamter,
				SQL.playlistitemid);
		param.add(0, playlistitemid);
		param.add(1, contentid);
		param.add(2, layoutid);

		getStringGenerals(SQL.sp_adminNewLayoutContent, param, 0);
		return result;
	}

	// =============================================
	public String admingetLayoutContainContent(String xmlparamter) {
		/**
		 * <parameter> <layoutid>1</layoutid> </parameter>
		 */
		String result = "OK";
		Vector param = new Vector(1);
		String layoutid = DateHelper.utilStringXml(xmlparamter, SQL.layoutid);
		param.add(0, layoutid);

		result = getStringGenerals(SQL.sp_admingetLayoutContainContent, param,
				1);
		return result;
	}

	// =============================================
	public static void main(String[] arg) {
		/*
		 * String xmlparamter = "<?xml version='1.0' encoding='UTF-8'?>\r\n" +
		 * "<Layout name='Name Layout HuuLN Test 01' id='3'>\r\n" + "<item>\r\n"
		 * + "<name>Clip 01</name>\r\n" + "<groupid>1</groupid>\r\n" +
		 * "<type>1</type>\r\n" + "<desc>HuuLN test Video</desc>\r\n" +
		 * "<x>180</x>\r\n" + "<y>0</y>\r\n" + "<width>1100</width>\r\n" +
		 * "<height>620</height>\r\n" + "<order>1</order>\r\n" + "</item>\r\n" +
		 * "<item>\r\n" + "<name>Logo</name>\r\n" + "<groupid>1</groupid>\r\n" +
		 * "<type>3</type>\r\n" + "<desc>HuuLN text Logo</desc>\r\n" +
		 * "<x>0</x>\r\n" + "<y>0</y>\r\n" + "<width>180</width>\r\n" +
		 * "<height>100</height>\r\n" + "<order>2</order>\r\n" + "</item>\r\n" +
		 * "</Layout>";
		 */
		// String xmlparamter = "<parameter>" +
		// "<layoutnameid>2</layoutnameid> " +
		// "<layoutname>Layout 024</layoutname> " +
		// "</parameter>";
		LayoutDao layoutdao = new LayoutDao();
		// String i = layoutdao.adminUpdateLayoutCoor(xmlparamter);
		// System.out.println("jaja: " + i);

		String xmltest = "<group><groupid>105</groupid><creator>admin</creator></group>";
		System.out.println(layoutdao.admingetLayoutName(xmltest));
	}
}
