package com.elcom.ehotel.esmile.util;

import com.elcom.ehotel.esmile.dbi.IMBroker;


public class SQL {
	public static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LOGIN = "BEGIN EADMIN.getLogin(?,?,?); END;";
	public static final String GET_LOCATION = "BEGIN EADMIN.getLocation(?); END;";
	public static final String GET_BACKGROUND = "BEGIN EADMIN.getBackground(?); END;";
	public static final String DELETE_BACKGROUND = "BEGIN EADMIN.deleteBackground(?,?); END;";
	public static final String EDIT_BACKGROUND = "BEGIN EADMIN.editBackground(?,?,?); END;";
	public static final String ADD_BACKGROUND = "BEGIN EADMIN.addBackground(?,?); END;";
	public static final String GET_STATISTIC = "BEGIN EADMIN.getStatistic(?,?,?,?); END;";
	public static final String GET_RATING = "BEGIN EADMIN.getRating(?,?,?,?,?,?); END;";
	public static final String GET_RATING_6 = "BEGIN EADMIN.getRating6(?,?,?,?,?,?); END;";
	public static final String GET_SMILE = "BEGIN EADMIN.getSmile(?,?,?,?,?); END;";
	public static final String GET_PROMOTION = "BEGIN EADMIN.getPromotion(?); END;";
	public static final String DELETE_PROMOTION = "BEGIN EADMIN.deletePromotion(?,?); END;";
	public static final String EDIT_PROMOTION = "BEGIN EADMIN.editPromotion(?,?,?,?,?); END;";
	public static final String ADD_PROMOTION = "BEGIN EADMIN.addPromotion(?,?,?,?); END;";
	public static final String GET_COMMENT = "BEGIN EADMIN.getListComment(?,?,?,?); END;";
	public static final String GET_RATING_DIVISION = "BEGIN EADMIN.getRatingDivision(?,?,?,?,?); END;";
	
	public static final String GET_SURVEY = "BEGIN EADMIN.getSurvey(?,?); END;";
	public static final String ADD_SURVEY = "BEGIN EADMIN.addSurvey(?,?,?); END;";
	public static final String EDIT_SURVEY = "BEGIN EADMIN.editSurvey(?,?,?,?,?,?); END;";
	public static final String DELETE_SURVEY = "BEGIN EADMIN.deleteSurvey(?,?); END;";
	
	public static final String GET_ROOM = "BEGIN EADMIN.getRoom(?); END;";
	public static final String EDIT_SMILE = "BEGIN EADMIN.editSmile(?,?,?,?,?,?); END;";
	
	// info
	public static final String GET_LANGUAGE = "BEGIN EADMIN.getLanguage(?); END;";
	
	public static final String GET_SUBJECT_INFO = "BEGIN EADMIN.getSubjectInfo(?,?); END;";
	public static final String ADD_SUBJECT_INFO = "BEGIN EADMIN.addSubjectInfo(?,?,?); END;";
	public static final String EDIT_SUBJECT_INFO = "BEGIN EADMIN.editSubjectInfo(?,?,?,?,?,?,?); END;";
	public static final String DELETE_SUBJECT_INFO = "BEGIN EADMIN.deleteSubjectInfo(?,?); END;";
	
	public static final String GET_CONTENT_INFO = "BEGIN EADMIN.getContentInfo(?,?,?); END;";
	public static final String ADD_CONTENT_INFO = "BEGIN EADMIN.addContentInfo(?,?,?,?); END;";
	public static final String EDIT_CONTENT_INFO = "BEGIN EADMIN.editContentInfo(?,?,?,?,?,?); END;";
	public static final String DELETE_CONTENT_INFO = "BEGIN EADMIN.deleteContentInfo(?,?); END;";
	

	// pms dining subject
	public static final String GET_SUBJECT_DINING = "BEGIN EADMIN.getDiningSubject(?,?,?); END;";
	public static final String ADD_SUBJECT_DINING = "BEGIN EADMIN.addSubjectDining(?,?,?,?,?,?); END;";
	public static final String EDIT_SUBJECT_DINING = "BEGIN EADMIN.editSubjectDining(?,?,?,?,?,?,?); END;";
	public static final String DELETE_SUBJECT_DINING = "BEGIN EADMIN.deleteSubjectDining(?,?); END;";

	// pms dining item
	public static final String GET_ITEM_DINING = "BEGIN EADMIN.getItemDining(?,?,?); END;";
	public static final String ADD_ITEM_DINING = "BEGIN EADMIN.addItemDining(?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_ITEM_DINING = "BEGIN EADMIN.editItemDining(?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_ITEM_DINING = "BEGIN EADMIN.deleteItemDining(?,?); END;";
	
	// speed boat
	public static final String GET_SPEED_BOAT = "BEGIN EADMIN.getSpeedBoatSchedule(?); END;";
	public static final String ADD_SPEED_BOAT = "BEGIN EADMIN.addSpeedBoat(?,?,?); END;";
	public static final String EDIT_SPEED_BOAT = "BEGIN EADMIN.editSpeedBoat(?,?,?,?,?); END;";
	public static final String DELETE_SPEED_BOAT = "BEGIN EADMIN.deleteSpeedBoat(?,?); END;";
	
	public static final String ADD_BOAT_TIME = "BEGIN EADMIN.addBoatTime(?,?,?); END;";
	public static final String EDIT_BOAT_TIME = "BEGIN EADMIN.editBoatTime(?,?,?,?,?); END;";
	public static final String DELETE_BOAT_TIME = "BEGIN EADMIN.deleteBoatTime(?,?); END;";
	
	public static final String GET_SERVICE = "BEGIN EADMIN.getService(?,?); END;";
	public static final String ADD_SERVICE = "BEGIN EADMIN.addService(?,?,?); END;";
	public static final String EDIT_SERVICE = "BEGIN EADMIN.editService(?,?,?,?,?,?,?); END;";
	public static final String DELETE_SERVICE = "BEGIN EADMIN.deleteService(?,?); END;";
	
	public static final String EDIT_RATING = "BEGIN EADMIN.editRating(?,?,?,?,?,?); END;";
	
	public static final String GET_VOTE_SURVEY = "BEGIN EADMIN.getVoteSurvey(?,?,?,?,?); END;";
	
	// staff
	public static final String GET_LIST_STAFF = "BEGIN EADMIN.getListStaff(?); END;";
	public static final String ADD_STAFF = "BEGIN EADMIN.addStaff(?,?,?,?,?); END;";
	public static final String EDIT_STAFF = "BEGIN EADMIN.editStaff(?,?,?,?,?); END;";
	public static final String DELETE_STAFF = "BEGIN EADMIN.deleteStaff(?,?); END;";
	
	// tablet
	public static final String GET_LIST_TABLET = "BEGIN EADMIN.getListTablet(?,?); END;";
	public static final String EDIT_TABLET = "BEGIN EADMIN.editTablet(?,?,?,?); END;";
	
	public static final String GET_VOTE_STAFF = "BEGIN EADMIN.getCompareStaff(?,?,?,?,?,?); END;";
	
	public static final String GET_RATING_CHILD = "BEGIN EADMIN.getRatingChild(?,?,?,?,?,?,?); END;";
	public static final String GET_RATING_ALL = "BEGIN EADMIN.getRatingAll(?,?,?,?,?,?); END;";
	
	public static final String GET_COMPARE_LOCATION = "BEGIN EADMIN.getCompareLocation(?,?,?,?,?); END;";
	
	public static final String GET_STATISTIC_SMILE = "BEGIN EADMIN.getStatisticSmile(?,?,?,?,?); END;";
	public static final String GET_STATISTIC_RATING = "BEGIN EADMIN.getStatisticRating(?,?,?,?,?); END;";
	
	//user
	public static final String GET_LIST_USER = "BEGIN EADMIN.getListUser(?); END;";
	public static final String ADD_USER = "BEGIN EADMIN.addUser(?,?,?,?,?,?,?); END;";
	public static final String EDIT_USER = "BEGIN EADMIN.editUser(?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_USER = "BEGIN EADMIN.deleteUser(?,?); END;";
	public static final String CHANGE_PASSWORD = "BEGIN EADMIN.changePassword(?,?,?,?,?,?); END;";
	public static final String GET_LIST_STORE = "BEGIN EADMIN.getListStore(?); END;";
	
	//rating smile staff
	public static final String GET_RATING_STAFF = "BEGIN EADMIN.getRatingStaff(?,?,?,?,?,?); END;";
	public static final String GET_RATING_CHILD_STAFF = "BEGIN EADMIN.getRatingChildStaff(?,?,?,?,?,?,?); END;";
	public static final String GET_SMILE_STAFF = "BEGIN EADMIN.getSmileStaff(?,?,?,?,?); END;";
	
	public static final String GET_RATING_SMILE_STAFF = "BEGIN EADMIN.getRatingSmileStaff(?,?,?,?,?,?); END;";
	public static final String GET_RATING_CHILD_SMILE_STAFF = "BEGIN EADMIN.getRatingChildSmileStaff(?,?,?,?,?,?,?); END;";
	
	public static final String GET_RATING_SMILE_LOCATION = "BEGIN EADMIN.getRatingSmileLocation(?,?,?,?,?,?); END;";
	public static final String GET_RATING_CHILD_SMILE_LOCATION = "BEGIN EADMIN.getRatingChildSmileLocation(?,?,?,?,?,?,?); END;";
	
	public static final String GET_HISTORY = "BEGIN EADMIN.getHistory(?,?,?,?,?); END;";
	
	//rating detail
	public static final String GET_RATING_DETAIL = "BEGIN EADMIN.getRatingDetail(?,?); END;";
	public static final String ADD_RATING_DETAIL = "BEGIN EADMIN.addRatingDetail(?,?); END;";
	public static final String EDIT_RATING_DETAIL = "BEGIN EADMIN.editRatingDetail(?,?,?,?,?,?); END;";
	public static final String DELETE_RATING_DETAIL = "BEGIN EADMIN.deleteRatingDetail(?,?); END;";
	
	public static final String GET_RATING_DETAIL_STAFF = "BEGIN EADMIN.getRatingDetailStaff(?,?,?,?,?); END;";
	public static final String GET_RATING_DETAIL_LOCATION = "BEGIN EADMIN.getRatingDetailLocation(?,?,?,?,?); END;";
	public static final String GET_RATING_DETAIL_REPORT = "BEGIN EADMIN.getRatingDetailReport(?,?,?,?,?,?); END;";
	
	public static final String GET_DATA_DETAIL = "BEGIN EADMIN.getDataDetail(?,?,?,?,?,?); END;";
	public static final String GET_DATA_STANDARD = "BEGIN EADMIN.getDataStandard(?,?,?,?,?,?); END;";
	public static final String GET_TREND_SMILE = "BEGIN EADMIN.getTrendSmile(?,?,?,?,?); END;";
	public static final String GET_TREND_RATING = "BEGIN EADMIN.getTrendRating(?,?,?,?,?,?); END;";
	
	public static final String GET_CHECKIN_LOCATION = "BEGIN EADMIN.getCheckinLocation(?,?,?,?,?,?); END;";
	public static final String GET_CHECKIN_STAFF = "BEGIN EADMIN.getCheckinStaff(?,?,?,?,?,?); END;";
	public static final String GET_STATISTIC_SMILE_LOCATION = "BEGIN EADMIN.getStaticSmileLocation(?,?,?,?,?,?); END;";
	public static final String GET_STATISTIC_SMILE_STAFF = "BEGIN EADMIN.getStaticSmileStaff(?,?,?,?,?,?); END;";
	
	public static final String GET_DATA_CHECKIN = "BEGIN EADMIN.getDataCheckin(?,?,?,?,?,?); END;";
}


