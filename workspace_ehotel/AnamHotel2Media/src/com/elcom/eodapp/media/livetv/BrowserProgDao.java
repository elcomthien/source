package com.elcom.eodapp.media.livetv;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.media.util.Vasc;
import com.elcom.eodapp.media.broker.*;
import com.elcom.eodapp.media.common.*;
import com.elcom.eodapp.media.util.*;
import com.elcom.eodapp.media.livetv.SQL;
import com.elcom.eodapp.media.exception.*;
import com.elcom.eodapp.media.cfg.*;
import org.apache.log4j.*;

public class BrowserProgDao {
	  private static final int BC_BROWSERINFO_SIZE = 10;
	  private static final int BC_PROGRAMS_SIZE = 10;
	  private static Configuration config = null;

	  //Refers the DB broker object
	  private static IMBroker broker = IMBroker.getInstance();
	  //To log application
	  private static final Logger logger = LogManager.getLogger(BrowserProgDao.class);
	  //Read configuration params
	  static
	  {
	    ConfigurationLoader loader = ConfigurationLoader.getInstance();
	    config = loader.getConfiguration();
	  }

	  private static final String pattern = "MM/dd/yyyy HH:mm:ss";

	  public BrowserProgDao()
	  {
	  }
	  
//=====================================================================
	  public String getClientURL(int channelid, String sernumber)
	  {
		  Vector params = new Vector(2);
	      SubProParam param = null;
	      param = new SubProParam(new String(), 1);
	      params.add(param);
	      param = new SubProParam(new BigDecimal(channelid), 0);
	      params.add(param);
	      
	      
	      params = broker.executeSubPro("BEGIN ? := vod.getUrlClientBc(?); END;", params);
	      
	      SubProParam paramOUT = (SubProParam)params.get(0);
	      String clientUrl = paramOUT.getString();
	      return clientUrl;
	  }
//=====================================================================
	  public String getLiveTvSubjectPhuGia(String keystb,int fromRow, int noRows)
	  {
	    String aRet = "<?xml version='1.0' encoding='UTF-8'?>\r\n<LiveSubject>\r\n";
        BCSubject bcsubject = null;
	    try{
	      Vector params = new Vector(1);
	      params.add(new String(keystb));
	      
	      Vector vResultSet = broker.executeSelect(SQL.sqlGetSubjectPhuGia, params,
	                                               noRows, fromRow);
	      Vector aRow = null;	     
	      for (int i = 2; i < vResultSet.size(); i++)
	      {
	        aRow = (Vector)vResultSet.get(i);	       
	        bcsubject = new BCSubject();
	        String subjectname = (String)aRow.get(1);
	        subjectname = UnicodeConverter.decodeUnicode(subjectname);
	        aRet = aRet + "<item id = '" + (String)aRow.get(0) + "'>\r\n";
	        aRet = aRet + "<subjectname><![CDATA[" + UnicodeConverter.encodeUnicode(subjectname) + "]]></subjectname>\r\n";
	        aRet = aRet + "</item>\r\n";	        
	      }
	      aRet = aRet + "</LiveSubject>";
	    } catch (Exception ex){
	      String msg = "Error from the DB ";
	      logger.error(msg, ex);
	      throw new BcpccSysException(msg + ex.getMessage());
	    }
	    return (aRet); //return result
	  } //getLiveTvSubject
//=====================================================================

	  public String getLiveTvSubject(String keystb,int fromRow, int noRows)
	  {
	    String aRet = "<?xml version='1.0' encoding='UTF-8'?>\r\n<LiveSubject>\r\n";
        BCSubject bcsubject = null;
	    try{
	      Vector params = new Vector(1);
	      params.add(new String(keystb));
	      
	      Vector vResultSet = broker.executeSelect(SQL.sqlGetSubjects, params,
	                                               noRows, fromRow);
	      Vector aRow = null;	     
	      for (int i = 2; i < vResultSet.size(); i++)
	      {
	        aRow = (Vector)vResultSet.get(i);	       
	        bcsubject = new BCSubject();
	        String subjectname = (String)aRow.get(1);
	        subjectname = UnicodeConverter.decodeUnicode(subjectname);
	        aRet = aRet + "<item id = '" + (String)aRow.get(0) + "'>\r\n";
	        aRet = aRet + "<subjectname><![CDATA[" + UnicodeConverter.encodeUnicode(subjectname) + "]]></subjectname>\r\n";
	        //aRet = aRet + "<subjectname><![CDATA[" + subjectname + "]]></subjectname>\r\n";
	        
	        aRet = aRet + "<picurl>" + (String)aRow.get(2) + "</picurl>\r\n";
	        aRet = aRet + "</item>\r\n";	        
	      }
	      aRet = aRet + "</LiveSubject>";
	    } catch (Exception ex){
	      String msg = "Error from the DB ";
	      logger.error(msg, ex);
	      throw new BcpccSysException(msg + ex.getMessage());
	    }
	    return (aRet); //return result
	  } //getLiveTvSubject
//======================================================================
	  public String getLivetvChannelList(int channelid)
	  {
		  String aRet = "<?xml version='1.0' encoding='UTF-8'?>\r\n<LiveChannel>\r\n"; 
		  String channelids = channelid + "";
		  String sql;
		  int inde = 0;
		  inde = channelids.indexOf("1200");
		  if (inde > 0)
		  {
			  channelid = new Integer(channelids.substring(0,channelids.indexOf("1200"))).intValue();
			  sql = SQL.sqlGetChannels1200;
		  }else
			  sql = SQL.sqlGetChannels;

	    try{
	    	
	      Vector params = new Vector(1);
		  params.add(new BigDecimal(channelid)); //1 IN	
		  
	      Vector vResultSet = broker.executeSelect(sql, params);

	      BCChannelDataBrief bc = null;
	      for (int i = 2; i < vResultSet.size(); i++)
	      {
	        Vector aRow = (Vector)vResultSet.get(i);
	        aRet = aRet + "<item id = '" + (String)aRow.get(0) + "'>\r\n";
	        aRet = aRet + "<ovschannelid>" + (String)aRow.get(1) + "</ovschannelid>\r\n";
	        aRet = aRet + "<channelname>" + (String)aRow.get(2) + "</channelname>\r\n";
	        aRet = aRet + "<channelnumber>" + (String)aRow.get(3) + "</channelnumber>\r\n";
	        aRet = aRet + "<price>" + (String)aRow.get(4) + "</price>\r\n";
	        aRet = aRet + "<urlplay>" + (String)aRow.get(5) + "</urlplay>\r\n";
	        aRet = aRet + "<urlpic>" + (String)aRow.get(6) + "</urlpic>\r\n";
	        aRet = aRet + "</item>\r\n";
	      }
	      aRet = aRet + "</LiveChannel>";
	    } catch (NumberFormatException ex){
	      String msg = "Error from the DB ";
	      logger.error(msg, ex);
	      throw new BcpccSysException(msg + ex.getMessage());
	    }
	    
	    return (aRet); //return result
	  }
//--------------------------------------------------------
	  
	  public String getChannelLivetivi(String  keystb)
	  {
		  String item = ""; 
		  	String result_vRS = "";
		  	Vector vRs = new Vector();
		  	Vector params = new Vector(2);

		  	SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
		  	params.add(ketstb_);	  
			
		  	SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
		  	params.add(out_data);
		  
		  	params = broker.executeSubPro(SQL.sqlgetChannelLivetivi,params);
		  	out_data = (SubProParam) params.get(1);
		  	vRs = out_data.getVector();		  
		  	result_vRS = loadDataChannelXML(vRs);
		  	item = result_vRS;
		  	return item;
	  }
	  
	  public String loadDataChannelXML(Vector vRS)
	  {
		String aRet = "<?xml version='1.0' encoding='UTF-8'?>\r\n<LiveChannel>\r\n";
		int id,ovschannelid,price,channelnumber,language;
	    String channelname,urlplay,urlpic;
	    VodCtnVO vo;
	    
	    for (int i = 0; i < vRS.size(); i = i + 8)
	    {	      
	      vo = new VodCtnVO(); //new an instance of MODCtnVO
	      id = Integer.parseInt((String)vRS.get(i));	      
	      ovschannelid = Integer.parseInt((String)vRS.get(i + 1));
	      channelname = UnicodeConverter.decodeUnicode((String)vRS.get(i + 2));
	      channelnumber = Integer.parseInt((String)vRS.get(i + 3));
	      price = Integer.parseInt((String)vRS.get(i + 4));
	      urlplay = (String)vRS.get(i + 5);
	      urlpic = (String)vRS.get(i + 6);
	      language = Integer.parseInt((String)vRS.get(i + 7));
	      
	      aRet = aRet + "<item id = '" + id + "'>\r\n";
	      aRet = aRet + "<ovschannelid>" + ovschannelid + "</ovschannelid>\r\n";
	      aRet = aRet + "<channelname>" + channelname + "</channelname>\r\n";
	      aRet = aRet + "<channelnumber>" + channelnumber + "</channelnumber>\r\n";
	      aRet = aRet + "<price>" + price + "</price>\r\n";
	      aRet = aRet + "<urlplay>" + urlplay + "</urlplay>\r\n";
	      aRet = aRet + "<urlpic>" + urlpic + "</urlpic>\r\n";
	      aRet = aRet + "<language>" + language + "</language>\r\n";
	      aRet = aRet + "</item>\r\n"; 
	    }
	    aRet = aRet + "</LiveChannel>";
	    return aRet;
	  }
//---------------------------------------------------------------------------------------
	  public int countLiveTv(int subjectid)
	  {
		  int count = 0;
		  Vector<SubProParam> params = new Vector<SubProParam>(2);
		    
		  SubProParam param = null;
		  param = new SubProParam(new String(), SubProParam.OUT);
		  params.add(param); //0 OUT
		  
		  param = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
		  params.add(param); //1 IN
		  
		    //Executes the DB stored procedure
		 params = broker.executeSubPro(SQL.countLiveTv, params);
		 //Get data returned by the stored procedure
		 SubProParam paramOUT = (SubProParam)params.get(0);	
		 count = new java.lang.Integer(paramOUT.getString()).intValue();	 
		 return count;
	  }  
	  
}
