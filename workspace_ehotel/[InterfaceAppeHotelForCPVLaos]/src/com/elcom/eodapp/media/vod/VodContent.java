package com.elcom.eodapp.media.vod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.media.broker.IMBroker;
import com.elcom.eodapp.media.common.VodCtnBriefVO;
import com.elcom.eodapp.media.common.VodCtnVO;
import com.elcom.eodapp.media.common.VodSubjectVO;
import com.elcom.eodapp.media.common.VodUrlVo;
import com.elcom.eodapp.media.exception.ModCtnNotFoundAppException;
import com.elcom.eodapp.media.exception.VodClientTypeNotFoundAppException;
import com.elcom.eodapp.media.exception.VodURLUnableException;
import com.elcom.eodapp.media.vod.SQL;
import com.elcom.eodapp.media.util.DateHelper;

public class VodContent {
	  private static final Logger logger = LogManager.getLogger(VodContent.class);
	  private static IMBroker broker = IMBroker.getInstance();
	  
	  public VodContent(){}
	  

	//------------------------------------------------------------------------------
	  public ArrayList exeBrowserData(String keystb,String whereStmt, Vector params,
	                                   int fromRow, int noRows)
	  {

	    //Set language
	    VodContentSQL sql = new VodContentSQL(keystb);
	    sql.setSqlBrowser();

	    logger.info("SQL: "+ sql.getSqlBrowser() + whereStmt);

	    if (params != null) logger.info("param size: "+ params.size());

	    Vector vRS = broker.executeSelect(sql.getSqlBrowser() + whereStmt,
	                                      params, fromRow, noRows);
	    return fetchData(vRS);
	  }

//------------------------------------------------------------------------------
	  protected ArrayList fetchData(Vector vRS)
	  {
	    ArrayList aRet = new ArrayList();
	    String urlfilm;
	    VodCtnVO vo;
	    for (int i = 2; i < vRS.size(); i = i + 1)
	    {	
	      Vector vRow = (Vector)vRS.get(i);
	      vo = new VodCtnVO(); //new an instance of MODCtnVO
	      vo.setContentid(Integer.parseInt((String)vRow.get(0)));
	      vo.setServicecontentid(Integer.parseInt((String)vRow.get(1)));
	      vo.setSubjectid(Integer.parseInt((String)vRow.get(2)));
	      vo.setServicename((String)vRow.get(3));
	      vo.setProductor((String)vRow.get(4));
	      vo.setDirector((String)vRow.get(5));
	      vo.setActors((String)vRow.get(6));
	      vo.setPlot((String)vRow.get(7));
	      vo.setWriter((String)vRow.get(8));
	      vo.setMilisecond((String)vRow.get(9));
	      vo.setPoster((String)vRow.get(10));
	      vo.setStarnumber((String)vRow.get(11));	
	      vo.setUrlfilm((String)vRow.get(12));
	      vo.setPrice((String)vRow.get(13));
	      vo.setIunit((String)vRow.get(14));
	      
	      aRet.add(vo);
	    }
	    return aRet;
	  }
//----------------------------------------------------------------------------------------
	  public ArrayList getBrief(String stmt, Vector params, int fromRow, int noRows)
	  {
	    ArrayList arrRlt = new ArrayList();

	    //Execute query statement and get returned results
	    Vector vRS = broker.executeSelect(stmt, params, fromRow, noRows);


	    Vector aRow;
	    VodCtnBriefVO vo;
	    for (int i = 2; i < vRS.size(); i++)
	    {
	      aRow = (Vector)vRS.get(i);

	      vo = new VodCtnBriefVO();
	      vo.setModcontentId(Integer.parseInt((String)aRow.get(0)));
	      vo.setContentId(Integer.parseInt((String)aRow.get(1)));
	      vo.setModcontentName((String)aRow.get(2));
	      vo.setPicUrl((String)aRow.get(3));
	      vo.setActor((String)aRow.get(4));
	      vo.setDirector((String)aRow.get(5));
	      vo.setReleaseDate(DateHelper.parseDate((String)aRow.get(6)));
	      vo.setAveragePoint(Float.parseFloat((String)aRow.get(7)));
	      vo.setMilisecond(Long.parseLong((String)aRow.get(8)));

	      arrRlt.add(vo);
	    }

	    return (arrRlt);
	  }
//-------------------------------------------------------------------------------------------------------------------------------------
	  public ArrayList fetchDataSub(Vector vResultSet)
	  {
	    ArrayList rlt = new ArrayList(); //Collection of Results
	
	    for (int i = 2; i < vResultSet.size(); i++)
	    {
	      Vector aRow = (Vector)vResultSet.get(i);
	
	      VodSubjectVO vo = new VodSubjectVO();
	
	      vo.setSubjectid(Integer.parseInt((String)aRow.get(0))); //ID
	      vo.setSubjectname((String)aRow.get(1)); //name
	      vo.setPicUrl((String)aRow.get(2)); //Picurl
	      vo.setDescription((String)aRow.get(3)); //Desc
	      vo.setParentid(Integer.parseInt((String)aRow.get(4))); //Parentid
	      vo.setHold_subject("1".equals((String)aRow.get(5))); //Hold Subject
	      vo.setModContentCount(Integer.parseInt((String)aRow.get(6))); //ModContent Count 
	      rlt.add(vo);
	      logger.info(vo.toString());
	    }
	
	    return (rlt); //return result
	  }


	//-----------------------------------------------------------------------------------
	  public boolean isNoE(String str)
	  {
	    return (str == null || str.trim().length() == 0);
	  }
//------------------------------------------------------------------------------------
	  public boolean isNNoE(String str)
	  {
	    return (str != null && str.trim().length() != 0);
	  }
//-----------------------------------------------------------------------------------------------------------------------
	  public String loadDataFilmXML(Vector vRS)
	  {
		String aRet = "<ListFilm>\r\n";
		int Contentid,Servicecontentid,Subjectid;
	    String Servicename,Productor,Director,
	    	   Actors,Plot,Writer,Milisecond,Poster,Starnumber,Urlfilm,Price,Iunit;
	    
	    VodCtnVO vo;
	    
	    for (int i = 0; i < vRS.size(); i = i + 15)
	    {	      
	      vo = new VodCtnVO(); //new an instance of MODCtnVO
	      Contentid = Integer.parseInt((String)vRS.get(i));	      
	      Servicecontentid = Integer.parseInt((String)vRS.get(i + 1));
	      Subjectid = Integer.parseInt((String)vRS.get(i + 2));
	      Servicename = UnicodeConverter.decodeUnicode((String)vRS.get(i + 3));
	      Productor = (String)vRS.get(i + 4);
	      Director = (String)vRS.get(i + 5);
	      Actors = (String)vRS.get(i + 6);
	      Plot = (String)vRS.get(i + 7);
	      Writer = (String)vRS.get(i + 8);
	      Milisecond = (String)vRS.get(i + 9);
	      Poster = (String)vRS.get(i + 10);
	      Starnumber = (String)vRS.get(i + 11);	
	      Urlfilm = (String)vRS.get(i + 12);
	      Price = (String)vRS.get(i + 13);
	      Iunit = (String)vRS.get(i + 14);
	      
	      /*Productor = "nothing";
	      Director = "nothing";
	      Writer = "nothing";
	      Starnumber = "nothing";*/
	      
	      
	      aRet = aRet + "<item id = '" + Contentid + "'>\r\n";
	      aRet = aRet + "<servicecontentid>" + Servicecontentid + "</servicecontentid>\r\n";
	      aRet = aRet + "<subjectid>" + Subjectid + "</subjectid>\r\n";
	      aRet = aRet + "<servicename><![CDATA[" + UnicodeConverter.encodeUnicode(Servicename) + "]]></servicename>\r\n";
	      aRet = aRet + "<productor><![CDATA[" + Productor + "]]></productor>\r\n";
	      aRet = aRet + "<director><![CDATA[" + Director + "]]></director>\r\n";
	      aRet = aRet + "<actors><![CDATA[" + Actors + "]]></actors>\r\n";
	      aRet = aRet + "<plot><![CDATA[" + Plot + "]]></plot>\r\n";
	      aRet = aRet + "<writer><![CDATA[" + Writer + "]]></writer>\r\n";
	      aRet = aRet + "<milisecond>" + Milisecond + "</milisecond>\r\n";
	      aRet = aRet + "<poster>" + Poster + "</poster>\r\n";
	      aRet = aRet + "<starnumber><![CDATA[" + Starnumber + "]]></starnumber>\r\n";
	      aRet = aRet + "<urlfilm><![CDATA[" + Urlfilm + "]]></urlfilm>\r\n";
	      aRet = aRet + "<price>" + Price + "</price>\r\n";
	      aRet = aRet + "<iunit>" + Iunit + "</iunit>\r\n";
	      aRet = aRet + "<pg>13</pg>\r\n";
	      aRet = aRet + "</item>\r\n";
	      
	    }
	    aRet = aRet + "</ListFilm>";
	    return aRet;
	  }
	//------------------------------------------------------------------------------------------------------------------------
	  public String loadVodSubjectXML(Vector vResultSet)
	  {
		    String aRet = "<ListSubject>\r\n";		    
		    VodSubjectVO vo;
		    int subjecid;
		    String subjectname;
		    String url_image;
		    String pic_icon;
		    for (int i = 0; i < vResultSet.size(); i = i + 5)
		    {
		      vo = new VodSubjectVO();
		      
		      subjecid = Integer.parseInt((String)vResultSet.get(i));
		      subjectname = (String)vResultSet.get(i + 1);
		      subjectname = UnicodeConverter.decodeUnicode(subjectname);
		      if (subjectname == null) subjectname = "";
		      url_image = (String)vResultSet.get( i + 2);
		      String level = (String)vResultSet.get( i + 3);
		      pic_icon = (String)vResultSet.get( i + 4);
		      if (url_image == null) url_image = "";
		      aRet = aRet + "<item id = '" + subjecid + "'>\r\n";
		      aRet = aRet + "<subjectname><![CDATA["  + UnicodeConverter.encodeUnicode(subjectname) + "]]></subjectname>\r\n";
		      aRet = aRet + "<url_image>" + url_image + "</url_image>\r\n";
		      aRet = aRet + "<pic_icon>" + pic_icon + "</pic_icon>\r\n";
		      aRet = aRet + "<level>" + level + "</level>\r\n";
		      aRet = aRet + "</item>\r\n";
		    }
		    aRet = aRet + "</ListSubject>";
		  return aRet;  
	  }	
	//--------------------------------------ML-------------------------------------	  
	  public String loadDataUrlXML(Vector vResultSet)
	  {
		    String aRet = "<ListUrl>\r\n";		    
		    VodUrlVo vo;
		    String id;
		    String langid;
			String url;
			String name_lang;
		    for (int i = 0; i < vResultSet.size(); i = i + 4)
		    {
		      vo = new VodUrlVo();
		      id = (String)vResultSet.get(i);
		      langid = (String)vResultSet.get(i + 1);
		      url = (String)vResultSet.get(i + 2);
		      name_lang = (String)vResultSet.get(i + 3);		     
		      aRet = aRet + "<item id = '" + id + "'>\r\n";
		      aRet = aRet + "<langid>" + langid + "</langid>\r\n";
		      aRet = aRet + "<url_lang>" + url + "</url_lang>\r\n";
		      aRet = aRet + "<name_lang>" + name_lang + "</name_lang>\r\n";
		      aRet = aRet + "</item>\r\n";
		      
		    }
		    aRet = aRet + "</ListUrl>";
		  return aRet;  
	  }	  
//---------------------------------------------------------------------------	  
	  public ArrayList<VodUrlVo> loadDataUrl(Vector vResultSet)
	  {
		    ArrayList<VodUrlVo> aRet = new ArrayList<VodUrlVo>();		    
		    VodUrlVo vo;
			
		    for (int i = 0; i < vResultSet.size(); i = i + 4)
		    {
		      vo = new VodUrlVo();
		      vo.setId(Integer.parseInt((String)vResultSet.get(i)));
		      vo.setLangid(Integer.parseInt((String)vResultSet.get(i + 1)));
		      vo.setUrl((String)vResultSet.get(i + 2));
		      vo.setName_lang((String)vResultSet.get(i + 3));		     
		      
		      aRet.add(vo);
		    }
		  return aRet;  
	  }
}
