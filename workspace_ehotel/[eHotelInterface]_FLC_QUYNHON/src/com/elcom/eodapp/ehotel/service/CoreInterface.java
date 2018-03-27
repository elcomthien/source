package com.elcom.eodapp.ehotel.service;

import java.rmi.RemoteException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.eodapp.ehotel.common.oBill;
import com.elcom.eodapp.ehotel.common.oMessageDel;
import com.elcom.eodapp.ehotel.common.oPost;
import com.elcom.eodapp.ehotel.common.oRoomData;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.DataHelper;

public class CoreInterface {

	private static CoreDao coredao = DAOFactory.getInstance().getCoreDao();
	private static final Logger logger = LogManager.getLogger(CoreInterface.class);

	public CoreInterface() throws RemoteException {
		super();
	}// constructor

	// ----------------------------------------------
	public String GuestCheckin(String RESERVATION_NUMER, String GUEST_ARV_DATE, String GUEST_DEPT_DATE, String GUEST_TITLE, String GUEST_FIRST_NAME,
			String GUEST_NAME, String ROOM_NUMER, String GUEST_PIN, String SHARE_FLAG, String GUEST_LANGUAGE, String GUEST_GROUP_NUMBER, String DATE,
			String TIME, String SWAP_FLAG) {

		String pInfo = DataHelper.getParamInfo("GuestCheckIn", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER,
				"GUEST_ARV_DATE:" + GUEST_ARV_DATE, "GUEST_DEPT_DATE:" + GUEST_DEPT_DATE, "GUEST_TITLE:" + GUEST_TITLE,
				"GUEST_FIRST_NAME:" + GUEST_FIRST_NAME, "GUEST_NAME:" + GUEST_NAME, "ROOM_NUMER:" + ROOM_NUMER, "GUEST_PIN:" + GUEST_PIN,
				"SHARE_FLAG:" + SHARE_FLAG, "GUEST_LANGUAGE:" + GUEST_LANGUAGE, "GUEST_GROUP_NUMBER:" + GUEST_GROUP_NUMBER, "DATE:" + DATE,
				"TIME:" + TIME });
		// RN|G#|GS|GF|GN|GL|GG|GT|GA|GD|DA|TI
		if (GUEST_DEPT_DATE == null || GUEST_DEPT_DATE.length() == 0)
			GUEST_DEPT_DATE = DATE;
		boolean i = coredao.GuestCheckin(ROOM_NUMER, RESERVATION_NUMER, SHARE_FLAG, GUEST_FIRST_NAME, GUEST_NAME, GUEST_LANGUAGE, GUEST_GROUP_NUMBER,
				GUEST_TITLE, GUEST_ARV_DATE, GUEST_DEPT_DATE, GUEST_PIN, SWAP_FLAG);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// ----------------------------------------------
	public String GuestCheckout(String RESERVATION_NUMER, String ROOM_NUMER, String SWAP_FLAG) // GO|FLRNG#
	{
		String pInfo = DataHelper.getParamInfo("GuestCheckout", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER, "ROOM_NUMER:" + ROOM_NUMER,
				"SWAP_FLAG:" + SWAP_FLAG });
		boolean i = coredao.GuestCheckout(RESERVATION_NUMER, ROOM_NUMER, SWAP_FLAG);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// -----------------------------------------------
	public String GuestChangeData(String RESERVATION_NUMER, String ROOM_NUMER, String SHARE_FLAG, String GUEST_FIRST_NAME, String GUEST_NAME,
			String GUEST_LANGUAGE, String GUEST_GROUP_NUMBER, String GUEST_TITLE, String GUEST_ARV_DATE, String GUEST_DEPT_DATE, String DATE,
			String TIME)
	// GC|FLRNG#GS GF GN GL GG GT GA GD DA TI
	{
		String pInfo = DataHelper.getParamInfo("GuestChangeData", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER,
				"ROOM_NUMER:" + ROOM_NUMER, "SHARE_FLAG:" + SHARE_FLAG, "GUEST_FIRST_NAME:" + GUEST_FIRST_NAME, "GUEST_NAME:" + GUEST_NAME,
				"GUEST_LANGUAGE:" + GUEST_LANGUAGE, "GUEST_GROUP_NUMBER:" + GUEST_GROUP_NUMBER, "GUEST_TITLE:" + GUEST_TITLE,
				"GUEST_ARV_DATE:" + GUEST_ARV_DATE, "GUEST_DEPT_DATE:" + GUEST_DEPT_DATE, "DATE:" + DATE, "TIME:" + TIME });
		String i = coredao.GuestChangeData(RESERVATION_NUMER, ROOM_NUMER, SHARE_FLAG, GUEST_FIRST_NAME, GUEST_NAME, GUEST_LANGUAGE,
				GUEST_GROUP_NUMBER, GUEST_TITLE, GUEST_ARV_DATE, GUEST_DEPT_DATE, DATE, TIME);
		logger.info(pInfo + "==>" + i);
		return i;
	}

	// ------------------------------------------------
	public String GuestRoomMove(String RESERVATION_NUMER, String GUEST_NAME, String ROOM_NUMER, String ROOM_NUMER_OLD) {
		String pInfo = DataHelper.getParamInfo("GuestRoomMove", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER, "ROOM_NUMER:" + ROOM_NUMER,
				"ROOM_NUMER_OLD:" + ROOM_NUMER_OLD, "GUEST_NAME:" + GUEST_NAME });
		boolean i = coredao.GuestRoomMove(RESERVATION_NUMER, GUEST_NAME, ROOM_NUMER, ROOM_NUMER_OLD);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// ------------------------------------------------
	public String getFunctionNew(String fun) {
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.getFunctionNew()");
		String i = coredao.getFunctionNew();
		return i;
	}

	// ------------------------------------------------
	public String guestMessageText(String ROOM_NUMER, String RESERVATION_NUMER, String MESSAGE_ID, String MESSAGE_TEXT, String DATE, String TIME) {
		// XT|FL RN G# MI MT DA TI
		String pInfo = DataHelper.getParamInfo("guestMessageText", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER,
				"ROOM_NUMER:" + ROOM_NUMER, "MESSAGE_ID:" + MESSAGE_ID, "MESSAGE_TEXT:" + MESSAGE_TEXT, "DATE:" + DATE, "TIME:" + TIME });
		boolean i = coredao.guestMessageTextOnline(ROOM_NUMER, RESERVATION_NUMER, MESSAGE_ID, MESSAGE_TEXT, DATE, TIME);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// ------------------------------------------------
	// XL|FL RN G# MI  MT DA TI
	public String guestMessageTextOnline(String ROOM_NUMER, String RESERVATION_NUMER, String MESSAGE_ID, String MESSAGE_TEXT, String DATE, String TIME) { 
		String pInfo = DataHelper.getParamInfo("guestMessageTextOnline", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER,
				"ROOM_NUMER:" + ROOM_NUMER, "MESSAGE_ID:" + MESSAGE_ID, "MESSAGE_TEXT:" + MESSAGE_TEXT, "DATE:" + DATE, "TIME:" + TIME });
		boolean i = coredao.guestMessageTextOnline(ROOM_NUMER, RESERVATION_NUMER, MESSAGE_ID, MESSAGE_TEXT, DATE, TIME);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// --------------------------------------------------
	public String guestMessageDelete(String ROOM_NUMER, String RESERVATION_NUMER, String MESSAGE_ID) { // XD|FL RN G# MI
		String pInfo = DataHelper.getParamInfo("guestMessageDelete", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER,
				"ROOM_NUMER:" + ROOM_NUMER, "MESSAGE_ID:" + MESSAGE_ID });
		boolean i = coredao.guestMessageDelete(ROOM_NUMER, RESERVATION_NUMER, MESSAGE_ID);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// --------------------------------------------------
	public oBill[] guestBillReq(String fun) {
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestBillReq()");
		return coredao.BillRequest();
	}

	// -------------------------------------------------
	public String guestBillItem(String ROOM_NUMER, String RESERVATION_NUMER, String TRANSACTION_ID, String TRANSACTION_CODE,
			String ITEM_AMOUNT, String CURRENCY, String ITEM_DESC, String BALANCE_AMOUNT, String DISPLAY_FLAG, String DATE, String TIME) { // XI|FL RN G# BD BI FD DA TI
		String pInfo = DataHelper.getParamInfo("guestBillItem", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER, "ROOM_NUMER:" + ROOM_NUMER,
				"ITEM_DESC:" + ITEM_DESC, "ITEM_AMOUNT:" + ITEM_AMOUNT, "DISPLAY_FLAG:" + DISPLAY_FLAG, "DATE:" + DATE, "TIME" + TIME });
		boolean i = coredao.BillItem(ROOM_NUMER, RESERVATION_NUMER, TRANSACTION_ID, TRANSACTION_CODE, ITEM_AMOUNT, CURRENCY, ITEM_DESC, BALANCE_AMOUNT, DISPLAY_FLAG, DATE, TIME);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// --------------------------------------------------
	public String guestBillBalan(String ROOM_NUMER, String RESERVATION_NUMER, String BALANCE_AMOUNT) { // XB|FL RN G# BA
		String pInfo = DataHelper.getParamInfo("guestBillBalan", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER, "ROOM_NUMER:" + ROOM_NUMER,
				"BALANCE_AMOUNT:" + BALANCE_AMOUNT });
		boolean i = coredao.guestBillBalan(ROOM_NUMER, RESERVATION_NUMER, BALANCE_AMOUNT);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// --------------------------------------------------
	public String GuestRoomEquipment(String ROOM_NUMER, String RESERVATION_NUMER, String CLASS_SERVICE, String MSG_LIGHT_STATUS,
			String MINIBAR_RIGHT, String TV_RIGHT) { // XB|FL RN G# BA
		String pInfo = DataHelper.getParamInfo("GuestRoomEquipment", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER,
				"ROOM_NUMER:" + ROOM_NUMER, "CLASS_SERVICE:" + CLASS_SERVICE, "MSG_LIGHT_STATUS:" + MSG_LIGHT_STATUS,
				"MINIBAR_RIGHT:" + MINIBAR_RIGHT, "TV_RIGHT:" + TV_RIGHT, });
		boolean i = coredao.RoomEquipment(ROOM_NUMER, RESERVATION_NUMER, CLASS_SERVICE, MSG_LIGHT_STATUS, MINIBAR_RIGHT, TV_RIGHT);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// --------------------------------------------------
	public String GuestPostAnswer(String ROOM_NUMER, String RESERVATION_NUMER, String ANSWER_STATUS, String CLEAR_TEXT, String SEQ_NUMBER,
			String DATE, String TIME) { // XB|FL RN G# BA
		String pInfo = DataHelper.getParamInfo("GuestPostAnswer", new String[] { "RESERVATION_NUMER:" + RESERVATION_NUMER,
				"ROOM_NUMER:" + ROOM_NUMER, "ANSWER_STATUS:" + ANSWER_STATUS, "CLEAR_TEXT:" + CLEAR_TEXT, "SEQ_NUMBER:" + SEQ_NUMBER, "DATE:" + DATE,
				"TIME:" + TIME });
		boolean i = coredao.guestPostAnswer(ROOM_NUMER, RESERVATION_NUMER, ANSWER_STATUS, CLEAR_TEXT, SEQ_NUMBER, DATE, TIME);
		logger.info(pInfo + "==>" + i);
		return "" + i;
	}

	// --------------------------------------------------
	public oPost[] guestPostReq(String fun) { // XB|FL RN G# BA
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestPostReq()");
		return coredao.PostSimple();
	}
	
	public oPost[] GuestPostInternetReq(String fun) { // XB|FL RN G# BA
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestPostInternetReq()");
		return coredao.PostSimpleInternet();
	}
	
	public oPost[] GuestPostMinibarReq(String fun) { // XB|FL RN G# BA
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestPostMinibarReq()");
		return coredao.PostSimpleMinibar();
	}
	
	public oPost[] GuestPostRoomStatusReq(String fun) { // XB|FL RN G# BA
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.GuestPostRoomStatusReq()");
		return coredao.PostSimpleRoomStatus();
	}

	// --------------------------------------------------
	public oRoomData[] guestRoomEquipReq(String fun) { // XB|FL RN G# BA
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestRoomEquipReq()");
		return coredao.RoomRequest();
	}

	// --------------------------------------------------
	public oMessageDel[] guestMessageDeleteReq(String fun) { // XB|FL RN G# BA
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestMessageDeleteReq()");
		return coredao.MessageDeleteReq();
	}

	// --------------------------------------------------
	public String synDatabase(String fun) {
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.synDatabase()");
		String date = coredao.synDatabase();
		System.out.println("Core.synDatabase(): " + date);
		return date;
	}
}
