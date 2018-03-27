package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.AllRatingModel;
import com.elcom.ehotel.esmile.model.ConfigModel;
import com.elcom.ehotel.esmile.model.DataDetailModel;
import com.elcom.ehotel.esmile.model.DataStandardModel;
import com.elcom.ehotel.esmile.model.LocationModel;
import com.elcom.ehotel.esmile.model.RatingDetailModel;
import com.elcom.ehotel.esmile.model.StaffSmileModel;
import com.elcom.ehotel.esmile.model.PromotionModel;
import com.elcom.ehotel.esmile.model.RatingDivisionModel;
import com.elcom.ehotel.esmile.model.RatingModel;
import com.elcom.ehotel.esmile.model.RatingSurveyModel;
import com.elcom.ehotel.esmile.model.SmileModel;
import com.elcom.ehotel.esmile.model.SmileRatingModel;
import com.elcom.ehotel.esmile.model.StaffRatingModel;
import com.elcom.ehotel.esmile.model.StaffVoteModel;
import com.elcom.ehotel.esmile.model.StatisticModel;
import com.elcom.ehotel.esmile.model.SurveyModel;
import com.elcom.ehotel.esmile.model.TabletModel;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.SQL;
import com.elcom.ehotel.esmile.util.UnicodeConverter;

public class eSmileDao {
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getLogin(String username, String password) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(username), 0);
		params.add(in);
		in = new SubProParam(new String(password), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LOGIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_LOGIN, params, "username,password", outParam.size());
		int temp = ConvertUtil.convertToInteger(outParam.get(0));
		HashMap<String, String> hm = new HashMap<>();
		if (temp < 0) {
			hm.put("status", outParam.get(0));
			hm.put("message", outParam.get(1));
		} else {
			hm.put("user_id", outParam.get(0));
			hm.put("username", outParam.get(1));
			hm.put("name", outParam.get(2));
			hm.put("image", outParam.get(3));
			hm.put("role_id", outParam.get(4));
		}
		return hm;
	}

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
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ConfigModel> getBackground() {
		List<ConfigModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_BACKGROUND, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_BACKGROUND, params, "none", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			ConfigModel bg = new ConfigModel();
			bg.setId(outParam.get(i));
			bg.setName(outParam.get(i + 1));
			bg.setValue(outParam.get(i + 2));
			bg.setType(outParam.get(i + 3));
			bg.setStatus(outParam.get(i + 4));
			list.add(bg);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deleteBackground(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_BACKGROUND, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_BACKGROUND, params, "id", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editBackground(String id, String value) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(value), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_BACKGROUND, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_BACKGROUND, params, "id,value", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> addBackground(String value) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(value), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_BACKGROUND, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.ADD_BACKGROUND, params, "value", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<StatisticModel> getStatistic(String from, String to, String listlocation) {
		List<StatisticModel> list = new ArrayList<StatisticModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_STATISTIC, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_STATISTIC, params, "from,to,listlocation", outParam.size() / 7);
		for (int i = 0; i < outParam.size(); i = i + 7) {
			StatisticModel sttt = new StatisticModel();
			sttt.setDate(outParam.get(i));
			sttt.setExcellent(outParam.get(i + 1));
			sttt.setGood(outParam.get(i + 2));
			sttt.setAverage(outParam.get(i + 3));
			sttt.setPoor(outParam.get(i + 4));
			sttt.setVerypoor(outParam.get(i + 5));
			sttt.setOther(outParam.get(i + 6));
			list.add(sttt);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<RatingModel> getRating(String from, String to, String listlocation, String smileid, String langid) {
		List<RatingModel> list = new ArrayList<RatingModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(smileid), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING, params, "from,to,listlocation,smileid,langid", outParam.size() / 4);
		for (int i = 0; i < outParam.size(); i = i + 4) {
			RatingModel rate = new RatingModel();
			rate.setId(outParam.get(i));
			rate.setName(outParam.get(i + 1));
			rate.setNum(outParam.get(i + 2));
			rate.setSum(outParam.get(i + 3));
			list.add(rate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<RatingModel> getRating6(String from, String to, String listlocation, String smileid, String langid) {
		List<RatingModel> list = new ArrayList<RatingModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(smileid), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_6, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_6, params, "from,to,listlocation,smileid,langid", outParam.size() / 4);
		for (int i = 0; i < outParam.size(); i = i + 4) {
			RatingModel rate = new RatingModel();
			HashMap<String, String> comment = new HashMap<>();
			rate.setId(outParam.get(i));
			rate.setName(outParam.get(i + 1));
			comment.put("comment", outParam.get(i + 2));
			comment.put("date_time:", outParam.get(i + 3));
			rate.setComments(comment);
			rate.setNum("1");
			list.add(rate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SmileModel> getSmile(String from, String to, String listlocation, String langid) {
		List<SmileModel> list = new ArrayList<SmileModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SMILE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SMILE, params, "from,to,listlocation,langid", outParam.size() / 4);
		for (int i = 0; i < outParam.size(); i = i + 4) {
			SmileModel smile = new SmileModel();
			smile.setId(outParam.get(i));
			smile.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			smile.setNum(outParam.get(i + 2));
			smile.setSum(outParam.get(i + 3));
			list.add(smile);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<PromotionModel> getPromotion() {
		List<PromotionModel> list = new ArrayList<PromotionModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_PROMOTION, params, "none", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			PromotionModel pro = new PromotionModel();
			pro.setId(outParam.get(i));
			pro.setName(outParam.get(i + 1));
			pro.setUrl(outParam.get(i + 2));
			pro.setImage(outParam.get(i + 3));
			pro.setStatus(outParam.get(i + 4));
			pro.setModify(outParam.get(i + 5));
			list.add(pro);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> addPromotion(String name, String url, String image) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(url), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.ADD_PROMOTION, params, "name,url,image", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editPromotion(String id, String name, String url, String image) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(url), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_PROMOTION, params, "id,name,url,image", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deletePromotion(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_PROMOTION, params, "id", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	public List<AllRatingModel> getAllRating(String from, String to, String listlocation, String langid) {
		List<AllRatingModel> list = new ArrayList<>();
		List<SmileModel> listsmile = new ArrayList<>();
		List<RatingModel> listrate = new ArrayList<>();
		List<HashMap<String, String>> listcmt = new ArrayList<>();
		listsmile = getSmile(from, to, listlocation, langid);
		for (int i = 0; i < listsmile.size(); i++) {
			AllRatingModel arm = new AllRatingModel();
			arm.setSmile_id(listsmile.get(i).getId());

			if (arm.getSmile_id().equals("6")) {
				// listrate = getRating6(from, to, listlocation, arm.getSmile_id(), langid);
				listcmt = getListComment(from, to, listlocation);
				arm.setComment(listcmt);
			} else {
				arm.setName(listsmile.get(i).getName());
				listrate = getRating(from, to, listlocation, arm.getSmile_id(), langid);
				arm.setRating(listrate);
			}
			list.add(arm);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListComment(String from, String to, String listlocation) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_COMMENT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_COMMENT, params, "from,to,listlocation", outParam.size() / 2);
		for (int i = 0; i < outParam.size(); i = i + 2) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("comment", outParam.get(i));
			map.put("date_time", outParam.get(i + 1));
			list.add(map);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public RatingDivisionModel getRatingDivision(String from, String to, String listlocation, String langid) {
		RatingDivisionModel rating = new RatingDivisionModel();
		List<RatingModel> listgood = new ArrayList<RatingModel>();
		List<RatingModel> listbad = new ArrayList<RatingModel>();
		List<HashMap<String, String>> listcomment = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_DIVISION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_DIVISION, params, "from,to,listlocation,langid", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			RatingModel rate = new RatingModel();
			rate.setId(outParam.get(i));
			rate.setName(outParam.get(i + 1));
			rate.setNum(outParam.get(i + 2));
			if (rate.getId().equals("1") || rate.getId().equals("2") || rate.getId().equals("3") || rate.getId().equals("4")
					|| rate.getId().equals("5") || rate.getId().equals("6")) {
				rate.setSum(outParam.get(i + 3));
				listgood.add(rate);
			} else {
				rate.setSum(outParam.get(i + 4));
				listbad.add(rate);
			}
		}
		listcomment = getListComment(from, to, listlocation);
		rating.setRating_good(listgood);
		rating.setRating_bad(listbad);
		rating.setComment(listcomment);
		return rating;
	}

	@SuppressWarnings("unchecked")
	public SurveyModel getListSurvey(String langid) {
		SurveyModel sur = new SurveyModel();
		List<HashMap<String, String>> smile = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> survey = new ArrayList<HashMap<String, String>>();

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
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
		// System.out.println(outParam.size());
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SURVEY, params, "langid", outParam.size());
		int count = ConvertUtil.convertToInteger(outParam.get(0));
		for (int i = 1; i <= count * 3; i = i + 3) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", outParam.get(i));
			map.put("name", outParam.get(i + 1));
			map.put("image", outParam.get(i + 2));
			smile.add(map);
		}
		sur.setSmile(smile);
		for (int j = count * 3 + 1; j < outParam.size(); j = j + 4) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", outParam.get(j));
			map.put("name", outParam.get(j + 1));
			map.put("image", outParam.get(j + 2));
			map.put("status", outParam.get(j + 3));
			survey.add(map);
		}
		sur.setSurvey(survey);
		return sur;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> addSurvey(String name, String image) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_SURVEY, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.ADD_SURVEY, params, "name,image", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editSurvey(String id, String name, String image, String status, String langid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(status), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_SURVEY, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_SURVEY, params, "id,name,image,status,langid", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deleteSurvey(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_SURVEY, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_SURVEY, params, "id", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListRoom() {
		List<HashMap<String, String>> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_ROOM, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_ROOM, params, "none", outParam.size());
		for (int i = 0; i < outParam.size(); i = i + 2) {
			HashMap<String, String> map = new HashMap<>();
			map.put("id", outParam.get(i));
			map.put("name", outParam.get(i + 1));
			list.add(map);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editSmile(String id, String name, String image, String langid, String type) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_SMILE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_SMILE, params, "id,name,image,langid,type", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editRating(String id, String name, String image, String langid, String type) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_RATING, params, "id,name,image,langid,type", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<RatingSurveyModel> getVoteSurvey(String from, String to, String listroom, String langid) {
		List<RatingSurveyModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listroom), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_VOTE_SURVEY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_RATING, params, "from,to,listroom,langid", outParam.size() / 7);
		for (int i = 0; i < outParam.size(); i = i + 7) {
			RatingSurveyModel sur = new RatingSurveyModel();
			sur.setId(outParam.get(i));
			sur.setName(outParam.get(i + 1));
			sur.setPoor(outParam.get(i + 2));
			sur.setAverge(outParam.get(i + 3));
			sur.setGood(outParam.get(i + 4));
			sur.setExcellent(outParam.get(i + 5));
			sur.setSum(outParam.get(i + 6));
			list.add(sur);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TabletModel> getListTablet(String userid) {
		List<TabletModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(userid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_TABLET, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_LIST_TABLET, params, "userid", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			TabletModel tablet = new TabletModel();
			tablet.setId(outParam.get(i));
			tablet.setName(outParam.get(i + 1));
			tablet.setImage(outParam.get(i + 2));
			tablet.setIsLogin(outParam.get(i + 3));
			tablet.setLogin_by(outParam.get(i + 4));
			tablet.setUser_id(outParam.get(i + 5));
			list.add(tablet);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editTablet(String id, String name, String image) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_TABLET, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_TABLET, params, "id,name,image,", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<StaffVoteModel> getCompareStaff(String liststaff, String listlocation, String from, String to, String langid) {
		List<StaffVoteModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_VOTE_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_VOTE_STAFF, params, "listuser,listlocation,from,to,langid", outParam.size() / 6);
		if (outParam.size() > 0) {
			StaffVoteModel vote = new StaffVoteModel();
			List<SmileModel> listsmile = new ArrayList<>();
			String stafftemp = outParam.get(0);
			vote.setUser_id(stafftemp);
			vote.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!stafftemp.equals(outParam.get(i))) {
					vote.setSmile(listsmile);
					list.add(vote);
					listsmile = new ArrayList<>();
					vote = new StaffVoteModel();
					vote.setUser_id(outParam.get(i));
					vote.setName(outParam.get(i + 1));
					stafftemp = outParam.get(i);
				}
				SmileModel smile = new SmileModel();
				smile.setId(outParam.get(i + 2));
				smile.setName(outParam.get(i + 3));
				smile.setNum(outParam.get(i + 4));
				smile.setSum(outParam.get(i + 5));
				listsmile.add(smile);
			}

			vote.setSmile(listsmile);
			list.add(vote);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<RatingModel> getRatingChild(String rid, String listlocation, String from, String to, String langid, String smileid) {
		List<RatingModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(rid), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		in = new SubProParam(new String(smileid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_CHILD, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(6);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_CHILD, params, "ratingid,listlocation,from,to,langid,smileid",
				outParam.size() / 4);
		for (int i = 0; i < outParam.size(); i = i + 4) {
			RatingModel rate = new RatingModel();
			rate.setId(outParam.get(i));
			rate.setName(outParam.get(i + 1));
			rate.setNum(outParam.get(i + 2));
			rate.setSum(outParam.get(i + 3));
			list.add(rate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SmileRatingModel> getRatingAll(String listsmile, String listlocation, String from, String to, String langid) {
		List<SmileRatingModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(listsmile), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_ALL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_ALL, params, "listsmile,listlocation,from,to,langid", outParam.size() / 6);
		if (outParam.size() > 0) {
			List<RatingModel> listrating = new ArrayList<>();
			String sidtem = outParam.get(0);
			SmileRatingModel rate = new SmileRatingModel();
			rate.setId(outParam.get(0));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!sidtem.equals(outParam.get(i))) {
					rate.setRating(listrating);
					list.add(rate);
					System.out.println(rate);
					listrating = new ArrayList<>();
					rate = new SmileRatingModel();
					rate.setId(outParam.get(i));
					rate.setName(outParam.get(i + 1));
					sidtem = outParam.get(i);
				}
				RatingModel rating = new RatingModel();
				rating.setId(outParam.get(i + 2));
				rating.setName(outParam.get(i + 3));
				rating.setNum(outParam.get(i + 4));
				rating.setSum(outParam.get(i + 5));
				listrating.add(rating);
			}
			rate.setRating(listrating);
			list.add(rate);
		}
		// System.out.println(list.size());
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SmileRatingModel> getChild(String listsmile, String listlocation, String from, String to, String langid) {
		List<SmileRatingModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(listsmile), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_ALL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_ALL, params, "listsmile,listlocation,from,to,langid", outParam.size());
		List<RatingModel> listrating = new ArrayList<>();
		String sidtem = outParam.get(0);
		SmileRatingModel rate = new SmileRatingModel();
		for (int i = 0; i < outParam.size(); i = i + 4) {
			if (!sidtem.equals(outParam.get(i))) {
				rate.setRating(listrating);
				list.add(rate);
				listrating = new ArrayList<>();
				rate.setId(outParam.get(i));
				rate.setName(outParam.get(i + 1));
				sidtem = outParam.get(i);
			}
			RatingModel rating = new RatingModel();
			rating.setId(outParam.get(i + 2));
			rating.setName(outParam.get(i + 3));
			rating.setNum(outParam.get(i + 4));
			rating.setSum(outParam.get(i + 5));
			listrating.add(rating);
		}
		if (outParam.size() > 0) {
			rate.setRating(listrating);
			list.add(rate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StaffSmileModel> getCompareLocation(String listlocation, String from, String to, String langid) {
		List<StaffSmileModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_COMPARE_LOCATION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_COMPARE_LOCATION, params, "listlocation,from,to,langid", outParam.size() / 6);
		if (outParam.size() > 0) {
			List<SmileModel> listsmile = new ArrayList<>();
			String lidtemp = outParam.get(0);
			StaffSmileModel local = new StaffSmileModel();
			local.setId(outParam.get(0));
			local.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!lidtemp.equals(outParam.get(i))) {
					local.setSmile(listsmile);
					list.add(local);
					listsmile = new ArrayList<>();
					local = new StaffSmileModel();
					local.setId(outParam.get(i));
					local.setName(outParam.get(i + 1));
					lidtemp = outParam.get(i);
				}
				SmileModel smile = new SmileModel();
				smile.setId(outParam.get(i + 2));
				smile.setName(outParam.get(i + 3));
				smile.setNum(outParam.get(i + 4));
				smile.setSum(outParam.get(i + 5));
				listsmile.add(smile);
			}

			local.setSmile(listsmile);
			list.add(local);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getStatisticSmile(String from, String to, String listlocation, String langid) {
		List<HashMap<String, String>> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_STATISTIC_SMILE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_STATISTIC_SMILE, params, "from,to,listlocation,langid", outParam.size() / 3);
		if (outParam.size() > 0) {
			HashMap<String, String> hm = new HashMap<>();
			hm.put("data", outParam.get(0));
			String temp = outParam.get(0);
			for (int i = 0; i < outParam.size(); i = i + 4) {
				if (!temp.equals(outParam.get(i))) {
					list.add(hm);
					hm = new HashMap<>();
					hm.put("data", outParam.get(i));
					temp = hm.put("data", outParam.get(i));
				}
				hm.put(convertName(outParam.get(i + 1)), outParam.get(i + 2));
			}
			list.add(hm);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getStatisticRating(String from, String to, String listlocation, String langid) {
		List<HashMap<String, String>> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_STATISTIC_RATING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_STATISTIC_RATING, params, "from,to,listlocation,langid", outParam.size() / 3);
		if (outParam.size() > 0) {
			HashMap<String, String> hm = new HashMap<>();
			hm.put("data", outParam.get(0));
			String temp = outParam.get(0);
			for (int i = 0; i < outParam.size(); i = i + 3) {
				if (!temp.equals(outParam.get(i))) {
					list.add(hm);
					hm = new HashMap<>();
					hm.put("data", outParam.get(i));
					temp = hm.put("data", outParam.get(i));
				}
				hm.put(convertName(outParam.get(i + 1)), outParam.get(i + 2));
			}
			list.add(hm);
		}
		return list;
	}

	public String convertName(String str) {
		if (str.equalsIgnoreCase("Our Products"))
			return "product";
		else if (str.equalsIgnoreCase("Price"))
			return "price";
		else if (str.equalsIgnoreCase("Staffs"))
			return "employee";
		else if (str.equalsIgnoreCase("Shop Ambience"))
			return "scene";
		else if (str.equalsIgnoreCase("Excellent"))
			return "excellent";
		else if (str.equalsIgnoreCase("Average"))
			return "average";
		else if (str.equalsIgnoreCase("Poor"))
			return "poor";
		return str;
	}

	@SuppressWarnings("unchecked")
	public List<SmileModel> getSmileStaff(String from, String to, String liststaff, String langid) {
		List<SmileModel> list = new ArrayList<SmileModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SMILE_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_SMILE_STAFF, params, "from,to,liststaff,langid", outParam.size() / 4);
		for (int i = 0; i < outParam.size(); i = i + 4) {
			SmileModel smile = new SmileModel();
			smile.setId(outParam.get(i));
			smile.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			smile.setNum(outParam.get(i + 2));
			smile.setSum(outParam.get(i + 3));
			list.add(smile);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<RatingModel> getRatingChildStaff(String rid, String liststaff, String from, String to, String langid, String smileid) {
		List<RatingModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(rid), 0);
		params.add(in);
		in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		in = new SubProParam(new String(smileid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_CHILD_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(6);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_CHILD_STAFF, params, "ratingid,listlocation,from,to,langid,smileid",
				outParam.size() / 4);
		for (int i = 0; i < outParam.size(); i = i + 4) {
			RatingModel rate = new RatingModel();
			rate.setId(outParam.get(i));
			rate.setName(outParam.get(i + 1));
			rate.setNum(outParam.get(i + 2));
			rate.setSum(outParam.get(i + 3));
			list.add(rate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<RatingModel> getRatingStaff(String from, String to, String liststaff, String smileid, String langid) {
		List<RatingModel> list = new ArrayList<RatingModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(smileid), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_STAFF, params, "from,to,listlocation,smileid,langid", outParam.size() / 4);
		for (int i = 0; i < outParam.size(); i = i + 4) {
			RatingModel rate = new RatingModel();
			rate.setId(outParam.get(i));
			rate.setName(outParam.get(i + 1));
			rate.setNum(outParam.get(i + 2));
			rate.setSum(outParam.get(i + 3));
			list.add(rate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StaffRatingModel> getRatingSmileStaff(String from, String to, String liststaff, String smileid, String langid) {
		List<StaffRatingModel> list = new ArrayList<StaffRatingModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(smileid), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_SMILE_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_SMILE_STAFF, params, "from,to,liststaff,smileid,langid",
				outParam.size() / 6);
		if (outParam.size() > 0) {
			List<RatingModel> listrate = new ArrayList<>();
			String staffidtemp = outParam.get(0);
			StaffRatingModel staffrate = new StaffRatingModel();
			staffrate.setId(outParam.get(0));
			staffrate.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!staffidtemp.equals(outParam.get(i))) {
					staffrate.setRating(listrate);
					list.add(staffrate);
					listrate = new ArrayList<>();
					staffrate = new StaffRatingModel();
					staffrate.setId(outParam.get(i));
					staffrate.setName(outParam.get(i + 1));
					staffidtemp = outParam.get(i);
				}
				RatingModel rate = new RatingModel();
				rate.setId(outParam.get(i + 2));
				rate.setName(outParam.get(i + 3));
				rate.setNum(outParam.get(i + 4));
				rate.setSum(outParam.get(i + 5));
				listrate.add(rate);
			}

			staffrate.setRating(listrate);
			list.add(staffrate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StaffRatingModel> getRatingChildSmileStaff(String rid, String liststaff, String from, String to, String langid,
			String smileid) {
		List<StaffRatingModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(rid), 0);
		params.add(in);
		in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		in = new SubProParam(new String(smileid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_CHILD_SMILE_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(6);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_CHILD_SMILE_STAFF, params,
				"ratingid,listlocation,from,to,langid,smileid", outParam.size() / 6);
		if (outParam.size() > 0) {
			List<RatingModel> listrate = new ArrayList<>();
			String staffidtemp = outParam.get(0);
			StaffRatingModel staffrate = new StaffRatingModel();
			staffrate.setId(outParam.get(0));
			staffrate.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!staffidtemp.equals(outParam.get(i))) {
					staffrate.setRating(listrate);
					list.add(staffrate);
					listrate = new ArrayList<>();
					staffrate = new StaffRatingModel();
					staffrate.setId(outParam.get(i));
					staffrate.setName(outParam.get(i + 1));
					staffidtemp = outParam.get(i);
				}
				RatingModel rate = new RatingModel();
				rate.setId(outParam.get(i + 2));
				rate.setName(outParam.get(i + 3));
				rate.setNum(outParam.get(i + 4));
				rate.setSum(outParam.get(i + 5));
				listrate.add(rate);
			}

			staffrate.setRating(listrate);
			list.add(staffrate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StaffRatingModel> getRatingSmileLocation(String from, String to, String listlocation, String smileid, String langid) {
		List<StaffRatingModel> list = new ArrayList<StaffRatingModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(smileid), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_SMILE_LOCATION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_SMILE_LOCATION, params, "from,to,listlocation,smileid,langid",
				outParam.size() / 6);
		if (outParam.size() > 0) {
			List<RatingModel> listrate = new ArrayList<>();
			String staffidtemp = outParam.get(0);
			StaffRatingModel staffrate = new StaffRatingModel();
			staffrate.setId(outParam.get(0));
			staffrate.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!staffidtemp.equals(outParam.get(i))) {
					staffrate.setRating(listrate);
					list.add(staffrate);
					listrate = new ArrayList<>();
					staffrate = new StaffRatingModel();
					staffrate.setId(outParam.get(i));
					staffrate.setName(outParam.get(i + 1));
					staffidtemp = outParam.get(i);
				}
				RatingModel rate = new RatingModel();
				rate.setId(outParam.get(i + 2));
				rate.setName(outParam.get(i + 3));
				rate.setNum(outParam.get(i + 4));
				rate.setSum(outParam.get(i + 5));
				listrate.add(rate);
			}

			staffrate.setRating(listrate);
			list.add(staffrate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StaffRatingModel> getRatingChildSmileLocation(String rid, String liststaff, String from, String to, String langid,
			String smileid) {
		List<StaffRatingModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(rid), 0);
		params.add(in);
		in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		in = new SubProParam(new String(smileid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_CHILD_SMILE_LOCATION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(6);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_CHILD_SMILE_LOCATION, params,
				"ratingid,listlocation,from,to,langid,smileid", outParam.size() / 6);
		if (outParam.size() > 0) {
			List<RatingModel> listrate = new ArrayList<>();
			String staffidtemp = outParam.get(0);
			StaffRatingModel staffrate = new StaffRatingModel();
			staffrate.setId(outParam.get(0));
			staffrate.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!staffidtemp.equals(outParam.get(i))) {
					staffrate.setRating(listrate);
					list.add(staffrate);
					listrate = new ArrayList<>();
					staffrate = new StaffRatingModel();
					staffrate.setId(outParam.get(i));
					staffrate.setName(outParam.get(i + 1));
					staffidtemp = outParam.get(i);
				}
				RatingModel rate = new RatingModel();
				rate.setId(outParam.get(i + 2));
				rate.setName(outParam.get(i + 3));
				rate.setNum(outParam.get(i + 4));
				rate.setSum(outParam.get(i + 5));
				listrate.add(rate);
			}

			staffrate.setRating(listrate);
			list.add(staffrate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<RatingDetailModel> getRatingDetail(String langid) {
		List<RatingDetailModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_DETAIL, params, "langid", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			RatingDetailModel rate = new RatingDetailModel(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
					outParam.get(i + 4));
			list.add(rate);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> addRatingDetail(String name) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_RATING_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.ADD_RATING_DETAIL, params, "rating", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> editRatingDetail(String id, String name, String status, String index, String langid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(status), 0);
		params.add(in);
		in = new SubProParam(new String(index), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_RATING_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.EDIT_RATING_DETAIL, params, "id,name,status,index,langid", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> deleteRatingDetail(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_RATING_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.DELETE_RATING_DETAIL, params, "id", rs);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", String.valueOf(rs));
		if (rs == 1)
			hm.put("message", "OK");
		else
			hm.put("message", "ERROR");
		return hm;
	}

	@SuppressWarnings("unchecked")
	public List<StaffSmileModel> getRatingDetailStaff(String liststaff, String from, String to, String langid) {
		List<StaffSmileModel> list = new ArrayList<StaffSmileModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_DETAIL_STAFF, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_DETAIL_STAFF, params, "liststaff,from,to,langid", outParam.size() / 6);
		if (outParam.size() > 0) {
			List<SmileModel> listsmile = new ArrayList<>();
			String lidtemp = outParam.get(0);
			StaffSmileModel local = new StaffSmileModel();
			local.setId(outParam.get(0));
			local.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!lidtemp.equals(outParam.get(i))) {
					local.setSmile(listsmile);
					list.add(local);
					listsmile = new ArrayList<>();
					local = new StaffSmileModel();
					local.setId(outParam.get(i));
					local.setName(outParam.get(i + 1));
					lidtemp = outParam.get(i);
				}
				SmileModel smile = new SmileModel();
				smile.setId(outParam.get(i + 2));
				smile.setName(outParam.get(i + 3));
				smile.setNum(outParam.get(i + 4));
				smile.setSum(outParam.get(i + 5));
				listsmile.add(smile);
			}

			local.setSmile(listsmile);
			list.add(local);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StaffSmileModel> getRatingDetailLocation(String listlocation, String from, String to, String langid) {
		List<StaffSmileModel> list = new ArrayList<StaffSmileModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_DETAIL_LOCATION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_DETAIL_LOCATION, params, "listlocation,from,to,langid",
				outParam.size() / 6);
		if (outParam.size() > 0) {
			List<SmileModel> listsmile = new ArrayList<>();
			String lidtemp = outParam.get(0);
			StaffSmileModel local = new StaffSmileModel();
			local.setId(outParam.get(0));
			local.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!lidtemp.equals(outParam.get(i))) {
					local.setSmile(listsmile);
					list.add(local);
					listsmile = new ArrayList<>();
					local = new StaffSmileModel();
					local.setId(outParam.get(i));
					local.setName(outParam.get(i + 1));
					lidtemp = outParam.get(i);
				}
				SmileModel smile = new SmileModel();
				smile.setId(outParam.get(i + 2));
				smile.setName(outParam.get(i + 3));
				smile.setNum(outParam.get(i + 4));
				smile.setSum(outParam.get(i + 5));
				listsmile.add(smile);
			}

			local.setSmile(listsmile);
			list.add(local);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StaffSmileModel> getRatingDetailReport(String liststaff, String listlocation, String from, String to, String langid) {
		List<StaffSmileModel> list = new ArrayList<StaffSmileModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_RATING_DETAIL_REPORT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING_DETAIL_REPORT, params, "liststaff,listlocation,from,to,langid",
				outParam.size() / 6);
		if (outParam.size() > 0) {
			List<SmileModel> listsmile = new ArrayList<>();
			String lidtemp = outParam.get(0);
			StaffSmileModel local = new StaffSmileModel();
			local.setId(outParam.get(0));
			local.setName(outParam.get(1));
			for (int i = 0; i < outParam.size(); i = i + 6) {
				if (!lidtemp.equals(outParam.get(i))) {
					local.setSmile(listsmile);
					list.add(local);
					listsmile = new ArrayList<>();
					local = new StaffSmileModel();
					local.setId(outParam.get(i));
					local.setName(outParam.get(i + 1));
					lidtemp = outParam.get(i);
				}
				SmileModel smile = new SmileModel();
				smile.setId(outParam.get(i + 2));
				smile.setName(outParam.get(i + 3));
				smile.setNum(outParam.get(i + 4));
				smile.setSum(outParam.get(i + 5));
				listsmile.add(smile);
			}

			local.setSmile(listsmile);
			list.add(local);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DataDetailModel> getDataDetail(String liststaff, String listlocation, String from, String to, String langid) {
		List<DataDetailModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_DATA_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_DATA_DETAIL, params, "liststaff,listlocation,from,to,langid",
				outParam.size() / 8);
		for (int i = 0; i < outParam.size(); i += 8) {
			DataDetailModel data = new DataDetailModel(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
					outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6), outParam.get(i + 7));
			list.add(data);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DataStandardModel> getDataStandard(String liststaff, String listlocation, String from, String to, String langid) {
		List<DataStandardModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(liststaff), 0);
		params.add(in);
		in = new SubProParam(new String(listlocation), 0);
		params.add(in);
		in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_DATA_STANDARD, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_DATA_STANDARD, params, "liststaff,listlocation,from,to,langid",
				outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i += 9) {
			DataStandardModel data = new DataStandardModel(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3),
					outParam.get(i + 4), outParam.get(i + 5), outParam.get(i + 6), outParam.get(i + 7), outParam.get(i + 8));
			list.add(data);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getTrendSmile(String from, String to, String type, String langid) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_TREND_SMILE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_TREND_SMILE, params, "from,to,type,langid", outParam.size());
			int count = Integer.parseInt(outParam.get(0));

			String temp = "";
			HashMap<String, Object> date = new HashMap<String, Object>();
			List<HashMap<String, Object>> listsmile = new ArrayList<HashMap<String, Object>>();
			for (int i = 1; i < outParam.size(); i += (count * 6) + 1) {
				listsmile = new ArrayList<HashMap<String, Object>>();
				date = new HashMap<String, Object>();
				// if ((i - 1) % 11 == 0) {
				temp = outParam.get(i);
				for (int j = i + 1; j < i + 1 + (count * 6); j += 6) {
					HashMap<String, Object> table = new HashMap<String, Object>();
					table.put("id", outParam.get(j));
					table.put("name", outParam.get(j+1));
					table.put("image", outParam.get(j+2));
					table.put("key", outParam.get(j+3));
					table.put("num", outParam.get(j+4));
					table.put("sum", outParam.get(j+5));
					listsmile.add(table);
				}
				date.put("date", temp);
				date.put("rating", listsmile);
				list.add(date);
				// }
			}
		return list;
	}

	// String liststaff, String listlocation, String from, String to, String langid
	public static void main(String[] args) {
		eSmileDao e = new eSmileDao();

		// {"date_from":"01-05-2017","date_to":"10-05-2017","location":["243","242","2","3","1","260"],"smile_id":"6","lang_id":"2"}
		// System.out.println(e.getLogin("admin", "202cb962ac59075b964b07152d234b70"));
		// System.out.println(e.getLocation());
		// System.out.println(e.getBackground());
		// System.out.println(e.getStatistic("01-05-2017", "03-05-2017", "243,242,2,3,1"));
		// System.out.println(e.getRating("01-05-2017", "03-05-2017", "1,2,3", "1", "2"));
		// System.out.println(e.getSmile("01-07-2017 00:01", "26-07-2017 23:59", "389", "2"));
		// System.out.println(e.getAllRating("01-05-2017", "10-05-2017", "243,242,2,1,260,3", "2"));
		// System.out.println(e.getRating6("01-05-2017", "10-05-2017", "243,242,2,1,260,3", "6", "2"));
		// System.out.println(e.addBackground("1493968383872.PNG"));
		// System.out.println(e.addPromotion("test", "1493979837542.html", "1493979837542.PNG"));
		// System.out.println(e.getRatingDivision("01-05-2017", "10-05-2017", "243,242", "2"));
		// System.out.println(e.getListSurvey("2"));
		// System.out.println(e.getRatingChild("1", "1,2,3", from, to, "2"));

		// System.out.println(e.getCompareStaff("1,2", "389", "19-07-2017 06:00", "26-07-2017 12:00", "1"));
		// System.out.println(e.getCompareLocation("389,388", "19-07-2017 06:00", "26-07-2017 12:00", "2"));
		// System.out.println(e.getRatingAll("2,3", "389", "19-07-2017 06:00", "26-07-2017 12:00", "2"));
		// System.out.println(e.getDataStandard("24,26,27,1,2,3,4,5,6,7,22", "421,419,420", "22-09-2017 06:00", "29-09-2017 23:00", "1"));

		System.out.println(e.getTrendSmile("22-11-2017 06:00:00", "25-01-2018 23:00:00", "day", "2"));
	}
}
