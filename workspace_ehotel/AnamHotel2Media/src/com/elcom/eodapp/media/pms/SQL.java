package com.elcom.eodapp.media.pms;

public interface SQL {
	public static final String sqlGetMessageInfo = "{call PMSAPP.getMessageInfo(?,?,?)}";
	public static final String sqlGetLinkPromotion = "begin ? := VOD.getLinkPromotion(?,?); end;";
	public static final String sqlgetLocations = "begin ? := PMSAPP.getLocations(?,?); end;";
	public static final String sqlgetLocationPics = "begin ? := PMSAPP.getLocationPics(?,?); end;";
	public static final String sqlgetHomeService = "{call PMSAPP.getHomeService(?,?)}";
	public static final String sqlgetVideoInfo = "{call VOD.getVideoInfo(?,?,?)}";
	
}
