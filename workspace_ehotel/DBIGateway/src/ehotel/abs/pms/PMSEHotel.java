package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.config.PMSContant;
import ehotel.dao.eBaseDao;
import ehotel.domain.pms.eMenu;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public abstract class PMSEHotel extends eBaseDao {
	private static Logger log = Logger.getLogger(PMSEHotel.class);

	public PMSEHotel() {
		super();
	}

	protected int addMenu(eMenu menu, int serviceId, int parentId, int level,
			int typePMS) {
		switch (typePMS) {
		case PMSContant.HOTELINFO:
			SqlQuery = eHotelSql.sqlAddMenu;
			RVSServiceID = PMSContant.HOTELINFO;
			break;
		case PMSContant.TRANSPORTATION:
			SqlQuery = eHotelSql.sqlAddMenu;
			RVSServiceID = PMSContant.TRANSPORTATION;
			break;

		default:
			SqlQuery = eHotelSql.sqlAddMenu;
			break;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = -1;
		SubProParam subPro = new SubProParam(menu.getMenuName(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(serviceId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(parentId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(level),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(menu.getUrlImage(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(menu.getUrlBg(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(RVSServiceID),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(SqlQuery, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				ret = Utils.parseInt(paramOUT.getString());

			}
			String outScreen = "[--> addMenu  with params: " + menu.toString()
					+ ",serviceId=" + serviceId + ",parentId=" + parentId
					+ ",level=" + level + ",typePMS=" + typePMS
					+ "] : returnValue(isAdd =" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	protected boolean removeMenu(int menuId, int level, int typePMS) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(level),
				SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(new java.math.BigDecimal(typePMS),
				SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			SqlQuery = eHotelSql.sqlRemoMenu;
			params = executeSubPro(SqlQuery, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				log.info("Utils.parseInt(paramOUT.getString()="
						+ Utils.parseInt(paramOUT.getString()));
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isRemove = true;
				else
					log.info("Not Exist");

			}
			String outScreen = "[--> removeMenu  with params:menuId= " + menuId
					+ ",level=" + level + ",typePMS=" + typePMS
					+ "]  : returnValue(isRemove =" + isRemove + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return isRemove;
	}

	protected boolean editMenu(eMenu menu, int langId, int level, int typePMS) {
		boolean isEdit = false;
		if (menu == null) {
			log.info("Input param[menu is null]");
			return isEdit;
		}
		switch (typePMS) {
		case PMSContant.HOTELINFO:
			SqlQuery = eHotelSql.sqlEditMenu;
			RVSServiceID = PMSContant.HOTELINFO;
			break;
		case PMSContant.HOTEL_ACTIVITY:
			SqlQuery = eHotelSql.sqlEditMenu;
			RVSServiceID = PMSContant.HOTEL_ACTIVITY;
			break;
		case PMSContant.LOCAL_ATTRACTION:
			SqlQuery = eHotelSql.sqlEditMenu;
			RVSServiceID = PMSContant.LOCAL_ATTRACTION;
			break;
		case PMSContant.DINING:
			SqlQuery = eHotelSql.sqlEditMenu;
			RVSServiceID = PMSContant.DINING;
			break;
		case PMSContant.TRANSPORTATION:
			SqlQuery = eHotelSql.sqlEditMenu;
			RVSServiceID = PMSContant.TRANSPORTATION;
		default:
			SqlQuery = eHotelSql.sqlEditMenu;
			break;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				menu.getMenuId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(menu.getMenuName(), SubProParam.IN);
		params.add(subPro);// 2

		String url = "";
		if (menu.getUrlImage() != null)
			url = menu.getUrlImage();
		subPro = new SubProParam(url, SubProParam.IN);
		params.add(subPro);// 3

		String urlBg = "";
		if (menu.getUrlBg() != null)
			urlBg = menu.getUrlBg();
		subPro = new SubProParam(urlBg, SubProParam.IN);
		params.add(subPro);// 4

		subPro = new SubProParam(new java.math.BigDecimal(RVSServiceID),
				SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(new java.math.BigDecimal(level),
				SubProParam.IN);
		params.add(subPro);// 6

		subPro = new SubProParam(new java.math.BigDecimal(typePMS),
				SubProParam.IN);
		params.add(subPro);// 7

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 7

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 8
		String outScreen = "[--> editMenu  with params: " + menu.toString()
				+ "],typePMS=" + typePMS + ",RVSServiceID=" + RVSServiceID
				+ ",level=" + level + "]";
		try {

			log.info(outScreen);
			params = executeSubPro(SqlQuery, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isEdit = true;
				if (Utils.parseInt(paramOUT.getString()) == -2) {
					isEdit = false;
					log.info("Duplicate menuName");
				}
			}
			outScreen = "  : returnValue(isEdit =" + isEdit;

			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isEdit;
	}

	@SuppressWarnings("unchecked")
	protected Vector<eMenu> getMainMenu(int serviceId, int langId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(serviceId), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eMenu> vImages = new Vector<eMenu>();
		try {
			params = executeSubPro(eHotelSql.sqlGetMainMenu, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadMenuPms(outParam);
		String outScreen = "[getMainMenu with params: langId=" + langId
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	protected Vector<eMenu> getSubMenu(int serviceId, int langId, int from,
			int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(serviceId), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eMenu> vImages = new Vector<eMenu>();
		try {
			params = executeSubPro(eHotelSql.sqlGetSubMenu, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadMenuPms(outParam);
		String outScreen = "[getSubMenu with params: langId=" + langId
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	protected int countMenu(int serviceId, int level) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(serviceId), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(level),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountMenu, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countItem(sql=" + eHotelSql.sqlCountMenu
					+ ") with no params returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	protected eMenu getMenuInfo(int menuId, int langId, int level) {
		eMenu menu = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(level),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eMenu> vImages = new Vector<eMenu>();
		String outScreen = "[getSubMenu with params: langId=" + langId + "]";
		log.info(outScreen);
		try {
			params = executeSubPro(eHotelSql.sqlGetMenuInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadMenuPms(outParam);
		if (vImages != null && vImages.size() > 0) {
			menu = vImages.get(0);
			outScreen = "-->returnValue(" + menu.toString() + ")";
			log.info(outScreen);
		}

		return menu;
	}

	protected String replaceSpecialSymbols(String data) {
		// ky tu <br>
		data.replaceAll("<br>", " ");
		data.replaceAll("</br>", " ");
		data.replaceAll("<br />", " ");
		// ky tu <p>
		data.replaceAll("<p>", " ");
		data.replaceAll("</p>", " ");
		// ky tu <strong>
		data.replaceAll("<strong>", " ");
		data.replaceAll("</strong>", " ");
		// <span style="font-weight: bold;">
		data.replaceAll("<span style=\"font-weight: bold;>\"", " ");
		// <li>
		data.replaceAll("<li>", " ");
		// h3,h1,h2
		data.replaceAll("<h3>", " ");
		data.replaceAll("</h3>", " ");
		// h3,h1,h2
		data.replaceAll("<h1>", " ");
		data.replaceAll("</h1>", " ");
		return data;

	}
}
