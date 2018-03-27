package com.elcom.eodapp.ehotel.smile;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.elcom.ehotel.variables.Common;
import com.elcom.eodapp.ehotel.common.oBill;
import com.elcom.eodapp.ehotel.common.oPost;
import com.elcom.pms.smile.service.PMSSmileService;
import com.elcom.pms.smile.util.BillModel;
import com.elcom.pms.smile.util.PostSimple;

public class CoreSmileDao {
	private static PMSSmileService dao = new PMSSmileService();
	private static String SWAP_FLAG = "";
	
	public CoreSmileDao() {
		SWAP_FLAG = ifNull(SWAP_FLAG);
	}
	
	public String ifNull(String fid)
	{
		if (fid == null || fid.length() == 0) return "NOTHING";
		return fid;
	}
	
	@SuppressWarnings("static-access")
	public boolean GuestCheckin(String GUEST_NUMER, String ROOM_NUMER,String RESERVATION_NUMER,String GUEST_FIRST_NAME,String GUEST_NAME,
			String GUEST_TITLE,String GUEST_ARV_DATE,String GUEST_DEPT_DATE)
	{		
		GUEST_NUMER = ifNull(GUEST_NUMER);
		ROOM_NUMER = ifNull(ROOM_NUMER);
		GUEST_FIRST_NAME = ifNull(GUEST_FIRST_NAME);
		GUEST_NAME = ifNull(GUEST_NAME);
		GUEST_TITLE = ifNull(GUEST_TITLE);
		GUEST_ARV_DATE = ifNull(GUEST_ARV_DATE);
		GUEST_DEPT_DATE = ifNull(GUEST_DEPT_DATE);		
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);		
		
		boolean rs = dao.functionGI(GUEST_NUMER, ROOM_NUMER, GUEST_FIRST_NAME, GUEST_NAME, GUEST_TITLE, GUEST_ARV_DATE, GUEST_DEPT_DATE, RESERVATION_NUMER,"");
		return rs;
	}
	//-----------------------------------------------
	@SuppressWarnings("static-access")
	public boolean GuestCheckout(String RESERVATION_NUMER, String ROOM_NUMBER) 
	{				
		String iParam = ROOM_NUMBER;
		if(ROOM_NUMBER == "")
			iParam = RESERVATION_NUMER;		
		boolean kq = dao.functionGO(iParam);
		return kq;
	}
	//-----------------------------------------------
	@SuppressWarnings("static-access")
	public boolean GuestChangeData(String GUEST_NUMER,String RESERVATION_NUMER,
			String GUEST_FIRST_NAME,String GUEST_NAME,String GUEST_LANGUAGE,
            String GUEST_TITLE,String GUEST_ARV_DATE,String GUEST_DEPT_DATE)  
	{
//		String result = "";
		GUEST_NUMER = ifNull(GUEST_NUMER);
		GUEST_FIRST_NAME = ifNull(GUEST_FIRST_NAME);
		GUEST_NAME = ifNull(GUEST_NAME);
		GUEST_TITLE = ifNull(GUEST_TITLE);
		GUEST_ARV_DATE = ifNull(GUEST_ARV_DATE);
		GUEST_LANGUAGE = ifNull(GUEST_LANGUAGE);
		GUEST_DEPT_DATE  = ifNull(GUEST_DEPT_DATE);		
		RESERVATION_NUMER = ifNull(RESERVATION_NUMER);		
		
		boolean kq = false;
		kq = dao.functionGC(GUEST_NUMER, GUEST_FIRST_NAME, GUEST_NAME, GUEST_TITLE, GUEST_ARV_DATE, GUEST_LANGUAGE, GUEST_DEPT_DATE, RESERVATION_NUMER,"","");
		return kq;
	}
	
	@SuppressWarnings("static-access")
	public boolean GuestRoomMove(String GUEST_RESERVATION,String ROOM_NUMBER)
	{
//		String result = "";
		boolean kq = false;		
		GUEST_RESERVATION = ifNull(GUEST_RESERVATION);
		ROOM_NUMBER = ifNull(ROOM_NUMBER);
		 
		kq = dao.functionRG(GUEST_RESERVATION, ROOM_NUMBER);
		return  kq;
	}
		
	//-----------------------------------------------
	@SuppressWarnings("static-access")
	public boolean GuestRoomMoveAll(String OLD_ROOM_NUMBER, String ROOM_NUMBER)
	{			
		OLD_ROOM_NUMBER = ifNull(OLD_ROOM_NUMBER);
		ROOM_NUMBER = ifNull(ROOM_NUMBER);
		 
		boolean kq = dao.functionRR(OLD_ROOM_NUMBER, ROOM_NUMBER);
		return kq;
	}
	//-----------------------------------------------
	
	@SuppressWarnings("static-access")
	public oBill[]  BillRequest() 
	{
		List<BillModel>  result = new ArrayList<BillModel>();
		List<oBill> kq = new ArrayList<oBill>();
		BillModel item = new BillModel();
		oBill itembill = new oBill();
		
		result = dao.functionXR();
		for (int i = 0 ; i < result.size() ; i++)
		{
			item = result.get(i);
			itembill = new oBill();
			itembill.setROOM_NUMER(item.getRN());
			itembill.setGUEST_NUMER(item.getGTH());			
			kq.add(itembill);
		}
		return kq.toArray(new oBill[kq.size()] );
	}
	
	
	//-----------------------------------------------	
		
	@SuppressWarnings("static-access")
	public boolean BillItem(String ROOM_NUMER,String GUEST_NUMER, String TRANSACTION_ID, String TRANSACTION_CODE,String ITEM_AMOUNT,String ITEM_DESC,
							String BALANCE_AMOUNT, String DATE, String TIME)
	{
//		String result = "";
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		GUEST_NUMER = ifNull(GUEST_NUMER);
		TRANSACTION_ID = ifNull(TRANSACTION_ID);
		TRANSACTION_CODE = ifNull(TRANSACTION_CODE);
		ITEM_AMOUNT = ifNull(ITEM_AMOUNT);
		ITEM_DESC = ifNull(ITEM_DESC);
		BALANCE_AMOUNT = ifNull(BALANCE_AMOUNT);
		DATE = ifNull(DATE);
		TIME = ifNull(TIME);
		
		kq = dao.functionXI(ROOM_NUMER, GUEST_NUMER, TRANSACTION_ID, TRANSACTION_CODE, ITEM_AMOUNT, ITEM_DESC, BALANCE_AMOUNT, DATE, TIME);
		return kq;
	}
	
	@SuppressWarnings("static-access")
	public boolean guestBillBalan(String ROOM_NUMER,String GUEST_NUMER,String BALANCE_AMOUNT, String DATE, String TIME)
	{
//		String result = "";
		boolean kq = false;
		ROOM_NUMER = ifNull(ROOM_NUMER);
		GUEST_NUMER = ifNull(GUEST_NUMER);
		BALANCE_AMOUNT = ifNull(BALANCE_AMOUNT);
		DATE = ifNull(DATE);
		TIME = ifNull(TIME);
		
		kq = dao.functionXB(ROOM_NUMER, GUEST_NUMER, BALANCE_AMOUNT, DATE, TIME);
		return kq;
	} 
	
	//------------------------------------------------
	@SuppressWarnings("static-access")
	public boolean guestMessageTextOnline(String ROOM_NUMER,String GUEST_NUMER,
			String MESSAGE_ID,String MESSAGE_TEXT, String MESSAGE_SUBJECT, String DATE,String TIME)
	{  		
		boolean kq = false;	
		ROOM_NUMER = ifNull(ROOM_NUMER);
		GUEST_NUMER = ifNull(GUEST_NUMER);
		MESSAGE_ID = ifNull(MESSAGE_ID);
		MESSAGE_TEXT = ifNull(MESSAGE_TEXT);
		MESSAGE_SUBJECT = ifNull(MESSAGE_SUBJECT);
		DATE = ifNull(DATE);
		TIME = ifNull(TIME);
		
		kq = dao.functionXL(ROOM_NUMER, GUEST_NUMER, MESSAGE_ID, MESSAGE_TEXT, DATE, TIME, MESSAGE_SUBJECT);		
		return kq;
	}
	//------------------------------------------------	
	@SuppressWarnings("static-access")
	public boolean guestMessageDelete(String MESSAGE_ID)
	{ 		
//		String result = "";
		boolean kq = false;		
		
		MESSAGE_ID = ifNull(MESSAGE_ID);		
		kq = dao.functionXD(MESSAGE_ID);
		return kq;
	}
	
	//--POST ---------------------------------------	
		
		@SuppressWarnings("static-access")
		public oPost[] PostSimple() 
		{
			List<PostSimple>  result = new ArrayList<PostSimple>();
			List<oPost> kq = new ArrayList<oPost>();
			PostSimple item = new PostSimple();
			oPost itemPost = new oPost();
			
			result = dao.functionPS();
			for (int i = 0 ; i < result.size() ; i++)
			{
				item = result.get(i);
				itemPost = new oPost();
				itemPost.setROOM_NUMBER(item.getRN()); 				
				itemPost.setTOTAL_AMOUNT(item.getTA());
				itemPost.setPOST_SEQ_NUMBER(item.getPTH());
				itemPost.setDATE(item.getDA());
				itemPost.setTIME(item.getTI());
				itemPost.setPOST_TEXT(item.getCT());
				itemPost.setCURRENCY_TYPE(item.getCU());			
				
				kq.add(itemPost);
			}
			return kq.toArray(new oPost[kq.size()] );
		}			
		
	//------------------------------------------------
	@SuppressWarnings("unused")
	public String synDatabase()   //date;time
	{
		String result = "" ,dates,time;
				
		Date today = null;
		if (today == null) today=new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat= new SimpleDateFormat(Common.PMSFormatDate + Common.eHotelRegex + Common.PMSFormatTime);
		result = timeFormat.format(today.getTime());
		return result;
	}	
}
