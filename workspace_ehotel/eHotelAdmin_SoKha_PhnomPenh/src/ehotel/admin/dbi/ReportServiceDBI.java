package ehotel.admin.dbi;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.admin.Report.MessageReportModel;
import ehotel.admin.Report.MovieDetailModel;
import ehotel.admin.Report.MovieGoupModel;
import ehotel.admin.Report.ProductionReportModel;
import ehotel.admin.Report.RoomReportModel;
import ehotel.admin.Report.RoomServiceOrderModel;
import ehotel.admin.Report.ServiceMenuModel;
import ehotel.admin.Report.SubjectServiceModel;
import ehotel.admin.Report.UserActivityModel;
import ehotel.admin.Report.VodReportModel;

public class ReportServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	public static final String GET_MESSAGE_REPORT = "BEGIN EREPORT.GETMESSAGEREPORT(?,?,?); END;";
	public static final String ADD_UESR_ACTIVITIES = "BEGIN EPMS.ADDUSERACTIVITIES(?,?); END;";

	@SuppressWarnings("rawtypes")
	public List<VodReportModel> getStaticFilmReport(String month) {
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		String sql = "select CONTENTNAME,count(*) as quanlity, SAMOUNT, CASHCARD from vod_charge ";
		sql += "where SERVICETYPENAME = 'VOD' and to_char(STARTTIME,'mmyyyy')="
				+ month;
		sql += " group by CONTENTNAME, CASHCARD, SAMOUNT";
//		System.out.println(sql);
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					VodReportModel vod = new VodReportModel();
					vod.setName(result.getParam("CONTENTNAME"));
					vod.setPrice(result.getParam("SAMOUNT"));
					vod.setUnit(result.getParam("CASHCARD"));
					vod.setQuantity(Integer.parseInt(result
							.getParam("QUANLITY")));
					list.add(vod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<VodReportModel> getMonthlyStaticReport(String year) {
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		String sql = "select to_char(starttime,'MM/yyyy') monthly, count(*) AMOUNT , sum(samount) sotien "
				+ "from vod_charge where servicetypename = 'VOD' and to_char(starttime,'yyyy') = "
				+ year + "  group by to_char(starttime,'MM/yyyy')";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					VodReportModel vod = new VodReportModel();
					vod.setDate(result.getParam("MONTHLY"));
					vod.setQuantity(Integer.parseInt(result.getParam("AMOUNT")));
					list.add(vod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<VodReportModel> getDailyStaticReport(String month) {
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		String sql = "select to_char(starttime,'dd/mm/yyyy') daily, sum(samount) samount  from vod_charge "
				+ "where to_char(starttime,'mmyyyy') = "
				+ month
				+ " and servicetypename = 'VOD' group by to_char(starttime,'dd/mm/yyyy')";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					VodReportModel vod = new VodReportModel();
					vod.setDate(result.getParam("daily"));
					vod.setQuantity(Integer.parseInt(result.getParam("SAMOUNT")));
					list.add(vod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MessageReportModel> getMessageReport1(String from, String to) {
		List<MessageReportModel> list = new ArrayList<MessageReportModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector outParam = new Vector();
		SubProParam subIn = new SubProParam(new String(from), SubProParam.IN);
		params.add(subIn);
		subIn = new SubProParam(new String(to), SubProParam.IN);
		params.add(subIn);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_MESSAGE_REPORT, params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if ((params != null) & (params.size() > 0)) {
			subOut = (SubProParam) params.get(0);
			outParam = subOut.getVector();
			for (int i = 0; i < outParam.size(); i = i + 8) {
				MessageReportModel mess = new MessageReportModel();
				mess.setRoom(outParam.get(i).toString());
				mess.setGuest(outParam.get(i + 1).toString());
				mess.setCheckin(outParam.get(i + 2).toString());
				mess.setCheckout(outParam.get(i + 3).toString());
				mess.setCreate(outParam.get(i + 4).toString());
				mess.setRead(outParam.get(i + 5).toString());
				mess.setSender(outParam.get(i + 6).toString());
				mess.setContent(outParam.get(i + 7).toString());
				System.out.println(mess.toString());
				list.add(mess);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<MessageReportModel> getMessageReport(String from, String to) {
		List<MessageReportModel> list = new ArrayList<MessageReportModel>();
		String sql = "select m.FOLIONUM, g.LASTNAME as guest, to_char(g.ARRIVAL_TIME, 'DD/MM/YYYY') as ARRIVAL_TIME, to_char(g.DEPARTMENT_TIME, 'DD/MM/YYYY') as DEPARTMENT_TIME, to_char(m.ENTERED_DATE, 'DD/MM/YYYY HH24:MI:SS') as created, to_char(m.READ_DATE, 'DD/MM/YYYY HH24:MI:SS') as readed, m.SENDER, m.CONTENT from pms_foliomessage m, pms_guestprofile g where  ENTERED_DATE >= TO_TIMESTAMP('"
				+ from
				+ " 00:00:00','DD/MM/YYYY HH24:MI:SS') and ENTERED_DATE <= TO_TIMESTAMP('"
				+ to
				+ " 23:59:59', 'DD/MM/YYYY HH24:MI:SS') and m.CLIENT_ID = g.CLIENT_ID and m.subject not in ('Welcome', 'DINING') ORDER BY m.FOLIONUM";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		System.out.println(vector.size());
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				MessageReportModel mess = new MessageReportModel();
				mess.setRoom(result.getParam("FOLIONUM"));
				mess.setGuest(result.getParam("GUEST"));
				mess.setCheckin(result.getParam("ARRIVAL_TIME"));
				mess.setCheckout(result.getParam("DEPARTMENT_TIME"));
				mess.setCreate(result.getParam("CREATED"));
				mess.setRead(result.getParam("READED"));
				mess.setSender(result.getParam("SENDER"));
				mess.setContent(result.getParam("CONTENT"));
				// System.out.println(mess.toString());
				list.add(mess);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<SubjectServiceModel> getSubjectMovieService() {
		List<SubjectServiceModel> list = new ArrayList<SubjectServiceModel>();
		String sql = "select s.SUBJECTID, s.SUBJECTNAME, (select count(*) from vod_service_subject v where v.SUBJECTID = s.SUBJECTID) as amount from vod_servicesubject s where s.LANG_ID = 2";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				SubjectServiceModel ssm = new SubjectServiceModel();
				ssm.setId(result.getParam("SUBJECTID"));
				ssm.setName(result.getParam("SUBJECTNAME"));
				ssm.setCount(result.getParam("AMOUNT"));
				list.add(ssm);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<MovieGoupModel> getMovieGroup(String id) {
		List<MovieGoupModel> list = new ArrayList<MovieGoupModel>();
		String sql = "select v.SVC_ID, v.CONTENTNAME, to_char(v.CREATEDATE, 'DD/MM/YYYY HH24:MI:SS') as CREATEDATE, (select count(*) from VOD_SUBTITLE t where t.CONTENTID = v.CONTENTID) as countsub, (select rtrim (xmlagg (xmlelement (e, l.CODE || ',')).extract ('//text()'), ',') from  VOD_SUBTITLE t, PMS_LANGUAGE l where t.CONTENTID = v.CONTENTID and t.LANG_ID = l.LANG_ID)  as Code  from VOD_SVC_CONTENTS v, vod_service_subject s where v.SVC_ID = s.SVC_ID and s.SUBJECTID = "
				+ id + " and v.LANG_ID = 2";
//		System.out.println("sql: " + sql);
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				MovieGoupModel mg = new MovieGoupModel();
				mg.setId(result.getParam("SVC_ID"));
				mg.setTitle(result.getParam("CONTENTNAME"));
				mg.setUpload(result.getParam("CREATEDATE"));
				mg.setCountsub(result.getParam("COUNTSUB"));
				mg.setLangsub(result.getParam("CODE"));
				list.add(mg);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<MovieDetailModel> getDetailReport(String from, String to) {
		List<MovieDetailModel> list = new ArrayList<MovieDetailModel>();
		String sql = "select v.SVC_ID, v.CONTENTNAME, to_char(v.CREATEDATE, 'DD/MM/YYYY HH24:MI:SS') as CREATEDATE, v.CURRENCY, v.IUNIT, (select count(*) from VOD_SUBTITLE t where t.CONTENTID = v.CONTENTID) as countsub, (select rtrim (xmlagg (xmlelement (e, l.CODE || ',')).extract ('//text()'), ',') from VOD_SUBTITLE t, PMS_LANGUAGE l where t.CONTENTID = v.CONTENTID and t.LANG_ID = l.LANG_ID)  as Code  from VOD_SVC_CONTENTS v, vod_service_subject s where v.SVC_ID = s.SVC_ID and v.LANG_ID = 2 and v.CREATEDATE >= TO_TIMESTAMP ('"
				+ from
				+ " 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and v.CREATEDATE <= TO_TIMESTAMP ('"
				+ to + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS')";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				MovieDetailModel detail = new MovieDetailModel();
				detail.setId(result.getParam("SVC_ID"));
				detail.setTitle(result.getParam("CONTENTNAME"));
				detail.setUpload(result.getParam("CREATEDATE"));
				detail.setCountsub(result.getParam("COUNTSUB"));
				detail.setLangsub(result.getParam("CODE"));
				detail.setPrice(result.getParam("CURRENCY"));
				detail.setCurrency(result.getParam("IUNIT"));
				list.add(detail);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<ProductionReportModel> getProductionReport(String from,
			String to) {
		List<ProductionReportModel> list = new ArrayList<ProductionReportModel>();
		String sql = "select DISTINCT v.CONTENTNAME, v.IUNIT, v.CURRENCY, (select count(*)  from VOD_REPORT p  where p.CONTENTID = r.CONTENTID and p.TYPEREPORT = 1) as pincode,"
				+ " (select count(*) from VOD_REPORT p  where p.CONTENTID = r.CONTENTID and p.TYPEREPORT = 2) as uniquepin "
				+ "from VOD_REPORT r, VOD_CONTENTS v where r.CONTENTID = v.CONTENTID and v.LANG_ID = 2 "
				+ "and r.VIEWDATE >= TO_TIMESTAMP ('"
				+ from
				+ " 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and r.VIEWDATE <= TO_TIMESTAMP ('"
				+ to + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS')";
		// System.out.println(sql);
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				ProductionReportModel pro = new ProductionReportModel();
				pro.setTitle(result.getParam("CONTENTNAME"));
				pro.setCurrency(result.getParam("IUNIT"));
				pro.setRate(result.getParam("CURRENCY"));
				pro.setUnique(result.getParam("UNIQUEPIN"));
				pro.setPincode(result.getParam("PINCODE"));
				// System.out.println(pro.toString());
				list.add(pro);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<RoomReportModel> getRoomReport(String from, String to) {
		List<RoomReportModel> list = new ArrayList<RoomReportModel>();
		String sql = "select DISTINCT s.FOLIO_CODE, v.CONTENTNAME, to_char(r.VIEWDATE, 'DD/MM/YYYY') as VIEWDATE, v.IUNIT, v.CURRENCY from VOD_REPORT r, VOD_CONTENTS v, PMS_FOLIOSTB s where r.CONTENTID = v.CONTENTID and r.SERINUMBER = s.SMART_CARD and r.VIEWDATE >= TO_TIMESTAMP ('"
				+ from
				+ " 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and r.VIEWDATE <= TO_TIMESTAMP ('"
				+ to
				+ " 23:59:59', 'DD/MM/YYYY HH24:MI:SS') ORDER BY TO_NUMBER(s.FOLIO_CODE)";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				RoomReportModel room = new RoomReportModel();
				room.setRoom(result.getParam("FOLIO_CODE"));
				room.setTitle(result.getParam("CONTENTNAME"));
				room.setDate(result.getParam("VIEWDATE"));
				room.setQty("1");
				room.setCurrency(result.getParam("IUNIT"));
				room.setRate(result.getParam("CURRENCY"));
				list.add(room);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<String> getRoom(String from, String to) {
		List<String> list = new ArrayList<String>();
		String sql = "select DISTINCT s.FOLIO_CODE from VOD_REPORT r, PMS_FOLIOSTB s where r.SERINUMBER = s.SMART_CARD and r.VIEWDATE >= TO_TIMESTAMP ('"
				+ from
				+ " 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and r.VIEWDATE <= TO_TIMESTAMP ('"
				+ to
				+ " 23:59:59', 'DD/MM/YYYY HH24:MI:SS') ORDER BY TO_NUMBER(s.FOLIO_CODE)";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				list.add(result.getParam("FOLIO_CODE"));
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<String> getRoomMessage(String from, String to) {
		List<String> list = new ArrayList<String>();
		String sql = "select distinct m.FOLIONUM from pms_foliomessage m, pms_guestprofile g where  ENTERED_DATE >= TO_TIMESTAMP('"
				+ from
				+ " 00:00:00','DD/MM/YYYY HH24:MI:SS') and ENTERED_DATE <= TO_TIMESTAMP('"
				+ to
				+ " 23:59:59', 'DD/MM/YYYY HH24:MI:SS') and m.CLIENT_ID = g.CLIENT_ID and m.subject not in ('Welcome', 'DINING') ORDER BY TO_NUMBER(m.FOLIONUM)";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				list.add(result.getParam("FOLIONUM"));
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<ServiceMenuModel> getServiceMenuReport(String from, String to) {
		List<ServiceMenuModel> list = new ArrayList<ServiceMenuModel>();
		String sql = "select icode, iname, to_char(modifydate, 'DD-Mon-YY') as datetime, currency, iunit, updateby from pms_item where modifydate >= TO_TIMESTAMP('"
				+ from
				+ " 00:00:00','DD/MM/YYYY HH24:MI:SS') and modifydate <= TO_TIMESTAMP('"
				+ to
				+ " 23:59:59','DD/MM/YYYY HH24:MI:SS') and IMENUNO in (select menuno from pms_menu where MMMENUNO in (select menuno from pms_menu where MMMENUNO = -9012 and lang_id = 2) and lang_id = 2) and lang_id = 2 order by modifydate desc";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				ServiceMenuModel menu = new ServiceMenuModel();
				menu.setId(result.getParam("ICODE"));
				menu.setName(result.getParam("INAME"));
				menu.setDatetime(result.getParam("DATETIME"));
				menu.setRate(result.getParam("CURRENCY"));
				menu.setCurency(result.getParam("IUNIT"));
				menu.setUpdateby(result.getParam("UPDATEBY"));
				list.add(menu);
			}
		}
		return list;
	}

	// select id,ten_mon_an,to_char(ngay_thuc_hien, 'DD-Mon-YY') as
	// ngay_thuc_hien,so_luong,gia,guest,thuoc_phong,checkin,checkout from
	// NHAN_ORDER_TU_TIVI where ngay_thuc_hien >= TO_TIMESTAMP('12/10/2015
	// 00:00:00','DD/MM/YYYY HH24:MI:SS') and ngay_thuc_hien <=
	// TO_TIMESTAMP('12/10/2015 23:59:59','DD/MM/YYYY HH24:MI:SS') and
	// dich_vu='DINING' order by ngay_thuc_hien;
	@SuppressWarnings("rawtypes")
	public List<RoomServiceOrderModel> getServiceOrderReport(String from,
			String to) {
		List<RoomServiceOrderModel> list = new ArrayList<RoomServiceOrderModel>();
		String sql = "select id,ten_mon_an,to_char(ngay_thuc_hien, 'DD-Mon-YY HH:MI AM') as ngay_thuc_hien,so_luong,gia,guest,thuoc_phong,to_char(checkin, 'DD-Mon-YY') as checkin, to_char(checkout, 'DD-Mon-YY') as checkout from NHAN_ORDER_TU_TIVI where ngay_thuc_hien >= TO_TIMESTAMP('"
				+ from
				+ " 00:00:00','DD/MM/YYYY HH24:MI:SS') and ngay_thuc_hien <= TO_TIMESTAMP('"
				+ to
				+ " 23:59:59','DD/MM/YYYY HH24:MI:SS') and dich_vu='DINING' order by ngay_thuc_hien desc";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				RoomServiceOrderModel order = new RoomServiceOrderModel();
				order.setId(result.getParam("id"));
				order.setName(result.getParam("ten_mon_an"));
				order.setOrderdate(result.getParam("ngay_thuc_hien"));
				order.setQuantity(result.getParam("so_luong"));
				order.setCurrency("US$");
				order.setAmount(result.getParam("gia"));
				order.setGuest(result.getParam("guest"));
				order.setRoom(result.getParam("thuoc_phong"));
				order.setCheckin(result.getParam("checkin"));
				order.setCheckout(result.getParam("checkout"));
				list.add(order);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static boolean addUserActivities(String des, String user) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subIn = new SubProParam(new String(des), SubProParam.IN);
		params.add(subIn);
		subIn = new SubProParam(new String(user), SubProParam.IN);
		params.add(subIn);
		try {
			params = broker.executeSubPro(ADD_UESR_ACTIVITIES, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public List<UserActivityModel> getUserActivityReport(String from, String to) {
		List<UserActivityModel> list = new ArrayList<UserActivityModel>();
		String sql = "select ID,DESCRIPTION,to_char(DATE_ACTION, 'DD-Mon-YY HH:MI AM') as DATE_ACTION,USERNAME from user_activities where date_action >= TO_TIMESTAMP('"
				+ from
				+ " 00:00:00','DD/MM/YYYY HH24:MI:SS') and date_action <= TO_TIMESTAMP('"
				+ to
				+ " 23:59:59','DD/MM/YYYY HH24:MI:SS') order by DATE_ACTION desc";
//		System.out.println(sql);
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				UserActivityModel user = new UserActivityModel();
				user.setId(result.getParam("ID"));
				user.setDescription(result.getParam("DESCRIPTION"));
				user.setDate(result.getParam("DATE_ACTION"));
				user.setUser(result.getParam("USERNAME"));
				list.add(user);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		ReportServiceDBI r = new ReportServiceDBI();

		System.out.println(r.getUserActivityReport("29/01/2015", "03/11/2015"));
	}
}
