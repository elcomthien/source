package com.elcom.ehotel.esmile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.elcom.ehotel.esmile.dao.DiningDao;
import com.elcom.ehotel.esmile.dao.ExchangeDao;
import com.elcom.ehotel.esmile.dao.FacultyDao;
import com.elcom.ehotel.esmile.dao.InfoDao;
import com.elcom.ehotel.esmile.dao.SpeedBoatDao;
import com.elcom.ehotel.esmile.dao.SystemDao;
import com.elcom.ehotel.esmile.dao.eSmileDao;
import com.elcom.ehotel.esmile.model.AllRatingModel;
import com.elcom.ehotel.esmile.model.ConfigModel;
import com.elcom.ehotel.esmile.model.ContentInfoModel;
import com.elcom.ehotel.esmile.model.FacultyModel;
import com.elcom.ehotel.esmile.model.LocationModel;
import com.elcom.ehotel.esmile.model.PMSDiningItemModel;
import com.elcom.ehotel.esmile.model.PMSDiningSubjectModel;
import com.elcom.ehotel.esmile.model.PromotionModel;
import com.elcom.ehotel.esmile.model.RatingDivisionModel;
import com.elcom.ehotel.esmile.model.RatingModel;
import com.elcom.ehotel.esmile.model.RatingSurveyModel;
import com.elcom.ehotel.esmile.model.SmileModel;
import com.elcom.ehotel.esmile.model.SpeedBoatModel;
import com.elcom.ehotel.esmile.model.StatisticModel;
import com.elcom.ehotel.esmile.model.SubjectInfoModel;
import com.elcom.ehotel.esmile.model.SurveyModel;
import com.elcom.ehotel.esmile.util.Encryptor;
import com.elcom.ehotel.esmile.util.Util;
import com.google.gson.Gson;

@WebService(endpointInterface = "com.elcom.ehotel.esmile.service.eSmileService")
public class eSmileServiceImpl implements eSmileService {
	private eSmileDao eSmileDao = new eSmileDao();
	private InfoDao infoDao = new InfoDao();
	private DiningDao diningDao = new DiningDao();
	private SpeedBoatDao speedBoatDao = new SpeedBoatDao();
	private ExchangeDao exchangeDao = new ExchangeDao();
	private SystemDao systemDao = new SystemDao();
	private FacultyDao facultyDao = new FacultyDao();

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
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		List<LocationModel> list = new ArrayList<LocationModel>();
		list = eSmileDao.getLocation(id);
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
		String value = map.get("url");
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
		String value = map.get("url");
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
		map = Util.convertObject(object, "");
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String smileid = map.get("smile_id");
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
		String url = map.get("url_content");
		String image = map.get("url_image");
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
		String id = map.get("id");
		List<SubjectInfoModel> list = new ArrayList<>();
		list = infoDao.getSubjectInfo(langid, id);
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
		String id = map.get("id");
		map = infoDao.addSubjectInfo(name, image, id);
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
		String langid = map.get("langid");
		String type = map.get("type");
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
		String id = map.get("id");
		String langid = map.get("langid");
		List<HashMap<String, String>> list = new ArrayList<>();
		list = infoDao.getService(id, langid);
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
		String id = map.get("id");
		String name = map.get("name");
		String image = map.get("image");
		String type = map.get("type");
		map = infoDao.addService(id, name, image, type);
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
		String langid = map.get("langid");
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
	public String getExchange(String object) {
		System.out.println("------>getExchange");
		System.out.println("object: " + object);
		List<HashMap<String, String>> list = new ArrayList<>();
		list = exchangeDao.getExchange();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addExchange(String object) {
		System.out.println("------>addExchange");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String name = map.get("name");
		String def = map.get("desc");
		String image = map.get("image");
		String buy = map.get("buy");
		String transfer = map.get("transfer");
		String sale = map.get("sale");
		String status = map.get("status");
		map = exchangeDao.addExchange(name, def, image, buy, transfer, sale, status);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editExchange(String object) {
		System.out.println("------>editExchange");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("name");
		String def = map.get("desc");
		String image = map.get("image");
		String buy = map.get("buy");
		String transfer = map.get("transfer");
		String sale = map.get("sale");
		String status = map.get("status");
		map = exchangeDao.editExchange(id, name, def, image, buy, transfer, sale, status);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteExchange(String object) {
		System.out.println("------>deleteExchange");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = exchangeDao.deleteExchange(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getLogo(String object) {
		System.out.println("------>getLogo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<>();
		map = systemDao.getLogo();
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String changeLogo(String object) {
		System.out.println("------>changeLogo");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String image = map.get("image");
		map = systemDao.changeLogo(image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getTextWelcome(String object) {
		System.out.println("------>getTextWelcome");
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
		map = systemDao.editTextWelcome(id, name, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getServiceType(String object) {
		System.out.println("------>getServiceType");
		System.out.println("object: " + object);
		List<HashMap<String, String>> list = new ArrayList<>();
		list = infoDao.getServiceType();
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getFaculty(String object) {
		System.out.println("------>getFaculty");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String langid = map.get("langid");
		List<FacultyModel> list = new ArrayList<>();
		list = facultyDao.getFaculty(langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String addFaculty(String object) {
		System.out.println("------>addFaculty");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String name = map.get("name");
		// name = UnicodeConverter.encodeUnicode(name);
		String image = map.get("image");
		map = facultyDao.addFaculty(name, image);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editFaculty(String object) {
		System.out.println("------>editFaculty");
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
		String langid = map.get("langid");
		map = facultyDao.editFaculty(id, name, image, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteFaculty(String object) {
		System.out.println("------>deleteFaculty");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = facultyDao.deleteFaculty(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String getSmileFaculty(String object) {
		System.out.println("------>getSmileFaculty");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String langid = map.get("langid");
		List<SmileModel> list = new ArrayList<>();
		list = eSmileDao.getSmileFaculty(id, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getRatingType(String object) {
		System.out.println("------>getRatingType");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String langid = map.get("langid");
		List<RatingModel> list = new ArrayList<>();
		list = eSmileDao.getRatingType(id, langid);
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
		map = Util.convertObject(object, "");
		List<SmileModel> list = new ArrayList<SmileModel>();
		String from = map.get("date_from");
		String to = map.get("date_to");
		String listlocation = map.get("location");
		String langid = map.get("lang_id");
		list = eSmileDao.getRatingAll(from, to, listlocation, langid);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String getInfoSurvey(String object) {
		System.out.println("------>getInfoSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		Map<String, Object> list = new HashMap<>();
		String langid = map.get("lang_id");
		String status = map.get("status");
		list = eSmileDao.getInfoSurvey(langid, status);
		System.out.println("result: " + list);
		return new Gson().toJson(list);
	}

	@Override
	public String postSurvey(String object) {
		System.out.println("------>postSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "content,smile");
		String listcontent = map.get("content");
		String listsmile = map.get("smile");
		map = eSmileDao.postSurveyService(listcontent, listsmile);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editSmileSurvey(String object) {
		System.out.println("------>editSmileSurvey");
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
		String langid = map.get("langid");
		map = eSmileDao.editSmileSurvey(id, name, image, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}
	
	@Override
	public String addRatingSurvey(String object) {
		System.out.println("------>addRatingSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String name = map.get("content");
		map = eSmileDao.addRatingSurvey(name);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String editRatingSurvey(String object) {
		System.out.println("------>editRatingSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		String name = map.get("content");
		String index = map.get("index");
		String status = map.get("status");
		String langid = map.get("langid");
		map = eSmileDao.editRatingSurvey(id, name, index, status, langid);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}

	@Override
	public String deleteRatingSurvey(String object) {
		System.out.println("------>deleteRatingSurvey");
		System.out.println("object: " + object);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Util.checkObject(object)) {
			map = Util.noneParam();
			return new Gson().toJson(map);
		}
		map = Util.convertObject(object, "");
		String id = map.get("id");
		map = eSmileDao.deleteRatingSurvey(id);
		System.out.println("result: " + map);
		return new Gson().toJson(map);
	}
}
