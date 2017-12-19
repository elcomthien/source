package com.elcom.eodapp.ehotel.processor;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.text.TabExpander;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_OUT_PARAM;

public class JSONParser_SoKha {
	
	private static final Logger logger = LogManager.getLogger(JSONParser_SoKha.class);
	XposConverter converter = DAOFactory.getInstance().getXposConverter();
	
	
	JSONParser jsonParser = new JSONParser();
	public Hashtable<String, String> ParseCheckIn(String message) {
		
		Hashtable<String, String> tabResult = (Hashtable<String, String>)MainObject.mapCheckIn.clone();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);
			
			Set<String> keys = tabResult.keySet();
			for(String key : keys) {
				if(!jsonObject.containsKey(key))
					continue;				
				tabResult.put(key, (String)jsonObject.get(key));
			}			
		}
		catch(Exception ex) {
			logger.info("ParseCheckIn error:");
			logger.error(ex);
		}
		return tabResult;
	}
	
	public Hashtable<String, String> ParseCheckOut(String message) {
		Hashtable<String, String> tabResult = (Hashtable<String, String>)MainObject.mapCheckOut.clone();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);
			
			Set<String> keys = tabResult.keySet();
			for(String key : keys) {
				if(!jsonObject.containsKey(key))
					continue;
				tabResult.put(key, (String)jsonObject.get(key));
			}			
		}
		catch(Exception ex) {
			logger.info("ParseCheckOut error:");
			logger.error(ex);
		}
		return tabResult;
	}
	
	public Hashtable<String, String> ParseGuestMove(String message) {
		Hashtable<String, String> tabResult = (Hashtable<String, String>)MainObject.mapGuestMove.clone();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);
			
			Set<String> keys = tabResult.keySet();
			for(String key : keys) {
				if(!jsonObject.containsKey(key))
					continue;
				tabResult.put(key, (String)jsonObject.get(key));
			}			
		}
		catch(Exception ex) {
			logger.info("ParseGuestMove error:");
			logger.error(ex);
		}
		return tabResult;
	}
	
	
	public Hashtable<String, String> ParseWaitMessage(String message) {
		Hashtable<String, String> tabResult = (Hashtable<String, String>)MainObject.mapWaitMess.clone();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);
			
			Set<String> keys = tabResult.keySet();
			for(String key : keys) {
				if(!jsonObject.containsKey(key))
					continue;
				tabResult.put(key, (String)jsonObject.get(key));
			}			
		}
		catch(Exception ex) {
			logger.info("ParseWaitMessage error:");
			logger.error(ex);
		}
		return tabResult;
	}
	
	public ArrayList<XposMessageObj> ParseGuestMessage(String message) {
		ArrayList<XposMessageObj> listMessage = new ArrayList<XposMessageObj>();		
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);
			Set<String> listKey = jsonObject.keySet();
			for(String key : listKey) {				
				JSONObject dataObj = (JSONObject)jsonObject.get(key);
				Hashtable<String, String> tabResult = (Hashtable<String, String>)MainObject.mapGuestMessage.clone();
				//Pares element
				Set<String> tabKeys = tabResult.keySet();
				for(String param : tabKeys) {
					if(!dataObj.containsKey(param))
						continue;
					tabResult.put(param, (String)dataObj.get(param));
				}
				XposMessageObj obj = converter.ConvertMessageObj(tabResult);
				listMessage.add(obj);
			}
		}
		catch(Exception ex) {
			logger.info("ParseGuestMessage error:");
			logger.error(ex);
		}
		return listMessage;
	}
	
	public ArrayList<XposBillObj> ParseGuestBill(String message) {
		ArrayList<XposBillObj> listBill = new ArrayList<XposBillObj>();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);			
			Set<String> listKey = jsonObject.keySet();
			for(String key : listKey) {				
				Hashtable<String, String> tabBill = (Hashtable<String, String>)MainObject.mapGuestBill.clone();
				JSONObject dataObj = (JSONObject)jsonObject.get(key);
				//Pares element
				Set<String> tabKeys = tabBill.keySet();
				for(String param : tabKeys) {
					if(!dataObj.containsKey(param))
						continue;
					tabBill.put(param, (String)dataObj.get(param));
				}				
				XposBillObj billObj = converter.ConvertBillObj(tabBill);
				listBill.add(billObj);
			}
		}
		catch(Exception ex) {
			logger.info("ParseGuestBill error:");
			logger.error(ex);
		}
		return listBill;
	}
	
	public ArrayList<XposGuestObj> ParseGuestDBSwap (String message) {
		ArrayList<XposGuestObj> listBill = new ArrayList<XposGuestObj>();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);			
			Set<String> listKey = jsonObject.keySet();
			for(String key : listKey) {				
				Hashtable<String, String> tabData = (Hashtable<String, String>)MainObject.mapCheckIn.clone();
				JSONObject dataObj = (JSONObject)jsonObject.get(key);
				//Pares element
				Set<String> tabKeys = tabData.keySet();
				for(String param : tabKeys) {
					if(!dataObj.containsKey(param))
						continue;
					tabData.put(param, (String)dataObj.get(param));
				}				
				XposGuestObj guestObj = converter.ConvertGuestObj(tabData);
				listBill.add(guestObj);
			}
		}
		catch(Exception ex) {
		}
		return listBill;
	}
}
