package com.elcom.ehotel.esmile.endpoint;

import javax.xml.ws.Endpoint;

import com.elcom.ehotel.esmile.service.eSmileServiceImpl;
import com.elcom.ehotel.esmile.util.Config;

public class eSmilePulisher {
	public static void main(String[] args) {
		System.out.println("Start web service eHotel eSmile!!!");
		Config config = new Config();
		Endpoint.publish(config.getLinkWS(), new eSmileServiceImpl());
	}
}
