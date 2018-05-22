package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eVODDao;
import ehotel.domain.vod.EVSStorage;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Trailer;
import ehotel.domain.vod.Vod;
import ehotel.inter.IEVS;

public class EVSVodImp implements IEVS {
	private eVODDao eDao;

	public EVSVodImp() {
		eDao = new eVODDao();
	}

	@Override
	public int addVod(int subjectId, int storageId, String url_mutilcast,
			Vod movie) {
		// TODO Auto-generated method stub
		return eDao.addVod(subjectId, storageId, url_mutilcast, movie);
	}

	@Override
	public int addTrailer(int vodId, Trailer trailer) {
		// TODO Auto-generated method stub
		return eDao.addTrailer(vodId, trailer);
	}

	@Override
	public Integer[] addSubtitle(int vodId, Vector<SubTitle> subTitle) {
		// TODO Auto-generated method stub
		return eDao.addSubtitle(vodId, subTitle);
	}

	@Override
	public boolean removeTrailer(int trailerId) {
		return eDao.removeTrailer(trailerId);
	}

	@Override
	public boolean removeSubtitle(int subtileId) {
		return eDao.removeSubtitle(subtileId);
	}

	@Override
	public boolean removeVod(int vodId) {
		return eDao.removeVod(vodId);
	}

	@Override
	public Vector<EVSStorage> getEVSStorages(int vodId) {

		return eDao.getEVSStorages(vodId);
	}

	@Override
	public boolean setUrl(int vodId, String url) {
		// TODO Auto-generated method stub
		return eDao.setUrl(vodId, url);
	}

	@Override
	public String getUrl(int seqId, String vodInfo) {
		// TODO Auto-generated method stub
		return eDao.getUrl(seqId, vodInfo);
	}

	@Override
	public int getVodId(int seqId, String vodInfo) {
		// TODO Auto-generated method stub
		return eDao.getVodId(seqId, vodInfo);
	}

	@Override
	public int[] getIDSubtitle(int vodId) {
		// TODO Auto-generated method stub
		return eDao.getIDSubtitle(vodId);
	}

	@Override
	public int getIDTrailer(int vodId) {
		// TODO Auto-generated method stub
		return eDao.getIDTrailer(vodId);
	}

}
