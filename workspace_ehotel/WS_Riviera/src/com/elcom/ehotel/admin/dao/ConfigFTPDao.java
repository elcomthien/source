package com.elcom.ehotel.admin.dao;

import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.ConfigFTPModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class ConfigFTPDao {
	@SuppressWarnings("unchecked")
	public ConfigFTPModel getConfigFTP() {
		ConfigFTPModel config = new ConfigFTPModel();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_FTP_CONFIG, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		LogUtil.logDao(ConfigFTPDao.class.toString(), SQL.GET_FTP_CONFIG, params, "none", outParam.size());
		
		config.setHost(outParam.get(0));
		config.setPort(outParam.get(1));
		config.setUser(outParam.get(2));
		config.setPass(outParam.get(3));
		return config;
	}
	
	@SuppressWarnings("unchecked")
	public int editFTPConfig(ConfigFTPModel ftp){
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(ftp.getHost()), 0);
		params.add(in);
		in = new SubProParam(new String(ftp.getPort()), 0);
		params.add(in);
		in = new SubProParam(new String(ftp.getUser()), 0);
		params.add(in);
		in = new SubProParam(new String(ftp.getPass()), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.UPDATE_FTP_CONFIG, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		LogUtil.logDao(ConfigFTPDao.class.toString(), SQL.UPDATE_FTP_CONFIG, params, "host,port,user,pass", rs);
		
		return rs;
	}
	
	public static void main(String[] args) {
		ConfigFTPDao c = new ConfigFTPDao();
		System.out.println(c.getConfigFTP());
	}
}
