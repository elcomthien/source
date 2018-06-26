package com.elcom.eodapp.media.mod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.media.broker.IMBroker;
import com.elcom.eodapp.media.common.ModSubject;
import com.elcom.eodapp.media.common.ModVO;
import com.elcom.eodapp.media.util.DateHelper;


public class ModContent {
	//Refers the DB broker object
	  private static IMBroker broker = IMBroker.getInstance();
	  //To log application
	  private static final Logger logger = LogManager.getLogger(ModContent.class);
	  private static final String pattern = "MM/dd/yyyy HH:mm:ss";

	  public ModContent(){}
	  
//------------------------------------------------------------------
public static int postedOnetHouseKeeping(String smartcard, String pinCode,
			   int icode, int IQty, int menuId, String orderTime,
			   String orderDate, String guestName, int checkNo) {
	String sqlPostedOnetHouseKeeping = "{call pmsapp.requestOneItemHouseKeeping(?,?,?,?,?,?,?,?,?,?)}";		  
	int ret = 0;

			  Vector<SubProParam> params = new Vector<SubProParam>();

			  SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
			  params.add(subIn); // 1

			  subIn = new SubProParam(pinCode, SubProParam.IN);
			  params.add(subIn); // 2

			  subIn = new SubProParam(new java.math.BigDecimal(icode), SubProParam.IN);
			  params.add(subIn); // 3

			  subIn = new SubProParam(new java.math.BigDecimal(IQty), SubProParam.IN);
			  params.add(subIn); // 4

			  subIn = new SubProParam(new java.math.BigDecimal(menuId),
			    SubProParam.IN);
			  params.add(subIn); // 5

			  
			  subIn = new SubProParam(orderTime, SubProParam.IN);
			  params.add(subIn); // 6

			  
			  subIn = new SubProParam(orderDate, SubProParam.IN);
			  params.add(subIn); // 7

			  subIn = new SubProParam(guestName, SubProParam.IN);
			  params.add(subIn); // 8

			  subIn = new SubProParam(new java.math.BigDecimal(checkNo),
			    SubProParam.IN);
			  params.add(subIn); // 9

			  SubProParam subISeq = new SubProParam(new String(), SubProParam.OUT);
			  params.add(subISeq); // 10

			  String outScreen = ("[postedOnetHouseKeeping with params [smartcard="
			    + smartcard + ",pinCode=" + pinCode + ",icode=" + icode
			    + "IQty=" + IQty + ",menuId=" + menuId + ", ordertime="
			    + orderTime + ",orderdate=" + orderDate + ",guestName="
			    + guestName + "checkNo=" + checkNo + "]");

			  try {
			   params = broker.executeSubPro(sqlPostedOnetHouseKeeping, params);
			 
			   // if (params != null && params.size() > 0) {
			   // SubProParam paramOUT = (SubProParam) params.get(9);
			   // String tmp = (paramOUT.getString()).trim();
			   //
			   // ret = Utils.parseInt(tmp);
			   // log.info("Request ICode=" + icode + " is complete!(Alert="
			   // + tmp + ")");
			   // }
			  
			  } catch (Exception e) {
			   e.printStackTrace();
			   
			  }

			  return ret;

			 }
	  
//==================================================================	  
	  public String getModSubject(String keystb){
		  String result_vRS = "";
		  Vector params = new Vector(1);
		    
		  SubProParam keystb_ = new SubProParam(new String(keystb),SubProParam.IN);
		  params.add(keystb_);
		    
		  SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
		  params.add(out_data);	
		    
		  params = broker.executeSubPro(SQL.sqlSubjectAlbum,params);		   
		  out_data = (SubProParam)params.get(1);
		  Vector vRs = out_data.getVector();		    
		  result_vRS = loadDataSubjectXML(vRs);

		  return result_vRS;
	  }
	  
//-----------------------------------------------------------------------------------
public String getModListSongSubject(String keystb,int subjectid,int from,int num){
   	String result_vRS = "";
    Vector vRs = new Vector();
    Vector params = new Vector(5);

    SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
    params.add(ketstb_);
    
    SubProParam subjectid_ = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
    params.add(subjectid_);	      

    SubProParam from_ = new SubProParam(new BigDecimal(from), SubProParam.IN);
	params.add(from_);
	
	SubProParam num_ = new SubProParam(new BigDecimal(num), SubProParam.IN);
	params.add(num_);
	
	SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
	params.add(out_data);
	
	params = broker.executeSubPro(SQL.sqlgetModListSongSubject,params);
	out_data = (SubProParam) params.get(4);
	vRs = out_data.getVector();
    result_vRS = loadDataSongAlbumXML(vRs);
	return result_vRS;
}
//--------------------------------------------------------------------------------------
public String getModFavorite(String keystb,int from,int num){
   	String result_vRS = "";
    Vector vRs = new Vector();
    Vector params = new Vector(5);

    SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
    params.add(ketstb_);     

    SubProParam from_ = new SubProParam(new BigDecimal(from), SubProParam.IN);
	params.add(from_);
	
	SubProParam num_ = new SubProParam(new BigDecimal(num), SubProParam.IN);
	params.add(num_);
	
	SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
	params.add(out_data);
	
	params = broker.executeSubPro(SQL.sqlgetModFavorite,params);
	out_data = (SubProParam) params.get(4);
	vRs = out_data.getVector();
    result_vRS = loadDataSongAlbumXML(vRs);
	return result_vRS;
}
//--------------------------------------------------------------------------------------
public int countMusic(int subjectid)
{	
    Vector<SubProParam> params = new Vector<SubProParam>(2);

    SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
    params.add(out_data);
    
    SubProParam subjectid_ = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
    params.add(subjectid_);
    
    params  = broker.executeSubPro(SQL.sqlCountMusic, params);
    
    //Get data returned by the stored procedure
    SubProParam paramOUT = (SubProParam)params.get(0);
    int retVal = Integer.parseInt(paramOUT.getString());
    return retVal;
}
//-------------------------------------------------------------------------------------
public String getUrlImageBack(int subjectid)
{
	String urlimage;

    Vector<SubProParam> params = new Vector<SubProParam>(2);

    SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
    params.add(out_data);
    
    SubProParam subjectid_ = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
    params.add(subjectid_);
    
    params  =  broker.executeSubPro(SQL.sqlGetUrlImageBack, params);
    
    //Get data returned by the stored procedure
    SubProParam paramOUT = (SubProParam)params.get(0);
    urlimage = paramOUT.getString();
    return urlimage;
}

//--------------------------------------------------------------------------------------
public int addModFavorite(String keystb,int contentid)
{
	Vector vRs = new Vector();
    Vector params = new Vector(3);

    SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
    params.add(out_data);
    
    SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
    params.add(ketstb_);
    
    SubProParam contentid_ = new SubProParam(new BigDecimal(contentid), SubProParam.IN);
    params.add(contentid_);
    
    params  = broker.executeSubPro(SQL.sqladdModFavorite, params);
    
    //Get data returned by the stored procedure
    SubProParam paramOUT = (SubProParam)params.get(0);
    int retVal = Integer.parseInt(paramOUT.getString());
    return retVal;
}
//-------------------------------------------------------------------------------------
public int removeFavorite(String keystb,int contentid)
{
	Vector vRs = new Vector();
    Vector params = new Vector(3);
    
    SubProParam out_data = new SubProParam(new String(), SubProParam.OUT);
    params.add(out_data);
    
    SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
    params.add(ketstb_);
    
    SubProParam contentid_ = new SubProParam(new BigDecimal(contentid), SubProParam.IN);
    params.add(contentid_);
    
    params = broker.executeSubPro(SQL.sqlremoveFavorite, params);
    //Get data returned by the stored procedure
    SubProParam paramOUT = (SubProParam)params.get(0);
    int retVal = Integer.parseInt(paramOUT.getString());
    return retVal;
}
	//---------------------------------------------------------------------------------------------------------------------------
	  private String loadDataSubjectXML(Vector vRs){
		    int idSubject;
		    String nameSubject;
		    String urlpic;
		    String def;
		    int status;
		    ModSubject subject;
		    String tmp_vRs = "<?xml version='1.0' encoding='UTF-8'?>\r\n<ListSubject>\r\n";
		    		    
		    if (vRs.size() != 0)
		    {
		      for (int i = 0 ; i < vRs.size() ; i = i + 5)
		      {		    	
		        idSubject = new Integer(((String)vRs.get(i)).trim()).intValue();
		        nameSubject = (String)vRs.get(i + 1);
		        nameSubject = UnicodeConverter.decodeUnicode(nameSubject);
		        def = (String)vRs.get(i + 2);
		        urlpic = (String)vRs.get(i + 3);
		        status = new Integer(((String)vRs.get(i + 4)).trim()).intValue();                
		        subject = new ModSubject();
		        
		        tmp_vRs = tmp_vRs + "<item id = '" + idSubject + "'>\r\n";
		        //tmp_vRs = tmp_vRs + "<subjectname>" + UnicodeConverter.encodeUnicode(nameSubject) + "</subjectname>\r\n";
		        tmp_vRs = tmp_vRs + "<subjectname><![CDATA[" + UnicodeConverter.encodeUnicode(nameSubject) + "]]></subjectname>\r\n";
		        tmp_vRs = tmp_vRs + "<desc>" + def + "</desc>\r\n";
		        tmp_vRs = tmp_vRs + "<url_image>" + urlpic + "</url_image>\r\n";
		        tmp_vRs = tmp_vRs + "<status>" + status + "</status>\r\n";
		        tmp_vRs = tmp_vRs + "</item>\r\n";
		       }
		    }
		    tmp_vRs = tmp_vRs + "</ListSubject>";
		    return tmp_vRs;
		  }
	//--------------------------------------------------------------------------------------------------------------------------
	  private String loadDataSongAlbumXML(Vector vRs)
	  {
		  int contentid;
		  String title;
		  String duration;
		  String bitrate;
		  String length;
		  String composer;
		  String album;
		  String singer;
		  String lyric;
		  String url;
	    
	    String tmp_vRs = "<?xml version='1.0' encoding='UTF-8'?>\r\n<ListSong>\r\n";	    
	    if (vRs.size() != 0)
	    {
	      for (int i = 0; i < vRs.size(); i = i + 9) {
	    	contentid = new Integer( ( (String) vRs.get(i)).trim()).intValue();
	    	title = (String) vRs.get(i + 1);
	    	title = UnicodeConverter.decodeUnicode(title);
	    	duration = (String) vRs.get(i + 2);	    	
	    	length = (String) vRs.get(i + 3);
	    	composer = (String) vRs.get(i + 4);
	    	album = (String) vRs.get(i + 5);
	    	singer = (String) vRs.get(i + 6);
	    	lyric = (String) vRs.get(i + 7);
	    	url = (String) vRs.get(i + 8);
	    	
	    	tmp_vRs = tmp_vRs + "<item id = '" + contentid + "'>\r\n";
	    	//tmp_vRs = tmp_vRs + "<title>" + UnicodeConverter.encodeUnicode(title) + "</title>\r\n";
	        tmp_vRs = tmp_vRs + "<title><![CDATA[" + UnicodeConverter.encodeUnicode(title) + "]]></title>\r\n";
	        tmp_vRs = tmp_vRs + "<duration>" + duration + "</duration>\r\n";
	        tmp_vRs = tmp_vRs + "<length>" + length + "</length>\r\n";
	        tmp_vRs = tmp_vRs + "<composer>" + composer + "</composer>\r\n";
	        tmp_vRs = tmp_vRs + "<album>" + album + "</album>\r\n";
	        tmp_vRs = tmp_vRs + "<singer>" + UnicodeConverter.decodeUnicode(singer) + "</singer>\r\n";
	        tmp_vRs = tmp_vRs + "<lyric><![CDATA[" + lyric + "]]></lyric>\r\n";
	        tmp_vRs = tmp_vRs + "<url><![CDATA[" + url + "]]></url>\r\n";
	        tmp_vRs = tmp_vRs + "</item>\r\n";
	      }
	    }
	    tmp_vRs = tmp_vRs + "</ListSong>";
	      return tmp_vRs;
	  }	  
}
