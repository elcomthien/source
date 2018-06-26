package com.elcom.ehotel.esmile.util;

import com.elcom.ehotel.esmile.dbi.IMBroker;

public class SQL {
	public static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LOGIN = "BEGIN EADMIN.getLogin(?,?,?); END;";
	public static final String GET_LOCATION = "BEGIN EADMIN.getLocation(?,?); END;";
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
	public static final String GET_RATING_DIVISION = "BEGIN EADMIN.getRatingDivision(?,?,?,?,?,?); END;";
	public static final String COUNT_FACULTY = "BEGIN EADMIN.countFaculty(?,?); END;";

	public static final String GET_SURVEY = "BEGIN EADMIN.getSurvey(?,?); END;";
	public static final String ADD_SURVEY = "BEGIN EADMIN.addSurvey(?,?,?); END;";
	public static final String EDIT_SURVEY = "BEGIN EADMIN.editSurvey(?,?,?,?,?,?); END;";
	public static final String DELETE_SURVEY = "BEGIN EADMIN.deleteSurvey(?,?); END;";

	public static final String GET_ROOM = "BEGIN EADMIN.getRoom(?); END;";
	public static final String EDIT_SMILE = "BEGIN EADMIN.editSmile(?,?,?,?,?,?); END;";

	// info
	public static final String GET_LANGUAGE = "BEGIN EADMIN.getLanguage(?); END;";

	public static final String GET_SUBJECT_INFO = "BEGIN EADMIN.getSubjectInfo(?,?,?); END;";
	public static final String ADD_SUBJECT_INFO = "BEGIN EADMIN.addSubjectInfo(?,?,?,?); END;";
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

	public static final String GET_SERVICE = "BEGIN EADMIN.getService(?,?,?); END;";
	public static final String ADD_SERVICE = "BEGIN EADMIN.addService(?,?,?,?,?); END;";
	public static final String EDIT_SERVICE = "BEGIN EADMIN.editService(?,?,?,?,?,?,?); END;";
	public static final String DELETE_SERVICE = "BEGIN EADMIN.deleteService(?,?); END;";

	public static final String EDIT_RATING = "BEGIN EADMIN.editRating(?,?,?,?,?,?); END;";
	public static final String DELETE_RATING = "BEGIN EADMIN.deleteRating(?,?); END;";

	public static final String GET_VOTE_SURVEY = "BEGIN EADMIN.getVoteSurvey(?,?,?,?,?); END;";

	// exchange
	public static final String GET_EXCHANGE = "BEGIN EADMIN.getListExchange(?); END;";
	public static final String ADD_EXCHANGE = "BEGIN EADMIN.addExchange(?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_EXCHANGE = "BEGIN EADMIN.editExchange(?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_EXCHANGE = "BEGIN EADMIN.deleteExchange(?,?); END;";

	public static final String GET_LOGO = "BEGIN EADMIN.getLogo(?); END;";
	public static final String CHANGE_LOGO = "BEGIN EADMIN.changeLogo(?,?); END;";

	// welcome
	public static final String GET_TEXT_WELCOME = "BEGIN EADMIN.getTextWelcome(?,?,?); END;";
	public static final String EDIT_TEXT_WELCOME = "BEGIN EADMIN.editTextWelcome(?,?,?,?); END;";

	public static final String GET_SERVICE_TYPE = "BEGIN EADMIN.getServiceType(?); END;";

	// faculty
	public static final String GET_FACULTY = "BEGIN EADMIN.getFaculty(?,?); END;";
	public static final String ADD_FACULTY = "BEGIN EADMIN.addFaculty(?,?,?); END;";
	public static final String EDIT_FACULTY = "BEGIN EADMIN.editFaculty(?,?,?,?,?); END;";
	public static final String DELETE_FACULTY = "BEGIN EADMIN.deleteFaculty(?,?); END;";

	public static final String GET_SMILE_FACULTY = "BEGIN EADMIN.getSmileFaculty(?,?,?); END;";
	public static final String GET_RATING_SMILE = "BEGIN EADMIN.getRatingType(?,?,?,?); END;";

	public static final String GET_RATING_ALL = "BEGIN EADMIN.getRatingAll(?,?,?,?,?); END;";

	public static final String GET_INFO_SURVEY = "BEGIN EADMIN.getSurveyInfo(?,?,?); END;";

	public static final String POST_SURVEY = "BEGIN EADMIN.postSurvey(?,?,?); END;";

	public static final String EDIT_SMILE_SURVEY = "BEGIN EADMIN.editSmileSurvey(?,?,?,?,?); END;";

	public static final String ADD_RATING_SURVEY = "BEGIN EADMIN.addRatingSurvey(?,?); END;";
	public static final String EDIT_RATING_SURVEY = "BEGIN EADMIN.editRatingSurvey(?,?,?,?,?,?); END;";
	public static final String DELETE_RATING_SURVEY = "BEGIN EADMIN.deleteRatingSurvey(?,?); END;";

	// account
	public static final String GET_LIST_ACCOUNT = "BEGIN EADMIN.getListAccount(?); END;";
	public static final String GET_ACCOUNT_BY_ID = "BEGIN EADMIN.getAccountById(?,?); END;";
	public static final String ADD_ACCOUNT = "BEGIN EADMIN.addAccount(?,?,?,?,?,?); END;";
	public static final String EDIT_ACCOUNT = "BEGIN EADMIN.editAccount(?,?,?,?,?,?,?); END;";
	public static final String DELETE_ACCOUNT = "BEGIN EADMIN.deleteAccount(?,?); END;";
	public static final String GET_LIST_ROLE = "BEGIN EADMIN.getListRole(?); END;";
	public static final String ADD_ROLE = "BEGIN EADMIN.addRole(?,?,?); END;";
	public static final String EDIT_ROLE = "BEGIN EADMIN.editRole(?,?,?,?); END;";
	public static final String DELETE_ROLE = "BEGIN EADMIN.deleteRole(?,?); END;";
	public static final String CHANGE_PASSWORD = "BEGIN EADMIN.changePassword(?,?,?,?,?,?); END;";
	
	public static final String ADD_RATING = "BEGIN EADMIN.addRating(?,?,?,?); END;";
	
	

}
