package com.elcom.eodapp.ehotel.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.elcom.Log.FileLog;
import com.elcom.ehotel.variables.Common;
import com.elcom.eodapp.ehotel.common.oBill;
import com.elcom.eodapp.ehotel.common.oMessageDel;
import com.elcom.eodapp.ehotel.common.oPost;
import com.elcom.eodapp.ehotel.common.oRoomData;
import com.elcom.pms.opera.service.PMSOperaService;
import com.elcom.pms.opera.util.BillModel;
import com.elcom.pms.opera.util.OjFunReq;
import com.elcom.pms.opera.util.OjMessReq;
import com.elcom.pms.opera.util.PostSimple;
import com.elcom.pms.opera.util.RoomData;

public class CoreDao {
	private static PMSOperaService dao = new PMSOperaService();
	@SuppressWarnings("unused")
	private static FileLog fl = new FileLog("logcommand");
	private static String SWAP_FLAG = "";

	public CoreDao() {
		SWAP_FLAG = ifNull(SWAP_FLAG);
	}

	public String ifNull(String fid) {
		if (fid == null || fid.length() == 0)
			return "NOTHING";
		return fid;
	}

	@SuppressWarnings({ "static-access", "unused" })
	public boolean GuestCheckin(String ROOM_NUMER, String RESERVATION_NUMER, String SHARE_FLAG, String GUEST_FIRST_NAME, String GUEST_NAME,
			String GUEST_LANGUAGE, String GUEST_GROUP_NUMBER, String GUEST_TITLE, String GUEST_ARV_DATE, String GUEST_DEPT_DATE,
			String GUEST_PIN, String SWAP_FLAG) {
		String result = "", TI = "", DA = "";
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		ROOM_NUMER = ifNull(ROOM_NUMER);
		GUEST_FIRST_NAME = ifNull(GUEST_FIRST_NAME);
		GUEST_NAME = ifNull(GUEST_NAME);
		GUEST_TITLE = ifNull(GUEST_TITLE);
		GUEST_ARV_DATE = ifNull(GUEST_ARV_DATE);
		GUEST_DEPT_DATE = ifNull(GUEST_DEPT_DATE);

		GUEST_PIN = ifNull(GUEST_PIN);
		SHARE_FLAG = ifNull(SHARE_FLAG);
		SWAP_FLAG = ifNull(SWAP_FLAG);
		GUEST_LANGUAGE = ifNull(GUEST_LANGUAGE);
		GUEST_GROUP_NUMBER = ifNull(GUEST_GROUP_NUMBER);

		// RN|G#|GS|GF|GN|GL|GG|GT|GA|GD|DA|TI

		boolean kq = dao.functionGI(ROOM_NUMER, RESERVATION_NUMER, SHARE_FLAG, GUEST_FIRST_NAME, GUEST_NAME, GUEST_LANGUAGE,
				GUEST_GROUP_NUMBER, GUEST_TITLE, GUEST_ARV_DATE, GUEST_DEPT_DATE, DA, TI, SWAP_FLAG);
		return kq;
	}

	// -----------------------------------------------
	@SuppressWarnings("static-access")
	public boolean GuestCheckout(String RESERVATION_NUMER, String ROOM_NUMER, String SWAP_FLAG) // GO|FLRNG#
	{
		// String result = "";
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		SWAP_FLAG = ifNull(SWAP_FLAG);
		kq = dao.functionGO(ROOM_NUMER, RESERVATION_NUMER, SWAP_FLAG);

		return kq;
	}

	// -----------------------------------------------
	@SuppressWarnings({ "static-access", "unused" })
	public String GuestChangeData(String RESERVATION_NUMER, String ROOM_NUMER, String SHARE_FLAG, String GUEST_FIRST_NAME,
			String GUEST_NAME, String GUEST_LANGUAGE, String GUEST_GROUP_NUMBER, String GUEST_TITLE, String GUEST_ARV_DATE,
			String GUEST_DEPT_DATE, String DATE, String TIME) // GC|FLRNG#GSGFGNGLGGGTGAGDDATI
	{
		String result = "";
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		ROOM_NUMER = ifNull(ROOM_NUMER);
		SHARE_FLAG = ifNull(SHARE_FLAG);
		GUEST_FIRST_NAME = ifNull(GUEST_FIRST_NAME);
		GUEST_NAME = ifNull(GUEST_NAME);
		GUEST_LANGUAGE = ifNull(GUEST_LANGUAGE);
		GUEST_GROUP_NUMBER = ifNull(GUEST_GROUP_NUMBER);
		GUEST_TITLE = ifNull(GUEST_TITLE);
		GUEST_ARV_DATE = ifNull(GUEST_ARV_DATE);
		GUEST_DEPT_DATE = ifNull(GUEST_DEPT_DATE);
		DATE = ifNull(DATE);
		TIME = ifNull(TIME);

		boolean kq = false;
		kq = dao.functionGC(ROOM_NUMER, RESERVATION_NUMER, SHARE_FLAG, GUEST_FIRST_NAME, GUEST_NAME, GUEST_LANGUAGE, GUEST_GROUP_NUMBER,
				GUEST_TITLE, GUEST_ARV_DATE, GUEST_DEPT_DATE, DATE, TIME, SWAP_FLAG);
		return result = kq + "";
	}

	// -----------------------------------------------
	@SuppressWarnings({ "static-access", "unused" })
	public boolean GuestRoomMove(String RESERVATION_NUMER, String GUEST_NAME, String ROOM_NUMER, String ROOM_NUMER_OLD) {
		String result = "";
		boolean kq = false;
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		GUEST_NAME = ifNull(GUEST_NAME);
		ROOM_NUMER = ifNull(ROOM_NUMER);
		ROOM_NUMER_OLD = ifNull(ROOM_NUMER_OLD);

		kq = dao.functionRG(RESERVATION_NUMER, GUEST_NAME, ROOM_NUMER, ROOM_NUMER_OLD, SWAP_FLAG);
		return kq;
	}

	// -----------------------------------------------
	public String GuestRoomMoveAll() {
		String result = "";
		return result;
	}

	// -----------------------------------------------
	@SuppressWarnings("static-access")
	public oBill[] BillRequest() {
		List<BillModel> result = new ArrayList<BillModel>();
		List<oBill> kq = new ArrayList<oBill>();
		BillModel item = new BillModel();
		oBill itembill = new oBill();

		result = dao.functionXR();
		for (int i = 0; i < result.size(); i++) {
			item = result.get(i);
			itembill = new oBill();
			itembill.setRESERVATION_NUMER(item.getGTH());
			itembill.setROOM_NUMER(item.getRN());
			kq.add(itembill);
		}

		return kq.toArray(new oBill[kq.size()]);
	}

	// -----------------------------------------------
	public oBill[] BillRequestTestO() {
		List<oBill> result = new ArrayList<oBill>();
		oBill item = new oBill();
		oBill item1 = new oBill();
		item.setROOM_NUMER("101");
		item.setRESERVATION_NUMER("1234");
		item1.setROOM_NUMER("102");
		item1.setRESERVATION_NUMER("5678");
		result.add(item);
		result.add(item1);
		return result.toArray(new oBill[result.size()]);
	}

	// -----------------------------------------------
	@SuppressWarnings("static-access")
	public boolean BillItem(String ROOM_NUMER, String RESERVATION_NUMER, String TRANSACTION_ID, String TRANSACTION_CODE,
			String ITEM_AMOUNT, String CURRENCY, String ITEM_DESC, String BALANCE_AMOUNT, String DISPLAY_FLAG, String DATE, String TIME) {
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		TRANSACTION_ID = ifNull(TRANSACTION_ID);
		TRANSACTION_CODE = ifNull(TRANSACTION_CODE);
		CURRENCY = ifNull(CURRENCY);
		ITEM_DESC = ifNull(ITEM_DESC);
		ITEM_AMOUNT = ifNull(ITEM_AMOUNT);
		BALANCE_AMOUNT = ifNull(BALANCE_AMOUNT);
		DISPLAY_FLAG = ifNull(DISPLAY_FLAG);
		DATE = ifNull(DATE);
		TIME = ifNull(TIME);

		kq = dao.functionXI(ROOM_NUMER, RESERVATION_NUMER, TRANSACTION_ID, TRANSACTION_CODE, ITEM_AMOUNT, CURRENCY, ITEM_DESC,
				BALANCE_AMOUNT, DISPLAY_FLAG, DATE, TIME, SWAP_FLAG);
		return kq;
	}

	// ------------------------------------------------
	@SuppressWarnings("static-access")
	public boolean guestBillBalan(String ROOM_NUMER, String RESERVATION_NUMER, String BALANCE_AMOUNT) { // XB|FL RN G# BA
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		BALANCE_AMOUNT = ifNull(BALANCE_AMOUNT);

		kq = dao.functionXB(ROOM_NUMER, RESERVATION_NUMER, BALANCE_AMOUNT, SWAP_FLAG);
		return kq;
	}

	// ------------------------------------------------
	@SuppressWarnings({ "unused", "static-access" })
	public boolean guestMessageTextOnline(String ROOM_NUMER, String RESERVATION_NUMER, String MESSAGE_ID, String MESSAGE_TEXT, String DATE,
			String TIME) { // XL|FL RN G# MI MT DA TI
		String result = "";
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		MESSAGE_ID = ifNull(MESSAGE_ID);
		MESSAGE_TEXT = ifNull(MESSAGE_TEXT);
		DATE = ifNull(DATE);
		TIME = ifNull(TIME);

		kq = dao.functionXL(ROOM_NUMER, RESERVATION_NUMER, MESSAGE_ID, MESSAGE_TEXT, DATE, TIME, SWAP_FLAG);
		return kq;
	}

	// ------------------------------------------------
	@SuppressWarnings({ "unused", "static-access" })
	public boolean guestMessageDelete(String ROOM_NUMER, String RESERVATION_NUMER, String MESSAGE_ID) { // XD|FL RN G# MI
		String result = "";
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		MESSAGE_ID = ifNull(MESSAGE_ID);

		kq = dao.functionXD(ROOM_NUMER, RESERVATION_NUMER, MESSAGE_ID, SWAP_FLAG);
		return kq;
	}

	// ------------------------------------------------
	@SuppressWarnings("static-access")
	public oMessageDel[] MessageDeleteReq() {
		List<OjMessReq> result = new ArrayList<OjMessReq>();
		List<oMessageDel> kq = new ArrayList<oMessageDel>();
		OjMessReq item = new OjMessReq();
		oMessageDel itemMsgDel = new oMessageDel();

		result = dao.getFunctionXDReq();
		for (int i = 0; i < result.size(); i++) {
			item = result.get(i);
			itemMsgDel = new oMessageDel();
			itemMsgDel.setROOM_NUMBER(item.getRoom_number());
			itemMsgDel.setRESERVATION_NUMER(item.getReservation_number());
			itemMsgDel.setMESSAGE_ID(item.getMessage_id());
			kq.add(itemMsgDel);
		}
		return kq.toArray(new oMessageDel[kq.size()]);
	}

	// --POST ---------------------------------------
	@SuppressWarnings("unused")
	public String Posting(String ROOM_NUMER) { // RN-TA-P#-DA-TI;RN-TA-P#-DA-TI //,String POSTING_TYPES,String TOTAL_POSTING,String
												// DATE,String TIME
		String result = "";
		String kq = "101-2-1234-000915-12345";
		return result = kq + "";
	}

	@SuppressWarnings("static-access")
	public oPost[] PostSimple() {
		List<PostSimple> result = new ArrayList<PostSimple>();
		List<oPost> kq = new ArrayList<oPost>();
		PostSimple item = new PostSimple();
		oPost itemPost = new oPost();

		result = dao.functionPS();
		for (int i = 0; i < result.size(); i++) {
			item = result.get(i);
			itemPost = new oPost();
			itemPost.setROOM_NUMBER(item.getRN());
			itemPost.setDATE(item.getDA());
			itemPost.setTIME(item.getTI());
			itemPost.setDIALED_DIGITS(item.getDD());
			itemPost.setDURATION(item.getDU());
			itemPost.setMINIBAR_ARTICLE(item.getMA());
			itemPost.setNUMOF_ARTICLE(item.getMTH());
			itemPost.setTAX_PULSE(item.getMP());
			itemPost.setPOSTING_TYPE(item.getPT());
			itemPost.setSALES_OUTLET(item.getSO());
			itemPost.setPOST_AMOUNT(item.getTA());
			itemPost.setROOM_STATUS("");
			itemPost.setPOST_SEQ_NUMBER(item.getPTH());
			itemPost.setPOST_CALL_TYPE(item.getPC());
			itemPost.setCLEAR_TEXT(item.getCT());

			kq.add(itemPost);
		}
		return kq.toArray(new oPost[kq.size()]);
	}

	@SuppressWarnings({ "unused", "static-access" })
	public boolean guestPostAnswer(String ROOM_NUMER, String RESERVATION_NUMER, String ANSWER_STATUS, String CLEAR_TEXT, String SEQ_NUMBER,
			String DATE, String TIME) { // XD|FL RN G# MI
		String result = "";
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		ANSWER_STATUS = ifNull(ANSWER_STATUS);
		CLEAR_TEXT = ifNull(CLEAR_TEXT);
		SEQ_NUMBER = ifNull(SEQ_NUMBER);
		DATE = ifNull(DATE);
		TIME = ifNull(TIME);

		kq = dao.functionPA(ANSWER_STATUS, CLEAR_TEXT, DATE, SEQ_NUMBER, ROOM_NUMER, TIME, RESERVATION_NUMER);
		return kq;
	}

	// ---RE - Room equipment------------------------------------
	@SuppressWarnings({ "static-access", "unused" })
	public boolean RoomEquipment(String ROOM_NUMER, String RESERVATION_NUMER, String CLASS_SERVICE, String MSG_LIGHT_STATUS,
			String MINIBAR_RIGHT, String TV_RIGHT) {// RE|FL RN G# CS ML MR TV
		String result = "";
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);
		CLASS_SERVICE = ifNull(CLASS_SERVICE);
		MSG_LIGHT_STATUS = ifNull(MSG_LIGHT_STATUS);
		MINIBAR_RIGHT = ifNull(MINIBAR_RIGHT);
		TV_RIGHT = ifNull(TV_RIGHT);

		kq = dao.functionRE(ROOM_NUMER, RESERVATION_NUMER, CLASS_SERVICE, MSG_LIGHT_STATUS, MINIBAR_RIGHT, TV_RIGHT);
		return kq;
	}

	@SuppressWarnings("static-access")
	public oRoomData[] RoomRequest() {
		List<RoomData> result = new ArrayList<RoomData>();
		List<oRoomData> kq = new ArrayList<oRoomData>();
		RoomData item = new RoomData();
		oRoomData itemRoom = new oRoomData();

		result = dao.functionRR();
		for (int i = 0; i < result.size(); i++) {
			item = result.get(i);
			itemRoom = new oRoomData();
			itemRoom.setROOM_NUMBER(item.getRN());
			itemRoom.setCLEAR_TEXT(item.getCT());
			itemRoom.setROOM_STATUS(item.getRS());
			itemRoom.setVOICE_MAIL(item.getVM());
			itemRoom.setUSERID(item.getID());
		}
		return kq.toArray(new oRoomData[kq.size()]);
	}

	// ------------------------------------------------
	@SuppressWarnings("unused")
	public String synDatabase() // date;time
	{
		String result = "", dates, time;

		Date today = null;
		if (today == null)
			today = new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat = new SimpleDateFormat(Common.PMSFormatDate + Common.eHotelRegex + Common.PMSFormatTime);
		result = timeFormat.format(today.getTime());
		return result;
	}

	// ------------------------------------------------
	public String getFunctionNew() {
		String fun = "GI|FLRNG#GSGFGNGLGGGTGAGDDATISF;"
				+ // check in 1
				"GC|FLRNG#GSGFGNGLGGGTGAGDDATI;"
				+ // change guest 2
				"GO|FLRNG#SF;"
				+ // check out 3
				"XL|FLRNG#MIMTDATI;"
				+ // Guest message onlie 4
				"XT|FLRNG#MIMTDATI;"
				+ // Guest message 5
				"XD|FLRNG#MI;"
				+ // delete message 6
				"XR|FLRNG#;"
				+ // bill requeest 7
				"XI|FLRNG#BDBIFDDATI;"
				+ // Guest bill item 8
				"XB|FLRNG#BA;"
				+ // Guest bill balance 9
				"PS|FLDADDDUPTRNTATI;" + "PR|FLDAG#GNPIPMRNP#TATI;" + "PL|FLG#GNP#RNGAGD;" + "PA|FLASCTP#RNTI;"
				+ "RE|FLRNCSCTDNIDMLMRPPPURSTVVM;" + "WR|FLDARNTI;" + "WC|FLDARNTI;" + "WA|FLASDARNTI;";
		return fun;
	}

	@SuppressWarnings("static-access")
	public oPost[] PostSimpleInternet() {
		List<PostSimple> result = new ArrayList<PostSimple>();
		List<oPost> kq = new ArrayList<oPost>();
		PostSimple item = new PostSimple();
		oPost itemPost = new oPost();

		result = dao.functionPSInternet();
		for (int i = 0; i < result.size(); i++) {
			item = result.get(i);
			itemPost = new oPost();
			itemPost.setROOM_NUMBER(item.getRN());
			itemPost.setDATE(item.getDA());
			itemPost.setTIME(item.getTI());
			itemPost.setDIALED_DIGITS(item.getDD());
			itemPost.setDURATION(item.getDU());
			itemPost.setMINIBAR_ARTICLE(item.getMA());
			itemPost.setNUMOF_ARTICLE(item.getMTH());
			itemPost.setTAX_PULSE(item.getMP());
			itemPost.setPOSTING_TYPE(item.getPT());
			itemPost.setSALES_OUTLET(item.getSO());
			itemPost.setPOST_AMOUNT(item.getTA());
			itemPost.setROOM_STATUS("");
			itemPost.setPOST_SEQ_NUMBER(item.getPTH());
			itemPost.setPOST_CALL_TYPE(item.getPC());
			itemPost.setCLEAR_TEXT(item.getCT());

			kq.add(itemPost);
		}
		return kq.toArray(new oPost[kq.size()]);
	}

	@SuppressWarnings("static-access")
	public oPost[] PostSimpleMinibar() {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		List<oPost> kq = new ArrayList<oPost>();
		HashMap<String, String> item = new HashMap<String, String>();
		oPost itemPost = new oPost();

		result = dao.functionPSMinibar();
		for (int i = 0; i < result.size(); i++) {
			item = result.get(i);
			itemPost = new oPost();
			itemPost.setROOM_NUMBER(item.get("folionum"));
			itemPost.setDATE(item.get("date"));
			itemPost.setTIME(item.get("time"));
			itemPost.setDIALED_DIGITS("");
			itemPost.setDURATION("");
			itemPost.setMINIBAR_ARTICLE(item.get("code"));
			itemPost.setNUMOF_ARTICLE(item.get("amount"));
			itemPost.setTAX_PULSE("");
			itemPost.setPOSTING_TYPE("");
			itemPost.setSALES_OUTLET("");
			itemPost.setPOST_AMOUNT("");
			itemPost.setROOM_STATUS("");
			itemPost.setPOST_SEQ_NUMBER("");
			itemPost.setPOST_CALL_TYPE("");
			itemPost.setCLEAR_TEXT("");

			kq.add(itemPost);
		}
		return kq.toArray(new oPost[kq.size()]);
	}

	@SuppressWarnings("static-access")
	public oPost[] PostSimpleRoomStatus() {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		List<oPost> kq = new ArrayList<oPost>();
		HashMap<String, String> item = new HashMap<String, String>();
		oPost itemPost = new oPost();

		result = dao.functionPSRoomStatus();
		for (int i = 0; i < result.size(); i++) {
			item = result.get(i);
			itemPost = new oPost();
			itemPost.setROOM_NUMBER(item.get("folionum"));
			itemPost.setDATE(item.get("date"));
			itemPost.setTIME(item.get("time"));
			itemPost.setDIALED_DIGITS("");
			itemPost.setDURATION("");
			itemPost.setMINIBAR_ARTICLE(item.get("code"));
			itemPost.setNUMOF_ARTICLE(item.get("amount"));
			itemPost.setTAX_PULSE("");
			itemPost.setPOSTING_TYPE("");
			itemPost.setSALES_OUTLET("");
			itemPost.setPOST_AMOUNT("");
			itemPost.setROOM_STATUS(item.get("status"));

			itemPost.setPOST_SEQ_NUMBER("");
			itemPost.setPOST_CALL_TYPE("");
			itemPost.setCLEAR_TEXT("");

			kq.add(itemPost);
		}
		return kq.toArray(new oPost[kq.size()]);
	}

	@SuppressWarnings("static-access")
	public static void main(String arg[]) {
		PMSOperaService dao = new PMSOperaService();
		List<OjFunReq> listReq = dao.getFunctionReq();
		for (OjFunReq req : listReq) {
			String key = req.getKey();
			int value = req.getValue();
			System.out.println(key + "-" + value);
		}
	}
}
