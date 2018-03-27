package com.elcom.ehotel.esmile.util;

import com.elcom.ehotel.esmile.dbi.IMBroker;

public class SQL {
	public static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LOCATION = "{call ETABLET.getLocation(?)}";
	public static final String GET_LANGUAGE = "{call ETABLET.getArrayLanguage(?)}";
	public static final String GET_INFO = "{call ETABLET.getInfo(?,?,?,?)}";
	public static final String GET_SUBJECT_RATING = "{call ETABLET.getSubjRating(?,?)}";
	public static final String GET_REGISTER = "{call ETABLET.registerLocation(?,?,?,?)}";
	public static final String SET_RATING = "{call ETABLET.setRating(?,?,?,?)}";
	public static final String SET_COMMENT = "{call ETABLET.setComment(?,?,?,?)}";
	public static final String LOGIN = "{call ETABLET.login(?,?,?)}";
	public static final String GET_PMSSUBJECT = "{call ETABLET.getPMSSubject(?,?)}";
	public static final String GET_PMSSUBJECTDETAIL = "{call ETABLET.getPMSSubjectDetail(?,?,?)}";
	public static final String SET_SMILE = "{call ETABLET.setSmile(?,?,?)}";

	public static final String GET_SUBJECT_INFO_HOTEL = "{call ETABLET.getSubjectInfoHotel(?,?,?)}";
	public static final String GET_INTEM_BY_SUBJECT_ID = "{call ETABLET.getItemBySubjectID(?,?,?,?)}";

	public static final String GET_SURVEY = "{call ETABLET.getSurvey(?,?)}";
	public static final String POST_SURVEY = "{call ETABLET.setSurveyRating(?,?,?,?)}";

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

	public static final String SET_RATING_GUEST = "{call ETABLET.setRatingGuest(?,?,?,?,?,?,?,?)}";
	public static final String SET_COMMENT_GUEST = "{call ETABLET.setCommentGuest(?,?,?,?,?,?,?,?)}";

	// new api
	public static final String POST_REGISTER = "{call ETABLET.registerKey(?,?,?,?)}";
	public static final String GET_LOCATIONS = "{call ETABLET.getStore(?)}";
	public static final String GET_INFOS = "{call ETABLET.getInfoTablet(?,?,?,?)}";
	public static final String POST_LOGIN = "{call ETABLET.loginStaff(?,?,?,?)}";
	public static final String POST_LOGOUT = "{call ETABLET.logoutStaff(?,?,?)}";
	public static final String CHECK_LOGIN = "{call ETABLET.checkLoginStaff(?,?)}";
	public static final String POST_SMILE = "{call ETABLET.setSmileStaff(?,?,?,?)}";
	public static final String POST_RATING = "{call ETABLET.setRatingStaff(?,?,?,?,?)}";
	public static final String POST_RATING_CHILD = "{call ETABLET.setRatingChildStaff(?,?,?,?,?,?)}";
	public static final String GET_EMPLOYEE = "{call ETABLET.getStaff(?)}";
	public static final String POST_REREGISTER = "{call ETABLET.reRegister(?,?)}";

	public static final String GET_MOBILE_NOTIFY = "{call ETABLET.getMobileNotify(?)}";
	public static final String GET_MOBILE_NOTIFY_ALL = "{call ETABLET.getMobileNotifyAll(?,?)}";
	public static final String CONFIRM_MOBILE_NOTIFY = "{call ETABLET.comfirmMobileNotify(?,?,?)}";
	public static final String DELETE_MOBILE_NOTIFY = "{call ETABLET.deleteMobileNotify(?,?,?)}";

	// procedure getMobileNotify(out_data out string_arr);
	// procedure getMobileNotifyAll(userid_ in varchar,out_data out string_arr);
	// procedure comfirmMonileNotify(idin in varchar, userid_ in varchar,
	// outData out string_arr);
	// procedure deleteMobileNotify(idin in varchar2, userid_ in varchar,
	// outData out string_arr);

	// procedure getInfoTablet(keyin in varchar,langidin in varchar, typein in
	// varchar, out_data out string_arr);
	// procedure setRatingStaff(keyin in varchar, smileidin in varchar,
	// ratingidin in varchar, staffidin in varchar,out_data out string_arr);
	// procedure setSmileStaff(keyin in varchar, smileidin in varchar, staffidin
	// in varchar,out_data out string_arr);
	// procedure checkLoginStaff(idstaffin_ in varchar, out_data out
	// string_arr);
	// procedure logoutStaff(idstaffin_ in varchar, keyin in varchar, out_data
	// out string_arr);
	// procedure loginStaff(usernamein in varchar, passwordin in varchar, keyin
	// in varchar,out_data out string_arr);
	// procedure registerKey(idstorein in varchar, ipin in varchar, namein in
	// varchar,out_data out string_arr);
	// procedure setRatingChildStaff(keyin in varchar, smileidin in varchar,
	// idratingin in varchar, arrayratingidin in varchar, staffidin in
	// varchar,out_data out string_arr);
	// procedure getStore(out_data out string_arr);
	// procedure getStaff(out_data out string_arr);

}
