package com.elcom.ehotel.esmile.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class HttpURLConnectionCon {
	static String url="https://fcm.googleapis.com/fcm/send";

	static private final String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws Exception {

//		HttpURLConnectionCon http = new HttpURLConnectionCon();

//		System.out.println("Testing 1 - Send Http GET request");
//		http.sendGet();

		System.out.println("\nTesting 2 - Send Http POST request");
//		http.sendPost("1","2057","","");

	}

	// HTTP GET request
	public static void sendGet() throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	static String key2="AAAAPfamnM8:APA91bHZXbjzPN6jtdur_eAB1kiocO08kWevALntTfGVW8iJC2ehHh1yDZXJBYaeO2a5B2q9z4nsK6ndWVXSPDUdgRJWWuIDs6q98FGFk_EpQUDHssV-wB4qdcztKL9ycREJ0WUBTBD3";

	static String nameTopic="/topics/info";
	
//	{"to":"/topics/info",
//		"notification":{
//			"body":{"user_id":"1","key":"false"},
//			"priority" : "high",
//			"title":"Title"
//		}
//	}
	// HTTP POST request
	public static void sendPost(String userid, String key, String storeid, String locationid) throws Exception {

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//		byte[] message = ("key"+":"+key).getBytes("UTF-8");
//		String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);		
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "key="+key2);
		con.setRequestProperty("Content-Type", "application/json");

		JSONObject json=new JSONObject();
		json.put("to", nameTopic);
		JSONObject jsonNo=new JSONObject();
		JSONObject jsonInfo=new JSONObject();
		jsonInfo.put("key", key);
		jsonInfo.put("user_id", userid);
		jsonInfo.put("location_id", locationid);
		jsonInfo.put("store_id", storeid);
		jsonNo.put("body", jsonInfo);
		json.put("notification", jsonNo);
		String urlParameters = json.toString();

		// Send post request
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
}
