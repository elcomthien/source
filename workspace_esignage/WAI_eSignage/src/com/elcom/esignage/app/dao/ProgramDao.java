package com.elcom.esignage.app.dao;

import java.util.Vector;

import com.elcom.esignage.app.util.DateHelper;
import com.elcom.esignage.app.dbi.ConnectionUtil;
import com.elcom.esignage.app.dbi.IMBroker;
import com.elcom.esignage.app.dbi.SubProParam;
import com.elcom.esignage.app.util.SQL;

public class ProgramDao {
	ConnectionUtil con = new ConnectionUtil();
	private static IMBroker broker = IMBroker.getInstance();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String spgetChanngeTemplates(String ipClient, String sernumber, String mac, String timereq, String ramtotal, String ram, String cputotal,
			String cpu, String hddtotal, String hdd, String sdcardtotal, String sdcard, String cursizefile, String totalsizefile) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, sernumber);
		param.add(1, mac);
		param.add(2, timereq);
		param.add(3, ramtotal);
		param.add(4, ram);
		param.add(5, cputotal);
		param.add(6, cpu);
		param.add(7, hddtotal);
		param.add(8, hdd);
		param.add(9, sdcardtotal);
		param.add(10, sdcard);
		param.add(11, cursizefile);
		param.add(12, ipClient);
		rs = con.getStringGenerals(SQL.sp_getChanngeTemplate, param, 1);
		return rs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getDownloadFileStb(String sernumber, String mac) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, sernumber);
		param.add(1, mac);
		rs = con.getStringGenerals(SQL.sp_getDownloadFileStb, param, 1);
		return rs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String downloadComplate(String sernumber, String mac, String contenid, String process) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, sernumber);
		param.add(1, mac);
		param.add(2, contenid);
		param.add(3, process);
		rs = con.getStringGenerals(SQL.sp_downloadComplate, param, 1);
		return rs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getPathFile(String contentid) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, contentid);
		rs = con.getStringGenerals(SQL.sp_getsuburldownload, param, 1);
		return rs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getCaptureCounterStb(String sernumber, String mac) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, sernumber);
		param.add(1, mac);
		rs = con.getStringGenerals(SQL.sp_getCaptureCounterStb, param, 1);
		return DateHelper.pareCapture(rs);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteContentstb(String sernumber, String mac) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, sernumber);
		param.add(1, mac);
		rs = con.getStringGenerals(SQL.sp_DelFileStb, param, 1);
		return DateHelper.stringToHTMLString(rs);
	}

	@SuppressWarnings("unchecked")
	public String spgetContentLayout(String sernumber, String mac, int playlistid, int changecontent) {
		String rs = "";
		try {
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam param = null;
			param = new SubProParam(new String(sernumber), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(mac), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(String.valueOf(changecontent)), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(String.valueOf(playlistid)), SubProParam.IN);
			params.add(param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(out_data);
			params = broker.executeSubPro(SQL.sp_getContentLayout, params);
			out_data = (SubProParam) params.get(4);
			rs = (String) out_data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	public String spgetInfoStb(String sernumber, String mac) {
		String rs = "";
		try {
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam param = null;
			param = new SubProParam(new String(sernumber), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(mac), SubProParam.IN);
			params.add(param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(out_data);

			params = broker.executeSubPro(SQL.sp_getInfoStb, params);
			out_data = (SubProParam) params.get(2);
			rs = (String) out_data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return rs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String spgetSchedule(String scheduleid, String sernumber, String mac) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, scheduleid);
		param.add(1, sernumber);
		param.add(2, mac);
		rs = con.getStringGenerals(SQL.sp_getSchedule, param, 1);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public String sploginstb(String sernumber, String mac) {
		String rs = "";
		try {
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam param = null;
			param = new SubProParam(new String(sernumber), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(mac), SubProParam.IN);
			params.add(param);

			SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
			params.add(out_data);

			params = broker.executeSubPro(SQL.sp_loginstb, params);
			out_data = (SubProParam) params.get(2);
			rs = (String) out_data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	public String getSchedulePeri(String sernumber, String mac) {
		String rs = "";
		try {
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam param = null;
			param = new SubProParam(new String(sernumber), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(mac), SubProParam.IN);
			params.add(param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(out_data);

			params = broker.executeSubPro(SQL.sp_getSchedulePeri, params);
			out_data = (SubProParam) params.get(2);
			rs = (String) out_data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return rs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String sp_regstb(String namein, String sernumber, String mac, String savelocalmedia, String savelocalfile) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, namein);
		param.add(1, sernumber);
		param.add(2, mac);
		param.add(3, savelocalmedia);
		param.add(4, savelocalfile);
		rs = con.getStringGenerals(SQL.sp_regstb, param, 1);
		return rs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String spgetDefauleHome(String sernumber, String mac) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, sernumber);
		param.add(1, mac);
		rs = con.getStringGenerals(SQL.sp_getDefauleHome, param, 1);
		return rs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String spiptvSubject() {
		String rs = "";
		Vector param = new Vector();
		param.add(0, "");
		rs = con.getStringGenerals(SQL.sp_iptvSubject, param, 1);
		return rs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String spiptvContentSubject(String idsubject) {
		String rs = "";
		Vector param = new Vector();
		param.add(0, idsubject);
		rs = con.getStringGenerals(SQL.sp_iptvContentSubject, param, 1);
		return rs;
	}

	public static void main(String[] args) {
		ProgramDao p = new ProgramDao();
		// String xml = p.spgetChanngeTemplates("172.16.9.100", "BIM001", "BIM001", "10:31:19", "1014908", "471576", "1500000", "1455000", "2031440",
		// "1759964", "3891204", "3790876", "0", "0");
		// try {
		// JSONObject xmlJSONObj = XML.toJSONObject(xml);
		// String jsonPrettyPrintString = xmlJSONObj.toString();
		// System.out.println(jsonPrettyPrintString);
		// } catch (Exception je) {
		// System.out.println(je.toString());
		// }
		// System.out.println(p.getPathFile("26"));
		System.out.println(p.spgetContentLayout("", "", 15, 0));
	}

}
