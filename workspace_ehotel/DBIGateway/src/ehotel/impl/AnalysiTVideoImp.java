package ehotel.impl;

import ehotel.dao.AnalysiTVideoDao;
import ehotel.domain.liveTV.AnalysiLiveTV;
import ehotel.domain.mod.AnalysicMusic;
import ehotel.domain.vod.AnalysicVideo;
import ehotel.inter.ITVideoAnalysis;

public class AnalysiTVideoImp implements ITVideoAnalysis {
	private AnalysiTVideoDao eDao;

	public AnalysiTVideoImp() {
		eDao = new AnalysiTVideoDao();
	}

	@Override
	public int insertAnalysisLiveTV(AnalysiLiveTV liveTV) {
		return eDao.addAnalysisLiveTV(liveTV);
	}

	@Override
	public int insertAnalysicVideo(AnalysicVideo video) {
		return eDao.addAnalysicVideo(video);
	}

	@Override
	public void logError(String objCode, String content, String url_req,
			String type) {
		eDao.logError(objCode, content, url_req, type);
	}

	@Override
	public int insertAnalysicMusic(AnalysicMusic music) {
		return eDao.addAnalysicMusic(music);
	}

	@Override
	public boolean checkExist(String vasc_id, String type) {
		return eDao.checkExist(vasc_id, type);
	}

	@Override
	public int insertTrailer(String idVod, String urlPlay) {
		return eDao.insertTrailer(idVod, urlPlay);
	}

	@Override
	public boolean deleteTVideo(String vasc_id, String type) {
		return eDao.deleteVascData(vasc_id, type);
	}

	@Override
	public int updateVideo(AnalysicVideo video) {
		return eDao.updateVascVod(video);
	}

}
