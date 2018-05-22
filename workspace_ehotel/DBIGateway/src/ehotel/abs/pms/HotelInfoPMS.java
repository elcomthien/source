package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.config.PMSContant;
import ehotel.domain.pms.eImage;
import ehotel.domain.pms.eMenu;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class HotelInfoPMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(HotelInfoPMS.class);

	/**
	 * create new subject menu
	 * 
	 * @param menu
	 * @return return <code>sequence</code> value which save in database.if the
	 *         subject menu is completely added.<code>-1</code> otherwise.
	 */
	public int addHotelMenu(eMenu menu) {
		int seq = -1;
		if (menu == null) {
			return seq;
		}
		seq = this.addMenu(menu, PMSContant.HOTELINFO, -1,
				PMSContant.level_hotel, PMSContant.HOTELINFO);
		return seq;
	}

	/**
	 * remove subject menu
	 * 
	 * @param menuId
	 * @return <code>true</code> if the subject menu is completely removed.
	 *         <code>false</code> otherwise
	 */
	public boolean removeHotelMenu(int menuId) {
		boolean isremove = this.removeMenu(menuId, PMSContant.level_hotel,
				PMSContant.HOTELINFO);
		return isremove;
	}

	public boolean editHotelMenu(eMenu menu, int langId) {
		boolean isEdit = false;
		if (menu == null) {
			return isEdit;
		}
		isEdit = this.editMenu(menu, langId, PMSContant.level_hotel,
				PMSContant.HOTELINFO);
		return isEdit;
	}

	/**
	 * get all menu of hotel info
	 * 
	 * @param langId
	 * @return
	 */
	public Vector<eMenu> getMenus(int langId) {
		System.out.println("getMenus");
		return this.getMainMenu(PMSContant.HOTELINFO, langId);
	}

	/**
	 * get all item of a menu (hotel-info)
	 * 
	 * @param menuId
	 * @param langId
	 * @param from
	 * @param tto
	 * @retur Vector empty if there is no any subject.
	 *        <code>Vector<eImage></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException ,
	 */
	@SuppressWarnings("unchecked")
	public Vector<eImage> getItemsOfHotelInfo(int menuId, int langId, int from,
			int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eImage> vImages = new Vector<eImage>();
		try {
			params = executeSubPro(eHotelSql.sqlGetItemOfHotelInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadItemOfHotel(outParam);
		String outScreen = "[getItemsOfHotelInfo with params: menuId=" + menuId
				+ ",langId=" + langId + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;

	}

	/**
	 * add new item into menu
	 * 
	 * @param menuId
	 * @param name
	 * @param def
	 * @param urlImage
	 *            : image name
	 * @param urlBg
	 *            : image name
	 * @return
	 */
	public int addItemHotel(int menuId, String name, String def,
			String urlImage, String urlBg) {
		int seqAdd = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(name, SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(def, SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(urlImage, SubProParam.IN);
		params.add(subPro);// 4

		subPro = new SubProParam(urlBg, SubProParam.IN);
		params.add(subPro);// 5

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 6

		try {
			params = executeSubPro(eHotelSql.sqlAddItemOfHotelInfo, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[HotelInfoPMS.addItem with params: menuId="
					+ menuId + ",name=" + name + ",urlImage=" + urlImage
					+ "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	/**
	 * get info deatial of item
	 * 
	 * @param itemId
	 * @param langId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public eImage getItemInfo(int itemId, int langId) {
		eImage aImage = null;
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

		Vector<eImage> vImages = new Vector<eImage>();
		try {
			params = executeSubPro(eHotelSql.sqlGetItemInfo_Hotel, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadItemOfHotel(outParam);
		String outScreen = "[HotelInfoPMS.getItemInfo with params: itemId="
				+ itemId + ",langId=" + langId + "] ";
		log.info(outScreen);
		if (vImages != null && vImages.size() > 0) {
			aImage = vImages.get(0);
			outScreen = "-->: returnValue(size=" + aImage.toString() + ")";
		}
		return aImage;
	}

	public boolean removeItem(int itemId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlRemoveItemOfHotelInfo, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[HotelInfoPMS.removeItem with params: itemId="
					+ itemId + "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean removeItem(String str_itemId) {
		boolean isRemove = false;
		if (str_itemId == null) {
			log.info("Input is null");
			return isRemove;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(str_itemId, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlRemoveItems_hotel, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[HotelInfoPMS.removeItem with params: str_itemId="
					+ str_itemId + "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean editItem(eImage image, int langId) {
		boolean isEdit = false;
		if (image == null) {
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				image.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(image.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String def = "";
		if (image.getDef() != null)
			def = image.getDef();
		subPro = new SubProParam(def, SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 4

		String url = "";
		if (image.getUrlImage() != null)
			url = image.getUrlImage();
		subPro = new SubProParam(url, SubProParam.IN);
		params.add(subPro);// 5

		String urlBg = "";
		if (image.getUrlBg() != null)
			urlBg = image.getUrlBg();
		subPro = new SubProParam(urlBg, SubProParam.IN);
		params.add(subPro);// 6

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 7

		try {
			String outScreen = "[HotelInfoPMS.editItemHotel with params: image="
					+ image.toString() + "] returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
			params = executeSubPro(eHotelSql.sqlEditItemOfHotelInfo, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isEdit = true;
			}
			outScreen = "-->returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isEdit;
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
			params = executeSubPro(eHotelSql.sqlchangeSubjectOfItem, params);
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

	/**
	 * change visible or invisible status of item
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
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlChangeStatus, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[HotelInfoPMS.changeStatus with params: itemId="
					+ itemId + "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isChange;
	}

	/**
	 * get all subject which contain this item (itemId)
	 * 
	 * @param itemId
	 * @param langId
	 * @return
	 */
	public Vector<eMenu> getSubjectsInItem(int itemId, int langId) {
		return getSubjectInOut(itemId, langId, "IN");
	}

	/**
	 * get all subject which not contain this item (itemId)
	 * 
	 * @param itemId
	 * @param langId
	 * @return
	 */
	public Vector<eMenu> getSubjectsOutItem(int itemId, int langId) {
		return getSubjectInOut(itemId, langId, "OUT");
	}

	@SuppressWarnings("unchecked")
	private Vector<eMenu> getSubjectInOut(int itemId, int langId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eMenu> vImages = new Vector<eMenu>();
		try {
			params = executeSubPro(eHotelSql.sqlGetSubjectInOut, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadMenuPms(outParam);
		String outScreen = "[getSubjectInOut with params: itemId=" + itemId
				+ ",type=" + type + "] : returnValue(size=" + vImages.size()
				+ ")";
		log.info(outScreen);
		return vImages;
	}

	public int countItem(int menuId) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountItemOfHotel, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[HotelInfoPMS.countItem with params: menuId="
					+ menuId + ",returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return count;
	}
}
