package com.elcom.ehotel.esmile.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.elcom.ehotel.esmile.service.eSmileService;
import com.google.gson.Gson;


public class TestWS {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://172.16.9.100:2999/ws/esmile?wsdl");
		QName qname = new QName("http://service.esmile.ehotel.elcom.com/", "eSmileServiceImplService");
		Service service = Service.create(url, qname);
		eSmileService eSmileService = service.getPort(eSmileService.class);
		HashMap<String, String> hm = new HashMap<>();
		hm.put("username", "admin");
		hm.put("password", "123");
		System.out.println(new Gson().toJson(hm));
		System.out.println(eSmileService.getLogin(new Gson().toJson(hm)));

//		InterfaceSerivce interfaceSerivce = service.getPort(InterfaceSerivce.class);
	//
//		System.out.println(interfaceSerivce.getHelloWorldAsString("Hello world"));
	}
	
}
