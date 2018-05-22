package com.elcom.adcenter.store;

public interface SQL {
	String sernumber = "2001";
	String mac = "00:00:01";
	static final String getChanngeTemplate = "SELECT COUNT(*) as number FROM Ad_LayoutContent WHERE statusPush = 1 AND Ad_Layout_idLayout IN (" + 
											 " SELECT idLayout FROM Ad_Layout WHERE Ad_Group_idGroup IN (" + 
		                                     " SELECT Ad_Group_idGroup FROM Ad_Stb WHERE seriNumber = '" + sernumber + "' AND mac = '" + mac + "' AND status_2 = 1" +
											 "))";
	static final String sp_getChanngeTemplate = "{call sp_getChanngeTemplate(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	static final String sp_getContentLayout = "{call sp_getContentLayout(?,?,?,?,?)}";
	static final String sp_getInfoStb = "{call sp_getInfoStb(?,?,?)}";
	static final String sp_getSchedule = "{call sp_getSchedule(?,?,?,?)}";
	static final String sp_loginstb = "{call sp_loginstb(?,?,?)}";
	static final String sp_regstb = "{call sp_regstb(?,?,?,?,?,?)}";
	static final String sp_getSchedulePeri = "{call sp_getSchedulePeri(?,?,?)}";
	static final String sp_getDownloadFileStb = "{call sp_getDownloadFileStb(?,?,?)}";
	static final String sp_downloadComplate = "{call sp_downloadComplate(?,?,?,?,?)}";
	static final String sp_DelFileStb = "{call sp_DelFileStb(?,?,?)}";
	static final String getCaptureCounterStb = "{call getCaptureCounterStb(?,?,?)}";
	static final String getContentid = "{call sp_getContentid(?,?)}";
	static final String getsuburldownload = "{call sp_getsuburldownload(?,?)}";
	
	
	//Dung cho HN
	static final String sp_getDefauleHome = "{call sp_getDefauleHome(?,?,?)}";
	//Dung cho eBop lay du lieu IPTV tu database MySql.
	static final String sp_iptvSubject = "{call sp_iptvSubject(?,?)}";
	static final String sp_iptvContentSubject = "{call sp_iptvContentSubject(?,?)}";
	static final String sp_updateIpAdress = "{call sp_updateIpAdress(?,?,?)}";
	
}
