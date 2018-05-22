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
	
	public static final String GET_WELCOME = "BEGIN EADMIN.getListWelcome(?,?); END;";
	public static final String EDIT_WELCOME = "BEGIN EADMIN.editWelcome(?,?,?,?); END;";
	
}
