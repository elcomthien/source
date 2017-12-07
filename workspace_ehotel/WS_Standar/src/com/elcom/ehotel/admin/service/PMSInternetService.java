package com.elcom.ehotel.admin.service;

import java.util.HashMap;
import java.util.List;

import com.elcom.ehotel.admin.dao.PMSInternetDao;
import com.elcom.ehotel.admin.model.PMSWeatherModel;
import com.elcom.ehotel.admin.model.SystemServiceModel;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSInternetService {
	PMSInternetDao pmsInternetDao = new PMSInternetDao();

	public List<PMSWeatherModel> getListCountryWeather() {
		return pmsInternetDao.getListCountryWeather();
	}

	public int addCountryWeather(PMSWeatherModel wea) {
		wea.setName(UnicodeConverter.encodeUnicode(wea.getName()));
		return pmsInternetDao.addCountryWeather(wea);
	}

	public int editCountryWeather(PMSWeatherModel wea) {
		wea.setName(UnicodeConverter.encodeUnicode(wea.getName()));
		return pmsInternetDao.editCountryWeather(wea);
	}

	public int deleteCountryWeather(String id) {
		return pmsInternetDao.deleteCountryWeather(id);
	}
	
	public HashMap<String, String> getItemInternet(String id, String langid){
		return pmsInternetDao.getItemInternet(id, langid);
	}
	
	public int editItemInternet(SystemServiceModel item){
		item.setName(UnicodeConverter.encodeUnicode(item.getName()));
		return pmsInternetDao.editItemInternet(item);
	}
}
