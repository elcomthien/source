package com.elcom.ehotel.admin.util;

import com.elcom.ehotel.admin.dbi.IMBroker;

public class SQL {

	public static IMBroker broker = IMBroker.getInstance();

	// Livetv subject
	public static final String GET_LIST_LIVETV_SUBJECT = "BEGIN ELIVETV.getSubjects(?,?); END;";
	public static final String ADD_LIVETV_SUBJECT = "BEGIN ELIVETV.addSubject(?,?,?,?,?); END;";
	public static final String EDIT_LIVETV_SUBJECT = "BEGIN ELIVETV.editSubject(?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_LIVETV_SUBJECT = "BEGIN ELIVETV.removeSubject(?,?); END;";
	
	public static final String GET_NOTIFY = "BEGIN ELIVETV.getNotify(?,?); END;";
	public static final String EDIT_NOTIFY = "BEGIN ELIVETV.editNotify(?,?,?,?,?,?); END;";
	
	public static final String GET_NOTIFY_GROUP = "BEGIN ELIVETV.getNotifyGroup(?,?,?); END;";
	public static final String EDIT_NOTIFY_GROUP = "BEGIN ELIVETV.editNotifyGroup(?,?,?,?,?,?,?); END;";

	// livetv channel
	public static final String GET_LIST_LIVETV_CHANNEL = "BEGIN ELIVETV.getChannels(?,?); END;";
	public static final String ADD_LIVETV_CHANNEL = "BEGIN ELIVETV.addNewChannel(?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_LIVETV_CHANNEL = "BEGIN ELIVETV.editChannel(?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_LIVETV_CHANNEL = "BEGIN ELIVETV.deleteChannel(?,?); END;";
	
	public static final String GET_LIST_LIVETV_CHANNEL_GROUP = "BEGIN ELIVETV.getChannelsGroup(?,?,?); END;";
	public static final String ADD_LIVETV_CHANNEL_GROUP = "BEGIN ELIVETV.addNewChannelGroup(?,?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_LIVETV_CHANNEL_GROUP = "BEGIN ELIVETV.editChannelGroup(?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_LIVETV_CHANNEL_GROUP = "BEGIN ELIVETV.deleteChannelGroup(?,?,?); END;";

	// livetivi channel advertise
	public static final String GET_LIST_CHANNEL_ADVERTISE = "BEGIN ELIVETV.getChannelAdvertise(?); END;";
	public static final String ADD_CHANNEL_ADVERTISE = "BEGIN ELIVETV.addChannelAdvertise(?,?,?,?,?); END;";
	public static final String EDIT_CHANNEL_ADVERTISE = "BEGIN ELIVETV.editChannelAdvertise(?,?,?,?,?); END;";
	public static final String DELETE_CHANNEL_ADVERTISE = "BEGIN ELIVETV.deleteChannelAdvertise(?,?); END;";
	
	public static final String GET_LIST_CHANNEL_ADVERTISE_GROUP = "BEGIN ELIVETV.getChannelAdvertiseGroup(?,?); END;";
	public static final String ADD_CHANNEL_ADVERTISE_GROUP = "BEGIN ELIVETV.addChannelAdvertiseGroup(?,?,?,?,?,?); END;";
	public static final String EDIT_CHANNEL_ADVERTISE_GROUP = "BEGIN ELIVETV.editChannelAdvertiseGroup(?,?,?,?,?,?); END;";
	public static final String DELETE_CHANNEL_ADVERTISE_GROUP = "BEGIN ELIVETV.deleteChannelAdvertiseGroup(?,?,?); END;";

	// pms advertise
	public static final String GET_LIST_ADVERTISE = "BEGIN EPMS.getImageAdverties(?,?); END;";
	public static final String ADD_ADVERTISE = "BEGIN EPMS.addAdvertise(?,?,?,?,?,?); END;";
	public static final String EDIT_ADVERTISE = "BEGIN EPMS.editAdvertise(?,?,?,?,?,?,?); END;";
	public static final String DELETE_ADVERTISE = "BEGIN EPMS.removeAdvertie(?,?); END;";
	
	public static final String GET_LIST_ADVERTISE_GROUP = "BEGIN EPMS.getImageAdvertiesGroup(?,?,?); END;";
	public static final String ADD_ADVERTISE_GROUP = "BEGIN EPMS.addAdvertiseGroup(?,?,?,?,?,?,?); END;";
	public static final String EDIT_ADVERTISE_GROUP = "BEGIN EPMS.editAdvertiseGroup(?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_ADVERTISE_GROUP = "BEGIN EPMS.removeAdvertieGroup(?,?,?); END;";

	// pms folio
	public static final String GET_LIST_FOLIO = "BEGIN EPMS.getFolioList2(?); END;";
	public static final String GET_LIST_GUEST = "BEGIN EPMS.getGuests(?,?); END;";
	public static final String ADD_OR_UPDATE_GUEST = "BEGIN EPMS.addorupdateguest(?,?,?,?,?,?,?); END;";
	public static final String DELETE_GUEST = "BEGIN EPMS.deleteGuestInFolio(?,?,?); END;";
	public static final String GET_LIST_FOLIO_PREVIEW = "BEGIN EPMS.getListRoomPreview(?); END;";
	public static final String UPDATE_FOLIO_PREVIEW = "BEGIN EPMS.updateIsPreview(?,?,?); END;";
	public static final String PUBLIC_PREVIEW = "BEGIN EPMS.publicContent(?); END;";
	
	public static final String GET_LIST_GROUP = "BEGIN EPMS.getGroupFolio(?); END;";
	public static final String GET_LIST_FOLIO_GROUP = "BEGIN EPMS.getFolioGroup(?,?); END;";
	public static final String ADD_GROUP = "BEGIN EPMS.addGroupFolio(?,?); END;";
	public static final String CHANGE_FOLIO_GROUP = "BEGIN EPMS.changeFolioGroup(?,?,?,?,?); END;";
	public static final String EDIT_GROUP = "BEGIN EPMS.editGroup(?,?,?); END;";
	public static final String DELETE_GROUP = "BEGIN EPMS.deleteGroup(?,?); END;";

	// pms folio message
	public static final String GET_LIST_MESSAGE = "BEGIN EPMS.getMessages(?,?,?); END;";
	public static final String ADD_MESSAGE_FOLIO = "BEGIN EPMS.addMessageFolio(?,?,?,?,?); END;";
	public static final String DELETE_MESSAGE_FOLIO = "BEGIN EPMS.deleteMessageFolio(?,?,?); END;";

	// pms language
	public static final String GET_LIST_LANGUAGE = "BEGIN EPMS.getListLanguage(?); END;";
	public static final String ADD_NEW_LANGUAGE = "BEGIN EPMS.addNewLanguage(?,?,?,?); END;";
	public static final String EDIT_LANGUAGE = "BEGIN EPMS.editLanguage(?,?,?,?,?); END;";
	public static final String DELETE_LANGUAGE = "BEGIN EPMS.deleteLanguage(?,?); END;";

	// pms hotel infomation subject
	public static final String GET_SUBJECT_INFO = "BEGIN EPMS.getMainMenu(?,?,?); END;";
	public static final String ADD_SUBJECT_INFO = "BEGIN EPMS.addMainMenu(?,?,?,?,?,?,?); END;";
	public static final String EDIT_SUBJECT_INFO = "BEGIN EPMS.editMainMenu(?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_SUBJECT_INFO = "BEGIN EPMS.deleteMainMenu(?,?,?); END;";
	
	public static final String GET_SUBJECT_INFO_GROUP = "BEGIN EPMS.getMainMenuGroup(?,?,?,?); END;";
	public static final String ADD_SUBJECT_INFO_GROUP = "BEGIN EPMS.addMainMenuGroup(?,?,?,?,?,?,?,?); END;";
	
	// pms hotel infomation content
	public static final String GET_CONTENT_INFO = "BEGIN EPMS.getItemsOfHotelInfo(?,?,?); END;";
	public static final String ADD_CONTENT_INFO = "BEGIN EPMS.addItemInfo(?,?,?,?,?,?); END;";
	public static final String EDIT_CONTENT_INFO = "BEGIN EPMS.editItemInfo(?,?,?,?,?,?); END;";
	public static final String DELETE_CONTENT_INFO = "BEGIN EPMS.removeItemOfHotelInfo(?,?); END;";

	// pms exchange rate
	public static final String GET_LIST_EXCHANGE = "BEGIN EPMS.getListExchangeRates(?); END;";
	public static final String ADD_EXCHANGE = "BEGIN EPMS.addExchangeRate(?,?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_EXCHANGE = "BEGIN EPMS.editExchangeRate(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_EXCHANGE = "BEGIN EPMS.deleteExchangeRate(?,?); END;";

	// pms promotion
	public static final String GET_LIST_PROMOTION = "BEGIN EPMS.getPromotions(?,?); END;";
	public static final String ADD_PROMOTION = "BEGIN EPMS.addPromotion(?,?,?,?,?,?); END;";
	public static final String EDIT_PROMOTION = "BEGIN EPMS.editPromotion(?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_PROMOTION = "BEGIN EPMS.deletePromotion(?,?); END;";

	// pms dining subject
	public static final String GET_SUBJECT_DINING = "BEGIN EPMS.getDiningSubject(?,?,?); END;";
	public static final String ADD_SUBJECT_DINING = "BEGIN EPMS.addSubjectDining(?,?,?,?,?,?,?); END;";
	public static final String EDIT_SUBJECT_DINING = "BEGIN EPMS.editSubjectDining(?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_SUBJECT_DINING = "BEGIN EPMS.deleteSubjectDining(?,?); END;";

	// pms dining item
	public static final String GET_ITEM_DINING = "BEGIN EPMS.getItemDining(?,?,?); END;";
	public static final String ADD_ITEM_DINING = "BEGIN EPMS.addItemDining(?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_ITEM_DINING = "BEGIN EPMS.editItemDining(?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_ITEM_DINING = "BEGIN EPMS.deleteItemDining(?,?); END;";

	// pms smartcard
	public static final String GET_SMARTCARD_FOLIO = "BEGIN EPMS.getSmartcardFolio(?,?); END;";
	public static final String DELETE_SMARTCARD_FOLIO = "BEGIN EPMS.deleteSmartcard(?,?); END;";
	public static final String GET_LIST_SMARTCARD = "BEGIN EPMS.getListSmartcard(?); END;";

	// vod subject
	public static final String GET_LIST_VOD_SUBJECT = "BEGIN EVOD.getSubjects(?,?,?); END;";
	public static final String ADD_VOD_SUBJECT = "BEGIN EVOD.addSubject(?,?,?,?,?,?); END;";
	public static final String DELETE_VOD_SUBJECT = "BEGIN EVOD.removeSubject(?,?); END;";
	public static final String EDIT_VOD_SUBJECT = "BEGIN EVOD.editSubject(?,?,?,?,?,?,?); END;";
	
	public static final String GET_LIST_VOD_SUBJECT_GROUP = "BEGIN EVOD.getSubjectsGroup(?,?,?,?); END;";
	public static final String ADD_VOD_SUBJECT_GROUP = "BEGIN EVOD.addSubjectGroup(?,?,?,?,?,?,?); END;";
	public static final String DELETE_VOD_SUBJECT_GROUP = "BEGIN EVOD.removeSubjectGroup(?,?,?); END;";
	public static final String EDIT_VOD_SUBJECT_GROUP = "BEGIN EVOD.editSubjectGroup(?,?,?,?,?,?,?,?); END;";

	// vod content
	public static final String GET_LIST_VOD_CONTENT = "BEGIN EVOD.getVodContent(?,?,?); END;";
	public static final String ADD_VOD_CONTENT = "BEGIN EVOD.addVod(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_VOD_CONTENT = "BEGIN EVOD.updateVod(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_VOD_CONTENT = "BEGIN EVOD.removeVod(?,?); END;";
	
	public static final String GET_LIST_VOD_CONTENT_GROUP = "BEGIN EVOD.getVodContentGroup(?,?,?,?); END;";
	public static final String ADD_VOD_CONTENT_GROUP = "BEGIN EVOD.addVodGroup(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_VOD_CONTENT_GROUP = "BEGIN EVOD.updateVodGroup(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_VOD_CONTENT_GROUP = "BEGIN EVOD.removeVodGroup(?,?,?); END;";

	// vod subtitle
	public static final String GET_SUBTITLE = "BEGIN EVOD.getSubtitles(?,?); END;";
	public static final String ADD_OR_EDIT_SUBTITLE = "BEGIN EVOD.addSubtitle(?,?,?); END;";
	public static final String DELETE_SUBTITLE = "BEGIN EVOD.removeSubtitle(?,?); END;";

	// vod remote percent
	public static final String INSERT_VOD_REMOTE_PERCENT = "BEGIN EVOD.insertVodRemotePercent(?,?,?,?); END;";
	public static final String DELTE_VOD_REMOTE_PERCENT = "BEGIN EVOD.deleteVodTransferPercent(?,?); END;";
	public static final String GET_VOD_REMOTE_PERCENT = "BEGIN EVOD.getVodRemotePercent(?); END;";

	// mod subject
	public static final String GET_LIST_MOD_SUBJECT = "BEGIN EMOD.getSubjects(?,?); END;";
	public static final String ADD_MOD_SUBJECT = "BEGIN EMOD.addSubject(?,?,?,?,?); END;";
	public static final String DELETE_MOD_SUBJECT = "BEGIN EMOD.removeSubject(?,?); END;";
	public static final String EDIT_MOD_SUBJECT = "BEGIN EMOD.editSubject(?,?,?,?,?,?,?); END;";
	
	public static final String GET_LIST_MOD_SUBJECT_GROUP = "BEGIN EMOD.getSubjectsGroup(?,?,?); END;";
	public static final String ADD_MOD_SUBJECT_GROUP = "BEGIN EMOD.addSubjectGroup(?,?,?,?,?,?); END;";
	public static final String DELETE_MOD_SUBJECT_GROUP = "BEGIN EMOD.removeSubjectGroup(?,?,?); END;";
	public static final String EDIT_MOD_SUBJECT_GROUP = "BEGIN EMOD.editSubjectGroup(?,?,?,?,?,?,?,?); END;";

	// mod content
	public static final String GET_LIST_MOD_CONTENT = "BEGIN EMOD.getMods(?,?,?); END;";
	public static final String ADD_MOD_CONTENT = "BEGIN EMOD.addMod(?,?,?,?,?); END;";
	public static final String EDIT_MOD_CONTENT = "BEGIN EMOD.updateMod(?,?,?,?,?,?); END;";
	public static final String DELETE_MOD_CONTENT = "BEGIN EMOD.removeMod(?,?); END;";
	
	public static final String GET_LIST_MOD_CONTENT_GROUP = "BEGIN EMOD.getModsGroup(?,?,?,?); END;";
	public static final String ADD_MOD_CONTENT_GROUP = "BEGIN EMOD.addModGroup(?,?,?,?,?,?); END;";
	public static final String EDIT_MOD_CONTENT_GROUP = "BEGIN EMOD.updateModGroup(?,?,?,?,?,?,?); END;";
	public static final String DELETE_MOD_CONTENT_GROUP = "BEGIN EMOD.removeModGroup(?,?,?); END;";

	// system
	public static final String GET_LIST_SERVICE = "BEGIN EMAIN.getAllServices(?,?); END;";
	public static final String EDIT_SYSTEM_SERVICE = "BEGIN EMAIN.updateServiceMain(?,?,?,?,?,?,?); END;";
	public static final String UPDATE_STATUS_SERVICE = "BEGIN EMAIN.changeVisbleService(?,?); END;";
	public static final String GET_TEXT_WELCOME = "BEGIN EMAIN.getTextWelcome(?); END;";
	public static final String UPDATE_TEXT_WELCOME = "BEGIN EMAIN.updateTextWelcome(?,?,?,?,?); END;";
	public static final String GET_SERVICE = "BEGIN EMAIN.getServices(?,?,?); END;";
	
	public static final String GET_TEXT_WELCOME_GROUP = "BEGIN EMAIN.getTextWelcomeGroup(?,?); END;";
	public static final String UPDATE_TEXT_WELCOME_GROUP = "BEGIN EMAIN.updateTextWelcomeGroup(?,?,?,?,?,?); END;";
	
	public static final String GET_SERVICE_GROUP = "BEGIN EMAIN.getServicesGroup(?,?,?,?); END;";
	public static final String GET_LIST_SERVICE_GROUP = "BEGIN EMAIN.getAllServicesGroup(?,?,?); END;";
	public static final String EDIT_SYSTEM_SERVICE_GROUP = "BEGIN EMAIN.updateServiceMainGroup(?,?,?,?,?,?,?,?); END;";
	public static final String UPDATE_STATUS_SERVICE_GROUP = "BEGIN EMAIN.changeVisbleServiceGroup(?,?,?); END;";

	// ftp config
	public static final String GET_FTP_CONFIG = "BEGIN EPMS.getConfigFTP(?); END;";
	public static final String UPDATE_FTP_CONFIG = "BEGIN EPMS.updateFtpConfig(?,?,?,?,?); END;";

	// user
	public static final String CHECK_LOGIN = "BEGIN EUSER.checkLogin(?,?,?); END;";

	// custom object
	public static final String ADD_CUS_OBJECT = "BEGIN ECUS.addCusObject(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_CUS_OBJECT = "BEGIN ECUS.editCusObject(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_CUS_OBJECT = "BEGIN ECUS.deleteCusObject(?,?); END;";

	// user active
	public static final String ADD_USER_ACTIVE = "BEGIN EPMS.addUserActivities(?,?,?,?); END;";
	public static final String GET_USER_ACTIVE = "BEGIN EPMS.getUserActivities(?,?,?); END;";

	// welcome media
	public static final String GET_WELCOME_MEDIA = "BEGIN EPMS.getMediaWelcome(?,?); END;";
	public static final String ADD_WELCOME_MEDIA = "BEGIN EPMS.addMediaWelcome(?,?,?,?); END;";
	public static final String EDIT_WELCOME_MEDIA = "BEGIN EPMS.editMediaWelcome(?,?,?,?,?,?); END;";
	public static final String DELETE_WELCOME_MEDIA = "BEGIN EPMS.deleteMediaWelcome(?,?); END;";
	
	public static final String GET_WELCOME_MEDIA_GROUP = "BEGIN EPMS.getMediaWelcomeGroup(?,?,?); END;";
	public static final String ADD_WELCOME_MEDIA_GROUP = "BEGIN EPMS.addMediaWelcomeGroup(?,?,?,?,?); END;";
	public static final String EDIT_WELCOME_MEDIA_GROUP = "BEGIN EPMS.editMediaWelcomeGroup(?,?,?,?,?,?,?); END;";
	public static final String DELETE_WELCOME_MEDIA_GROUP = "BEGIN EPMS.deleteMediaWelcomeGroup(?,?,?); END;";

	// user manager
	public static final String GET_LIST_USER = "BEGIN EUSER.getListUser(?); END;";
	public static final String ADD_USER = "BEGIN EUSER.addUser(?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_USER = "BEGIN EUSER.editUser(?,?,?,?,?,?,?); END;";
	public static final String DELETE_USER = "BEGIN EUSER.deleteUser(?,?); END;";
	public static final String CHANGE_PASS = "BEGIN EUSER.changePass(?,?,?,?); END;";
	public static final String CHANGE_PASS_ADMIN = "BEGIN EUSER.changePassword(?,?,?); END;";
	public static final String GET_LIST_ROLE = "BEGIN EUSER.getListRole(?); END;";

	// flight shedule
	public static final String GET_LIST_AIRPORT = "BEGIN EPMS.getListAirport(?,?); END;";
	public static final String ADD_AIRPORT = "BEGIN EPMS.addAirport(?,?,?,?); END;";
	public static final String EDIT_AIRPORT = "BEGIN EPMS.editAirport(?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_AIRPORT = "BEGIN EPMS.deleteAirport(?,?); END;";

	// world clock
	public static final String GET_LIST_WORLDCLOCK = "BEGIN EPMS.getWorldClock(?); END;";
	public static final String ADD_WORLDCLOCK = "BEGIN EPMS.addWorldClock(?,?,?,?,?); END;";
	public static final String EDIT_WORLDCLOCK = "BEGIN EPMS.editWorldClock(?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_WORLDCLOCK = "BEGIN EPMS.deleteWorldClock(?,?); END;";
}
