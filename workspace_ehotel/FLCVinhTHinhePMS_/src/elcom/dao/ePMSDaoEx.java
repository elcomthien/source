package elcom.dao;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import elcom.config.PMSConstant;
import elcom.domain.CMNShopbill;
import elcom.domain.Logo;
import elcom.domain.OrderRes;
import elcom.domain.ServiceCommon;
import elcom.domain.VersionData;
import elcom.domain.eContainer;
import elcom.domain.eMapLocation;
import elcom.domain.ex.eAcitivity;
import elcom.domain.ex.eAttraction;
import elcom.domain.ex.eHouseKeeping;
import elcom.domain.ex.eObject;
import elcom.utils.Utils;

public class ePMSDaoEx extends eBaseDao {
	public ePMSDaoEx() {
		super();
	}

	private static Logger log = Logger.getLogger(ePMSDaoEx.class);

	public static Logo getLogo(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Logo logo = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		String urlimage = null;
		SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn); // 1
		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);// 2

		String outScreen = ("[getLogo with params [smartcard=" + smartcard + "]");

		try {
			params = executeSubPro(ePMSSqlEx.sqlGetLogo, params);
			log.info(outScreen);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}

		Vector<Logo> vAttraction = DataUtils.LoadLogo(outParam);
		outScreen = outScreen + "--> returnValue(size=" + vAttraction.size()
				+ ")";
		log.info(outScreen);
		if (vAttraction != null && vAttraction.size() > 0)
			logo = vAttraction.get(0);
		return logo;
	}

	@SuppressWarnings("unchecked")
	public static eAttraction getLocalAttractionInfo(int attractionId,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		eAttraction attraction = null;
		if (smartcard == null)
			smartcard = "NOT";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam Sub = new SubProParam(
				new java.math.BigDecimal(attractionId), SubProParam.IN);
		params.add(Sub);

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSqlEx.sqlGetLocalAttractionInfo, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		List<eAttraction> vAttraction = DataUtils.LoadAttraction(outParam);
		String outScreen = "getLocalAttractionInfo with params:smartcard="
				+ smartcard + ",attractionId=" + attractionId
				+ "] : returnValue(size=" + vAttraction.size() + ")";
		log.info(outScreen);
		if (vAttraction != null && vAttraction.size() > 0)
			attraction = vAttraction.get(0);
		return attraction;
	}

	@SuppressWarnings("unchecked")
	public static List<eAttraction> getItemOfAttractions(int menuId,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";

		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam Sub = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(Sub);

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSqlEx.sqlGetItemOfAttractions, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		List<eAttraction> vAttraction = DataUtils.LoadAttraction(outParam);
		String outScreen = "getItemOfAttractions with params:smartcard="
				+ smartcard + ",menuId=" + menuId + "] : returnValue(size="
				+ vAttraction.size() + ")";
		log.info(outScreen);
		return vAttraction;
	}

	@SuppressWarnings("unchecked")
	public static Vector<eHouseKeeping> getItemOfHousekeeping(int menuId,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam Sub = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(Sub);

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSqlEx.sqlGetItemOfHousekeeping, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		Vector<eHouseKeeping> vHousekeeping = DataUtils
				.LoadHouseKeeping(outParam);
		String outScreen = "getItemOfHousekeeping with params:smartcard="
				+ smartcard + ",menuId=" + menuId + "] : returnValue(size="
				+ vHousekeeping.size() + ")";
		log.info(outScreen);
		return vHousekeeping;
	}

	public static OrderRes requestHouseKeeping(String smartcard,
			String pinCode, int menuId, String orderTime, String orderDate,
			String guestName, int HKType) {

		OrderRes result = null;
		int isOrder = -1;
		int checkNo = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);// 1

		subIn = new SubProParam(pinCode, SubProParam.IN);
		params.add(subIn);// 2

		subIn = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);// 3
		params.add(subIn);

		subIn = new SubProParam(orderDate, SubProParam.IN);
		params.add(subIn);// 4

		subIn = new SubProParam(orderTime, SubProParam.IN);
		params.add(subIn);// 5

		subIn = new SubProParam(guestName, SubProParam.IN);
		params.add(subIn);// 6

		subIn = new SubProParam(new java.math.BigDecimal(HKType),
				SubProParam.IN);// 7
		params.add(subIn);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut); // 8

		subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut); // 9

		String outScreen = ("[requestHouseKeeping with params [smartcard="
				+ smartcard + ",pinCode=" + pinCode + ",ordertime=" + orderTime
				+ ",orderDate=" + orderDate + "]");

		try {
			params = executeSubPro(ePMSSqlEx.sqlPostedOneHKService, params);
			log.info(outScreen);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				String tmp = (paramOUT.getString()).trim();
				checkNo = Utils.parseInt(tmp);

				paramOUT = (SubProParam) params.get(8);
				isOrder = Utils.parseInt((paramOUT.getString()).trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		result = new OrderRes();
		result.setCheckNo(checkNo);
		result.setAlertFlag(isOrder);
		if (result != null)
			log.info("--->requestHouseKeeping is completed. Result[checkNo="
					+ result.getCheckNo() + "isAlert=" + result.getAlertFlag()
					+ "]");
		return result;
	}

	public static OrderRes requestItemHouseKeeping(
			Vector<CMNShopbill> itemOrders, String smartcard, String pinCode,
			String orderTime, String orderDate, String guestName) {
		int checkNo = -1;
		int isOrder = 1;// alert is opened

		OrderRes result = null;
		if (itemOrders == null || itemOrders.size() == 0) {
			System.out.println("Cart is empty!");
			result = new OrderRes();
			result.setCheckNo(checkNo);
			result.setAlertFlag(isOrder);
			return result;
		}
		log.info("Begin process about requestItemHouseKeeping");
		try {
			for (CMNShopbill aitem : itemOrders) {
				if (aitem.getCheckNo() > 0) {// item trong bill dang ton tai
					checkNo = aitem.getCheckNo();
					System.out.println("CheckNo is exist!");
				} else {
					// item trong bill moi
					checkNo = getCheckNo(PMSConstant.HOUSEKEEPING);
					System.out.println("Item New checkNo" + checkNo);
				}
				if (aitem.getICode() > 0 && aitem.getIQty() > 0) {
					isOrder = postedOneItemHouseKeeping(smartcard, pinCode,
							aitem.getICode(), aitem.getIQty(),
							aitem.getMenuID(), orderTime, orderDate, guestName,
							checkNo);
				}
			}
			result = new OrderRes();
			result.setCheckNo(checkNo);
			result.setAlertFlag(isOrder);
		} catch (Exception ex) {
			result = new OrderRes();
			result.setCheckNo(checkNo);
			result.setAlertFlag(isOrder);
			log.info(ex.getMessage());
		}
		if (result != null)
			log.info("--->requestItemHouseKeeping is completed. Result[checkNo="
					+ result.getCheckNo()
					+ "isAlert="
					+ result.getAlertFlag()
					+ "]");
		return result;
	}

	public static int postedOneItemHouseKeeping(String smartcard,
			String pinCode, int icode, int IQty, int menuId, String orderTime,
			String orderDate, String guestName, int checkNo) {
		int ret = 0;

		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn); // 1

		SubProParam subIn01 = new SubProParam(pinCode, SubProParam.IN);
		params.add(subIn01); // 2

		SubProParam subIn02 = new SubProParam(new java.math.BigDecimal(icode),
				SubProParam.IN);
		params.add(subIn02); // 3

		SubProParam subIn03 = new SubProParam(new java.math.BigDecimal(IQty),
				SubProParam.IN);
		params.add(subIn03); // 4

		SubProParam subIn04 = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subIn04); // 5

		String timeFull = Utils.convertDateToFullString(new Date());
		if (orderTime == null)
			orderTime = timeFull.substring(11, timeFull.length());
		SubProParam subIn05 = new SubProParam(orderTime, SubProParam.IN);
		params.add(subIn05); // 6

		if (orderDate == null)
			orderDate = timeFull.substring(0, 10);
		SubProParam subIn06 = new SubProParam(orderDate, SubProParam.IN);
		params.add(subIn06); // 7

		SubProParam subIn07 = new SubProParam(guestName, SubProParam.IN);
		params.add(subIn07); // 8

		SubProParam subIn08 = new SubProParam(
				new java.math.BigDecimal(checkNo), SubProParam.IN);
		params.add(subIn08); // 9

		SubProParam subISeq = new SubProParam(new String(), SubProParam.OUT);
		params.add(subISeq); // 10

		String outScreen = ("[postedOnetHouseKeeping with params [smartcard="
				+ smartcard + ",pinCode=" + pinCode + ",icode=" + icode
				+ "IQty=" + IQty + ",menuId=" + menuId + ", ordertime="
				+ orderTime + ",orderdate=" + orderDate + ",guestName="
				+ guestName + "checkNo=" + checkNo + "]");

		try {
			params = executeSubPro(ePMSSqlEx.sqlPostedOnetHouseKeeping, params);
			log.info(outScreen);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				String tmp = (paramOUT.getString()).trim();

				ret = Utils.parseInt(tmp);
				log.info("Request ICode=" + icode + " is complete!(Alert="
						+ tmp + ")");
			}
			log.info("--->postedOnetHouseKeeping is complete!");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			log.info("--->postedOnetHouseKeeping is not complete!");
		}

		return ret;

	}

	@SuppressWarnings("unchecked")
	public static Vector<eObject> getTaxiList(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);// 1

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		Vector<eObject> vServiceCommon = new Vector<eObject>();
		try {
			params = executeSubPro(ePMSSqlEx.sqlTaxiList, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceCommon = DataUtils.LoadObject(outParam);
		String outScreen = "[getHomeService with no params: ] : returnValue(size="
				+ vServiceCommon.size() + ")";
		log.info(outScreen);
		return vServiceCommon;
	}

	@SuppressWarnings("unchecked")
	public static Vector<ServiceCommon> getMenuOfAirlineTransport(
			String smartcard, int menuId) {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);// 1

		subIn = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subIn);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		Vector<ServiceCommon> vServiceCommon = new Vector<ServiceCommon>();
		try {
			params = executeSubPro(ePMSSqlEx.sqlGetMenuOfAirlineTransport,
					params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceCommon = DataUtils.LoadArilneMenu(outParam);
		String outScreen = "[getMenuOfAirlineTransport with params: smartcard="
				+ smartcard + ",menuId=" + menuId + "] : returnValue(size="
				+ vServiceCommon.size() + ")";
		log.info(outScreen);
		return vServiceCommon;
	}

	public static OrderRes requestTaxi(String smartcard, String pincode,
			int transportId, int qty, int seatNum, String orderTime,
			String orderdate, String phone, String whereTo, String guestName) {
		OrderRes result = null;
		int isOrder = -1;
		int checkNo = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);// 1

		subIn = new SubProParam(pincode, SubProParam.IN);
		params.add(subIn);// 2

		subIn = new SubProParam(new java.math.BigDecimal(transportId),
				SubProParam.IN);
		params.add(subIn);// 3

		subIn = new SubProParam(new java.math.BigDecimal(qty), SubProParam.IN);// 3
		params.add(subIn);// 4

		subIn = new SubProParam(new java.math.BigDecimal(seatNum),
				SubProParam.IN);
		params.add(subIn);// 5

		String timeFull = Utils.convertDateToFullString(new Date());
		if (orderTime == null)
			orderTime = timeFull.substring(11, timeFull.length());
		subIn = new SubProParam(orderTime, SubProParam.IN);
		params.add(subIn);// 6

		if (orderdate == null)
			orderdate = timeFull.substring(0, 10);
		subIn = new SubProParam(orderdate, SubProParam.IN);
		params.add(subIn);// 7

		subIn = new SubProParam(phone, SubProParam.IN);
		params.add(subIn);// 8

		subIn = new SubProParam(whereTo, SubProParam.IN);// 7
		params.add(subIn);// 9

		subIn = new SubProParam(guestName, SubProParam.IN);// 7
		params.add(subIn);// 10

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut); // 11

		subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut); // 12

		// String outScreen = ("[requestTaxi with params [smartcard=" +
		// smartcard
		// + ",pinCode=" + pincode + ",transportId=" + transportId
		// + ",IQty=" + qty + ",seatNum=" + seatNum + ",ordertime="
		// + orderTime + ",orderDate=" + orderdate + ",guestName="
		// + guestName + "]");

		try {
			params = executeSubPro(ePMSSqlEx.sqlRequestTaxi, params);

			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(10);
				String tmp = (paramOUT.getString()).trim();
				checkNo = Utils.parseInt(tmp);

				paramOUT = (SubProParam) params.get(11);
				isOrder = Utils.parseInt((paramOUT.getString()).trim());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		result = new OrderRes();
		result.setCheckNo(checkNo);
		result.setAlertFlag(isOrder);
		if (result != null)
			log.info("--->requestTaxi is completed. Result[checkNo="
					+ result.getCheckNo() + "isAlert=" + result.getAlertFlag()
					+ "]");
		return result;
	}

	public static OrderRes requestTransportation(String smartcard,
			String pincode, int menuId, String orderTime, String orderdate,
			String guestName) {
		OrderRes result = null;
		int isOrder = -1;
		int checkNo = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);// 1

		subIn = new SubProParam(pincode, SubProParam.IN);
		params.add(subIn);// 2

		subIn = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subIn);// 3

		String timeFull = Utils.convertDateToFullString(new Date());
		if (orderTime == null)
			orderTime = timeFull.substring(11, timeFull.length());
		subIn = new SubProParam(orderTime, SubProParam.IN);
		params.add(subIn);// 4

		if (orderdate == null)
			orderdate = timeFull.substring(0, 10);
		subIn = new SubProParam(orderdate, SubProParam.IN);
		params.add(subIn);// 5

		if (guestName == null)
			guestName = "";
		subIn = new SubProParam(guestName, SubProParam.IN);
		params.add(subIn);// 6

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut); // 7

		subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut); // 8

		// String outScreen = ("[requestTransportation with params [smartcard="
		// + smartcard + ",pinCode=" + pincode + ",menuId=" + menuId
		// + ",ordertime=" + orderTime + ",orderDate=" + orderdate + "]");
		// log.info(outScreen);

		try {
			params = executeSubPro(ePMSSqlEx.sqlRequestTransportation, params);

			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				String tmp = (paramOUT.getString()).trim();
				checkNo = Utils.parseInt(tmp);

				paramOUT = (SubProParam) params.get(7);
				isOrder = Utils.parseInt((paramOUT.getString()).trim());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		result = new OrderRes();
		result.setCheckNo(checkNo);
		result.setAlertFlag(isOrder);
		if (result != null)
			log.info("--->requestHouseKeeping is completed. Result[checkNo="
					+ result.getCheckNo() + "isAlert=" + result.getAlertFlag()
					+ "]");
		return result;
	}

	@SuppressWarnings("unchecked")
	public static List<eAcitivity> getItemOfScheduleActivity(int menuId,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIn = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subIn);

		subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);// 1

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		Vector<eAcitivity> vServiceCommon = new Vector<eAcitivity>();
		try {
			params = executeSubPro(ePMSSqlEx.sqlGetItemOfScheduleActivity,
					params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceCommon = DataUtils.LoadActivity(outParam);
		String outScreen = "[getHomeService with no params: ] : returnValue(size="
				+ vServiceCommon.size() + ")";
		log.info(outScreen);
		return vServiceCommon;
	}

	@SuppressWarnings("unchecked")
	public static Vector<eMapLocation> getMapLocations(String smartcard,
			int menuId) {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam Sub = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(Sub);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSqlEx.sqlGetLocations, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		Vector<eMapLocation> vMapLocation = DataUtils.LoadMapLocation(outParam);
		String outScreen = "getLocations with params:smartcard=" + smartcard
				+ ",menuId=" + menuId + "] : returnValue(size="
				+ vMapLocation.size() + ")";
		log.info(outScreen);
		return vMapLocation;
	}

	public static boolean checkCoordinatesChange(String smartcard) {
		boolean isCheck = false;
		if (smartcard == null)
			return isCheck;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut); // 2

		try {
			params = executeSubPro(ePMSSqlEx.sqlCheckCoordinatesChange, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				int tmp = Utils.parseInt((subOut.getString()).trim());
				if (tmp > 0)
					isCheck = true;

			}
			String outScreen = "[---> checkCoordinatesChange  with params: [smartcard="
					+ smartcard + "] : returnValue(isCheck=" + isCheck + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isCheck;
	}

	@SuppressWarnings("unchecked")
	public static Vector<eMapLocation> getMapCoordinatesChange(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSqlEx.sqlGetMapLocationsChange, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		Vector<eMapLocation> vMapLocation = DataUtils.LoadMapLocation(outParam);
		String outScreen = "getLocations with params:smartcard=" + smartcard
				+ "] : returnValue(size=" + vMapLocation.size() + ")";
		log.info(outScreen);
		return vMapLocation;
	}

	public static boolean setLocationSynStatus(String str_locationId) {
		boolean isSet = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam Sub = new SubProParam(str_locationId, SubProParam.IN);
		params.add(Sub);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut); // 2
		try {
			params = executeSubPro(ePMSSqlEx.sqlsetLocationSynStatus, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				int tmp = Utils.parseInt((subOut.getString()).trim());
				if (tmp == 1)
					isSet = true;

			}
			String outScreen = "[---> setLocationSynStatus  with params: [str_locationId="
					+ str_locationId + "] : returnValue(isSet=" + isSet + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isSet;
	}

	// game
	public static Vector<eContainer> getGames(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSqlEx.sqlgetGames, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		Vector<eContainer> vMapLocation = DataUtils.LoadGames(outParam);
		String outScreen = "getLocations with params:smartcard=" + smartcard
				+ "] : returnValue(size=" + vMapLocation.size() + ")";
		log.info(outScreen);
		return vMapLocation;
	}

	public static Vector<eContainer> getInternets() {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSqlEx.sqlgetInternets, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		Vector<eContainer> vitems = DataUtils.LoadGames(outParam);
		String outScreen = "getLocations with no params"
				+ "] : returnValue(size=" + vitems.size() + ")";
		log.info(outScreen);
		return vitems;
	}

	@SuppressWarnings("unchecked")
	public static Vector<VersionData> getVersions() {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<VersionData> vVersions = new Vector<VersionData>();
		try {
			params = executeSubPro(ePMSSqlEx.sqlgetVersion, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
			vVersions = DataUtils.LoadVersions(outParam);
			String outScreen = "[--> getVersions  with params: returnValue(size ="
					+ vVersions.size() + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return vVersions;
	}
}
