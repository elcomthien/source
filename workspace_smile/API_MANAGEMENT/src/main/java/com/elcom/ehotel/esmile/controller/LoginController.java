package com.elcom.ehotel.esmile.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.service.LoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> checkLogin(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("username", params.getUsername());
//		map.put("password", params.getPassword());
		map = loginService.checkLogin(params);
		System.out.println(map);
		return map;
	}
}
