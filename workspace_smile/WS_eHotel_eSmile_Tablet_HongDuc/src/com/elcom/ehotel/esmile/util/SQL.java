package com.elcom.ehotel.esmile.util;

import com.elcom.ehotel.esmile.dbi.IMBroker;

public class SQL {
	public static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LOCATION = "{call ETABLET.getLocation(?)}";
	public static final String GET_LANGUAGE = "{call ETABLET.getArrayLanguage(?)}";
	public static final String GET_INFO = "{call ETABLET.getInfo(?,?)}";
	public static final String GET_SUBJECT_RATING = "{call ETABLET.getSubjRating(?,?)}";
	public static final String GET_REGISTER = "{call ETABLET.registerLocation(?,?,?,?)}";
	public static final String SET_RATING = "{call ETABLET.setRating(?,?,?,?,?)}";
	public static final String SET_COMMENT = "{call ETABLET.setComment(?,?,?,?)}";
	public static final String LOGIN = "{call ETABLET.login(?,?,?)}";
	public static final String GET_PMSSUBJECT = "{call ETABLET.getPMSSubject(?,?)}";
	public static final String GET_PMSSUBJECTDETAIL = "{call ETABLET.getPMSSubjectDetail(?,?,?)}";
	public static final String SET_SMILE = "{call ETABLET.setSmile(?,?,?)}";

	public static final String GET_SUBJECT_INFO_HOTEL = "{call ETABLET.getSubjectInfoHotel(?,?,?)}";
	public static final String GET_INTEM_BY_SUBJECT_ID = "{call ETABLET.getItemBySubjectID(?,?,?)}";

	public static final String GET_SURVEY = "{call ETABLET.getSurvey(?,?)}";
	public static final String POST_SURVEY = "{call ETABLET.setSurveyRating(?,?,?)}";

	public static final String GET_SUBJECT_DINING = "{call ETABLET.getSubjectDining(?,?,?)}";
	public static final String GET_SUBJECT_DINING_BYSUBJECTID = "{call ETABLET.getSubjectDiningSubjectID(?,?,?,?)}";
	public static final String GET_LIST_FOOD_BY_ITEM = "{call ETABLET.getListFootByItem(?,?,?,?)}";

	public static final String GET_SHIP_SCHEDULE = "{call ETABLET.getSpeedBoat(?,?,?)}";

	public static final String EDIT_SMILE = "{call ETABLET.editSmile(?,?,?,?)}";
	public static final String EDIT_RATING = "{call ETABLET.editRating(?,?,?)}";

	public static final String GET_RATING_NOTIFY = "{call ETABLET.getRatingNotify(?)}";
	public static final String GET_NOTIFY = "{call ETABLET.getNotify(?)}";
	public static final String CONFIRM_RATING = "{call ETABLET.comfirmRating(?,?)}";

	public static final String DELETE_NOTIFY = "{call ETABLET.deleteNotify(?,?)}";

	public static final String SET_RATING_GUEST = "{call ETABLET.setRatingGuest(?,?,?,?,?)}";
	public static final String SET_COMMENT_GUEST = "{call ETABLET.setCommentGuest(?,?,?,?,?)}";

	public static final String GET_MENU = "{call ETABLET.getMenu(?,?,?)}";

	public static final String GET_EXCHANGE = "{call ETABLET.getExchange(?,?)}";
	public static final String GET_ALL_FACULTY = "{call ETABLET.getAllFaculty(?,?,?)}";
	
	public static final String GET_FACULTY = "{call ETABLET.getFaculty(?,?,?)}";
	
	public static final String CHECK_KEY = "{call ETABLET.checkKey(?,?)}";
	public static final String GET_KEY = "{call ETABLET.getKey(?,?)}";
	
	public static final String SET_LANG = "{call ETABLET.setLang(?,?,?)}";
	public static final String REGISTER_TABLET = "{call ETABLET.registerTablet(?,?,?,?,?)}";
	
	public static final String CHECK_KEY_MOBILE = "{call ETABLET.checkKeyMobile(?,?)}";
	
	public static final String POST_IMAGE = "{call ETABLET.postImage(?,?,?,?,?,?)}";
	
	public static final String GET_NOTIFY_ALL = "{call ETABLET.getNotifyAll(?)}";
	public static final String GET_NOTIFY_REAL_TIME = "{call ETABLET.getNotifyRealTime(?)}";
}
