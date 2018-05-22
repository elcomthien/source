package ehotel.impl;

import ehotel.dao.eMODDao;
import ehotel.domain.mod.Mod;
import ehotel.inter.IEAS;

public class EASImp implements IEAS {
	private eMODDao eDao;

	public EASImp() {
		eDao = new eMODDao();
	}

	@Override
	public int addMod(int subjId, Mod mod, int langid) {
		// TODO Auto-generated method stub
		return eDao.addMod(subjId, mod, langid);
	}

	@Override
	public boolean removeMod(int modId) {
		// TODO Auto-generated method stub
		return eDao.removeEASMod(modId);
	}

	@Override
	public Mod getModInfo(int modId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getModInfo(modId, langid, "CONTENT");
	}

	@Override
	public boolean setUrl(int modId, String url) {
		// TODO Auto-generated method stub
		return eDao.setUrl(modId, url);
	}

	@Override
	public boolean setClip(String fileName, int subjId, int vodiId) {
		// TODO Auto-generated method stub
		return eDao.setClip(fileName, subjId, vodiId);
	}

}
