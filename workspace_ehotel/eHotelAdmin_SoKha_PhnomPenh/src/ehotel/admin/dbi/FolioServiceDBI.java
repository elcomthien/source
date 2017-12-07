package ehotel.admin.dbi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.admin.pms.FolioModel;
import ehotel.admin.pms.GuestModel;
import ehotel.domain.pms.eSTB;

public class FolioServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	public static final String ADD_MESSAGE = "BEGIN EPMS.ADDMESSAGEFOLIO(?,?,?,?); END;";
	public static final String CHECK_OUT = "BEGIN EPMS.CHECKOUTFOLIO(?,?); END;";
	public static final String CHECK_IN_FOLIO = "BEGIN EPMS.checkInFolio(?,?,?,?,?,?,?); END;";
	public static final String UPDATE_STATUS_RECORD = "BEGIN EPMS.updateStatusRecord(?); END;";
	public static final String UPDATE_ACCOUNT_VALUE = "BEGIN EPMS.UPDATEACCOUNTVALUE(?,?); END;";
	public static final String RESET_UNIQUE_PIN = "BEGIN EPMS.RESETUNIQUEPINCODE(?); END;";
	public static final String UPDATE_OTA = "BEGIN UPDATEOTA(?,?); END;";
	public static final String EDIT_UNIQUE_CHARGE = "BEGIN EPMS.EDITUNIQUECHARGE(?); END;";
	public static final String UPDATE_STATUS_XXX = "BEGIN EPMS.UPDATESTATUSXXX(?,?); END;";
	public static final String UPDATE_STATUS_FREE = "BEGIN EPMS.UPDATESTATUSFREE(?,?); END;";
	public static final String GET_LIST_FOLIO = "BEGIN EPMS.getFolioList(?,?,?); END;";
	public static final String GET_LIST_FOLIO_SEARCH = "BEGIN EPMS.searchFolioNew(?,?,?,?); END;";
	public static final String COUNT_LIST_FOLIO = "BEGIN EPMS.countFolio(?); END;";
	
	@SuppressWarnings("unchecked")
	public boolean CheckIn(int folionum, String firstname, String lastname,
			String gioitinh, String checkindate, String checkoutdate,
			int roomsharer) {
		// String sql =
		// "insert into PMS_GUESTPROFILE(FOLIONUM,FIRSTNAME,LASTNAME,CLIENT_NAME,ARRIVAL_TIME,DEPARTMENT_TIME,CLIENT_STATUS,ROOM_SHARER) "
		// + "values(" + folionum + ",'" + firstname + "','" + lastname + "','"
		// + gioitinh + "','" + checkindate + "','" + checkoutdate +
		// "'," + 1 + "," + roomsharer + ")";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(folionum),
				SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(firstname), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(lastname), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(gioitinh), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(checkindate), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(checkoutdate), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new BigDecimal(roomsharer), SubProParam.IN);
		params.add(in);
		try {
			params = broker.executeSubPro(CHECK_IN_FOLIO, params);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// int id = getMaxIdGuest();
		// boolean flag = updatePincode(id);
		return true;
	}

	@SuppressWarnings("rawtypes")
	public Vector<Record> getAllRecord() {
		String sql = "select * from record_channel order by id asc";
		Vector vector = new Vector();
		Vector<Record> listRecords = new Vector<Record>();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					Record record = new Record();
					record.setId(Integer.parseInt(result.getParam("ID")));
					record.setChannalname(result.getParam("CHANNEL_NAME"));
					record.setUrlrecord(result.getParam("URL_RECORD"));
					record.setSernumber(result.getParam("SERNUMBER"));
					record.setStarttime(result.getParam("START_TIME"));
					record.setStoptime(result.getParam("STOP_TIME"));
					record.setStatus(Integer.parseInt(result.getParam("STATUS")));
					record.setPrivatechannelname(result
							.getParam("PRIVATE_CHANNELNAME"));
					if (!result.getParam("SIZEINKB").equalsIgnoreCase(""))
						record.setSizeinkb(Integer.parseInt(result
								.getParam("SIZEINKB")));
					record.setUrlpicture(result.getParam("URL_HINH"));
					listRecords.add(record);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRecords;
	}

	@SuppressWarnings("rawtypes")
	public Vector<Record> getRecordForFolio(String folio) {
		Vector<Record> listRecords = new Vector<Record>();
		List<String> listsernumber = getSmartCardForFolio(folio);
		if (listsernumber.size() == 0)
			return listRecords;
		else {
			String condition = "";
			for (int i = 0; i < listsernumber.size(); i++) {
				condition += "SERNUMBER = " + listsernumber.get(i);
				if (i + 1 < listsernumber.size()) {
					condition += " or ";
				}
			}
			String sql = "select * from record_channel where " + condition
					+ " order by id asc";
			Vector vector = new Vector();
			try {
				vector = broker.executeSelect(sql, null);
				if (vector.size() > 2) {
					ResultDB result = new ResultDB(vector);
					while (result.hasNext()) {
						result.next();
						Record record = new Record();
						record.setId(Integer.parseInt(result.getParam("ID")));
						record.setChannalname(result.getParam("CHANNEL_NAME"));
						record.setUrlrecord(result.getParam("URL_RECORD"));
						record.setSernumber(result.getParam("SERNUMBER"));
						record.setStarttime(result.getParam("START_TIME"));
						record.setStoptime(result.getParam("STOP_TIME"));
						record.setStatus(Integer.parseInt(result
								.getParam("STATUS")));
						record.setPrivatechannelname(result
								.getParam("PRIVATE_CHANNELNAME"));
						if (!result.getParam("SIZEINKB").equalsIgnoreCase(""))
							record.setSizeinkb(Integer.parseInt(result
									.getParam("SIZEINKB")));
						record.setUrlpicture(result.getParam("URL_HINH"));
						listRecords.add(record);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listRecords;
	}

	@SuppressWarnings("rawtypes")
	public List<String> getSmartCardForFolio(String folio) {
		List<String> list = new ArrayList<String>();
		String sql = "select smart_card from pms_foliostb where folio_code = '"
				+ folio + "' order by smart_card asc";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					list.add(result.getParam("SMART_CARD"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateStatusRecord(int id) {
		// String sql =
		// "update record_channel set status = 4, TRANG_THAI_CORE = 0 where id = "
		// + id;
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(UPDATE_STATUS_RECORD, params);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean insertMessage(int id, String subject, String content,
			String sender) {
		boolean flag = true;
		Vector<SubProParam> param = new Vector<SubProParam>(4);
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		param.add(in);// 0
		SubProParam in1 = new SubProParam(new String(subject), SubProParam.IN);
		param.add(in1);// 1
		SubProParam in2 = new SubProParam(new String(content), SubProParam.IN);
		param.add(in2);// 2
		SubProParam in3 = new SubProParam(new String(sender), SubProParam.IN);
		param.add(in3);// 3
		try {
			broker.executeSubPro(ADD_MESSAGE, param);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public boolean checkoutRoom(int id) {
		boolean flag = true;
		Vector<SubProParam> param = new Vector<SubProParam>(2);
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		param.add(in);// 0
		SubProParam out = new SubProParam(new String(), SubProParam.OUT);
		param.add(out);// 1
		try {
			param = broker.executeSubPro(CHECK_OUT, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// TO_CHAR(ARRIVAL_TIME, 'dd/mm/yy')
	@SuppressWarnings("rawtypes")
	public List<GuestModel> getGuestForFolio(int id) {
		List<GuestModel> list = new ArrayList<GuestModel>();
		String sql = "select FIRSTNAME, LASTNAME, CLIENT_ID, TO_CHAR(ARRIVAL_TIME, 'dd/mm/yy') as ARRIVAL_TIME , TO_CHAR(DEPARTMENT_TIME, 'dd/mm/yy') as DEPARTMENT_TIME, CLIENT_NAME, CLIENT_STATUS, ROOM_SHARER, PINCODE, (select PASS_CELLPHONE from pms_folio where folio_code = "
				+ id
				+ ") AS UNIQUEPIN from pms_guestprofile where folionum = "
				+ id + " and client_status = 1 order by CLIENT_ID asc";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					GuestModel guest = new GuestModel();
					guest.setFolionum(id);
					guest.setFirstname(result.getParam("FIRSTNAME"));
					guest.setLastname(result.getParam("LASTNAME"));
					guest.setClientid(Integer.parseInt(result
							.getParam("CLIENT_ID")));
					guest.setArrivaltime(result.getParam("ARRIVAL_TIME"));
					guest.setDepartmenttime(result.getParam("DEPARTMENT_TIME"));
					guest.setClientname(result.getParam("CLIENT_NAME"));
					guest.setClientstatus(Integer.parseInt(result
							.getParam("CLIENT_STATUS")));
					guest.setRoomshare(Integer.parseInt(result
							.getParam("ROOM_SHARER")));
					guest.setPincode(Integer.parseInt(result
							.getParam("PINCODE")));
					guest.setUnique(result.getParam("UNIQUEPIN"));
					list.add(guest);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public int checkExistsMainGuest(int id) {
		int rs = 0;
		String sql = "select * from pms_guestprofile where client_status = 1 and folionum ="
				+ id;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2)
				rs = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@SuppressWarnings("rawtypes")
	public List<eSTB> getListSearchSTB(String key) {
		List<eSTB> list = new ArrayList<eSTB>();
		String sql = "select smartcardid,serinumber,description,folio_code,createdate,status "
				+ "from (select smartcardid,serinumber,description,foo.folio_code,createdate,status,rownum as stt "
				+ "from pms_smartcard stb, pms_foliostb foo where stb.serinumber = foo.smart_card(+) order by serinumber) "
				+ "where serinumber like '%"
				+ key
				+ "%' or folio_code like '%"
				+ key + "%' or description like '%" + key + "%'";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					eSTB e = new eSTB();
					e.setId(Integer.parseInt(result.getParam("smartcardid")));
					e.setIP(result.getParam("description"));
					e.setRoomCode(result.getParam("folio_code"));
					e.setKeyCode(result.getParam("serinumber"));
					list.add(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public String getAccountValue(String foliocode) {

		String sql = "select s.ACCOUNTVALUE, s.STATUS_XXX from PMS_SMARTCARD s, PMS_FOLIOSTB f where s.SERINUMBER = f.SMART_CARD and f.FOLIO_CODE = "
				+ foliocode;
		Vector vector = new Vector();
		String value = "";
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				result.moveToFirst();
				value = result.getParam("ACCOUNTVALUE");
				value += "#" + result.getParam("STATUS_XXX");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("get account value ::::::::: " + foliocode + " = "
		// + value);
		return value;
	}

	public boolean updateAccountValue(int folio, int xxx) {
		boolean flag = true;
		Vector<SubProParam> param = new Vector<SubProParam>(2);
		SubProParam in = new SubProParam(new BigDecimal(folio), SubProParam.IN);
		param.add(in);// 0
		in = new SubProParam(new BigDecimal(xxx), SubProParam.IN);
		param.add(in);// 1
		try {
			broker.executeSubPro(UPDATE_ACCOUNT_VALUE, param);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public boolean updateStatusXXX(int folio, int xxx) {
		boolean flag = true;
		Vector<SubProParam> param = new Vector<SubProParam>(2);
		SubProParam in = new SubProParam(new BigDecimal(folio), SubProParam.IN);
		param.add(in);// 0
		in = new SubProParam(new BigDecimal(xxx), SubProParam.IN);
		param.add(in);// 1
		try {
			broker.executeSubPro(UPDATE_STATUS_XXX, param);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateStatusFree(int folio, int xxx) {
		boolean flag = true;
		Vector<SubProParam> param = new Vector<SubProParam>(2);
		SubProParam in = new SubProParam(new BigDecimal(folio), SubProParam.IN);
		param.add(in);// 0
		in = new SubProParam(new BigDecimal(xxx), SubProParam.IN);
		param.add(in);// 1
		try {
			broker.executeSubPro(UPDATE_STATUS_FREE, param);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public static String getURLPromotion(int id, int lang) {
		String link = "";
		String sql = "select url_web from pms_promotion where promotion_id = "
				+ id + " and lang_id = " + lang;
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			link = result.getParam("URL_WEB");
		}
		return link;
	}

	public static boolean resetUniquePincode(int folio) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>(1);
		SubProParam in = new SubProParam(new BigDecimal(folio), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(RESET_UNIQUE_PIN, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public boolean updateOTAToRoom(int folio, String list) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(folio), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(list), SubProParam.IN);
		params.add(in);
		try {
			params = broker.executeSubPro(UPDATE_OTA, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public String getChargeFilm() {
		String data = "";
		String sql = "select value from ehotel_config where key = 'charge_film'";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			data = result.getParam("VALUE");
		}
		return data;
	}

	public boolean editUniqueCharge(int charge) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>(1);
		SubProParam in = new SubProParam(new BigDecimal(charge), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(EDIT_UNIQUE_CHARGE, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<FolioModel> getListFolio(int from, int to) {
		List<FolioModel> list = new ArrayList<FolioModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(from), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(to), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_LIST_FOLIO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 8) {
			FolioModel folio = new FolioModel();
			folio.setFolionum(outParam.get(i));
			folio.setFoliocode(outParam.get(i + 1));
			folio.setType(outParam.get(i + 2));
			folio.setStatus(outParam.get(i + 3));
			folio.setFee(outParam.get(i + 4));
			folio.setFree(outParam.get(i + 5));
			folio.setCharge(outParam.get(i + 6));
			folio.setXxx(outParam.get(i + 7));
			list.add(folio);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<FolioModel> getListFolioSearch(String key, int from, int to) {
		List<FolioModel> list = new ArrayList<FolioModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(from), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(to), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_LIST_FOLIO_SEARCH, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 8) {
			FolioModel folio = new FolioModel();
			folio.setFolionum(outParam.get(i));
			folio.setFoliocode(outParam.get(i + 1));
			folio.setType(outParam.get(i + 2));
			folio.setStatus(outParam.get(i + 3));
			folio.setFee(outParam.get(i + 4));
			folio.setFree(outParam.get(i + 5));
			folio.setCharge(outParam.get(i + 6));
			folio.setXxx(outParam.get(i + 7));
			list.add(folio);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public int countFolioList(){
		int out = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(new BigDecimal(out), 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(COUNT_LIST_FOLIO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam)params.get(0);
				System.out.println(subOut);
				System.out.println(outParam);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		FolioServiceDBI f = new FolioServiceDBI();
		System.out.println(f.countFolioList());
	}
}
