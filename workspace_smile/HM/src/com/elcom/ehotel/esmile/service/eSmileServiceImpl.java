package com.elcom.ehotel.esmile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.elcom.ehotel.esmile.dao.eSmileDao;
import com.elcom.ehotel.esmile.model.InfoModel;
import com.elcom.ehotel.esmile.model.LocationModel;
import com.elcom.ehotel.esmile.model.ObjExchange;
import com.elcom.ehotel.esmile.model.ObjFaculty;
import com.elcom.ehotel.esmile.model.ObjFood;
import com.elcom.ehotel.esmile.model.ObjItem;
import com.elcom.ehotel.esmile.model.ObjItemInfo;
import com.elcom.ehotel.esmile.model.ObjLang;
import com.elcom.ehotel.esmile.model.ObjMenu;
import com.elcom.ehotel.esmile.model.ObjPostSurvey;
import com.elcom.ehotel.esmile.model.ObjResult;
import com.elcom.ehotel.esmile.model.ObjShipSchedule;
import com.elcom.ehotel.esmile.model.ObjSubject;
import com.elcom.ehotel.esmile.model.ObjSubjectDining;
import com.elcom.ehotel.esmile.model.ObjSubjectInfo;
import com.elcom.ehotel.esmile.model.ObjSubjectRating;
import com.elcom.ehotel.esmile.model.SurveyModel;
import com.elcom.ehotel.esmile.util.Param;
import com.elcom.ehotel.esmile.util.Util;
import com.google.gson.Gson;

@WebService(endpointInterface = "com.elcom.ehotel.esmile.service.eSmileService")
public class eSmileServiceImpl implements eSmileService {
	private eSmileDao eSmileDao = new eSmileDao();

	@Override
	public String getLocation(String object) {
		List<LocationModel> list = new ArrayList<>();
		list = eSmileDao.getLocation();
		return new Gson().toJson(list);
	}

	@Override
	public String getLanguage(String object) {
		List<ObjLang> list = new ArrayList<>();
		list = eSmileDao.getLanguage();
		return new Gson().toJson(list);
	}

	// ---------------------------edit---------------------------------------
	@Override
	public String getInfo(String object) {
		System.out.println("------>getInfo");
		System.out.println("object: " + object);
		JSONObject jObject;
		List<InfoModel> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			list = eSmileDao.getInfo(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getSubjRating(String object) {
		JSONObject jObject;
		List<ObjSubjectRating> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String langid = jObject.getString(Param.langid);

			list = eSmileDao.getSubjRating(langid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String register(String object) {
		System.out.println("------>register");
		System.out.println("object: " + object);
		JSONObject jObject;
		HashMap<String, String> map = new HashMap<>();
		try {
			jObject = new JSONObject(object);
			String location = jObject.getString(Param.id);
			String key = jObject.getString(Param.key);
			String ip = jObject.getString(Param.ip);
			String name = jObject.getString(Param.name);
			map = eSmileDao.registerTablet(location, key, ip, name);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String setRating(String object) {
		System.out.println("------>setRating");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "rating");
		String fid = map.get(Param.id);
		String key = map.get(Param.key);
		String smileid = map.get(Param.smile_id);
		String rating = map.get("rating");
		if (rating.equals(""))
			rating = "-2";
		map = eSmileDao.setRating(fid, key, smileid, rating);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String setComment(String object) {
		System.out.println("------>setComment");
		System.out.println("object: " + object);
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.smile_id);
			String textcomment = jObject.getString(Param.text_comment);
			return new Gson().toJson(eSmileDao.setComment(key, smileid, textcomment).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		System.out.println("result: " + rs);
		return new Gson().toJson(rs);
	}

	@Override
	public String login(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String ip = jObject.getString(Param.ip);
			return new Gson().toJson(eSmileDao.login(key, ip).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String getPmsSubject(String object) {
		JSONObject jObject;
		List<ObjSubject> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			list = eSmileDao.getPmsSubject(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getPmsSubjectDetail(String object) {
		JSONObject jObject;
		List<ObjItem> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String subject_id = jObject.getString(Param.id);
			list = eSmileDao.getPmsSubjectDetail(key, subject_id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String setSmile(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.id);
			return new Gson().toJson(eSmileDao.setSmile(key, smileid).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String getSubjectInfoHotel(String object) {
		System.out.println("------>getSubjectInfoHotel");
		System.out.println("object: " + object);
		JSONObject jObject;
		List<ObjSubjectInfo> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String id = jObject.getString(Param.id);
			list = eSmileDao.getSubjectInfoHotel(key, id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getItemBySubjectID(String object) {
		JSONObject jObject;
		List<ObjItemInfo> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String subject_id = jObject.getString(Param.id);
			list = eSmileDao.getItemBySubjectID(key, subject_id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getSurvey(String object) {
		JSONObject jObject;
		List<SurveyModel> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getSurvey(langid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String postSurvey(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			List<ObjPostSurvey> listPost = new ArrayList<>();
			JSONArray arrayRatingid = jObject.getJSONArray(Param.survey);
			for (int i = 0; i < arrayRatingid.length(); i++) {
				JSONObject obj = arrayRatingid.getJSONObject(i);
				ObjPostSurvey objPost = new ObjPostSurvey(obj.getString("id_survey"), obj.getString("id_smile"));
				listPost.add(objPost);
			}
			return new Gson().toJson(eSmileDao.setRatingSurvey(key, listPost).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String getSubjectDining(String object) {
		JSONObject jObject;
		List<ObjSubjectDining> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getSubjectDining(key, langid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getItemDiningBySubjectID(String object) {
		JSONObject jObject;
		List<ObjSubjectDining> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String id_ = jObject.getString(Param.id);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getItemDiningBySubjectID(key, id_, langid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getListFoodByItem(String object) {
		JSONObject jObject;
		List<ObjFood> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String id_ = jObject.getString(Param.id);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getListFootByItem(key, id_, langid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getShipSchedule(String object) {
		JSONObject jObject;
		List<ObjShipSchedule> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getShipSchedule(key, langid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String editSmile(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String smileid = jObject.getString(Param.id);
			String image = jObject.getString(Param.image);
			String lang = jObject.getString(Param.langid);

			return new Gson().toJson(eSmileDao.editSmile(smileid, image, lang).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String editRating(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String id = jObject.getString(Param.id);
			String image = jObject.getString(Param.image);
			return new Gson().toJson(eSmileDao.editRating(id, image).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	// @Override
	// public String checkNotifyRating(String object) {
	// JSONObject jObject;
	// jObject = eSmileDao.getRatingNotify();
	// return jObject.toString();
	// }

	// @Override
	// public String getNotify(String object) {
	// List<ObjNotify> list = new ArrayList<>();
	// list = eSmileDao.getNotify();
	// return new Gson().toJson(list);
	// }

	@Override
	public String checkNotifyRating(String object) {
		Map<String, Object> map = new HashMap<>();
		map = eSmileDao.getNotifyRealTime();
		return new Gson().toJson(map);
	}

	@Override
	public String getNotify(String object) {
		Map<String, Object> map = new HashMap<>();
		map = eSmileDao.getNotifyAll();
		return new Gson().toJson(map);
	}

	@Override
	public String confirmRating(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			JSONArray arr = jObject.getJSONArray(Param.id);
			String id = "";
			for (int i = 0; i < arr.length(); i++) {
				id += arr.get(i) + ",";
			}
			id = id.substring(0, id.length() - 1);
			System.out.println(id);
			return new Gson().toJson(eSmileDao.confirmNotify(id).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String deleteNotify(String object) {
		System.out.println(object);
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			JSONArray arr = jObject.getJSONArray(Param.id);
			String id = "";
			for (int i = 0; i < arr.length(); i++) {
				id += arr.get(i) + ",";
			}
			id = id.substring(0, id.length() - 1);
			System.out.println(id);
			return new Gson().toJson(eSmileDao.deleteNotify(id).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String setRatingGuest(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String id = jObject.getString(Param.id);
			String smileid = jObject.getString(Param.smile_id);
			JSONArray arrayRatingid = jObject.getJSONArray(Param.rating);
			return new Gson().toJson(eSmileDao.setRatingGuest(id, key, smileid, arrayRatingid).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String setCommentGuest(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String id = jObject.getString(Param.id);
			String smileid = jObject.getString(Param.smile_id);
			String textcomment = jObject.getString(Param.text_comment);
			return new Gson().toJson(eSmileDao.setCommentGuest(id, key, smileid, textcomment).get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String getMenu(String object) {
		JSONObject jObject;
		List<ObjMenu> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String id = jObject.getString(Param.id);
			// String langid = jObject.getString(Param.langid);
			list = eSmileDao.getMenu(key, id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getExchange(String object) {
		JSONObject jObject;
		List<ObjExchange> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			list = eSmileDao.getExchange(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getFaculty(String object) {
		System.out.println("------>getFaculty");
		System.out.println("object: " + object);
		JSONObject jObject;
		List<ObjFaculty> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getFaculty(key, langid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String checkKey(String object) {
		System.out.println("------>checkKey");
		System.out.println("object: " + object);
		JSONObject jObject;
		HashMap<String, String> map = new HashMap<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			map = eSmileDao.checkKey(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getKey(String object) {
		System.out.println("------>getKey");
		System.out.println("object: " + object);
		JSONObject jObject;
		List<HashMap<String, String>> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String fid = jObject.getString(Param.fid);
			list = eSmileDao.getKey(fid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String setLang(String object) {
		System.out.println("------>setLang");
		System.out.println("object: " + object);
		JSONObject jObject;
		HashMap<String, String> map = new HashMap<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String langid = jObject.getString(Param.langid);
			map = eSmileDao.setLang(key, langid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String checkKeyMobile(String object) {
		System.out.println("------>checkKeyMobile");
		System.out.println("object: " + object);
		JSONObject jObject;
		HashMap<String, String> map = new HashMap<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			map = eSmileDao.checkKeyMobile(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String postImage(String object) {
		System.out.println("------>postImage");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "image");
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String fid = jObject.getString(Param.id);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.smile_id);
			String textcomment = jObject.getString(Param.text_comment);
			String image = map.get("image");
			return new Gson().toJson(eSmileDao.postImage(fid, key, smileid, textcomment, image));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		System.out.println("result: " + rs);
		return new Gson().toJson(rs);
	}

	public static void main(String[] args) {
		String obj = "{\"id\":\"1\",\"rating\":[],\"key\":\"19001\",\"smile_id\":\"4\"}";
		eSmileServiceImpl e = new eSmileServiceImpl();
		System.out.println(e.setRating(obj));
	}
}
