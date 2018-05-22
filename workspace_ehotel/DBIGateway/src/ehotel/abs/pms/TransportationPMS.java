package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.config.PMSContant;
import ehotel.domain.pms.eMenu;
import ehotel.domain.pms.eUrlAirline;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class TransportationPMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(TransportationPMS.class);

	public Vector<eMenu> getMenus(int langId) {
		return this.getMainMenu(PMSContant.TRANSPORTATION, langId);
	}

	public Vector<eMenu> getItemMenu(int menuId, int langId, int from, int tto) {
		return this.getSubMenu(menuId, langId, from, tto);
	}

	public int addItemMenu(int menuId, eMenu menu) {
		int seq = -1;
		if (menu == null)
			return seq;
		seq = this.addMenu(menu, menuId, -1, PMSContant.level_transportation,
				PMSContant.TRANSPORTATION);
		return seq;
	}

	public boolean removeItemMenu(int menuId) {
		boolean isremove = this.removeMenu(menuId,
				PMSContant.level_transportation, PMSContant.TRANSPORTATION);
		return isremove;
	}

	public int countItemMenu(int menuId) {
		return this.countMenu(menuId, PMSContant.level_transportation);
	}

	public eMenu getItemMenuInfo(int itemMenuId, int langId) {
		return this.getMenuInfo(itemMenuId, langId,
				PMSContant.level_transportation);
	}

	public boolean editItemMenu(eMenu menu, int langId) {
		boolean isEdit = this.editMenu(menu, langId,
				PMSContant.level_transportation, PMSContant.TRANSPORTATION);
		return isEdit;
	}

	@SuppressWarnings("unchecked")
	public Vector<eUrlAirline> getUrlAirlines(int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(from),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eUrlAirline> vImages = new Vector<eUrlAirline>();
		try {
			params = executeSubPro(eHotelSql.sqlGetUrlAirlines, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadUrlAirlines(outParam);
		String outScreen = "[getUrlAirlines(sql=" + eHotelSql.sqlGetUrlAirlines
				+ ") with params: from=" + from + ",tto=" + tto
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public eUrlAirline geteUrlAirlineInfo(int urlId) {
		eUrlAirline airLine = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(urlId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eUrlAirline> vImages = new Vector<eUrlAirline>();

		String outScreen = "[getItems(sql=" + eHotelSql.sqlGetUrlAirlines
				+ ") with params: urlId=" + urlId + "]";
		log.info(outScreen);
		try {

			params = executeSubPro(eHotelSql.sqlGetUrlAirlineInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadUrlAirlines(outParam);
		if (vImages != null && vImages.size() > 0) {
			airLine = vImages.get(0);
			outScreen = "--> returnValue(" + airLine.toString() + ")";
			log.info(outScreen);
		}
		return airLine;
	}

	public int addUrlAirline(eUrlAirline url) {
		int seqAdd = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(url.getName(), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(url.getUrlImage(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(url.getUrlLink(), SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4
		try {
			String outScreen = "[addUrlAirline (sql="
					+ eHotelSql.sqlAddUrlAirline + ") with params: "
					+ url.toString() + "] ";
			params = executeSubPro(eHotelSql.sqlAddUrlAirline, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}

			if (seqAdd == -2)
				outScreen = "---> returnValue(seq=" + seqAdd
						+ "). Name is duplicated";
			else
				outScreen = "---> returnValue(seq=" + seqAdd + ")";

			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean removeUrlAirline(int urlId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(urlId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2
		try {
			String outScreen = "[removeUrlAirline (sql="
					+ eHotelSql.sqlRemoveUrlAirline + ") with params: urlId="
					+ urlId + "] ";
			params = executeSubPro(eHotelSql.sqlRemoveUrlAirline, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isRemove = true;
			}
			outScreen = "---> returnValue(seq=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean editUrlAirline(eUrlAirline url) {
		boolean isEdit = false;
		if (url == null) {
			log.info("Input params is null.");
			return isEdit;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				url.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(url.getName(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(url.getUrlImage(), SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(url.getUrlLink(), SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 5
		try {
			String outScreen = "[editUrlAirline (sql="
					+ eHotelSql.sqlEditUrlAirline + ") with params: "
					+ url.toString() + "] ";
			params = executeSubPro(eHotelSql.sqlEditUrlAirline, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isEdit = true;
			}
			outScreen = "---> returnValue(seq=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isEdit;
	}

	public boolean changeStaus(int urlId) {
		boolean isChange = false;

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(urlId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2
		try {
			String outScreen = "[changeStaus (sql=" + eHotelSql.sqlChangeStaus
					+ ") with params: urlId=" + urlId + "] ";
			params = executeSubPro(eHotelSql.sqlChangeStaus, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isChange = true;
			}
			outScreen = "---> returnValue(seq=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isChange;
	}

	public int countItem() {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountUrlAirline, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countItem(sql=" + eHotelSql.sqlCountUrlAirline
					+ ") with no params returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}
}
