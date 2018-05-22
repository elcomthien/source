package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.pms.eExchangeRate;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class ExchangeRatePMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(ExchangeRatePMS.class);

	@SuppressWarnings("unchecked")
	public Vector<eExchangeRate> getExchangeRates(int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(from),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eExchangeRate> vImages = new Vector<eExchangeRate>();
		try {
			params = executeSubPro(eHotelSql.sqlGetExchangeRates, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadExchangeRates(outParam);
		String outScreen = "[getExchangeRates(sql="
				+ eHotelSql.sqlGetExchangeRates
				+ ") with no params] : returnValue(size=" + vImages.size()
				+ ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public eExchangeRate getExchangeRateInfo(int itemId) {
		eExchangeRate pro = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(itemId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eExchangeRate> vImages = new Vector<eExchangeRate>();
		try {
			params = executeSubPro(eHotelSql.sqlGetExchangeRateInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		String outScreen = "[getExchangeRateInfo(sql="
				+ eHotelSql.sqlGetExchangeRateInfo + ") with params: itemId="
				+ itemId + "] ";
		log.info(outScreen);

		vImages = DataUtils.LoadExchangeRates(outParam);
		if (vImages != null && vImages.size() > 0) {
			pro = vImages.get(0);
			outScreen = "--> returnValue(" + pro.toString() + ")";
			log.info(outScreen);
		}
		return pro;
	}

	public int addExchangeRate(eExchangeRate exchange) {
		int seqAdd = -1;
		if (exchange == null) {
			log.info("Input param is null.");
			return seqAdd;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(exchange.getCode(), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(exchange.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String buyRate = "0";
		if (exchange.getBuyRate() != null)
			buyRate = exchange.getBuyRate();
		subPro = new SubProParam(buyRate, SubProParam.IN);
		params.add(subPro);// 3

		String transferRate = "0";
		if (exchange.getTransferRate() != null)
			transferRate = exchange.getTransferRate();
		subPro = new SubProParam(transferRate, SubProParam.IN);
		params.add(subPro);// 4

		String sellRate = "0";
		if (exchange.getSellRate() != null)
			sellRate = exchange.getSellRate();
		subPro = new SubProParam(sellRate, SubProParam.IN);
		params.add(subPro);// 5

		String image = "";
		if (exchange.getUrlImage() != null)
			image = exchange.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 6

		String imageBg = "";
		if (exchange.getUrlBg() != null)
			imageBg = exchange.getUrlBg();
		subPro = new SubProParam(imageBg, SubProParam.IN);
		params.add(subPro);// 7

		String bankingName = "";
		if (exchange.getBankingName() != null)
			bankingName = exchange.getBankingName();
		subPro = new SubProParam(bankingName, SubProParam.IN);
		params.add(subPro);// 8

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 9

		try {
			String outScreen = "[addItem (sql=" + eHotelSql.sqlAddExchangeRate
					+ ") with params: " + exchange.toString() + "] ";
			log.info(outScreen);

			params = executeSubPro(eHotelSql.sqlAddExchangeRate, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(8);
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

	public boolean editExchangeRate(eExchangeRate exchange, int langid) {
		boolean isEdit = false;
		if (exchange == null) {
			log.info("Input param is null.");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				exchange.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(exchange.getCode(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(exchange.getName(), SubProParam.IN);
		params.add(subPro);// 3

		String buyRate = "0";
		if (exchange.getBuyRate() != null)
			buyRate = exchange.getBuyRate();
		subPro = new SubProParam(buyRate, SubProParam.IN);
		params.add(subPro);// 4

		String sellRate = "0";
		if (exchange.getSellRate() != null)
			sellRate = exchange.getSellRate();
		subPro = new SubProParam(sellRate, SubProParam.IN);
		params.add(subPro);// 5

		String transferRate = "0";
		if (exchange.getTransferRate() != null)
			transferRate = exchange.getTransferRate();
		subPro = new SubProParam(transferRate, SubProParam.IN);
		params.add(subPro);// 6

		String image = "";
		if (exchange.getUrlImage() != null)
			image = exchange.getUrlImage();
		subPro = new SubProParam(image, SubProParam.IN);
		params.add(subPro);// 7

		String imageBg = "";
		if (exchange.getUrlBg() != null)
			imageBg = exchange.getUrlBg();
		subPro = new SubProParam(imageBg, SubProParam.IN);
		params.add(subPro);// 8

		String bankingName = "";
		if (exchange.getBankingName() != null)
			bankingName = exchange.getBankingName();
		subPro = new SubProParam(bankingName, SubProParam.IN);
		params.add(subPro);// 9

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 10
		try {
			String outScreen = "[addItem (sql=" + eHotelSql.sqlEditExchangeRate
					+ ") with params: " + exchange.toString() + "] ";
			log.info(outScreen);

			params = executeSubPro(eHotelSql.sqlEditExchangeRate, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(9);
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

	public boolean removeExchangeRate(String str_itemId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(str_itemId, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlRemoveExchange, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removePromotion(sql="
					+ eHotelSql.sqlRemoveExchange
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
			params = executeSubPro(eHotelSql.sqlChangeStatusExchange, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[changeStatus(sql="
					+ eHotelSql.sqlChangeStatusExchange
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
			params = executeSubPro(eHotelSql.sqlCountExchangeRates, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[countItem(sql="
					+ eHotelSql.sqlCountExchangeRates
					+ ") with no params -->returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}
}
