package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eLiveTVDao;
import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDLiveTV;

public class AMDCntLiveTVImp implements AMDLiveTV {
	private eLiveTVDao eDao;

	public AMDCntLiveTVImp() {
		eDao = new eLiveTVDao();
	}

	@Override
	public int addSubject(Subject subject, int parentId) {
		return eDao.addCntSubject(subject, parentId);
	}

	@Override
	public boolean removeSubject(int subjId) {
		// TODO Auto-generated method stub
		return eDao.removeCntSubject(subjId);
	}

	@Override
	public int editSubject(Subject subject, int langId) {
		// TODO Auto-generated method stub
		return eDao.editCntSubject(subject, langId);
	}

	@Override
	public Subject getSubjectInfo(int subjId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjectInfo(subjId, langid, "CONTENT");
	}

	@Override
	public Vector<Subject> getSubjects(int langid) {
		// TODO Auto-generated method stub
		return eDao.getCntSubjects(langid);
	}

	@Override
	public LiveTV getChannelInfo(int channelId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getChannelInfo(channelId, langid);
	}

	@Override
	public boolean removeChannel(int subjId, String str_channel_id) {
		// TODO Auto-generated method stub
		return eDao.removeChannel(subjId, str_channel_id, "CONTENT");
	}

	@Override
	public Vector<LiveTV> getChannels(int subjId, int langid, int from, int tto) {
		// TODO Auto-generated method stub
		return eDao.getChannels(subjId, langid, from, tto, "CONTENT");
	}

	@Override
	public boolean changeSubject(int livetvId, String str_subj_id) {
		// TODO Auto-generated method stub
		return eDao.changeSubject(livetvId, str_subj_id, "CONTENT");
	}

	@Override
	public Vector<LiveTV> searchChannel(int subjId, String liveName, int from,
			int tto) {
		// TODO Auto-generated method stub
		return eDao.searchChannel(subjId, liveName, from, tto, "CONTENT");
	}

	@Override
	public int countChannels(int subjId) {
		// TODO Auto-generated method stub
		return eDao.countChannels(subjId, "CONTENT");
	}

	@Override
	public boolean addLiveTV(int subjId, String str_channelId) {
		// TODO Auto-generated method stub
		return eDao.addLiveTV(subjId, str_channelId, "CONTENT");
	}

	@Override
	public boolean changStatus(int subjctId, int livetvId) {
		// TODO Auto-generated method stub
		return eDao.changStatus(subjctId, livetvId, "CONTENT");
	}

	@Override
	public int updateChannel(LiveTV channel, int langid) {
		// TODO Auto-generated method stub
		return eDao.updateChannel(channel, langid);
	}

	@Override
	public Vector<LiveTV> getChannelContentOutSuject(int subjectId, int langid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countSearchChannel(int subject, String liveName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkExistFile(String aFilePath) {
		// TODO Auto-generated method stub
		return eDao.checkExistFile(aFilePath);
	}

	@Override
	public boolean createFolder(String pathfolder) {
		// TODO Auto-generated method stub
		return eDao.createFolder(pathfolder);
	}

}
