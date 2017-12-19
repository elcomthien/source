package com.elcom.ehotel.esmile.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.service.LayoutService;

@Controller
@RequestMapping("")
public class eSmileController {

	@Autowired
	private LayoutService layoutService;

	@RequestMapping(value = { "addlayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addLayout(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = layoutService.addLayout(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "editlayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> editLayout(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = layoutService.editLayout(params);
		System.out.println(map);
		return map;
	}

	@RequestMapping(value = { "deletelayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteLayout(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = layoutService.deleteLayout(params);
		System.out.println(map);
		return map;
	}
	
	@RequestMapping(value = { "getseqlayout" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getLayout() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = layoutService.getLayout();
		System.out.println(map);
		return map;
	}
}
