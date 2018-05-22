package ehotel.sql;

public class eMODSql {

	public static final String sqlAddSubject = "{call eMOD.addSubject(?,?,?,?,?,?)}";
	public static final String sqlRemoveSubject = "{call eMOD.removeSubject(?,?,?)}";
	public static final String sqlEditSubject = "{call eMOD.editSubject(?,?,?,?,?,?,?)}";
	public static final String sqlGetSubjectInfo = "{call eMOD.getSubjectInfo(?,?,?,?)}";
	public static final String sqlGetSubjects = "{call eMOD.getSubjects(?,?,?)}";

	public static final String sqlAddMod = "{call eMOD.addMod(?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String sqlAddSvcMod = "{call eMOD.addSvcMod(?,?,?)}";
	public static final String sqlUpdateMod = "{call eMOD.updateMod(?,?,?,?,?,?,?,?,?)}";
	public static final String sqlRemoveSvcMod = "{call eMOD.removeSvcMod(?,?,?)}";
	public static final String sqlRemoveMod = "{call eMOD.removeMod(?,?)}";

	public static final String sqlGetModInfo = "{call eMOD.getModInfo(?,?,?,?)}";
	public static final String sqlGetMods = "{call eMOD.getMods(?,?,?,?,?,?)}";
	public static final String sqlCountMod = "{call eMOD.countMod(?,?,?)}";
	public static final String sqlSearchMod = "{call eMOD.searchMod(?,?,?,?,?,?,?)}";
	public static final String sqlGetModsOfSubject = "{call eMOD.getModsOfSubject(?,?,?,?,?,?)}";
	public static final String sqlChangeSubjectOfMod = "{call eMOD.changeSubjectOfMod(?,?,?,?)}";
	public static final String sqlGetOutSubjects = "{call eMOD.getOutSubject(?,?,?,?)}";
	public static final String sqlGetINSubjects = "{call eMOD.getInSubject(?,?,?,?)}";

	public static final String sqlSetUrl = "{call eMOD.setURL(?,?,?)}";
	public static final String sqlSetClip = "{call eMOD.setClip(?,?,?)}";

}
