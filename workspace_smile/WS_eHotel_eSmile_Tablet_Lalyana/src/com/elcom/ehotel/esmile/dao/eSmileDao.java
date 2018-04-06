package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.InfoModel;
import com.elcom.ehotel.esmile.model.LocationModel;
import com.elcom.ehotel.esmile.model.ObjFood;
import com.elcom.ehotel.esmile.model.ObjInfo;
import com.elcom.ehotel.esmile.model.ObjItem;
import com.elcom.ehotel.esmile.model.ObjItemInfo;
import com.elcom.ehotel.esmile.model.ObjLang;
import com.elcom.ehotel.esmile.model.ObjLocation;
import com.elcom.ehotel.esmile.model.ObjLogin;
import com.elcom.ehotel.esmile.model.ObjLoginStatus;
import com.elcom.ehotel.esmile.model.ObjMobileNotify;
import com.elcom.ehotel.esmile.model.ObjNotify;
import com.elcom.ehotel.esmile.model.ObjPage;
import com.elcom.ehotel.esmile.model.ObjPostSurvey;
import com.elcom.ehotel.esmile.model.ObjPromotion;
import com.elcom.ehotel.esmile.model.ObjRating;
import com.elcom.ehotel.esmile.model.ObjRatingDetail;
import com.elcom.ehotel.esmile.model.ObjRatingNotify;
import com.elcom.ehotel.esmile.model.ObjRatingStaff;
import com.elcom.ehotel.esmile.model.ObjReRegister;
import com.elcom.ehotel.esmile.model.ObjRegister;
import com.elcom.ehotel.esmile.model.ObjResult;
import com.elcom.ehotel.esmile.model.ObjShipSchedule;
import com.elcom.ehotel.esmile.model.ObjSmile;
import com.elcom.ehotel.esmile.model.ObjStaff;
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
import com.elcom.ehotel.esmile.util.UnicodeConverter;

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
	public List<InfoModel> getInfo(String key, String langid, String typein) {
		List<InfoModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(typein), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_INFO, params, "none", outParam.size() / 7);
			for (int i = 0; i < outParam.size(); i = i + 7) {
				InfoModel loc = new InfoModel(outParam.get(i), convertToArrayLang(outParam.get(i + 1)),
						convertToArraySmile(outParam.get(i + 2)), convertToArrayRating(outParam.get(i + 3)),
						convertToArrayWelcome(outParam.get(i + 4)), convertToArrayBackground(outParam.get(i + 5)),
						convertToArrayPromotion(outParam.get(i + 6)));
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

	public List<ObjRatingDetail> convertToArrayRatingDetail(String input) {
		List<ObjRatingDetail> arrRatingDetail = new ArrayList<ObjRatingDetail>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 3) {
					ObjRatingDetail obj = new ObjRatingDetail(arr2[i], arr2[i + 1], arr2[i + 2]);
					arrRatingDetail.add(obj);
				}
				return arrRatingDetail;
			}
		}
		return arrRatingDetail;
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
				for (int i = 0; i < arr2.length; i += 7) {
					ObjRating obj = new ObjRating(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3], arr2[i + 4], arr2[i + 5], arr2[i + 6]);
					arrRating.add(obj);
				}
				return arrRating;
			}
		}
		return arrRating;
	}

	public List<ObjRatingStaff> convertToArrayRatingTablet(String input) {
		List<ObjRatingStaff> arrRating = new ArrayList<ObjRatingStaff>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 5) {
					ObjRatingStaff obj = new ObjRatingStaff(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3], arr2[i + 4]);
					arrRating.add(obj);
				}
				return arrRating;
			}
		}
		return arrRating;
	}

	public List<ObjPage> convertToArrayPage(String input) {
		List<ObjPage> arrRating = new ArrayList<ObjPage>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 2) {
					ObjPage obj = new ObjPage(arr2[i], arr2[i + 1]);
					arrRating.add(obj);
				}
				return arrRating;
			}
		}
		return arrRating;
	}

	public List<ObjWelcome> convertToArrayWelcome(String input) {
		List<ObjWelcome> arrWelcome = new ArrayList<ObjWelcome>();
		if (input != null && input.length() > 0) {

			if (input.contains("|")) {
				if (input.endsWith("|"))
					input = input.substring(0, input.length() - 1);
				String[] arr2 = input.split("\\|");
				for (int i = 0; i < arr2.length; i += 3) {
					ObjWelcome obj = new ObjWelcome(arr2[i], arr2[i + 1], arr2[i + 2]);
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
				for (int i = 0; i < arr2.length; i += 5) {
					ObjSmile obj = new ObjSmile(arr2[i], arr2[i + 1], arr2[i + 2], arr2[i + 3], arr2[i + 4]);
					arrSmile.add(obj);
				}
				return arrSmile;
			}
		}
		return arrSmile;
	}

	public static void main(String[] args) {
		eSmileDao e = new eSmileDao();
		System.out.println(e.getRatingNotify());

	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> setRating(String key, String smileid, JSONArray arrRatingID) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(smileid), SubProParam.IN);
		params.add(param);
		String arrRID = "";
		try {
			arrRID = convertArrayToString(arrRatingID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		param = new SubProParam(new String(arrRID), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.SET_RATING, params, "none", outParam.size() / 1);
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

	private String convertArrayToString(JSONArray arrin) throws Exception {
		// TODO Auto-generated method stub
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
	public List<ObjSubjectInfo> getSubjectInfoHotel(String key, String langid) {
		List<ObjSubjectInfo> list = new ArrayList<>();
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
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_INFO_HOTEL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SUBJECT_INFO_HOTEL, params, "none", outParam.size() / 3);
			for (int i = 0; i < outParam.size(); i = i + 3) {
				ObjSubjectInfo subject = new ObjSubjectInfo(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2));
				list.add(subject);
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjItemInfo> getItemBySubjectID(String key, String subjectid, String langid) {
		List<ObjItemInfo> list = new ArrayList<>();
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
			params = SQL.broker.executeSubPro(SQL.GET_INTEM_BY_SUBJECT_ID, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_INTEM_BY_SUBJECT_ID, params, "none", outParam.size() / 3);
			for (int i = 0; i < outParam.size(); i = i + 9) {
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
	public List<ObjResult> setRatingSurvey(String key, List<ObjPostSurvey> arrSurveyPost, String folionum, String guestname) {
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
		param = new SubProParam(new String(folionum), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(guestname), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_SURVEY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
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
	public List<ObjResult> setRatingSurvey(String key, String staffid, List<ObjPostSurvey> arrSurveyPost, String folionum, String guestname) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(staffid), SubProParam.IN);
		params.add(param);
		String arrRID = "";
		try {
			arrRID = convertArrayToString(arrSurveyPost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		param = new SubProParam(new String(arrRID), SubProParam.IN);
		params.add(param);

		param = new SubProParam(new String(folionum), SubProParam.IN);
		params.add(param);

		param = new SubProParam(new String(guestname), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_SURVEY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
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
						UnicodeConverter.decodeUnicode(outParam.get(i + 4)), outParam.get(i + 5), outParam.get(i + 6));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				objectJSON.put(Param.status, "0");
				objectJSON.put(Param.message, "FAIL");
				objectJSON.put(Param.data, list);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
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
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_NOTIFY, params, "none", outParam.size() / 9);
			for (int i = 0; i < outParam.size(); i = i + 9) {
				ObjNotify obj = new ObjNotify(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6), outParam.get(i + 7), outParam.get(i + 8));
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
		LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_NOTIFY, params, "none", outParam.size() / 1);
		if (outParam.size() > 0) {
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
	public List<ObjResult> setRatingGuest(String key, String smileid, JSONArray arrRatingID, String folionum, String nameguest,
			String checkindate, String checkountdate) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(smileid), SubProParam.IN);
		params.add(param);
		String arrRID = "";
		try {
			arrRID = convertArrayToString(arrRatingID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		param = new SubProParam(new String(arrRID), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(folionum), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(nameguest), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(checkindate), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(checkountdate), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_RATING_GUEST, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(7);
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
	public List<ObjResult> setCommentGuest(String key, String smileid, String textcomment, String folionum, String nameguest,
			String checkindate, String checkountdate) {
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
		param = new SubProParam(new String(folionum), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(nameguest), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(checkindate), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(checkountdate), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_COMMENT_GUEST, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(7);
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

	// new api

	@SuppressWarnings("unchecked")
	public ObjRegister postRegister(String storeid, String ipadd, String name) {
		ObjRegister objReg = new ObjRegister("0", Param.fail, "");
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(storeid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(ipadd), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(name), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_REGISTER, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_REGISTER, params, "none", outParam.size() / 1);
			for (int i = 0; i < outParam.size(); i++) {
				String rs = outParam.get(i);
				if (Integer.parseInt(rs) > 0) {
					objReg = new ObjRegister("1", Param.ok, rs);
				} else {
					objReg = new ObjRegister("0", Param.fail, "");
				}
			}
		} else {
			objReg = new ObjRegister("0", Param.fail, "");
		}
		return objReg;
	}

	@SuppressWarnings("unchecked")
	public List<LocationModel> getLocations() {
		List<LocationModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LOCATIONS, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_LOCATIONS, params, "none", outParam.size() / 2);
			for (int i = 0; i < outParam.size(); i = i + 2) {
				LocationModel loc = new LocationModel();
				loc.setId(isNull(outParam.get(i)));
				loc.setName(isNull(outParam.get(i + 1)));
				list.add(loc);
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
	public ObjInfo getInfos(String key, String langid, String typein) {
		ObjInfo objInfo = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(langid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(typein), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_INFOS, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_INFOS, params, "none", outParam.size() / 10);
			// for (int i = 0; i < outParam.size(); i = i + 9) {
			int i = 0;
			ObjInfo loc = new ObjInfo(outParam.get(i), convertToArrayLang(outParam.get(i + 1)), convertToArraySmile(outParam.get(i + 2)),
					convertToArrayRatingTablet(outParam.get(i + 3)), convertToArrayRatingTablet(outParam.get(i + 4)),
					convertToArrayPage(outParam.get(i + 5)), convertToArrayWelcome(outParam.get(i + 6)),
					convertToArrayBackground(outParam.get(i + 7)), convertToArrayPromotion(outParam.get(i + 8)),
					convertToArrayRatingDetail(outParam.get(i + 9)));
			return loc;
			// }
		}
		return objInfo;
	}

	@SuppressWarnings("unchecked")
	public ObjLogin postLogin(String username, String password, String key) {
		ObjLogin objLogin = new ObjLogin("0", Param.fail, "", "", "", "");
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam param = null;
		param = new SubProParam(new String(username), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(com.elcom.ehotel.esmile.util.Encryptor.encryptPassword(password)), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_LOGIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_LOGIN, params, "none", outParam.size() / 4);
			// for (int i = 0; i < outParam.size(); i = i + 4) {
			int i = 0;
			if (Integer.parseInt(outParam.get(i)) > 0)
				objLogin = new ObjLogin("1", Param.ok, outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3));
			else
				objLogin = new ObjLogin("0", Param.fail, "", "", "", "");
			// }
		}
		return objLogin;
	}

	@SuppressWarnings("unchecked")
	public ObjResult postLogout(String staffid, String key) {
		ObjResult objRs = new ObjResult("0", Param.fail);
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;

		param = new SubProParam(new String(staffid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_LOGOUT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_LOGOUT, params, "none", outParam.size() / 1);
			// for (int i = 0; i < outParam.size(); i = i + 4) {
			int i = 0;
			if (Integer.parseInt(outParam.get(i)) > 0)
				objRs = new ObjResult("1", Param.ok);
			else
				objRs = new ObjResult("0", Param.fail);
			// }
		}
		return objRs;
	}

	@SuppressWarnings("unchecked")
	public ObjLoginStatus checkLogin(String staffid) {
		ObjLoginStatus objLogin = new ObjLoginStatus("0", "", "");
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam param = null;
		param = new SubProParam(new String(staffid), SubProParam.IN);
		params.add(param);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CHECK_LOGIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.CHECK_LOGIN, params, "none", outParam.size() / 3);
			// for (int i = 0; i < outParam.size(); i = i + 4) {
			int i = 0;
			if (Integer.parseInt(outParam.get(i)) > 0)
				objLogin = new ObjLoginStatus("1", outParam.get(i), outParam.get(i + 1));
			else
				objLogin = new ObjLoginStatus("0", "", "");
			// }
		}
		return objLogin;
	}

	@SuppressWarnings("unchecked")
	public ObjResult postSmile(String staffid, String idSmile, String key) {
		ObjResult objRs = new ObjResult("0", Param.fail);
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(idSmile), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(staffid), SubProParam.IN);
		params.add(param);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_SMILE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_SMILE, params, "none", outParam.size() / 1);
			// for (int i = 0; i < outParam.size(); i = i + 4) {
			int i = 0;
			if (Integer.parseInt(outParam.get(i)) > 0)
				objRs = new ObjResult("1", Param.ok);
			else
				objRs = new ObjResult("0", Param.fail);
			// }
		}
		return objRs;
	}

	@SuppressWarnings("unchecked")
	public ObjResult postRating(String key, String idSmile, String ratingid, String staffid) {
		ObjResult objRs = new ObjResult("0", Param.fail);
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;

		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(idSmile), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(ratingid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(staffid), SubProParam.IN);
		params.add(param);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_RATING, params, "none", outParam.size() / 1);
			// for (int i = 0; i < outParam.size(); i = i + 4) {
			int i = 0;
			if (Integer.parseInt(outParam.get(i)) > 0)
				objRs = new ObjResult("1", Param.ok);
			else
				objRs = new ObjResult("0", Param.fail);
			// }
		}
		return objRs;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> postRatingChild(String key, String smileid, String idrating, JSONArray arrRatingID, String staffid) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(smileid), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(idrating), SubProParam.IN);
		params.add(param);

		String arrRID = "";
		try {
			arrRID = convertArrayToString(arrRatingID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		param = new SubProParam(new String(arrRID), SubProParam.IN);
		params.add(param);

		param = new SubProParam(new String(staffid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_RATING_CHILD, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_RATING_CHILD, params, "none", outParam.size() / 1);
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
	public List<ObjStaff> getEmployee() {
		List<ObjStaff> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_EMPLOYEE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_EMPLOYEE, params, "none", outParam.size() / 6);
			for (int i = 0; i < outParam.size(); i = i + 6) {
				ObjStaff loc = new ObjStaff(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5));
				list.add(loc);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public ObjReRegister postReRegister(String storeid) {
		ObjReRegister objReg = new ObjReRegister("", "", null);
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(storeid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.POST_REREGISTER, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			objReg.setId(outParam.get(0));
			objReg.setName(outParam.get(1));
			List<ObjLocation> loca = new ArrayList<>();
			LogUtil.logDao(eSmileDao.class.toString(), SQL.POST_REREGISTER, params, "none", outParam.size() / 3);
			for (int i = 2; i < outParam.size(); i = i + 3) {
				String idKey = outParam.get(i);
				String namekey = outParam.get(i + 1);
				String ipkey = outParam.get(i + 2);
				loca.add(new ObjLocation(idKey, namekey, ipkey));
			}
			objReg.setDevice(loca);
		}
		return objReg;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getMobileNotify() {
		List<ObjMobileNotify> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_MOBILE_NOTIFY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() >= 8) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_MOBILE_NOTIFY, params, "none", outParam.size() / 8);
			for (int i = 0; i < outParam.size(); i = i + 8) {
				ObjMobileNotify obj = new ObjMobileNotify(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2),
						getListUser(outParam.get(i + 7)));
				list.add(obj);
			}
		}
		JSONObject objectJSON = new JSONObject();
		if (list.size() > 0) {
			try {
				objectJSON.put(Param.status, "1");
				objectJSON.put(Param.message, Param.ok);
				objectJSON.put(Param.data, list);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				objectJSON.put(Param.status, "0");
				objectJSON.put(Param.message, Param.fail);
				objectJSON.put(Param.data, list);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return objectJSON;
	}

	String[] getListUser(String input) {
		try {
			if (input.contains(","))
				return input.split(",");
			else
				return new String[] { input };
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new String[] {};
	}

	@SuppressWarnings("unchecked")
	public List<ObjNotify> getMobileNotifyAll(String userid) {
		List<ObjNotify> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(userid), SubProParam.IN);
		params.add(param);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_MOBILE_NOTIFY_ALL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() >= 9) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_MOBILE_NOTIFY_ALL, params, "none", outParam.size() / 9);
			for (int i = 0; i < outParam.size(); i = i + 9) {
				ObjNotify obj = new ObjNotify(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6), outParam.get(i + 7), outParam.get(i + 8));
				list.add(obj);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ObjResult> confirmMobileNotify(String id, String userid) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);

		param = new SubProParam(new String(userid), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.CONFIRM_MOBILE_NOTIFY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.CONFIRM_MOBILE_NOTIFY, params, "none", outParam.size() / 1);
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
	public List<ObjResult> deleteMobileNotify(String id, String userid) {
		List<ObjResult> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam param = null;
		param = new SubProParam(new String(id), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(userid), SubProParam.IN);
		params.add(param);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_MOBILE_NOTIFY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() > 0) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_MOBILE_NOTIFY, params, "none", outParam.size() / 1);
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
	public HashMap<String, String> setCommentNew(String key, String room, String guest, String comment) {
		System.out.println(key);
		System.out.println(room);
		System.out.println(guest);
		System.out.println(comment);
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(key), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(room), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(guest), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(comment), SubProParam.IN);
		params.add(param);

		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_COMMENT_NEW, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.SET_COMMENT_NEW, params, "key,room,guest,comment", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<ObjNotify> getNotifyComment() {
		List<ObjNotify> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_NOTIFY_COMMENT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (outParam.size() >= 9) {
			LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_NOTIFY_COMMENT, params, "none", outParam.size() / 7);
			for (int i = 0; i < outParam.size(); i = i + 7) {
				ObjNotify obj = new ObjNotify(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
						outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6));
				list.add(obj);
			}
		}
		return list;
	}
	
}
