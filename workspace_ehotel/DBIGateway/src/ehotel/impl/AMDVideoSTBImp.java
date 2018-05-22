package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eVideoSTBDao;
import ehotel.domain.pms.eSTB;
import ehotel.domain.vod.Vod;
import ehotel.inter.IVideoSTB;

public class AMDVideoSTBImp implements IVideoSTB {
	eVideoSTBDao eDao;

	public AMDVideoSTBImp() {
		eDao = new eVideoSTBDao();
	}

	@Override
	public Vector<eSTB> getSTBList() {
		// TODO Auto-generated method stub
		return eDao.getSTBList();
	}

	@Override
	public Vector<Vod> getVodsOfStb(String serial_num, int langid, int from,
			int tto) {
		// TODO Auto-generated method stub
		return eDao.getVodsInStb(serial_num, langid, from, tto);
	}

	@Override
	public Vector<Vod> getVods_UnSynCompleted(String serial_num, int langid) {
		// TODO Auto-generated method stub
		return eDao.getVods_UnSynCompleted(serial_num, langid);
	}

	@Override
	public boolean insertSynVodSTB(String serial_num, int vod_id,
			String session_core_id, String file_size) {
		// TODO Auto-generated method stub
		return eDao.insertSynVodSTB(serial_num, vod_id, session_core_id,
				file_size);
	}

	@Override
	public boolean setSynStatus(String serial_num, String session_id) {
		return eDao.setSynStatus(serial_num, session_id);
	}

	@Override
	public Vector<Vod> getVodsOutStb(int subjectid, String serial_num,
			int langid) {
		return eDao.getVodsOutStb(subjectid, serial_num, langid);
	}

	@Override
	public String getSessionId(String serial_num, int vod_id) {
		return eDao.getSessionId(serial_num, vod_id);
	}

	@Override
	public int countVodOnSTB(String serial_num) {
		return eDao.countVodOnSTB(serial_num);
	}

	@Override
	public Vector<Vod> searchVodOnStb(String serial_num, String searchCode,
			int langid) {
		return eDao.searchVodOnStb(serial_num, searchCode, langid);
	}

	@Override
	public String getFilePath(int vod_id) {
		return eDao.getFilePath(vod_id);
	}

	@Override
	public String getIpStb(String serial_num) {
		return eDao.getIpStb(serial_num);
	}

	@Override
	public String getEvs_storage(String protocol) {
		// TODO Auto-generated method stub
		return eDao.getEvs_storage(protocol);
	}

}
