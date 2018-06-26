package com.elcom.eodapp.media.pms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.media.util.*;
import com.elcom.eodapp.media.broker.IMBroker;
import com.elcom.eodapp.media.common.eInfoVideo;
import com.elcom.eodapp.media.common.eMessage;
import com.elcom.eodapp.media.common.eService;
import com.elcom.eodapp.media.cfg.Configuration;
import com.elcom.eodapp.media.cfg.ConfigurationLoader;
import com.elcom.eodapp.media.exception.BcpccSysException;




public class PmsContent {
	private static IMBroker broker = IMBroker.getInstance();
	private static final Logger logger = LogManager.getLogger(PmsContent.class);
	private static final String pattern = "MM/dd/yyyy HH:mm:ss";
	private static Configuration config = null;
	
	 static
	  {
	    ConfigurationLoader loader = ConfigurationLoader.getInstance();
	    config = loader.getConfiguration();
	  }
	
	public PmsContent(){}
	
	public String getLinkPromotion(int promotionId, String smartcard) {
		 String clientUrl;
		    //Bind IN/OUT Parameters
		    Vector params = new Vector(2);
		    SubProParam param = null;
		    param = new SubProParam(new String(), SubProParam.OUT);
		    params.add(param); //0 OUT		    
		    param = new SubProParam(new BigDecimal(promotionId), SubProParam.IN);
		    params.add(param); //1 IN		   
		    param = new SubProParam(new String(smartcard), SubProParam.IN);
		    params.add(param); //2 IN

		    //Executes the DB stored procedure
		    params = broker.executeSubPro(SQL.sqlGetLinkPromotion, params);
		    //Get data returned by the stored procedure
		    SubProParam paramOUT = (SubProParam)params.get(0);
		    if (paramOUT.getString() == null)
		    	clientUrl = "";
		    else clientUrl = (paramOUT.getString());

		   
		   

		    return (clientUrl);

	}
	
	public String getLocations(String homeServiceId, String smartcard) {
		 String xml;
		    //Bind IN/OUT Parameters
		    Vector params = new Vector(2);
		    SubProParam param = null;
		    param = new SubProParam(new String(), SubProParam.OUT);
		    params.add(param); //0 OUT		    
		    param = new SubProParam(new BigDecimal(homeServiceId), SubProParam.IN);
		    params.add(param); //1 IN		   
		    param = new SubProParam(new String(smartcard), SubProParam.IN);
		    params.add(param); //2 IN

		    //Executes the DB stored procedure
		    params = broker.executeSubPro(SQL.sqlgetLocations, params);
		    //Get data returned by the stored procedure
		    SubProParam paramOUT = (SubProParam)params.get(0);
		    if (paramOUT.getString() == null)
		    	xml = "";
		    else xml = (paramOUT.getString());
		    return (xml);

	}
	
	public String getLocationPics(String locateid, String smartcard) {
		 String xml;
		    //Bind IN/OUT Parameters
		    Vector params = new Vector(2);
		    SubProParam param = null;
		    param = new SubProParam(new String(), SubProParam.OUT);
		    params.add(param); //0 OUT		    
		    param = new SubProParam(new BigDecimal(locateid), SubProParam.IN);
		    params.add(param); //1 IN		   
		    param = new SubProParam(new String(smartcard), SubProParam.IN);
		    params.add(param); //2 IN

		    //Executes the DB stored procedure
		    params = broker.executeSubPro(SQL.sqlgetLocationPics, params);
		    //Get data returned by the stored procedure
		    SubProParam paramOUT = (SubProParam)params.get(0);
		    if (paramOUT.getString() == null)
		    	xml = "";
		    else xml = (paramOUT.getString());
		    return (xml);

	}
	
	public String getHomeService(String stbkey) {
		String xml = "";
		Vector params = new Vector(2);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 0 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetHomeService, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eService> services = LoadService(outParam);

		
		xml = "";
		xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
		xml = xml + "<name>ELCOM-HCM</name>\r\n";
		for (int i = 0; i < services.size(); i++) {
			eService service = new eService();
			service = services.get(i);
			xml = xml + "<item id='" + service.getService_id() + "'>\r\n";
			//xml = xml + "<name><![CDATA[" + UnicodeConverter.encodeUnicode(service.getService_name()) + "]]></name>\r\n";
			//xml = xml + "<name><![CDATA[" + decodeHtml(service.getService_name()) + "]]></name>\r\n";
			xml = xml + "<name><![CDATA[" + service.getService_name() + "]]></name>\r\n";
			
			xml = xml + "<service_code>" + service.getService_code() + "</service_code>\r\n";
			xml = xml + "<apkcode>" + service.getApk_code() + "</apkcode>\r\n";
			xml = xml + "<urlIcon>" + service.getUrl_icon() + "</urlIcon>\r\n";
			xml = xml + "<urlImage>" + service.getUrl_image() + "</urlImage>\r\n";
			xml = xml + "<urlPicBg>" + service.getUrl_picbg() + "</urlPicBg>\r\n";
			xml = xml + "<level>" + service.getIlevel() + "</level>\r\n";
			xml = xml + "<urllink>" + service.getService_url() + "</urllink>\r\n";
			xml = xml + "</item>\r\n";
		}
		xml = xml + "</ehotel>";
		return xml;
	}
	
	public String getMessageInfo(int messId, String smartcard) {
		String xml = "";
		eMessage aMessage = null;
		Vector<eMessage> eMessages = new Vector<eMessage>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subIn = new SubProParam(new java.math.BigDecimal(messId),
				SubProParam.IN);
		params.add(subIn);

		subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = ("getMessageInfo with messId=" + messId + "] ");

		try {
			params = broker.executeSubPro(SQL.sqlGetMessageInfo, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		eMessages = LoadMessages(outParam, 0);
		if (eMessages != null && eMessages.size() > 0) {
			aMessage = eMessages.get(0);
		}
		
		xml = xml + "<item id='" + aMessage.getId() + "'>";
		xml = xml + "<numNo>" + aMessage.getCheckNo() + "</numNo>";
		xml = xml + "<content><![CDATA[" +  UnicodeConverter.encodeUnicode((aMessage.getContent())) + "]]></content>";
		xml = xml + "<content_bottom><![CDATA[" + UnicodeConverter.encodeUnicode(aMessage.getContent_bottom()) + "]]></content_bottom>";
		xml = xml + "<content_top><![CDATA[" + UnicodeConverter.encodeUnicode(aMessage.getContent_top()) + "]]></content_top>";
		xml = xml + "<enterDate>" + aMessage.getEnterDate() + "</enterDate>";
		//xml = xml + "<enterTime>" + aMessage.getEnterTime() + "</enterTime>";
		xml = xml + "<enterTime> </enterTime>";
		xml = xml + "<room>" + aMessage.getFolioNum() + "</room>";
		xml = xml + "<isConfirm>" + aMessage.getIsConfirm() + "</isConfirm>";
		xml = xml + "<isRead>" + aMessage.getIsRead() + "</isRead>";
		xml = xml + "<sender>" + aMessage.getSender() + "</sender>";
		xml = xml + "<subject>" + aMessage.getSubject() + "</subject>";
		xml = xml + "<type>" + aMessage.getType() + "</type>";
		xml = xml + "</item>";
		return xml;
	}
	
	
	
	public String getVideoInfo(String stbkey,String menuid) {
		String xml = "";
		Vector params = new Vector(2);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 0 IN
		
		param = new SubProParam(menuid, SubProParam.IN);
		params.add(param); // 0 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetVideoInfo, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eInfoVideo> Infovideo = LoadVideoInfo(outParam);

		
		xml = "";
		xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
		xml = xml + "<name>ELCOM-HCM</name>\r\n";
		for (int i = 0; i < Infovideo.size(); i++) {
			eInfoVideo infovideo = new eInfoVideo();
			infovideo = Infovideo.get(i);
			xml = xml + "<item id='" + infovideo.getId() + "'>\r\n";
			xml = xml + "<title><![CDATA[" + decodeHtml(infovideo.getTitle()) + "]]></title>\r\n";
			xml = xml + "<def><![CDATA[" + infovideo.getDef() + "]]></def>\r\n";
			xml = xml + "<type>" + infovideo.getType() + "</type>\r\n";
			xml = xml + "<idrvc>" + infovideo.getId_rvc() + "</idrvc>\r\n";
			xml = xml + "</item>\r\n";
		}
		xml = xml + "</ehotel>";
		return xml;
	}
	
	
	private static String decodeHtml(String original) {
		return UnicodeConverter.decodeUnicode(original);
	}
	
	public static String trim(String data) {
		if (data != null) {
			return data.trim();
		}
		return null;
	}
	
	public static int parseInt(String number) {
		number = trim(number);
		if (number != null && !number.equals("null") && !number.equals("")) {
			return Integer.parseInt(number);
		}
		return 0;
	}

	public static Vector<eMessage> LoadMessages(Vector<String> vTmp, int numStr) {
		Vector<eMessage> ret = new Vector<eMessage>();
		eMessage mess = null;
		for (int i = 0; i < vTmp.size(); i = i + 12) {
			mess = new eMessage();
			mess.setId(Integer.parseInt((String) vTmp.get(i)));
			String subject = (String) vTmp.get(i + 1);
			mess.setSubject(decodeHtml(subject));

			String content = (String) vTmp.get(i + 2);
			mess.setContent(decodeHtml(content));

			String content_top = (String) vTmp.get(i + 3);
			mess.setContent_top(decodeHtml(content_top));

			String content_bottom = (String) vTmp.get(i + 4);
			mess.setContent_bottom(decodeHtml(content_bottom));

			String isRead = (String) vTmp.get(i + 5);
			if (isRead == null || isRead.equals("")) {
				isRead = "0";
			}
			mess.setIsRead(Integer.parseInt(isRead));

			mess.setCheckNo(parseInt((String) vTmp.get(i + 6)));

			String sender = (String) vTmp.get(i + 7);
			mess.setSender(decodeHtml(sender));

			String type = (String) vTmp.get(i + 8);
			if (type == null) {
				type = "NORMAL";
			}
			mess.setType(type);

			mess.setEnterDate((String) vTmp.get(i + 9));
			mess.setEnterTime((String) vTmp.get(i + 10));
			mess.setFolioNum(parseInt((String) vTmp.get(i + 11)));
			ret.add(mess);
		}

		return ret;
	}
	
	public static Vector<eService> LoadService(Vector<String> vTmp) {
		Vector<eService> ret = new Vector<eService>();
		eService service = null;
		for (int i = 0; i < vTmp.size(); i = i + 10) {
			service = new eService();
			service.setService_id(Integer.parseInt((String) vTmp.get(i)));
			service.setService_name((String) vTmp.get(i + 1));
			service.setUrl_icon((String) vTmp.get(i + 3));
			service.setApk_code((String) vTmp.get(i + 4));
			service.setService_url((String) vTmp.get(i + 5));
			service.setService_code((String) vTmp.get(i + 6));
			service.setUrl_image((String) vTmp.get(i + 7));
			service.setUrl_picbg((String) vTmp.get(i + 8));
			service.setIlevel((String) vTmp.get(i + 9));
			ret.add(service);
		}
		return ret;
	}
//--------------------------------------------------------------------------------------
	public static Vector<eInfoVideo> LoadVideoInfo(Vector<String> vTmp) {
		Vector<eInfoVideo> ret = new Vector<eInfoVideo>();
		eInfoVideo infovideo = null;
		for (int i = 0; i < vTmp.size(); i = i + 5) {
			infovideo = new eInfoVideo();
			infovideo.setId(Integer.parseInt((String) vTmp.get(i)));
			infovideo.setTitle((String) vTmp.get(i + 1));
			infovideo.setDef((String) vTmp.get(i + 2));
			infovideo.setType((String) vTmp.get(i + 3));
			infovideo.setId_rvc((String) vTmp.get(i + 4));
			
			ret.add(infovideo);
		}
		return ret;
	}
	
}
