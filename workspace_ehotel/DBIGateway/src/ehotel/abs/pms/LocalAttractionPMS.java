package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.config.PMSContant;
import ehotel.domain.pms.eAttraction;
import ehotel.domain.pms.eLocation;
import ehotel.domain.pms.eMenu;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class LocalAttractionPMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(LocalAttractionPMS.class);

	public int addAttractionMenu(eMenu menu) {
		int seq = -1;
		if (menu == null) {
			return seq;
		}
		seq = this.addMenu(menu, PMSContant.LOCAL_ATTRACTION, -1,
				PMSContant.level_attraction, PMSContant.LOCAL_ATTRACTION);
		return seq;
	}

	/**
	 * 
	 * @param menuId
	 * @return
	 */
	public boolean removeAttractionMenu(int menuId) {
		boolean isremove = this.removeMenu(menuId, PMSContant.level_attraction,
				PMSContant.LOCAL_ATTRACTION);
		return isremove;
	}

	/**
	 * 
	 * @param menu
	 * @param langId
	 * @return
	 */
	public boolean editAttractionMenu(eMenu menu, int langId) {
		boolean isEdit = this.editMenu(menu, langId,
				PMSContant.level_attraction, PMSContant.LOCAL_ATTRACTION);
		return isEdit;
	}

	/**
	 * Get list of Attraction Menus
	 * 
	 * @param serviceId
	 * @param langId
	 * @return
	 */
	public Vector<eMenu> getMenus(int langId) {
		return this.getMainMenu(PMSContant.LOCAL_ATTRACTION, langId);
	}

	/**
	 * 
	 * @param menuId
	 * @param attraction
	 * @return
	 */
	public int addItem(int menuId, eAttraction attraction) {
		int seqAdd = -1;
		if (attraction == null) {
			log.info("Input param is null.");
			return seqAdd;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(attraction.getName(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(attraction.getDef(), SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(attraction.getAddress(), SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 5

		try {
			params = executeSubPro(eHotelSql.sqlAddItemOfAttraction, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addItem (sql="
					+ eHotelSql.sqlAddItemOfAttraction + ") with params: "
					+ attraction.toString() + "]  : returnValue(seq=" + seqAdd
					+ ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean removeItem(String str_itemId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(str_itemId, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlRemoveItemOfAttraction, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removeItem (sql="
					+ eHotelSql.sqlRemoveItemOfAttraction
					+ ") with params: str_itemId=" + str_itemId
					+ "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	/**
	 * 
	 * @param attraction
	 * @param langId
	 * @return
	 */
	public boolean editItem(eAttraction attraction, int langId) {
		boolean isEdit = false;
		if (attraction == null) {
			log.info("Input param is null.");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				attraction.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(attraction.getName(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(attraction.getDef(), SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(attraction.getAddress(), SubProParam.IN);
		params.add(subPro);// 4

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 5

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 6

		try {
			params = executeSubPro(eHotelSql.sqlEditItemOfAttraction, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isEdit = true;
			}
			String outScreen = "[editItem (sql="
					+ eHotelSql.sqlEditItemOfAttraction + ") with params: "
					+ attraction.toString() + "] returnValue(isEdit=" + isEdit
					+ ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isEdit;
	}

	/**
	 * 
	 * @param itemId
	 * @param langId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public eAttraction getItemInfo(int itemId, int langId) {
		eAttraction attraction = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 3

		Vector<eAttraction> vImages = new Vector<eAttraction>();
		try {
			params = executeSubPro(eHotelSql.sqlGetItemInfo_Attraction, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		vImages = DataUtils.LoadItemOfAttraction(outParam);
		String outScreen = "[getItemInfo (sql="
				+ eHotelSql.sqlGetItemInfo_Attraction
				+ ") with params: itemId=" + itemId + ",langId=" + langId
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		if (vImages != null && vImages.size() > 0) {
			attraction = vImages.get(0);
		}
		return attraction;
	}

	/**
	 * 
	 * @param menuId
	 * @param langId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<eAttraction> getItems(int menuId, int langId, int from,
			int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 5

		Vector<eAttraction> vImages = new Vector<eAttraction>();
		try {
			params = executeSubPro(eHotelSql.sqlGetItemsOfAttraction, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		vImages = DataUtils.LoadItemOfAttraction(outParam);
		String outScreen = "[getItems(sql=" + eHotelSql.sqlGetItemsOfAttraction
				+ ") with params: menuId=" + menuId + ",langId=" + langId
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);

		return vImages;
	}

	/**
	 * 
	 * @param itemId
	 * @param newMenuId
	 * @return
	 */
	public boolean changeSubjectOfItem(int itemId, String newMenuId) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(newMenuId, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqlChangeSubjectOfItem_Attraction,
					params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[changeSubjectOfItem (sql="
					+ eHotelSql.sqlChangeSubjectOfItem_Attraction
					+ ") with params: image=" + itemId + ",newMenuId="
					+ newMenuId + "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isChange;
	}

	/**
	 * 
	 * @param itemId
	 * @return
	 */
	public boolean changeStatus(int itemId) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlChangeStatus_Attraction, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[changeStatus(sql="
					+ eHotelSql.sqlChangeStatus_Attraction
					+ ") with params: itemId=" + itemId
					+ "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isChange;
	}

	/**
	 * 
	 * @param menuId
	 * @return
	 */
	public int countItem(int menuId) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountItemOfAttraction, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countItem(sql="
					+ eHotelSql.sqlCountItemOfAttraction
					+ ") with params: menuId=" + menuId + ",returnValue="
					+ count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	public int setMapLocation(int itemId, eLocation location) {
		int seq = -1;
		if (location == null || location.getX() == null
				|| location.getY() == null) {
			log.info("Input param[eLocaltion] is null");
			return seq;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				location.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(location.getX(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(location.getY(), SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlSetMapLocation, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				seq = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[SetMapLocation(sql="
					+ eHotelSql.sqlSetMapLocation + ") with params: itemId="
					+ itemId + "," + location.toString() + ",returnValue="
					+ seq + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return seq;

	}

	@SuppressWarnings("unchecked")
	public eLocation getMapLocation(int itemId) {
		eLocation location = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		try {
			String outScreen = "[getMapLocation(sql="
					+ eHotelSql.sqlGetMapLocation + ") with params: itemId="
					+ itemId + "] )";
			log.info(outScreen);
			params = executeSubPro(eHotelSql.sqlGetMapLocation, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		location = DataUtils.LoadMapLocation(outParam);
		String outScreen = "[getMapLocation(sql=" + eHotelSql.sqlGetMapLocation
				+ ") with params: itemId=" + itemId + "] : returnValue(size="
				+ location.toString() + ")";
		outScreen = "-->: returnValue(" + location.toString() + ")";
		log.info(outScreen);

		return location;
	}
}
