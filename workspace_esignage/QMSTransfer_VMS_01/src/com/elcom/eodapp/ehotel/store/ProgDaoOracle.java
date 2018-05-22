package com.elcom.eodapp.ehotel.store;

import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;
import com.elcom.eodapp.ehotel.broker.*;
import com.elcom.eodapp.ehotel.cfg.Configuration;
import com.elcom.eodapp.ehotel.cfg.ConfigurationLoader;

public class ProgDaoOracle {
	private static IMBroker broker = IMBroker.getInstance();
	private static final Logger logger = LogManager.getLogger(ProgDaoOracle.class);
	private static final String pattern = "MM/dd/yyyy HH:mm:ss";
	private static Configuration config = null;

	static {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		config = loader.getConfiguration();
	}

	public ProgDaoOracle() {
	}
      
	 
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
		  String rerult = "";
		  Vector params = new Vector(19);
		  SubProParam param = null;
		  Vector<String> outParam = new Vector<String>();
		  param = new SubProParam(message_code, SubProParam.IN);
		  params.add(param); // 0 IN
		  param = new SubProParam(ngon_ngu, SubProParam.IN);
		  params.add(param); // 1 IN
		  param = new SubProParam(stt, SubProParam.IN);
		  params.add(param); // 2 IN
		  param = new SubProParam(ma_phim_nhan, SubProParam.IN);
		  params.add(param); // 3 IN
		  param = new SubProParam(ten_phim_nhan, SubProParam.IN);
		  params.add(param); // 4 IN
		  param = new SubProParam(ma_dich_vu, SubProParam.IN);
		  params.add(param); // 5 IN
		  param = new SubProParam(ten_dich_vu, SubProParam.IN);
		  params.add(param); // 6 IN
		  param = new SubProParam(loai_khach_hang, SubProParam.IN);
		  params.add(param); // 7 IN
		  param = new SubProParam(ma_quay_goi_stt, SubProParam.IN);
		  params.add(param); // 8 IN
		  param = new SubProParam(ten_quay_goi_stt, SubProParam.IN);
		  params.add(param); // 9 IN
		  param = new SubProParam(ma_nhan_vien_goi_stt, SubProParam.IN);
		  params.add(param); // 10 IN
		  param = new SubProParam(ten_nhan_vien_goi_stt, SubProParam.IN);
		  params.add(param); // 11 IN
		  param = new SubProParam(thoi_gian_lay_stt, SubProParam.IN);
		  params.add(param); // 12 IN
		  param = new SubProParam(thoi_gian_goi, SubProParam.IN);
		  params.add(param); // 13 IN
		  param = new SubProParam(thoi_gian_xong, SubProParam.IN);
		  params.add(param); // 14 IN
		  param = new SubProParam(ma_quay_chuyen_stt, SubProParam.IN);
		  params.add(param); // 15 IN
		  param = new SubProParam(ten_quay_chuyen_stt, SubProParam.IN);
		  params.add(param); // 16 IN
		  param = new SubProParam(chuoi_nhan_duoc, SubProParam.IN);
		  params.add(param); // 17 IN
		  SubProParam OUT = new SubProParam(new String(), SubProParam.OUT);
		  params.add(OUT); // 18
		  
		  params = broker.executeSubPro(SQL.sp_qmsinsert_rawdata, params);
		  if (params != null && params.size() > 0) {
			   SubProParam paramOUT = (SubProParam) params.get(18);
		       rerult = (paramOUT.getString()).trim();
		  
		  }
		  return rerult;
	  }
	
	  public static void main (String arv[])
	  {
		
		  /*a.sp_qmsinsert_rawdata("A", "0", "0", "0001", "00", "BII", 
				  "00", "BIBI", "1", "001", "01", "000", "00", 
				  "09/18/2015 14:00:00: 00", "09/18/2015 14:00:00: 00", "09/18/2015 14:00:00: 00");*/
	  }
}
