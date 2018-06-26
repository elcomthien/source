package com.elcom.eodapp.media.service;

public interface Command {
	public static String command = "command";
	//com cho vod 
	public static int com_getlistsubjectvod = 1;
	public static int com_getlistfileofsubject = 2;
	public static int com_getdetailfilm = 3;
	public static int com_getountilm = 4;
	public static int com_getlisturlsub = 5;
	public static int com_getwelcomelink = 6;
	//com cho mod
	public static int com_getmodsubject = 11;
	public static int com_getModListSongSubject = 12;
	public static int com_getountmusic = 13;
	public static int com_getUrlImageBack = 14;
	public static int com_chargeVod = 15;
	public static int com_checkpaid = 16;
	//com cho livetv
	public static int com_getlivesubject = 21;
	public static int com_getlivechannel = 22;
	public static int com_getlivecount = 23;
	public static int com_getlivesubjectphugia = 25;
	public static int com_getChannelLivetivi = 26;
	//com danh cho phan cas
	public static int com_getlang = 31;
	public static int com_getlogin = 32;
	public static int com_getreg = 33;
	public static int com_static = 35;
	public static int com_getLangs = 46;
	public static int com_stbftpapk = 47;
	public static int com_updatestatusstbftpapk = 48;
	public static int com_gethelp = 49;
	//them cho phan welcome khach
	public static int com_getwelcome = 34;
	//them cho phan dong bo gio cho cac stb khong the nho thoi gian
	public static int com_gettiem = 36;
	//Cac chuc nang danh cho Record
	public static int com_setScheduleStb = 37;
	public static int com_getListRecordStb = 38;
	public static int com_delScheduleStb = 39;
	public static int com_updateStatusStbRecord = 40;
	public static int com_getListRecordCore = 41;
	public static int com_updateLinkStbRecord = 42;
	public static int com_updateSizeStbRecord = 43;
	public static int com_updateStatusCore = 44;
	public static int com_delScheduleCore = 45;
	//Cac chuc nang danh cho pms
	public static int com_getMessageInfo = 100;
	public static int com_getConfigInfo = 101;
	public static int com_getLinkPromotion = 102;
	public static int com_getLocations = 103;
	public static int com_getLocationPics = 104;
	public static int com_getHomeService = 105;
	public static int com_getVideoInfo = 106;

}
