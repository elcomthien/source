package com.elcom.ehotel.esmile.util;

import com.elcom.ehotel.esmile.dbi.IMBroker;

public class SQL {
	public static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LAYOUT_ID = "BEGIN PRO_GETSEQLAYOUT(?); END;";
	public static final String ADD_LAYOUT_URL = "BEGIN PRO_ADDLAYOUTURL(?,?,?,?); END;";
}
