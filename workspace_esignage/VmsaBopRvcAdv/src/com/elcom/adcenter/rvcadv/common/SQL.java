package com.elcom.adcenter.rvcadv.common;

public interface SQL {
	// TODO
	// static final String sp_admingetContentGroup =
	// "{call dbadcenter.sp_admingetContentGroup(?,?,?)}";
	//
	// static final String sp_admingetLayoutContainContent =
	// "{call dbadcenter.sp_admingetLayoutContainContent(?,?)}";
	// static final String sp_admingetLayoutGroup =
	// "{call dbadcenter.sp_admingetLayoutGroup(?,?,?)}";
	// static final String sp_admingetPlayListGroup =
	// "{call dbadcenter.sp_admingetPlayListGroup(?,?)}";
	//
	// //TODO
	// static final String sp_admingetListGroup =
	// "{call dbadcenter.sp_admingetListGroup(?,?,?)}";
	//
	// static final String sp_getadminListStbGroup =
	// "{call dbadcenter.sp_getadminListStbGroup(?,?)}";
	// // static final String sp_getadminListStbGroup =
	// "{call sp_getadminListStbGroup(?,?)}";
	// static final String sp_admingetListStb =
	// "{call dbadcenter.sp_admingetListStb(?,?)}";
	// static final String sp_adminDelStb =
	// "{call dbadcenter.sp_adminDelStb(?)}";
	// static final String sp_adminStbPush =
	// "{call dbadcenter.sp_adminStbPush(?,?)}";
	// static final String sp_admingetListGroupAll =
	// "{call dbadcenter.sp_admingetListGroupAll(?,?)}";
	//
	// //TODO
	// static final String sp_admingetListGroups =
	// "{call dbadcenter.sp_admingetListGroups(?,?,?)}";
	//
	// static final String sp_admingetListStbAll =
	// "{call dbadcenter.sp_admingetListStbAll(?,?)}";
	// static final String sp_admingetConfig =
	// "{call dbadcenter.sp_admingetConfig(?,?)}";
	// static final String sp_adminUpdateConfig =
	// "{call dbadcenter.sp_adminUpdateConfig(?,?)}";
	//
	// static final String sp_adminUpdateDirecGroup =
	// "{call dbadcenter.sp_adminUpdateDirecGroup(?,?)}";
	// static final String sp_adminInsertLayout =
	// "{call dbadcenter.sp_adminInsertLayout(?,?,?,?,?,?,?,?,?,?,?)}";
	// static final String sp_adminInsertItemPlaylist =
	// "{call dbadcenter.sp_adminInsertItemPlaylist(?,?,?,?,?,?,?)}";
	//
	// //TODO
	// static final String sp_adminNewGroup =
	// "{call dbadcenter.sp_adminNewGroup(?,?,?)}";
	//
	// static final String sp_adminDelGroup =
	// "{call dbadcenter.sp_adminDelGroup(?,?)}";
	// static final String sp_AdminUpdateGroup =
	// "{call dbadcenter.sp_AdminUpdateGroup(?,?,?)}";
	// static final String sp_adminToolGetListContent =
	// "{call dbadcenter.sp_adminToolGetListContent(?,?)}";
	// static final String sp_adminSetContentIntoStb =
	// "{call dbadcenter.sp_adminSetContentIntoStb(?,?)}";
	// static final String sp_adminUpdatePushInfoStb =
	// "{call dbadcenter.sp_adminUpdatePushInfoStb(?,?)}";
	// static final String sp_admingetPlayList =
	// "{call dbadcenter.sp_admingetPlayList(?,?)}";
	// static final String sp_admingetContentStb =
	// "{call dbadcenter.sp_admingetContentStb(?,?)}";
	// static final String sp_admingetContent =
	// "{call dbadcenter.sp_admingetContent(?,?)}";
	// static final String sp_admingetContentNoGroup =
	// "{call dbadcenter.sp_admingetContentNoGroup(?,?)}";
	// static final String sp_admingetScheduleDailyNamGroup =
	// "{call dbadcenter.sp_admingetScheduleDailyNamGroup(?,?)}";
	// static final String sp_adminNewScheduleDailyName =
	// "{call dbadcenter.sp_adminNewScheduleDailyName(?,?,?)}";
	// static final String sp_adminUpdateScheduleDailyTime =
	// "{call dbadcenter.sp_adminUpdateScheduleDailyTime(?,?,?)}";
	// static final String sp_adminDelSchduledailyTime =
	// "{call dbadcenter.sp_adminDelSchduledailyTime(?,?)}";
	// static final String sp_admingetPlayListLayout =
	// "{call dbadcenter.sp_admingetPlayListLayout(?,?)}";
	//
	// static final String sp_adminNewLayoutContent =
	// "{call dbadcenter.sp_adminNewLayoutContent(?,?,?)}";
	// static final String sp_admingetPlaylistItem =
	// "{call dbadcenter.sp_admingetPlaylistItem(?,?,?)}";
	// static final String sp_adminDelPlaylist =
	// "{call dbadcenter.sp_adminDelPlaylist(?,?)}";
	//
	// //TODO
	// static final String sp_admingetListStbAlls =
	// "{call dbadcenter.sp_admingetListStbAlls(?,?,?)}";
	//
	// static final String sp_adminCheckStb =
	// "{call dbadcenter.sp_adminCheckStb(?,?)}";
	// static final String sp_adminDelConntentStbWar =
	// "{call dbadcenter.sp_adminDelConntentStbWar(?)}";
	//
	// /* sp_adminUpdateStbDefaultHome 10.10 */
	// static final String sp_adminUpdateStbDefaultHome =
	// "{call dbadcenter.sp_adminUpdateStbDefaultHome(?,?,?)}";
	//
	// /* sp_getSchedulePeriXml(groupidin,resul) 19.11 for focus*/
	// static final String sp_getSchedulePeriXml =
	// "{call dbadcenter.sp_getSchedulePeriXml(?,?)}";
	//
	// static final String sp_admingetScheduleDailyGroup =
	// "{call dbadcenter.sp_admingetScheduleDailyGroup(?,?)}";
	// static final String sp_adminInserContentIntoGroup =
	// "{call dbadcenter.sp_adminInserContentIntoGroup(?,?)}";
	// static final String sp_adminDeleteContentIntoGroup =
	// "{call dbadcenter.sp_adminDeleteContentIntoGroup(?,?)}";
	// static final String sp_adminDelContentStb =
	// "{call dbadcenter.sp_adminDelContentStb(?,?)}";
	//
	// //TODO
	// static final String sp_admingetLayoutName =
	// "{call dbadcenter.sp_admingetLayoutName(?,?,?)}";
	//
	// static final String sp_adminAddStbIntoGroup =
	// "{call dbadcenter.sp_adminAddStbIntoGroup(?,?)}";
	// static final String sp_adminUpdateInfoStb =
	// "{call dbadcenter.sp_adminUpdateInfoStb(?,?,?,?)}";
	//
	// //TODO
	// static final String sp_adminNewLayoutName =
	// "{call dbadcenter.sp_adminNewLayoutName(?,?,?,?)}";
	//
	// static final String sp_adminUpdateLayoutName =
	// "{call dbadcenter.sp_adminUpdateLayoutName(?,?,?)}";
	// static final String sp_adminDelLayoutName =
	// "{call dbadcenter.sp_adminDelLayoutName(?,?)}";
	// static final String sp_admingetTypeLayout =
	// "{call dbadcenter.sp_admingetTypeLayout(?,?)}";
	// static final String sp_admingetSchedulePeriGroup =
	// "{call dbadcenter.sp_admingetSchedulePeriGroup(?,?)}";
	// static final String sp_adminNewScheduleDaily =
	// "{call dbadcenter.sp_adminNewScheduleDaily(?,?,?,?,?)}";
	// static final String sp_adminNewSchedulePeri =
	// "{call dbadcenter.sp_adminNewSchedulePeri(?,?)}";
	// static final String sp_adminNewSchedulePeriName =
	// "{call dbadcenter.sp_adminNewSchedulePeriName(?,?,?,?)}";
	// static final String sp_admingetItemScheduleDailyForPerio =
	// "{call dbadcenter.sp_admingetItemScheduleDailyForPerio(?,?)}";
	// static final String sp_adminDelScheduleDailyName =
	// "{call dbadcenter.sp_adminDelScheduleDailyName(?)}";
	// static final String sp_adminDelSchedulePeriItem =
	// "{call dbadcenter.sp_adminDelSchedulePeriItem(?,?)}";
	// static final String sp_adminDelSchedulePeri =
	// "{call dbadcenter.sp_adminDelSchedulePeri(?,?)}";
	//
	// static final String sp_adminUpdateSchedulePeri =
	// "{call dbadcenter.sp_adminUpdateSchedulePeri(?,?,?,?,?)}";
	//
	// static final String sp_adminUpdateScheduleDailyName =
	// "{call dbadcenter.sp_adminUpdateScheduleDailyName(?,?)}";
	// static final String sp_adminUpdateScheduleSailyPerio =
	// "{call dbadcenter.sp_adminUpdateScheduleSailyPerio()}";
	// static final String sp_adminDleteScheduleSailyPerio =
	// "{call dbadcenter.sp_adminDleteScheduleSailyPerio()}";
	// static final String sp_adminNewContent =
	// "{call dbadcenter.sp_adminNewContent(?,?,?)}";
	//
	// //bo sung update duration 28.2
	// static final String sp_adminNewContentWithDuration =
	// "{call dbadcenter.sp_adminNewContentWithDuration(?,?,?,?)}";
	//
	// static final String sp_adminNewPlaylistName =
	// "{call dbadcenter.sp_adminNewPlaylistName(?,?,?)}";
	// static final String sp_adminDelItemPlaylist =
	// "{call dbadcenter.sp_adminDelItemPlaylist(?)}";
	//
	// static final String sp_adminMoveGroup =
	// "{call dbadcenter.sp_adminMoveGroup(?,?)}";
	// static final String sp_adminUdateStb =
	// "{call dbadcenter.sp_adminUdateStb(?,?,?,?,?)}";
	// static final String sp_adminUpdateLayoutCoor =
	// "{call dbadcenter.sp_adminUpdateLayoutCoor(?,?,?,?,?)}";
	// static final String sp_adminUpdatePlayListName =
	// "{call dbadcenter.sp_adminUpdatePlayListName(?,?)}";
	// static final String sp_adminUpdatePlaylistTime =
	// "{call dbadcenter.sp_adminUpdatePlaylistTime(?,?,?)}";
	// static final String sp_adminDelLayOutItem =
	// "{call dbadcenter.sp_adminDelLayOutItem(?,?)}";
	// //content text
	// static final String sp_adminNewContentText =
	// "{call dbadcenter.sp_adminNewContentText(?,?,?,?,?,?,?)}";
	// static final String sp_adminUpdateContentText =
	// "{call dbadcenter.sp_adminUpdateContentText(?,?,?,?,?,?,?)}";
	// static final String sp_adminDelContentText =
	// "{call dbadcenter.sp_adminDelContentText(?)}";
	// //Report
	// static final String sp_adminReportLayout =
	// "{call dbadcenter.sp_adminReportLayout(?,?,?,?)}";
	// static final String sp_adminReportContentGroup =
	// "{call dbadcenter.sp_adminReportContentGroup(?,?)}";
	// static final String sp_adminReportContentAll =
	// "{call dbadcenter.sp_adminReportContentAll(?,?)}";
	// static final String sp_adminReportPlaylist =
	// "{call dbadcenter.sp_adminReportPlaylist(?,?,?,?)}";
	// static final String sp_adminReportDaily =
	// "{call dbadcenter.sp_adminReportDaily(?,?,?,?)}";
	// static final String sp_adminReportSchedulePerio =
	// "{call dbadcenter.sp_adminReportSchedulePerio(?,?)}";
	// static final String sp_adminReportStb =
	// "{call dbadcenter.sp_adminReportStb(?,?)}";

	static final String sp_admingetContentGroup = "{call sp_admingetContentGroup(?,?,?,?)}";

	static final String sp_admingetLayoutContainContent = "{call sp_admingetLayoutContainContent(?,?)}";
	static final String sp_admingetLayoutGroup = "{call sp_admingetLayoutGroup(?,?,?)}";
	static final String sp_admingetPlayListGroup = "{call sp_admingetPlayListGroup(?,?,?)}";

	// TODO
	static final String sp_admingetListGroup = "{call sp_admingetListGroup(?,?,?,?)}";

	static final String sp_getadminListStbGroup = "{call sp_getadminListStbGroup(?,?,?,?)}";
	// static final String sp_getadminListStbGroup =
	// "{call sp_getadminListStbGroup(?,?)}";
	static final String sp_admingetListStb = "{call sp_admingetListStb(?,?)}";
	static final String sp_adminDelStb = "{call sp_adminDelStb(?)}";
	static final String sp_adminRemoveBox = "{call sp_adminRemoveBox(?,?)}";
	static final String sp_adminStbPush = "{call sp_adminStbPush(?,?)}";
	static final String sp_admingetListGroupAll = "{call sp_admingetListGroupAll(?,?)}";

	// TODO
	static final String sp_admingetListGroups = "{call sp_admingetListGroups(?,?,?)}";

	static final String sp_admingetListStbAll = "{call sp_admingetListStbAll(?,?)}";
	static final String sp_admingetConfig = "{call sp_admingetConfig(?,?)}";
	static final String sp_adminUpdateConfig = "{call sp_adminUpdateConfig(?,?)}";

	static final String sp_adminUpdateDirecGroup = "{call sp_adminUpdateDirecGroup(?,?)}";
	static final String sp_adminInsertLayout = "{call sp_adminInsertLayout(?,?,?,?,?,?,?,?,?,?,?)}";
	static final String sp_adminInsertItemPlaylist = "{call sp_adminInsertItemPlaylist(?,?,?,?,?,?,?)}";
	// TODO
	static final String sp_adminNewGroup = "{call sp_adminNewGroup(?,?,?,?,?)}";

	static final String sp_adminDelGroup = "{call sp_adminDelGroup(?,?)}";
	static final String sp_AdminUpdateGroup = "{call sp_AdminUpdateGroup(?,?,?)}";
	static final String sp_adminToolGetListContent = "{call sp_adminToolGetListContent(?,?)}";
	static final String sp_adminSetContentIntoStb = "{call sp_adminSetContentIntoStb(?,?)}";
	static final String sp_adminUpdatePushInfoStb = "{call sp_adminUpdatePushInfoStb(?,?)}";
	static final String sp_admingetPlayList = "{call sp_admingetPlayList(?,?)}";
	static final String sp_admingetContentStb = "{call sp_admingetContentStb(?,?)}";
	static final String sp_admingetContent = "{call sp_admingetContent(?,?,?,?)}";
	static final String sp_adminGetAllContent = "{call sp_adminGetAllContent(?,?,?,?,?)}";
	static final String sp_adminGetAllContentMedia = "{call sp_adminGetAllContentMedia(?,?,?,?)}";
	static final String sp_admingetContentNoGroup = "{call sp_admingetContentNoGroup(?,?)}";
	static final String sp_admingetScheduleDailyNamGroup = "{call sp_admingetScheduleDailyNamGroup(?,?,?)}";
	static final String sp_adminNewScheduleDailyName = "{call sp_adminNewScheduleDailyName(?,?,?,?,?,?)}";
	static final String sp_adminUpdateScheduleDailyTime = "{call sp_adminUpdateScheduleDailyTime(?,?,?, ?)}";
	static final String sp_adminDelSchduledailyTime = "{call sp_adminDelSchduledailyTime(?,?)}";
	static final String sp_admingetPlayListLayout = "{call sp_admingetPlayListLayout(?,?)}";

	static final String sp_adminNewLayoutContent = "{call sp_adminNewLayoutContent(?,?,?)}";
	static final String sp_admingetPlaylistItem = "{call sp_admingetPlaylistItem(?,?,?)}";
	static final String sp_adminDelPlaylist = "{call sp_adminDelPlaylist(?,?)}";

	// TODO
	static final String sp_admingetListStbAlls = "{call sp_admingetListStbAlls(?,?,?)}";

	static final String sp_adminCheckStb = "{call sp_adminCheckStb(?,?)}";
	static final String sp_adminDelConntentStbWar = "{call sp_adminDelConntentStbWar(?)}";

	/* sp_adminUpdateStbDefaultHome 10.10 */
	static final String sp_adminUpdateStbDefaultHome = "{call sp_adminUpdateStbDefaultHome(?,?,?)}";

	/* sp_getSchedulePeriXml(groupidin,resul) 19.11 for focus */
	static final String sp_getSchedulePeriXml = "{call sp_getSchedulePeriXml(?,?)}";

	static final String sp_admingetScheduleDailyGroup = "{call sp_admingetScheduleDailyGroup(?,?)}";
	static final String sp_adminInserContentIntoGroup = "{call sp_adminInserContentIntoGroup(?,?)}";
	static final String sp_adminDeleteContentIntoGroup = "{call sp_adminDeleteContentIntoGroup(?,?)}";
	static final String sp_adminDelContentStb = "{call sp_adminDelContentStb(?,?)}";

	// TODO
	static final String sp_admingetLayoutName = "{call sp_admingetLayoutName(?,?,?,?)}";

	static final String sp_adminAddStbIntoGroup = "{call sp_adminAddStbIntoGroup(?,?)}";
	static final String sp_abopRemoveStbFromGroup = "{call sp_abopRemoveStbFromGroup(?,?)}";
	static final String sp_adminUpdateInfoStb = "{call sp_adminUpdateInfoStb(?,?,?,?)}";

	// TODO
	static final String sp_adminNewLayoutName = "{call sp_adminNewLayoutName(?,?,?,?,?, ?,?)}";

	static final String sp_adminUpdateLayoutName = "{call sp_adminUpdateLayoutName(?,?,?,?)}";
	static final String sp_adminDelLayoutName = "{call sp_adminDelLayoutName(?,?)}";
	static final String sp_admingetTypeLayout = "{call sp_admingetTypeLayout(?,?)}";
	static final String sp_admingetSchedulePeriGroup = "{call sp_admingetSchedulePeriGroup(?,?)}";
	static final String sp_adminNewScheduleDaily = "{call sp_adminNewScheduleDaily(?,?,?,?,?,?)}";
	static final String sp_abopDeleteAllDailyItem = "{call sp_abopDeleteAllDailyItem(?)}";
	static final String sp_adminNewSchedulePeri = "{call sp_adminNewSchedulePeri(?,?)}";
	static final String sp_adminNewSchedulePeriName = "{call sp_adminNewSchedulePeriName(?,?,?,?,?)}";
	static final String sp_admingetItemScheduleDailyForPerio = "{call sp_admingetItemScheduleDailyForPerio(?,?)}";
	static final String sp_adminDelScheduleDailyName = "{call sp_adminDelScheduleDailyName(?,?)}";
	static final String sp_adminDelSchedulePeriItem = "{call sp_adminDelSchedulePeriItem(?,?)}";
	static final String sp_adminDelSchedulePeri = "{call sp_adminDelSchedulePeri(?,?)}";

	static final String sp_adminUpdateSchedulePeri = "{call sp_adminUpdateSchedulePeri(?,?,?,?,?)}";

	static final String sp_adminUpdateScheduleDailyName = "{call sp_adminUpdateScheduleDailyName(?,?,?)}";
	static final String sp_adminUpdateScheduleSailyPerio = "{call sp_adminUpdateScheduleSailyPerio()}";
	static final String sp_adminDleteScheduleSailyPerio = "{call sp_adminDleteScheduleSailyPerio()}";
	static final String sp_adminNewContent = "{call sp_adminNewContent(?,?,?,?)}";

	// bo sung update duration 28.2
	static final String sp_adminNewContentWithDuration = "{call sp_adminNewContentWithDuration(?,?,?,?)}";

	static final String sp_adminNewPlaylistName = "{call sp_adminNewPlaylistName(?,?,?,?,?,?)}";
	static final String sp_adminDelItemPlaylist = "{call sp_adminDelItemPlaylist(?)}";

	static final String sp_adminMoveGroup = "{call sp_adminMoveGroup(?,?)}";
	static final String sp_adminUdateStb = "{call sp_adminUdateStb(?,?,?,?)}";
	static final String sp_adminUpdateLayoutCoor = "{call sp_adminUpdateLayoutCoor(?,?,?,?,?,?)}";
	static final String sp_adminUpdatePlayListName = "{call sp_adminUpdatePlayListName(?,?,?)}";
	static final String sp_adminUpdatePlaylistTime = "{call sp_adminUpdatePlaylistTime(?,?,?)}";
	static final String sp_abopDeletePlaylistItems = "{call sp_abopDeletePlaylistItems(?)}";
	static final String sp_adminDelLayOutItem = "{call sp_adminDelLayOutItem(?,?)}";
	// content text
	static final String sp_adminNewContentText = "{call sp_adminNewContentText(?,?,?,?,?,?,?,?,?,?)}";
	static final String sp_adminUpdateContentText = "{call sp_adminUpdateContentText(?,?,?,?,?,?,?)}";
	static final String sp_adminDelContentText = "{call sp_adminDelContentText(?)}";
	// Report
	static final String sp_adminReportLayout = "{call sp_adminReportLayout(?,?,?,?)}";
	static final String sp_adminReportContentGroup = "{call sp_adminReportContentGroup(?,?)}";
	static final String sp_adminReportContentAll = "{call sp_adminReportContentAll(?,?)}";
	static final String sp_adminReportPlaylist = "{call sp_adminReportPlaylist(?,?,?,?)}";
	static final String sp_adminReportDaily = "{call sp_adminReportDaily(?,?,?,?)}";
	static final String sp_adminReportSchedulePerio = "{call sp_adminReportSchedulePerio(?,?)}";
	static final String sp_adminReportStb = "{call sp_adminReportStb(?,?)}";

	static final String sp_adminGetPlaylistIDByDaily = "{call sp_adminGetPlaylistIDByDaily(?,?)}";
	static final String sp_adminGetContentIDByPlaylist = "{call sp_adminGetContentIDByPlaylist(?,?)}";
	static final String sp_adminGetIdSTBInGroup = "{call sp_adminGetIdSTBInGroup(?,?)}";
	static final String sp_adminPushContentIntoSTB = "{call sp_adminPushContentIntoSTB(?,?)}";
	static final String sp_adminUpdateDownloadStatusSTB = "{call sp_adminUpdateDownloadStatusSTB(?)}";

	// subject content
	static final String sp_adminAddSubjectContent = "{call sp_adminAddSubjectContent(?,?,?,?)}";
	static final String sp_adminGetAllSubjectContent = "{call sp_adminGetAllSubjectContent(?,?,?)}";
	static final String sp_adminUpdateSubjectContent = "{call sp_adminUpdateSubjectContent(?,?,?)}";
	static final String sp_adminDeleteSubjectContent = "{call sp_adminDeleteSubjectContent(?)}";

	static final String sp_adminDeleteAllContentAllSTBInGroup = "{call sp_adminDeleteAllContentAllSTBInGroup(?)}";
	static final String sp_adminGetPlaylistIdByGroupId = "{call sp_adminGetPlaylistIdByGroupId(?,?)}";
	static final String sp_adminAddNewContentMedia = "{call sp_adminAddNewContentMedia(?,?,?,?,?,?,?)}";
	static final String sp_adminRemoveSTBOutToGroup = "{call sp_adminRemoveSTBOutToGroup(?)}";
	static final String sp_adminNewContentSlide = "{call sp_adminNewContentSlide(?,?,?,?,?,?,?)}";
	static final String sp_adminNewSlide = "{call sp_adminNewSlide(?,?,?,?)}";
	static final String sp_adminNewImageSlide = "{call sp_adminNewImageSlide(?,?,?,?)}";
	static final String sp_adminDeleteSlideContent = "{call sp_adminDeleteSlideContent(?)}";
	static final String sp_adminGetContentIDByForSlide = "{call sp_adminGetContentIDByForSlide(?,?)}";
	static final String sp_adminUpdateSlideContent = "{call sp_adminUpdateSlideContent(?,?,?,?,?)}";

	// User manager
	static final String sp_adminGetAllUser = "{call abop.sp_adminGetAllUser()}";

	// TODO
	static final String creator = "creator";
	static final String parentcreator = "parentcreator";

	static final String groupid = "groupid";
	static final String stbid = "stbid";
	static final String layoutnameid = "layoutnameid";
	static final String playlistid = "playlistid";
	static final String groupname = "groupname";
	static final String layoutname = "layoutname";
	static final String parentgroupid = "parentgroupid";
	static final String scheduleid = "scheduleid";
	static final String namedaily = "namedailyin";
	static final String nameschedule = "nameschedule";
	static final String nameplaylist = "nameplaylist";
	static final String startdate = "startdate";
	static final String stopdate = "stopdate";
	static final String desc = "desc";
	static final String dailynameid = "dailynameid";
	static final String contentname = "contentname";
	static final String contentype = "contentype";
	static final String urlcontent = "urlcontent";
	static final String layoutid = "layoutid";
	static final String contentid = "contentid";
	static final String playlistitemid = "playlistitemid";
	static final String groupmoveid = "groupmoveid";
	static final String name = "name";
	static final String xcoor = "xcoor";
	static final String ycoor = "ycoor";
	static final String width = "width";
	static final String urlserver = "urlserver";
	static final String height = "height";
	static final String savelocalmedia = "savelocalmedia";
	static final String savelocalfile = "savelocalfile";
	static final String scheduleitemid = "scheduleitemid";
	static final String id_Content = "id_Content";
	static final String nameContent = "nameContent";
	static final String typeContent = "typeContent";
	static final String urlContent = "urlContent";
	static final String subjectContent = "subjectContent";
	static final String lengthContent = "lengthContent";
	static final String colorText = "colorText";
	static final String fontText = "fontText";
	static final String sizeText = "sizeText";
	static final String direcRoll = "direcRoll";
	static final String desstbfile = "direcRoll";
	static final String direction = "direction";
	static final String createdate = "createdate";
	static final String createdatestart = "createdatestart";
	static final String createdatestop = "createdatestop";

	// chuyen file ln server
	static final String serverhostftp = "server_ftp";
	static final String serverportftp = "server_port";
	static final String serveruserftp = "serveruserftp";
	static final String serverpassftp = "serverpassftp";
	static final String srcfile = "src_file";
	static final String desc_host = "desc_host";
	static final String local_file = "local_file";
	static final String timeout = "timeout";

	static final String filelayout = "DataLayout.xml";
	static final String fileplaylist = "DataPlaylist.xml";
	static final String filestb = "DataStb.xml";
	static final String filecontentgroup = "DataContentGroup.xml";
	static final String filescheduledaily = "DataScheduleDaily.xml";
	static final String filescheduleperi = "DataSchedulePeri.xml";
	static final String filelayoutupdate = "DataLayoutUpdate.xml";
	static final String filesplaylisttime = "DataPlaylistTime.xml";
	static final String filesplaylistitemdel = "DataPlaylistItemDel.xml";
	static final String filestbpush = "DataStbPush.xml";
	static final String typelayout = "typelayout";
	static final String sp_admingetLayoutNameType = "{call sp_admingetLayoutNameType(?,?,?,?)}";

	static final String idstb = "idstb";
	static final String idcontent = "idcontent";
	static final String sp_abopDeleteContentFromSTB = "{call sp_abopDeleteContentFromSTB(?,?,?)}";
	static final String itemplaylistid = "itemplaylistid";
	static final String monitoring = "monitoring";

	static final String sp_abopSetMonitoring = "{call sp_abopSetMonitoring(?,?)}";

	static final String sp_abopGetUser = "{call sp_abopGetUser(?,?,?)}";
	static final String sp_abopGetAllBox = "{call sp_abopGetAllBox(?,?,?)}";
	static final String username = "username";
	static final String password = "password";

	static final String sp_abopGetListUser = "{call sp_abopGetListUser(?,?,?)}";

	static final String sp_abopCreateUser = "{call sp_abopCreateUser(?,?)}";

	static final String abopGetRole = "{call sp_abopGetRole(?)}";

	static final String sp_abopCheckUser = "{call sp_abopCheckUser(?, ?)}";

	static final String sp_abopUpdateUser = "{call sp_abopUpdateUser(?,?)}";
	
	static final String sp_abopAddStbUser = "{call sp_abopAddStbUser(?,?)}";

	static final String sp_abopDeleteUser = "{call sp_abopDeleteUser(?,?)}";

	static final String size_screen = "size_screen";

}