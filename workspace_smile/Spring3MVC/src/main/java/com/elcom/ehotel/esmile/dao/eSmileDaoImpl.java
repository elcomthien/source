package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.DataPostRatingModel;
import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.model.SelectedPostRatingModel;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.SQL;

public class eSmileDaoImpl implements eSmileDao {

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addLayout(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_desc()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_direction()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_url()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_html()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getNegative()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getNeutral()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPositive()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_LAYOUT_URL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(11);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.EDIT_LAYOUT, params,
				"userid,layoutid,layoutname,layoutdesc,direction,sessionid,url,html,negative,neutral,positive",
				ConvertUtil.convertToInteger(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "add", "layout", param.getLayout_id(), param.getLayout_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> editLayout(Params param) {
		int rs = -1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_desc()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_url()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_change()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getNegative()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getNeutral()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPositive()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_LAYOUT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(10);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.EDIT_LAYOUT, params,
				"layoutid,layoutname,layoutdesc,layouturl,layoutchange,sessionid,userid,negative,neutral,positive", rs);
		if (rs == 1) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "edit", "layout", param.getLayout_id(), param.getLayout_name());
		} else if (rs == -2) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> updateStatusLayout(Params param) {
		int rs = -1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_STATUS_LAYOUT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.UPDATE_STATUS_LAYOUT, params, "sessionid,userid,layoutid,status,", rs);
		if (rs == 1) {
			map.put("status_code", 200);
			map.put("message", "OK");
		} else if (rs == -2) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> deleteLayout(Params param) {
		int rs = -1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_LAYOUT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.DELETE_LAYOUT, params, "layoutid,sessionid", rs);
		if (rs == 1) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "delete", "layout", param.getLayout_id(), param.getLayout_name());
		} else if (rs == -2) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getLayoutId() {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LAYOUT_ID, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		map.put("layout_id", rs);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getLayout(String userid, String layoutid, String sessionid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(userid), 0);
		params.add(in);
		in = new SubProParam(new String(layoutid), 0);
		params.add(in);
		in = new SubProParam(new String(sessionid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LAYOUT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_LAYOUT, params, "userid,layoutid,sessionid", outParam.size() / 4);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < outParam.size(); i += 4) {
			HashMap<String, String> layout = new HashMap<String, String>();
			layout.put("layout_id", outParam.get(i));
			layout.put("layout_name", outParam.get(i + 1));
			layout.put("layout_url", outParam.get(i + 2));
			layout.put("layout_change", outParam.get(i + 3));
			list.add(layout);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("layouts", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addPage(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getPage_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPage_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPage_url()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPage_change()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_PAGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_PAGE, params, "id,name,parentid,layoutid,url,change", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addObjectContent(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getObj_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getObj_idcon()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPage_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getObj_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getObj_type()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getObj_title()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getObj_string()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_OBJECT_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_OBJECT_CONTENT, params,
				"objectid,objectidcon,pageid,name,type,title,string,image,layoutid", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addObject(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getObj_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getObj_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getObj_type()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getObj_text()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPage_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_OBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_OBJECT, params, "objectid,objectname,type,text,pageid,layoutid",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getSubjectTemplate(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_TEMPLATE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_SUBJECT_TEMPLATE, params, "parentid,sessionid", outParam.size() / 3);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> sub = new HashMap<String, String>();
			sub.put("subject_id", outParam.get(i));
			sub.put("subject_name", outParam.get(i + 1));
			sub.put("subject_image", outParam.get(i + 2));
			list.add(sub);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("subjects", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getTemplate(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSubject_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_TEMPLATE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_TEMPLATE, params, "subjectid,sessionid", outParam.size() / 4);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < outParam.size(); i += 4) {
			HashMap<String, String> sub = new HashMap<String, String>();
			sub.put("item_id", outParam.get(i));
			sub.put("item_name", outParam.get(i + 1));
			sub.put("item_image", outParam.get(i + 2));
			sub.put("item_url", outParam.get(i + 3));
			list.add(sub);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("template", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addAccount(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPassword()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getFull_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRole_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getAddress()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getAvatar()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_mail()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_ACCOUNT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(11);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_ACCOUNT, params,
				"userid,username,pass,fullname,status,roleid,zoneid,address,avatar,email,sessionid", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("0")) {
			map.put("status_code", 400);
			map.put("message", "Account already exists");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request timeout");
		} else if (rs.equals("-3")) {
			map.put("status_code", 500);
			map.put("message", "Permission denied");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "add", "account", param.getUser_id(), param.getUser_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> editAccount(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getFull_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRole_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getAddress()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getAvatar()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_mail()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_ACCOUNT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(11);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.EDIT_ACCOUNT, params,
				"userid,parent,username,fullname,status,roleid,zoneid,address,avatar,email,sessionid", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Requst timeout");
		} else if (rs.equals("-3")) {
			map.put("status_code", 500);
			map.put("message", "Permission denied");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "edit", "account", param.getUser_id(), param.getUser_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getRole(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_ROLE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_ROLE, params, "sessionid,parentid", outParam.size());
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> role = new HashMap<String, Object>();
		List<HashMap<String, Object>> listper = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> per = new HashMap<String, Object>();
		/*
		 * List<HashMap<String, Object>> listper = new ArrayList<HashMap<String, Object>>(); HashMap<String, Object> per = new
		 * HashMap<String, Object>(); int count = ConvertUtil.convertToInteger(outParam.get(0)); for (int i = 1; i < outParam.size(); i +=
		 * 2) { role = new HashMap<String, Object>(); role.put("role_id", outParam.get(i)); role.put("role_name", outParam.get(i + 1));
		 * listper = new ArrayList<HashMap<String, Object>>(); for (int j = i + 2; j < i + 1 + count * 3; j += 3) { per = new
		 * HashMap<String, Object>(); per.put("privilege_id", outParam.get(j)); per.put("privilege_name", outParam.get(j + 1));
		 * per.put("checked", outParam.get(j + 2)); listper.add(per); } role.put("privileges", listper); list.add(role); i = i + 2 + count *
		 * 3; }
		 */
		int count = ConvertUtil.convertToInteger(outParam.get(0));
		for (int i = 1; i < 1 + count * 3; i += 3) {
			role = new HashMap<String, Object>();
			role.put("role_id", outParam.get(i));
			role.put("role_name", outParam.get(i + 1));
			role.put("privileges", outParam.get(i + 2).split(","));
			list.add(role);
		}
		for (int i = 1 + count * 3; i < outParam.size(); i += 3) {
			per = new HashMap<String, Object>();
			per.put("privilege_id", outParam.get(i));
			per.put("privilege_code", outParam.get(i + 1));
			per.put("privilege_name", outParam.get(i + 2));
			listper.add(per);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("list_role", list);
		map.put("list_privilege", listper);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> postRating(Params param) {
		String rs = "-1";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getType()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDevice()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStart_time()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStop_time()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getTags())), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_RATING_LAYOUT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.POST_RATING_LAYOUT, params, "layoutid,type,device,start,stop,tags",
				ConvertUtil.convertToInteger(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
			return map;
		} else if (rs.equals("-2")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
			return map;

		}
		for (int i = 0; i < param.getData().size(); i++) {
			postRatingData(rs, param.getData().get(i));
		}
		map.put("status_code", 200);
		map.put("message", "OK");
		return map;
	}

	public void postRatingData(String ratingid, DataPostRatingModel data) {
		for (int i = 0; i < data.getSelected().size(); i++) {
			postRatingData(ratingid, data.getPage_id(), data.getSelected().get(i));
		}
	}

	@SuppressWarnings("unchecked")
	public void postRatingData(String ratingid, String pageid, SelectedPostRatingModel select) {

		String rs = "-1";
		// HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(ratingid), 0);
		params.add(in);
		in = new SubProParam(new String(pageid), 0);
		params.add(in);
		in = new SubProParam(new String(select.getObject_id()), 0);
		params.add(in);
		in = new SubProParam(new String(select.getObject_type()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_RATING_PAGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.POST_RATING_PAGE, params, "ratingid,pageid,objectid,objecttype",
				ConvertUtil.convertToInteger(rs));
		String value = "";
		for (int i = 0; i < select.getSelected_id().length; i++) {
			if (select.getSelected_value().length == 0)
				value = "No Value";
			else
				value = select.getSelected_value()[i];
			postRatingSelected(rs, select.getSelected_id()[i], value, select.getPoint()[i]);
		}
	}

	@SuppressWarnings("unchecked")
	public void postRatingSelected(String ratingid, String id, String value, String point) {

		String rs = "-1";
		// HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(ratingid), 0);
		params.add(in);
		in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(value), 0);
		params.add(in);
		in = new SubProParam(new String(point), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_RATING_VOTE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.POST_RATING_VOTE, params, "ratingid,selectid,selectvalue,point",
				ConvertUtil.convertToInteger(rs));
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getListZone(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_ZONE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_LIST_ZONE, params, "sessionid,parentid", outParam.size());
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (outParam.size() == 0) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("list_zone", list);
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		for (int i = 0; i < outParam.size(); i += 2) {
			HashMap<String, String> zone = new HashMap<String, String>();
			zone.put("zone_id", outParam.get(i));
			zone.put("zone_name", outParam.get(i + 1));
			list.add(zone);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("list_zone", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> resetPassword(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPassword()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getConfirm_password()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.RESET_PASSWORD, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.RESET_PASSWORD, params, "sessionid,userid,password,confirm_password",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> deleteAccount(Params param, String listuser) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(listuser), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_ACCOUNT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.DELETE_ACCOUNT, params, "sessionid,userid,listuser", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("-3")) {
			map.put("status_code", 500);
			map.put("message", "Permission denied");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "delete", "account", param.getUser_id(), listuser);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getSession(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SESSION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_SESSION, params, "sessionid", outParam.size() / 5);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < outParam.size(); i += 5) {
			HashMap<String, String> session = new HashMap<String, String>();
			session.put("id", outParam.get(i));
			session.put("session_id", outParam.get(i + 1));
			session.put("session_name", outParam.get(i + 2));
			session.put("update_time", outParam.get(i + 3));
			session.put("type", outParam.get(i + 4));
			list.add(session);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("session", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> updateSession(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_SESSION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.UPDATE_SESSION, params, "sessionid,status", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getInfoFilter(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_INFO_FILTER, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_INFO_FILTER, params, "sessionid,userid", outParam.size());
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<String> listdevice = new ArrayList<String>();
		List<HashMap<String, Object>> listtags = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> listzone = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> listlayout = new ArrayList<HashMap<String, Object>>();
		int count = 0;
		HashMap<String, Object> tags = new HashMap<String, Object>();
		HashMap<String, Object> zone = new HashMap<String, Object>();
		HashMap<String, Object> layout = new HashMap<String, Object>();
		for (int i = 0; i < outParam.size(); i += 2) {
			if (outParam.get(i).equals("device"))
				listdevice.add(outParam.get(i + 1));
			else if (outParam.get(i).equals("tags")) {
				tags = new HashMap<String, Object>();
				tags.put("tag_id", outParam.get(i + 1));
				tags.put("tag_name", outParam.get(i + 2));
				i++;
				listtags.add(tags);
			} else {
				zone = new HashMap<String, Object>();
				zone.put("zone_id", outParam.get(i));
				zone.put("zone_name", outParam.get(i + 1));
				count = ConvertUtil.convertToInteger(outParam.get(i + 2));
				for (int j = i + 3; j < i + 3 + count * 2; j += 2) {
					layout = new HashMap<String, Object>();
					layout.put("layout_id", outParam.get(j));
					layout.put("layout_name", outParam.get(j + 1));
					listlayout.add(layout);
				}
				zone.put("layout", listlayout);
				listzone.add(zone);
				i = i + 1 + count * 2;
			}
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("device", listdevice);
		map.put("tags", listtags);
		map.put("zone", listzone);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addDevice(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSerinumber()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDevice_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDevice_ip()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLatitude()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLongitude()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getAddress()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLocation_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_DEVICE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(11);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_DEVICE, params,
				"userid,serinumber,name,ip,status,latitube,longitube,address,locationid,layoutid,sessionid", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "add", "device", param.getSerinumber(), param.getDevice_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getFeedbackLayout(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_FEEDBACK_LAYOUT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_FEEDBACK_LAYOUT, params, "sessionid,userid", outParam.size() / 11);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (outParam.size() == 0) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("feedback", list);
		} else if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		HashMap<String, String> app = new HashMap<String, String>();
		app.put("android", outParam.get(0));
		app.put("ios", outParam.get(1));
		for (int i = 2; i < outParam.size(); i += 11) {
			HashMap<String, String> layout = new HashMap<String, String>();
			layout.put("layout_id", outParam.get(i));
			layout.put("layout_name", outParam.get(i + 1));
			layout.put("layout_desc", outParam.get(i + 2));
			layout.put("layout_url", outParam.get(i + 3));
			layout.put("zone_id", outParam.get(i + 4));
			layout.put("zone_name", outParam.get(i + 5));
			layout.put("last_update", outParam.get(i + 6));
			layout.put("update_by", outParam.get(i + 7));
			layout.put("status", outParam.get(i + 8));
			layout.put("email", outParam.get(i + 9));
			layout.put("respone", outParam.get(i + 10));
			list.add(layout);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("feedback", list);
		map.put("app", app);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addPlugin(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_image()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_text()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_type()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getWebsite()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getContact_email()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_PLUGIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_PLUGIN, params, "sessionid,userid,id,name,image,text,type,website,email",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			for (int i = 0; i < param.getApp_object().size(); i++)
				addObjectPlugin(param.getApp_id(), param.getApp_object().get(i).getObject_id(), param.getApp_object().get(i)
						.getObject_name(), param.getApp_object().get(i).getObject_image());
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "add", "plugin", param.getApp_id(), param.getApp_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public void addObjectPlugin(String id, String objid, String objname, String objimage) {
		String rs = "0";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(objid), 0);
		params.add(in);
		in = new SubProParam(new String(objname), 0);
		params.add(in);
		in = new SubProParam(new String(objimage), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_OBJECT_PLUGIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_OBJECT_PLUGIN, params, "appid,objectid,name,image", Integer.parseInt(rs));
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPlugin(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_PLUGIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_PLUGIN, params, "sessionid,userid", outParam.size());

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> listobj = new ArrayList<HashMap<String, Object>>();
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("plugin", list);
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		int count = 0;
		for (int i = 0; i < outParam.size(); i += 9) {
			HashMap<String, Object> plugin = new HashMap<String, Object>();
			listobj = new ArrayList<HashMap<String, Object>>();
			plugin.put("app_id", outParam.get(i));
			plugin.put("app_name", outParam.get(i + 1));
			plugin.put("app_image", outParam.get(i + 2));
			plugin.put("app_text", outParam.get(i + 3));
			plugin.put("app_type", outParam.get(i + 4));
			plugin.put("website", outParam.get(i + 5));
			plugin.put("contact_email", outParam.get(i + 6));
			plugin.put("status", outParam.get(i + 7));
			count = ConvertUtil.convertToInteger(outParam.get(i + 8));
			for (int j = i + 9; j < i + 9 + count * 3; j += 3) {
				HashMap<String, Object> obj = new HashMap<String, Object>();
				obj.put("object_id", outParam.get(j));
				obj.put("object_name", outParam.get(j + 1));
				obj.put("object_image", outParam.get(j + 2));
				listobj.add(obj);
			}
			plugin.put("app_object", listobj);
			list.add(plugin);
			i = i + count * 3;
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("plugin", list);

		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> editPlugin(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_image()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_text()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_type()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getWebsite()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getContact_email()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_PLUGIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(10);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.EDIT_PLUGIN, params,
				"sessionid,userid,id,name,image,text,type,website,email,status", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			for (int i = 0; i < param.getApp_object().size(); i++)
				addObjectPlugin(param.getApp_id(), param.getApp_object().get(i).getObject_id(), param.getApp_object().get(i)
						.getObject_name(), param.getApp_object().get(i).getObject_image());
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "edit", "plugin", param.getApp_id(), param.getApp_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> deletePlugin(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_PLUGIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.DELETE_PLUGIN, params, "sessionid,userid,id", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("0")) {
			map.put("status_code", 500);
			map.put("message", "Permission denied");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "delete", "plugin", param.getApp_id(), param.getApp_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addPluginEmail(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getFrom()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getTo())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getCc())), 0);
		params.add(in);
		in = new SubProParam(new String(param.getAuto_send()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getContent()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_PLUGIN_EMAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_PLUGIN_EMAIL, params, "sessionid,appid,subject,from,to,cc,auto,content",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> boxLogin(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSerinumber()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLatitude()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLongitude()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.BOX_LOGIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.BOX_LOGIN, params, "serinumber,latitude,longitude", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Device not exist");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("session_id", rs);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> boxGetLink(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSerinumber()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.BOX_GET_LINK, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.BOX_GET_LINK, params, "sessionid,serinumber", 1);
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("layout_html", rs);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> postPlugin(Params param) {
		String rs = "-1";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getDevice()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getType()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getText()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_RATING_PLUGIN, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.POST_RATING_PLUGIN, params, "device,appid,type,text",
				ConvertUtil.convertToInteger(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
			return map;
		} else if (rs.equals("-2")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
			return map;

		}
		for (int i = 0; i < param.getSelected().size(); i++) {
			postPluginSelected(rs, param.getSelected().get(i).getId(), param.getSelected().get(i).getName(), param.getSelected().get(i)
					.getImage());
		}
		map.put("status_code", 200);
		map.put("message", "OK");
		return map;
	}

	@SuppressWarnings("unchecked")
	public void postPluginSelected(String ratingid, String id, String name, String image) {

		String rs = "-1";
		// HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(ratingid), 0);
		params.add(in);
		in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_RATING_SELECTED, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.POST_RATING_SELECTED, params, "ratingid,id,name,image",
				ConvertUtil.convertToInteger(rs));
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getFeedbackPluginDeatail(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_FEEDBACK_PLUGIN_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_FEEDBACK_PLUGIN_DETAIL, params, "sessionid,appid,from,to",
				outParam.size() / 5);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> plugin = new HashMap<String, Object>();
		if (outParam.size() == 0) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
			return map;
		} else if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		for (int i = 0; i < outParam.size(); i += 5) {
			plugin = new HashMap<String, Object>();
			plugin.put("device", outParam.get(i));
			plugin.put("date", outParam.get(i + 1));
			plugin.put("selected_id", outParam.get(i + 2));
			plugin.put("selected_name", outParam.get(i + 3));
			plugin.put("text", outParam.get(i + 4));
			list.add(plugin);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("data", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getFeedbackPlugin(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_FEEDBACK_PLUGIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_FEEDBACK_PLUGIN, params, "sessionid,appid,from,to", outParam.size() / 4);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> listrating = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> plugin = new HashMap<String, Object>();
		HashMap<String, Object> select = new HashMap<String, Object>();
		if (outParam.size() == 1) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
			return map;
		} else if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}

		int count = ConvertUtil.convertToInteger(outParam.get(0));
		for (int i = 1; i < outParam.size(); i++) {
			plugin = new HashMap<String, Object>();
			listrating = new ArrayList<HashMap<String, Object>>();
			plugin.put("text", outParam.get(i));
			for (int j = i + 1; j < i + 1 + count * 3; j += 3) {
				select = new HashMap<String, Object>();
				select.put("date", outParam.get(j));
				select.put("num", outParam.get(j + 1));
				select.put("sum", outParam.get(j + 2));
				listrating.add(select);
			}
			plugin.put("data", listrating);
			list.add(plugin);
			i = i + count * 3;
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("data", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPluginEmail(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_PLUGIN_EMAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_PLUGIN_EMAIL, params, "sessionid,appid", outParam.size());
		if (outParam.size() == 0) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("app_id", "");
			map.put("subject", "");
			map.put("from", "");
			map.put("to", "");
			map.put("cc", "");
			map.put("auto_send", "");
			map.put("content", "");
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		map.put("app_id", outParam.get(0));
		map.put("subject", outParam.get(1));
		map.put("from", outParam.get(2));
		map.put("to", outParam.get(3));
		map.put("cc", outParam.get(4));
		map.put("auto_send", outParam.get(5));
		map.put("content", outParam.get(6));
		map.put("status_code", "200");
		map.put("message", "OK");
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getPluginById(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		// in = new SubProParam(new String(param.getSession_id()), 0);
		// params.add(in);
		// in = new SubProParam(new String(param.getUser_id()), 0);
		// params.add(in);
		SubProParam in = new SubProParam(new String(param.getApp_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_PLUGIN_BY_ID, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_PLUGIN_BY_ID, params, "sessionid,userid,appid", outParam.size());
		if (outParam.size() == 0) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("plugin", new HashMap<String, Object>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> listobj = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> plugin = new HashMap<String, Object>();
			listobj = new ArrayList<HashMap<String, Object>>();
			plugin.put("app_id", outParam.get(0));
			plugin.put("app_name", outParam.get(1));
			plugin.put("app_image", outParam.get(2));
			plugin.put("app_text", outParam.get(3));
			plugin.put("app_type", outParam.get(4));
			plugin.put("website", outParam.get(5));
			plugin.put("contact_email", outParam.get(6));
			plugin.put("status", outParam.get(7));
			for (int i = 8; i < outParam.size(); i += 3) {
				HashMap<String, Object> obj = new HashMap<String, Object>();
				obj.put("object_id", outParam.get(i));
				obj.put("object_name", outParam.get(i + 1));
				obj.put("object_image", outParam.get(i + 2));
				listobj.add(obj);
			}
			plugin.put("app_object", listobj);
			list.add(plugin);
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("plugin", plugin);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getReportOverview(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_day())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayTime(param.getFilter_time())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_tags())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_source())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_device())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_type())), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_REPORT_OVERVIEW, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(10);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_REPORT_OVERVIEW, params,
				"sessionid,layoutid,from,to,day,time,tags,source,device,type", outParam.size());
		// for (int i = 0; i < outParam.size(); i++) {
		// System.out.println(i + "---" + outParam.get(i));
		// }
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			HashMap<String, Object> report = new HashMap<String, Object>();
			List<HashMap<String, Object>> listdata = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> response = new HashMap<String, Object>();
			HashMap<String, Object> score = new HashMap<String, Object>();
			HashMap<String, Object> question = new HashMap<String, Object>();
			HashMap<String, Object> answer = new HashMap<String, Object>();
			HashMap<String, Object> datareponse = new HashMap<String, Object>();
			HashMap<String, Object> datascore = new HashMap<String, Object>();
			HashMap<String, Object> dataquesttion = new HashMap<String, Object>();
			HashMap<String, Object> dataanswer = new HashMap<String, Object>();
			List<HashMap<String, Object>> listdatareponse = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> listdatascore = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> listdataquestion = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> listdataanswer = new ArrayList<HashMap<String, Object>>();

			response.put("id", "response");
			response.put("name", "Response");
			response.put("type", "response");
			response.put("status", "true");

			score.put("id", "score");
			score.put("name", "Score");
			score.put("type", "score");
			score.put("status", "true");

			question.put("id", "question");
			question.put("name", "Question");
			question.put("type", "question");
			question.put("status", "true");

			answer.put("id", "answer");
			answer.put("name", "Answer");
			answer.put("type", "answer");
			answer.put("status", "true");

			report.put("response", outParam.get(0));
			report.put("score", outParam.get(1));
			report.put("question", outParam.get(2));
			report.put("answer", outParam.get(3));
			int countdate = ConvertUtil.convertToInteger(outParam.get(4));
			for (int i = 5; i < 5 + countdate * 2; i += 2) {
				datareponse = new HashMap<String, Object>();
				datascore = new HashMap<String, Object>();
				datareponse.put("date", outParam.get(i));
				datareponse.put("num", outParam.get(i + 1));
				listdatareponse.add(datareponse);
			}
			response.put("data", listdatareponse);

			int countvote = ConvertUtil.convertToInteger(outParam.get(5 + countdate * 2));
			for (int i = 5 + countdate * 2 + 1; i < 5 + countdate * 2 + 1 + countvote * 2; i += 2) {
				// for (int i = 6 + countdate * 2; i < 6 + (countdate + countvote) * 2; i += 2) {
				datascore = new HashMap<String, Object>();
				datascore.put("date", outParam.get(i));
				datascore.put("num", outParam.get(i + 1));
				listdatascore.add(datascore);
			}
			score.put("data", listdatascore);

			int countobject = ConvertUtil.convertToInteger(outParam.get(5 + countdate * 2 + 1 + countvote * 2));
			for (int i = 5 + countdate * 2 + 1 + countvote * 2 + 1; i < 5 + countdate * 2 + 1 + countvote * 2 + 1 + countobject * 4; i += 4) {
				// for (int i = 7 + (countdate + countvote) * 2; i < 7 + (countdate + countvote) * 2 + countobject * 4; i += 4) {
				dataquesttion = new HashMap<String, Object>();
				dataquesttion.put("id", outParam.get(i));
				dataquesttion.put("name", outParam.get(i + 1));
				dataquesttion.put("num", outParam.get(i + 2));
				dataquesttion.put("score", outParam.get(i + 3));
				listdataquestion.add(dataquesttion);
			}
			question.put("data", listdataquestion);

			int countanswer = ConvertUtil.convertToInteger(outParam.get(5 + countdate * 2 + 1 + countvote * 2 + 1 + countobject * 4));
			// System.out.println(5 + countdate * 2 + 1 + countvote * 2 + 1 + countobject * 4);
			for (int i = 5 + countdate * 2 + 1 + countvote * 2 + 1 + countobject * 4 + 1; i < 5 + countdate * 2 + 1 + countvote * 2 + 1
					+ countobject * 4 + 1 + countanswer * 4; i += 4) {
				// for (int i = 8 + (countdate + countvote) * 2 + countobject * 4; i < 8 + (countdate + countvote) * 2
				// + (countobject + countanswer) * 4; i += 4) {
				dataanswer = new HashMap<String, Object>();
				dataanswer.put("id", outParam.get(i));
				dataanswer.put("name", outParam.get(i + 1));
				dataanswer.put("num", outParam.get(i + 2));
				dataanswer.put("score", outParam.get(i + 3));
				listdataanswer.add(dataanswer);
			}
			answer.put("data", listdataanswer);

			listdata.add(response);
			listdata.add(score);
			listdata.add(question);
			listdata.add(answer);

			report.put("data", listdata);
			response.put("data", listdatareponse);
			score.put("data", listdatascore);
			map.put("report", report);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getReportResponse(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_day())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayTime(param.getFilter_time())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_tags())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_source())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_device())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_type())), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_REPORT_RESPONSE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(10);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// for (int i = 0; i < outParam.size(); i++)
		// System.out.println(i + "---" + outParam.get(i));
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_REPORT_RESPONSE, params,
				"sessionid,layoutid,from,to,day,time,tags,source,device,type", outParam.size());
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			HashMap<String, Object> report = new HashMap<String, Object>();
			List<HashMap<String, Object>> listdata = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> object = new HashMap<String, Object>();
			int count = 0;
			int countrow = 0;
			int countcol = 0;
			HashMap<String, Object> question = new HashMap<String, Object>();
			List<HashMap<String, Object>> listquestion = new ArrayList<HashMap<String, Object>>();

			HashMap<String, Object> survey = new HashMap<String, Object>();
			List<String> listcol = new ArrayList<String>();
			List<String> listnum = new ArrayList<String>();
			List<String> listscore = new ArrayList<String>();
			List<HashMap<String, Object>> listsurvey = new ArrayList<HashMap<String, Object>>();

			HashMap<String, Object> other = new HashMap<String, Object>();
			List<HashMap<String, Object>> listother = new ArrayList<HashMap<String, Object>>();

			report.put("response", outParam.get(0));
			report.put("score", outParam.get(1));
			report.put("question", outParam.get(2));
			report.put("answer", outParam.get(3));

			for (int i = 4; i < outParam.size(); i += 8) {
				object = new HashMap<String, Object>();
				object.put("id", outParam.get(i));
				object.put("name", outParam.get(i + 1));
				object.put("type", outParam.get(i + 2));
				object.put("option", outParam.get(i + 3));
				object.put("status", outParam.get(i + 4));
				object.put("sum", outParam.get(i + 5));
				object.put("sum_score", outParam.get(i + 6));
				count = ConvertUtil.convertToInteger(outParam.get(i + 7));
				if (count > 0) {
					if (outParam.get(i + 2).equals("question")) {
						listquestion = new ArrayList<HashMap<String, Object>>();
						// for (int j = i + 8; j < i + 8 + count * 2; j += 2) {
						int j = i + 8;
						question = new HashMap<String, Object>();
						question.put("date", outParam.get(j));
						question.put("text", outParam.get(j + 1));
						listquestion.add(question);
						// }
						object.put("data", listquestion);
						i = i + count * 2;
					} else if (outParam.get(i + 2).equals("survey")) {
						listsurvey = new ArrayList<HashMap<String, Object>>();
						survey = new HashMap<String, Object>();
						countrow = ConvertUtil.convertToInteger(outParam.get(i + 8));
						countcol = ConvertUtil.convertToInteger(outParam.get(i + 9));
						for (int j = i + 10; j < i + 10 + countrow * 2; i += 2) {
							listcol = new ArrayList<String>();
							listnum = new ArrayList<String>();
							listscore = new ArrayList<String>();
							survey.put("id", outParam.get(j));
							survey.put("name", outParam.get(j + 1));
							for (int x = j + 1; x < j + 1 + countcol * 4; x += 4) {
								listcol.add(outParam.get(x + 1));
								listnum.add(outParam.get(x + 2));
								listscore.add(outParam.get(x + 3));
							}
							survey.put("num", listnum);
							survey.put("score", listscore);
							survey.put("columns", listcol);
							listsurvey.add(survey);
						}
						object.put("data", listsurvey);
						i = i + 2 + countrow * 2;
					} else {
						listother = new ArrayList<HashMap<String, Object>>();
						// for (int j = i + 8; j < i + 8 + count * 4; j += 4) {
						int j = i + 8;
						other = new HashMap<String, Object>();
						other.put("id", outParam.get(j));
						other.put("name", outParam.get(j + 1));
						other.put("num", outParam.get(j + 2));
						other.put("score", outParam.get(j + 3));
						listother.add(other);
						// }
						object.put("data", listother);
						i = i + count * 4;
					}
				}
				listdata.add(object);
			}

			report.put("data", listdata);
			map.put("report", report);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getReportFeedback(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_day())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayTime(param.getFilter_time())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_tags())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_source())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_device())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_type())), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_REPORT_FEEDBACK, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(10);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_REPORT_FEEDBACK, params,
				"sessionid,layoutid,from,to,day,time,tags,source,device,type", outParam.size());
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			HashMap<String, Object> report = new HashMap<String, Object>();
			List<HashMap<String, Object>> listdata = new ArrayList<HashMap<String, Object>>();

			HashMap<String, Object> percentage = new HashMap<String, Object>();
			List<HashMap<String, Object>> listpercentage = new ArrayList<HashMap<String, Object>>();

			HashMap<String, Object> time = new HashMap<String, Object>();
			List<HashMap<String, Object>> listtime = new ArrayList<HashMap<String, Object>>();

			HashMap<String, Object> score = new HashMap<String, Object>();
			List<HashMap<String, Object>> listscore = new ArrayList<HashMap<String, Object>>();

			report.put("response", outParam.get(0));
			report.put("score", outParam.get(1));
			report.put("question", outParam.get(2));
			report.put("answer", outParam.get(3));

			time.put("id", "time");
			time.put("name", "Time taken to complete the survey");
			time.put("type", "feedback");
			time.put("status", "true");

			HashMap<String, Object> obj = new HashMap<String, Object>();
			obj.put("name", "0 - 1 minute");
			obj.put("num", outParam.get(4));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "1 - 2 minute");
			obj.put("num", outParam.get(5));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "2 - 3 minute");
			obj.put("num", outParam.get(6));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "3 - 4 minute");
			obj.put("num", outParam.get(7));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "4 - 5 minute");
			obj.put("num", outParam.get(8));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "5 - 6 minute");
			obj.put("num", outParam.get(9));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "6 - 7 minute");
			obj.put("num", outParam.get(10));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "7 - 8 minute");
			obj.put("num", outParam.get(11));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "8 - 9 minute");
			obj.put("num", outParam.get(12));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "9 - 10 minute");
			obj.put("num", outParam.get(13));
			listtime.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "> 10 minute");
			obj.put("num", outParam.get(14));
			listtime.add(obj);
			time.put("data", listtime);

			percentage.put("id", "percentage");
			percentage.put("name", "Survey completeness");
			percentage.put("type", "feedback");
			percentage.put("status", "true");

			obj = new HashMap<String, Object>();
			obj.put("name", "0 - 10 %");
			obj.put("num", outParam.get(15));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "11 - 20 %");
			obj.put("num", outParam.get(16));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "21 - 30 %");
			obj.put("num", outParam.get(17));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "31 - 40 %");
			obj.put("num", outParam.get(18));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "41 - 50 %");
			obj.put("num", outParam.get(19));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "51 - 60 %");
			obj.put("num", outParam.get(20));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "61 - 70 %");
			obj.put("num", outParam.get(21));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "71 - 80 %");
			obj.put("num", outParam.get(22));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "81 - 90 %");
			obj.put("num", outParam.get(23));
			listpercentage.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "91 - 100 %");
			obj.put("num", outParam.get(24));
			listpercentage.add(obj);
			percentage.put("data", listpercentage);

			score.put("id", "score");
			score.put("name", "Survey Score");
			score.put("type", "feedback");
			score.put("status", "true");

			obj = new HashMap<String, Object>();
			obj.put("name", "0 - 10 point");
			obj.put("num", outParam.get(25));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "11 - 20 point");
			obj.put("num", outParam.get(26));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "21 - 30 point");
			obj.put("num", outParam.get(27));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "31 - 40 point");
			obj.put("num", outParam.get(28));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "41 - 50 point");
			obj.put("num", outParam.get(29));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "51 - 60 point");
			obj.put("num", outParam.get(30));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "61 - 70 point");
			obj.put("num", outParam.get(31));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "71 - 80 point");
			obj.put("num", outParam.get(32));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "81 - 90 point");
			obj.put("num", outParam.get(33));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "91 - 100 point");
			obj.put("num", outParam.get(34));
			listscore.add(obj);

			obj = new HashMap<String, Object>();
			obj.put("name", "> 10 point");
			obj.put("num", outParam.get(35));
			listscore.add(obj);
			score.put("data", listscore);

			listdata.add(score);
			listdata.add(time);
			listdata.add(percentage);
			report.put("data", listdata);
			map.put("report", report);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getListDevice(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_DEVICE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_DEVICE, params, "sessionid,parentid", outParam.size());
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		if (outParam.size() == 0) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("list_device", list);
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}

		for (int i = 0; i < outParam.size(); i += 13) {
			HashMap<String, Object> device = new HashMap<String, Object>();
			HashMap<String, Object> loc = new HashMap<String, Object>();
			device.put("device_id", outParam.get(i));
			device.put("serinumber", outParam.get(i + 1));
			device.put("device_name", outParam.get(i + 2));
			device.put("device_ip", outParam.get(i + 3));
			device.put("status", outParam.get(i + 4));

			loc.put("lat", outParam.get(i + 5));
			loc.put("long", outParam.get(i + 6));
			device.put("location", loc);
			device.put("address", outParam.get(i + 7));
			device.put("layout_id", outParam.get(i + 8));
			device.put("layout_name", outParam.get(i + 9));
			device.put("layout_url", outParam.get(i + 10));
			device.put("zone_id", outParam.get(i + 11));
			device.put("location_id", outParam.get(i + 12));
			list.add(device);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("list_device", list);

		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> editDevice(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDevice_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDevice_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDevice_ip()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLatitude()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLongitude()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getAddress()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLocation_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_DEVICE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(10);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.EDIT_DEVICE, params,
				"sessionid,id,name,ip,status,latitube,longitube,address,locationid,layoutid", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "edit", "device", param.getSerinumber(), param.getDevice_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> deleteDevice(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getList_device())), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_DEVICE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.DELETE_DEVICE, params, "sessionid,parentid,listdevice", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("0")) {
			map.put("status_code", 500);
			map.put("message", "Permission denied");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "delete", "device", param.getSerinumber(), param.getDevice_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addLayoutEmail(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getFrom()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getTo())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getCc())), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUrl()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_LAYOUT_EMAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_LAYOUT_EMAIL, params, "sessionid,layoutid,from,to,cc,subject,url",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getListLocation(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_LOCATION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_LIST_LOCATION, params, "sessionid,parentid", outParam.size() / 2);
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < outParam.size(); i += 5) {
			HashMap<String, String> location = new HashMap<String, String>();
			location.put("location_id", outParam.get(i));
			location.put("location_name", outParam.get(i + 1));
			list.add(location);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("list_location", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addRole(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRole_name()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getList_user())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getPrivileges())), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_ROLE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_ROLE, params, "sessionid,parentid,rolename,user,permission",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("0")) {
			map.put("status_code", 500);
			map.put("message", "Role name already exist");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "add", "role", param.getRole_id(), param.getRole_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> editRole(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRole_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRole_name()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getList_user())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getPrivileges())), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_ROLE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.EDIT_ROLE, params, "sessionid,parentid,roleid,rolename,user,permission",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "edit", "role", param.getRole_id(), param.getRole_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getListAccount(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_ACCOUNT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_LIST_ACCOUNT, params, "sessionid,parent_id", outParam.size() / 16);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		if (outParam.size() == 0) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("data", list);
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", "408");
			map.put("message", "Request timeout");
			return map;
		}
		for (int i = 0; i < outParam.size(); i += 16) {
			HashMap<String, Object> acc = new HashMap<String, Object>();
			HashMap<String, String> user = new HashMap<String, String>();
			HashMap<String, String> device = new HashMap<String, String>();
			HashMap<String, String> storage = new HashMap<String, String>();
			acc.put("user_id", outParam.get(i));
			acc.put("user_name", outParam.get(i + 1));
			acc.put("user_mail", outParam.get(i + 2));
			acc.put("full_name", outParam.get(i + 3));
			acc.put("avatar", outParam.get(i + 4));
			acc.put("zone_id", outParam.get(i + 5));
			acc.put("role_id", outParam.get(i + 6));
			acc.put("status", outParam.get(i + 7));
			acc.put("address", outParam.get(i + 8));
			acc.put("last_update", outParam.get(i + 9));
			user.put("used", outParam.get(i + 10));
			user.put("total", outParam.get(i + 11));
			device.put("used", outParam.get(i + 12));
			device.put("total", outParam.get(i + 13));
			storage.put("used", outParam.get(i + 14));
			storage.put("total", outParam.get(i + 15));
			acc.put("sub_account", user);
			acc.put("device", device);
			acc.put("storage", storage);
			list.add(acc);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("data", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	public void logActivity(String sessionid, String action, String type, String id, String name) {
		String rs = "-1";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(sessionid), 0);
		params.add(in);
		in = new SubProParam(new String(action), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);
		in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.LOG_ACTIVITY, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.LOG_ACTIVITY, params, "sessionid,action,type,id,name", Integer.parseInt(rs));
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getListActivity(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_ACTIVITY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_LIST_ACTIVITY, params, "sessionid,from,to", outParam.size() / 4);
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("list_activity", new ArrayList<HashMap<String, Object>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < outParam.size(); i += 4) {
			HashMap<String, Object> activity = new HashMap<String, Object>();
			activity.put("id", outParam.get(i));
			activity.put("name", outParam.get(i + 1));
			activity.put("time", outParam.get(i + 2));
			activity.put("activity", outParam.get(i + 3));
			list.add(activity);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("list_activity", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addZone(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_image()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getLocation())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getAccount())), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_ZONE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_ZONE, params, "sessionid,userid,name,image,location,account",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("0")) {
			map.put("status_code", 500);
			map.put("message", "Role name already exist");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "add", "zone", param.getZone_id(), param.getZone_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> editZone(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_image()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getLocation())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getAccount())), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_ZONE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.EDIT_ZONE, params, "sessionid,userid,id,name,image,status,location,account",
				Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "edit", "zone", param.getZone_id(), param.getZone_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> deleteZone(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_ZONE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.DELETE_ZONE, params, "sessionid,id", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("0")) {
			map.put("status_code", 500);
			map.put("message", "Zone already used");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "delete", "zone", param.getZone_id(), param.getZone_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> updateStatusZone(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_STATUS_ZONE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.UPDATE_STATUS_ZONE, params, "sessionid,id,status", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("0")) {
			map.put("status_code", 500);
			map.put("message", "Zone already used");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "delete", "zone", param.getZone_id(), param.getZone_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getListLayout(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getParent_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_LAYOUT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_LIST_LAYOUT, params, "sessionid,parentid", outParam.size() / 3);
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> layout = new HashMap<String, String>();
			layout.put("layout_id", outParam.get(i));
			layout.put("layout_name", outParam.get(i + 1));
			layout.put("layout_url", outParam.get(i + 2));
			list.add(layout);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("layouts", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> saveReport(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getLayout_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSave_name()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getReport_type()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_day())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_time())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_tags())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_source())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_device())), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_type())), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUrl()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRepeat()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getIschedule()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getIsfinish()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SAVE_REPORT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(17);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// for (int i = 0; i < params.size(); i++)
		// System.out.println(i + ": " + params.get(i).getValue().toString());
		LogUtil.logDao(
				eSmileDaoImpl.class.toString(),
				SQL.SAVE_REPORT,
				params,
				"sessionid,userid,layoutid,savename,reporttype,from,to,filterday,filtertime,filtertag,filtersource,filterdevice,filtertype,url,repeat,ischedule,isfinish",
				Integer.parseInt(rs));
		if (rs.equals("-1") || rs.equals("0")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("report_id", rs);
			logActivity(param.getSession_id(), "save", "report", param.getReport_type(), param.getSave_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getSaveReport(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SAVE_REPORT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_SAVE_REPORT, params, "sessionid,zoneid", outParam.size() / 8);
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("report", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < outParam.size(); i += 8) {
			HashMap<String, String> report = new HashMap<String, String>();
			report.put("id", outParam.get(i));
			report.put("name", outParam.get(i + 1));
			report.put("create_by", outParam.get(i + 2));
			report.put("last_update", outParam.get(i + 3));
			report.put("type", outParam.get(i + 4));
			report.put("zone_id", outParam.get(i + 5));
			report.put("zone_name", outParam.get(i + 6));
			report.put("url", outParam.get(i + 7));
			list.add(report);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("report", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> deleteScheduleReport(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getSchedule_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_SCHEDULE_REPORT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.DELETE_SCHEDULE_REPORT, params, "sessionid,id", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(param.getSession_id(), "delete", "schedule", param.getSchedule_id(), param.getSave_name());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getScheduleReport(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SCHEDULE_REPORT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_SCHEDULE_REPORT, params, "sessionid,userid", outParam.size() / 10);
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("report", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < outParam.size(); i += 10) {
			HashMap<String, String> report = new HashMap<String, String>();
			report.put("id", outParam.get(i));
			report.put("name", outParam.get(i + 1));
			report.put("date_from", outParam.get(i + 2));
			report.put("date_to", outParam.get(i + 3));
			report.put("create_by", outParam.get(i + 4));
			report.put("last_update", outParam.get(i + 5));
			report.put("type", outParam.get(i + 6));
			report.put("zone_id", outParam.get(i + 7));
			report.put("zone_name", outParam.get(i + 8));
			report.put("count", outParam.get(i + 9));
			list.add(report);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("report", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getRespondent(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RESPONDENT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_RESPONDENT, params, "sessionid,zoneid,from,to", outParam.size() / 11);
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("report", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		map.put("sum_flag", outParam.get(0));
		map.put("sum_important", outParam.get(1));
		for (int i = 2; i < outParam.size(); i += 11) {
			HashMap<String, Object> respondent = new HashMap<String, Object>();
			respondent.put("respondent_id", outParam.get(i));
			respondent.put("layout_id", outParam.get(i + 1));
			respondent.put("layout_name", outParam.get(i + 2));
			respondent.put("create_by", outParam.get(i + 3));
			respondent.put("create_date", outParam.get(i + 4));
			respondent.put("tags", outParam.get(i + 5).split(","));
			respondent.put("flag", outParam.get(i + 6));
			respondent.put("important", outParam.get(i + 7));
			respondent.put("zone_name", outParam.get(i + 8));
			respondent.put("duration", ConvertUtil.getDurationString(ConvertUtil.convertToInteger(outParam.get(i + 9))));
			respondent.put("score", outParam.get(i + 10));
			list.add(respondent);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("respondent", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> addTagsRespondent(String session_id, DataPostRatingModel param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(session_id), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRespondent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getTags())), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_TAGS_RESPONDENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.ADD_TAGS_RESPONDENT, params, "sessionid,id,listtags", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(session_id, "add", "tags respondent", param.getRespondent_id(), "");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> updateFlag(String session_id, DataPostRatingModel param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(session_id), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRespondent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getFlag()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_FLAG, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.UPDATE_FLAG, params, "sessionid,id,flag", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(session_id, "update", "flag respondent", param.getRespondent_id(), "");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> updateImportant(String session_id, DataPostRatingModel param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(session_id), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRespondent_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getImportant()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_IMPORTANT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.UPDATE_IMPORTANT, params, "sessionid,id,important", Integer.parseInt(rs));
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else if (rs.equals("1")) {
			map.put("status_code", 200);
			map.put("message", "OK");
			logActivity(session_id, "update", "important respondent", param.getRespondent_id(), "");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> deleteRespondent(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getList_id())), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_RESPONDENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.DELETE_RESPONDENT, params, "sessionid,listid", 1);
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
		} else {
			String[] arr = {};
			if (rs.length() > 2) {
				rs = rs.substring(2);
				arr = rs.split(",");
			}
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("error_list", arr);
			logActivity(param.getSession_id(), "delete", "respondent", ConvertUtil.convertArrayToString(param.getList_id()), "");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getReportDevice(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getZone_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_REPORT_DEVICE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_REPORT_DEVICE, params, "sessionid,zoneid,from,to", outParam.size() / 2);
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("report", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> device = new HashMap<String, Object>();
		device.put("name", "Phone");
		device.put("num", outParam.get(0));
		list.add(device);
		device = new HashMap<String, Object>();
		device.put("name", "Tablet");
		device.put("num", outParam.get(1));
		list.add(device);
		device = new HashMap<String, Object>();
		device.put("name", "Other");
		device.put("num", outParam.get(2));
		list.add(device);

		// for (int i = 0; i < outParam.size(); i += 2) {
		// HashMap<String, Object> device = new HashMap<String, Object>();
		// device.put("name", outParam.get(i));
		// device.put("num", outParam.get(i + 1));
		// list.add(device);
		// }
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("report", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getRespondentDetail(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRespondent_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RESPONDENT_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_RESPONDENT_DETAIL, params, "sessionid,respondentid", outParam.size());
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("respondent", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// List<HashMap<String, Object>> listselect = new ArrayList<HashMap<String, Object>>();
		map.put("score", outParam.get(0));
		map.put("question", outParam.get(1));
		map.put("answer", outParam.get(2));
		List<String> selected = new ArrayList<String>();
		int count = 0;
		for (int i = 3; i < outParam.size(); i += 4) {
			HashMap<String, Object> res = new HashMap<String, Object>();
			// listselect = new ArrayList<HashMap<String, Object>>();
			selected = new ArrayList<String>();
			res.put("object_id", outParam.get(i));
			res.put("object_name", outParam.get(i + 1));
			res.put("object_type", outParam.get(i + 2));
			count = ConvertUtil.convertToInteger(outParam.get(i + 3));
			for (int j = i + 4; j < i + 4 + count * 3; j += 3) {
				if (outParam.get(i + 2).equals("question"))
					selected.add(outParam.get(j + 2));
				else if (outParam.get(i + 2).equals("survey"))
					selected.add(outParam.get(j + 1) + " - " + outParam.get(j + 2));
				else
					selected.add(outParam.get(j + 1));
				// HashMap<String, Object> select = new HashMap<String, Object>();
				// select.put("selected_id", outParam.get(j));
				// select.put("selected_name", outParam.get(j + 1));
				// select.put("selected_value", outParam.get(j + 2));
				// listselect.add(select);
			}
			res.put("selected", selected);
			i = i + count * 3;
			list.add(res);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("respondent", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getZone(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_ZONE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_ZONE, params, "sessionid,userid", outParam.size());
		// for (int i = 0; i < outParam.size(); i++)
		// System.out.println(i + "---" + outParam.get(i));
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("zones", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> listloc = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> listdev = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> listacc = new ArrayList<HashMap<String, Object>>();
		int countloc = 0;
		int countdev = 0;
		int countacc = 0;
		for (int i = 0; i < outParam.size(); i += 6) {
			HashMap<String, Object> zone = new HashMap<String, Object>();
			listloc = new ArrayList<HashMap<String, Object>>();
			listdev = new ArrayList<HashMap<String, Object>>();
			listacc = new ArrayList<HashMap<String, Object>>();
			zone.put("zone_id", outParam.get(i));
			zone.put("zone_name", outParam.get(i + 1));
			zone.put("zone_image", outParam.get(i + 2));
			zone.put("status", outParam.get(i + 3));
			zone.put("last_update", outParam.get(i + 4));
			countloc = ConvertUtil.convertToInteger(outParam.get(i + 5));
			for (int j = i + 6; j < i + 5 + countloc * 2; j += 2) {
				HashMap<String, Object> location = new HashMap<String, Object>();
				location.put("location_id", outParam.get(j));
				location.put("location_name", outParam.get(j + 1));
				listloc.add(location);
			}
			countdev = ConvertUtil.convertToInteger(outParam.get(i + 6 + countloc * 2));
			for (int j = i + 6 + countloc * 2 + 1; j < i + 6 + countloc * 2 + 1 + countdev * 2; j += 2) {
				HashMap<String, Object> device = new HashMap<String, Object>();
				device.put("device_id", outParam.get(j));
				device.put("device_name", outParam.get(j + 1));
				listdev.add(device);
			}
			countacc = ConvertUtil.convertToInteger(outParam.get(i + 6 + countloc * 2 + 1 + countdev * 2));
			for (int j = i + 6 + countloc * 2 + 1 + countdev * 2 + 1; j < i + 6 + countloc * 2 + 1 + countdev * 2 + 1 + countacc * 2; j += 2) {
				HashMap<String, Object> acc = new HashMap<String, Object>();
				acc.put("account_id", outParam.get(j));
				acc.put("account_name", outParam.get(j + 1));
				listacc.add(acc);
			}
			zone.put("locations", listloc);
			zone.put("devices", listdev);
			zone.put("accounts", listacc);
			list.add(zone);
			i = i + countloc * 2 + 1 + countdev * 2 + 1 + countacc * 2;
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("zones", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getGeoLocationDevice(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_GEO_LOCATION_DEVICE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_GEO_LOCATION_DEVICE, params, "sessionid,userid", outParam.size());
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("list_device", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < outParam.size(); i += 13) {
			HashMap<String, Object> device = new HashMap<String, Object>();
			HashMap<String, Object> info = new HashMap<String, Object>();
			HashMap<String, Object> location = new HashMap<String, Object>();
			device.put("device_id", outParam.get(i));
			device.put("device_name", outParam.get(i + 1));
			device.put("device_ip", outParam.get(i + 2));
			device.put("layout_id", outParam.get(i + 3));
			device.put("layout_name", outParam.get(i + 4));
			device.put("status", outParam.get(i + 5));
			location.put("lat", outParam.get(i + 6));
			location.put("long", outParam.get(i + 7));
			device.put("network", outParam.get(i + 8));
			info.put("pin", outParam.get(i + 9));
			info.put("cpu", outParam.get(i + 10));
			info.put("ram", outParam.get(i + 11));
			info.put("storage", outParam.get(i + 12));
			device.put("location", location);
			device.put("info", info);
			list.add(device);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("list_device", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getDashboardDevice(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(ConvertUtil.convertArrayToString(param.getFilter_type())), 0);
		params.add(in);
		in = new SubProParam(new String(param.getStatus()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_DASHBOEARD_DEVICE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_DASHBOEARD_DEVICE, params, "sessionid,userid,filtertype,status",
				outParam.size());
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("num_device", new ArrayList<HashMap<String, String>>());
			map.put("list_device", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> num = new HashMap<String, Object>();
		num.put("total", outParam.get(0));
		num.put("online", outParam.get(1));
		num.put("offline", outParam.get(2));
		for (int i = 3; i < outParam.size(); i += 13) {
			HashMap<String, Object> device = new HashMap<String, Object>();
			HashMap<String, Object> info = new HashMap<String, Object>();
			HashMap<String, Object> location = new HashMap<String, Object>();
			device.put("device_id", outParam.get(i));
			device.put("device_name", outParam.get(i + 1));
			device.put("device_ip", outParam.get(i + 2));
			device.put("layout_id", outParam.get(i + 3));
			device.put("layout_name", outParam.get(i + 4));
			device.put("status", outParam.get(i + 5));
			location.put("lat", outParam.get(i + 6));
			location.put("long", outParam.get(i + 7));
			device.put("network", outParam.get(i + 8));
			info.put("pin", outParam.get(i + 9));
			info.put("cpu", outParam.get(i + 10));
			info.put("ram", outParam.get(i + 11));
			info.put("storage", outParam.get(i + 12));
			device.put("location", location);
			device.put("info", info);
			list.add(device);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("num_device", num);
		map.put("list_device", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getDashboardOverview(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_from()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getDate_to()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_DASHBOEARD_OVERVIEW, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_DASHBOEARD_OVERVIEW, params, "sessionid,userid,from,to", outParam.size());
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("zone", new ArrayList<HashMap<String, Object>>());
			map.put("overview", new ArrayList<HashMap<String, String>>());
			map.put("response", new ArrayList<HashMap<String, Object>>());
			map.put("layouts", new ArrayList<HashMap<String, Object>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		HashMap<String, Object> overview = new HashMap<String, Object>();
		HashMap<String, Object> storage = new HashMap<String, Object>();
		HashMap<String, Object> sub_account = new HashMap<String, Object>();
		HashMap<String, Object> feedback = new HashMap<String, Object>();
		List<HashMap<String, Object>> listzone = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> zone = new HashMap<String, Object>();
		List<HashMap<String, Object>> listresponse = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		List<HashMap<String, Object>> listlayout = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> layout = new HashMap<String, Object>();
		List<HashMap<String, Object>> listquestion = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> question = new HashMap<String, Object>();
		HashMap<String, Object> answer = new HashMap<String, Object>();

		overview.put("response", outParam.get(0));
		overview.put("score", outParam.get(1));
		storage.put("used", outParam.get(2));
		storage.put("total", outParam.get(3));
		overview.put("storage", storage);
		sub_account.put("used", outParam.get(4));
		sub_account.put("total", outParam.get(5));
		overview.put("sub_account", sub_account);
		feedback.put("bad", outParam.get(6));
		feedback.put("good", outParam.get(7));
		feedback.put("total", outParam.get(0));
		overview.put("feedback", feedback);
		map.put("overview", overview);
		zone.put("zone_id", outParam.get(8));
		zone.put("zone_name", outParam.get(9));
		zone.put("score", outParam.get(10));
		listzone.add(zone);
		map.put("zone", listzone);
		int countdate = ConvertUtil.convertToInteger(outParam.get(11));
		for (int i = 12; i < 12 + countdate * 2; i += 2) {
			response = new HashMap<String, Object>();
			response.put("date", outParam.get(i));
			response.put("num", outParam.get(i + 1));
			listresponse.add(response);
		}
		map.put("response", listresponse);
		int countlayout = ConvertUtil.convertToInteger(outParam.get(12 + countdate * 2));
		for (int i = 12 + countdate * 2 + 1; i < 12 + countdate * 2 + 1 + countlayout * 2; i += 2) {
			layout = new HashMap<String, Object>();
			listresponse = new ArrayList<HashMap<String, Object>>();
			layout.put("layout_id", outParam.get(i));
			layout.put("layout_name", outParam.get(i + 1));
			for (int j = i + 2; j < i + 2 + countdate * 2; j += 2) {
				response = new HashMap<String, Object>();
				response.put("date", outParam.get(j));
				response.put("num", outParam.get(j + 1));
				listresponse.add(response);
			}
			layout.put("responses", listresponse);
			int countobject = ConvertUtil.convertToInteger(outParam.get(i + 2 + countdate * 2));
			for (int j = i + 2 + countdate * 2 + 1; j < i + 2 + countdate * 2 + 1 + countobject * 4; j += 4) {
				question = new HashMap<String, Object>();
				answer = new HashMap<String, Object>();
				question.put("question_id", outParam.get(j));
				question.put("question_name", outParam.get(j + 1));
				answer.put("good", outParam.get(j + 2));
				answer.put("total", outParam.get(j + 3));
				question.put("answer", answer);
				listquestion.add(question);
			}
			layout.put("questions", listquestion);
			listlayout.add(layout);
			i = i + countdate * 2 + 1 + countobject * 4;
		}
		map.put("layouts", listlayout);
		map.put("status_code", "200");
		map.put("message", "OK");
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> renewalSession(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_name()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.RENEWAL_SESSION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.RENEWAL_SESSION, params, "sessionid,username", 1);
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 500);
			map.put("message", "Session not exist");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> boxGetInfo(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSerinumber()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getCpu()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getTotal_cpu()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getRam()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getTotal_ram()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getHdd()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getTotal_hdd()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getPin()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.BOX_GET_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.BOX_GET_INFO, params, "serinumber,cpu,totalcpu,ram,totalram,hdd,totalhdd,pin", 1);
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 500);
			map.put("message", "Device not exist");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("status", rs);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> setBackground(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getImage()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_BACKGROUND, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.SET_BACKGROUND, params, "session,userid,image", 1);
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request timeout");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getNotification(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getUser_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getGood()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_NOTIFICATION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_NOTIFICATION, params, "sessionid,userid,good", outParam.size() / 2);
		if (outParam.size() == 0) {
			map.put("status_code", 200);
			map.put("message", "OK");
			map.put("notification", new ArrayList<HashMap<String, String>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < outParam.size(); i += 2) {
			HashMap<String, Object> notify = new HashMap<String, Object>();
			notify.put("device_id", outParam.get(i));
			notify.put("device_name", outParam.get(i + 1));
			list.add(notify);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("notification", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getLayoutEmail(Params param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getIssend()), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LAYOUT_EMAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.GET_LAYOUT_EMAIL, params, "sessionid,issend", outParam.size());
		if (outParam.size() == 0) {
			map.put("status_code", "200");
			map.put("message", "OK");
			map.put("mails", new ArrayList<HashMap<String, Object>>());
			return map;
		}
		if (outParam.get(0).equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request Timeout");
			return map;
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < outParam.size(); i += 8) {
			HashMap<String, Object> mail = new HashMap<String, Object>();
			mail.put("id", outParam.get(i));
			mail.put("layout_id", outParam.get(i + 1));
			mail.put("from", outParam.get(i + 2));
			mail.put("to", outParam.get(i + 3));
			mail.put("cc", outParam.get(i + 4));
			mail.put("subject", outParam.get(i + 5));
			mail.put("url", outParam.get(i + 6));
			mail.put("issend", outParam.get(i + 7));
			list.add(mail);
		}
		map.put("status_code", "200");
		map.put("message", "OK");
		map.put("mails", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> setSendEmail(Params param) {
		String rs = "0";
		HashMap<String, Object> map = new HashMap<String, Object>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(param.getSession_id()), 0);
		params.add(in);
		in = new SubProParam(new String(param.getEmail_id()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_SEND_EMAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDaoImpl.class.toString(), SQL.SET_SEND_EMAIL, params, "session,emailid", 1);
		if (rs.equals("-1")) {
			map.put("status_code", 500);
			map.put("message", "Internal Server Error");
		} else if (rs.equals("-2")) {
			map.put("status_code", 408);
			map.put("message", "Request timeout");
		} else {
			map.put("status_code", 200);
			map.put("message", "OK");
		}
		return map;
	}

	public static void main(String[] args) {
		eSmileDaoImpl l = new eSmileDaoImpl();
		Params params = new Params();
		params.setSession_id("-999");
		params.setUser_id("1");
		params.setLayout_id("1547");
		params.setParent_id("1");
		params.setDate_from("15-04-2018");
		params.setDate_to("16-04-2018");
		String[] day = { "2", };
		// params.setFilter_day(day);
		String[] time = {};
		// params.setFilter_time(time);
		String[] tags = {};
		// params.setFilter_tags(tags);
		String[] source = { "URL", "APP" };
		// params.setFilter_source(source);
		String[] device = { "Windows 7/Chrome 64.0.3282.186", "Android 4.4.4/Chrome 33.0.0.0" };
		// String[] device = {};
		// params.setFilter_device(device);
		String[] type = { "score", "multiple", "icon", "smile" };
		// params.setFilter_type(type);
		String[] list_user = { "1", "44", "41", "46", "43", "27" };
		// params.setList_user(list_user);
		String[] privileges = { "1", "2", "3", "5" };
		// params.setPrivileges(privileges);
		params.setRespondent_id("243");
		params.setParent_id("1");
		params.setApp_id("92109224060474");
		params.setIssend("-1");

		System.out.println(l.getLayoutEmail(params));

		// System.out.println(l.getFeedbackPlugin(params));
		// System.out.println(l.getDashboardOverview(params));
		// System.out.println(l.getListZone(params));

		// System.out.println(l.getRespondentDetail(params));
		// System.out.println(l.getReportFeedback(params));

		// System.out.println(l.getPlugin(params));

		// System.out.println(l.editRole(params));

		// System.out.println(l.getListAccount(params));
		// FileUtil.writeFileJson("D://list_account.json", l.getListAccount(params));
		// System.out.println(l.getReportOverview(params));
		// System.out.println(l.getReportResponse(params));
		// System.out.println(l.getPluginById(params));

		// System.out.println(l.getInfoFilter(params));

		// System.out.println(l.getRole(params));
		// System.out.println(l.getLayoutId());

		// HashMap<String, Object> map = new HashMap<String, Object>();
		// Vector<String> outParam = new Vector<String>();
		// outParam.add("device");
		// outParam.add("device 1");
		// outParam.add("device");
		// outParam.add("device 2");
		// outParam.add("tags");
		// outParam.add("tags 1");
		// outParam.add("1");
		// outParam.add("zone 1");
		// outParam.add("2");
		// outParam.add("layout1");
		// outParam.add("name1");
		// outParam.add("layout2");
		// outParam.add("name2");
		// outParam.add("2");
		// outParam.add("zone 2");
		// outParam.add("1");
		// outParam.add("layout3");
		// outParam.add("name3");
		// List<String> listdevice = new ArrayList<String>();
		// List<String> listtags = new ArrayList<String>();
		// List<HashMap<String, Object>> listzone = new ArrayList<HashMap<String, Object>>();
		// List<HashMap<String, Object>> listlayout = new ArrayList<HashMap<String, Object>>();
		// int count = 0;
		// HashMap<String, Object> zone = new HashMap<String, Object>();
		// HashMap<String, Object> layout = new HashMap<String, Object>();
		// for (int i = 0; i < outParam.size(); i += 2) {
		// if (outParam.get(i).equals("device"))
		// listdevice.add(outParam.get(i + 1));
		// else if (outParam.get(i).equals("tags"))
		// listtags.add(outParam.get(i + 1));
		// else {
		// zone = new HashMap<String, Object>();
		// zone.put("zone_id", outParam.get(i));
		// zone.put("zone_name", outParam.get(i + 1));
		// count = ConvertUtil.convertToInteger(outParam.get(i + 2));
		// for (int j = i + 3; j < i + 3 + count * 2; j += 2) {
		// layout = new HashMap<String, Object>();
		// layout.put("layout_id", outParam.get(j));
		// layout.put("layout_name", outParam.get(j + 1));
		// listlayout.add(layout);
		// }
		// zone.put("layout", listlayout);
		// listzone.add(zone);
		// i = i + 1 + count * 2;
		// }
		// }
		// map.put("status_code", "200");
		// map.put("message", "OK");
		// map.put("device", listdevice);
		// map.put("tags", listtags);
		// map.put("zone", listzone);
		// System.out.println(map);

		// String[] testArray = { "Apple", "Banana", "Carrots" };
		// String testString = Arrays.toString(testArray);
		// System.out.println(testString);

		// String a = "1,2,3,4";
		// System.out.println(a.substring(1, a.length()));
		// String[] arr = a.substring(1, a.length()).split(",");
		// System.out.println(arr.length);
	}
}
