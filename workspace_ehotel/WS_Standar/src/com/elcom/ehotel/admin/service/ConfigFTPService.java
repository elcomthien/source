package com.elcom.ehotel.admin.service;

import java.util.ArrayList;
import java.util.List;

import com.elcom.ehotel.admin.dao.ConfigFTPDao;
import com.elcom.ehotel.admin.model.ConfigFTPModel;
import com.elcom.ehotel.admin.model.FileModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.Ftp4jClient;

public class ConfigFTPService {
	private ConfigFTPDao configFTPDao = new ConfigFTPDao();

	public ConfigFTPModel getConfigFTP() {
		return configFTPDao.getConfigFTP();
	}

	public int editConfigFTP(ConfigFTPModel ftp) {
		return configFTPDao.editFTPConfig(ftp);
	}

	public List<FileModel> getListFileFTP(String type) {
		List<FileModel> list = new ArrayList<FileModel>();
		ConfigFTPModel ftp = configFTPDao.getConfigFTP();
		Ftp4jClient f = new Ftp4jClient(ftp.getHost(),
				ConvertUtil.convertToInteger(ftp.getPort()), ftp.getUser(),
				ftp.getPass());
		f.connect();
		String path = "";
		list = f.getListFileFilterType(path, type);
		f.close();
		return list;
	}

	public List<String> getListFolderFTP(String path) {
		List<String> list = new ArrayList<String>();
		ConfigFTPModel ftp = configFTPDao.getConfigFTP();
		Ftp4jClient f = new Ftp4jClient(ftp.getHost(),
				ConvertUtil.convertToInteger(ftp.getPort()), ftp.getUser(),
				ftp.getPass());
		f.connect();
		list = f.getListFolder(path);
		f.close();
		return list;
	}
	
	public static void main(String[] args) {
		ConfigFTPService c= new ConfigFTPService();
		System.out.println(c.getListFileFTP("video"));
	}
}
