package com.elcom.eodapp.media.livetv;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.media.util.Vasc;
import com.elcom.eodapp.media.broker.*;
import com.elcom.eodapp.media.common.*;
import com.elcom.eodapp.media.util.*;
import com.elcom.eodapp.media.livetv.SQL;
import com.elcom.eodapp.media.exception.*;
import com.elcom.eodapp.media.cfg.*;

import org.apache.log4j.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BrowserProgDao {
	private static final int BC_BROWSERINFO_SIZE = 10;
	private static final int BC_PROGRAMS_SIZE = 10;
	private static Configuration config = null;

	// Refers the DB broker object
	private static IMBroker broker = IMBroker.getInstance();
	// To log application
	private static final Logger logger = LogManager.getLogger(BrowserProgDao.class);
	// Read configuration params
	static {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		config = loader.getConfiguration();
	}

	private static final String pattern = "MM/dd/yyyy HH:mm:ss";

	public BrowserProgDao() {
	}

	// =====================================================================
	public String getClientURL(int channelid, String sernumber) {
		Vector params = new Vector(2);
		SubProParam param = null;
		param = new SubProParam(new String(), 1);
		params.add(param);
		param = new SubProParam(new BigDecimal(channelid), 0);
		params.add(param);

		params = broker.executeSubPro("BEGIN ? := vod.getUrlClientBc(?); END;", params);

		SubProParam paramOUT = (SubProParam) params.get(0);
		String clientUrl = paramOUT.getString();
		return clientUrl;
	}

	// =====================================================================

	public String getLiveTvSubject(String keystb, int fromRow, int noRows) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		BCSubject bcsubject = null;
		try {
			Vector params = new Vector(1);
			params.add(new String(keystb));

			Vector vResultSet = broker.executeSelect(SQL.sqlGetSubjects, params, noRows, fromRow);
			Vector aRow = null;
			for (int i = 2; i < vResultSet.size(); i++) {
				obj = new JSONObject();
				aRow = (Vector) vResultSet.get(i);
				bcsubject = new BCSubject();
				String subjectname = (String) aRow.get(1);
				subjectname = UnicodeConverter.decodeUnicode(subjectname);
				obj.put("id", (String) aRow.get(0));
				obj.put("subjectname", UnicodeConverter.encodeUnicode(subjectname));
				obj.put("picurl", (String) aRow.get(2));
				ja.add(i - 2, obj);
			}
		} catch (Exception ex) {
			String msg = "Error from the DB ";
			logger.error(msg, ex);
			throw new BcpccSysException(msg + ex.getMessage());
		}
		StringWriter out = new StringWriter();
		ja.writeJSONString(out);
		jsonText = out.toString();
		return jsonText;
	} // getLiveTvSubject
		// ======================================================================

	public String getLivetvChannelList(int channelid) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		String channelids = channelid + "";
		String sql;
		int inde = 0;
		inde = channelids.indexOf("1200");
		if (inde > 0) {
			channelid = new Integer(channelids.substring(0, channelids.indexOf("1200"))).intValue();
			sql = SQL.sqlGetChannels1200;
		} else
			sql = SQL.sqlGetChannels;

		try {

			Vector params = new Vector(1);
			params.add(new BigDecimal(channelid)); // 1 IN

			Vector vResultSet = broker.executeSelect(sql, params);

			BCChannelDataBrief bc = null;
			for (int i = 2; i < vResultSet.size(); i++) {
				obj = new JSONObject();
				Vector aRow = (Vector) vResultSet.get(i);
				obj.put("id", (String) aRow.get(0));
				obj.put("ovschannelid", (String) aRow.get(1));
				obj.put("channelname", (String) aRow.get(2));
				obj.put("channelnumber", (String) aRow.get(3));
				obj.put("price", (String) aRow.get(4));
				obj.put("urlplay", (String) aRow.get(5));
				obj.put("urlpic", (String) aRow.get(6));

				ja.add(i - 2, obj);
			}

		} catch (NumberFormatException ex) {
			String msg = "Error from the DB ";
			logger.error(msg, ex);
			throw new BcpccSysException(msg + ex.getMessage());
		}

		StringWriter out = new StringWriter();
		ja.writeJSONString(out);
		jsonText = out.toString();
		return jsonText;
	}

	// ---------------------------------------------------------------------------------------
	public int countLiveTv(int subjectid) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>(2);

		SubProParam param = null;
		param = new SubProParam(new String(), SubProParam.OUT);
		params.add(param); // 0 OUT

		param = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
		params.add(param); // 1 IN

		// Executes the DB stored procedure
		params = broker.executeSubPro(SQL.countLiveTv, params);
		// Get data returned by the stored procedure
		SubProParam paramOUT = (SubProParam) params.get(0);
		count = new java.lang.Integer(paramOUT.getString()).intValue();
		return count;
	}

	@SuppressWarnings({ "unchecked" })
	public String getListChannel(String subjectid) throws IOException {
		String jstext = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		SubProParam in = new SubProParam(new String(subjectid), SubProParam.IN);
		params.add(in);
		try {
			params = broker.executeSubPro(SQL.GET_CHANNEL_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 7) {
			obj = new JSONObject();
			obj.put("id", (String) outParam.get(i));
			obj.put("ovschannelid", (String) outParam.get(i + 1));
			obj.put("channelname", (String) outParam.get(i + 2));
			obj.put("channelnumber", (String) outParam.get(i + 3));
			obj.put("price", (String) outParam.get(i + 4));
			obj.put("urlplay", (String) outParam.get(i + 5));
			obj.put("urlpic", (String) outParam.get(i + 6));

			ja.add(i / 7, obj);
		}
		StringWriter out = new StringWriter();
		ja.writeJSONString(out);
		jstext = out.toString();
		return jstext;
	}

}
