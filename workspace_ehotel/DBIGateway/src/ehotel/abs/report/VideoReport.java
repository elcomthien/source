package ehotel.abs.report;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.dao.eBaseDao;
import ehotel.domain.report.DataReport;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;

public class VideoReport extends eBaseDao {
	private static Logger log = Logger.getLogger(VideoReport.class);

	@SuppressWarnings("unchecked")
	public Vector<DataReport> getUsedFrequency_monthly(int year) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(year),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<DataReport> vImages = new Vector<DataReport>();
		try {
			params = executeSubPro(eHotelSql.sqlgetUsedFrequency_monthly,
					params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadReport(outParam);
		String outScreen = "[sql=" + eHotelSql.sqlgetUsedFrequency_monthly
				+ ") with params: year=" + year + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	/**
	 * Lay tan suat su dung dich vu theo ten phim
	 * 
	 * @param frDate
	 *            : format = dd/mm/yyyy
	 * @param toDate
	 *            :format = dd/mm/yyyy
	 * @param langid
	 * @param frRow
	 * @param toRow
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<DataReport> getUsedFrequency_name(String frDate,
			String toDate, int langid, int frRow, int toRow) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(frDate, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(toDate, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(frRow),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(toRow),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<DataReport> vImages = new Vector<DataReport>();
		try {
			params = executeSubPro(eHotelSql.sqlgetUsedFrequency_name, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadDataReport(outParam);
		String outScreen = "[sql=" + eHotelSql.sqlgetUsedFrequency_name
				+ ") with params: frDate=" + frDate + ",toDate=" + toDate
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public Vector<DataReport> getUsedFrequency_gener(String frDate,
			String toDate, int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(frDate, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(toDate, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<DataReport> vImages = new Vector<DataReport>();
		try {
			params = executeSubPro(eHotelSql.sqlgetUsedFrequency_gener, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadReport(outParam);
		String outScreen = "[sql=" + eHotelSql.sqlgetUsedFrequency_gener
				+ ") with params: frDate=" + frDate + ",toDate=" + toDate
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

}
