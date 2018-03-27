package com.elcom.eodapp.ehotel.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.elcom.ehotel.smile.FieldVariables.FID;
import com.elcom.eodapp.ehotel.common.xsd.OBill;
import com.elcom.eodapp.ehotel.common.xsd.OPost;
import com.elcom.eodapp.ehotel.processor.MainObject;
import com.elcom.eodapp.ehotel.utils.CommandProfile;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.DataHelper;

public class DataProcessor {

	Logger logger = Logger.getLogger(DataProcessor.class);
	CommandBuilder cmdBuilder = DAOFactory.getInstance().getCommandBuilder();

	public void GuestCheckIn(CommandProfile pf) {
		String GUEST_NUMER = pf.getFieldValue(FID.GUEST_NUMBER);// G#: GuestNumber(Smile) = ReservationNumber(Opera)
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		String RESERVATION_NUMER = pf.getFieldValue(FID.RESERVATION_NUMBER);// R#
		String GUEST_FIRST_NAME = pf.getFieldValue(FID.GUEST_FIRST_NAME);
		String GUEST_NAME = pf.getFieldValue(FID.GUEST_NAME);
		String GUEST_TITLE = pf.getFieldValue(FID.GUEST_TITLE);
		String GUEST_ARV_DATE = pf.getFieldValue(FID.GUEST_ARV_DATE);
		String GUEST_DEPT_DATE = pf.getFieldValue(FID.GUEST_DEPT_DATE);
		if (GUEST_TITLE == "")
			GUEST_TITLE = "Mister";

		// Add
		String PIN_NUMBER = pf.getFieldValue(FID.GUEST_PIN);
		String shareFLAG = pf.getFieldValue(FID.SHARE_FLAG);
		String guestLANGUAGE = pf.getFieldValue(FID.GUEST_LANGUAGE);
		String guestGROUPNUMBER = pf.getFieldValue(FID.GUEST_GROUP_NUMBER);
		String date = pf.getFieldValue(FID.DATE);
		String time = pf.getFieldValue(FID.TIME);
		String swapFLAG = "0";
		/*
		 * if(shareFLAG == "Y") shareFLAG = "1"; else shareFLAG = "0";:/
		 * 
		 * /*Customize GuestName with VietAnh's Opera format Not use this from mr.HuuLN 25102015
		 */
		// GUEST_NAME = ReBuildGuestName(GUEST_TITLE, GUEST_NAME, GUEST_FIRST_NAME);

		String pInfo = DataHelper.getParamInfo("GuestCheckIn", new String[] { "GUEST_NUMER(G#):" + GUEST_NUMER,
				"ROOM_NUMBER:" + ROOM_NUMBER, "RESERVATION_NUMER(R#):" + RESERVATION_NUMER, "GUEST_FIRST_NAME:" + GUEST_FIRST_NAME,
				"GUEST_NAME:" + GUEST_NAME, "GUEST_TITLE:" + GUEST_TITLE, "GUEST_FIRST_NAME:" + GUEST_FIRST_NAME,
				"GUEST_ARV_DATE:" + GUEST_ARV_DATE, "GUEST_DEPT_DATE:" + GUEST_DEPT_DATE, "PIN_NUMBER:" + PIN_NUMBER,
				"shareFLAG:" + shareFLAG, "guestLANGUAGE:" + guestLANGUAGE, "date:" + date, "time:" + time, });
		logger.info(pInfo);
		// boolean out = dao.GuestCheckin(GUEST_NUMER, ROOM_NUMBER, RESERVATION_NUMER, GUEST_FIRST_NAME, GUEST_NAME, GUEST_TITLE,
		// GUEST_ARV_DATE, GUEST_DEPT_DATE);
		String out = MainObject.eHotelWebService.guestCheckin(GUEST_NUMER, GUEST_ARV_DATE, GUEST_DEPT_DATE, GUEST_TITLE, GUEST_FIRST_NAME,
				GUEST_NAME, ROOM_NUMBER, PIN_NUMBER, shareFLAG, guestLANGUAGE, guestGROUPNUMBER, date, time, swapFLAG);
		logger.info(pInfo + "==>" + out);
	}

	public void GuestCheckOut(CommandProfile pf) {
		String RESERVATION_NUMER = pf.getFieldValue(FID.RESERVATION_NUMBER);
		String GUEST_NUMER = pf.getFieldValue(FID.GUEST_NUMBER);
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		// Add
		String swapFLAG = "0";
		String pInfo = DataHelper.getParamInfo("GuestCheckOut", new String[] { "GUEST_NUMER(G#):" + GUEST_NUMER,
				"RESERVATION_NUMER(R#):" + RESERVATION_NUMER, "ROOM_NUMER:" + ROOM_NUMBER });
		logger.info(pInfo);
		// boolean out = dao.GuestCheckout(RESERVATION_NUMER, ROOM_NUMBER);
		String out = MainObject.eHotelWebService.guestCheckout(GUEST_NUMER, ROOM_NUMBER, swapFLAG);
		logger.info(pInfo + "==>" + out);
	}

	public void GuestChangeData(CommandProfile pf) {
		String GUEST_NUMBER = pf.getFieldValue(FID.GUEST_NUMBER);
		String RESERVATION_NUMER = pf.getFieldValue(FID.RESERVATION_NUMBER);
		String GUEST_FIRST_NAME = pf.getFieldValue(FID.GUEST_FIRST_NAME);
		String GUEST_NAME = pf.getFieldValue(FID.GUEST_NAME);
		String GUEST_LANGUAGE = pf.getFieldValue(FID.GUEST_LANGUAGE);
		String GUEST_TITLE = pf.getFieldValue(FID.GUEST_TITLE);
		String GUEST_ARV_DATE = pf.getFieldValue(FID.GUEST_ARV_DATE);
		String GUEST_DEPT_DATE = pf.getFieldValue(FID.GUEST_DEPT_DATE);
		// Add
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		String SHARE_FLAG = pf.getFieldValue(FID.SHARE_FLAG);
		String GUEST_GROUP_NUMBER = pf.getFieldValue(FID.GUEST_GROUP_NUMBER);
		String DATE = pf.getFieldValue(FID.DATE);
		String TIME = pf.getFieldValue(FID.TIME);
		/*
		 * if(SHARE_FLAG == "Y") SHARE_FLAG = "1"; else SHARE_FLAG = "0";
		 */

		/*
		 * Customize GuestName with VietAnh's Opera format Not use this from mr.HuuLN 25102015
		 */
		// GUEST_NAME = ReBuildGuestName(GUEST_TITLE, GUEST_NAME, GUEST_FIRST_NAME);

		String pInfo = DataHelper.getParamInfo("GuestChangeData", new String[] { "GUEST_NUMBER (G#):" + GUEST_NUMBER,
				"RESERVATION_NUMER(R#):" + RESERVATION_NUMER, "GUEST_TITLE:" + GUEST_TITLE, "GUEST_FIRST_NAME:" + GUEST_FIRST_NAME,
				"GUEST_NAME:" + GUEST_NAME, "GUEST_LANGUAGE:" + GUEST_LANGUAGE, "GUEST_ARV_DATE:" + GUEST_ARV_DATE,
				"GUEST_DEPT_DATE:" + GUEST_DEPT_DATE, "ROOM_NUMBER:" + ROOM_NUMBER, "SHARE_FLAG:" + SHARE_FLAG,
				"GUEST_GROUP_NUMBER:" + GUEST_GROUP_NUMBER, "DATE:" + DATE, "TIME:" + TIME });
		logger.info(pInfo);
		// boolean out = dao.GuestChangeData(GUEST_NUMBER, RESERVATION_NUMER, GUEST_FIRST_NAME, GUEST_NAME, GUEST_LANGUAGE, GUEST_TITLE,
		// GUEST_ARV_DATE, GUEST_DEPT_DATE);
		String out = MainObject.eHotelWebService.guestChangeData(GUEST_NUMBER, ROOM_NUMBER, SHARE_FLAG, GUEST_FIRST_NAME, GUEST_NAME,
				GUEST_LANGUAGE, GUEST_GROUP_NUMBER, GUEST_TITLE, GUEST_ARV_DATE, GUEST_DEPT_DATE, DATE, TIME);
		logger.info(pInfo + "==>" + out);
	}

	@SuppressWarnings("unused")
	public void GuestRoomMove(CommandProfile pf) {
		String RESERVATION_NUMBER = pf.getFieldValue(FID.RESERVATION_NUMBER);
		String GUEST_NUMBER = pf.getFieldValue(FID.GUEST_NUMBER);
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		// Add
		String OLD_ROOM_NUMBER = pf.getFieldValue(FID.OLD_ROOM_NUMBER);
		String GUEST_NAME = pf.getFieldValue(FID.GUEST_NAME);
		String GUEST_TITLE = pf.getFieldValue(FID.GUEST_TITLE);
		String GUEST_FIRST_NAME = pf.getFieldValue(FID.GUEST_FIRST_NAME);

		/*
		 * Customize GuestName with VietAnh's Opera format Not use this from mr.HuuLN 25102015
		 */
		// GUEST_NAME = ReBuildGuestName(GUEST_TITLE, GUEST_NAME, GUEST_FIRST_NAME);

		String pInfo = DataHelper.getParamInfo("GuestRoomMove", new String[] { "GUEST_NUMBER (G#):" + GUEST_NUMBER,
				"RESERVATION_NUMBER (R#):" + RESERVATION_NUMBER, "GUEST_NAME:" + GUEST_NAME, "ROOM_NUMBER:" + ROOM_NUMBER,
				"OLD_ROOM_NUMBER:" + OLD_ROOM_NUMBER });
		logger.info(pInfo);
		// boolean out = dao.GuestRoomMove(RESERVATION_NUMBER, ROOM_NUMBER);
		String out = MainObject.eHotelWebService.guestRoomMove(GUEST_NUMBER, GUEST_NAME, ROOM_NUMBER, OLD_ROOM_NUMBER);
		logger.info(pInfo + "==>" + out);
	}

	@SuppressWarnings("unused")
	public void GuestRoomMoveAll(CommandProfile pf) {
		String RESERVATION_NUMBER = pf.getFieldValue(FID.RESERVATION_NUMBER); 
		String OLD_ROOM_NUMBER = pf.getFieldValue(FID.OLD_ROOM_NUMBER);
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		String GUEST_NAME = pf.getFieldValue(FID.GUEST_NAME);

		String pInfo = DataHelper.getParamInfo("GuestRoomMoveAll", new String[] { "OLD_ROOM_NUMBER:" + OLD_ROOM_NUMBER,
				"ROOM_NUMBER:" + ROOM_NUMBER });
		logger.info(pInfo);
		// boolean out = dao.GuestRoomMoveAll(OLD_ROOM_NUMBER, ROOM_NUMBER);
//		String out = "waiting";
		String out = MainObject.eHotelWebService.guestRoomMove(RESERVATION_NUMBER, GUEST_NAME, ROOM_NUMBER, OLD_ROOM_NUMBER);
		logger.info(pInfo + "==>" + out);
	}

	public void GuestBillItem(CommandProfile pf) {
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		String GUEST_NUMBER = pf.getFieldValue(FID.GUEST_NUMBER);
		String TRANSACTION_ID = pf.getFieldValue(FID.TRANSACTION_ID);
		String TRANSACTION_CODE = pf.getFieldValue(FID.TRANSACTION_CODE);
		String ITEM_AMOUNT = pf.getFieldValue(FID.ITEM_AMOUNT);
		String ITEM_DESC = pf.getFieldValue(FID.ITEM_DESCRIPTION);
		String BALANCE_AMOUNT = pf.getFieldValue(FID.BALANCE_AMOUNT);
		String CURRENCY = pf.getFieldValue(FID.CURRENCY);
		String DATE = pf.getFieldValue(FID.DATE);
		String TIME = pf.getFieldValue(FID.TIME);

		// Add
		String displayFLAG = "1";
		String pInfo = DataHelper.getParamInfo("GuestBillItem", new String[] { "ROOM_NUMBER:" + ROOM_NUMBER,
				"GUEST_NUMBER(G#):" + GUEST_NUMBER, "TRANSACTION_ID:" + TRANSACTION_ID, "TRANSACTION_CODE:" + TRANSACTION_CODE,
				"ITEM_AMOUNT:" + ITEM_AMOUNT, "ITEM_DESC:" + ITEM_DESC, "BALANCE_AMOUNT:" + BALANCE_AMOUNT, "CURRENCY:" + CURRENCY,
				"DATE:" + DATE, "TIME:" + TIME });
		logger.info(pInfo);
		// boolean out = dao.BillItem(ROOM_NUMBER, GUEST_NUMBER, TRANSACTION_ID, TRANSACTION_CODE, ITEM_AMOUNT, ITEM_DESC, BALANCE_AMOUNT,
		// DATE, TIME);
		BALANCE_AMOUNT = BALANCE_AMOUNT.replace("&", "@");
		String out = MainObject.eHotelWebService.guestBillItem(ROOM_NUMBER, GUEST_NUMBER, TRANSACTION_ID, TRANSACTION_CODE, ITEM_AMOUNT,
				CURRENCY, ITEM_DESC, BALANCE_AMOUNT, displayFLAG, DATE, TIME);
		logger.info(pInfo + "==>" + out);
	}

	public void GuestBillBalance(CommandProfile pf) {
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		String GUEST_NUMBER = pf.getFieldValue(FID.GUEST_NUMBER);
		String BALANCE_AMOUNT = pf.getFieldValue(FID.BALANCE_AMOUNT);
		String DATE = pf.getFieldValue(FID.DATE);
		String TIME = pf.getFieldValue(FID.TIME);

		String pInfo = DataHelper.getParamInfo("GuestBillBalance", new String[] { "ROOM_NUMBER:" + ROOM_NUMBER,
				"GUEST_NUMBER(G#):" + GUEST_NUMBER, "BALANCE_AMOUNT:" + BALANCE_AMOUNT, "DATE:" + DATE, "TIME:" + TIME });
		logger.info(pInfo);
		logger.info(pInfo + "==>" + " khong thuc hien command.");
		// boolean out = dao.guestBillBalan(ROOM_NUMBER, GUEST_NUMBER, BALANCE_AMOUNT, DATE, TIME);
		// String out = MainObject.eHotelWebService.guestBillBalan(ROOM_NUMBER, GUEST_NUMBER, BALANCE_AMOUNT);
		// logger.info(pInfo + "==>" + out);
	}

	public void GuestMessageText(CommandProfile pf) {
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		String GUEST_NUMBER = pf.getFieldValue(FID.GUEST_NUMBER);
		String MESSAGE_ID = pf.getFieldValue(FID.MESSAGE_ID);
		String MESSAGE_TEXT = pf.getFieldValue(FID.MESSAGE_TEXT);
		String MESSAGE_SUBJECT = "Administrator";
		String DATE = pf.getFieldValue(FID.DATE);
		String TIME = pf.getFieldValue(FID.TIME);

		if (GUEST_NUMBER == "")
			GUEST_NUMBER = "1000";
		if (DATE == "")
			DATE = getDate();
		if (TIME == "")
			TIME = getTime();

		String pInfo = DataHelper.getParamInfo("GuestMessageText", new String[] { "ROOM_NUMBER:" + ROOM_NUMBER,
				"GUEST_NUMBER(G#):" + GUEST_NUMBER, "MESSAGE_ID:" + MESSAGE_ID, "MESSAGE_TEXT:" + MESSAGE_TEXT,
				"MESSAGE_SUBJECT:" + MESSAGE_SUBJECT, "DATE:" + DATE, "TIME:" + TIME });
		logger.info(pInfo);
		// boolean out = dao.guestMessageTextOnline(ROOM_NUMBER, GUEST_NUMBER, MESSAGE_ID, MESSAGE_TEXT, MESSAGE_SUBJECT, DATE, TIME);
		String out = MainObject.eHotelWebService.guestMessageText(ROOM_NUMBER, GUEST_NUMBER, MESSAGE_ID, MESSAGE_TEXT, DATE, TIME);
		logger.info(pInfo + "==>" + out);
	}

	public void GuestMessageDelete(CommandProfile pf) {
		String ROOM_NUMBER = pf.getFieldValue(FID.ROOM_NUMBER);
		String GUEST_NUMBER = pf.getFieldValue(FID.GUEST_NUMBER);
		String MESSAGE_ID = pf.getFieldValue(FID.MESSAGE_ID);

		String pInfo = DataHelper.getParamInfo("GuestMessageDelete", new String[] { "ROOM_NUMBER:" + ROOM_NUMBER,
				"GUEST_NUMBER(G#):" + GUEST_NUMBER, "MESSAGE_ID:" + MESSAGE_ID });
		logger.info(pInfo);
		// boolean out = dao.guestMessageDelete(MESSAGE_ID);
		String out = MainObject.eHotelWebService.guestMessageDelete(ROOM_NUMBER, GUEST_NUMBER, MESSAGE_ID);
		logger.info(pInfo + "==>" + out);
	}

	/**
	 * Create list command send to PMS
	 */

	@SuppressWarnings("unused")
	private String ReBuildGuestName(String GuestTitle, String GuestName, String GuestFirstName) {
		String result = GuestName;
		if (!result.contains(GuestTitle)) {
			result = GuestTitle + " " + result;
		}
		if (!result.contains(GuestFirstName)) {
			result = result + " " + GuestName;
		}
		return result;
	}

	public List<String> getBillReqCommand() {
		List<String> listCMD = new ArrayList<String>();
		// oBill[] listBill = dao.BillRequest();
		List<OBill> listBill = MainObject.eHotelWebService.guestBillReq("");
		logger.info("BillReq count:" + listBill.size());
		String cmdSend = "";
		for (OBill bill : listBill) {
			String cmd = cmdBuilder.GetBillCommand(bill);
			cmdSend += cmd + "\n";
			listCMD.add(cmd);
		}
		logger.info("guest bill request:" + cmdSend);
		return listCMD;
	}

	public List<String> getPostReqCommand() {
		List<String> listCMD = new ArrayList<String>();
		// oPost[] listPost = dao.PostSimple();
		List<OPost> listPost = MainObject.eHotelWebService.guestPostReq("");
		logger.info("count Post:" + listPost.size());
		String cmdSend = "";
		for (OPost post : listPost) {
			String cmd = cmdBuilder.GetPostCommand(post);
			cmdSend += cmd + "\n";
			listCMD.add(cmd);
		}
		logger.info("guest post request:" + cmdSend);
		return listCMD;
	}

	private String getDate() {
		DateFormat df = new SimpleDateFormat("dd-MMM-yy");

		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	private String getTime() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public static void main(String[] args) {
		String BALANCE_AMOUNT = "1425VND&0USD";
		BALANCE_AMOUNT = BALANCE_AMOUNT.replace("&", "@");
		System.out.println(BALANCE_AMOUNT);
	}

}
