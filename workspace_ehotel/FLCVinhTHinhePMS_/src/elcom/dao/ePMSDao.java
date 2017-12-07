package elcom.dao;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import elcom.config.PMSConstant;
import elcom.domain.CMNShopbill;
import elcom.domain.Guest;
import elcom.domain.OrderRes;
import elcom.domain.OutletImage;
import elcom.domain.OutletMovie;
import elcom.domain.Promotion;
import elcom.domain.ServiceCharge;
import elcom.domain.ServiceCommon;
import elcom.domain.ServiceItem;
import elcom.domain.eCountry;
import elcom.domain.eCurrency;
import elcom.domain.eMessage;
import elcom.domain.eReservation;
import elcom.domain.eWakeup;
import elcom.domain.eWakeupForms;
import elcom.domain.eWeather;
import elcom.domain.ex.eObject;
import elcom.utils.Utils;



public class ePMSDao extends eBaseDao {
	private static Logger log = Logger.getLogger(ePMSDao.class);

	public static boolean checkPinCode(String smartcard, String pinCode) {
		boolean isCheck = false;
		if (smartcard == null)
			return false;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(smartcard, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(pinCode, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(ePMSSql.sqlCheckPinCode, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				int tmp = Utils.parseInt((subOut.getString()).trim());
				System.out.println("tmp=" + tmp);
				if (tmp == 1)
					isCheck = true;

			}
			String outScreen = "[---> checkPinCode  with params: [smartcard="
					+ smartcard + ",code=" + pinCode
					+ "] : returnValue(isCheck=" + isCheck + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isCheck;
	}

	public static String getFolioNum(String smartcard) {
		if (smartcard == null)
			smartcard = "NOT";

		Vector<SubProParam> params = new Vector<SubProParam>();
		String ret = "-1";
		SubProParam subPro = new SubProParam(smartcard, SubProParam.IN);
		params.add(subPro);

		SubProParam subPro1 = new SubProParam(new String(), SubProParam.OUT);
		params.add(subPro1);

		try {
			params = executeSubPro(ePMSSql.sqlGetFoliNum, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				String tmp = (paramOUT.getString()).trim();
				ret = tmp;
			}
			String outScreen = "[---> getFolioNum  with params: [smartcard="
					+ smartcard + "] : returnValue(Folio=" + ret + "]";
			// Utils.outScreen(log, outScreen, false);
			log.info(outScreen);
		} catch (Exception ex) {
			// Utils.outScreen(log, ex.getMessage(), false);
			log.error(ex.getMessage());
		}
		return ret;

	}

	public static String getFolioLanguage(String smartcard) {
		if (smartcard == null)
			smartcard = "NOT";

		Vector<SubProParam> params = new Vector<SubProParam>();
		String ret = "2";
		SubProParam subPro = new SubProParam(smartcard, SubProParam.IN);
		params.add(subPro);

		SubProParam subPro1 = new SubProParam(new String(), SubProParam.OUT);
		params.add(subPro1);

		String outScreen = "[getFolioLanguage with params: smartcard="
				+ smartcard + "]";
		try {
			params = executeSubPro(ePMSSql.sqlGetFolioLanguage, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				String tmp = (paramOUT.getString()).trim();
				ret = tmp;
			}
			outScreen = outScreen + " : returnValue(FolioLan=" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public static Guest getWelcomeMessage(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";

		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIDic = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIDic);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		try {
			params = executeSubPro(ePMSSql.sqlGetWelcomeMessage, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		Guest guest = DataUtils.LoadGuestInfo(outParam);
		String outScreen = "getWelcomeMessage with params:[smartcard="
				+ smartcard + "] ";
		log.info(outScreen);
		return guest;
	}

	@SuppressWarnings("unchecked")
	public static Vector<Guest> getGuestsInRoom(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSql.sqlGetGuestsInRoom, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		Vector<Guest> vGuest = DataUtils.LoadGuests(outParam);
		String outScreen = "getGuestsInRoom with params:smartcard=" + smartcard
				+ "] : returnValue(size=" + vGuest.size() + ")";
		log.info(outScreen);
		return vGuest;
	}

	@SuppressWarnings("unchecked")
	public static Vector<Promotion> getPromotions(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		Vector<Promotion> vPromotion = new Vector<Promotion>();
		try {
			params = executeSubPro(ePMSSql.sqlGetPromotions, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vPromotion = DataUtils.LoadPromotion(outParam);
		String outScreen = "getPromotions with params[smartcard=" + smartcard
				+ "] : returnValue(size=" + vPromotion.size() + ")";
		log.info(outScreen);
		return vPromotion;

	}

	public static List<OutletImage> getImagesOfWelcomePage(String smartcard) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Vector<ServiceCommon> getHomeService(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";

		Vector<ServiceCommon> vServiceCommon = new Vector<ServiceCommon>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		try {
			params = executeSubPro(ePMSSql.sqlGetHomeService, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceCommon = DataUtils.LoadHomeService(outParam);
		String outScreen = "[getHomeService with params: smartcard="
				+ smartcard + "] : returnValue(size=" + vServiceCommon.size()
				+ ")";
		log.info(outScreen);
		return vServiceCommon;
	}

	//-------------------------------------------------------------------------------------
	public static Vector<ServiceCommon> getService(String smartcard,String serviceid) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";

		Vector<ServiceCommon> vServiceCommon = new Vector<ServiceCommon>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);
		SubProParam subserviceid = new SubProParam(serviceid, SubProParam.IN);
		params.add(subserviceid);
		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		try {
			params = executeSubPro(ePMSSql.sqlGetService, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceCommon = DataUtils.LoadHomeService(outParam);
		String outScreen = "[getService with params: smartcard="
				+ smartcard + " - serviceid" + subserviceid + "] : returnValue(size=" + vServiceCommon.size()
				+ ")";
		log.info(outScreen);
		return vServiceCommon;
	}
	//-------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static Vector<ServiceCommon> getMainMenu(int homeServiceId,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";
		Vector<ServiceCommon> vServiceCommon = new Vector<ServiceCommon>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIDic = new SubProParam(new java.math.BigDecimal(
				homeServiceId), SubProParam.IN);
		params.add(subIDic);

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		try {
			params = executeSubPro(ePMSSql.sqlGetMainMenu, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceCommon = DataUtils.LoadServiceMenu(outParam);
		String outScreen = "[getMainMenu with params:smartcard=" + smartcard
				+ ", homeServiceId=" + homeServiceId + "] : returnValue(size="
				+ vServiceCommon.size() + ")";
		log.info(outScreen);
		return vServiceCommon;
	}

	@SuppressWarnings("unchecked")
	public static Vector<ServiceCommon> getSubMenu(int mainMenuId,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";
		Vector<ServiceCommon> vServiceCommon = new Vector<ServiceCommon>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIDic = new SubProParam(new java.math.BigDecimal(
				mainMenuId), SubProParam.IN);
		params.add(subIDic);

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		try {
			params = executeSubPro(ePMSSql.sqlGetSubMenu, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceCommon = DataUtils.LoadServiceMenu(outParam);
		String outScreen = "[getSubMenu with params: mainMenuId=" + mainMenuId
				+ ",smartcard=" + smartcard + "] : returnValue(size="
				+ vServiceCommon.size() + ")";
		log.info(outScreen);
		return vServiceCommon;
	}

	@SuppressWarnings("unchecked")
	public static Vector<OutletImage> getOutletImage(int subMenuId,
			String smartcard, String type) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null)
			smartcard = "NOT";

		Vector<OutletImage> vServiceItem = new Vector<OutletImage>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIMenu = new SubProParam(new java.math.BigDecimal(
				subMenuId), SubProParam.IN);
		params.add(subIMenu);

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam subIType = new SubProParam(type, SubProParam.IN);
		params.add(subIType);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getOutletImage with params:smartcard=" + smartcard
				+ ", subMenuId=" + subMenuId + "]";
		try {
			params = executeSubPro(ePMSSql.sqlGetItemOfHotel, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(3);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceItem = DataUtils.LoadHotelInfo(outParam);
		outScreen = outScreen + " : returnValue(size=" + vServiceItem.size()
				+ ")";
		log.info(outScreen);
		return vServiceItem;

	}

	/**
	 * lay danh sach item ve image cua hotel(room,restaurant,spa..).PHAN HOTEl
	 * 
	 * @param subMenuId
	 * @param langid
	 * @param from
	 * @param tto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Vector<ServiceItem> getItemOfService(int subMenuId,
			String smartcard, int from, int tto) {
		Vector<String> outParam = new Vector<String>();
		Vector<ServiceItem> vServiceItem = new Vector<ServiceItem>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIMenu = new SubProParam(new java.math.BigDecimal(
				subMenuId), SubProParam.IN);
		params.add(subIMenu);

		subIMenu = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIMenu);

		SubProParam subIFrom = new SubProParam(new java.math.BigDecimal(from),
				SubProParam.IN);
		params.add(subIFrom);

		SubProParam subITo = new SubProParam(new java.math.BigDecimal(tto),
				SubProParam.IN);
		params.add(subITo);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getItemOfService with params: smartcard="
				+ smartcard + ",subMenuId=" + subMenuId + "]";
		try {
			params = executeSubPro(ePMSSql.sqlGetItemOfService, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(4);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceItem = DataUtils.LoadServiceItem(outParam);
		outScreen = outScreen + " : returnValue(size=" + vServiceItem.size()
				+ ")";
		log.info(outScreen);
		return vServiceItem;

	}

	@SuppressWarnings("unchecked")
	public static Vector<ServiceCharge> getItemByCheckNo(int checkNo,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<ServiceCharge> vServiceItem = new Vector<ServiceCharge>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subIn = new SubProParam(new java.math.BigDecimal(checkNo),
				SubProParam.IN);
		params.add(subIn);

		subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getItemByCheckNo with params:smartcard="
				+ smartcard + ", checkNo=" + checkNo + "]";
		try {
			params = executeSubPro(ePMSSql.sqlGetItemByCheckNo, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServiceItem = DataUtils.LoadBills(outParam);
		outScreen = outScreen + " : returnValue(size=" + vServiceItem.size()
				+ ")";
		log.info(outScreen);
		return vServiceItem;
	}

	@SuppressWarnings("unchecked")
	public static ServiceItem getServiceItemInfo(int itemId, String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIItem = new SubProParam(
				new java.math.BigDecimal(itemId), SubProParam.IN);
		params.add(subIItem);

		subIItem = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIItem);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);
		try {
			params = executeSubPro(ePMSSql.sqlGetServiceItemInfo, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		Vector<ServiceItem> vItem = DataUtils.LoadServiceItem(outParam);
		ServiceItem item = null;
		if (vItem != null && vItem.size() > 0) {
			item = (ServiceItem) vItem.get(0);
		}
		String outScreen = "getServiceItemInfo with params:[itemId=" + itemId
				+ "] ";
		log.info(outScreen);
		return item;

	}

	public static OrderRes orderService(String smartcard, String pinCode,
			Vector<CMNShopbill> itemOrders, String orderTime, String orderDate,
			String guestName) {
		OrderRes result = null;
		CMNShopbill abill = null;
		int Icode = 0;
		int IQty = 0;
		int IMainMenu = 0;
		int IMenu = 0;
		int checkNo = -1;
		int isOrder = 1;// alert is opened

		if (itemOrders == null || itemOrders.size() == 0) {
			System.out.println("Cart is empty!");
			result = new OrderRes();
			result.setCheckNo(checkNo);
			result.setAlertFlag(isOrder);
			return result;
		}
		log.info("Begin to process Order.");
		try {
			// luu thong tin chi tiet cac item duoc order
			for (int i = 0; i < itemOrders.size(); i++) {
				abill = (CMNShopbill) itemOrders.get(i);
				System.out.println(abill.toString());
				if (abill != null) {
					if (abill.getCheckNo() > 0) {// item trong bill dang ton tai
						checkNo = abill.getCheckNo();
						System.out.println("Cu cu cu");
					} else {
						// item trong bill moi
						checkNo = getCheckNo(PMSConstant.DINNING);
						System.out.println("Moi moi moi moi 30122011----");

					}
					Icode = abill.getICode();
					IQty = abill.getIQty();
					IMainMenu = abill.getMainMenuId();
					IMenu = abill.getMenuID();
					// orderTime = abill.getOrderTime();
					// orderDate = abill.getOrderDate();
					String outScreen = ((i + 1) + ".Items is selected : Icode="
							+ Icode + "|| IQty=" + IQty + ",|| checkNo="
							+ checkNo + ",orderTime=" + orderTime
							+ ",orderDate=" + orderDate);
					log.info(outScreen);

					if (Icode > 0 && IQty > 0) {
						isOrder = postedItemToBill(smartcard, checkNo,
								IMainMenu, IMenu, Icode, IQty, orderTime,
								orderDate, pinCode, guestName);
					}
				}
			}

			result = new OrderRes();
			result.setCheckNo(checkNo);
			result.setAlertFlag(isOrder);

		} catch (Exception e) {
			result = new OrderRes();
			result.setCheckNo(checkNo);
			result.setAlertFlag(isOrder);
			log.info(e.getMessage());
		}
		log.info("--->sendOrderService is complete!");
		return result;

	}

	public static int bookTableHotel(eReservation reservation) {
		int seq = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		if (reservation != null) {
			SubProParam subISer = new SubProParam(new java.math.BigDecimal(
					reservation.getsId()), SubProParam.IN);
			params.add(subISer); // 1

			SubProParam subIHotel = new SubProParam(new java.math.BigDecimal(
					reservation.getMenuId()), SubProParam.IN);
			params.add(subIHotel); // 2

			SubProParam subIGuestNum = new SubProParam(
					new java.math.BigDecimal(reservation.getGuestNum()),
					SubProParam.IN);
			params.add(subIGuestNum); // 3

			SubProParam subIDateTime = new SubProParam(reservation.getDate(),
					SubProParam.IN);
			params.add(subIDateTime); // 4

			String openTime = reservation.getOpentTime();
			if (openTime == null) {
				openTime = "";
			}
			if (openTime != null & openTime.equals("null")) {
				openTime = "";
			}
			SubProParam subIOpenTime = new SubProParam(openTime, SubProParam.IN);
			params.add(subIOpenTime); // 5

			int warningTime = reservation.getWarningTime();
			SubProParam subIWarning = new SubProParam(new java.math.BigDecimal(
					warningTime), SubProParam.IN);
			params.add(subIWarning); // 6

			String note = "";
			if (reservation.getNote() != null) {
				note = reservation.getNote();
			}
			SubProParam subINote = new SubProParam(note, SubProParam.IN);
			params.add(subINote); // 7

			SubProParam subIView = new SubProParam(reservation.getSeatView(),
					SubProParam.IN);
			params.add(subIView); // 8

			SubProParam subISmartcard = new SubProParam(
					reservation.getSmartCard(), SubProParam.IN);
			params.add(subISmartcard); // 9

			SubProParam subIPinCode = new SubProParam(reservation.getPinCode(),
					SubProParam.IN);
			params.add(subIPinCode); // 10

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(out_data); // 11

			String outScreen = "[bookTableHotel: with params reservation="
					+ reservation.toString() + "]";

			try {
				params = executeSubPro(ePMSSql.sqlBookTableHotel, params);
				log.info(outScreen);
				if (params != null && params.size() > 0) {
					SubProParam paramOUT = (SubProParam) params.get(10);
					String tmp = (paramOUT.getString()).trim();
					seq = Utils.parseInt(tmp);
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return seq;
	}

	public static List<eObject> getItemOfWeb(int mainMenuId, String smartcard,
			int from, int tto) {
		Vector<String> outParam = new Vector<String>();
		if (smartcard == null || smartcard.equals("")) {
			smartcard = "-1";
		}
		Vector<eObject> veWeb = new Vector<eObject>();

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				mainMenuId), SubProParam.IN);
		params.add(subPro);

		SubProParam subPro1 = new SubProParam(smartcard, SubProParam.IN);
		params.add(subPro1);

		SubProParam subPro2 = new SubProParam(new java.math.BigDecimal(from),
				SubProParam.IN);
		params.add(subPro2);

		SubProParam subPro3 = new SubProParam(new java.math.BigDecimal(tto),
				SubProParam.IN);
		params.add(subPro3);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getItemOfWeb with params:IMainMenu=" + mainMenuId
				+ " ,smartcard=" + smartcard + "]";

		try {
			params = executeSubPro(ePMSSql.sqlGetItemOfWeb, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(4);
				outParam = out_data.getVector();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		veWeb = DataUtils.LoadWeb(outParam);
		log.info(outScreen + " : returnValue(size=" + veWeb.size() + ")]");
		return veWeb;
	}

	public static int saveUrlWeb(int mainMenuId, String smartcard, String url) {
		int seq = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				mainMenuId), SubProParam.IN);
		params.add(subPro);

		SubProParam subPro1 = new SubProParam(smartcard, SubProParam.IN);
		params.add(subPro1);

		SubProParam subPro2 = new SubProParam(url, SubProParam.IN);
		params.add(subPro2);

		SubProParam subPro3 = new SubProParam(new String(), SubProParam.OUT);
		params.add(subPro3);

		String outScreen = "[saveUrlWeb with params:IMainMenu=" + mainMenuId
				+ " ,smartcard=" + smartcard + "url=" + url + "]";

		try {
			params = executeSubPro(ePMSSql.sqSaveUrlWeb, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				String tmp = (paramOUT.getString()).trim();
				seq = Utils.parseInt(tmp);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info(outScreen + ": returnValue seq=" + seq);
		return seq;
	}

	public static boolean deleteUrlWeb(int webId) {
		int seq = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subIWeb = new SubProParam(new java.math.BigDecimal(webId),
				SubProParam.IN);
		params.add(subIWeb);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[deleteUrlWeb with params:webId=" + webId + "]";
		try {
			params = executeSubPro(ePMSSql.sqDelelteUrlWeb, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				String tmp = (paramOUT.getString()).trim();
				seq = Utils.parseInt(tmp);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info(outScreen + ": returnValue seq=" + seq);
		return true;
	}

	// ---------------------weather------------------------
	public static Vector<eCountry> getCountries(int level) {
		Vector<String> outParam = new Vector<String>();
		Vector<eCountry> veCountry = new Vector<eCountry>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIItem = new SubProParam(new java.math.BigDecimal(level),
				SubProParam.IN);
		params.add(subIItem);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getCountries with params: level=" + level + "]";
		try {
			params = executeSubPro(ePMSSql.sqlGetCountries, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			// Utils.outScreen(log,ex.getMessage(),false);
			log.error(ex.getMessage());
		}

		veCountry = DataUtils.LoadCountries(outParam);
		outScreen = outScreen + " : returnValue(size=" + veCountry.size() + ")";
		log.info(outScreen);
		return veCountry;
	}

	public static eWeather getWeatherCommonToday() {
		return getWeatherToday();
	}

	public static eWeather getWeatherToday() {
		Vector<String> outParam = new Vector<String>();
		eWeather eweather = null;
		Date today = new Date();
		int thuHienTai = today.getDay() + 1;
		if (thuHienTai == 1)
			thuHienTai = 8;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIFolio = new SubProParam(new java.math.BigDecimal(
				thuHienTai), SubProParam.IN);
		params.add(subIFolio);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getWeatherToday no params input (but input funtion thuHienTai="
				+ thuHienTai + "]";
		try {
			params = executeSubPro(ePMSSql.sqlGetWeatherToday, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		log.info(outScreen);
		Vector<eWeather> vWeather = DataUtils.LoadWeathers(outParam);
		if (vWeather != null && vWeather.size() > 0) {
			eweather = (eWeather) vWeather.get(0);
		}
		return eweather;
	}

	@SuppressWarnings("unchecked")
	public static Vector<eWeather> getWeathersInWeek(int countryId,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<eWeather> vWeather = new Vector<eWeather>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIn = new SubProParam(
				new java.math.BigDecimal(countryId), SubProParam.IN);
		params.add(subIn);

		subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = ("[getWeathersInWeek with params countryId="
				+ countryId + "]");

		try {
			params = executeSubPro(ePMSSql.sqlGetWeathersInWeek, params);

			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vWeather = DataUtils.LoadWeathers(outParam);
		outScreen = outScreen + " : returnValue(size=" + vWeather.size() + ")";
		log.info(outScreen);
		return vWeather;
	}

	@SuppressWarnings("unchecked")
	public static eWeather getWeatherToday(int countryId) {
		Vector<String> outParam = new Vector<String>();
		eWeather eweather = null;
		Date today = new Date();
		int thuHienTai = today.getDay() + 1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subIthuHienTai = new SubProParam(new java.math.BigDecimal(
				thuHienTai), SubProParam.IN);
		params.add(subIthuHienTai);

		SubProParam subICountry = new SubProParam(new java.math.BigDecimal(
				countryId), SubProParam.IN);
		params.add(subICountry);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getWeatherToday with params countryId="
				+ countryId + " , input thuHienTai=" + thuHienTai;

		try {
			params = executeSubPro(ePMSSql.sqlGetWeatherTodayById, params);

			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		log.info(outScreen);
		Vector<eWeather> vWeather = DataUtils.LoadWeathers(outParam);
		if (vWeather != null && vWeather.size() > 0) {
			eweather = (eWeather) vWeather.get(0);
		}
		return eweather;

	}

	public static Vector<eWakeup> getFolioWakeup(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<eWakeup> vWakeup = new Vector<eWakeup>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getFolioWakeup with params smartcard=" + smartcard
				+ "]";

		try {
			params = executeSubPro(ePMSSql.sqlGetFolioWakeup, params);

			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vWakeup = DataUtils.LoadWakeups(outParam);
		log.info(outScreen + " : returnValue=" + vWakeup.size());
		return vWakeup;
	}

	public static Vector<eWakeupForms> getWakeupForms(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<eWakeupForms> vWakeupForms = new Vector<eWakeupForms>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getWakeupForms with no params ]";
		try {
			params = executeSubPro(ePMSSql.sqlGetWakeupForms, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		vWakeupForms = DataUtils.LoadWakeupForms(outParam);
		outScreen = outScreen + " : returnValue(size=" + vWakeupForms.size()
				+ ")";
		log.info(outScreen);
		return vWakeupForms;
	}

	public static boolean postedWakeup(String smartcard, String hours,
			String minutes, String wakeupDate, String phone) {
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIsmartcard);

		SubProParam IHours = new SubProParam(hours, SubProParam.IN);
		params.add(IHours);

		SubProParam IMinutes = new SubProParam(minutes, SubProParam.IN);
		params.add(IMinutes);

		SubProParam IDate = new SubProParam(wakeupDate, SubProParam.IN);
		params.add(IDate);

		SubProParam IWakeupForms = new SubProParam(phone, SubProParam.IN);
		params.add(IWakeupForms);

		try {
			params = executeSubPro(ePMSSql.sqlPostedWakeup, params);

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return true;
	}

	public static boolean closedWakeup(String smartcard) {
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIsmartcard);
		String outScreen = "[closedWakeup with params smartcard=" + smartcard
				+ "]";
		try {
			params = executeSubPro(ePMSSql.sqlClosedWakeup, params);
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static Vector<eCurrency> getExchangeRates(int fromRow, int noRows) {
		Vector<String> outParam = new Vector<String>();
		Vector<eCurrency> veCurrency = new Vector<eCurrency>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIFrom = new SubProParam(
				new java.math.BigDecimal(fromRow), SubProParam.IN);
		params.add(subIFrom);

		SubProParam subITo = new SubProParam(new java.math.BigDecimal(noRows),
				SubProParam.IN);
		params.add(subITo);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "getExchangeRates with params:[fromRow=" + fromRow
				+ "noRows=" + noRows + "] ";
		try {
			params = executeSubPro(ePMSSql.sqlGetExchangeRates, params);

			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		veCurrency = DataUtils.LoadCurrencies(outParam);
		outScreen = outScreen + " : returnValue(size=" + veCurrency.size()
				+ ")";
		log.info(outScreen);
		return veCurrency;
	}

	public static Vector<eMessage> getFolioMessages(String smartcard,
			int fromRow, int noRows, int numStr) {
		Vector<String> outParam = new Vector<String>();
		Vector<eMessage> vFolioMessage = new Vector<eMessage>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIFolio = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIFolio);

		SubProParam subIFrom = new SubProParam(
				new java.math.BigDecimal(fromRow), SubProParam.IN);
		params.add(subIFrom);

		SubProParam subITo = new SubProParam(new java.math.BigDecimal(noRows),
				SubProParam.IN);
		params.add(subITo);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "getFolioMessages with params:[smartcard="
				+ smartcard + ",fromRow=" + fromRow + ",noRows=" + noRows
				+ "] ";
		try {
			params = executeSubPro(ePMSSql.sqlGetFolioMessages, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(3);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vFolioMessage = DataUtils.LoadMessages(outParam, numStr);
		outScreen = outScreen + " : returnValue(size=" + vFolioMessage.size()
				+ ")";
		log.info(outScreen);
		return vFolioMessage;
	}

	@SuppressWarnings("unchecked")
	public static Vector<OutletImage> getAdverImages(String serviceType,
			String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<OutletImage> vOutletImage = new Vector<OutletImage>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(serviceType, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(smartcard, SubProParam.IN);
		params.add(subPro);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "getAdverImages with params:serviceType="
				+ serviceType + "]";
		try {
			params = executeSubPro(ePMSSql.sqlGetAdverImages, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vOutletImage = DataUtils.LoadOutletImages(outParam);
		outScreen = outScreen + " : returnValue(size=" + vOutletImage.size()
				+ ")";
		log.info(outScreen);
		return vOutletImage;
	}

	public static OutletMovie getAdvertiseMovie(String serviceType) {
		Vector<String> outParam = new Vector<String>();
		OutletMovie outletMovie = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subIDic = new SubProParam(serviceType, SubProParam.IN);
		params.add(subIDic);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = ("getAdvertiseMovie with params:serviceType="
				+ serviceType + "] ");

		try {
			params = executeSubPro(ePMSSql.sqlGetAdvertiseMovie, params);
			log.info(outScreen);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		outletMovie = DataUtils.LoadOutletMovie(outParam);
		return outletMovie;

	}

	public static boolean setMessageStatus(int messId, int status) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subImessageId = new SubProParam(new java.math.BigDecimal(
				messId), SubProParam.IN);
		params.add(subImessageId);
		SubProParam subisconfirm = new SubProParam(new java.math.BigDecimal(
				status), SubProParam.IN);
		params.add(subisconfirm);

		String outScreen = "setMessageStatus with params [messageId=" + messId
				+ "|| isconfirm=" + status + "]";
		try {
			params = executeSubPro(ePMSSql.sqlSetMessageStatus, params);
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return true;
	}

	public static int countOther(String smartcard, String type) {
		int ret = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subIDic = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIDic);

		SubProParam subItype = new SubProParam(type, SubProParam.IN);
		params.add(subItype);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		String outScreen = "countOther with params:smartcard=" + smartcard
				+ ",type=" + type + "]";

		try {
			params = executeSubPro(ePMSSql.sqlcountOther, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				String tmp = (paramOUT.getString()).trim();
				ret = Utils.parseInt(tmp);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
		log.info(outScreen + " : returnValue count=" + ret);
		return ret;
	}

	public static int countNewMessage(String smartcard) {
		int ret = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subFolio = new SubProParam(smartcard, SubProParam.IN);
		params.add(subFolio);

		SubProParam subIOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subIOut);

		String outScreen = "countNewMessage with params:smartcard=" + smartcard
				+ "]";
		try {
			params = executeSubPro(ePMSSql.sqlCountNewMessage, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				String tmp = (paramOUT.getString()).trim();
				ret = Utils.parseInt(tmp);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		log.info(outScreen + " : returnValue count=" + ret);
		return ret;
	}

	public static int countItem(int menuId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = 0;
		// R=Roomservice, S=Shop,I=info tour,A=Activity tour
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subPro01 = new SubProParam(type, SubProParam.IN);
		params.add(subPro01);

		SubProParam subIOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subIOut);

		String outScreen = "countItem with params:[IMenu=" + menuId
				+ " || type=" + type + "]";

		try {
			params = executeSubPro(ePMSSql.sqlCountItem, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				String tmp = (paramOUT.getString()).trim();
				ret = Utils.parseInt(tmp);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		log.info(outScreen + " : returnValue count=" + ret);
		return ret;
	}

	private static int postedItemToBill(String smartcard, int checkNo,
			int IMainMenu, int IMenu, int ItemCode, int IQty, String orderTime,
			String orderDate, String cashcard, String guestName) {
		int ret = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subParam = new SubProParam(smartcard, SubProParam.IN);
		params.add(subParam); // 1

		subParam = new SubProParam(new java.math.BigDecimal(checkNo),
				SubProParam.IN);
		params.add(subParam); // 2

		subParam = new SubProParam(new java.math.BigDecimal(IMainMenu),
				SubProParam.IN);
		params.add(subParam); // 3

		subParam = new SubProParam(new java.math.BigDecimal(IMenu),
				SubProParam.IN);
		params.add(subParam); // 4

		subParam = new SubProParam(new java.math.BigDecimal(ItemCode),
				SubProParam.IN);
		params.add(subParam); // 5

		subParam = new SubProParam(new java.math.BigDecimal(IQty),
				SubProParam.IN);
		params.add(subParam); // 6

		if (orderTime == null)
			orderTime = "default";
		subParam = new SubProParam(orderTime, SubProParam.IN);
		params.add(subParam); // 7

		if (orderDate == null)
			orderDate = "default";
		subParam = new SubProParam(orderDate, SubProParam.IN);
		params.add(subParam); // 8

		subParam = new SubProParam(cashcard, SubProParam.IN);
		params.add(subParam); // 9

		subParam = new SubProParam(guestName, SubProParam.IN);
		params.add(subParam); // 10

		SubProParam subISeq = new SubProParam(new String(), SubProParam.OUT);
		params.add(subISeq); // 11

		String outScreen = ("[postedItemToBill with params [smartcard="
				+ smartcard + ",IMainMenu=" + IMainMenu + ",IMenu=" + IMenu
				+ ",ICode=" + ItemCode + ", IQuanlity=" + IQty + "]");

		try {
			params = executeSubPro(ePMSSql.sqlPostedItemToBill, params);
			log.info(outScreen);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				String tmp = (paramOUT.getString()).trim();
				ret = Utils.parseInt(tmp);
				log.info("Posted ICode=" + ItemCode + " is complete!");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info("--->postedItemToBill is complete!");
		return ret;
	}

	@SuppressWarnings("unchecked")
	public static Vector<eMessage> getMessages(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<eMessage> veCurrency = new Vector<eMessage>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIFrom = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIFrom);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "getMessages with params:[smartcard=" + smartcard
				+ "] ";
		try {
			params = executeSubPro(ePMSSql.sqlGetMessages, params);

			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		veCurrency = DataUtils.LoadMessages(outParam, 0);
		outScreen = outScreen + " : returnValue(size=" + veCurrency.size()
				+ ")";
		log.info(outScreen);
		return veCurrency;
	}

	@SuppressWarnings("unchecked")
	public static eMessage getMessageInfo(int messId, String smartcard) {
		Vector<String> outParam = new Vector<String>();
		eMessage aMessage = null;
		Vector<eMessage> eMessages = new Vector<eMessage>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subIn = new SubProParam(new java.math.BigDecimal(messId),
				SubProParam.IN);
		params.add(subIn);

		subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = ("getMessageInfo with messId=" + messId + "] ");

		try {
			params = executeSubPro(ePMSSql.sqlGetMessageInfo, params);
			log.info(outScreen);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		eMessages = DataUtils.LoadMessages(outParam, 0);
		if (eMessages != null && eMessages.size() > 0) {
			aMessage = eMessages.get(0);
		}
		return aMessage;
	}

	public static String countTotalByCheckNo(int checkNo) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		String ret = "0";
		// R=Roomservice, S=Shop,I=info tour,A=Activity tour
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(checkNo),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subIOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subIOut);

		String outScreen = "countTotalByCheckNo with params:[checkNo="
				+ checkNo + "]";

		try {
			params = executeSubPro(ePMSSql.sqlCountTotalByCheckNo, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				String tmp = (paramOUT.getString()).trim();
				ret = tmp;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		log.info(outScreen + " : returnValue count=" + ret);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public static Vector<ServiceCharge> getBills(String smartcard) {
		Vector<String> outParam = new Vector<String>();
		Vector<ServiceCharge> vServiceCharge = new Vector<ServiceCharge>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[getBills with params:smartcard=" + smartcard + "]";
		try {
			params = executeSubPro(ePMSSql.sqlGetBills, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		vServiceCharge = DataUtils.LoadBills(outParam);
		log.info(outScreen + " : returnValue(size=" + vServiceCharge.size()
				+ ")]");
		return vServiceCharge;
	}

	public static String countBillTotal(String smartcard) {
		String count = "";
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subsmartcard = new SubProParam(smartcard, SubProParam.IN);
		params.add(subsmartcard);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		String outScreen = "[countBillTotal with params:smartcard=" + smartcard
				+ "]";
		try {
			params = executeSubPro(ePMSSql.sqlCountBillTotal, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				String tmp = (paramOUT.getString()).trim();
				count = Utils.processAmountOfUser(tmp);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info(outScreen + " : returnValue(size=" + count + ")]");
		return count;
	}

	public static void main(String[] args) {
		ePMSDao dao = new ePMSDao();
		dao.getHomeService("2001");
	}
}
