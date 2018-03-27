package elcom.com.core.dao;

import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.Log.FileEvent;

import elcom.com.core.common.CMDCommon;
import elcom.com.core.common.ServiceCharge;
import elcom.com.core.write.PMSCoreWrite;
import elcom.com.util.UtilLoad;
import elcom.com.util.Utils;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class InterfaceDao extends BaseDAO {
	String sql = "";

	public InterfaceDao() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteInfoRequest(int folioNum, int guestId, String command) {

		Vector params = new Vector();
		SubProParam subFolioNum = new SubProParam(new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subFolioNum);

		SubProParam subGuestId = new SubProParam(new java.math.BigDecimal(guestId), SubProParam.IN);
		params.add(subGuestId);
		SubProParam subCommand = new SubProParam(command, SubProParam.IN);
		params.add(subCommand);
		String sqlQuery = "{call PMS_INTERFACE.deleteInfoRequest(?,?,?)}";

		try {
			dbiService.executeSubPro(sqlQuery, params);
			System.out.println("InterfaceDao.deleteInfoRequest:sqlQuery=" + sqlQuery);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ServiceCharge getServiceCharge(int transationID) {
		// System.out.println("InterfaceDao.sendCmdAlertChargeToPms:sql");
		ServiceCharge charge = null;
		Vector params = new Vector();
		SubProParam subTranID = new SubProParam(new java.math.BigDecimal(transationID), SubProParam.IN);
		SubProParam subOut = new SubProParam(new Vector(), "STRING_ARR", SubProParam.OUT);
		params.add(subTranID);
		params.add(subOut);
		String sqlQuery = "{call PMS_INTERFACE.getVodCharge(?,?)}";
		Vector result = null;
		try {
			result = dbiService.executeSubPro(sqlQuery, params);
			System.out.println("InterfaceDao.sendCmdAlertChargeToPms:sql=" + sqlQuery);
			if (result != null & result.size() > 0) {
				SubProParam out_data = (SubProParam) result.get(1);
				result = out_data.getVector();
			}
			charge = UtilLoad.LoadServiceCharge(result);
		} catch (Exception ex) {
		}

		return charge;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CMDCommon getInfoReadMessage(String command, int objId) {
		CMDCommon common = null;
		Vector params = new Vector();
		SubProParam subObjID = new SubProParam(new java.math.BigDecimal(objId), SubProParam.IN);
		SubProParam subOut = new SubProParam(new Vector(), "STRING_ARR", SubProParam.OUT);
		params.add(subObjID);
		params.add(subOut);
		String sqlQuery = "{call PMS_INTERFACE.getInfoReadMessage(?,?)}";
		Vector result = null;
		try {
			result = dbiService.executeSubPro(sqlQuery, params);
			System.out.println("PMSCoreWrite.sendCmdCommonToPms:sql=" + sqlQuery);
			if (result != null & result.size() > 0) {
				SubProParam out_data = (SubProParam) result.get(1);
				result = out_data.getVector();
			}
			common = UtilLoad.LoadCMDCommon(result);

		} catch (Exception ex) {
		}

		return common;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getGuestOfFolioNum(int folioNum) {
		Vector params = new Vector();
		SubProParam subFolioNum = new SubProParam(new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subFolioNum);

		SubProParam subOut = new SubProParam(new Vector(), "STRING_ARR", SubProParam.OUT);
		params.add(subOut);
		String sqlQuery = "{call PMS_APP.getGuestListOfRoom(?,?)}";
		Vector result = null;
		try {
			result = dbiService.executeSubPro(sqlQuery, params);
			System.out.println("InterfaceDao.sendCmdAlertChargeToPms:sql=" + sqlQuery);
			if (result != null & result.size() > 0) {
				SubProParam out_data = (SubProParam) result.get(1);
				result = out_data.getVector();
			}
			result = UtilLoad.LoadGuests(result);
		} catch (Exception ex) {
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void LoadInfoRequest(FileEvent log, String command) {// FileEvent log = new FileEvent("eIPTVPMSServer");
		Vector params = new Vector();
		SubProParam subCommand = new SubProParam(command, SubProParam.IN);
		params.add(subCommand);
		SubProParam subOut = new SubProParam(new Vector(), "STRING_ARR", SubProParam.OUT);
		params.add(subOut);
		String sqlQuery = "{call PMS_INTERFACE.getRequestList(?,?)}";
		Utils.outScreen(log, "InterfaceDao.LoadInfoRequest: SQL=" + sqlQuery, false);

		try {
			params = dbiService.executeSubPro(sqlQuery, params);
			if (params != null & params.size() > 0) {
				SubProParam out_data = (SubProParam) params.get(1);
				params = out_data.getVector();
			}
			ProcessInfoRequest(log, command, params);
		} catch (Exception ex) {
			ex.printStackTrace();
			Utils.outScreen(log, ex.getMessage(), false);
		}

	}

	/**
	 * LoadInfoRequest
	 * 
	 * @param params
	 *            Vector
	 * @return Vector
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	private void ProcessInfoRequest(FileEvent log, String command, Vector vTmp) {
		if (vTmp != null && vTmp.size() > 0) {
			// System.out.println("huhu");
			for (int i = 0; i < vTmp.size(); i = i + 2) {
				int folioCode = Integer.parseInt((String) vTmp.get(i));
				int guestId = Integer.parseInt((String) vTmp.get(i + 1));
				PMSCoreWrite core = new PMSCoreWrite();
				String cmd = core.sendCmdRequestToPms(command, folioCode, guestId);
				System.out.println("ProcessInfoRequest=" + cmd);
				Utils.outScreen(log, "ProcessInfoRequest=" + cmd, false);
			}
		}
	}

	public static void main(String[] arg) {

	}
}
