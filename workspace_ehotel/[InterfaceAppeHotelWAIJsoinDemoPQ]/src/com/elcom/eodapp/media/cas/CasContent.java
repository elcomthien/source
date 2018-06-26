package com.elcom.eodapp.media.cas;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.media.util.*;
import com.elcom.eodapp.media.broker.IMBroker;
import com.elcom.eodapp.media.common.BCChannelDataBrief;
import com.elcom.eodapp.media.common.StbVO;
import com.elcom.eodapp.media.common.VascVO;
import com.elcom.eodapp.media.common.VodCtnVO;
import com.elcom.eodapp.media.cas.SQL;
import com.elcom.eodapp.media.cfg.Configuration;
import com.elcom.eodapp.media.cfg.ConfigurationLoader;
import com.elcom.eodapp.media.exception.BcpccSysException;

public class CasContent {
	private static IMBroker broker = IMBroker.getInstance();
	private static final Logger logger = LogManager.getLogger(CasContent.class);
	private static final String pattern = "MM/dd/yyyy HH:mm:ss";
	private static Configuration config = null;

	static {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		config = loader.getConfiguration();
	}

	public CasContent() {
	}

	public int autLogin(String keystb) {
		int result = 0;

		if (config.certificate_vas <= 0)
			return 1; // bo qua xac thuc.
		ArrayList rsl = new ArrayList(); // Collection of Results
		Vector vResultSet = new Vector();
		VascVO vascVo = null;
		// lay du lieu tu DB ra de thuc hien chung thuc.
		try {
			Vector params = new Vector(2);

			SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
			params.add(ketstb_); // 1 IN

			SubProParam out_data = new SubProParam(new Vector(), "STRING_ARR", SubProParam.OUT);
			params.add(out_data);

			params = broker.executeSubPro(SQL.sqlgetInfoVasc, params);

			out_data = (SubProParam) params.get(1);
			Vector vRs = out_data.getVector();

			for (int i = 0; i < vRs.size(); i = i + 4) {
				vascVo = new VascVO();

				vascVo.setKeystb((String) vRs.get(i));
				vascVo.setIpstb((String) vRs.get(i + 1));
				vascVo.setVascuser((String) vRs.get(i + 2));
				vascVo.setVascpass((String) vRs.get(i + 3));

			}
		} catch (Exception ex) {
			String msg = "Error from the DB ";
			logger.error(msg, ex);
			System.out.println(ex.getMessage());
		}
		return 1;
	}

	/**
	 * 
	 * @param keystb
	 * @return
	 */
	public boolean checkStbOnline(String keystb, String ipin) // 0 la offline, 1 la online
	{
		boolean keystbonline = false;
		Vector params = new Vector(3);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		SubProParam keystb_ = new SubProParam(new String(keystb), SubProParam.IN);
		params.add(keystb_);

		SubProParam ip_ = new SubProParam(new String(ipin), SubProParam.IN);
		params.add(ip_);

		params = broker.executeSubPro(SQL.getIpStb, params);
		out_data = (SubProParam) params.get(0);
		String ip = out_data.getString();
		System.out.println("CasContent.checkStbOnline(" + keystb + ") => ip: " + ip);
		// keystbonline = DateHelper.pingStb(ip);
		keystbonline = false;
		return keystbonline;
	}

	/**
	 * 
	 * @param keystb
	 * @return: StbVO
	 * @throws IOException
	 */
	public String login(String keystb, String ip) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();

		String xml = "";
		Vector vRs = new Vector();
		StbVO stbvo = new StbVO();
		Vector params = new Vector(3);

		/* Kiem tra xem voi key nay stb hien da co dung chua */
		boolean keystbonline = checkStbOnline(keystb, ip);

		if (keystbonline) {
			stbvo.setKeystb(keystb);
			stbvo.setFolio("-1");
		} else {
			SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
			params.add(out_data);

			SubProParam keystb_ = new SubProParam(new String(keystb), SubProParam.IN);
			params.add(keystb_);

			SubProParam ip_ = new SubProParam(new String(ip), SubProParam.IN);
			params.add(ip_);

			params = broker.executeSubPro(SQL.sqllogineHotel, params);
			// Get data returned by the stored procedure
			SubProParam paramOUT = (SubProParam) params.get(0);

			String out = paramOUT.getString();
			if (out.equals("0")) { // khong co phong va khong co key
				stbvo.setKeystb("0");
				stbvo.setFolio("0");
			} else if (out.equals("-1")) // key dung nhung chua co phong
			{
				stbvo.setKeystb(keystb);
				stbvo.setFolio("-1");
			} else { // login thanh cong
				stbvo.setKeystb(keystb);
				stbvo.setFolio(out);
			}
		}// ens else
		/*
		 * xml = "<?xml version='1.0' encoding='UTF-8'?>\r\n<Stb>\r\n"; xml = xml + "<item>"; xml = xml + "<key>" + stbvo.getKeystb() +
		 * "</key>\r\n"; xml = xml + "<room>" + stbvo.getFolio() + "</room>\r\n"; xml = xml + "</item>"; xml = xml + "</Stb>";
		 */
		obj.put("key", stbvo.getKeystb());
		obj.put("room", stbvo.getFolio());
		ja.add(obj);
		StringWriter out = new StringWriter();
		ja.writeJSONString(out);
		jsonText = out.toString();
		return jsonText;

	}

	// ------------------------------------------------------------------
	public int register(String keystb, String folio, String ip, String vascuser, String vascpass) {
		int retur = -9999;
		String ip_ = "localhost";
		Vector params = new Vector(5);

		// Buoc dang ky vao server ehotel
		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		SubProParam keystb_ = new SubProParam(new String(keystb), SubProParam.IN);
		params.add(keystb_);

		SubProParam folio_ = new SubProParam(new String(folio), SubProParam.IN);
		params.add(folio_);

		if (ip != null)
			ip_ = ip;
		SubProParam ipi = new SubProParam(new String(ip_), SubProParam.IN);
		params.add(ipi);

		SubProParam vascuser_ = new SubProParam(new String(vascuser), SubProParam.IN);
		params.add(vascuser_);

		SubProParam vascpass_ = new SubProParam(new String(vascpass), SubProParam.IN);
		params.add(vascpass_);

		params = broker.executeSubPro(SQL.sqlsetFolioStbVasc, params);
		out_data = (SubProParam) params.get(0);
		String Rs = out_data.getString();
		retur = new Integer(Rs).intValue();
		return retur;
	}

	// ------------------------------------------------------------------
	// ------------------------------------------------------------------
	public String setKeyStbIntoRoom(String keystb, String folio, String ip) {
		int retur = -9999;
		String ip_ = "localhost";
		Vector params = new Vector(5);

		// Buoc dang ky vao server ehotel
		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		SubProParam keystb_ = new SubProParam(new String(keystb), SubProParam.IN);
		params.add(keystb_);

		SubProParam folio_ = new SubProParam(new String(folio), SubProParam.IN);
		params.add(folio_);

		SubProParam ipi = new SubProParam(new String(ip), SubProParam.IN);
		params.add(ipi);

		params = broker.executeSubPro(SQL.sqlsetKeyStbIntoRoom, params);
		out_data = (SubProParam) params.get(0);
		String Rs = out_data.getString();

		return Rs;
	}

	// ------------------------------------------------------------------
	/**
	 * 
	 * @param keystb
	 * @param folio
	 * @return -1 phong nay chua co tren he thong, hay bi nhap sai phong
	 */
	public int register(String keystb, String folio, String ip) {
		int retur = -9999;
		String ip_ = "localhost";
		Vector params = new Vector(3);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		SubProParam keystb_ = new SubProParam(new String(keystb), SubProParam.IN);
		params.add(keystb_);

		SubProParam folio_ = new SubProParam(new String(folio), SubProParam.IN);
		params.add(folio_);

		if (ip != null)
			ip_ = ip;
		SubProParam ipi = new SubProParam(new String(ip_), SubProParam.IN);
		params.add(ipi);

		params = broker.executeSubPro(SQL.sqlsetFolioStb, params);
		out_data = (SubProParam) params.get(0);
		String Rs = out_data.getString();
		retur = new Integer(Rs).intValue();
		return retur;
	}

	// ---------------------------------------------------------------------------------
	/**
	 * 
	 * @param keystb
	 * @return
	 */
	public int setLang(String keystb, int langid) {
		int retur = 1;
		Vector params = new Vector(2);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		SubProParam keystb_ = new SubProParam(new String(keystb), SubProParam.IN);
		params.add(keystb_);

		SubProParam langid_ = new SubProParam(new BigDecimal(langid), SubProParam.IN);
		params.add(langid_);

		params = broker.executeSubPro(SQL.sqlsetLang, params);
		out_data = (SubProParam) params.get(0);
		String Rs = out_data.getString();
		retur = new Integer(Rs).intValue();
		return retur;
	}

	/*
	 * sernum: s/n cua stb id_content: id cua contnet type: VOD,LIVE,MOD
	 */
	public int statics(String sernum, int id_content, String type) {
		int retur = 1;
		Vector params = new Vector(3);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);
		SubProParam keystb_ = new SubProParam(new String(sernum), SubProParam.IN);
		params.add(keystb_);
		SubProParam id_content_ = new SubProParam(new BigDecimal(id_content), SubProParam.IN);
		params.add(id_content_);
		SubProParam type_ = new SubProParam(new String(type), SubProParam.IN);
		params.add(type_);

		params = broker.executeSubPro(SQL.sqlcheckpay, params);
		out_data = (SubProParam) params.get(0);
		String Rs = out_data.getString();
		retur = new Integer(Rs).intValue();
		return retur;
	}

	public String getLangs(String keystb, String ip) {
		String Rs = "";
		Vector params = new Vector(3);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);
		SubProParam keystb_ = new SubProParam(new String(keystb), SubProParam.IN);
		params.add(keystb_);
		SubProParam ip_ = new SubProParam(new String(ip), SubProParam.IN);
		params.add(ip_);

		params = broker.executeSubPro(SQL.sqlgetLangs, params);
		out_data = (SubProParam) params.get(0);
		Rs = out_data.getString();

		return Rs;
	}

	public String getStbftpapk(String sernum) {
		String Rs = "";
		Vector params = new Vector(2);

		SubProParam keystb_ = new SubProParam(new String(sernum), SubProParam.IN);
		params.add(keystb_);

		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);

		params = broker.executeSubPro(SQL.sqlstbftpapk, params);
		out_data = (SubProParam) params.get(1);
		Rs = out_data.getString();
		return Rs;
	}

	public String updatestatusStbftpapk(String sernum, String idapk) {
		String Rs = "";
		Vector params = new Vector(2);

		SubProParam keystb_ = new SubProParam(new String(sernum), SubProParam.IN);
		params.add(keystb_);
		SubProParam idapk_ = new SubProParam(new String(idapk), SubProParam.IN);
		params.add(idapk_);

		broker.executeSubPro(SQL.sqlupdatestatusstbftpapk, params);
		return "OK";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updatestatusbox(String keystb, String status) {
		String Rs = "";
		Vector params = new Vector(2);
		SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
		params.add(out_data);
		SubProParam keystb_ = new SubProParam(new String(keystb), SubProParam.IN);
		params.add(keystb_);
		SubProParam status_ = new SubProParam(new BigDecimal(status), SubProParam.IN);
		params.add(status_);

		params = broker.executeSubPro(SQL.sqlupdatestatusbox, params);
		out_data = (SubProParam) params.get(0);
		Rs = out_data.getString();
		return Rs;
	}

	public HashMap<String, String> getSystemDate() {
		HashMap<String, String> map = new HashMap<String, String>();
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		year.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		SimpleDateFormat month = new SimpleDateFormat("MM");
		month.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		SimpleDateFormat day = new SimpleDateFormat("dd");
		day.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		SimpleDateFormat hour = new SimpleDateFormat("HH");
		hour.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		SimpleDateFormat minute = new SimpleDateFormat("mm");
		minute.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		SimpleDateFormat second = new SimpleDateFormat("ss");
		second.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		map.put("year", year.format(new Date()));
		map.put("month", month.format(new Date()));
		map.put("day", day.format(new Date()));
		map.put("hour", hour.format(new Date()));
		map.put("minute", minute.format(new Date()));
		map.put("second", second.format(new Date()));
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> getAllInfo(String keystb, String ip) {
		Map<String, Object> map = new HashMap<String, Object>();
		Vector params = new Vector(3);

		SubProParam in = new SubProParam(new String(keystb), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(ip), SubProParam.IN);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(subOut);

		try {
			params = broker.executeSubPro(SQL.sqlgetAllInfo, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
//		for (int i = 0; i < outParam.size(); i++)
//			System.out.println(i + "---" + outParam.get(i));

		Map<String, Object> wlc = new HashMap<String, Object>();
		wlc.put("room", outParam.get(0));
		wlc.put("guest", outParam.get(1));
//		int countLang = Integer.parseInt(outParam.get(2)) * 4;
//		List<HashMap<String, String>> listLang = new ArrayList<HashMap<String, String>>();
//		for (int i = 3; i < 3 + countLang; i += 4) {
//			HashMap<String, String> lang = new HashMap<String, String>();
//			lang.put("id", outParam.get(i));
//			lang.put("name", outParam.get(i + 1));
//			lang.put("icon", outParam.get(i + 2));
//			lang.put("code", outParam.get(i + 3));
//			listLang.add(lang);
//		}
//		wlc.put("lang", listLang);
		wlc.put("welcommusic", outParam.get(2));
		wlc.put("welcomvideo", outParam.get(3));
		wlc.put("background", outParam.get(4));
		wlc.put("logo", outParam.get(5));
		wlc.put("logosmall", outParam.get(6));
		wlc.put("background_movie", outParam.get(7));
		wlc.put("background_music", outParam.get(8));
//		wlc.put("welcome_01", outParam.get(8));
//		wlc.put("welcome_02", outParam.get(9));
//		wlc.put("message_order_vn", outParam.get(10));
//		wlc.put("message_order_en", outParam.get(11));
//		wlc.put("message_order_start", outParam.get(12));
//		wlc.put("message_order_stop", outParam.get(13));
		map.put("welcome", wlc);
		List<Map<String, Object>> listParent = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listChild = new ArrayList<Map<String, Object>>();
//		int countParent = Integer.parseInt(outParam.get(7));
		int countChild = 0;
		for (int i = 9; i < outParam.size(); i += 6) {
			Map<String, Object> parent = new HashMap<String, Object>();
			parent.put("id", outParam.get(i));
			parent.put("name", outParam.get(i + 1));
			parent.put("image", outParam.get(i + 2));
			parent.put("url", outParam.get(i + 3));
			parent.put("code", outParam.get(i + 4));
			countChild = Integer.parseInt(outParam.get(i + 5));
			listChild = new ArrayList<Map<String, Object>>();
			if (countChild > 0) {
				for (int j = i + 6; j < i + 6 + countChild * 5; j += 5) {
					Map<String, Object> child = new HashMap<String, Object>();
					child.put("id", outParam.get(j));
					child.put("name", outParam.get(j + 1));
					child.put("image", outParam.get(j + 2));
					child.put("url", outParam.get(j + 3));
					child.put("code", outParam.get(j + 4));
					listChild.add(child);
				}
				i += countChild * 5;
			}
			parent.put("listchild", listChild);
			listParent.add(parent);
		}
		map.put("service", listParent);
		return map;
	}

	public static void main(String[] args) {
		CasContent c = new CasContent();
		System.out.println(c.getAllInfo("2014", "0.0.0.0"));

	}
}
