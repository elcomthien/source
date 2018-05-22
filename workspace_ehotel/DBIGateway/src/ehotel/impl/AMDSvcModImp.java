package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eMODDao;
import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDMod;

public class AMDSvcModImp implements AMDMod {
	private eMODDao eDao;

	public AMDSvcModImp() {
		eDao = new eMODDao();

	}

	@Override
	public int addSubject(Subject subject, int parentId) {
		return eDao.addSvcSubject(subject, parentId);
	}

	@Override
	public int editSubject(Subject subject, int langId) {
		return eDao.editSvcSubject(subject, langId);
	}

	public Subject getSubjectInfo(int subjId, int langid) {
		return eDao.getSvcSubjectInfo(subjId, langid);
	}

	@Override
	public int removeSubject(int subjId) {
		return eDao.removeSvcSubject(subjId);
	}

	@Override
	public boolean removeMods(int subjId, String strModId) {
		return eDao.removeModsOfSvc(subjId, strModId);
	}

	@Override
	public Vector<Mod> getMods(int subjId, int langid, int frRow, int toRow) {
		return eDao.getSvcMods(subjId, langid, frRow, toRow);
	}

	@Override
	public boolean updateMod(Mod mod, int langid) {
		return eDao.updateSvcMod(mod, langid);
	}

	@Override
	public boolean changeSubjectOfMod(int modId, String strSubjId) {
		// TODO Auto-generated method stub
		return eDao.changeSvcSubjectOfMod(modId, strSubjId);
	}

	@Override
	public Vector<Mod> searchMod(int subjId, String modName, int langid,
			int frRow, int toRow) {
		// TODO Auto-generated method stub
		return eDao.searchSvcMod(subjId, modName, langid, frRow, toRow);
	}

	@Override
	public Mod getModInfo(int modId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getModInfo(modId, langid, "SERVICE");
	}

	@Override
	public boolean addMod(int subjectId, String str_modId) {
		// TODO Auto-generated method stub
		return eDao.addSvcMod(subjectId, str_modId);
	}

	@Override
	public Vector<Subject> getSubjects(int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjects(langid, "SERVICE");
	}

	@Override
	public int countModOfSubject(int subjId) {
		// TODO Auto-generated method stub
		return eDao.countModOfSubject(subjId, "SERVICE");
	}

	@Override
	public Vector<Subject> getSubjectsOutMod(int modId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjectsOutMod(modId, langid, "SERVICE");
	}

	@Override
	public Vector<Subject> getSubjectsInMod(int modId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getSubjectsInMod(modId, langid, "SERVICE");
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
