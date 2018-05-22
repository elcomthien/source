package com.elcom.ehotel.admin.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.elcom.ehotel.admin.dao.InterafaceDao;

public class InterfaceService {

	private InterafaceDao interafaceDao = new InterafaceDao();

	public Map<String, Object> interfaceFileXML(String path) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> listin = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> listout = new ArrayList<HashMap<String, String>>();
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			// System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("G_ROOM");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				// System.out.println("\nCurrent Element :" + nNode.getNodeName().toString());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					HashMap<String, String> guest = new HashMap<String, String>();
					guest.put("folionum", eElement.getElementsByTagName("ROOM").item(0).getTextContent());
					guest.put("arrival", eElement.getElementsByTagName("ARRIVAL").item(0).getTextContent());
					guest.put("departure", eElement.getElementsByTagName("DEPARTURE").item(0).getTextContent());
					guest.put("name", eElement.getElementsByTagName("GUEST_NAME").item(0).getTextContent());
					guest.put("guestid", eElement.getElementsByTagName("GUEST_NAME_ID").item(0).getTextContent());
					guest.put("reservation", eElement.getElementsByTagName("RESV_NAME_ID").item(0).getTextContent());
					Date date = new SimpleDateFormat("dd/MM/yy").parse(guest.get("departure"));
					Date today = new Date();
					// System.out.println(guest.get("departure") + " - " + date.compareTo(today));
					if (date.compareTo(today) == 1)
						listin.add(guest);
					else
						listout.add(guest);
				}
			}
			map = checkoutFileService(listout, map);
			map = checkinFileService(listin, map);
			if (map.size() == 0) {
				map.put("status", 1);
				map.put("message", "successful");
			} else {
				map.put("status", 0);
				map.put("message", "error");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("return result : " + map);
		return map;
	}

	public Map<String, Object> checkoutFileService(List<HashMap<String, String>> list, Map<String, Object> map) {
		List<HashMap<String, String>> error = new ArrayList<HashMap<String, String>>();
		int rs = 0;
		for (int i = 0; i < list.size(); i++) {

			rs = interafaceDao.checkoutGuest(list.get(i));
			if (rs == -1) {
				HashMap<String, String> hmap = new HashMap<String, String>();
				hmap.put("message", "Checkout Error");
				hmap.put("room", list.get(i).get("foliomum"));
				hmap.put("name", list.get(i).get("name"));
				error.add(hmap);
			}
		}
		if (error.size() > 0)
			map.put("checkout", error);
		return map;
	}

	public Map<String, Object> checkinFileService(List<HashMap<String, String>> list, Map<String, Object> map) {
		List<HashMap<String, String>> error = new ArrayList<HashMap<String, String>>();
		int rs = 0;
		for (int i = 0; i < list.size(); i++) {

			rs = interafaceDao.checkinGuest(list.get(i));
			if (rs == -1) {
				HashMap<String, String> hmap = new HashMap<String, String>();
				hmap.put("message", "Checkin Error");
				hmap.put("room", list.get(i).get("foliomum"));
				hmap.put("name", list.get(i).get("name"));
				error.add(hmap);
			}
		}
		if (error.size() > 0)
			map.put("checkin", error);
		return map;
	}

	public static void main(String[] args) {
		InterfaceService r = new InterfaceService();
		System.out.println(r.interfaceFileXML(""));

		// try {
		//
		// File fXmlFile = new File("D:/Run/staff.xml");
		// DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// Document doc = dBuilder.parse(fXmlFile);
		//
		// //optional, but recommended
		// //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		// doc.getDocumentElement().normalize();
		//
		// System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		//
		// NodeList nList = doc.getElementsByTagName("staff");
		//
		// System.out.println("----------------------------");
		//
		// for (int temp = 0; temp < nList.getLength(); temp++) {
		//
		// Node nNode = nList.item(temp);
		//
		// System.out.println("\nCurrent Element :" + nNode.getNodeName());
		//
		// if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		//
		// Element eElement = (Element) nNode;
		//
		// System.out.println("Staff id : " + eElement.getAttribute("id"));
		// System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
		// System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
		// System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
		// System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
		//
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
