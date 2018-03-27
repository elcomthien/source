package com.elcom.eodapp.ehotel.smile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.eodapp.ehotel.common.oBill;
import com.elcom.eodapp.ehotel.common.oPost;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.DataHelper;

public class CoreSmileInterface {
	
	private static CoreSmileDao dao = DAOFactory.getInstance().getCoreSmileDao();
	private static final Logger logger = LogManager.getLogger(CoreSmileInterface.class);	
		
	public void GuestCheckin(String GUEST_NUMBER, String ROOM_NUMBER, String RESERVATION_NUMER,String GUEST_FIRST_NAME, 
			String GUEST_NAME,String GUEST_TITLE, String GUEST_ARV_DATE,String GUEST_DEPT_DATE)	{		

		String pInfo = DataHelper.getParamInfo("GuestCheckIn", new String[]
				{"GUEST_NUMER:" + GUEST_NUMBER, 
				"ROOM_NUMBER:" + ROOM_NUMBER, 
				"RESERVATION_NUMER:" + RESERVATION_NUMER, 
				"GUEST_FIRST_NAME:" + GUEST_FIRST_NAME,
				"GUEST_NAME:" + GUEST_NAME,
				"GUEST_TITLE:" + GUEST_TITLE,
				"GUEST_FIRST_NAME:" + GUEST_FIRST_NAME,
				"GUEST_ARV_DATE:" + GUEST_ARV_DATE,
				"GUEST_DEPT_DATE:" + GUEST_DEPT_DATE
				});
		logger.info(pInfo);
		boolean out = dao.GuestCheckin(GUEST_NUMBER, ROOM_NUMBER, RESERVATION_NUMER, GUEST_FIRST_NAME, GUEST_NAME, GUEST_TITLE, GUEST_ARV_DATE, GUEST_DEPT_DATE);
		logger.info(pInfo + "==>" + out);	
	}
	
	
	public boolean GuestCheckout(String RESERVATION_NUMER, String ROOM_NUMBER) {
		String pInfo = DataHelper.getParamInfo("GuestCheckOut", new String[]
				{				 
				"RESERVATION_NUMER:" + RESERVATION_NUMER, 
				"ROOM_NUMER:" + ROOM_NUMBER
				});
		logger.info(pInfo);
		boolean out = dao.GuestCheckout(RESERVATION_NUMER, ROOM_NUMBER);		
		logger.info(pInfo + "==>" + out);
		return out;
	}
	
	public boolean GuestChangeData(String GUEST_NUMBER, String RESERVATION_NUMER, String GUEST_FIRST_NAME, String GUEST_NAME, String GUEST_LANGUAGE, 
			String GUEST_TITLE, String GUEST_ARV_DATE,String GUEST_DEPT_DATE) 
	{	
		String pInfo = DataHelper.getParamInfo("GuestChangeData", new String[]
				{				 
				"GUEST_NUMER:" + GUEST_NUMBER,
				"RESERVATION_NUMER:" + RESERVATION_NUMER, 
				"GUEST_FIRST_NAME:" + GUEST_FIRST_NAME,
				"GUEST_NAME:" + GUEST_NAME,
				"GUEST_LANGUAGE:" + GUEST_LANGUAGE,
				"GUEST_TITLE:" + GUEST_TITLE,				
				"GUEST_ARV_DATE:" + GUEST_ARV_DATE,
				"GUEST_DEPT_DATE:" + GUEST_DEPT_DATE
				});
		logger.info(pInfo);
		boolean out = dao.GuestChangeData(GUEST_NUMBER, RESERVATION_NUMER, GUEST_FIRST_NAME, GUEST_NAME, GUEST_LANGUAGE, GUEST_TITLE, GUEST_ARV_DATE, GUEST_DEPT_DATE);
		logger.info(pInfo + "==>" + out);	
		return out;
	}

	public boolean GuestRoomMove(String RESERVATION_NUMBER, String ROOM_NUMBER) {
		String pInfo  = DataHelper.getParamInfo("GuestRoomMove", new String[]
				{				 
				"RESERVATION_NUMBER:" + RESERVATION_NUMBER, 
				"ROOM_NUMBER:" + ROOM_NUMBER
				});
		logger.info(pInfo);
		boolean out = dao.GuestRoomMove(RESERVATION_NUMBER, ROOM_NUMBER);
		logger.info(pInfo + "==>" + out);
		return out;
	}
	
	public void GuestRoomMoveAll(String ROOM_NUMBER, String OLD_ROOM_NUMBER) 
	{		
		String pInfo = DataHelper.getParamInfo("GuestRoomMoveAll", new String[]
				{				 
				"OLD_ROOM_NUMBER:" + OLD_ROOM_NUMBER, 
				"ROOM_NUMBER:" + ROOM_NUMBER
				});
		logger.info(pInfo);
		boolean out = dao.GuestRoomMoveAll(OLD_ROOM_NUMBER, ROOM_NUMBER);
		logger.info(pInfo + "==>" + out);
	}
	
	public void GuestBillItem(String ROOM_NUMBER, String GUEST_NUMBER, String TRANSACTION_ID, String TRANSACTION_CODE, String ITEM_AMOUNT,
			String ITEM_DESC, String BALANCE_AMOUNT, String DATE, String TIME) 
	{	
		String pInfo = DataHelper.getParamInfo("GuestBillItem", new String[]
				{
				"ROOM_NUMBER:" + ROOM_NUMBER,
				"GUEST_NUMBER:" + GUEST_NUMBER,
				"TRANSACTION_ID:" + TRANSACTION_ID,
				"TRANSACTION_CODE:" + TRANSACTION_CODE,
				"ITEM_AMOUNT:" + ITEM_AMOUNT,
				"ITEM_DESC:" + ITEM_DESC,
				"BALANCE_AMOUNT:" + BALANCE_AMOUNT,
				"DATE:" + DATE,
				"TIME:" + TIME
				});
		logger.info(pInfo);
		boolean out = dao.BillItem(ROOM_NUMBER, GUEST_NUMBER, TRANSACTION_ID, TRANSACTION_CODE, ITEM_AMOUNT, ITEM_DESC, BALANCE_AMOUNT, DATE, TIME);
		logger.info(pInfo + "==>" + out);
	}
	
	public void GuestBillBalance(String ROOM_NUMBER, String GUEST_NUMBER,String BALANCE_AMOUNT, String DATE, String TIME ) 
	{	
		String pInfo = DataHelper.getParamInfo("GuestBillBalance", new String[]
				{
				"ROOM_NUMBER:" + ROOM_NUMBER,
				"GUEST_NUMBER:" + GUEST_NUMBER,				
				"BALANCE_AMOUNT:" + BALANCE_AMOUNT,
				"DATE:" + DATE,
				"TIME:" + TIME
				});
		logger.info(pInfo);
		boolean out = dao.guestBillBalan(ROOM_NUMBER, GUEST_NUMBER, BALANCE_AMOUNT, DATE, TIME);
		logger.info(pInfo + "==>" + out);
	}
	
	public void GuestMessageText(String ROOM_NUMBER, String GUEST_NUMBER, String MESSAGE_ID, String MESSAGE_TEXT, String MESSAGE_SUBJECT, String DATE, String TIME) 
	{
		
		MESSAGE_SUBJECT = "Administrator";		
		
		if(GUEST_NUMBER == "") GUEST_NUMBER = "1000";
		if(DATE == "") DATE = getDate();
		if(TIME == "") TIME = getTime();
		
		String pInfo = DataHelper.getParamInfo("GuestMessageText", new String[]
				{
				"ROOM_NUMBER:" + ROOM_NUMBER,
				"GUEST_NUMBER:" + GUEST_NUMBER,				
				"MESSAGE_ID:" + MESSAGE_ID,
				"MESSAGE_TEXT:" + MESSAGE_TEXT,
				"MESSAGE_SUBJECT:" + MESSAGE_SUBJECT,
				"DATE:" + DATE,
				"TIME:" + TIME
				});
		logger.info(pInfo);
		boolean out = dao.guestMessageTextOnline(ROOM_NUMBER, GUEST_NUMBER, MESSAGE_ID, MESSAGE_TEXT, MESSAGE_SUBJECT, DATE, TIME);
		logger.info(pInfo + "==>" + out);
	}
	
	public void GuestMessageDelete(String MESSAGE_ID) 
	{		
		String pInfo  = DataHelper.getParamInfo("GuestMessageDelete", new String[]
				{
				"MESSAGE_ID:" + MESSAGE_ID
				});
		logger.info(pInfo);
		boolean out = dao.guestMessageDelete(MESSAGE_ID);
		logger.info(pInfo + "==>" + out);
	}
	
	/**
	 * Create list command send to PMS	
	 */
		public oBill[] getBillReqCommand() {
			logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestBillReq()");	
			oBill[] listBill = dao.BillRequest();			
			return listBill;
		}
		
		public oPost[] getPostReq() {
			logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestPostReq()");
			oPost[] listPost = dao.PostSimple();			
			return listPost;
		}
	
	//========================================================================
	
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
}
