package com.elcom.ehotel.esmile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.esmile.model.AllRatingModel;
import com.elcom.ehotel.esmile.model.ConfigModel;
import com.elcom.ehotel.esmile.model.EmailModel;
import com.elcom.ehotel.esmile.model.LocationModel;
import com.elcom.ehotel.esmile.model.PromotionModel;
import com.elcom.ehotel.esmile.model.RatingDivisionModel;
import com.elcom.ehotel.esmile.model.RatingModel;
import com.elcom.ehotel.esmile.model.RatingSurveyModel;
import com.elcom.ehotel.esmile.model.SmileModel;
import com.elcom.ehotel.esmile.model.StatisticModel;
import com.elcom.ehotel.esmile.model.SurveyModel;
import com.elcom.ehotel.esmile.util.ConvertUtil;
import com.elcom.ehotel.esmile.util.LogUtil;
import com.elcom.ehotel.esmile.util.SQL;

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
		HashMap<String, String> hm = new HashMap<>();
		hm.put("status", outParam.get(0));
		hm.put("message", outParam.get(1));

		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_LOGIN, params, "uaername,password", outParam.size());
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
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_RATING, params, "from,to,listlocation,smileid,langid", outParam.size() / 3);
		for (int i = 0; i < outParam.size(); i = i + 3) {
			RatingModel rate = new RatingModel();
			rate.setId(outParam.get(i));
			rate.setName(outParam.get(i + 1));
			rate.setNum(outParam.get(i + 2));
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
			smile.setName(outParam.get(i + 1));
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
	public List<EmailModel> getListEmail() {
		List<EmailModel> list = new ArrayList<>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_EMAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(eSmileDao.class.toString(), SQL.GET_EMAIL, params, "none", outParam.size());
		for (int i = 0; i < outParam.size(); i = i + 4) {
			EmailModel email = new EmailModel(outParam.get(i), outParam.get(i + 1), outParam.get(i + 2), outParam.get(i + 3));
			list.add(email);
		}
		return list;
	}

	public static void main(String[] args) {
		eSmileDao e = new eSmileDao();

		// {"date_from":"01-05-2017","date_to":"10-05-2017","location":["243","242","2","3","1","260"],"smile_id":"6","lang_id":"2"}
		// System.out.println(e.getLogin("admin", "202cb962ac59075b964b07152d234b70"));
		// System.out.println(e.getLocation());
		// System.out.println(e.getBackground());
		// System.out.println(e.getStatistic("01-05-2017", "03-05-2017", "243,242,2,3,1"));
		// System.out.println(e.getRating("01-05-2017", "03-05-2017", "1,2,3", "1", "2"));
		// System.out.println(e.getSmile("01-05-2017", "03-05-2017", "1,2,3", "2"));
		// System.out.println(e.getAllRating("01-05-2017", "10-05-2017", "243,242,2,1,260,3", "2"));
		// System.out.println(e.getRating6("01-05-2017", "10-05-2017", "243,242,2,1,260,3", "6", "2"));
		// System.out.println(e.addBackground("1493968383872.PNG"));
		// System.out.println(e.addPromotion("test", "1493979837542.html", "1493979837542.PNG"));
		// System.out.println(e.getRatingDivision("01-05-2017", "10-05-2017", "243,242", "2"));
		// System.out.println(e.getListSurvey("2"));
		System.out.println(e.getListSurvey("1"));
	}
}
