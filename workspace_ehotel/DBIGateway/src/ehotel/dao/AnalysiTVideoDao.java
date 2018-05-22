package ehotel.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.liveTV.AnalysiLiveTV;
import ehotel.domain.mod.AnalysicMusic;
import ehotel.domain.vod.AnalysicVideo;
import ehotel.sql.eSynTVideoSql;
import ehotel.utils.Utils;

public class AnalysiTVideoDao extends eBaseDao {
	private static Logger log = Logger.getLogger(AnalysiTVideoDao.class);

	// phan doc du lieu tu mytv
	public int addAnalysisLiveTV(AnalysiLiveTV liveTV) {
		int ret = -1;
		if (liveTV != null) {

			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam subPro = new SubProParam(liveTV.getChannelId(),
					SubProParam.IN);
			params.add(subPro);

			subPro = new SubProParam(liveTV.getChannelName(), SubProParam.IN);
			params.add(subPro);

			subPro = new SubProParam(liveTV.getChannelUrl(), SubProParam.IN);
			params.add(subPro);

			SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
			params.add(subOut);

			try {
				params = executeSubPro(eSynTVideoSql.sqladdAnalysisLiveTV,
						params);
				if (params != null && params.size() > 0) {
					SubProParam paramOUT = (SubProParam) params.get(3);
					ret = Utils.parseInt(paramOUT.getString().trim());
				}
				String outScreen = "[-->addAnalysisLiveTV  with params: ["
						+ liveTV.toString() + "-->returnValue=" + ret + "]";
				log.info(outScreen);
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}
		return ret;
	}

	public int addAnalysicVideo(AnalysicVideo video) {
		int ret = -1;
		if (video != null) {
			if (video.getTitle() == null) {
				logError(video.getSrc_id(), "NOT GET INFO",
						video.getUrl_request(), "VIDEO");
				return ret;
			}
			System.out.println(video.toString());
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam subPro = new SubProParam(video.getSrc_id(),
					SubProParam.IN);
			params.add(subPro);// 1

			System.out.println("Title=" + video.getTitle());
			subPro = new SubProParam(video.getTitle(), SubProParam.IN);
			params.add(subPro);// 2

			String description = "";
			if (video.getDescription() != null)
				description = video.getDescription();
			subPro = new SubProParam(description, SubProParam.IN);
			params.add(subPro);// 3

			String urlVideo = "";
			if (video.getUrl_video() != null)
				urlVideo = video.getUrl_video();
			subPro = new SubProParam(urlVideo, SubProParam.IN);
			params.add(subPro);// 4

			String actor = "";
			if (video.getActors() != null)
				actor = video.getActors();
			subPro = new SubProParam(actor, SubProParam.IN);
			params.add(subPro);// 5

			String director = "";
			if (video.getDirectors() != null)
				director = video.getDirectors();
			subPro = new SubProParam(director, SubProParam.IN);
			params.add(subPro);// 6

			String duration = "";
			if (video.getDuration() != null)
				duration = video.getDuration();
			subPro = new SubProParam(duration, SubProParam.IN);
			params.add(subPro);// 7

			String typeVideo = "";
			if (video.getTypes() != null)
				typeVideo = video.getTypes();
			subPro = new SubProParam(typeVideo, SubProParam.IN);
			params.add(subPro);// 8

			String country = "";
			if (video.getCountry() != null)
				country = video.getCountry();
			subPro = new SubProParam(country, SubProParam.IN);
			params.add(subPro);// 9

			String language = "";
			if (video.getLanguages() != null)
				language = video.getLanguages();
			subPro = new SubProParam(language, SubProParam.IN);
			params.add(subPro);// 10

			String subtitle = "";
			if (video.getSubtitle() != null)
				subtitle = video.getSubtitle();
			subPro = new SubProParam(subtitle, SubProParam.IN);
			params.add(subPro);// 11

			String level_ = "";
			if (video.getLevels() != null)
				level_ = video.getLevels();
			subPro = new SubProParam(level_, SubProParam.IN);
			params.add(subPro);// 12

			String url_req = "-100";
			if (video.getUrl_request() != null)
				url_req = video.getUrl_request();
			subPro = new SubProParam(url_req, SubProParam.IN);
			params.add(subPro);// 13

			String columid = "-100";
			if (video.getColumnid() != null)
				columid = video.getColumnid();
			subPro = new SubProParam(columid, SubProParam.IN);
			params.add(subPro);// 14

			String trailer = "-100";
			if (video.getUrl_trailer() != null)
				trailer = video.getUrl_trailer();
			subPro = new SubProParam(trailer, SubProParam.IN);
			params.add(subPro);// 15

			String VODType = "VOD";
			if (video.getVodType() != null)
				VODType = video.getVodType();
			subPro = new SubProParam(VODType, SubProParam.IN);
			params.add(subPro);// 16

			SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
			params.add(subOut);// 17

			try {
				params = executePro(eSynTVideoSql.sqladdAnalysicVideo, params);
				if (params != null && params.size() > 0) {
					SubProParam paramOUT = (SubProParam) params.get(16);
					ret = Utils.parseInt(paramOUT.getString().trim());
				}
				String outScreen = "[-->addAnalysicVideo  with params: ["
						+ video.toString() + "-->returnValue=" + ret + "]";
				log.info(outScreen);
			} catch (Exception ex) {
				log.error(ex.getMessage());
				ex.printStackTrace();
			}
		}
		return ret;
	}

	public void logError(String objCode, String content, String req, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(objCode, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(content, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(req, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);
		try {
			params = executeSubPro(eSynTVideoSql.sqllogError, params);
			String outScreen = eSynTVideoSql.sqllogError + " with params: ["
					+ objCode + ",content= " + content + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public int addAnalysicMusic(AnalysicMusic music) {
		int ret = -1;
		if (music != null) {
			if (music.getSong_name() == null) {
				if (music.getSong_name() == null
						|| music.getContent_id() == null) {
					logError(music.getContent_id(), "NOT GET INFO",
							music.getUrl_req(), "MUSIC");
					return ret;
				}
			}
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam subPro = new SubProParam(music.getContent_id(),
					SubProParam.IN);
			params.add(subPro);// 0

			subPro = new SubProParam(music.getSong_name(), SubProParam.IN);
			params.add(subPro);// 1

			subPro = new SubProParam(music.getSong_type(), SubProParam.IN);
			params.add(subPro);// 2

			String Song_type_name = "";
			if (music.getSong_type_name() != null)
				Song_type_name = music.getSong_type_name();
			subPro = new SubProParam(Song_type_name, SubProParam.IN);
			params.add(subPro);// 3

			subPro = new SubProParam(music.getUrl_play(), SubProParam.IN);
			params.add(subPro);// 4

			String url_req = "";
			if (music.getUrl_req() != null)
				url_req = music.getUrl_req();
			subPro = new SubProParam(url_req, SubProParam.IN);
			params.add(subPro);// 5

			subPro = new SubProParam(new java.math.BigDecimal(
					music.getDes_page()), SubProParam.IN);
			params.add(subPro);// 6

			SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
			params.add(subOut);// 7
			try {
				params = executePro(eSynTVideoSql.sqladdAnalysicMusic, params);
				if (params != null && params.size() > 0) {
					SubProParam paramOUT = (SubProParam) params.get(7);
					ret = Utils.parseInt(paramOUT.getString().trim());
				}
				String outScreen = "[-->addAnalysicMusic  with params: ["
						+ music.toString() + "-->returnValue=" + ret + "]";
				log.info(outScreen);
			} catch (Exception ex) {
				log.error(ex.getMessage());
				ex.printStackTrace();
			}
		}
		return ret;
	}

	public boolean checkExist(String vasc_id, String type) {
		boolean ischeck = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(vasc_id, SubProParam.IN);
		params.add(subPro);// 0

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executePro(eSynTVideoSql.sqlcheckExist, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == -1)
					ischeck = false;
			}
			String outScreen = "-->checkExist  with params: [vasc_id="
					+ vasc_id + ",type=" + type + "-->returnValue=" + ischeck
					+ "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		return ischeck;
	}

	public int insertTrailer(String idVod, String urlPlay) {
		int ret = -1;
		if (urlPlay == null)
			return ret;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(idVod, SubProParam.IN);
		params.add(subPro);// 0

		subPro = new SubProParam(urlPlay, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = executePro(eSynTVideoSql.sqlinsertTrailer, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				ret = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[-->addUrlTrailer  with params: [idVod" + idVod
					+ ",urlPlay=" + urlPlay + "-->returnValue=" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
		return ret;

	}

	public boolean deleteVascData(String vasc_id, String type) {
		boolean ischeck = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(vasc_id, SubProParam.IN);
		params.add(subPro);// 0

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executePro(eSynTVideoSql.sqldeleteVascData, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == -1)
					ischeck = false;
			}
			String outScreen = "-->deleteVascData  with params: [vasc_id="
					+ vasc_id + ",type=" + type + "-->returnValue=" + ischeck
					+ "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		return ischeck;
	}

	public int updateVascVod(AnalysicVideo video) {
		int ret = -1;
		if (video != null) {
			if (video.getTitle() == null) {
				logError(video.getSrc_id(), "NOT GET INFO",
						video.getUrl_request(), "VIDEO");
				return ret;
			}
			System.out.println(video.toString());
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam subPro = new SubProParam(video.getSrc_id(),
					SubProParam.IN);
			params.add(subPro);// 1

			System.out.println("Title=" + video.getTitle());
			subPro = new SubProParam(video.getTitle(), SubProParam.IN);
			params.add(subPro);// 2

			String urlVideo = "";
			if (video.getUrl_video() != null)
				urlVideo = video.getUrl_video();
			subPro = new SubProParam(urlVideo, SubProParam.IN);
			params.add(subPro);// 3

			String trailer = "-100";
			if (video.getUrl_trailer() != null)
				trailer = video.getUrl_trailer();
			subPro = new SubProParam(trailer, SubProParam.IN);
			params.add(subPro);// 4

			SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
			params.add(subOut);// 5

			try {
				params = executePro(eSynTVideoSql.sqlupdateAnalysicVideo,
						params);
				if (params != null && params.size() > 0) {
					SubProParam paramOUT = (SubProParam) params.get(4);
					ret = Utils.parseInt(paramOUT.getString().trim());
				}
				String outScreen = "[-->"
						+ eSynTVideoSql.sqlupdateAnalysicVideo
						+ " with params: [" + video.toString()
						+ "-->returnValue=" + ret + "]";
				log.info(outScreen);
			} catch (Exception ex) {
				log.error(ex.getMessage());
				ex.printStackTrace();
			}
		}
		return ret;
	}
}
