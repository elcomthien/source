package com.elcom.ehotel.esmile.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elcom.ehotel.esmile.service.IOTService;

@Controller
@RequestMapping("")
public class ComandController {

	@Resource
	WebServiceContext webServiceContext;

	@Autowired
	private IOTService iotService;

	@RequestMapping(value = "{table}/{idbu}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getCommand(@PathVariable String table, @PathVariable String idbu, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("table", table);
		map.put("command", idbu);
		map.put("ip", request.getRemoteAddr());

		System.out.println("object: " + map);
		iotService.addDataRaw(table, idbu);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result: " + "status_code", "200");
		return result;
	}
}
