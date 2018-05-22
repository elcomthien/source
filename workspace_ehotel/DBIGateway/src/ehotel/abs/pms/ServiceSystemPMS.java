package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.pms.eAPKCode;
import ehotel.domain.pms.eService;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class ServiceSystemPMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(ServiceSystemPMS.class);

	public Vector<eAPKCode> getAPKCodes() {
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eAPKCode> vImages = new Vector<eAPKCode>();
		try {
			params = executeSubPro(eHotelSql.sqlgetAPKCodes, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadApkcodes(outParam);
		String outScreen = "[getAPKCodes(sql=" + eHotelSql.sqlGetFolioList
				+ ") with no params] : returnValue(size=" + vImages.size()
				+ ")";
		log.info(outScreen);
		return vImages;
	}

	public Vector<eService> getAllServices(int langId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eService> vImages = new Vector<eService>();
		try {
			params = executeSubPro(eHotelSql.sqlgetAllServices, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadServices(outParam);
		String outScreen = "[getAllServices(sql=" + eHotelSql.sqlgetAllServices
				+ ") with params: langid=" + langId + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	public eService getServiceInfo(int serviceId, int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(serviceId), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eService> vImages = new Vector<eService>();
		try {
			params = executeSubPro(eHotelSql.sqlgetServiceInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadServices(outParam);
		String outScreen = "[getAllServices(sql=" + eHotelSql.sqlgetServiceInfo
				+ ") with params: serviceId=" + serviceId
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		if (vImages.size() > 0)
			return vImages.get(0);
		else
			return null;
	}

	public String getApkPath(int serviceId) {
		String path = null;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(serviceId), SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqlgetApkPath, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				path = paramOUT.getString();

			}
			String outScreen = "[getApkPath (sql=" + eHotelSql.sqlgetApkPath
					+ ") with params: serviceId=" + serviceId
					+ "]  : returnValue(path=" + path + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return path;
	}

	public boolean updateServiceMain(int serviceid, String name,
			String imagePath, int langid) {
		boolean isRemove = false;
		if (name == null) {
			log.info("Input param[name] is null.");
			return isRemove;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(serviceid), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(name, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(imagePath, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqlupdateServiceMain, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isRemove = true;
			}
			String outScreen = "[updateServiceMain (sql="
					+ eHotelSql.sqlupdateServiceMain + ") with params: name="
					+ name + ",imagePath=" + imagePath + ",serviceid="
					+ serviceid + "]  : returnValue(isAdd=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean changeVisbleService(int serviceId) {
		boolean isChange = false;

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(serviceId), SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlchangeVisbleService, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isChange = true;
			}
			String outScreen = "[changeInvisble (sql="
					+ eHotelSql.sqlchangeVisbleService
					+ ") with params: serviceId=" + serviceId
					+ "]  : returnValue(isAdd=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isChange;
	}

	public boolean setValueApkcode(int serviceId, String value_apkcode) {
		boolean isChange = false;

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(serviceId), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(value_apkcode, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlsetValueApkcode, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isChange = true;
			}
			String outScreen = "[" + eHotelSql.sqlsetValueApkcode
					+ ") with params: serviceId=" + serviceId
					+ "]  : returnValue(isAdd=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isChange;
	}

	/**
	 * 
	 * @param str_service_id
	 *            : format "(service_id1,service_id2,...)
	 * @param str_order
	 *            format "(2,3,1,...)
	 * @return
	 */
	public boolean orderByService(String str_service_id, String str_order) {
		boolean isChange = false;

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(str_service_id, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(str_order, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlorderByService, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isChange = true;
			}
			String outScreen = "[" + eHotelSql.sqlorderByService
					+ ") with params: serviceId=" + str_service_id
					+ ",orderBy=" + str_order + "]  : returnValue(isAdd="
					+ isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isChange;
	}
	
	public static void main(String[] args) {
		ServiceSystemPMS s = new ServiceSystemPMS();
		System.out.println(s.getAllServices(2).size());
		System.out.println(s.getAllServices(2));
	}

}
