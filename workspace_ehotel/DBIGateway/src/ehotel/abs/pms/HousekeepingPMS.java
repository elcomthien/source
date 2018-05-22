package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.config.PMSContant;
import ehotel.domain.pms.eHousekeeping;
import ehotel.domain.pms.eMenu;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class HousekeepingPMS extends PMSEHotel {

	private static Logger log = Logger.getLogger(HousekeepingPMS.class);

	public Vector<eMenu> getMenus(int langId) {
		return this.getMainMenu(PMSContant.HOUSEKEEPING, langId);
	}

	public eMenu getMenuHSInfo(int menuId, int langId) {
		return this.getMenuInfo(menuId, langId, PMSContant.level_housekeeping);
	}

	public boolean editMenuHousekeeping(eMenu menu, int langId) {
		return this.editMenu(menu, langId, PMSContant.level_housekeeping,
				PMSContant.HOUSEKEEPING);
	}

	@SuppressWarnings("unchecked")
	public Vector<eHousekeeping> getItems(int menuId, int langid, int fro,
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

		Vector<eHousekeeping> vImages = new Vector<eHousekeeping>();
		String outScreen = "[getItems(sql="
				+ eHotelSql.sqlgetItemsOfHousekeeping
				+ ") with params: menuId=" + menuId + "]";
		log.info(outScreen);
		try {
			params = executeSubPro(eHotelSql.sqlgetItemsOfHousekeeping, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadItemHousekeeping(outParam);
		outScreen = "-->returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);

		return vImages;
	}

	@SuppressWarnings("unchecked")
	public eHousekeeping getItemInfo(int itemId, int langid) {
		eHousekeeping aImage = null;
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

		Vector<eHousekeeping> vImages = new Vector<eHousekeeping>();
		String outScreen = "[getItems(sql="
				+ eHotelSql.sqlgetItemsOfHousekeeping
				+ ") with params: itemId=" + itemId + "]";
		log.info(outScreen);
		try {
			params = executeSubPro(eHotelSql.sqlgetItemHousekeepingInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		vImages = DataUtils.LoadItemHousekeeping(outParam);
		outScreen = "[getItemInfo with params: itemId=" + itemId + ",langId="
				+ langid + "] ";
		log.info(outScreen);
		if (vImages != null && vImages.size() > 0) {
			aImage = vImages.get(0);
			outScreen = "-->: returnValue(size=" + aImage.toString() + ")";
		}
		return aImage;
	}

	public int addItem(int menuId, eHousekeeping item) {
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

		String price = "";
		if (item.getPrice() != null)
			price = item.getPrice();
		subPro = new SubProParam(price, SubProParam.IN);
		params.add(subPro);// 3

		String unit = "";
		if (item.getIunit() != null)
			unit = item.getIunit();
		subPro = new SubProParam(unit, SubProParam.IN);
		params.add(subPro);// 3

		String image = "";
		if (item.getUrlImage() != null)
			image = item.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlAddHousekeepingItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addItem (sql="
					+ eHotelSql.sqlAddHousekeepingItem + ") with params: "
					+ item.toString() + "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean editItem(eHousekeeping item, int langid) {
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

		String price = "";
		if (item.getPrice() != null)
			price = item.getPrice();
		subPro = new SubProParam(price, SubProParam.IN);
		params.add(subPro);// 3

		String unit = "";
		if (item.getIunit() != null)
			unit = item.getIunit();
		subPro = new SubProParam(unit, SubProParam.IN);
		params.add(subPro);// 4

		String image = "";
		if (item.getUrlImage() != null)
			image = item.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 6

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 7
		try {
			params = executeSubPro(eHotelSql.sqleditHousekeepingItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isEdit = true;
			}
			String outScreen = "[editItem (sql="
					+ eHotelSql.sqleditHousekeepingItem + ") with params: "
					+ item.toString() + "] returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isEdit;
	}

	public boolean removeItem(String str_itemId) {
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
			params = executeSubPro(eHotelSql.sqlremoveHousekeepingItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removeItem(sql="
					+ eHotelSql.sqlremoveHousekeepingItem
					+ ") with params: str_itemId=" + str_itemId
					+ "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public int countItems(int menuId) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlcountHousekeepingItem, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countItem(sql="
					+ eHotelSql.sqlcountHousekeepingItem
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
			params = executeSubPro(eHotelSql.sqlChangeStatusHousekeeping,
					params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[SQL=(" + eHotelSql.sqlChangeStatusHousekeeping
					+ ") with params: itemId=" + itemId
					+ "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isChange;
	}
}
