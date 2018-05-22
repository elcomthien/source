package ehotel.dao;

public class eLiveTVSql {
	public static final String sqlAddSubject = "{call eLiveTV.addSubject(?,?,?,?)}";
	public static final String sqlRemoveSubject = "{call eLiveTV.removeSubject(?,?,?)}";
	public static final String sqlEditSubject = "{call eLiveTV.editSubject(?,?,?,?,?)}";
	public static final String sqlGetSubjectInfo = "{call eLiveTV.getSubjectInfo(?,?,?,?)}";
	public static final String sqlGetSubjects = "{call eLiveTV.getSubjects(?,?,?)}";

	public static final String sqlAddChannel = "{call eLiveTV.addChannel(?,?,?,?)}";
	public static final String sqlGetOvsSevers = "{call eLiveTV.etOvsSevers(?)}";
}
