package com.elcom.adcenter.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.adcenter.broker.IMBroker;
import com.elcom.adcenter.broker.SubProParam;
import com.elcom.adcenter.cfg.Configuration;
import com.elcom.adcenter.cfg.ConfigurationLoader;
import com.elcom.adcenter.util.BoxUtil;
import com.elcom.adcenter.util.DateHelper;

public class ProgDao {
	// Refers the DB broker object
	private static IMBroker broker = IMBroker.getInstance();
	@SuppressWarnings("unused")
	private static Configuration config = null;
	// To log application
	private static Logger log = Logger.getLogger(ProgDao.class);
	// Read configuration params
	static {
		try {
			ConfigurationLoader loader = ConfigurationLoader.getInstance();
			config = loader.getConfiguration();
			Properties props = new Properties();
			FileInputStream fileinputstream = new FileInputStream(
					"Config/log4j.properties");
			props.load(fileinputstream);
			PropertyConfigurator.configure(props);
		} catch (IOException ex) {
			log.error(null, ex);
		}
	}

	@SuppressWarnings("unused")
	private static final String pattern = "MM/dd/yyyy HH:mm:ss";

	// =============================================
	public ProgDao() {
	}

	// =============================================
	public int getChanngeTemplate(String sernumber, String mac) {
		int num = 0;
		try {
			java.sql.ResultSet rs = broker.executeQuery(SQL.getChanngeTemplate);
			System.out.println(SQL.getChanngeTemplate);
			while (rs.next()) {
				num = new Integer(rs.getString("number")).intValue();
				if (num > 0) // co thay doi
					return 1;
			}
		} catch (Exception ex) {
		}
		return 0;
	}

	// -------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static String getStringGenerals(String query, Vector paramiv,
			int paramout) {
		String result = "";
		int size = paramiv.size();
		SubProParam out_data;
		/*
		 * log.info("query: " + query + " | param size: " + size); for(int ii =
		 * 0 ; ii < paramiv.size(); ii++) { log.info("pram " + ii + " = " +
		 * paramiv.get(ii)); }
		 */

		try {
			Vector<SubProParam> params;
			if (paramout == 1) {
				params = new Vector<SubProParam>(size + 1);
			} else
				params = new Vector<SubProParam>(size);
			SubProParam param = null;
			for (int i = 0; i < size; i++) {
				String para = (String) paramiv.get(i);
				param = new SubProParam(new String(para), SubProParam.IN);
				params.add(i, param);
			}
			if (paramout == 1) {
				out_data = new SubProParam(new String(), SubProParam.OUT);
				params.add(paramiv.size(), out_data);
			}

			params = broker.executeSubPro(query, params);

			if (paramout == 1) {
				out_data = (SubProParam) params.get(size);
				result = (String) out_data.getValue();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return result;
	}

	// -------------------------------------------------------
	@SuppressWarnings("unchecked")
	public String getStringContentGeneral(String query, String sernumber,
			String mac) {
		String result = "";
		log.info(query + "sernumber: " + sernumber + " | mac: " + mac);
		try {
			Vector<SubProParam> params = new Vector<SubProParam>(3);
			SubProParam param = null;
			param = new SubProParam(new String(sernumber), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(mac), SubProParam.IN);
			params.add(param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(out_data);

			params = broker.executeSubPro(query, params);
			out_data = (SubProParam) params.get(2);
			result = (String) out_data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return result;
	}

	// -------------------------------------------------------

	// -------------------------------------------------------
	@SuppressWarnings("unchecked")
	public String getStringContentGeneral2(String query, String sernumber,
			String mac, String idplaylist, String changeconten) {
		String result = "";
		log.info(query + "sernumber: " + sernumber + " | mac: " + mac
				+ " | idplaylist: " + idplaylist);
		try {
			Vector<SubProParam> params = new Vector<SubProParam>(5);
			SubProParam param = null;
			param = new SubProParam(new String(sernumber), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(mac), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(changeconten), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(idplaylist), SubProParam.IN);
			params.add(param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(out_data);

			params = broker.executeSubPro(query, params);
			out_data = (SubProParam) params.get(4);
			result = (String) out_data.getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return result;
	}

	// -------------------------------------------------------
	public String spgetContentLayout(String sernumber, String mac,
			int playlistid, int changecontent) {
		String result = "";

		result = getStringContentGeneral2(SQL.sp_getContentLayout, sernumber,
				mac, playlistid + "", changecontent + "");
		return result;
	}

	// -------------------------------------------------------

	// -------------------------------------------------------
	public String spgetInfoStb(String sernumber, String mac) {
		String result = "";
		result = getStringContentGeneral(SQL.sp_getInfoStb, sernumber, mac);
		return result;
	}

	// -------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String spgetSchedule(String scheduleid, String sernumber, String mac) {
		Vector param = new Vector(3);
		param.add(0, scheduleid);
		param.add(1, sernumber);
		param.add(2, mac);

		return getStringGenerals(SQL.sp_getSchedule, param, 1);
	}

	// -------------------------------------------------------
	public String getSchedulePeri(String sernumber, String mac) {
		String result = "";
		result = getStringContentGeneral(SQL.sp_getSchedulePeri, sernumber, mac);
		return result;
	}

	// -------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String spgetChanngeTemplates(String ipClient, String sernumber,
			String mac, String timereq, String ramtotal, String ram,
			String cputotal, String cpu, String hddtotal, String hdd,
			String sdcardtotal, String sdcard, String cursizefile,
			String totalsizefile) {

		Vector param = new Vector(13);
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
		// param.add(12,totalsizefile);
		param.add(12, ipClient);
		// System.out.println("totalsizefile: " + totalsizefile +
		// " | ipClient: " + ipClient);
		return getStringGenerals(SQL.sp_getChanngeTemplate, param, 1);
	}

	// -------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDownloadFileStb(String sernumber, String mac) {
		Vector param = new Vector(2);
		param.add(0, sernumber);
		param.add(1, mac);
		return getStringGenerals(SQL.sp_getDownloadFileStb, param, 1);
	}

	// -------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getCaptureCounterStb(String sernumber, String mac) {
		Vector param = new Vector(2);
		param.add(0, sernumber);
		param.add(1, mac);
		String result = getStringGenerals(SQL.getCaptureCounterStb, param, 1);
		return DateHelper.pareCapture(result);
	}

	// -------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String downloadComplate(String sernumber, String mac,
			String contenid, String process) {
		Vector param = new Vector(4);
		Vector paramtmp = new Vector(2);
		if (process.equals("Downloading") || process.equals("Fail")) {
			process = "0";
		}
		try {
			Double size = new Double(process);
			paramtmp.add(0, contenid);
			String namefile = getStringGenerals(SQL.getContentid, paramtmp, 1);
			String path = getStringGenerals(SQL.getsuburldownload, paramtmp, 1);
			/* lay dung luong file */
			namefile = path + namefile;
			File file = new File(namefile);
			double bytes = file.length();
			int per_cur = (int) Math.round((size * 100) / bytes);
			process = per_cur + "%";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		param.add(0, sernumber);
		param.add(1, mac);
		param.add(2, contenid);
		param.add(3, process);

		return getStringGenerals(SQL.sp_downloadComplate, param, 1);
	}

	// -------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteContentstb(String sernumber, String mac) {
		String result = "";
		Vector param = new Vector(2);
		param.add(0, sernumber);
		param.add(1, mac);
		result = getStringGenerals(SQL.sp_DelFileStb, param, 1);
		return result;
	}

	// -------------------------------------------------------
	public String sploginstb(String sernumber, String mac) {
		String result = "";
		result = getStringContentGeneral(SQL.sp_loginstb, sernumber, mac);
		return result;
	}

	// -------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String sp_regstb(String namein, String sernumber, String mac,
			String savelocalmedia, String savelocalfile) {
		Vector param = new Vector(5);
		param.add(0, namein);
		param.add(1, sernumber);
		param.add(2, mac);
		param.add(3, savelocalmedia);
		param.add(4, savelocalfile);

		return getStringGenerals(SQL.sp_regstb, param, 1);
	}

	// -------------------------------------------------------
	@SuppressWarnings("unchecked")
	public int spgetChanngeTemplate(String sernumber, String mac) {
		int num = 0;
		try {
			Vector<SubProParam> params = new Vector<SubProParam>(3);
			SubProParam param = null;
			param = new SubProParam(new String(sernumber), SubProParam.IN);
			params.add(param);

			param = new SubProParam(new String(mac), SubProParam.IN);
			params.add(param);

			SubProParam out_data = new SubProParam(new String(),
					SubProParam.OUT);
			params.add(out_data);

			params = broker.executeSubPro(SQL.sp_getChanngeTemplate, params);
			out_data = (SubProParam) params.get(2);
			num = new Integer((String) out_data.getValue()).intValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return num;
	}

	// ===========================================
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String spgetDefauleHome(String sernumber, String mac) {
		Vector param = new Vector(2);
		param.add(0, sernumber);
		param.add(1, mac);

		return getStringGenerals(SQL.sp_getDefauleHome, param, 1);
	}

	// =============================================
	// ===========================Ham danh cho eBop chay cho HN================
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String spiptvSubject() {
		Vector param = new Vector(1);
		param.add(0, "");

		return getStringGenerals(SQL.sp_iptvSubject, param, 1);
	}

	// =======================================================================
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String spiptvContentSubject(String idsubject) {
		Vector param = new Vector(1);
		param.add(0, idsubject);

		return getStringGenerals(SQL.sp_iptvContentSubject, param, 1);
	}

	// =======================================================================
	public String getTest(String in) {
		for (int i = 0; i < 1000; i++) {
			// System.out.println("getTest: " + i);
		}
		return in;
	}

	// ===========================TEST==========================

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateIpAdressBox(String ip, String mac) {
		Vector param = new Vector();
		param.add(0, ip);
		param.add(1, mac);
		return getStringGenerals(SQL.sp_updateIpAdress, param, 1);
	}

	// =======================================================================

	public static void main(String[] args) {
		ProgDao p = new ProgDao();
		String xml = p.getSchedulePeri("2001", "2001");
		System.out.println(xml);
	}
}
