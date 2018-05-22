package ehotel.test;

import ehotel.domain.liveTV.AnalysiLiveTV;
import ehotel.domain.mod.AnalysicMusic;
import ehotel.domain.vod.AnalysicVideo;
import ehotel.inter.ITVideoAnalysis;
import ehotel.render.DBIGateway;

public class TestSynTVideo {
	static ITVideoAnalysis dao = DBIGateway.getITVideoAnalysis();

	public static void test() {

		AnalysiLiveTV liveTV = new AnalysiLiveTV();
		liveTV.setChannelId("hoavk");
		liveTV.setChannelName("hoavk");
		liveTV.setChannelUrl("hoavk");
		dao.insertAnalysisLiveTV(liveTV);
	}

	public static void addVideo() {
		AnalysicVideo video = new AnalysicVideo();
		video.setSrc_id("100001");
		video.setTitle("demo");
		video.setActors("Hoavk");
		video.setDescription("demo");
		video.setDuration("1:10:20");
		video.setSubtitle("TiengViet");
		video.setTypes("Action");
		video.setUrl_video("rtsp://xxxx.xxxx.xxxx.xxxx:xxxx/vod/00000000020000046999.mpg?vcdnid=001&serveraddr=172.16.79.12:554");
		video.setUrl_trailer("rtsp://xxxx.xxxx.xxxx.xxxx:xxxx/vod/00000000020000047190.mpg?vcdnid=001&serveraddr=172.16.79.12:554");
		dao.insertAnalysicVideo(video);
	}

	public static void addMusic() {
		AnalysicMusic music = new AnalysicMusic();
		music.setContent_id("0000000003050000015400");
		music.setSong_name("7 years and 50 days");
		music.setSong_type("2");
		music.setSong_type_name("International");
		music.setUrl_play("rtsp://xxxx.xxxx.xxxx.xxxx:xxxx/vod/00000000020000010255.mpg?vcdnid=001&serveraddr=172.16.79.12:554");
		music.setUrl_req("GET /iptvepg/frame31/musicauthchoice.jsp?columnid=070300&programid=0000000003050000020692&ContentID=0000000003050000020692&programid=0000000003050000020692&vodtype=-1&FatherContent=0000000003050000020692&CategoryID=070300&ContentType=8&programName=All%20time%20love&leefocus=llinker307 HTTP/1.1");
		music.setDes_page(1);
		dao.insertAnalysicMusic(music);
	}

	public static void addTrailer() {
		dao.insertTrailer("100001", "hoavk");
	}

	public static void main(String[] arg) {
		TestSynTVideo.addMusic();

	}
}
