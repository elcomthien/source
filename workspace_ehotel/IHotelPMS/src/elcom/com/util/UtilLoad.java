package elcom.com.util;

import java.util.Date;
import java.util.Vector;

import elcom.com.core.common.CMDCommon;
import elcom.com.core.common.Guest;
import elcom.com.core.common.ServiceCharge;

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
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class UtilLoad {
	// -------------------------------------------------------------------------
	@SuppressWarnings("rawtypes")
	public static CMDCommon LoadCMDCommon(Vector vTmp) {
		CMDCommon commond = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				commond = new CMDCommon();
				commond.setFolioNum((String) vTmp.get(i));
				System.out.println((String) vTmp.get(i));
				commond.setClientId((String) vTmp.get(i + 1));
				commond.setCmdId((String) vTmp.get(i + 2));
				String datetime = (String) vTmp.get(i + 3);
				System.out.println(datetime);
				Date date = Utils.parseDate(datetime);
				commond.setDate(Utils.format(date, "yyMMdd"));
				System.out.println(Utils.format(date, "yyMMdd"));
				commond.setTime(Utils.format(date, "HHmmss"));
			}
		}
		return commond;
	}

	// -------------------------------------------------------------------------
	@SuppressWarnings("rawtypes")
	public static ServiceCharge LoadServiceCharge(Vector vTmp) {
		// System.out.println("huhu");
		ServiceCharge charger = null;
		if (vTmp != null && vTmp.size() > 0) {
			// System.out.println("huhu");
			for (int i = 0; i < vTmp.size(); i = i + 8) {
				charger = new ServiceCharge();
				charger.setTranID(Utils.parseInt((String) vTmp.get(i)));
				System.out.println("Folio_code=" + (String) vTmp.get(i + 1));
				charger.setFolioNum((String) vTmp.get(i + 1));
				charger.setDesciption((String) vTmp.get(i + 2));
				System.out.println((String) vTmp.get(i + 2));
				charger.setAmount((String) vTmp.get(i + 3));
				String datetime = (String) vTmp.get(i + 4);
				Date date = Utils.parseDate(datetime);
				charger.setTranDate(Utils.format(date, "yyMMdd"));
				charger.setTranTime(Utils.format(date, "HHmmss"));
				charger.setPinCode((String) vTmp.get(i + 5));
				charger.setAmountUnit((String) vTmp.get(i + 6));
				charger.setIdReservation((String) vTmp.get(i + 7));
			}
		}
		return charger;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector LoadGuests(Vector vTmp) {
		Guest guest = null;
		Vector tmp_vRs = new Vector();
		int count = 0;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				guest = new Guest();
				guest.setGuestId((String) vTmp.get(i));
				guest.setName((String) vTmp.get(i + 1));
				guest.setPhone((String) vTmp.get(i + 2));
				guest.setArrival((String) vTmp.get(i + 3));
				guest.setDepature((String) vTmp.get(i + 4));
				guest.setRoomCode((String) vTmp.get(i + 5));
				tmp_vRs.add(guest);
				count++;
			}
		}
		System.out.println("PMSCtnDAO.getGuestListInRoom : returnValue(size=" + count + ")]");
		return tmp_vRs;
	}

}
