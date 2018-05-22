package com.elcom.ehotel.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.dbi.IMBroker;
import com.elcom.ehotel.model.DiningModel;
import com.elcom.ehotel.model.HousekeepingModel;
import com.elcom.ehotel.model.RoomStatusModel;
import com.elcom.ehotel.model.WakeupModel;
import com.elcom.ehotel.util.ResultDB;
import com.elcom.eod.util.Encryptor;
import com.elcom.eod.util.UnicodeConverter;

import ehotel.utils.Utils;

public class OrderDao {

	static IMBroker broker = IMBroker.getInstance();
	public static final String ADD_UESR_ACTIVITIES = "BEGIN EPMS.ADDUSERACTIVITIES(?,?); END;";
	public static final String ADD_MESSAGE = "BEGIN EPMS.ADDMESSAGEFOLIO(?,?,?,?); END;";
	public static final String UPDATE_STATUS_HOUSEKEEPING = "BEGIN EPMS.UPDATESTATUSHOUSEKEEPING(?,?); END;";
	public static final String REMOVE_HOUSEKEEPING = "BEGIN EPMS.UPDATESTATUSREMOVEHOUSEKEEPING(?); END;";
	public static final String UPDATE_STATUS_WAKEUP = "BEGIN EPMS.UPDATESTATUSWAKEUP(?,?); END;";
	public static final String REMOVE_WAKEUP = "BEGIN EPMS.UPDATESTATUSREMOVEWAKEUP(?); END;";
	public static final String CHECK_ORDER = "BEGIN EPMS.CHECKORDER(?); END;";
	public static final String UPDATE_STATUS_ROOM = "BEGIN EPMS.UPDATESTATUSROOMSTATUS(?,?); END;";
	public static final String CONFIRM_ORDER = "BEGIN EPMS.confirmOrderManager(?,?); END;";
	public static final String GET_ORDER_DATA = "BEGIN EPMS.getOrderData(?); END;";
	public static final String LOGIN = "BEGIN eUser.login(?,?,?,?); END;";
	public static final String ISADMIN = "BEGIN eUser.isadmin(?,?); END;";
	public static final String CHECK_ROLE = "BEGIN eUser.checkRoleUser(?,?,?); END;";

	public OrderDao() {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DiningModel> getNewOrder(String role) {
		String sql = "select ID,DICH_VU,TEN_MON_AN,SO_LUONG,THUOC_PHONG,TO_CHAR(TO_DATE(NGAY_DAT_ORDER,'DD/MM/YYYY'), 'DD-Mon-YY') as NGAY_DAT_ORDER,TRANG_THAI,GIA,CHECK_OUT,TO_CHAR(NGAY_THUC_HIEN, 'DD-Mon-YY HH:MI:SS AM') as NGAY_LUU from nhan_order_tu_tivi where xoa = 0 and DICH_VU = 'DINING' order by ngay_thuc_hien desc";
		Vector vector = new Vector();
		List list = new ArrayList();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				DiningModel order;
				for (ResultDB result = new ResultDB(vector); result.hasNext(); list.add(order)) {
					result.next();
					order = new DiningModel();
					order.setMaorder(result.getParam("ID"));
					order.setDichvu(result.getParam("DICH_VU"));
					order.setTenmonan(result.getParam("TEN_MON_AN"));
					order.setSoluong(result.getParam("SO_LUONG"));
					order.setPhong(result.getParam("THUOC_PHONG"));
					order.setNgaydat(result.getParam("NGAY_DAT_ORDER"));
					order.setTrangthai(result.getParam("TRANG_THAI"));
					order.setGia(result.getParam("GIA"));
					order.setCheckout(result.getParam("CHECK_OUT"));
					order.setNgaythuchien(result.getParam("NGAY_LUU"));
					order.setRole(role);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getNewWakup() {
		String sql = "select id, folionum, TO_CHAR(wakeup_date, 'DD-MON-YY') as wakeup_date, hours, minutes, TO_CHAR(POSTED_DATE, 'DD-MON-YY HH:mm:ss') as POSTED_DATE, status from pms_wakeup where remove = 0 order by posted_date";
		Vector vector = new Vector();
		List list = new ArrayList();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				WakeupModel order;
				for (ResultDB result = new ResultDB(vector); result.hasNext(); list.add(order)) {
					result.next();
					order = new WakeupModel();
					order.setId(result.getParam("ID"));
					order.setFolionum(result.getParam("FOLIONUM"));
					order.setService("WAKEUP");
					order.setDatewakeup(result.getParam("WAKEUP_DATE"));
					order.setHours(result.getParam("HOURS"));
					order.setMinutes(result.getParam("MINUTES"));
					order.setDatepost(result.getParam("POSTED_DATE"));
					order.setStatus(result.getParam("STATUS"));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getNewHousekeeping() {
		String sql = "select ID, FOLIONUM, NAME, AMOUNT,TO_CHAR(DATE_SAVE, 'DD-Mon-YY HH:MI:SS AM') as DATE_SAVE, h.STATUS, USER_HK, HOUSEKEEPINGNAME from PMS_HOUSEKEEPING h, pms_user_housekeeping u where h.USER_HK = u.USERNAME and remove = 0 order by date_save";
		Vector vector = new Vector();
		List list = new ArrayList();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				HousekeepingModel order;
				for (ResultDB result = new ResultDB(vector); result.hasNext(); list.add(order)) {
					result.next();
					order = new HousekeepingModel();
					order.setId(result.getParam("ID"));
					order.setService("HOUSEKEEPING");
					order.setName(result.getParam("NAME"));
					order.setAmount(result.getParam("AMOUNT"));
					order.setFolionum(result.getParam("FOLIONUM"));
					order.setStatus(result.getParam("STATUS"));
					order.setDatesave(result.getParam("DATE_SAVE"));
					order.setUserhk(result.getParam("USER_HK"));
					order.setGuest(result.getParam("HOUSEKEEPINGNAME"));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateStatusOrder(int id, int trangthai) {
		String sql = (new StringBuilder("update NHAN_ORDER_TU_TIVI set TRANG_THAI = ")).append(trangthai).append(" where ID = ").append(id)
				.toString();
		boolean flag = true;
		try {
			broker.executeBlockSQLStmt(sql);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean insertMessageFolioConfirm(int folionum, String service) {
		String sql = (new StringBuilder(
				"insert into PMS_FOLIOMESSAGE(FOLIONUM,CLIENT_ID,SUBJECT,CONTENT_TOP,CONTENT_BOTTOM,DELETED,ISREAD,ISCONFIRM,MESSAGE_TYPE,SENDER)values("))
				.append(folionum).append(",2,'").append(service).append("','OK','OK',0,1,1,'CONFIRM','Service Center')").toString();
		boolean flag = true;
		try {
			broker.executeBlockSQLStmt(sql);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean insertMessageFolioDeny(int folionum, String service) {
		String sql = (new StringBuilder(
				"insert into PMS_FOLIOMESSAGE(FOLIONUM,CLIENT_ID,SUBJECT,CONTENT_TOP,CONTENT_BOTTOM,DELETED,ISREAD,ISCONFIRM,MESSAGE_TYPE,SENDER)values("))
				.append(folionum).append(",2,'").append(service).append("','OK','OK',0,1,1,'DENY','Service Center')").toString();
		boolean flag = true;
		try {
			broker.executeBlockSQLStmt(sql);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean updateStatusDeleteOrder(int id) {
		String sql = (new StringBuilder("update NHAN_ORDER_TU_TIVI set XOA = 1 where ID = ")).append(id).toString();
		boolean flag = true;
		try {
			broker.executeBlockSQLStmt(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List filterOrder(String service, String folio, String role) {
		List list = new ArrayList();
		String sql = "";
		if ("".equalsIgnoreCase(service) && "".equalsIgnoreCase(folio)) {
			list = getNewOrder(role);
			return list;
		}
		if (!"".equalsIgnoreCase(service) && "".equalsIgnoreCase(folio))
			sql = (new StringBuilder("select * from nhan_order_tu_tivi where xoa = 0 and dich_vu like '%")).append(service)
					.append("%' order by trang_thai asc").toString();
		else if ("".equalsIgnoreCase(service) && !"".equalsIgnoreCase(folio))
			sql = (new StringBuilder("select * from nhan_order_tu_tivi where xoa = 0 and thuoc_phong = '")).append(folio)
					.append("' order by trang_thai asc").toString();
		else if (!"".equalsIgnoreCase(service) && !"".equalsIgnoreCase(folio))
			sql = (new StringBuilder("select * from nhan_order_tu_tivi where xoa = 0 and dich_vu like '%")).append(service)
					.append("%' and thuoc_phong = '").append(folio).append("' order by trang_thai asc").toString();
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				DiningModel order;
				for (ResultDB result = new ResultDB(vector); result.hasNext(); list.add(order)) {
					result.next();
					order = new DiningModel();
					order.setMaorder(result.getParam("ID"));
					order.setDichvu(result.getParam("DICH_VU"));
					order.setTenmonan(result.getParam("TEN_MON_AN"));
					order.setSoluong(result.getParam("SO_LUONG"));
					order.setPhong(result.getParam("THUOC_PHONG"));
					order.setNgaydat(result.getParam("NGAY_DAT_ORDER"));
					order.setTrangthai(result.getParam("TRANG_THAI"));
					order.setGia(result.getParam("GIA"));
					order.setCheckout(result.getParam("CHECK_OUT"));
					order.setNgaythuchien(result.getParam("NGAY_THUC_HIEN"));
					order.setRole(role);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insertMessage(int id, String subject, String content, String sender) {
		boolean flag = true;
		Vector param = new Vector(4);
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		param.add(in);
		SubProParam in1 = new SubProParam(new String(subject), 0);
		param.add(in1);
		SubProParam in2 = new SubProParam(new String(content), 0);
		param.add(in2);
		SubProParam in3 = new SubProParam(new String(sender), 0);
		param.add(in3);
		try {
			broker.executeSubPro("BEGIN EPMS.ADDMESSAGEFOLIO(?,?,?,?); END;", param);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean addUserActivities(String des, String user) {
		boolean flag = true;
		Vector params = new Vector();
		SubProParam subIn = new SubProParam(new String(des), 0);
		params.add(subIn);
		subIn = new SubProParam(new String(user), 0);
		params.add(subIn);
		try {
			params = broker.executeSubPro("BEGIN EPMS.ADDUSERACTIVITIES(?,?); END;", params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List checkNewOrder() {
		List list = new ArrayList();
		String sql = "select count(case dich_vu when 'DINING' then 1 end) as DINING, count(case dich_vu when 'HOUSEKEEPING' then 1 end) as HOUSEKEEPING, count(case dich_vu when 'WAKEUP' then 1 end) as WAKEUP from nhan_order_tu_tivi where trang_thai = 0 and xoa = 0";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			for (ResultDB result = new ResultDB(vector); result.hasNext(); list.add(result.getParam("WAKEUP"))) {
				result.next();
				list.add(result.getParam("DINING"));
				list.add(result.getParam("HOUSEKEEPING"));
			}

		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean updateStatusHousekeeping(int id, int status) {
		boolean flag = true;
		Vector params = new Vector();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(status), 0);
		params.add(in);
		try {
			broker.executeSubPro("BEGIN EPMS.UPDATESTATUSHOUSEKEEPING(?,?); END;", params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean updateStatusRemoveHousekeeping(int id) {
		boolean flag = true;
		Vector params = new Vector();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		try {
			broker.executeSubPro("BEGIN EPMS.UPDATESTATUSREMOVEHOUSEKEEPING(?); END;", params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean updateStatusWakeup(int id, int status) {
		boolean flag = true;
		Vector params = new Vector();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(status), 0);
		params.add(in);
		try {
			broker.executeSubPro("BEGIN EPMS.UPDATESTATUSWAKEUP(?,?); END;", params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean updateStatusRemoveWakeup(int id) {
		boolean flag = true;
		Vector params = new Vector();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		try {
			broker.executeSubPro("BEGIN EPMS.UPDATESTATUSREMOVEWAKEUP(?); END;", params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List checkOrder() {
		List list = new ArrayList();
		Vector params = new Vector();
		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);
		try {
			params = broker.executeSubPro("BEGIN EPMS.CHECKORDER(?); END;", params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				String rs = paramOUT.getString().trim();
				if (rs.length() > 0) {
					String arr[] = rs.split(",");
					for (int i = 0; i < arr.length; i++)
						list.add(arr[i]);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getListRoomStatus() {
		List list = new ArrayList();
		String sql = "select folionum, room_status, statusname, to_char(date_modify_status,'DD-MON-YYYY HH:MM:SS') as date_modify_status from pms_folio f, pms_room_status_name r where f.new_status = 0 and f.room_status = r.id order by f.date_modify_status";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			RoomStatusModel rs;
			for (ResultDB result = new ResultDB(vector); result.hasNext(); list.add(rs)) {
				result.next();
				rs = new RoomStatusModel();
				rs.setFolionum(result.getParam("FOLIONUM"));
				rs.setStatusid(result.getParam("ROOM_STATUS"));
				rs.setStatusname(result.getParam("STATUSNAME"));
				rs.setDate(result.getParam("DATE_MODIFY_STATUS"));
				rs.setService("ROOM STATUS");
			}

		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean updateStatusRoomStatus(int folionum, int status) {
		boolean flag = true;
		Vector params = new Vector();
		SubProParam in = new SubProParam(new BigDecimal(folionum), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(status), 0);
		params.add(in);
		try {
			broker.executeSubPro("BEGIN EPMS.UPDATESTATUSROOMSTATUS(?,?); END;", params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public boolean confirmOrder(int order) {
		String rs = "-1";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(order), 0);
		params.add(in);

		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);
		try {
			params = broker.executeSubPro(CONFIRM_ORDER, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (rs.equals("1"))
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<DiningModel> getOrderData(String role) {
		System.out.println(role);
		List<DiningModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_ORDER_DATA, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 8) {
			DiningModel order = new DiningModel();
			order.setMaorder(outParam.get(i));
			order.setTenmonan(outParam.get(i + 1));
			order.setSoluong(outParam.get(i + 2));
			order.setPhong(outParam.get(i + 3));
			order.setTrangthai(outParam.get(i + 4));
			order.setCheckout(outParam.get(i + 5));
			order.setNgaythuchien(outParam.get(i + 6));
			order.setDetail(UnicodeConverter.decodeUnicode(outParam.get(i + 7)));
			order.setRole(role);
			list.add(order);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean login(String account, String passwd, String markAddress) {
		boolean islogin = false;
		int userId = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(account, SubProParam.IN);
		params.add(subparam);// 1
		subparam = new SubProParam(Encryptor.encryptPassword(passwd), SubProParam.IN);
		params.add(subparam);// 2
		subparam = new SubProParam(markAddress, SubProParam.IN);
		params.add(subparam);// 3
		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 4

		try {
			params = broker.executeSubPro(LOGIN, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				userId = Utils.parseInt(paramOUT.getString().trim());
				if (userId > 0)
					islogin = true;
			}
		} catch (Exception ex) {
			ex.getCause();
		}
		return islogin;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isAdmin(String markAdrress) {
		boolean isAdmin = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(markAdrress, SubProParam.IN);
		params.add(subparam);// 1
		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 2

		try {
			params = broker.executeSubPro(ISADMIN, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret > 0) {
					isAdmin = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isAdmin;
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkRoleUser(String markAddress, String role) {
		boolean isCheck = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(markAddress, SubProParam.IN);
		params.add(subparam);// 1
		subparam = new SubProParam(role, SubProParam.IN);
		params.add(subparam);// 2
		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 3

		try {
			params = broker.executeSubPro(CHECK_ROLE, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString());
				if (ret == 1)
					isCheck = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isCheck;
	}

	public static void main(String args[]) {
		OrderDao orderDao = new OrderDao();
		System.out.println(orderDao.getOrderData("order"));
	}

}
