package com.elcom.esignage.app.util;

public class SQL {
	public static final String sp_getChanngeTemplate = "{call sp_getChanngeTemplate(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String sp_getDownloadFileStb = "{call sp_getDownloadFileStb(?,?,?)}";
	public static final String sp_downloadComplate = "{call sp_downloadComplate(?,?,?,?,?)}";
	public static final String sp_getsuburldownload = "{call sp_getsuburldownload(?,?)}";
	public static final String sp_getCaptureCounterStb = "{call getCaptureCounterStb(?,?,?)}";
	public static final String sp_DelFileStb = "{call sp_DelFileStb(?,?,?)}";
	public static final String sp_getContentLayout = "{call sp_getContentLayout(?,?,?,?,?)}";
	public static final String sp_getInfoStb = "{call sp_getInfoStb(?,?,?)}";
	public static final String sp_getSchedule = "{call sp_getSchedule(?,?,?,?)}";
	public static final String sp_loginstb = "{call sp_loginstb(?,?,?)}";
	public static final String sp_regstb = "{call sp_regstb(?,?,?,?,?,?)}";
	public static final String sp_getSchedulePeri = "{call sp_getSchedulePeri(?,?,?)}";
	// public static final String getCaptureCounterStb = "{call getCaptureCounterStb(?,?,?)}";
	// public static final String getContentid = "{call sp_getContentid(?,?)}";
	// public static final String getsuburldownload = "{call sp_getsuburldownload(?,?)}";
	public static final String sp_getDefauleHome = "{call sp_getDefauleHome(?,?,?)}";
	public static final String sp_iptvSubject = "{call sp_iptvSubject(?,?)}";
	public static final String sp_iptvContentSubject = "{call sp_iptvContentSubject(?,?)}";
}
