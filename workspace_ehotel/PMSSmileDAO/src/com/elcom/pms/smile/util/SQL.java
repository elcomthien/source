package com.elcom.pms.smile.util;

public class SQL {

	public static final String FUNCTION_XR = "begin FUNCTION_XR(?); end;";
	public static final String FUNCTION_PS = "begin FUNCTION_PS(?); end;";

	public static final String COM_LOG_STA = "begin COM_LOG_STA(?); end;";
	public static final String COM_NEW_GUEST = "begin COM_NEW_GUEST(?,?,?,?,?,?,?,?,?); end;";
	public static final String COM_UPDATE_GUEST = "begin COM_UPDATE_GUEST(?,?,?,?,?,?,?,?,?,?); end;";
	public static final String COM_DELETE_GUEST = "begin COM_DELETE_GUEST(?); end;"; 
	// check out khach hang
	public static final String COM_DELETE_ROOM = "begin COM_DELETE_ROOM(?); end;";//---------
	
	public static final String COM_MOVE_GUEST = "begin COM_MOVE_GUEST(?,?); end;";
	public static final String COM_MOVEALL_GUEST = "begin COM_MOVEALL_GUEST(?,?); end;";
	public static final String COM_NEW_MESSAGE = "begin COM_NEW_MESSAGE(?,?,?,?,?,?,?); end;";
	public static final String COM_DELETE_MESSAGE = "begin COM_DELETE_MESSAGE(?); end;";
	public static final String COM_NEW_BILL_ITEM = "begin COM_NEW_BILL_ITEM(?,?,?,?,?,?,?,?,?); end;";
	public static final String COM_UPDATE_BILL_ITEM = "begin COM_UPDATE_BILL_ITEM(?); end;";
	public static final String COM_NEW_PMS_ITEM = "begin COM_NEW_PMS_ITEM(?,?,?,?,?,?,?); end;";
	public static final String COM_NEW_PMS_ITEMHK = "begin COM_NEW_PMS_ITEMHK(?,?,?); end;";
	public static final String COM_NEW_PMS_MENU = "begin COM_NEW_PMS_MENU(?,?,?,?); end;";
	public static final String COM_UPDATE_PMS_MENU = "begin COM_UPDATE_PMS_MENU(?,?,?); end;";
}
