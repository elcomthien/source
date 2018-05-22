package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eVODDao;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;
import ehotel.inter.AMDVod;

public class AMDCntVodImp implements AMDVod {

	private eVODDao eDao;

	public AMDCntVodImp() {
		eDao = new eVODDao();
	}

	@Override
	public int addSubject(String subjName, String urlImage, int parentId) {
		return eDao.addCntSubject(subjName, urlImage, parentId);
	}

	@Override
	public boolean removeSubject(int subjId) {
		return eDao.removeCntSubject(subjId);
	}

	@Override
	public int editSubject(int subjId, String subjName, String urlImage,
			int langid) {
		return eDao.editCntSubject(subjId, subjName, urlImage, langid);
	}

	@Override
	public Subject getSubjectInfo(int subjId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjectInfo(subjId, langid, "CONTENT");
	}

	@Override
	public Vector<Subject> getSubjects(int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjects(langid, "CONTENT");
	}

	// dung chung
	@Override
	public Vod getVodInfo(int subjId, int vodId, int langid) {
		return eDao.getVodInfo(vodId, langid);
	}

	@Override
	public Vector<Vod> getVods(int subjId, int langid, int from, int tto) {
		return eDao.getCntVods(subjId, langid, from, tto);
	}

	@Override
	public boolean updateVod(Vod vod, int langid) {
		return eDao.updateCntVod(vod, langid);
	}

	@Override
	public Vector<Vod> searchVod(int subjId, String vodName, int langid,
			int from, int tto) {
		return eDao.searchCntVod(subjId, vodName, langid, from, tto);
	}

	public boolean changeCntSubjectOfVod(int vodId, int newSubjId) {
		return eDao.changeCntSubjectOfVod(vodId, newSubjId);
	}

	@Override
	public boolean addVod(int subjId, String vodId) {
		// TODO Auto-generated method stub
		return eDao.addSvcVod(subjId, vodId);
	}

	@Override
	public boolean removeVod(int subjId, String str_vod_id) {
		// TODO Auto-generated method stub
		return eDao.removeSvcVods(subjId, str_vod_id);
	}

	@Override
	public boolean changeSubjectOfVod(int vodId, int newSubjId) {
		// TODO Auto-generated method stub
		return eDao.changeCntSubjectOfVod(vodId, newSubjId);
	}

	@Override
	public boolean changeSubjectOfSvcVod(int vodId, String str_subj_id) {
		// TODO Auto-generated method stub
		return eDao.changeSvcSubjectOfVod(vodId, str_subj_id);
	}

	@Override
	public int countVodOfSubject(int subjId) {
		// TODO Auto-generated method stub
		return eDao.countVodOfSubject(subjId, "CONTENT");
	}

	@Override
	public boolean setVisileStatus(int vodId, int subjId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setNewReleased(int vodId, int subjId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<Subject> getSubjectsOutVod(int vodId, int langid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Subject> getSubjectsInVod(int vodId, int langid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<SubTitle> getSubtiles(int vodId) {
		// TODO Auto-generated method stub
		return eDao.getSubtitles(vodId);
	}

	@Override
	public boolean updateSubtitleLang(int subtitleId, int langId) {
		// TODO Auto-generated method stub
		return eDao.updateSubtitleLang(subtitleId, langId);
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

	@Override
	public Vector<Vod> getVascVods(int subj_cnt, int subj_svc, int langid,
			int frRow, int toRow) {
		// TODO Auto-generated method stub
		return eDao.getVascVods(subj_cnt, subj_svc, langid, frRow, toRow);
	}
}
