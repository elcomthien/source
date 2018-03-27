package com.elcom.ehotel.esmile.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = { "getstore/{userid}" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getStore(@PathVariable String userid) {
		Params params = new Params();
		params.setUser_id(userid);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getStore(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "getstore" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getStorePOST(@RequestBody Params params) {
		System.out.println("getStorePOST with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getStore(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "realtime/{storeid}" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getRealTime(@PathVariable String storeid) {
		Params params = new Params();
		params.setStore_id(storeid);
		System.out.println("getRealTime with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getRealTime(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "realtime" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getRealTimePOST(@RequestBody Params params) {
		System.out.println("getRealTimePOST with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getRealTime(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "getrealtime/{storeid}" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getRealTimeDetal(@PathVariable String storeid) {
		Params params = new Params();
		params.setStore_id(storeid);
		System.out.println("getRealTimeDetal with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getRealTimeDetail(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "getrealtime" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getRealTimeDetalPOST(@RequestBody Params params) {
		System.out.println("getRealTimeDetalPOST with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getRealTimeDetail(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "gettable/{storeid}" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getTableGET(@PathVariable String storeid) {
		Params params = new Params();
		params.setStore_id(storeid);
		System.out.println("getTableGET with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getTable(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "gettable" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getTablePOST(@RequestBody Params params) {
		System.out.println("getTablePOST with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getTable(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "login" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> login(@RequestBody Params params) {
		System.out.println("login with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.login(params);
		return map;
	}

	@RequestMapping(value = { "sumlike" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getSumLike(@RequestBody Params params) {
		System.out.println("getSumLike with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getSumLike(params);
		return map;
	}

	@RequestMapping(value = { "sumwait" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getSumWait(@RequestBody Params params) {
		System.out.println("getSumWait with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getSumWait(params);
		return map;
	}

	@RequestMapping(value = { "postdone" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> postDone(@RequestBody Params params) {
		System.out.println("postDone with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.postDone(params);
		return map;
	}

	@RequestMapping(value = { "getinfotable" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getInfoTable(@RequestBody Params params) {
		System.out.println("getInfoTable with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getInfoTable(params);
		return map;
	}

	@RequestMapping(value = { "getquickstats" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getQuickStats(@RequestBody Params params) {
		System.out.println("getQuickStats with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getQuickStats(params);
		return map;
	}

	@RequestMapping(value = { "gethistory" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getHistory(@RequestBody Params params) {
		System.out.println("getHistory with params: " + params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = adminService.getHistory(params);
		return map;
	}

}
