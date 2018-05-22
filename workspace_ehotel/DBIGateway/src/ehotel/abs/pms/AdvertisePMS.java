package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.pms.eAdvertise;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class AdvertisePMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(AdvertisePMS.class);

	/**
	 * Get a list of advertising categories
	 * 
	 * @param langid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<String> getAdvTypeList(int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<String> vImages = new Vector<String>();
		try {
			params = executeSubPro(eHotelSql.sqlGetAdvTypeList, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadAdvTypeList(outParam);
		String outScreen = "[getItems(sql=" + eHotelSql.sqlGetAdvTypeList
				+ ") with params: langid=" + langid + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	/**
	 * Get a list of advertising images
	 * 
	 * @param langid
	 * @param from
	 * @param tto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<eAdvertise> getImageAdverties(int langid, int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);
		Vector<eAdvertise> vImages = new Vector<eAdvertise>();
		try {
			params = executeSubPro(eHotelSql.sqlGetImageAdverties, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadImageAdverties(outParam);
		String outScreen = "[getItems(sql=" + eHotelSql.sqlGetUrlAirlines
				+ ") with params: langid=" + langid + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	/**
	 * Get a advertising info
	 * 
	 * @param advId
	 * @param langid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public eAdvertise getAdvertiseInfo(int advId, int langid) {
		eAdvertise adv = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(advId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eAdvertise> vImages = new Vector<eAdvertise>();

		String outScreen = "[getAdvertiseInfo(sql="
				+ eHotelSql.sqlgetAdvertiseInfo + ") with params: advId="
				+ advId + "] ";
		log.info(outScreen);

		try {
			params = executeSubPro(eHotelSql.sqlgetAdvertiseInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadImageAdverties(outParam);
		if (vImages != null && vImages.size() > 0) {
			adv = vImages.get(0);
			outScreen = "--> returnValue(" + adv.toString() + ")";
			log.info(outScreen);
		}
		return adv;
	}

	/**
	 * create a new image of the ad
	 * 
	 * @param adv
	 * @param type
	 * @return
	 */
	public int addAdvertise(eAdvertise adv, String type) {
		int seqAdd = -1;
		if (adv == null) {
			log.info("Input param is null.");
			return seqAdd;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(adv.getName(), SubProParam.IN);
		params.add(subPro);// 1

		String image = "";
		if (adv.getUrlImage() != null)
			image = adv.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 2

		String imageBg = "";
		if (adv.getUrlBg() != null)
			imageBg = adv.getUrlBg();
		if (adv.getUrlIcon() != null)
			imageBg = adv.getUrlIcon();
		subPro = new SubProParam(imageBg, SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 5

		try {
			String outScreen = "[addItem (sql=" + eHotelSql.sqlAddAdvertise
					+ ") with params: " + adv.toString() + "] ";
			log.info(outScreen);

			params = executeSubPro(eHotelSql.sqlAddAdvertise, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			outScreen = "--> returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	/**
	 * edit image of the ad info
	 * 
	 * @param adv
	 * @param langid
	 * @return
	 */
	public boolean editAdvertise(eAdvertise adv, int langid) {
		boolean isEdit = false;
		if (adv == null) {
			log.info("Input param is null.");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				adv.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(adv.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String image = "";
		if (adv.getUrlImage() != null)
			image = adv.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 3

		String imageBg = "";
		if (adv.getUrlBg() != null)
			imageBg = adv.getUrlBg();
		subPro = new SubProParam(imageBg, SubProParam.IN);
		params.add(subPro);// 4

		subPro = new SubProParam(adv.getType(), SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 5

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 6
		try {
			String outScreen = "[addItem (sql=" + eHotelSql.sqleditAdvertise
					+ ") with params: " + adv.toString() + "] ";
			log.info(outScreen);

			params = executeSubPro(eHotelSql.sqleditAdvertise, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isEdit = true;
			}
			outScreen = "--> returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isEdit;
	}

	public boolean removeAdverties(String str_advId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(str_advId, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlremoveAdverties, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removeAdverties(sql="
					+ eHotelSql.sqlremoveAdverties
					+ ") with params: str_advId=" + str_advId
					+ "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	/**
	 * change active or deactive for adv
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
			params = executeSubPro(eHotelSql.sqlChangeStatusAdvertise, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[changeStatus(sql="
					+ eHotelSql.sqlChangeStatusAdvertise
					+ ") with params: itemId=" + itemId
					+ "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isChange;
	}

	public int countItem() {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountAdvertise, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[countItem(sql=" + eHotelSql.sqlCountAdvertise
					+ ") with no params -->returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

}
