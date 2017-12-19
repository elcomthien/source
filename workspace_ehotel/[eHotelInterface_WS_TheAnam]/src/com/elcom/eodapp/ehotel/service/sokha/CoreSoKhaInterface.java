package com.elcom.eodapp.ehotel.service.sokha;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.eodapp.ehotel.common.oBill;
import com.elcom.eodapp.ehotel.common.oPost;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.DataHelper;

public class CoreSoKhaInterface {
	private static final Logger logger = LogManager.getLogger(CoreSoKhaInterface.class);
	private static CoreSoKhaDao coredao = DAOFactory.getInstance().getCoreSoKhaDao();
		
	public boolean GuestCheckin(String GUEST_ARV_DATE, String GUEST_DEPT_DATE,String GUEST_FIRST_NAME, String GUEST_LAST_NAME, String GUEST_NAME,
			String GUEST_RESERVATION, String GUEST_ROOM, String GUEST_LANGUAGE,String GUEST_GROUP, String GUEST_TITLE, String GUEST_VIP_STATUS,
			String GUEST_TV_RIGHT, String GUEST_VIDEO_RIGHT,String GUEST_BIRTHDAY, String ROOM_SHARE_FLAG) {
		

		String GUEST_PIN = "";
		String DATE = "";
		String TIME = "";
		String SWAP_FLAG = "";
		
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.GuestCheckin(" + "RESERVATION_NUMER:"+ GUEST_RESERVATION + ",ROOM_NUMER:" + GUEST_ROOM + 
				  ",GUEST_ARV_DATE:" + GUEST_ARV_DATE + ",GUEST_DEPT_DATE:" + GUEST_DEPT_DATE +  
				  ",GUEST_FIRST_NAME:"+ GUEST_FIRST_NAME + ",GUEST_LAST_NAME:"+ GUEST_LAST_NAME + ",GUEST_NAME:" + GUEST_NAME + 
			      ",GUEST_LANGUAGE:" + GUEST_LANGUAGE + ",GUEST_GROUP:" + GUEST_GROUP + "GUEST_TITLE:," + GUEST_TITLE +
			      ",GUEST_VIP_STATUS:" + GUEST_VIP_STATUS + ",GUEST_TV_RIGHT:" + GUEST_TV_RIGHT + ", GUEST_VIDEO_RIGHT:" + GUEST_VIDEO_RIGHT +
			      ",GUEST_BIRTHDAY" + GUEST_BIRTHDAY + ",SHARE_FLAG:" + ROOM_SHARE_FLAG + 
			      ",GUEST_PIN:" + GUEST_PIN + ",DATE:" + DATE + ",TIME:" + TIME + ",SWAP_FLAG:" + SWAP_FLAG +  ")" );		
		
				  
	 // RN|G#|GS|GF|GN|GL|GG|GT|GA|GD|DA|TI
	  if (GUEST_DEPT_DATE == null || GUEST_DEPT_DATE.length() == 0) GUEST_DEPT_DATE = DATE;
	  boolean isSucc = coredao.GuestCheckin(GUEST_ROOM,GUEST_RESERVATION,ROOM_SHARE_FLAG,GUEST_FIRST_NAME,GUEST_NAME,
				GUEST_LANGUAGE,GUEST_GROUP,GUEST_TITLE,GUEST_ARV_DATE,GUEST_DEPT_DATE,GUEST_PIN, SWAP_FLAG);
	  return isSucc;
	}
	
	
	public boolean GuestCheckout(String GUEST_RESERVATION, String GUEST_ROOM,String ROOM_SHARE_FLAG) {
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.GuestCheckout(GUEST_RESERVATION:"+ GUEST_RESERVATION + ",GUEST_ROOM:" + GUEST_ROOM + ",ROOM_SHARE_FLAG:" + ROOM_SHARE_FLAG+ ")");			

		String SWAP_FLAG = "";
		boolean isSucc = coredao.GuestCheckout(GUEST_RESERVATION,GUEST_ROOM,SWAP_FLAG);
		return isSucc;
		/*if(isSucc) 
			  return ACK_SOKHA.SUCC.value;	  
		  else return ACK_SOKHA.FAIL.value;*/
	}
	
	public boolean GuestRoomAndChangeMove(String GUEST_EXROOM, String GUEST_RESERVATION,String GUEST_ROOM, String ROOM_EXSHARE_FLAG, String ROOM_SHARE_FLAG,
			String GUEST_ARV_DATE, String GUEST_DEPT_DATE,String GUEST_FIRST_NAME, String GUEST_LAST_NAME, String GUEST_NAME,
			String GUEST_LANGUAGE,String GUEST_GROUP, String GUEST_TITLE, String GUEST_VIP_STATUS,
			String GUEST_TV_RIGHT, String GUEST_VIDEO_RIGHT) {
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.GuestRoomMoveAndChange(GUEST_RESERVATION:"+ GUEST_RESERVATION + 
				",GUEST_EXROOM:" + GUEST_EXROOM +",GUEST_ROOM:" + GUEST_ROOM + ",ROOM_EXSHARE_FLAG:" + ROOM_EXSHARE_FLAG + ",ROOM_SHARE_FLAG:" + ROOM_SHARE_FLAG+
				",GUEST_ARV_DATE:" + GUEST_ARV_DATE + ",GUEST_DEPT_DATE:" + GUEST_DEPT_DATE +  
				",GUEST_FIRST_NAME:"+ GUEST_FIRST_NAME + ",GUEST_LAST_NAME:"+ GUEST_LAST_NAME + ",GUEST_NAME:" + GUEST_NAME + 
			    ",GUEST_LANGUAGE:" + GUEST_LANGUAGE + ",GUEST_GROUP:" + GUEST_GROUP + "GUEST_TITLE:," + GUEST_TITLE +
			    ",GUEST_VIP_STATUS:" + GUEST_VIP_STATUS + ",GUEST_TV_RIGHT:" + GUEST_TV_RIGHT + ", GUEST_VIDEO_RIGHT:" + GUEST_VIDEO_RIGHT +		      
			      
				")");
		
		boolean isSucc = coredao.GuestRoomMoveAndChange(GUEST_EXROOM, GUEST_RESERVATION, GUEST_ROOM, ROOM_EXSHARE_FLAG, ROOM_SHARE_FLAG, GUEST_ARV_DATE, GUEST_DEPT_DATE, GUEST_FIRST_NAME, GUEST_LAST_NAME, GUEST_NAME, GUEST_LANGUAGE, GUEST_GROUP, GUEST_TITLE, GUEST_VIP_STATUS, GUEST_TV_RIGHT, GUEST_VIDEO_RIGHT);
		return isSucc;
	}

	public boolean GuestRoomMove(String GUEST_EXROOM, String GUEST_RESERVATION,String GUEST_ROOM, String ROOM_EXSHARE_FLAG, String ROOM_SHARE_FLAG) {
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.GuestRoomMove(GUEST_RESERVATION:"+ GUEST_RESERVATION + 
				",GUEST_EXROOM:" + GUEST_EXROOM +",GUEST_ROOM:" + GUEST_ROOM + 
				",ROOM_EXSHARE_FLAG:" + ROOM_EXSHARE_FLAG + ",ROOM_SHARE_FLAG:" + ROOM_SHARE_FLAG+ ")");

		boolean isSucc = coredao.GuestRoomMove(GUEST_EXROOM, GUEST_RESERVATION, GUEST_ROOM, ROOM_EXSHARE_FLAG, ROOM_SHARE_FLAG);
		return isSucc;
	}
	
	public boolean GuestMessageTextOnline(String GUEST_ROOM, String GUEST_RESERVATION,String MESSAGE_ID, String MESSAGE_TEXT, String LAST_UPDATE) {
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.GuestMessageTextOnline(GUEST_RESERVATION:"+ GUEST_RESERVATION + 
				",GUEST_ROOM:" + GUEST_ROOM + ",MESSAGE_ID:" + MESSAGE_ID + ",MESSAGE_TEXT:" + MESSAGE_TEXT+ 
				",LAST_UPDATE:" + LAST_UPDATE + ")");
		String DATE = "";
		String TIME = "";
		
		if(LAST_UPDATE.length() > 0) {
			java.util.Date date= DataHelper.parseDate(LAST_UPDATE, "yyy-MM-dd HH:mm:ss");
			DATE = DataHelper.format(date, "yyy-MM-dd");
			TIME = DataHelper.format(date, "HH:mm:ss");
		}
		boolean res = coredao.guestMessageTextOnline(GUEST_ROOM, GUEST_RESERVATION, MESSAGE_ID, MESSAGE_TEXT, DATE, TIME);
		return res;
	}
	
	public boolean DeleteBillItem(String ROOM_NUMER,String RESERVATION_NUMER)
	{ 
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.DeleteBillItem(GUEST_RESERVATION:"+ RESERVATION_NUMER +	",GUEST_ROOM:" + ROOM_NUMER  + ")");		
		return coredao.BillDelete(ROOM_NUMER, RESERVATION_NUMER);
	}
	
	public boolean GuestBillItem(String ROOM_NUMER,String RESERVATION_NUMER,String ITEM_DESC,String ITEM_AMOUNT,
            String ITEM_FLAG,String DATE,String TIME)
	{ 
		String DISPLAY_FLAG = "";
		if(ITEM_FLAG.equals("Y")) 
			DISPLAY_FLAG = "1"; 
		else DISPLAY_FLAG = "0";
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.GuestBillItem(GUEST_RESERVATION:"+ RESERVATION_NUMER + 
				",GUEST_ROOM:" + ROOM_NUMER + ",ITEM_DESC:" + ITEM_DESC + ",ITEM_AMOUNT:" + ITEM_AMOUNT+ ",ITEM_FLAG:" + ITEM_FLAG +
				",DISPLAY_FLAG:" + DISPLAY_FLAG + ",DATE:" + DATE + ",TIME:" + TIME + ")");
		return coredao.BillItem(ROOM_NUMER,RESERVATION_NUMER,ITEM_DESC,ITEM_AMOUNT,	DISPLAY_FLAG,DATE,TIME);
	} 
	//--------------------------------------------------
	public boolean GuestBillBalan(String ROOM_NUMER,String RESERVATION_NUMER,String BALANCE_AMOUNT)
	{  //XB|FL RN G# BA
		return coredao.guestBillBalan(ROOM_NUMER,RESERVATION_NUMER,BALANCE_AMOUNT);
	}
	
	//========================================================================
	public oBill[] guestBillReq(String fun){ 
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestBillReq()");	
		return coredao.BillRequest();
	}	

	public oPost[] guestPostReq(String fun)
	{  //XB|FL RN G# BA
		logger.info("Ip: " + DataHelper.getIpRemote() + " - In Core.guestPostReq()");	
		return coredao.PostSimple();
	}
}
