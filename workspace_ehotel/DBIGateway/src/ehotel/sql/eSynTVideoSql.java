package ehotel.sql;

public class eSynTVideoSql {
	// phan connect mytv
	public static final String sqllogError = "{call tvideo_syn.logError(?,?,?,?)}";
	public static final String sqlcheckExist = "{call tvideo_syn.checkExist(?,?,?)}";
	public static final String sqlupdateAnalysicVideo = "{call tvideo_syn.updateAnalysicVideo(?,?,?,?,?)}";
	public static final String sqlinsertTrailer = "{call tvideo_syn.addUrlTrailer(?,?,?)}";
	public static final String sqldeleteVascData = "{call tvideo_syn.deleteVascData(?,?,?)}";
	public static final String sqladdAnalysisLiveTV = "{call tvideo_syn.addAnalysisLiveTV(?,?,?,?)}";
	public static final String sqladdAnalysicMusic = "{call tvideo_syn.addAnalysicMusic(?,?,?,?,?,?,?,?)}";
	public static final String sqladdAnalysicVideo = "{call tvideo_syn.addAnalysicVideo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
}
