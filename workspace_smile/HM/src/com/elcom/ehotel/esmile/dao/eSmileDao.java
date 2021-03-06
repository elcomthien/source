package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.InfoModel;
import com.elcom.ehotel.esmile.model.LocationModel;
import com.elcom.ehotel.esmile.model.ObjExchange;
import com.elcom.ehotel.esmile.model.ObjFaculty;
import com.elcom.ehotel.esmile.model.ObjFood;
import com.elcom.ehotel.esmile.model.ObjItem;
import com.elcom.ehotel.esmile.model.ObjItemInfo;
import com.elcom.ehotel.esmile.model.ObjLang;
import com.elcom.ehotel.esmile.model.ObjMenu;
import com.elcom.ehotel.esmile.model.ObjNotify;
import com.elcom.ehotel.esmile.model.ObjPostSurvey;
import com.elcom.ehotel.esmile.model.ObjPromotion;
import com.elcom.ehotel.esmile.model.ObjRating;
import com.elcom.ehotel.esmile.model.ObjRatingNotify;
import com.elcom.ehotel.esmile.model.ObjResult;
import com.elcom.ehotel.esmile.model.ObjShipSchedule;
import com.elcom.ehotel.esmile.model.ObjSmile;
import com.elcom.ehotel.esmile.model.ObjSubject;
import com.elcom.ehotel.esmile.model.ObjSubjectDining;
import com.elcom.ehotel.esmile.model.ObjSubjectInfo;
import com.elcom.ehotel.esmile.model.ObjSubjectRating;
import com.elcom.ehotel.esmile.model.ObjWelcome;
import com.elcom.ehotel.esmile.model.SurveyModel;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.Param;
import com.elcom.ehotel.esmile.util.SQL;

public class eSmileDao {

	@SuppressWarnings("unchecked")
	public List<LocationModel> getLocation() {
		List<LocationModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LOCATION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_LOCATION, params, "none", outParam.size() / 6);
			for (int i = 0; i < outParam.size(); i = i + 6) {
				LocationModel loc = new LocationModel();
				loc.setId(outParam.get(i));
				loc.setName(outParam.get(i + 1));
				loc.setState(outParam.get(i + 2));
				loc.setKey(outParam.get(i + 3));
				loc.setIp(outParam.get(i + 4));
				loc.setLangid(outParam.get(i + 5));
				list.add(loc);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjLang> getLanguage() {
		List<ObjLang> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LANGUAGE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_LANGUAGE, params, "none", outParam.size() / 4);
			for (int i = 0; i < outParam.size(); i = i + 4) {
				ObjLang lang = new ObjLang(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3));
				list.add(lang);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjSubjectRating> getSubjRating(String langid) {
		List<ObjSubjectRating> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SUBJECT_RATING, params, "none", outParam.size() / 5);
			for (int i = 0; i < outParam.size(); i = i + 5) {
				ObjSubjectRating objSujRating = new ObjSubjectRating(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2),
						outParam.get(i + 3), outParam.get(i + 4));
				list.add(objSujRating);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> register(String key, String ip, String name) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(ip), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(name), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_REGISTER, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_REGISTER, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<InfoModel> getInfo(String key) {
		List<InfoModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		// param = new SubProParam(new String(langid), SubProParam.IN);
		// params.add(param);
		// param = new SubProParam(new String(typein), SubProParam.IN);
		// params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() == 1) {
			list = new ArrayList<>();
			return list;
		}
		if (outParam.size() > 1) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_INFO, params, "none", outParam.size());
			for (int i = 0; i < outParam.size(); i = i + 8) {
				InfoModel loc = new InfoModel(outParam.get(i), convertToArrayLang(outParam.get(i + 1)),
						convertToArraySmile(outParam.get(i + 2)), convertToArrayRating(outParam.get(i + 3)),
						convertToArrayWelcome(outParam.get(i + 4)), convertToArrayBackground(outParam.get(i + 5)),
						convertToArrayPromotion(outParam.get(i + 6)), convertToArrayFaculty(outParam.get(i + 7)));
				list.add(loc);
			}
		}
		return list;
	}

	public List<ObjLang> convertToArrayLang(String input) {
		List<ObjLang> arrLang = new ArrayList<ObjLang>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 4) {
					ObjLang obj = new ObjLang(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3]);
					arrLang.add(obj);
				}
				return arrLang;
			}
		}
		return arrLang;
	}

	public List<String> convertToArrayBackground(String input) {
		List<String> arrBg = new ArrayList<String>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 3) {
					String obj = arr2[i + 1];
					arrBg.add(obj);
				}
				return arrBg;
			}
		}
		return arrBg;
	}

	public List<ObjPromotion> convertToArrayPromotion(String input) {
		List<ObjPromotion> arrPromo = new ArrayList<ObjPromotion>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 4) {
					ObjPromotion obj = new ObjPromotion(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3]);
					arrPromo.add(obj);
				}
				return arrPromo;
			}
		}
		return arrPromo;
	}

	public List<ObjRating> convertToArrayRating(String input) {
		List<ObjRating> arrRating = new ArrayList<ObjRating>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 5) {
					// ObjRating obj = new ObjRating(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3], arr2[i + 4], arr2[i + 5], arr2[i + 6]);
					ObjRating obj = new ObjRating(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3], arr2[i + 4]);
					arrRating.add(obj);
				}
				return arrRating;
			}
		}
		return arrRating;
	}

	public List<ObjFaculty> convertToArrayFaculty(String input) {
		List<ObjFaculty> arrFaculty = new ArrayList<ObjFaculty>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 3) {
					ObjFaculty obj = new ObjFaculty(arr2[i], arr2[i + 1], arr2[i + 2]);
					arrFaculty.add(obj);
				}
				return arrFaculty;
			}
		}
		return arrFaculty;
	}

	public List<ObjWelcome> convertToArrayWelcome(String input) {
		List<ObjWelcome> arrWelcome = new ArrayList<ObjWelcome>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 4) {
					ObjWelcome obj = new ObjWelcome(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3]);
					arrWelcome.add(obj);
				}
				return arrWelcome;
			}
		}
		return arrWelcome;
	}

	public List<ObjSmile> convertToArraySmile(String input) {
		List<ObjSmile> arrSmile = new ArrayList<ObjSmile>();
		if (input != null && input.length() > 0) {
			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 6) {
					ObjSmile obj = new ObjSmile(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3], arr2[i + 4], arr2[i + 5]);
					arrSmile.add(obj);
				}
				return arrSmile;
			}
		}
		return arrSmile;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> setRating(String fid, String key, String smileid, String listrating) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(fid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(smileid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(listrating), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.SET_RATING, params, "none", rs);
		HashMap<String, String> hm = new HashMap<>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	private String convertArrayToString(JSONArray arrin) throws Exception {
		String rs = "";
		if (arrin != null && arrin.length() > 0) {
			for (int i = 0; i < arrin.length(); i++) {
				if (i < arrin.length() - 1)
					rs += arrin.getString(i) + ",";
				else
					rs += arrin.getString(i);
			}
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> setComment(String key, String smileid, String textcomment) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(smileid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(textcomment), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_COMMENT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.SET_COMMENT, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> login(String key, String ip) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(ip), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.LOGIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.LOGIN, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}

			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	String convertArrayToString(List<ObjPostSurvey> arrin) throws Exception {
		String rs = "";
		if (arrin != null && arrin.size() > 0) {
			for (int i = 0; i < arrin.size(); i++) {
				if (i < arrin.size() - 1)
					rs += arrin.get(i).getId_survey() + "," + arrin.get(i).getId_smile() + ",";
				else
					rs += arrin.get(i).getId_survey() + "," + arrin.get(i).getId_smile();
			}
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<ObjSubject> getPmsSubject(String key) {
		List<ObjSubject> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_PMSSUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_PMSSUBJECT, params, "none", outParam.size() / 13);
			for (int i = 0; i < outParam.size(); i = i + 13) {
				ObjSubject subject = new ObjSubject(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6), outParam.get(i + 7), outParam.get(i + 8),
						outParam.get(i + 9), outParam.get(i + 10), outParam.get(i + 11), outParam.get(i + 12));
				list.add(subject);
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjItem> getPmsSubjectDetail(String key, String subjectid) {
		List<ObjItem> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);

		param = new SubProParam(new String(subjectid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_PMSSUBJECTDETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_PMSSUBJECTDETAIL, params, "none", outParam.size() / 9);
			for (int i = 0; i < outParam.size(); i = i + 9) {
				ObjItem item = new ObjItem(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6), outParam.get(i + 7), outParam.get(i + 8));
				list.add(item);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> setSmile(String key, String smileid) {
		System.out.println(key + " " + smileid);
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(smileid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_SMILE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.SET_SMILE, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				System.out.println(rs);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjSubjectInfo> getSubjectInfoHotel(String key, String id) {
		List<ObjSubjectInfo> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), 0);
		params.add(param);
		param = new SubProParam(new String(id), 0);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_INFO_HOTEL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 2) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SUBJECT_INFO_HOTEL, params, "none", outParam.size() / 3);
			for (int i = 0; i < outParam.size(); i = i + 3) {
				ObjSubjectInfo subject = new ObjSubjectInfo(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2));
				list.add(subject);
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjItemInfo> getItemBySubjectID(String key, String subjectid) {
		List<ObjItemInfo> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);

		param = new SubProParam(new String(subjectid), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_INTEM_BY_SUBJECT_ID, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 1) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_INTEM_BY_SUBJECT_ID, params, "none", outParam.size() / 3);
			for (int i = 0; i < outParam.size(); i = i + 3) {
				ObjItemInfo item = new ObjItemInfo(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2));
				list.add(item);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SurveyModel> getSurvey(String langid) {
		List<SurveyModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SURVEY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SURVEY, params, "none", outParam.size() / 2);
			for (int i = 0; i < outParam.size(); i = i + 2) {
				SurveyModel objSurvey = new SurveyModel(convertToArraySmile(outParam.get(i)), convertToArrayRating(outParam.get(i + 1)));
				list.add(objSurvey);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> setRatingSurvey(String key, List<ObjPostSurvey> arrSurveyPost) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		String arrRID = "";
		try {
			arrRID = convertArrayToString(arrSurveyPost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		param = new SubProParam(new String(arrRID), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_SURVEY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_SURVEY, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjSubjectDining> getSubjectDining(String key, String langid) {
		List<ObjSubjectDining> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_DINING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SUBJECT_DINING, params, "none", outParam.size() / 3);
			for (int i = 0; i < outParam.size(); i = i + 3) {
				ObjSubjectDining objSubject = new ObjSubjectDining(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2));
				list.add(objSubject);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjSubjectDining> getItemDiningBySubjectID(String key, String subjectid, String langid) {
		List<ObjSubjectDining> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(subjectid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_DINING_BYSUBJECTID, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SUBJECT_DINING_BYSUBJECTID, params, "none", outParam.size() / 3);
			for (int i = 0; i < outParam.size(); i = i + 3) {
				ObjSubjectDining objSubject = new ObjSubjectDining(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2));
				list.add(objSubject);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjFood> getListFootByItem(String key, String itemid, String langid) {
		List<ObjFood> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(itemid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_FOOD_BY_ITEM, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_LIST_FOOD_BY_ITEM, params, "none", outParam.size() / 6);
			for (int i = 0; i < outParam.size(); i = i + 6) {
				System.out.println(outParam.get(i));
				ObjFood objFood = new ObjFood(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5));
				list.add(objFood);
			}
		}
		return list;
	}

	public List<String> convertToArrayTime(String input) {
		List<String> arrTimes = new ArrayList<String>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 1) {
					String obj = arr2[i];
					arrTimes.add(obj);
				}
				return arrTimes;
			}
		}
		return arrTimes;
	}

	@SuppressWarnings("unchecked")
	public List<ObjShipSchedule> getShipSchedule(String key, String langid) {
		List<ObjShipSchedule> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SHIP_SCHEDULE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SHIP_SCHEDULE, params, "none", outParam.size() / 3);
			for (int i = 0; i < outParam.size(); i = i + 3) {
				ObjShipSchedule objShip = new ObjShipSchedule(outParam.get(i), outParam.get(i + 1), convertToArrayTime(outParam.get(i + 2)));
				list.add(objShip);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> editSmile(String id, String image, String lang) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(image), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(lang), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_SMILE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_SMILE, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> editRating(String id, String image) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(image), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_RATING, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getRatingNotify() {
		List<ObjRatingNotify> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_NOTIFY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() >= 7) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_NOTIFY, params, "none", outParam.size() / 7);
			for (int i = 0; i < outParam.size(); i = i + 7) {
				ObjRatingNotify obj = new ObjRatingNotify(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6));
				list.add(obj);
			}
		}
		JSONObject objectJSON = new JSONObject();
		if (list.size() > 0) {
			try {
				objectJSON.put(Param.status, "1");
				objectJSON.put(Param.message, "OK");
				objectJSON.put(Param.data, list);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} else {
			try {
				objectJSON.put(Param.status, "0");
				objectJSON.put(Param.message, "FAIL");
				objectJSON.put(Param.data, list);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return objectJSON;
	}

	@SuppressWarnings("unchecked")
	public List<ObjNotify> getNotify() {
		List<ObjNotify> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_NOTIFY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() >= 9) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_NOTIFY, params, "none", outParam.size() / 11);
			for (int i = 0; i < outParam.size(); i = i + 11) {
				ObjNotify obj = new ObjNotify(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6), outParam.get(i + 7), outParam.get(i + 8),
						outParam.get(i + 9), outParam.get(i + 10));
				list.add(obj);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> confirmNotify(String id) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CONFIRM_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.CONFIRM_RATING, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> deleteNotify(String id) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_NOTIFY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_NOTIFY, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> setRatingGuest(String id, String key, String smileid, JSONArray arrRatingID) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(smileid), SubProParam.IN);
		params.add(param);
		String arrRID = "";
		try {
			arrRID = convertArrayToString(arrRatingID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		param = new SubProParam(new String(arrRID), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_RATING_GUEST, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.SET_RATING_GUEST, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> setCommentGuest(String id, String key, String smileid, String textcomment) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(smileid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(textcomment), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_COMMENT_GUEST, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.SET_COMMENT_GUEST, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					ObjResult objrs = new ObjResult("1", Param.ok);
					list.add(objrs);
				} else {
					ObjResult objrs = new ObjResult("0", Param.fail);
					list.add(objrs);
				}
			}
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjMenu> getMenu(String key, String id) {
		List<ObjMenu> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_MENU, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 1) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_MENU, params, "none", outParam.size() / 5);
			for (int i = 0; i < outParam.size(); i = i + 5) {
				ObjMenu objSubject = new ObjMenu(outParam.get(i), isNull(outParam.get(i + 1)), isNull(outParam.get(i + 2)),
						isNull(outParam.get(i + 3)), isNull(outParam.get(i + 4)));
				list.add(objSubject);
			}
		}
		return list;
	}

	String isNull(String s) {
		if (s == null)
			return "";
		return s;
	}

	@SuppressWarnings("unchecked")
	public List<ObjExchange> getExchange(String key) {
		List<ObjExchange> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_EXCHANGE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() >= 2) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_EXCHANGE, params, "none", outParam.size() / 9);
			for (int i = 0; i < outParam.size(); i = i + 9) {
				ObjExchange objExchange = new ObjExchange(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6), outParam.get(i + 7), outParam.get(i + 8));
				list.add(objExchange);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjFaculty> getFacultyold(String key, String langid) {
		List<ObjFaculty> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_ALL_FACULTY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_ALL_FACULTY, params, "none", outParam.size() / 4);
			for (int i = 0; i < outParam.size(); i = i + 5) {
				ObjFaculty objSubject = new ObjFaculty(outParam.get(i), isNull(outParam.get(i + 1)), isNull(outParam.get(i + 2)),
						isNull(outParam.get(i + 3)));
				list.add(objSubject);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjFaculty> getFaculty(String key, String langid) {
		List<ObjFaculty> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;

		param = new SubProParam(new String(key), 0);
		params.add(param);
		param = new SubProParam(new String(langid), 0);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_FACULTY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_ALL_FACULTY, params, "key,langid", outParam.size());
			int count = 0;
			for (int i = 0; i < outParam.size(); i = i + 4) {
				ObjFaculty obj = new ObjFaculty();
				obj.setId(outParam.get(i));
				obj.setName(outParam.get(i + 1));
				obj.setImage(outParam.get(i + 2));
				count = ConvertUtil.convertToInteger(outParam.get(i + 3));
				if (count > 0) {
					List<HashMap<String, String>> location = new ArrayList<>();
					int length = i + 4 + count * 2;
					for (int j = i + 4; j < length; j = j + 2) {
						HashMap<String, String> map = new HashMap<>();
						map.put("id", outParam.get(j));
						map.put("name", outParam.get(j + 1));
						location.add(map);
					}
					obj.setLocation(location);
					i = length - 4;
				}
				list.add(obj);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> checkKey(String key) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CHECK_KEY, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.CHECK_KEY, params, "key", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "KEY ALREADY USED");
		else if (rs == 0)
			hm.put("message", "KEY UNUSED");
		else
			hm.put("message", "KEY NOT EXIST");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getKey(String fid) {
		List<HashMap<String, String>> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(fid), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_KEY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_KEY, params, "fid", outParam.size());
		if (outParam.size() > 0) {
			for (int i = 0; i < outParam.size(); i = i + 3) {
				HashMap<String, String> map = new HashMap<>();
				map.put("id", outParam.get(i));
				map.put("name", outParam.get(i + 1));
				map.put("key", outParam.get(i + 2));
				list.add(map);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> setLang(String key, String langid) {
		HashMap<String, String> hm = new HashMap<>();
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);

		in = new SubProParam(new String(langid), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_LANG, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.SET_LANG, params, "key,langid", rs);
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> registerTablet(String lid, String key, String ip, String name) {
		HashMap<String, String> hm = new HashMap<>();
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(lid), 0);
		params.add(in);
		in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(ip), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.REGISTER_TABLET, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.REGISTER_TABLET, params, "lid,key,ip,name", rs);
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> checkKeyMobile(String key) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		// in = new SubProParam(new base);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CHECK_KEY_MOBILE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.CHECK_KEY_MOBILE, params, "key", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == -2)
			hm.put("message", "KEY ALREADY USED");
		else if (rs == 1)
			hm.put("message", "ADD KEY SUCCESSFUL");
		else
			hm.put("message", "ADD KEY ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> postImage(String fid, String key, String smileid, String textcomment, String image) {
		int rs = -1;
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(fid), 0);
		params.add(param);
		param = new SubProParam(new String(key), 0);
		params.add(param);
		param = new SubProParam(new String(smileid), 0);
		params.add(param);
		param = new SubProParam(new String(textcomment), 0);
		params.add(param);
		param = new SubProParam(new String(image), 0);
		params.add(param);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);

		try {
			params = SQL.broker.executeSubPro(SQL.POST_IMAGE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_IMAGE, params, "fid,key,smile,text,image", rs);
		if (rs > 0) {
			ObjResult objrs = new ObjResult("1", Param.ok);
			list.add(objrs);
		} else {
			ObjResult objrs = new ObjResult("0", Param.fail);
			list.add(objrs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getNotifyAll() {
		Map<String, Object> notify = new HashMap<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_NOTIFY_ALL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_NOTIFY_ALL, params, "none", outParam.size());
		if (outParam.size() > 0) {
			notify.put("status", 1);
			notify.put("message", "OK");
			List<HashMap<String, String>> data = new ArrayList<>();
			for (int i = 0; i < outParam.size(); i += 6) {
				HashMap<String, String> map = new HashMap<>();
				map.put("id", outParam.get(i));
				map.put("name", outParam.get(i + 1));
				map.put("location_id", outParam.get(i + 2));
				map.put("location_name", outParam.get(i + 3));
				map.put("speciality_id", outParam.get(i + 4));
				map.put("speciality_name", outParam.get(i + 5));
				data.add(map);
			}
			notify.put("data", data);
		} else {
			notify.put("status", 0);
			notify.put("message", "NO DATA");
		}
		return notify;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getNotifyRealTime() {
		Map<String, Object> notify = new HashMap<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_NOTIFY_REAL_TIME, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_NOTIFY_REAL_TIME, params, "none", outParam.size());
		if (outParam.size() > 0) {
			notify.put("status", 1);
			notify.put("message", "OK");
			List<HashMap<String, String>> data = new ArrayList<>();
			for (int i = 0; i < outParam.size(); i += 6) {
				HashMap<String, String> map = new HashMap<>();
				map.put("id", outParam.get(i));
				map.put("name", outParam.get(i + 1));
				map.put("location_id", outParam.get(i + 2));
				map.put("location_name", outParam.get(i + 3));
				map.put("speciality_id", outParam.get(i + 4));
				map.put("speciality_name", outParam.get(i + 5));
				data.add(map);
			}
			notify.put("data", data);
		} else {
			notify.put("status", 0);
			notify.put("message", "NO DATA");
		}
		return notify;
	}

	public static void main(String[] args) {
		eSmileDao e = new eSmileDao();
		// System.out.println(e.getFaculty("19001", "2"));
		// System.out.println(e.getKey("-1"));

		// System.out.println(e.getMenu("222c8fe82063606f", "3"));
		// System.out.println(e.getNotifyAll());
		System.out.println(e.setRating("1", "19001", "4", "-2"));
	}
}
