package ehotel.sql;

public class eLiveTVSql {
	public static final String sqlAddSubject = "{call eLiveTV.addSubject(?,?,?,?,?)}";
	public static final String sqlRemoveSubject = "{call eLiveTV.removeSubject(?,?,?)}";
	public static final String sqlEditSubject = "{call eLiveTV.editSubject(?,?,?,?,?,?)}";
	public static final String sqlGetSubjectInfo = "{call eLiveTV.getSubjectInfo(?,?,?,?)}";
	public static final String sqlGetSubjects = "{call eLiveTV.getSubjects(?,?,?)}";

	public static final String sqladdLiveTV = "{call eLiveTV.addChannel(?,?,?,?)}";
	public static final String sqleditChannel = "{call eLiveTV.editChannel(?,?,?,?,?)}";
	public static final String sqlgetChannelInfo = "{call eLiveTV.getChannelInfo(?,?,?)}";
	public static final String sqlgetChannels = "{call eLiveTV.getChannels(?,?,?,?,?,?)}";
	public static final String sqlremoveChannel = "{call eLiveTV.removeChannel(?,?,?,?)}";
	public static final String sqlchangeSubject = "{call eLiveTV.changeSubject(?,?,?,?)}";
	public static final String sqlsearchChannel = "{call eLiveTV.searchChannel(?,?,?,?,?)}";
	public static final String sqlcountChannels = "{call eLiveTV.countChannels(?,?,?)}";
	public static final String sqlchangStatus = "{call eLiveTV.changStatus(?,?,?,?)}";
	public static final String sqlgetLiveTVContentOutSuject = "{call eLiveTV.getLiveTVContentOutSuject(?,?,?)}";
	public static final String sqlcountSearchChannel = "{call eLiveTV.countSearchChannel(?,?,?)}";

}
