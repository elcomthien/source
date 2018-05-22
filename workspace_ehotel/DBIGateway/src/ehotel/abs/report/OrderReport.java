package ehotel.abs.report;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.dao.eBaseDao;
import ehotel.domain.report.DataReport;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;

public class OrderReport extends eBaseDao {
	private static Logger log = Logger.getLogger(OrderReport.class);

	@SuppressWarnings("unchecked")
	public Vector<DataReport> getRoomserviceRpt(String frDate, String toDate,
			int langid) {
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
			params = executeSubPro(eHotelSql.sqlgetRoomserviceRpt, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadDataReport(outParam);
		String outScreen = "[sql=" + eHotelSql.sqlgetRoomserviceRpt
				+ ") with params: frDate=" + frDate + ",toDate=" + toDate
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public Vector<DataReport> getTransportationRpt(String frDate,
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
			params = executeSubPro(eHotelSql.sqlgetTransportationRpt, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadReport(outParam);
		String outScreen = "[sql=" + eHotelSql.sqlgetTransportationRpt
				+ ") with params: frDate=" + frDate + ",toDate=" + toDate
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}
}
