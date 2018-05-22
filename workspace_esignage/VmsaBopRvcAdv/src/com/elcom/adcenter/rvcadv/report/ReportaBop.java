package com.elcom.adcenter.rvcadv.report;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.adcenter.rvcadv.broker.IMBroker;
import com.elcom.adcenter.rvcadv.broker.SubProParam;
import com.elcom.adcenter.rvcadv.cfg.Configuration;
import com.elcom.adcenter.rvcadv.cfg.ConfigurationLoader;
import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.util.DateHelper;

public class ReportaBop {
	//Refers the DB broker object
	  private static IMBroker broker = IMBroker.getInstance();
	  private static Configuration config = null;
	  //To log application	 
	  private static Logger log = Logger.getLogger(ReportaBop.class);
	  //Read configuration params
	  static
	  {
		  try{			 
			 ConfigurationLoader loader = ConfigurationLoader.getInstance();
			 config = loader.getConfiguration();
			 Properties props = new Properties();
			 FileInputStream fileinputstream = new FileInputStream("Config/log4j.properties");
			 props.load(fileinputstream);
			 PropertyConfigurator.configure(props);
		  }catch (IOException ex) {
			  log.error(null, ex);
		  }
	  }
	//==============================================
		 public String getStringGenerals(String query,Vector paramiv,int paramout)
		  {
			  String result = "";
			  int size = paramiv.size();
			  SubProParam out_data;
			  log.info("query: " + query + " | param size: " + size);
			  try{
				  Vector<SubProParam> params;
				  if (paramout == 1)
				  {
					  params = new Vector<SubProParam>(size + 1);
				  }else
					  params = new Vector<SubProParam>(size);
				  SubProParam param = null;		  
				  for (int i = 0;i < size ; i ++)
				  {
					  String para = (String)paramiv.get(i);
					  System.out.println(para);
					  param = new SubProParam(new String(para), SubProParam.IN);
					  params.add(i,param);			  
				  }
				  if (paramout == 1)
				  {
					  out_data = new SubProParam(new String(),SubProParam.OUT);
					  params.add(paramiv.size(),out_data);
				  }
			  
				  params = broker.executeSubPro(query,params);
				  
				  if (paramout == 1)
				  {
					  out_data = (SubProParam) params.get(size);			  
					  result = (String)out_data.getValue();		
				  }
			  }catch(Exception ex){ex.printStackTrace();
			      return "";
			  }
			  return result;
		  } 
		
	//============================================================
		 public String adminReportLayout(String xmlparamter)
			{
			/**
			 * <parameter><groupname>-1</groupname><createdatestart>-1</createdatestart><createdatestop></createdatestop></parameter>
			 * -1,-1 All
			 * ...,0 tim theo group
			 * 0,... tim theo create
			 * ...,... tim theo gorup, createdate
			 */
			 String result = "1";
			 Vector param = new Vector(3);
			 String groupname = DateHelper.utilStringXml(xmlparamter, SQL.groupname);	
			 String createdatestart = DateHelper.utilStringXml(xmlparamter, SQL.createdatestart);
			 String createdatestop = DateHelper.utilStringXml(xmlparamter, SQL.createdatestop);
			 param.add(0,groupname);	
			 param.add(1,createdatestart);
			 param.add(2,createdatestop);
					
			 result = getStringGenerals(SQL.sp_adminReportLayout,param,1);
			 return result;
			}
    //============================================================
		 public String adminReportContentGroup()
			{
				String result = "1";
				Vector param = new Vector(1);
				String para = "";				
				param.add(0,para);		
					
				result = getStringGenerals(SQL.sp_adminReportContentGroup,param,1);
				return result;
			}
	//============================================================
		 public String adminReportContentAll(String xmlparamter)
		 {
			/**
			 * <parameter><groupname>-1</groupname></parameter>	
			 */
			String result = "1";
			Vector param = new Vector(1);
			String groupname = DateHelper.utilStringXml(xmlparamter, SQL.groupname);				
			param.add(0,groupname);		
				
			result = getStringGenerals(SQL.sp_adminReportContentAll,param,1);
			return result;
		 }	
	//============================================================
		 public String adminReportPlaylist(String xmlparamter)
		 {
			 /**
				 * <parameter><groupname>-1</groupname><createdatestart>-1</createdatestart><createdatestop></createdatestop></parameter>
				 * -1,-1 All
				 * ...,0 tim theo group
				 * 0,... tim theo create
				 * ...,... tim theo gorup, createdate
				 */
			 String result = "1";
			 Vector param = new Vector(3);
			 String groupname = DateHelper.utilStringXml(xmlparamter, SQL.groupname);	
			 String createdatestart = DateHelper.utilStringXml(xmlparamter, SQL.createdatestart);
			 String createdatestop = DateHelper.utilStringXml(xmlparamter, SQL.createdatestop);
			 param.add(0,groupname);	
			 param.add(1,createdatestart);
			 param.add(2,createdatestop);
					
				result = getStringGenerals(SQL.sp_adminReportPlaylist,param,1);
				return result;
		}
	//============================================================
		 public String adminReportDaily(String xmlparamter)
		{
			 /**
				 * <parameter><groupname>-1</groupname><createdatestart>-1</createdatestart><createdatestop></createdatestop></parameter>	
			*/
			 String result = "1";
			 Vector param = new Vector(3);
			 String groupname = DateHelper.utilStringXml(xmlparamter, SQL.groupname);	
			 String createdatestart = DateHelper.utilStringXml(xmlparamter, SQL.createdatestart);
			 String createdatestop = DateHelper.utilStringXml(xmlparamter, SQL.createdatestop);
			 param.add(0,groupname);	
			 param.add(1,createdatestart);
			 param.add(2,createdatestop);		
					
			result = getStringGenerals(SQL.sp_adminReportDaily,param,1);
			return result;
		}
	//============================================================
		 public String adminReportSchedulePeriod(String xmlparamter)
			{
			 	/**
				 * <parameter><groupname>-1</groupname></parameter>	
				 */
			 	String result = "1";
				Vector param = new Vector(1);
				String groupname = DateHelper.utilStringXml(xmlparamter, SQL.groupname);					
				param.add(0,groupname);		
					
				result = getStringGenerals(SQL.sp_adminReportSchedulePerio,param,1);
				return result;
			}
		//============================================================
		 public String sp_adminReportStb(String xmlparamter)
			{
			 	/**
				 * <parameter><groupname>-1</groupname></parameter>	
				 */
			 	String result = "1";
				Vector param = new Vector(1);
				String groupname = DateHelper.utilStringXml(xmlparamter, SQL.groupname);					
				param.add(0,groupname);		
					
				result = getStringGenerals(SQL.sp_adminReportStb,param,1);
				return result;
			}	 
}
