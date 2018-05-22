package ehotel.admin.dbi;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ehotel.admin.Report.VodReportModel;

public class ReportServiceDBI {
	static IMBroker broker = IMBroker.getInstance();

	@SuppressWarnings("rawtypes")
	public List<VodReportModel> getStaticFilmReport(String month) {
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		String sql = "select CONTENTNAME,count(*) as quanlity, SAMOUNT, CASHCARD from vod_charge ";
		sql += "where SERVICETYPENAME = 'VOD' and to_char(STARTTIME,'mmyyyy')=" + month;
		sql += " group by CONTENTNAME, CASHCARD, SAMOUNT";
		System.out.println(sql);
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while(result.hasNext()){
					result.next();
					VodReportModel vod = new VodReportModel();
					vod.setName(result.getParam("CONTENTNAME"));
					vod.setPrice(result.getParam("SAMOUNT"));
					vod.setUnit(result.getParam("CASHCARD"));
					vod.setQuantity(Integer.parseInt(result.getParam("QUANLITY")));
					list.add(vod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List<VodReportModel> getMonthlyStaticReport(String year){
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		String sql = "select to_char(starttime,'MM/yyyy') monthly, count(*) AMOUNT , sum(samount) sotien " +
				"from vod_charge where servicetypename = 'VOD' and to_char(starttime,'yyyy') = "+ year +"  group by to_char(starttime,'MM/yyyy')";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while(result.hasNext()){
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
	public List<VodReportModel> getDailyStaticReport(String month){
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		String sql = "select to_char(starttime,'dd/mm/yyyy') daily, sum(samount) samount  from vod_charge " +
				"where to_char(starttime,'mmyyyy') = "+ month +" and servicetypename = 'VOD' group by to_char(starttime,'dd/mm/yyyy')";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while(result.hasNext()){
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
	
	public static void main(String[] args) {
		ReportServiceDBI r = new ReportServiceDBI();
		System.out.println(r.getDailyStaticReport("072014"));
	}

}
