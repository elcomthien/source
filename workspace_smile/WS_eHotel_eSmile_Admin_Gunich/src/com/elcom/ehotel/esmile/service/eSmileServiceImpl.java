package com.elcom.ehotel.esmile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jws.WebService;

import com.elcom.ehotel.esmile.dao.DiningDao;
import com.elcom.ehotel.esmile.dao.InfoDao;
import com.elcom.ehotel.esmile.dao.SpeedBoatDao;
import com.elcom.ehotel.esmile.dao.StaffDao;
import com.elcom.ehotel.esmile.dao.SystemDao;
import com.elcom.ehotel.esmile.dao.UserDao;
import com.elcom.ehotel.esmile.dao.eSmileDao;
import com.elcom.ehotel.esmile.model.AllRatingModel;
import com.elcom.ehotel.esmile.model.ConfigModel;
import com.elcom.ehotel.esmile.model.ContentInfoModel;
import com.elcom.ehotel.esmile.model.DataDetailModel;
import com.elcom.ehotel.esmile.model.DataStandardModel;
import com.elcom.ehotel.esmile.model.LocationModel;
import com.elcom.ehotel.esmile.model.RatingDetailModel;
import com.elcom.ehotel.esmile.model.StaffRatingModel;
import com.elcom.ehotel.esmile.model.StaffSmileModel;
import com.elcom.ehotel.esmile.model.PMSDiningItemModel;
import com.elcom.ehotel.esmile.model.PMSDiningSubjectModel;
import com.elcom.ehotel.esmile.model.PromotionModel;
import com.elcom.ehotel.esmile.model.RatingDivisionModel;
import com.elcom.ehotel.esmile.model.RatingModel;
import com.elcom.ehotel.esmile.model.RatingSurveyModel;
import com.elcom.ehotel.esmile.model.SmileModel;
import com.elcom.ehotel.esmile.model.SmileRatingModel;
import com.elcom.ehotel.esmile.model.SpeedBoatModel;
import com.elcom.ehotel.esmile.model.StaffModel;
import com.elcom.ehotel.esmile.model.StaffVoteModel;
import com.elcom.ehotel.esmile.model.StatisticModel;
import com.elcom.ehotel.esmile.model.SubjectInfoModel;
import com.elcom.ehotel.esmile.model.SurveyModel;
import com.elcom.ehotel.esmile.model.TabletModel;
import com.elcom.ehotel.esmile.model.UserModel;
import com.elcom.ehotel.esmile.util.Encryptor;
import com.elcom.ehotel.esmile.util.Util;
import com.google.gson.Gson;

@WebService(endpointInterface = "com.elcom.ehotel.esmile.service.eSmileService")
public class eSmileServiceImpl implements eSmileService {
	private eSmileDao eSmileDao = new eSmileDao();
	private InfoDao infoDao = new InfoDao();
	private DiningDao diningDao = new DiningDao();
	private SpeedBoatDao speedBoatDao = new SpeedBoatDao();
	private StaffDao staffDao = new StaffDao();
	private UserDao userDao = new UserDao();
	private SystemDao systemDao = new SystemDao();

	@Override
	public String getLogin(String object) {
		System.out.println("------>getLogin");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String username = map.get("username");
		String password = map.get("password");
		password = Encryptor.encryptPassword(password);
		map = eSmileDao.getLogin(username, password);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getLocation(String object) {
		System.out.println("------>getLocation");
		System.out.println("object: " + object);
		List<LocationModel> list = new ArrayList<LocationModel>();
		list = eSmileDao.getLocation();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getBackground(String object) {
		System.out.println("------>getBackground");
		System.out.println("object: " + object);
		List<ConfigModel> list = new ArrayList<ConfigModel>();
		list = eSmileDao.getBackground();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String deleteBackground(String object) {
		System.out.println("------>deleteBackground");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = eSmileDao.deleteBackground(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editBackground(String object) {
		System.out.println("------>editBackground");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String value = map.get("image");
		map = eSmileDao.editBackground(id, value);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String addBackground(String object) {
		System.out.println("------>addBackground");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String value = map.get("image");
		map = eSmileDao.addBackground(value);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getStatistic(String object) {
		System.out.println("------>getStatistic");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		List<StatisticModel> list = new ArrayList<StatisticModel>();
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		list = eSmileDao.getStatistic(from, to, listlocation);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRating(String object) {
		System.out.println("------>getRating");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String smileid = map.get("id");
		String langid = map.get("lang_id");
		if (smileid.equals("6")) {
			List<HashMap<String, String>> listcmt = new ArrayList<>();
			listcmt = eSmileDao.getListComment(from, to, listlocation);
			System.out.println("result: " + listcmt);
			return new Gson().toJson(listcmt);
		} else {
			List<RatingModel> list = new ArrayList<RatingModel>();
			list = eSmileDao.getRating(from, to, listlocation, smileid, langid);
			System.out.println("result: " + list);
			return new Gson().toJson(list);
		}
	}

	@Override
	public String getSmile(String object) {
		System.out.println("------>getSmile");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		List<SmileModel> list = new ArrayList<SmileModel>();
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String langid = map.get("lang_id");
		list = eSmileDao.getSmile(from, to, listlocation, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getPromotion(String object) {
		System.out.println("------>getPromotion");
		System.out.println("object: " + object);
		List<PromotionModel> list = new ArrayList<PromotionModel>();
		list = eSmileDao.getPromotion();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addPromotion(String object) {
		System.out.println("------>addPromotion");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();

		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String url = map.get("url");
		String image = map.get("image");
		String name = map.get("name");
		map = eSmileDao.addPromotion(name, url, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editPromotion(String object) {
		System.out.println("------>editPromotion");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String url = map.get("url_content");
		String image = map.get("url_image");
		map = eSmileDao.editPromotion(id, name, url, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deletePromotion(String object) {
		System.out.println("------>deletePromotion");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = eSmileDao.deletePromotion(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getAllRating(String object) {
		System.out.println("------>getAllRating");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		List<AllRatingModel> list = new ArrayList<AllRatingModel>();
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String langid = map.get("lang_id");
		list = eSmileDao.getAllRating(from, to, listlocation, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingDivision(String object) {
		System.out.println("------>getRatingDivision");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String langid = map.get("lang_id");
		RatingDivisionModel rating = new RatingDivisionModel();
		rating = eSmileDao.getRatingDivision(from, to, listlocation, langid);
		System.out.println("result: " + rating);
		return new Gson().toJson(rating);
	}

	@Override
	public String getLanguage(String object) {
		System.out.println("------>getLanguage");
		System.out.println("object: " + object);
		List<HashMap<String, String>> list = new ArrayList<>();
		list = infoDao.getLanguage();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getSubjectInfo(String object) {
		System.out.println("------>getSubjectInfo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String langid = map.get("langid");
		List<SubjectInfoModel> list = new ArrayList<>();
		list = infoDao.getSubjectInfo(langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addSubjectInfo(String object) {
		System.out.println("------>addSubjectInfo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String name = map.get("name");
		String image = map.get("image");
		map = infoDao.addSubjectInfo(name, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editSubjectInfo(String object) {
		System.out.println("------>editSubjectInfo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String image = map.get("image");
		String invisible = map.get("invisible");
		String index = map.get("index");
		String langid = map.get("langid");
		map = infoDao.editSubjectInfo(id, name, image, langid, invisible, index);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteSubjectInfo(String object) {
		System.out.println("------>deleteSubjectInfo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = infoDao.deleteSubjectInfo(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getContentInfo(String object) {
		System.out.println("------>getContentInfo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String subid = map.get("id");
		String langid = map.get("langid");
		List<ContentInfoModel> list = new ArrayList<>();
		list = infoDao.getContentInfo(subid, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addContentInfo(String object) {
		System.out.println("------>addContentInfo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String def = map.get("url");
		map = infoDao.addContentInfo(id, name, def);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editContentInfo(String object) {
		System.out.println("------>editContentInfo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String def = map.get("url");
		String invisible = map.get("invisible");
		String langid = map.get("langid");
		map = infoDao.editContentInfo(id, name, def, invisible, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteContentInfo(String object) {
		System.out.println("------>deleteContentInfo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = infoDao.deleteContentInfo(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getSubjectDining(String object) {
		System.out.println("------>getSubjectDining");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String parentid = map.get("id");
		String langid = map.get("langid");
		List<PMSDiningSubjectModel> list = new ArrayList<>();
		list = diningDao.getSubjectDining(parentid, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addSubjectDining(String object) {
		System.out.println("------>addSubjectDining");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String parentid = map.get("id");
		String name = map.get("name");
		String image = map.get("image");
		String invisible = map.get("invisible");
		String index = map.get("index");
		PMSDiningSubjectModel sub = new PMSDiningSubjectModel();
		sub.setParent(parentid);
		sub.setName(name);
		sub.setImage(image);
		sub.setActive(invisible);
		sub.setIndex(index);
		map = diningDao.addSubjectDining(sub);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editSubjectDining(String object) {
		System.out.println("------>editSubjectDining");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String image = map.get("image");
		String invisible = map.get("invisible");
		String index = map.get("index");
		String langid = map.get("langid");
		PMSDiningSubjectModel sub = new PMSDiningSubjectModel();
		sub.setId(id);
		sub.setName(name);
		sub.setImage(image);
		sub.setActive(invisible);
		sub.setIndex(index);
		sub.setLangId(langid);
		map = diningDao.editSubjectDining(sub);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteSubjectDining(String object) {
		System.out.println("------>deleteSubjectDining");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = diningDao.deleteSubjectDining(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getItemDining(String object) {
		System.out.println("------>getItemDining");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String langid = map.get("langid");
		List<PMSDiningItemModel> list = new ArrayList<>();
		list = diningDao.getItemDining(id, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addItemDining(String object) {
		System.out.println("------>addItemDining");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		// object: {"image":"food-1497255202453.jpg","detail":"test add food detail","price":"50000","unit":"VND"}
		String subjectid = map.get("id");
		String name = map.get("name");
		String def = map.get("detail");
		String price = map.get("price");
		String image = map.get("image");
		String unit = map.get("unit");
		String invisible = "0";
		String index = "0";
		PMSDiningItemModel item = new PMSDiningItemModel();
		item.setSubjectId(subjectid);
		item.setName(name);
		item.setDef(def);
		item.setPrice(price);
		item.setImage(image);
		item.setIunit(unit);
		item.setActive(invisible);
		item.setIndex(index);
		map = diningDao.addItemDining(item);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editItemDining(String object) {
		System.out.println("------>editItemDining");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String def = map.get("detail");
		String price = map.get("price");
		String image = map.get("image");
		String unit = map.get("unit");
		String invisible = map.get("invisible");
		String index = map.get("index");
		String langid = map.get("langid");
		PMSDiningItemModel item = new PMSDiningItemModel();
		item.setId(id);
		item.setName(name);
		item.setDef(def);
		item.setPrice(price);
		item.setImage(image);
		item.setIunit(unit);
		item.setActive(invisible);
		item.setIndex(index);
		item.setLangid(langid);
		map = diningDao.editItemDining(item);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteItemDining(String object) {
		System.out.println("------>deleteItemDining");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = diningDao.deleteItemDining(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getSpeedBoatSchedule(String object) {
		System.out.println("------>getSpeedBoatSchedule");
		System.out.println("object: " + object);
		List<SpeedBoatModel> list = new ArrayList<>();
		list = speedBoatDao.getSpeedBoatSchedule();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addSpeedBoat(String object) {
		System.out.println("------>addSpeedBoat");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String name = map.get("name");
		String times = map.get("times");
		map = speedBoatDao.addSpeedBoat(name, times);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editSpeedBoat(String object) {
		System.out.println("------>editSpeedBoat");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String invisible = map.get("invisible");
		String index = "0";
		map = speedBoatDao.editSpeedBoat(id, name, invisible, index);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteSpeedBoat(String object) {
		System.out.println("------>deleteSpeedBoat");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = speedBoatDao.deleteSpeedBoat(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String addBoatTime(String object) {
		System.out.println("------>addBoatTime");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String time = map.get("time");
		map = speedBoatDao.addBoatTime(id, time);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editBoatTime(String object) {
		System.out.println("------>editBoatTime");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String time = map.get("time");
		String invisible = "0";
		String index = "0";
		map = speedBoatDao.editBoatTime(id, time, invisible, index);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteBoatTime(String object) {
		System.out.println("------>deleteBoatTime");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = speedBoatDao.deleteBoatTime(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getSurvey(String object) {
		System.out.println("------>getSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String langid = map.get("langid");
		SurveyModel sur = new SurveyModel();
		sur = eSmileDao.getListSurvey(langid);
		System.out.println("result: " + sur);
		return new Gson().toJson(sur);
	}

	@Override
	public String addSurvey(String object) {
		System.out.println("------>addSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String name = map.get("name");
		String image = map.get("image");
		map = eSmileDao.addSurvey(name, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editSurvey(String object) {
		System.out.println("------>editSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String image = map.get("image");
		String status = map.get("status");
		String langid = map.get("langid");
		map = eSmileDao.editSurvey(id, name, image, status, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteSurvey(String object) {
		System.out.println("------>deleteSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = eSmileDao.deleteSurvey(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getRoom(String object) {
		System.out.println("------>getRoom");
		System.out.println("object: " + object);
		List<HashMap<String, String>> list = new ArrayList<>();
		list = eSmileDao.getListRoom();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String editSmile(String object) {
		System.out.println("------>editSmile");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String image = map.get("image");
		String langid = map.get("lang_id");
		String type = map.get("type");
		;
		map = eSmileDao.editSmile(id, name, image, langid, type);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getService(String object) {
		System.out.println("------>getService");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String langid = map.get("langid");
		List<HashMap<String, String>> list = new ArrayList<>();
		list = infoDao.getService(langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addService(String object) {
		System.out.println("------>addService");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String name = map.get("name");
		String image = map.get("image");
		map = infoDao.addService(name, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editService(String object) {
		System.out.println("------>editService");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String image = map.get("image");
		String status = map.get("status");
		String index = map.get("index");
		String langid = map.get("langid");
		map = infoDao.editService(id, name, image, status, index, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteService(String object) {
		System.out.println("------>deleteService");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = infoDao.deleteService(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editRating(String object) {
		System.out.println("------>editRating");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String image = map.get("image");
		String langid = map.get("lang_id");
		String name = map.get("name");
		String type = "1";
		map = eSmileDao.editRating(id, name, image, langid, type);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getVoteSurvey(String object) {
		System.out.println("------>getVoteSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		List<RatingSurveyModel> list = new ArrayList<>();
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String langid = map.get("lang_id");
		list = eSmileDao.getVoteSurvey(from, to, listlocation, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getListStaff(String object) {
		System.out.println("------>getListStaff");
		System.out.println("object: " + object);
		List<StaffModel> list = new ArrayList<>();
		list = staffDao.getListStaff();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addStaff(String object) {
		System.out.println("------>addStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String username = map.get("username");
		String password = map.get("password");
		String name = map.get("name");
		String image = map.get("image");
		password = Encryptor.encryptPassword(password);
		map = staffDao.addStaff(username, password, name, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editStaff(String object) {
		System.out.println("------>editStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String password = map.get("password");
		String name = map.get("name");
		String image = map.get("image");
		if (!password.equals("-1"))
			password = Encryptor.encryptPassword(password);
		map = staffDao.editStaff(id, password, name, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteStaff(String object) {
		System.out.println("------>deleteStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = staffDao.deleteStaff(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getListTablet(String object) {
		System.out.println("------>getListTablet");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String userid = map.get("id");
		List<TabletModel> list = new ArrayList<>();
		list = eSmileDao.getListTablet(userid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String editTablet(String object) {
		System.out.println("------>editTablet");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String image = map.get("image");
		map = eSmileDao.editTablet(id, name, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getCompareStaff(String object) {
		System.out.println("------>getCompareStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "user_id,location");
		List<StaffVoteModel> list = new ArrayList<>();
		String id = map.get("user_id");
		String listlocation = map.get("location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("lang_id");
		list = eSmileDao.getCompareStaff(id, listlocation, from, to, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingChild(String object) {
		System.out.println("------>getRatingChild");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location");
		List<RatingModel> list = new ArrayList<>();
		String id = map.get("id");
		String listlocation = map.get("location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("lang_id");
		String smile_id = map.get("smile_id");
		list = eSmileDao.getRatingChild(id, listlocation, from, to, langid, smile_id);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingAll(String object) {
		System.out.println("------>getRatingAll");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "id,location");
		List<SmileRatingModel> list = new ArrayList<>();
		String id = map.get("id");
		String listlocation = map.get("location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("lang_id");
		list = eSmileDao.getRatingAll(id, listlocation, from, to, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getCompareLocation(String object) {
		System.out.println("------>getCompareLocation");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "id");
		List<StaffSmileModel> list = new ArrayList<>();
		String listlocation = map.get("id");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("lang_id");
		list = eSmileDao.getCompareLocation(listlocation, from, to, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getStatisticSmile(String object) {
		System.out.println("------>getStatisticSmile");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		List<HashMap<String, String>> list = new ArrayList<>();
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String langid = map.get("lang_id");
		list = eSmileDao.getStatisticSmile(from, to, listlocation, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getStatisticRating(String object) {
		System.out.println("------>getStatisticRating");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		List<HashMap<String, String>> list = new ArrayList<>();
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String langid = map.get("lang_id");
		list = eSmileDao.getStatisticRating(from, to, listlocation, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getListUser(String object) {
		System.out.println("------>getListUser");
		System.out.println("object: " + object);
		List<UserModel> list = new ArrayList<>();
		list = userDao.getListUser();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addUser(String object) {
		System.out.println("------>addUser");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "store_id");
		String username = map.get("username");
		String password = map.get("password");
		String name = map.get("name");
		String image = map.get("image");
		String roleid = map.get("role_id");
		String liststore = map.get("store_id");
		password = Encryptor.encryptPassword(password);
		map = userDao.adduser(username, password, name, image, roleid, liststore);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editUser(String object) {
		System.out.println("------>editUser");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "store_id");
		String userid = map.get("id");
		String username = map.get("username");
		String password = map.get("password");
		String name = map.get("name");
		String image = map.get("image");
		String roleid = map.get("role_id");
		String liststore = map.get("store_id");
		if (!password.equals("-1"))
			password = Encryptor.encryptPassword(password);
		map = userDao.editUser(userid, username, password, name, image, roleid, liststore);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteUser(String object) {
		System.out.println("------>deleteUser");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String userid = map.get("id");
		map = userDao.deleteUser(userid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String changePassword(String object) {
		System.out.println("------>changePassword");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String username = map.get("username");
		String currentpass = map.get("current_pass");
		String newpassword = map.get("password");
		String name = map.get("name");
		String image = map.get("image");
		if (!currentpass.equals("-1"))
			currentpass = Encryptor.encryptPassword(currentpass);
		if (!newpassword.equals("-1"))
			newpassword = Encryptor.encryptPassword(newpassword);
		map = userDao.changePassword(username, currentpass, newpassword, name, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getListStore(String object) {
		System.out.println("------>getListStore");
		System.out.println("object: " + object);
		List<HashMap<String, String>> list = new ArrayList<>();
		list = userDao.getListStore();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getSmileStaff(String object) {
		System.out.println("------>getSmileStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "employee");
		List<SmileModel> list = new ArrayList<SmileModel>();
		String from = map.get("date_from");
		String to = map.get("date_to");
		String liststaff = map.get("employee");
		String langid = map.get("lang_id");
		list = eSmileDao.getSmileStaff(from, to, liststaff, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingStaff(String object) {
		System.out.println("------>getRatingStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "employee");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String liststaff = map.get("employee");
		String smileid = map.get("id");
		String langid = map.get("lang_id");
		List<RatingModel> list = new ArrayList<RatingModel>();
		list = eSmileDao.getRatingStaff(from, to, liststaff, smileid, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingChildStaff(String object) {
		System.out.println("------>getRatingChildStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "employee");
		List<RatingModel> list = new ArrayList<>();
		String id = map.get("id");
		String liststaff = map.get("employee");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("lang_id");
		String smileid = map.get("smile_id");
		list = eSmileDao.getRatingChildStaff(id, liststaff, from, to, langid, smileid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingSmileStaff(String object) {
		System.out.println("------>getRatingSmileStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "employee");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String liststaff = map.get("employee");
		String smileid = map.get("id");
		String langid = map.get("lang_id");
		List<StaffRatingModel> list = new ArrayList<StaffRatingModel>();
		list = eSmileDao.getRatingSmileStaff(from, to, liststaff, smileid, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingChildSmileStaff(String object) {
		System.out.println("------>getRatingChildSmileStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "employee");
		List<StaffRatingModel> list = new ArrayList<>();
		String id = map.get("id");
		String liststaff = map.get("employee");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("lang_id");
		String smileid = map.get("smile_id");
		list = eSmileDao.getRatingChildSmileStaff(id, liststaff, from, to, langid, smileid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingSmileLocation(String object) {
		System.out.println("------>getRatingSmileLocation");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String smileid = map.get("id");
		String langid = map.get("lang_id");
		List<StaffRatingModel> list = new ArrayList<StaffRatingModel>();
		list = eSmileDao.getRatingSmileLocation(from, to, listlocation, smileid, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingChildSmileLocation(String object) {
		System.out.println("------>getRatingChildSmileLocation");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location");
		List<StaffRatingModel> list = new ArrayList<>();
		String id = map.get("id");
		String listlocation = map.get("location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("lang_id");
		String smileid = map.get("smile_id");
		list = eSmileDao.getRatingChildSmileLocation(id, listlocation, from, to, langid, smileid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getHistory(String object) {
		System.out.println("------>getHistory");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location,employee");
		List<HashMap<String, String>> list = new ArrayList<>();
		String listlocation = map.get("location");
		String liststaff = map.get("employee");
		String from = map.get("date_from");
		String to = map.get("date_to");
		list = staffDao.getHistory(liststaff, listlocation, from, to);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingDetail(String object) {
		System.out.println("------>getRatingDetail");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		List<RatingDetailModel> list = new ArrayList<>();
		String langid = map.get("langid");
		list = eSmileDao.getRatingDetail(langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addRatingDetail(String object) {
		System.out.println("------>addRatingDetail");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String name = map.get("rating");
		map = eSmileDao.addRatingDetail(name);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editRatingDetail(String object) {
		System.out.println("------>editRatingDetail");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("rating");
		String status = map.get("status");
		String index = map.get("index");
		String langid = map.get("langid");
		map = eSmileDao.editRatingDetail(id, name, status, index, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteRatingDetail(String object) {
		System.out.println("------>deleteRatingDetail");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = eSmileDao.deleteRatingDetail(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getRatingDetailStaff(String object) {
		System.out.println("------>getRatingDetailStaff");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "employee");
		List<StaffSmileModel> list = new ArrayList<>();
		String liststaff = map.get("employee");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("langid");
		list = eSmileDao.getRatingDetailStaff(liststaff, from, to, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingDetailLocation(String object) {
		System.out.println("------>getRatingDetailLocation");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location");
		List<StaffSmileModel> list = new ArrayList<>();
		String location = map.get("location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("langid");
		list = eSmileDao.getRatingDetailLocation(location, from, to, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingDetailReport(String object) {
		System.out.println("------>getRatingDetailReport");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location,employee");
		List<StaffSmileModel> list = new ArrayList<>();
		String employee = map.get("employee");
		String location = map.get("location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("langid");
		list = eSmileDao.getRatingDetailReport(employee, location, from, to, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getDataDetail(String object) {
		System.out.println("------>getDataDetail");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location,employee");
		List<DataDetailModel> list = new ArrayList<>();
		String employee = map.get("employee");
		String location = map.get("location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("langid");
		list = eSmileDao.getDataDetail(employee, location, from, to, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getDataStandard(String object) {
		System.out.println("------>getDataStandard");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "location,employee");
		List<DataStandardModel> list = new ArrayList<>();
		String employee = map.get("employee");
		String location = map.get("location");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String langid = map.get("langid");
		list = eSmileDao.getDataStandard(employee, location, from, to, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getWelcome(String object) {
		System.out.println("------>getWelcome");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String langid = map.get("langid");
		List<HashMap<String, String>> list = new ArrayList<>();
		list = systemDao.getTextWelcome(id, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String editWelcome(String object) {
		System.out.println("------>editWelcome");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String langid = map.get("langid");
		map = systemDao.editTextWelcome(id, name, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}
	
	@Override
	public String getTextWelcome(String object) {
//		System.out.println("------>getTextWelcome");
//		System.out.println("object: " + object);
//		HashMap<String, String> map = new HashMap<String, String>();
//		if (!Util.checkObject(object)) {
//			map = Util.noneParam();
//			return new Gson().toJson(map);
//		}
//		map = Util.convertObject(object, "");
//		String id = map.get("id");
//		String langid = map.get("langid");
//		List<HashMap<String, String>> list = new ArrayList<>();
//		list = eSmileDao.getTextWelcome(id, langid);
//		System.out.println("result: " + list);
//		return new Gson().toJson(list);
		System.out.println("------>getTextWelcome --> getBackground");
		System.out.println("object: " + object);
		List<ConfigModel> list = new ArrayList<ConfigModel>();
		list = eSmileDao.getBackground();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String editTextWelcome(String object) {
		System.out.println("------>editTextWelcome");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String langid = map.get("langid");
		map = eSmileDao.editTextWelcome(id, name, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

}
