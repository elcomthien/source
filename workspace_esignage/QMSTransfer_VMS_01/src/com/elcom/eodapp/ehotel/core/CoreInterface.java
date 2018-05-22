package com.elcom.eodapp.ehotel.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


import com.elcom.adcenter.store.ProgDao;
import com.elcom.eodapp.ehotel.store.ProgDaoOracle;
import com.elcom.eodapp.ehotel.utils.DAOFactory;


public class CoreInterface {
	
	Logger logger = Logger.getLogger(CoreInterface.class);
	ProgDao dao = DAOFactory.getInstance().getProgDao();
	ProgDaoOracle daoOracle = DAOFactory.getInstance().getProgDaoOracle();
		
    
	public void sp_qmsinsert_rawdata(String message_code,
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
		logger.info("Them du lieu vao Mysql");
		dao.sp_qmsinsert_rawdata(message_code,ngon_ngu, stt, ma_phim_nhan, ten_phim_nhan, ma_dich_vu, ten_dich_vu, loai_khach_hang, ma_quay_goi_stt, ten_quay_goi_stt, ma_nhan_vien_goi_stt, ten_nhan_vien_goi_stt, thoi_gian_lay_stt, thoi_gian_goi, thoi_gian_xong,ma_quay_chuyen_stt,ten_quay_chuyen_stt,chuoi_nhan_duoc);
		logger.info("Them du lieu vao Oracle");
		daoOracle.sp_qmsinsert_rawdata(message_code, ngon_ngu, stt, ma_phim_nhan, ten_phim_nhan, ma_dich_vu, ten_dich_vu, loai_khach_hang, ma_quay_goi_stt, ten_quay_goi_stt, ma_nhan_vien_goi_stt, ten_nhan_vien_goi_stt, thoi_gian_lay_stt, thoi_gian_goi, thoi_gian_xong,ma_quay_chuyen_stt,ten_quay_chuyen_stt, chuoi_nhan_duoc);
	}
	
	private String getTime() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}
	
}
