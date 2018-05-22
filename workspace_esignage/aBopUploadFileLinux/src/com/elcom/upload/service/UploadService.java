package com.elcom.upload.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddNewContentMedia;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetConfig;

public class UploadService {
	public static String admingetConfig() throws Exception {
		String link  = getService();
		AdcenterAdminServiceStub stub = new AdcenterAdminServiceStub(link);
		AdmingetConfig content = new AdmingetConfig();
		content.setXmlparam("");
		String xmlstring = stub.admingetConfig(content).get_return();
		return xmlstring;
	}
	
	public static void adminAddNewContentMedia(String xmlparamter) throws Exception{
		System.out.println("add new media content!");
		String link  = getService();
		System.out.println("link service: " + link);
		AdcenterAdminServiceStub stub = new AdcenterAdminServiceStub(link);
		AdminAddNewContentMedia content = new AdminAddNewContentMedia();
		content.setXmlparamter(xmlparamter);
		stub.adminAddNewContentMedia(content);
	}
	
	public static String getService(){
		String path = UploadService.class.getResource("").getPath();
		path = path.substring(0, path.indexOf("aBopUploadFile"));
		path += "aBopConfig/config.properties";
		System.out.println("path get properties: " + path);
		Properties pro = new Properties();
		InputStream input = null;
		String link = "";
		try{
			input = new FileInputStream(path);
			pro.load(input);
			link = pro.getProperty("END_POINT");
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(input!= null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("link service: " + link);
		return link;
	}
	
	public static void main(String[] args) throws Exception {
		String link  = "http://192.168.1.9:9090/VmsSignageRvcAdv/services/AdcenterAdminService?wsdl";
		AdcenterAdminServiceStub stub = new AdcenterAdminServiceStub(link);
		AdmingetConfig content = new AdmingetConfig();
		content.setXmlparam("");
		String xmlstring = stub.admingetConfig(content).get_return();
		System.out.println(xmlstring);
	}
}
