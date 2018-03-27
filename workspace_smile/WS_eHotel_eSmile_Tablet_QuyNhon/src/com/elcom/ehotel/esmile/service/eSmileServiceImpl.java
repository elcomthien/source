package com.elcom.ehotel.esmile.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.elcom.ehotel.esmile.dao.eSmileDao;
import com.elcom.ehotel.esmile.model.InfoModel;
import com.elcom.ehotel.esmile.model.LocationModel;
import com.elcom.ehotel.esmile.model.ObjFood;
import com.elcom.ehotel.esmile.model.ObjInfo;
import com.elcom.ehotel.esmile.model.ObjItem;
import com.elcom.ehotel.esmile.model.ObjItemInfo;
import com.elcom.ehotel.esmile.model.ObjLang;
import com.elcom.ehotel.esmile.model.ObjLocation;
import com.elcom.ehotel.esmile.model.ObjLogin;
import com.elcom.ehotel.esmile.model.ObjNotify;
import com.elcom.ehotel.esmile.model.ObjPostSurvey;
import com.elcom.ehotel.esmile.model.ObjReRegister;
import com.elcom.ehotel.esmile.model.ObjRegister;
import com.elcom.ehotel.esmile.model.ObjResult;
import com.elcom.ehotel.esmile.model.ObjShipSchedule;
import com.elcom.ehotel.esmile.model.ObjStaff;
import com.elcom.ehotel.esmile.model.ObjSubject;
import com.elcom.ehotel.esmile.model.ObjSubjectDining;
import com.elcom.ehotel.esmile.model.ObjSubjectInfo;
import com.elcom.ehotel.esmile.model.ObjSubjectRating;
import com.elcom.ehotel.esmile.model.SurveyModel;
import com.elcom.ehotel.esmile.util.Param;
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
		// TODO Auto-generated method stub
		List<ObjLang> list = new ArrayList<>();
		list = eSmileDao.getLanguage();
		return new Gson().toJson(list);
	}

	@Override
	public String getInfo(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<InfoModel> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String langid = jObject.getString(Param.langid);
			String key = jObject.getString(Param.key);
			String type = jObject.getString(Param.type);

			list = eSmileDao.getInfo(key, langid, type);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getSubjRating(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjSubjectRating> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String langid = jObject.getString(Param.langid);

			list = eSmileDao.getSubjRating(langid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String register(String object) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String ip = jObject.getString(Param.ip);
			String name = jObject.getString(Param.name);
			if (key != null)
				return new Gson().toJson(eSmileDao.register(key, ip, name).get(0));
			else {
				ObjResult rs = new ObjResult("0", Param.fail);
				return new Gson().toJson(rs);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String setRating(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.id);
			JSONArray arrayRatingid = jObject.getJSONArray(Param.rating);
			return new Gson().toJson(eSmileDao.setRating(key, smileid, arrayRatingid).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String setComment(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.id);
			String textcomment = jObject.getString(Param.text_comment);
			return new Gson().toJson(eSmileDao.setComment(key, smileid, textcomment).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String login(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String ip = jObject.getString(Param.ip);
			return new Gson().toJson(eSmileDao.login(key, ip).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String getPmsSubject(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjSubject> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			list = eSmileDao.getPmsSubject(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getPmsSubjectDetail(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjItem> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String subject_id = jObject.getString(Param.id);
			list = eSmileDao.getPmsSubjectDetail(key, subject_id);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String setSmile(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.id);
			return new Gson().toJson(eSmileDao.setSmile(key, smileid).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String getSubjectInfoHotel(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjSubjectInfo> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getSubjectInfoHotel(key, langid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getItemBySubjectID(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjItemInfo> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String subject_id = jObject.getString(Param.id);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getItemBySubjectID(key, subject_id, langid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getSurvey(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<SurveyModel> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getSurvey(langid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String postSurvey(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String userid = jObject.getString(Param.user_id);
			List<ObjPostSurvey> listPost = new ArrayList<>();
			JSONArray arrayRatingid = jObject.getJSONArray(Param.rating);
			JSONArray arraySmielid = jObject.getJSONArray(Param.smile);
			for (int i = 0; i < arrayRatingid.length(); i++) {
				String obj = arrayRatingid.getString(i);
				String obj2 = arraySmielid.getString(i);
				ObjPostSurvey objPost = new ObjPostSurvey(obj, obj2);
				listPost.add(objPost);
			}
			return new Gson().toJson(eSmileDao.setRatingSurvey(key,userid, listPost).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String getSubjectDining(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjSubjectDining> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getSubjectDining(key, langid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getItemDiningBySubjectID(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjSubjectDining> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String id_ = jObject.getString(Param.id);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getItemDiningBySubjectID(key, id_, langid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getListFoodByItem(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjFood> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String id_ = jObject.getString(Param.id);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getListFootByItem(key, id_, langid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String getShipSchedule(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		List<ObjShipSchedule> list = new ArrayList<>();
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String langid = jObject.getString(Param.langid);
			list = eSmileDao.getShipSchedule(key, langid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String editSmile(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String smileid = jObject.getString(Param.id);
			String image = jObject.getString(Param.image);
			String lang = jObject.getString(Param.langid);

			return new Gson().toJson(eSmileDao.editSmile(smileid, image, lang).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String editRating(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String id = jObject.getString(Param.id);
			String image = jObject.getString(Param.image);
			return new Gson().toJson(eSmileDao.editRating(id, image).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String checkNotifyRating(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		jObject = eSmileDao.getRatingNotify();
		return jObject.toString();
	}

	@Override
	public String getNotify(String object) {
		// TODO Auto-generated method stub
		List<ObjNotify> list = new ArrayList<>();
		list = eSmileDao.getNotify();

		return new Gson().toJson(list);
	}

	@Override
	public String confirmRating(String object) {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String deleteNotify(String object) {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String setRatingGuest(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.id);
			String folionum = jObject.getString(Param.folionum);
			String nameguest = jObject.getString(Param.nameguest);
			String checkindate = jObject.getString(Param.checkindate);
			String checkountdate = jObject.getString(Param.checkoutdate);
			JSONArray arrayRatingid = jObject.getJSONArray(Param.rating);
			return new Gson().toJson(eSmileDao
					.setRatingGuest(key, smileid, arrayRatingid, folionum, nameguest, checkindate, checkountdate)
					.get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String setCommentGuest(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.id);
			String textcomment = jObject.getString(Param.text_comment);
			String folionum = jObject.getString(Param.folionum);
			String nameguest = jObject.getString(Param.nameguest);
			String checkindate = jObject.getString(Param.checkindate);
			String checkountdate = jObject.getString(Param.checkoutdate);
			return new Gson().toJson(eSmileDao
					.setCommentGuest(key, smileid, textcomment, folionum, nameguest, checkindate, checkountdate)
					.get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String postRegister(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String storeid = jObject.getString(Param.id);
			String ipadd = jObject.getString(Param.ip);
			String name = jObject.getString(Param.name);
			return new Gson().toJson(eSmileDao.postRegister(storeid, ipadd, name));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjRegister rs = new ObjRegister("0", Param.fail, "");
		return new Gson().toJson(rs);
	}

	@Override
	public String getLocations(String object) {
		// TODO Auto-generated method stub
		List<LocationModel> list = new ArrayList<>();
		list = eSmileDao.getLocations();
		return new Gson().toJson(list);
	}

	@Override
	public String getInfos(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		ObjInfo list = null;
		try {
			jObject = new JSONObject(object);
			String langid = jObject.getString(Param.langid);
			String key = jObject.getString(Param.key);
			String type = jObject.getString(Param.type);

			list = eSmileDao.getInfos(key, langid, type);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(list);
	}

	@Override
	public String postLogin(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String username = jObject.getString(Param.username);
			String password = jObject.getString(Param.password);
			String key = jObject.getString(Param.key);
			return new Gson().toJson(eSmileDao.postLogin(username, password, key));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjLogin rs = new ObjLogin("0", Param.fail, "", "", "", "");
		return new Gson().toJson(rs);
	}

	@Override
	public String postLogout(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String staffid = jObject.getString(Param.user_id);
			String key = jObject.getString(Param.key);
			return new Gson().toJson(eSmileDao.postLogout(staffid, key));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String checkLogin(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String staffid = jObject.getString(Param.user_id);
			return new Gson().toJson(eSmileDao.checkLogin(staffid));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String postSmile(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String staffid = jObject.getString(Param.user_id);
			String idSmile = jObject.getString(Param.id);
			String key = jObject.getString(Param.key);
			return new Gson().toJson(eSmileDao.postSmile(staffid, idSmile, key));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String postRating(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String staffid = jObject.getString(Param.user_id);
			String idSmile = jObject.getString(Param.smile_id);
			String key = jObject.getString(Param.key);
			String ratingid = jObject.getString(Param.rating_id);
			return new Gson().toJson(eSmileDao.postRating(key, idSmile, ratingid, staffid));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String postRatingChild(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String key = jObject.getString(Param.key);
			String smileid = jObject.getString(Param.smile_id);
			String ratingid = jObject.getString(Param.rating_id);
			String staffid = jObject.getString(Param.user_id);
			JSONArray arrayRatingid = jObject.getJSONArray(Param.childe_id);
			return new Gson().toJson(eSmileDao.postRatingChild(key, smileid, ratingid, arrayRatingid, staffid));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String getEmployee(String object) {
		// TODO Auto-generated method stub
		try {
			return new Gson().toJson(eSmileDao.getEmployee());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjStaff rs = new ObjStaff("", "", "", "", "", "");
		return new Gson().toJson(rs);
	}

	@Override
	public String postReRegister(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String idstore = jObject.getString(Param.id);
			return new Gson().toJson(eSmileDao.postReRegister(idstore));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjReRegister rs = new ObjReRegister("", "", new ArrayList<ObjLocation>());
		return new Gson().toJson(rs);
	}

	@Override
	public String getMobileNotify(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		jObject = eSmileDao.getMobileNotify();
		return jObject.toString();
	}

	@Override
	public String getMobileNotifyAll(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			String userid = jObject.getString(Param.user_id);
			return new Gson().toJson(eSmileDao.getMobileNotifyAll(userid));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().toJson(new ArrayList<>());
	}

	@Override
	public String confirmMobileNotify(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			JSONArray arr = jObject.getJSONArray(Param.id);
			String id = "";
			for (int i = 0; i < arr.length(); i++) {
				id += arr.get(i) + ",";
			}
			id = id.substring(0, id.length() - 1);
			String userid = jObject.getString(Param.user_id);
			System.out.println(id);
			return new Gson().toJson(eSmileDao.confirmMobileNotify(id, userid).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

	@Override
	public String deleteMobileNotify(String object) {
		// TODO Auto-generated method stub
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			JSONArray arr = jObject.getJSONArray(Param.id);
			String id = "";
			for (int i = 0; i < arr.length(); i++) {
				id += arr.get(i) + ",";
			}
			id = id.substring(0, id.length() - 1);
			String userid = jObject.getString(Param.user_id);
			System.out.println(id);
			return new Gson().toJson(eSmileDao.deleteMobileNotify(id, userid).get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjResult rs = new ObjResult("0", Param.fail);
		return new Gson().toJson(rs);
	}

}
