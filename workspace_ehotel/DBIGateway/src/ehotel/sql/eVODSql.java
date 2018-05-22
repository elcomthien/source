package ehotel.sql;

public class eVODSql {

	public static final String sqlAddSubject = "{call eVOD.addSubject(?,?,?,?,?)}";
	public static final String sqlRemoveSubject = "{call eVOD.removeSubject(?,?,?)}";
	public static final String sqlEditSubject = "{call eVOD.editSubject(?,?,?,?,?,?)}";
	public static final String sqlGetSubjectInfo = "{call eVOD.getSubjectInfo(?,?,?,?)}";
	public static final String sqlGetSubjects = "{call eVOD.getSubjects(?,?,?)}";

	public static final String sqlAddVod = "{call eVOD.addVod(?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String sqlAddSvcVod = "{call eVOD.addSvcVod(?,?,?)}";
	public static final String sqlUpdateVod = "{call eVOD.updateVod(?,?,?,?,?,?,?,?,?,?)}";
	public static final String sqlAddTrailer = "{call eVOD.addTrailer(?,?,?,?)}";
	public static final String sqlAddSubtitle = "{call eVOD.addSubtitle(?,?,?,?,?)}";
	public static final String sqlRemoveTrailer = "{call eVOD.removeTrailer(?,?)}";
	public static final String sqlRemoveSubtitle = "{call eVOD.removeSubtitle(?,?)}";
	public static final String sqlRemoveVod = "{call eVOD.removeVod(?,?)}";
	public static final String sqlRemoveSvcVod = "{call eVOD.removeSvcVod(?,?,?)}";
	public static final String sqlSetVisileStatus = "{call eVOD.setVisileStatus(?,?,?)}";
	public static final String sqlSetNewReleased = "{call eVOD.setNewReleased(?,?,?)}";

	public static final String sqlGetVodInfo = "{call eVOD.getVodInfo(?,?,?)}";
	public static final String sqlGetVodInfo_svc = "{call eVOD.getVodInfo_svc(?,?,?,?)}";
	public static final String sqlCountVod = "{call eVOD.countVod(?,?,?)}";
	public static final String sqlSearchVod = "{call eVOD.searchVod(?,?,?,?,?,?)}";
	public static final String sqlGetVodsOfSubject = "{call eVOD.getVods(?,?,?,?,?,?)}";
	public static final String sqlGetSubtitles = "{call eVOD.getSubtitles(?,?)}";
	public static final String sqlUpdateSubtitleLang = "{call eVOD.updateSubtitleLang(?,?,?)}";
	public static final String sqlChangeSubjectOfVod = "{call eVOD.changeSubjectOfVod(?,?,?)}";
	public static final String sqlChangeSvcSubjectOfVod = "{call eVOD.changeSvcSubjectOfVod(?,?,?)}";
	public static final String sqlGetOutInSubjects = "{call eVOD.getOutInSubject(?,?,?,?)}";
	public static final String sqlgetVascVods = "{call eVOD.getVascVods(?,?,?,?,?,?)}";

	public static final String sqlGetEVSStorages = "{call eVOD.getEVSStorages(?,?)}";
	public static final String sqlSetUrl = "{call eVOD.setURL(?,?,?)}";
	public static final String sqlGetUrl = "{call eVOD.getURL(?,?,?)}";
	public static final String sqlGetVodId = "{call eVOD.getVodId(?,?,?)}";
	public static final String sqlGetIDSubtitle = "{call eVOD.getIDSubtitle(?,?)}";
	public static final String sqlGetIDTrailer = "{call eVOD.getIDTrailer(?,?)}";
	public static final String sqlGetServers = "{call eVOD.getServers(?)}";
	public static final String sqlGetServerInfo = "{call eVOD.getServerInfo(?,?)}";
	public static final String sqlAddServer = "{call eVOD.addServer(?,?,?)}";

	// monitor syn stb-video
	public static final String sqlgetVodsOfStb = "{call eVOD.getVodsOfStb(?,?,?,?,?)}";
	public static final String sqlgetVods_UnSynCompleted = "{call eVOD.getVods_UnSynCompleted(?,?,?)}";
	public static final String sqladdSynVodSTB = "{call eVOD.addSynVodSTB(?,?,?)}";
	public static final String sqlsetSynStatus = "{call eVOD.setSynStatus(?,?,?)}";
	public static final String sqlgetVodsOutStb = "{call eVOD.getVodsOutStb(?,?,?,?)}";
	public static final String sqlinsertSynVodSTB = "{call eVOD.insertSynVodSTB(?,?,?,?,?)}";
	public static final String sqlcountVodOnSTB = "{call eVOD.countVodOnSTB(?,?)}";
	public static final String sqlgetSessionId = "{call eVOD.getSessionId(?,?,?)}";
	public static final String sqlsearchVodOnStb = "{call eVOD.searchVodOnStb(?,?,?,?)}";
	public static final String sqlgetFilePath = "{call eVOD.getURL(?,?,?)}";
	public static final String sqlgetIpStb = "{call eVOD.getIpStb(?,?)}";
	public static final String sqlgetEvs_storage = "{call eVOD.getEvs_storage(?,?)}";
}
