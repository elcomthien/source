package com.elcom.ehotel.esmile.util;

import com.elcom.ehotel.esmile.dbi.IMBroker;

public class SQL {
	public static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LAYOUT_ID = "BEGIN PRO_GETSEQLAYOUT(?); END;";
	public static final String ADD_LAYOUT_URL = "BEGIN PRO_ADDLAYOUTURL(?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String GET_LAYOUT = "BEGIN PRO_GETLAYOUT(?,?,?,?); END;";
	public static final String EDIT_LAYOUT = "BEGIN PRO_EDITLAYOUT(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String UPDATE_STATUS_LAYOUT = "BEGIN PRO_UPDATESTATUSLAYOUT(?,?,?,?,?); END;";
	public static final String DELETE_LAYOUT = "BEGIN PRO_DELETELAYOUT(?,?,?); END;";
	public static final String ADD_LAYOUT_EMAIL = "BEGIN PRO_ADD_LAYOUT_EMAIL(?,?,?,?,?,?,?,?); END;";
	public static final String GET_LIST_LAYOUT = "BEGIN PRO_GET_LIST_LAYOUT(?,?,?); END;";
	public static final String GET_LAYOUT_EMAIL = "BEGIN PRO_GET_LAYOUT_EMAIL(?,?,?); END;";
	public static final String SET_SEND_EMAIL = "BEGIN PRO_SET_SEND_EMAIL(?,?,?); END;";

	public static final String LOGIN = "BEGIN PRO_LOGIN(?,?,?); END;";
	public static final String ADD_PAGE = "BEGIN PRO_ADDPAGE(?,?,?,?,?,?,?); END;";
	public static final String ADD_OBJECT_CONTENT = "BEGIN PRO_ADDOBJECTCONTENT(?,?,?,?,?,?,?,?,?,?); END;";
	public static final String ADD_OBJECT = "BEGIN PRO_ADDOBJECT(?,?,?,?,?,?,?); END;";

	public static final String GET_SUBJECT_TEMPLATE = "BEGIN PRO_GETSUBJECT_TEMPLATE(?,?,?); END;";
	public static final String GET_TEMPLATE = "BEGIN PRO_GETTEMPLATE(?,?,?); END;";

	public static final String GET_LIST_ACCOUNT = "BEGIN PRO_GET_LIST_ACCOUNT(?,?,?); END;";
	public static final String ADD_ACCOUNT = "BEGIN PRO_ADDACCOUNT(?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_ACCOUNT = "BEGIN PRO_EDITACCOUNT(?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String RESET_PASSWORD = "BEGIN PRO_RESETPASSWORD(?,?,?,?,?); END;";
	public static final String DELETE_ACCOUNT = "BEGIN PRO_DELETEACCOUNT(?,?,?,?); END;";
	public static final String SET_BACKGROUND = "BEGIN PRO_SET_BACKGROUND(?,?,?,?); END;";

	public static final String GET_ROLE = "BEGIN PRO_GETROLE(?,?,?); END;";
	public static final String ADD_ROLE = "BEGIN PRO_ADD_ROLE(?,?,?,?,?,?); END;";
	public static final String EDIT_ROLE = "BEGIN PRO_EDIT_ROLE(?,?,?,?,?,?,?); END;";

	public static final String GET_LIST_ZONE = "BEGIN PRO_GET_LIST_ZONE(?,?,?); END;";
	public static final String ADD_ZONE = "BEGIN PRO_ADD_ZONE(?,?,?,?,?,?,?); END;";
	public static final String EDIT_ZONE = "BEGIN PRO_EDIT_ZONE(?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_ZONE = "BEGIN PRO_DELETE_ZONE(?,?,?); END;";
	public static final String UPDATE_STATUS_ZONE = "BEGIN PRO_UPDATE_STATUS_ZONE(?,?,?,?); END;";
	public static final String GET_ZONE = "BEGIN PRO_GET_ZONE(?,?,?); END;";

	public static final String POST_RATING_LAYOUT = "BEGIN PRO_POST_RATING_LAYOUT(?,?,?,?,?,?,?); END;";
	public static final String POST_RATING_PAGE = "BEGIN PRO_POST_RATING_PAGE(?,?,?,?,?); END;";
	public static final String POST_RATING_VOTE = "BEGIN PRO_POST_RATING_VOTE(?,?,?,?,?); END;";

	public static final String GET_SESSION = "BEGIN PRO_GETSESSION(?,?); END;";
	public static final String UPDATE_SESSION = "BEGIN PRO_UPDATESESSION(?,?,?); END;";

	public static final String GET_INFO_FILTER = "BEGIN PRO_GETINFOFILTER(?,?,?); END;";

	public static final String GET_DEVICE = "BEGIN PRO_GET_LIST_DEVICE(?,?,?); END;";
	public static final String ADD_DEVICE = "BEGIN PRO_ADDDEVICE(?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_DEVICE = "BEGIN PRO_EDIT_DEVICE(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_DEVICE = "BEGIN PRO_DELETE_DEVICE(?,?,?,?); END;";

	public static final String GET_FEEDBACK_LAYOUT = "BEGIN PRO_GETFEEDBACKLAYOUT(?,?,?); END;";

	public static final String GET_PLUGIN = "BEGIN PRO_GETPLUGIN(?,?,?); END;";
	public static final String ADD_PLUGIN = "BEGIN PRO_ADDPLUGIN(?,?,?,?,?,?,?,?,?,?); END;";
	public static final String ADD_OBJECT_PLUGIN = "BEGIN PRO_ADDPLUGINOBJECT(?,?,?,?,?); END;";
	public static final String EDIT_PLUGIN = "BEGIN PRO_EDITPLUGIN(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_PLUGIN = "BEGIN PRO_DELETEPLUGIN(?,?,?,?); END;";
	public static final String ADD_PLUGIN_EMAIL = "BEGIN PRO_ADDPLUGINEMAIL(?,?,?,?,?,?,?,?,?); END;";
	public static final String POST_RATING_SELECTED = "BEGIN PRO_POST_PLUGIN_SELECTED(?,?,?,?,?); END;";
	public static final String POST_RATING_PLUGIN = "BEGIN PRO_POST_RATING_PLUGIN(?,?,?,?,?); END;";
	public static final String GET_FEEDBACK_PLUGIN_DETAIL = "BEGIN PRO_GETFEEDBACK_PLUGIN_DETAIL(?,?,?,?,?); END;";
	public static final String GET_FEEDBACK_PLUGIN = "BEGIN PRO_GETFEEDBACK_PLUGIN(?,?,?,?,?); END;";
	public static final String GET_PLUGIN_EMAIL = "BEGIN PRO_GET_PLUGIN_MAIL(?,?,?); END;";
	public static final String GET_PLUGIN_BY_ID = "BEGIN PRO_GET_PLUGIN_BY_ID(?,?); END;";

	public static final String BOX_LOGIN = "BEGIN PRO_BOX_LOGIN(?,?,?,?); END;";
	public static final String BOX_GET_LINK = "BEGIN PRO_BOX_GETLINK(?,?,?); END;";
	public static final String BOX_GET_INFO = "BEGIN PRO_BOX_GET_INFO(?,?,?,?,?,?,?,?,?); END;";

	public static final String GET_LIST_LOCATION = "BEGIN PRO_GET_LIST_LOCATION(?,?,?); END;";

	// ///report
	public static final String GET_REPORT_OVERVIEW = "BEGIN PRO_GET_REPORT_OVERVIEW(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String GET_REPORT_RESPONSE = "BEGIN PRO_GET_REPORT_RESPONSE(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String GET_REPORT_FEEDBACK = "BEGIN PRO_GET_REPORT_FEEDBACK(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String GET_REPORT_DEVICE = "BEGIN PRO_GET_REPORT_DEVICE(?,?,?,?,?); END;";
	
	public static final String SAVE_REPORT = "BEGIN PRO_SAVE_REPORT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String GET_SAVE_REPORT = "BEGIN PRO_GET_SAVE_REPORT(?,?,?); END;";
	public static final String DELETE_SCHEDULE_REPORT = "BEGIN PRO_DELETE_SCHEDULE_REPORT(?,?,?); END;";
	public static final String GET_SCHEDULE_REPORT = "BEGIN PRO_GET_SCHEDULE_REPORT(?,?,?); END;";
	
	
	public static final String GET_RESPONDENT = "BEGIN PRO_GET_RESPONDENT(?,?,?,?,?); END;";
	public static final String ADD_TAGS_RESPONDENT = "BEGIN PRO_ADD_TAGS_RESPONDENT(?,?,?,?); END;";
	public static final String UPDATE_FLAG = "BEGIN PRO_UPDATE_FLAG(?,?,?,?); END;";
	public static final String UPDATE_IMPORTANT = "BEGIN PRO_UPDATE_IMPORTANT(?,?,?,?); END;";
	public static final String DELETE_RESPONDENT = "BEGIN PRO_DELETE_RESPONDENT(?,?,?); END;";
	public static final String GET_RESPONDENT_DETAIL = "BEGIN PRO_GET_RESPONDENT_DETAIL(?,?,?); END;";

	public static final String LOG_ACTIVITY = "BEGIN PRO_ADD_ACTIVITY(?,?,?,?,?,?); END;";
	public static final String GET_LIST_ACTIVITY = "BEGIN PRO_GET_ACTIVITY(?,?,?,?); END;";
	
	public static final String GET_GEO_LOCATION_DEVICE = "BEGIN PRO_GET_GEO_LOCATION_DEVICE(?,?,?); END;";
	public static final String GET_DASHBOEARD_DEVICE = "BEGIN PRO_GET_DASHBOARD_DEVICE(?,?,?,?,?); END;";
	public static final String GET_DASHBOEARD_OVERVIEW = "BEGIN PRO_GET_DASHBOARD_OVERVIEW(?,?,?,?,?); END;";
	
	public static final String RENEWAL_SESSION = "BEGIN PRO_RENEWAL_SESSION(?,?,?); END;";
	
	public static final String GET_NOTIFICATION = "BEGIN PRO_GET_NOTIFICATION(?,?,?,?); END;";
	
}
