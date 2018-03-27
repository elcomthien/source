package com.elcom.ehotel.esmile.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.service.TabletService;

@Controller
@RequestMapping("/tablet")
public class TabletController {

	@Autowired
	private TabletService tabletService;

	@RequestMapping(value = { "table" }, method = RequestMethod.POST)
	public @ResponseBody List<HashMap<String, String>> getListTable(@RequestBody Params params) {
		System.out.println("getListTable with params: " + params);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list = tabletService.getListTable(params);
		return list;
	}

	@RequestMapping(value = { "location" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> setLocation(@RequestBody Params params) {
		System.out.println("Set location with params: " + params);
		HashMap<String, String> map = new HashMap<String, String>();
		map = tabletService.setLocation(params);
		return map;
	}

	@RequestMapping(value = { "statusrequest/{tableid}/{buttonid}" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> setStatusRequest(@PathVariable String tableid, @PathVariable String buttonid) {
		Params params = new Params();
		params.setTable_id(tableid);
		params.setButton_id(buttonid);
		System.out.println("setStatusRequest with params: " + params);
		HashMap<String, String> map = new HashMap<String, String>();
		map = tabletService.setStatusRequest(params);
		return map;
	}

	@RequestMapping(value = { "rating" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getRatingTime(@RequestBody Params params) {
		System.out.println("getRatingTime with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = tabletService.getRatingTime(params);
		return map;
	}

	@RequestMapping(value = { "notify" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> setNotify(@RequestBody Params params) {
		System.out.println("setNotify with params: " + params);
		HashMap<String, String> map = new HashMap<String, String>();
		map = tabletService.setNotify(params);
		return map;
	}

	@RequestMapping(value = { "notify/{empid}" }, method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, String>> getNotify(@PathVariable String empid) {
		Params params = new Params();
		params.setEmp_id(empid);
		System.out.println("getNotify with params: " + params);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list = tabletService.getNotify(params);
		return list;
	}

	@RequestMapping(value = { "tables" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getTable(@RequestBody Params params) {
		System.out.println("getTable with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = tabletService.getTables(params);
		return map;
	}

	@RequestMapping(value = { "button" }, method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, String>> getButton() {
		System.out.println("getButton with none params");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list = tabletService.getButton();
		return list;
	}

	@RequestMapping(value = { "employee" }, method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, String>> getEmployee() {
		System.out.println("getEmployee with none params");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list = tabletService.getEmployee();
		return list;
	}

	@RequestMapping(value = { "login" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> login(@RequestBody Params params) {
		System.out.println("login with params: " + params);
		HashMap<String, String> map = new HashMap<String, String>();
		map = tabletService.login(params);
		return map;
	}
	
	@RequestMapping(value = { "regtablet" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> registerDevice(@RequestBody Params params) {
		System.out.println("registerDevice with params: " + params);
		HashMap<String, String> map = new HashMap<String, String>();
		map = tabletService.registerDevice(params);
		return map;
	}
	
	@RequestMapping(value = { "information/{deviceid}" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getStoreInfo(@PathVariable String deviceid) {
		Params params = new Params();
		params.setDevice_id(deviceid);
		System.out.println("getStoreInfo with params: " + params);
		HashMap<String, String> map = new HashMap<String, String>();
		map = tabletService.getStoreInfo(params);
		return map;
	}
	
	@RequestMapping(value = { "store" }, method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, String>> getStore() {
		System.out.println("getStore with none params");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		list = tabletService.getStore();
		return list;
	}
}
