package elcom.com.core.read;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 03/2011
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author Hoavk@
 * @version 1.0
 */
public class CMDMap {
	@SuppressWarnings("rawtypes")
	protected static Map ICMDMap = new HashMap();
	@SuppressWarnings("rawtypes")
	protected static Map IParamMap = new HashMap();
	@SuppressWarnings("rawtypes")
	protected static Map IConditionMap = new HashMap();
	@SuppressWarnings("rawtypes")
	static Vector MESSAGE = new Vector();
	@SuppressWarnings("rawtypes")
	static Vector GUEST = new Vector();
	@SuppressWarnings("rawtypes")
	static Vector BILL = new Vector();

	@SuppressWarnings("rawtypes")
	ConcurrentMap map;

	public CMDMap() {
		initVar();
		initMap();
		initFieldMap();
		initConditionmap();
	}

	@SuppressWarnings("unchecked")
	public void initMap() {// key= command,value=store procedure db
		ICMDMap.put("GI", "checInGuest");// check-in khach
		ICMDMap.put("GO", "checOutGuest");// check-out cua khach
		ICMDMap.put("GC", "changeGuestInfo");// thay doi thong tin khach
		ICMDMap.put("RICI", "cancelCheckIn");// huy check-in khach//RIS
		ICMDMap.put("RICO", "cancelCheckOut");// huy check-out khach.phuc hoi trang thai in-house cho khach

		/*
		 * Phan move room Note:moveGuestRoom va So param theo sau la linh dong khong giong tai ve so field. Ex: RR|R#12|RO134|RN130|G#12|
		 */
		ICMDMap.put("RG", "moveGuestRoom");// chuyen phong theo khach/
		ICMDMap.put("RR", "moveGuestRoom");// chuyen tat cac khach qua phong khac

		// phan ve message
		ICMDMap.put("XL", "recievMessage");// pms send message online to iptv
		ICMDMap.put("XM", "requestMessage");// iptv request message lai tu pms
		ICMDMap.put("XT", "recievMessage");// pms send message detail to iptv khi send lenh XM
		ICMDMap.put("XU", "updateMessage");// iptv send message da nhan hoac da doc
		ICMDMap.put("XD", "deleteMessage");// delete message pms->iptv

		// phan bill
		ICMDMap.put("XO", "recievTransaction");// nhan transaction tu pms-> iptv
		ICMDMap.put("PS", "postTransaction");// post transaction tu iptv --> pms
		ICMDMap.put("PA", "updatePostTransaction");// update trang thai da nhan transaction tu pms --> iptv
		ICMDMap.put("XI", "recievTransaction");// nhan transaction tu pms-> iptv
		ICMDMap.put("XID", "deleteTransaction");// nhan transaction tu pms-> iptv
		ICMDMap.put("XB", "balanceTransaction");// lenh ket thuc doc bill (tu pms-> iptv)

	}

	@SuppressWarnings("unchecked")
	public void initFieldMap() {// key=store,value=so param cua store
		IParamMap.put("checInGuest", "13");// so param cho procedure
		IParamMap.put("changeGuestInfo", "13");
		IParamMap.put("checOutGuest", "1");

		IParamMap.put("cancelCheckIn", "1");
		IParamMap.put("cancelCheckOut", "1");

		IParamMap.put("moveGuestRoom", "4");
		IParamMap.put("recievMessage", "6");
		IParamMap.put("deleteMessage", "3");
		IParamMap.put("updateMessage", "6");

		IParamMap.put("recievTransaction", "10");
		IParamMap.put("updatePostTransaction", "5");
		IParamMap.put("balanceTransaction", "5");
		IParamMap.put("deleteTransaction", "4");

	}

	@SuppressWarnings("unchecked")
	public static void initConditionmap() {// map chua thong tin rang buoc cua du lieu
		IConditionMap.put("checInGuest", GUEST);// so param cho procedure
		IConditionMap.put("changeGuestInfo", GUEST);
		IConditionMap.put("checOutGuest", GUEST);
		IConditionMap.put("cancalCheckIn", GUEST);

		IConditionMap.put("moveGuestRoom", GUEST);// R#,RN
		IConditionMap.put("moveAllGuestRoom", GUEST);

		IConditionMap.put("recievMessage", MESSAGE);// MI,G#
		IConditionMap.put("deleteMessage", MESSAGE);// MI
		IConditionMap.put("updateMessage", MESSAGE);// MI,G#

		IConditionMap.put("recievTransaction", BILL);
		IConditionMap.put("updatePostTransaction", BILL);
		IConditionMap.put("balanceTransaction", BILL);

	}

	@SuppressWarnings("unchecked")
	public void initVar() {// chua thong tin cac field rang buoc cua du lieu dau vao
		GUEST.add("R#");
		GUEST.add("G#");
		GUEST.add("RN");// phuc vu move phong

		MESSAGE.add("MI");
		MESSAGE.add("G#");

		BILL.add("T#");
		BILL.add("G#");
		BILL.add("RN");
	}

}
