package com.elcom.ehotel.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.PMSWeatherModel;
import com.elcom.ehotel.admin.model.SystemServiceModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSInternetDao {

	@SuppressWarnings("unchecked")
	public List<PMSWeatherModel> getListCountryWeather() {
		List<PMSWeatherModel> list = new ArrayList<PMSWeatherModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_WEATHER, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInternetDao.class.toString(), SQL.GET_LIST_WEATHER, params, "none", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			PMSWeatherModel wea = new PMSWeatherModel();
			wea.setId(outParam.get(i));
			wea.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			wea.setDef(outParam.get(i + 2));
			wea.setCountry(outParam.get(i + 3));
			wea.setIscurrent(outParam.get(i + 4));
			wea.setLocation(outParam.get(i + 5));
			wea.setIndex(outParam.get(i + 6));
			wea.setImage(outParam.get(i + 7));
			wea.setStatus(outParam.get(i + 8));
			list.add(wea);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addCountryWeather(PMSWeatherModel wea) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(wea.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getDef()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getCountry()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getIscurrent()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getLocation()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getStatus()), 0);
		params.add(in);

		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);

		try {
			params = SQL.broker.executeSubPro(SQL.ADD_COUNTRY_WEATHER, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInternetDao.class.toString(), SQL.ADD_COUNTRY_WEATHER, params, "name,def,country,iscurrent,location,index,image,status", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editCountryWeather(PMSWeatherModel wea) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(wea.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getDef()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getCountry()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getIscurrent()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getLocation()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(wea.getStatus()), 0);
		params.add(in);

		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);

		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_COUNTRY_WEATHER, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInternetDao.class.toString(), SQL.EDIT_COUNTRY_WEATHER, params, "id,name,def,country,iscurrent,location,index,image,status", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteCountryWeather(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);

		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);

		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_COUNTRY_WEATHER, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInternetDao.class.toString(), SQL.DELETE_COUNTRY_WEATHER, params, "id", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getItemInternet(String id, String langid) {
		HashMap<String, String> hm = new HashMap<String, String>();

		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_ITEM_SERVICE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInternetDao.class.toString(), SQL.GET_ITEM_SERVICE, params, "serviceid,langid", outParam.size());
		hm.put("id", outParam.get(0));
		hm.put("name", UnicodeConverter.decodeUnicode(outParam.get(1)));
		hm.put("invisible", outParam.get(2));
		hm.put("index", outParam.get(3));
		hm.put("package", outParam.get(4));
		hm.put("image", outParam.get(5));
		hm.put("apkpath", outParam.get(6));
		return hm;
	}

	@SuppressWarnings("unchecked")
	public int editItemInternet(SystemServiceModel item) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(item.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getApkpackage()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getApkpath()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getLangid()), 0);
		params.add(in);

		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);

		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_ITEM_SERVICE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInternetDao.class.toString(), SQL.EDIT_ITEM_SERVICE, params, "id,name,invisible,index,package,image,apkpath,langid", rs);
		return rs;
	}

	public static void main(String[] args) {
		PMSInternetDao w = new PMSInternetDao();
		System.out.println(w.getListCountryWeather());
		System.out.println(w.getItemInternet("31", "2"));
	}
}
