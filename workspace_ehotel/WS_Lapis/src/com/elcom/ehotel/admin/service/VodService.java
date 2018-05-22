package com.elcom.ehotel.admin.service;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.elcom.ehotel.admin.dao.VodDao;
import com.elcom.ehotel.admin.model.VodContentModel;
import com.elcom.ehotel.admin.model.VodPercentModel;
import com.elcom.ehotel.admin.model.VodSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;

import ehotel.core.FTPGatewayInterface;
import ehotel.core.FTPServerStruct;

public class VodService {
	private VodDao vodDao = new VodDao();

	public List<VodSubjectModel> getListSubject(String langid, String type) {
		return vodDao.getListSubjectVod(ConvertUtil.convertToInteger(langid), type);
	}

	public int addVodSubject(VodSubjectModel vod) {
		vod.setName(UnicodeConverter.encodeUnicode(vod.getName()));
		return vodDao.addVodSubject(vod);
	}

	public int editVodSubject(VodSubjectModel vod) {
		vod.setName(UnicodeConverter.encodeUnicode(vod.getName()));
		return vodDao.editVodSubject(vod);
	}

	public int deleteVodSubject(String idSubject) {
		return vodDao.deleteVodSubject(ConvertUtil.convertToInteger(idSubject));
	}

	public List<VodContentModel> getListContent(VodContentModel vod) {
		return vodDao.getListContent(vod);
	}

	public int addNewMovie(VodContentModel con, String uuid) {
		VodPercentModel per = new VodPercentModel();
		per.setNameview(con.getName());
		per.setFilename(con.getUrl());
		per.setUuid(uuid);
		vodDao.insertVodRemote(per);
		return vodDao.addNewMovie(con);
	}

	public int editMovie(VodContentModel con) {
		return vodDao.editMovie(con);
	}

	public int deleteMovie(String idContent, String uuid, String ipserver) {
		boolean flag = true;
		if (!uuid.equals("")) {
			try {
				FTPGatewayInterface ftpgateway;
				ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + ipserver + ":2099/elc_ftpgateway");
				flag = ftpgateway.StopUrl(UUID.fromString(uuid));
				if (flag) {
					vodDao.deleteVodRemotePercent(uuid);
					System.out.println("Stop transfer vod: " + idContent + " with uuid: " + uuid + " success!!!");
				} else
					System.out.println("Stop transfer vod: " + idContent + " with uuid: " + uuid + " unsuccess???");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vodDao.deleteMovie(ConvertUtil.convertToInteger(idContent));
	}

	public List<VodPercentModel> getListVodRemotePercent(String ipserver) {
		List<VodPercentModel> list = new ArrayList<VodPercentModel>();
		list = vodDao.getListVodRemotePercent();
		for (int i = 0; i < list.size(); i++) {
			try {
				UUID uuid = UUID.fromString(list.get(i).getUuid());
				FTPGatewayInterface ftpgateway;

				ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + ipserver + ":2099/elc_ftpgateway");
				if (ftpgateway.getStatus(uuid) == FTPServerStruct.STATUS_NOT_EXIST || ftpgateway.getStatus(uuid) == FTPServerStruct.STATUS_NONE) {
					deleteVodRemotePercent(list.get(i).getUuid());
					list.remove(i);
					i--;
				} else if (ftpgateway.getStatus(uuid) == FTPServerStruct.STATUS_TRANSFERING) {
					list.get(i).setStatus("0");
				} else if (ftpgateway.getStatus(uuid) == FTPServerStruct.STATUS_COMPLETED) {
					list.get(i).setStatus("1");
					deleteVodRemotePercent(list.get(i).getUuid());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int addVodRemotePercent(VodPercentModel per) {
		return vodDao.insertVodRemote(per);
	}

	public int deleteVodRemotePercent(String uuid) {
		return vodDao.deleteVodRemotePercent(uuid);
	}

	public List<HashMap<String, String>> getSubtitle(String vodId) {
		return vodDao.getSubtitle(ConvertUtil.convertToInteger(vodId));
	}

	public int addOrEditSubtitle(String vodId, String listSub) {
		return vodDao.addOrEditSubtitle(ConvertUtil.convertToInteger(vodId), listSub);
	}

	public int deleteSubtitle(String subId) {
		return vodDao.deleteSubtitle(ConvertUtil.convertToInteger(subId));
	}

	public static void main(String[] args) {
		// VodService v = new VodService();
		// System.out.println(v.getListVodRemotePercent());
	}

}
