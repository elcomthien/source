package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eMODDao;
import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDMod;

public class AMDModImp implements AMDMod {
	private eMODDao eDao;

	public AMDModImp() {
		eDao = new eMODDao();

	}

	@Override
	public int addSubject(Subject subject, int parentId) {
		return eDao.addCntSubject(subject, parentId);
	}

	@Override
	public int editSubject(Subject subject, int langId) {
		return eDao.editCntSubject(subject, langId);
	}

	@Override
	public int removeSubject(int subjId) {
		return eDao.removeCntSubject(subjId);
	}

	public Subject getSubjectInfo(int subjId, int langid) {
		return eDao.getCntSubjectInfo(subjId, langid);
	}

	public Vector<Subject> getSubjects(int langid) {
		return eDao.getCntSubjects(langid);
	}

	@Override
	public Vector<Mod> getMods(int subjId, int langid, int frRow, int toRow) {
		return eDao.getCntMods(subjId, langid, frRow, toRow);
	}

	@Override
	public Mod getModInfo(int vodId, int langId) {
		return eDao.getModInfo(vodId, langId, "CONTENT");
	}

	@Override
	public boolean updateMod(Mod modInfo, int langid) {
		return eDao.updateCntMod(modInfo, langid);
	}

	@Override
	public boolean removeMods(int subjId, String strModId) {
		return eDao.removeModsOfSvc(subjId, strModId);
	}

	@Override
	public Vector<Mod> searchMod(int subjId, String modName, int langid,
			int frRow, int toRow) {
		return eDao.searchCntMod(subjId, modName, langid, frRow, toRow);
	}

	@Override
	public boolean changeSubjectOfMod(int modId, String str_subj_id) {
		// TODO Auto-generated method stub
		return eDao.changeCntSubjectOfVod(modId, str_subj_id);
	}

	@Override
	public boolean addMod(int subjId, String modId) {
		// TODO Auto-generated method stub
		return eDao.addSvcMod(subjId, modId);
	}

	@Override
	public int countModOfSubject(int subjId) {
		// TODO Auto-generated method stub
		return eDao.countModOfSubject(subjId, "CONTENT");
	}

	@Override
	public Vector<Subject> getSubjectsOutMod(int modId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjectsOutMod(modId, langid, "CONTENT");
	}

	@Override
	public Vector<Subject> getSubjectsInMod(int modId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjectsInMod(modId, langid, "CONTENT");
	}

	@Override
	public boolean changeStatus(int modId) {
		// TODO Auto-generated method stub
		return false;
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
