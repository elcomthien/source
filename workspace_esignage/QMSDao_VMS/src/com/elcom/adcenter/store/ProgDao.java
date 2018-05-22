package com.elcom.adcenter.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.adcenter.broker.SubProParam;
import com.elcom.adcenter.broker.IMBroker;
import com.elcom.adcenter.cfg.ConfigurationLoader;
import com.elcom.adcenter.cfg.Configuration;




public class ProgDao {
	 //Refers the DB broker object
	  private static IMBroker broker = IMBroker.getInstance();
	  private static Configuration config = null;
	  //To log application	 
	  private static Logger log = Logger.getLogger(ProgDao.class);
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

	  private static final String pattern = "MM/dd/yyyy HH:mm:ss";
      
	  //=============================================
	  public ProgDao()
	  {
	  }
	 
	  //-------------------------------------------------------
	  private static String getStringGenerals(String query,Vector paramiv,int paramout)
	  {
		  String result = "";
		  int size = paramiv.size();
		  SubProParam out_data;
		 /* log.info("query: " + query + " | param size: " + size);
		  for(int ii = 0 ; ii < paramiv.size(); ii++)
		  {
			  log.info("pram " + ii + " = " + paramiv.get(ii));
		  }*/
		  
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
	  //-------------------------------------------------------
	  public String getStringContentGeneral(String query,String sernumber,String mac)
	  {
		  String result = "";
		  log.info(query + "sernumber: " + sernumber + " | mac: " + mac);
		  try{
			  Vector<SubProParam> params = new Vector<SubProParam>(3);
			  SubProParam param = null;		  
			  param = new SubProParam(new String(sernumber), SubProParam.IN);
			  params.add(param); 
		  
			  param = new SubProParam(new String(mac), SubProParam.IN);
			  params.add(param);
		  
			  SubProParam out_data = new SubProParam(new String(),SubProParam.OUT);
			  params.add(out_data);
		  
			  params = broker.executeSubPro(query,params);
			  out_data = (SubProParam) params.get(2);			  
			  result = (String)out_data.getValue();
		  }catch(Exception ex){ex.printStackTrace();
		      return "";
		  }
		  return result;
	  }
	  //-------------------------------------------------------	
	  
	  
	  
	  //-------------------------------------------------------
	  public String sp_qmsinsert_rawdata(String message_code,
			  String ngon_ngu,
			  String stt,
			  String ma_phim_nhan,
			  String ten_phim_nhan,
			  String ma_dich_vu,
			  String ten_dich_vu,
			  String loai_khach_hang,
			  String ma_quay_goi_stt,
			  String ten_quay_goi_stt,
			  String ma_nhan_vien_goi_stt,
			  String ten_nhan_vien_goi_stt,
			  String thoi_gian_lay_stt,
			  String thoi_gian_goi,
			  String thoi_gian_xong,
			  String ma_quay_chuyen_stt,
			  String ten_quay_chuyen_stt,
			  String chuoi_nhan_duoc)
	  {
		   
		  Vector param = new Vector(20);
		  param.add(0,message_code);	
		  param.add(1,ngon_ngu);
		  param.add(2,stt);
		  param.add(3,ma_phim_nhan);
		  param.add(4,ten_phim_nhan);
		  param.add(5,ma_dich_vu);
		  param.add(6,ten_dich_vu);
		  param.add(7,loai_khach_hang); 
		  param.add(8,ma_quay_goi_stt);
		  param.add(9,ten_quay_goi_stt);
		  param.add(10,ma_nhan_vien_goi_stt); 
		  param.add(11,ten_nhan_vien_goi_stt);
		  param.add(12,thoi_gian_lay_stt);
		  param.add(13,thoi_gian_goi);
		  param.add(14,thoi_gian_xong);
		  param.add(15,ma_quay_chuyen_stt);
		  param.add(16,ten_quay_chuyen_stt);
		  param.add(17,chuoi_nhan_duoc);
		  //System.out.println("totalsizefile: " + totalsizefile + " | ipClient: " + ipClient);
		  return getStringGenerals(SQL.sp_qmsinsert_rawdata,param,0);
	  }
	
	  public static void main (String arv[])
	  {
		  ProgDao a = new ProgDao();
		  /*a.sp_qmsinsert_rawdata("A", "0", "0", "0001", "00", "BII", 
				  "00", "BIBI", "1", "001", "01", "000", "00", 
				  "09/18/2015 14:00:00: 00", "09/18/2015 14:00:00: 00", "09/18/2015 14:00:00: 00");*/
	  }
}
