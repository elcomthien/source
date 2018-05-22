package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eLiveTVDao;
import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDLiveTV;

public class AMDSvcLiveTVImp implements AMDLiveTV {

	private eLiveTVDao eDao;

	public AMDSvcLiveTVImp() {
		eDao = new eLiveTVDao();
	}

	@Override
	public int addSubject(Subject subjName, int parentId) {
		// TODO Auto-generated method stub
		return eDao.addSvcSubject(subjName, parentId);
	}

	@Override
	public boolean removeSubject(int subjId) {
		// TODO Auto-generated method stub
		return eDao.removeSvcSubject(subjId);
	}

	@Override
	public int editSubject(Subject subject, int langId) {
		// TODO Auto-generated method stub
		return eDao.editSvcSubject(subject, langId);
	}

	@Override
	public Subject getSubjectInfo(int subjId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjectInfo(subjId, langid, "SERVICE");
	}

	@Override
	public Vector<Subject> getSubjects(int langid) {
		// TODO Auto-generated method stub
		return eDao.getSvcSubjects(langid);
	}

	@Override
	public Vector<LiveTV> getChannels(int subjId, int langid, int from, int tto) {
		return eDao.getChannels(subjId, langid, from, tto, "SERVICE");
	}

	@Override
	public boolean addLiveTV(int subjId, String str_channelId) {
		return eDao.addLiveTV(subjId, str_channelId, "SERVICE");
	}

	@Override
	public LiveTV getChannelInfo(int channelId, int langid) {
		return eDao.getChannelInfo(channelId, langid);
	}

	@Override
	public int updateChannel(LiveTV channel, int langid) {
		return eDao.updateChannel(channel, langid);
	}

	@Override
	public boolean removeChannel(int subjId, String str_channel_id) {
		return eDao.removeChannel(subjId, str_channel_id, "SERVICE");
	}

	@Override
	public boolean changeSubject(int livetvId, String str_subj_id) {
		// TODO Auto-generated method stub
		return eDao.changeSubject(livetvId, str_subj_id, "SERVICE");
	}

	@Override
	public Vector<LiveTV> searchChannel(int subjId, String liveName, int from,
			int tto) {
		// TODO Auto-generated method stub
		return eDao.searchChannel(subjId, liveName, from, tto, "SERVICE");
	}

	@Override
	public int countChannels(int subjId) {
		// TODO Auto-generated method stub
		return eDao.countChannels(subjId, "SERVICE");
	}

	@Override
	public boolean changStatus(int subjctId, int livetvId) {
		// TODO Auto-generated method stub
		return eDao.changStatus(subjctId, livetvId, "SERVICE");
	}

	@Override
	public Vector<LiveTV> getChannelContentOutSuject(int subjectId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getChannelContentOutSuject(subjectId, langid);
	}

	@Override
	public int countSearchChannel(int subjId, String liveName) {
		// TODO Auto-generated method stub
		return eDao.countSearchChannel(subjId, liveName);
	}

	@Override
	public boolean checkExistFile(String aFilePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createFolder(String pathfolder) {
		// TODO Auto-generated method stub
		return false;
	}

}
