package ehotel.inter;

import ehotel.domain.liveTV.AnalysiLiveTV;
import ehotel.domain.mod.AnalysicMusic;
import ehotel.domain.vod.AnalysicVideo;

public interface ITVideoAnalysis {

	public int insertAnalysisLiveTV(AnalysiLiveTV liveTV);

	public int insertAnalysicVideo(AnalysicVideo video);

	public int insertAnalysicMusic(AnalysicMusic music);

	public void logError(String objId, String content, String url_req,
			String type);

	public int insertTrailer(String idVod, String urlPlay);

	/**
	 * 
	 * @param vasc_id
	 * @param type
	 *            :VOD or MOD
	 * @return
	 */
	public boolean checkExist(String vasc_id, String type);

	public boolean deleteTVideo(String vasc_id, String type);

	public int updateVideo(AnalysicVideo video);
}
