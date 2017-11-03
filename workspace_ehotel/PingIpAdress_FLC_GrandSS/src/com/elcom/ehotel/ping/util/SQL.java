package com.elcom.ehotel.ping.util;

import com.elcom.ehotel.ping.dbi.IMBroker;

public class SQL {
	public static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LIST_IP = "BEGIN EPMS.getListIp(?); END;";
	public static final String UPDATE_STATUS = "BEGIN EPMS.updateStatusTivi(?,?,?); END;";
}
