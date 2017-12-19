package com.elcom.eodapp.ehotel.processor;

import java.util.Hashtable;

import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_OUT_PARAM;

public class XposConverter {
	
	public XposGuestObj ConvertGuestObj(Hashtable<String, String> tabData) {
		XposGuestObj obj = new XposGuestObj();
		
		String gUEST_ARV_DATE = tabData.get(XPOS_OUT_PARAM.GUEST_ROOM.value);
		String gUEST_DEPT_DATE = tabData.get(XPOS_OUT_PARAM.GUEST_RESERVATION.value);
		String gUEST_FIRST_NAME = tabData.get(XPOS_OUT_PARAM.ITEM.value);
		String gUEST_GROUP = tabData.get(XPOS_OUT_PARAM.AMOUNT.value);
		String gUEST_LANGUAGE = tabData.get(XPOS_OUT_PARAM.DEP_CODE.value);			    	
		String gUEST_LAST_NAME = tabData.get(XPOS_OUT_PARAM.FOLIO_NUMBER.value);
		String gUEST_NAME = tabData.get(XPOS_OUT_PARAM.ITEM_DATE.value);
		String gUEST_RESERVATION = tabData.get(XPOS_OUT_PARAM.ITEM_TIME.value);
		String gUEST_ROOM = tabData.get(XPOS_OUT_PARAM.ITEM_FLAG.value);
		String gUEST_TV_RIGHT = tabData.get(XPOS_OUT_PARAM.ITEM_FLAG.value);
		String gUEST_TITLE = tabData.get(XPOS_OUT_PARAM.ITEM_FLAG.value);
		String gUEST_VIP_STATUS = tabData.get(XPOS_OUT_PARAM.ITEM_FLAG.value);
		String rOOM_SHARE_FLAG = tabData.get(XPOS_OUT_PARAM.ITEM_FLAG.value);
						
		obj.setGUEST_ARV_DATE(gUEST_ARV_DATE);
		obj.setGUEST_DEPT_DATE(gUEST_DEPT_DATE);
		obj.setGUEST_FIRST_NAME(gUEST_FIRST_NAME);
		obj.setGUEST_GROUP(gUEST_GROUP);
		obj.setGUEST_LANGUAGE(gUEST_LANGUAGE);
		obj.setGUEST_LAST_NAME(gUEST_LAST_NAME);
		obj.setGUEST_NAME(gUEST_NAME);
		obj.setGUEST_RESERVATION(gUEST_RESERVATION);
		obj.setGUEST_ROOM(gUEST_ROOM);
		//GUEST_SiteCode
		obj.setGUEST_TV_RIGHT(gUEST_TV_RIGHT);
		obj.setGUEST_TITLE(gUEST_TITLE);
		obj.setGUEST_VIP_STATUS(gUEST_VIP_STATUS);
		//Room-DBSwap
		obj.setROOM_SHARE_FLAG(rOOM_SHARE_FLAG);
		return obj;
	}
	
	public XposBillObj ConvertBillObj(Hashtable<String, String> tabData) {
		XposBillObj obj = new XposBillObj();
		
		String gUEST_ROOM = tabData.get(XPOS_OUT_PARAM.GUEST_ROOM.value);
		String gUEST_RESERVATION = tabData.get(XPOS_OUT_PARAM.GUEST_RESERVATION.value);
		String iTEM = tabData.get(XPOS_OUT_PARAM.ITEM.value);
		String aMOUNT = tabData.get(XPOS_OUT_PARAM.AMOUNT.value);
		String dEP_CODE = tabData.get(XPOS_OUT_PARAM.DEP_CODE.value);			    	
		String fOLIO_NUMBER = tabData.get(XPOS_OUT_PARAM.FOLIO_NUMBER.value);
		String iTEM_DATE = tabData.get(XPOS_OUT_PARAM.ITEM_DATE.value);
		String iTEM_TIME = tabData.get(XPOS_OUT_PARAM.ITEM_TIME.value);
		String iTEM_FLAG = tabData.get(XPOS_OUT_PARAM.ITEM_FLAG.value);
						
		obj.setGUEST_ROOM(gUEST_ROOM);
		obj.setGUEST_RESERVATION(gUEST_RESERVATION);
		obj.setITEM(iTEM);
		obj.setAMOUNT(aMOUNT);
		obj.setDEP_CODE(dEP_CODE);
		obj.setFOLIO_NUMBER(fOLIO_NUMBER);
		obj.setITEM_DATE(iTEM_DATE);
		obj.setITEM_TIME(iTEM_TIME);
		obj.setITEM_FLAG(iTEM_FLAG);
		return obj;
	}
	
	
	public XposMessageObj ConvertMessageObj(Hashtable<String, String> tabData) {
		XposMessageObj obj = new XposMessageObj();
		
		String gUEST_ROOM = tabData.get(XPOS_OUT_PARAM.GUEST_ROOM.value);
		String gUEST_RESERVATION = tabData.get(XPOS_OUT_PARAM.GUEST_RESERVATION.value);
		String mESSAGE_ID = tabData.get(XPOS_OUT_PARAM.MESSAGE_ID.value);
		String mESSAGE_TEXT = tabData.get(XPOS_OUT_PARAM.MESSAGE_TEXT.value);
		String lAST_UPDATE = tabData.get(XPOS_OUT_PARAM.LAST_UPDATE.value);
						
		obj.setGUEST_ROOM(gUEST_ROOM);
		obj.setGUEST_RESERVATION(gUEST_RESERVATION);
		obj.setMESSAGE_ID(mESSAGE_ID);
		obj.setMESSAGE_TEXT(mESSAGE_TEXT);
		obj.setLAST_UPDATE(lAST_UPDATE);
		return obj;
	}
	
}
