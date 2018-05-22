package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.pms.ePromotion;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class PromotionPMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(PromotionPMS.class);

	@SuppressWarnings("unchecked")
	public Vector<ePromotion> getPromotions(int langId, int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<ePromotion> vImages = new Vector<ePromotion>();
		try {
			params = executeSubPro(eHotelSql.sqlGetPromotions, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadPromotions(outParam);
		String outScreen = "[getPromotions(sql=" + eHotelSql.sqlGetPromotions
				+ ") with params: langId=" + langId + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public ePromotion getPromotionInfo(int itemId, int langid) {
		ePromotion pro = null;
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

		Vector<ePromotion> vImages = new Vector<ePromotion>();
		try {
			params = executeSubPro(eHotelSql.sqlGetPromotionInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		String outScreen = "[getPromotionInfo(sql="
				+ eHotelSql.sqlGetPromotionInfo + ") with params: itemId="
				+ itemId + "] ";
		log.info(outScreen);

		vImages = DataUtils.LoadPromotions(outParam);
		if (vImages != null && vImages.size() > 0) {
			pro = vImages.get(0);
			outScreen = "--> returnValue(" + pro.toString() + ")";
			log.info(outScreen);
		}
		return pro;
	}

	public int addPromotion(ePromotion promotion) {
		int seqAdd = -1;
		if (promotion == null) {
			log.info("Input param is null.");
			return seqAdd;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(promotion.getName(),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(promotion.getContent(), SubProParam.IN);
		params.add(subPro);// 2

		String urlimage = "";
		if (promotion.getUrlImage() != null)
			urlimage = promotion.getUrlImage();
		subPro = new SubProParam(urlimage, SubProParam.IN);
		params.add(subPro);// 3

		String urlWeb = "";
		if (promotion.getLinkWeb() != null)
			urlWeb = promotion.getLinkWeb();

		subPro = new SubProParam(urlWeb, SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 5

		try {
			params = executeSubPro(eHotelSql.sqlAddPromotion, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addItem (sql=" + eHotelSql.sqlAddPromotion
					+ ") with params: " + promotion.toString()
					+ "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean editPromotion(ePromotion promotion, int langid) {
		boolean isEdit = false;
		if (promotion == null) {
			log.info("Input param is null.");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				promotion.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(promotion.getName(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(promotion.getContent(), SubProParam.IN);
		params.add(subPro);// 3

		String urlImage = "";
		if (promotion.getUrlImage() != null)
			urlImage = promotion.getUrlImage();
		subPro = new SubProParam(urlImage, SubProParam.IN);
		params.add(subPro);// 4

		String urlWeb = "";
		if (promotion.getLinkWeb() != null)
			urlWeb = promotion.getLinkWeb();
		subPro = new SubProParam(urlWeb, SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 6

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 7

		try {
			params = executeSubPro(eHotelSql.sqlEditPromotion, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isEdit = true;
			}
			String outScreen = "[editItem (sql=" + eHotelSql.sqlEditPromotion
					+ ") with params: " + promotion.toString()
					+ "] returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isEdit;
	}

	public boolean removePromotion(String str_itemId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(str_itemId, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlRemovePromotion, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removePromotion(sql="
					+ eHotelSql.sqlRemovePromotion
					+ ") with params: str_itemId=" + str_itemId
					+ "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean changeStatus(int itemId) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlChangeStatusPromotion, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[changeStatus(sql="
					+ eHotelSql.sqlChangeStatusPromotion
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
			params = executeSubPro(eHotelSql.sqlCountPromotions, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countItem(sql=" + eHotelSql.sqlCountPromotions
					+ ") with no params -->returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.fillInStackTrace().toString());
		}
		return count;
	}
}
