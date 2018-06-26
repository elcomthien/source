package com.elcom.eodapp.media.cas;

public interface SQL {
	public static final String sqllogineHotel = "begin ? := vod.logineHotel(?,?); end;";
	public static final String sqlgetNameFolio = "begin ? := vod.getNameFolio(?); end;";
	public static final String sqlcheckFolio = "begin vod.checkFolio(?); end;";
	public static final String sqlsetFolioStb = "begin ? := vod.setFolioStb(?,?,?); end;";
	public static final String sqlsetFolioStbVasc = "begin ? := vod.setFolioStbVasc(?,?,?,?,?); end;";
	public static final String getIpStb = "begin ? := vod.getIpStb(?,?); end;";
	public static final String sqlsetLang = "begin ? := vod.setLang(?,?); end;";
	public static final String sqlgetInfoVasc = "begin vod.getInfoVasc(?,?); end;";
	public static final String sqlstbftpapk = "begin vod.stbftpapk(?,?); end;";
	public static final String sqlupdatestatusstbftpapk = "begin vod.updatestatusstbftpapk(?,?); end;";
	public static final String sqlcheckpay = "begin ? := vod.checkpay(?,?,?); end;";
	public static final String sqlgetLangs = "begin ? := vod.getLangs(?); end;";
	public static final String sqlupdateIPbox = "begin ? := vod.updateIPbox(?,?); end;";
	public static final String sqlgethelp = "begin ? := vod.getHelp(?); end;";
	public static final String sqlgetConfigInfo = "begin ? := vod.getConfigInfo(?); end;";	
}