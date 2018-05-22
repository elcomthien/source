package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.config.PMSContant;
import ehotel.domain.pms.eActivity;
import ehotel.domain.pms.eMenu;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class HotelActivityPMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(HotelActivityPMS.class);

	/**
	 * 
	 * @param name
	 * @param urlImage
	 * @param urlBg
	 * @return
	 */
	public int addActiMenu(eMenu menu) {
		int seq = -1;
		if (menu == null)
			return seq;

		seq = this.addMenu(menu, PMSContant.HOTEL_ACTIVITY, -1,
				PMSContant.level_activity, PMSContant.HOTEL_ACTIVITY);
		return seq;
	}

	/**
	 * 
	 * @param menuId
	 * @return
	 */
	public boolean removeActiMenu(int menuId) {
		boolean isremove = this.removeMenu(menuId, PMSContant.level_activity,
				PMSContant.HOTEL_ACTIVITY);
		return isremove;
	}

	/**
	 * 
	 * @param menu
	 * @param langId
	 * @return
	 */
	public boolean editActiMenu(eMenu menu, int langId) {
		if (menu == null)
			return false;
		boolean isEdit = this.editMenu(menu, langId, PMSContant.level_hotel,
				PMSContant.HOTEL_ACTIVITY);
		return isEdit;
	}

	/**
	 * 
	 * @param langId
	 * @return
	 */
	public Vector<eMenu> getMenus(int langId) {
		return this.getMainMenu(PMSContant.HOTEL_ACTIVITY, langId);
	}

	/**
	 * 
	 * @param menuId
	 * @param langId
	 * @param fro
	 * @param tto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<eActivity> getItems(int menuId, int langId, int fro, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(fro), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eActivity> vImages = new Vector<eActivity>();
		try {
			params = executeSubPro(eHotelSql.sqlGetItemsOfActi, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadItemOfActi(outParam);
		String outScreen = "[getItems(sql=" + eHotelSql.sqlGetItemsOfActi
				+ ") with params: menuId=" + menuId + ",langId=" + langId
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	/**
	 * 
	 * @param itemId
	 * @param langId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public eActivity getItemInfo(int itemId, int langId) {
		eActivity activity = null;
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

		Vector<eActivity> vImages = new Vector<eActivity>();
		try {
			params = executeSubPro(eHotelSql.sqlGetItemOfActi, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		vImages = DataUtils.LoadItemOfActi(outParam);
		String outScreen = "[getItemInfo (sql=" + eHotelSql.sqlGetItemOfActi
				+ ")with params: itemId=" + itemId + ",langId=" + langId + "])";
		log.info(outScreen);
		if (vImages != null && vImages.size() > 0) {
			activity = vImages.get(0);
			outScreen = "--> returnValue(size=" + vImages.size() + ")";
			log.info(outScreen);
		}
		return activity;
	}

	public int addItem(int menuId, eActivity acti) {
		int seqAdd = -1;
		if (acti == null || acti.getName() == null) {
			log.info("Input param is null. or Name is null");
			return seqAdd;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(acti.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String def = "";
		if (acti.getDef() != null)
			def = acti.getDef();
		subPro = new SubProParam(def, SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlAddItemOfActi, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addItem (sql=" + eHotelSql.sqlAddItemOfActi
					+ ") with params: " + acti.toString()
					+ "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
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
			params = executeSubPro(eHotelSql.sqlRemoveItemOfActi, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removeItem(sql="
					+ eHotelSql.sqlRemoveItemOfActi
					+ ") with params: str_itemId=" + str_itemId
					+ "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean editItem(eActivity acti, int langId) {
		boolean isEdit = false;
		if (acti == null || acti.getName() == null) {
			log.info("Input param is null.");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				acti.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(acti.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String def = "";
		if (acti.getDef() != null)
			def = acti.getDef();
		subPro = new SubProParam(def, SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 5

		try {
			params = executeSubPro(eHotelSql.sqlEditItemOfActi, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isEdit = true;
			}
			String outScreen = "[editItem (sql=" + eHotelSql.sqlEditItemOfActi
					+ ") with params: " + acti.toString()
					+ "] returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isEdit;
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
			params = executeSubPro(eHotelSql.sqlChangeSubjectOfItem_Acti,
					params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[changeSubjectOfItem (sql="
					+ eHotelSql.sqlChangeSubjectOfItem_Acti
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
			params = executeSubPro(eHotelSql.sqlChangeStatus_Acti, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[changeStatus(sql="
					+ eHotelSql.sqlChangeStatus_Acti + ") with params: itemId="
					+ itemId + "] returnValue(isChange=" + isChange + ")";
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
			params = executeSubPro(eHotelSql.sqlCountItemOfActi, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countItem(sql=" + eHotelSql.sqlCountItemOfActi
					+ ") with params: menuId=" + menuId + ",returnValue="
					+ count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

}
