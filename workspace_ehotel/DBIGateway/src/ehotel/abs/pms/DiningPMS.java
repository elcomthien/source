package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.config.PMSContant;
import ehotel.domain.pms.eItem;
import ehotel.domain.pms.eMenu;
import ehotel.domain.pms.eRestaurant;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class DiningPMS extends PMSEHotel {

	private static Logger log = Logger.getLogger(DiningPMS.class);

	/**
	 * get all main menu of dining service
	 * 
	 * @param langId
	 * @return
	 */
	public Vector<eMenu> getMenus(int langId) {
		return this.getMainMenu(PMSContant.DINING, langId);
	}

	/**
	 * get all sub menu of dining service
	 * 
	 * @param menuId
	 * @param langId
	 * @return
	 */
	public Vector<eMenu> getSubMenus(int mainMenuId, int langId) {
		return this.getSubMenu(mainMenuId, langId, -1, -1);
	}

	public eMenu getMenuInfo(int menuId, int langid) {
		return this.getMenuInfo(menuId, langid, 2);
	}

	public int addDiningMenu(int parentId, eMenu menu) {
		int seqAdd = -1;
		if (menu == null) {
			log.info("Input param[menu is null].");
			return seqAdd;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(parentId), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(menu.getMenuName(), SubProParam.IN);
		params.add(subPro);// 2

		String image = "";
		if (menu.getUrlImage() != null)
			image = menu.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 3

		String bg = "";
		if (menu.getUrlBg() != null)
			bg = menu.getUrlBg();
		subPro = new SubProParam(bg, SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = executeSubPro(eHotelSql.sqladdMenuDining, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[-->(sql=" + eHotelSql.sqladdMenuDining
					+ ")   with params: " + menu.toString() + ",parentId="
					+ parentId + "] : returnValue(isAdd =" + seqAdd + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return seqAdd;

	}

	public boolean editDiningMenu(eMenu menu, int langid) {
		return this.editMenu(menu, langid, PMSContant.level_dinning,
				PMSContant.DINING);
	}

	public boolean removeDiningMenu(int menuId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlremoveMenuDining, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isRemove = true;
				else
					log.info("Not Exist");

			}
			String outScreen = "[sql=" + eHotelSql.sqlremoveMenuDining
					+ "  with params:menuId= " + menuId
					+ "]  : returnValue(isRemove =" + isRemove + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return isRemove;
	}

	// phan in-room dining
	@SuppressWarnings("unchecked")
	public eItem getRoomSvcItemInfo(int itemId, int langid) {
		eItem aItem = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eItem> vImages = new Vector<eItem>();
		String outScreen = "[getItemInfo with params: itemId=" + itemId + "]";
		log.info(outScreen);
		try {
			params = executeSubPro(eHotelSql.sqlgetRoomSvcItemInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadItemDining(outParam);
		if (vImages != null && vImages.size() > 0) {
			aItem = vImages.get(0);
			outScreen = "-->returnValue(" + aItem.toString() + ")";
			log.info(outScreen);
		}

		return aItem;
	}

	@SuppressWarnings("unchecked")
	public Vector<eItem> getRoomSvcItems(int menuId, int langid, int fro,
			int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(fro), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eItem> vImages = new Vector<eItem>();
		String outScreen = "[getItems(sql=" + eHotelSql.sqlgetRoomSvcItems
				+ ") with params: menuId=" + menuId + "]";
		log.info(outScreen);
		try {
			params = executeSubPro(eHotelSql.sqlgetRoomSvcItems, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadItemDining(outParam);
		outScreen = "-->returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);

		return vImages;
	}

	public int addRoomSvcItem(eItem item, int menuId) {
		int seqAdd = -1;
		if (item == null) {
			log.info("Input[eItem] is null");
			return seqAdd;

		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(item.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String def = "";
		if (item.getDef() != null)
			def = item.getDef();
		subPro = new SubProParam(def, SubProParam.IN);
		params.add(subPro);// 3

		String Currency = "0";
		if (item.getCurrency() != null)
			Currency = item.getCurrency();
		subPro = new SubProParam(Currency, SubProParam.IN);
		params.add(subPro);// 4

		String Currency_small = item.getCurrency_small();
		if (item.getCurrency_small() == null)
			Currency_small = Currency;
		subPro = new SubProParam(Currency_small, SubProParam.IN);
		params.add(subPro);// 5

		String Currency_large = item.getCurrency_large();
		if (item.getCurrency_large() == null)
			Currency_large = Currency;
		subPro = new SubProParam(Currency_large, SubProParam.IN);
		params.add(subPro);// 6

		String image = "";
		if (item.getUrlImage() != null)
			image = item.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 7

		String unit = "USD";
		if (item.getIUnit() != null)
			unit = item.getIUnit();
		subPro = new SubProParam(unit, SubProParam.IN);
		params.add(subPro);// 8

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 9

		try {
			params = executeSubPro(eHotelSql.sqlAddItemDining, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				seqAdd = Utils.parseInt(paramOUT.getString());
				if (seqAdd == -2)
					log.info("ItemName is duplicated.");

			}
			String outScreen = "[--> addItem(sql=" + eHotelSql.sqlAddItemDining
					+ ")  with params: " + item.toString()
					+ "-->returvalue[seqAdd=" + seqAdd + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean removeRoomSvcItem(int itemId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlRemoveItemDining, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removeItem(sql="
					+ eHotelSql.sqlRemoveItemDining + ") with params: itemId="
					+ itemId + "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean editRoomSvcItem(eItem item, int langid) {
		boolean isEdit = false;
		if (item == null) {
			log.info("Input param is null.");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				item.getICode()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(item.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String def = "";
		if (item.getDef() != null)
			def = item.getDef();
		subPro = new SubProParam(def, SubProParam.IN);
		params.add(subPro);// 3

		String Currency = "0";
		if (item.getCurrency() != null)
			Currency = item.getCurrency();
		subPro = new SubProParam(Currency, SubProParam.IN);
		params.add(subPro);// 4

		String Currency_small = item.getCurrency_small();
		if (item.getCurrency_small() == null)
			Currency_small = Currency;
		subPro = new SubProParam(Currency_small, SubProParam.IN);
		params.add(subPro);// 5

		String Currency_large = item.getCurrency_large();
		if (item.getCurrency_large() == null)
			Currency_large = Currency;
		subPro = new SubProParam(Currency_large, SubProParam.IN);
		params.add(subPro);// 6

		String image = "0";
		if (item.getUrlImage() != null)
			image = item.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 7

		String unit = "USD";
		if (item.getIUnit() != null)
			unit = item.getIUnit();
		subPro = new SubProParam(unit, SubProParam.IN);
		params.add(subPro);// 8

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 9

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 10

		try {
			String outScreen = "[addItem (sql=" + eHotelSql.sqleditItemDining
					+ ") with params: " + item.toString() + "] ";
			log.info(outScreen);

			params = executeSubPro(eHotelSql.sqleditItemDining, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isEdit = true;
				else if (Utils.parseInt(paramOUT.getString()) == -2)
					log.info("ItemName is duplicated.");

			}
			outScreen = "--> returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isEdit;
	}

	public int countRoomSvcItem(int subMenuId) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(subMenuId), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlcountItemDining, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[countItem(sql=" + eHotelSql.sqlcountItemDining
					+ ") with no params -->returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	public boolean changeItemStatus(int itemId) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlChangeStatusRoomsvcItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[SQL=(" + eHotelSql.sqlChangeStatusRoomsvcItem
					+ ") with params: itemId=" + itemId
					+ "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isChange;
	}

	// PHAN RESTAURANT
	@SuppressWarnings("unchecked")
	public Vector<eRestaurant> getRestaurantItems(int menuId, int langid,
			int fro, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(fro), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eRestaurant> vImages = new Vector<eRestaurant>();
		String outScreen = "[getItems(sql=" + eHotelSql.sqlgetRestaurantItems
				+ ") with params: menuId=" + menuId + "]";
		log.info(outScreen);
		try {
			params = executeSubPro(eHotelSql.sqlgetRestaurantItems, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadItemRestauarnt(outParam);
		outScreen = "-->returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);

		return vImages;
	}

	@SuppressWarnings("unchecked")
	public eRestaurant getResItemInfo(int itemId, int langid) {
		eRestaurant aImage = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 3

		Vector<eRestaurant> vImages = new Vector<eRestaurant>();
		try {
			params = executeSubPro(eHotelSql.sqlgetResItemInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadItemRestauarnt(outParam);
		String outScreen = "[getResItemInfo with params: itemId=" + itemId
				+ ",langId=" + langid + "] ";
		log.info(outScreen);
		if (vImages != null && vImages.size() > 0) {
			aImage = vImages.get(0);
			outScreen = "-->: returnValue(size=" + aImage.toString() + ")";
		}
		return aImage;
	}

	public int addRestaurantItem(eRestaurant item, int menuId) {
		int seqAdd = -1;
		if (item == null || item.getName() == null) {
			log.info("Input param is null. or Name is null");
			return seqAdd;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(item.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String def = "";
		if (item.getDef() != null)
			def = item.getDef();
		subPro = new SubProParam(def, SubProParam.IN);
		params.add(subPro);// 3

		String image = "";
		if (item.getUrlImage() != null)
			image = item.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlAddRestaurantItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addItem (sql="
					+ eHotelSql.sqlAddRestaurantItem + ") with params: "
					+ item.toString() + "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean removeRestaurantItem(String str_itemId) {
		boolean isRemove = false;
		if (str_itemId == null) {
			log.info("Input param is null.");
			return isRemove;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(str_itemId, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlremoveRestaurantItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removeItem(sql="
					+ eHotelSql.sqlremoveRestaurantItem
					+ ") with params: str_itemId=" + str_itemId
					+ "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean editRestaurantItem(eRestaurant item, int langId) {
		boolean isEdit = false;
		if (item == null || item.getName() == null) {
			log.info("Input param is null.");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				item.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(item.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String def = "";
		if (item.getDef() != null)
			def = item.getDef();
		subPro = new SubProParam(def, SubProParam.IN);
		params.add(subPro);// 3

		String image = "";
		if (item.getUrlImage() != null)
			image = item.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 5

		try {
			params = executeSubPro(eHotelSql.sqleditRestaurantItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isEdit = true;
			}
			String outScreen = "[editRestaurantItem (sql="
					+ eHotelSql.sqleditRestaurantItem + ") with params: "
					+ item.toString() + "] returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isEdit;
	}

	public int countRestaurantItem(int menuId) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlcountRestaurantItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[sqlcountRestaurantItem(sql="
					+ eHotelSql.sqlcountRestaurantItem
					+ ") with params: menuId=" + menuId + ",returnValue="
					+ count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	public boolean changeStatus(int itemId) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlChangeStatusResItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[SQL=(" + eHotelSql.sqlChangeStatusResItem
					+ ") with params: itemId=" + itemId
					+ "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isChange;
	}

	/**
	 * change new subject which itemId is contained.
	 * 
	 * @param itemId
	 * @param subj_id_str
	 * @return
	 */
	public boolean changeSubjectOfItem(int itemId, String subj_id_str) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(subj_id_str, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqlchangeSubjResItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[HotelInfoPMS.changeSubjectOfItem with params: image="
					+ itemId
					+ ",subj_id_str="
					+ subj_id_str
					+ "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isChange;
	}
}
