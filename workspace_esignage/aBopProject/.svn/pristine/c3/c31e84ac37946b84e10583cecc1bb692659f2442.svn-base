package elcom.abop.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.SheetCollate;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import elcom.abop.bean.ObjectABOP;
import elcom.abop.bean.ObjectBean;
import elcom.abop.bean.ObjectSchedule;
import elcom.abop.bean.User;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.BasicAction;
import elcom.abop.common.Constant;
import elcom.abop.common.ConstantProperties;
import elcom.abop.util.ModelService;
import elcom.abop.util.ParseXmlService;
import elcom.abop.util.UtilBasic;
import elcom.abop.util.XmlService;

public class ScheduleAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(ScheduleAction.class);
	private String XMLCREATE = "";
	private String XMLRESULT = "";

	public String playlist() throws Exception {
		// get session current
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		// get playlist by username
		XMLCREATE = XmlService.admingetPlaylistByUser(user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.admingetPlaylistByUser(XMLCREATE);
		Constant.getObjectBean().setPlaylist(
				ParseXmlService.admingetPlaylistByUser(XMLRESULT));
		// get subject by username
		XMLCREATE = XmlService.adminGetSubjectByUser(user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.adminGetSubjectByUser(XMLCREATE);
		Constant.getObjectBean().setSubject(
				ParseXmlService.adminGetSubjectByUser(XMLRESULT));
		// get content by username
		XMLCREATE = XmlService.admingetContentByUser(user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.getContentList(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setContent(
				ParseXmlService.getGroupItemMap(XMLRESULT));
		// set content into abopContent using search autocomplete
		UtilBasic.abopContent = Constant.getObjectBean().getContent();
		
		// get layout by USERNAME
		XMLCREATE = XmlService.getLayoutFromGroup("-1", user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.getLayoutName(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setLayout(
				ParseXmlService.getLayoutName(XMLRESULT));
		// get layout item[0]
		String id = "";
		if (Constant.getObjectBean().getLayout().size() > 0) {
			id = Constant.getObjectBean().getLayout().get(0).getId();
		}
		XMLCREATE = XmlService.getItemFromGroupAndLayout("", id, "");
		XMLRESULT = ModelService.getLayout(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setLayoutItem(
				ParseXmlService.getLayoutItemMap(XMLRESULT));
		// get group by user
		XMLCREATE = XmlService.getGroupByUser(user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.admingetGroupList(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setGroup(
				ParseXmlService.getGroupItemName(XMLRESULT));
		// get schedule daily
		XMLCREATE = XmlService.admingetScheduleDaily(user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.admingetScheduleDaily(XMLCREATE);
		Constant.getObjectBean().setScheduleDaily(
				ParseXmlService.admingetScheduleDailyName(XMLRESULT));
		return SUCCESS;
	}
	
	public String ajaxPlaylist() throws Exception {
		// get item in playlist
		String idPlaylist = Constant.getObjectBean().getData();
		// get playlist item
		XMLCREATE = XmlService.xmladmingetPlayListLayout(idPlaylist);
		XMLRESULT = ModelService.admingetPlayListLayout(XMLCREATE);
		// Constant.getObjectBean().setPlaylist(
		// ParseXmlService.getPlaylistItem(XMLRESULT));

		// XMLCREATE = XmlService.xmladmingetPlayListLayout(idPlaylist);
		// XMLRESULT = ModelService.admingetPlayListLayout(XMLCREATE);
		Constant.getObjectBean().setLayout(
				ParseXmlService.getPlayListLayouItem(XMLRESULT));
		
		// get item layout
		XMLCREATE = XmlService.getItemFromGroupAndLayout("", Constant
				.getObjectBean().getLayout().get(0).getIdParent(), "");
		XMLRESULT = ModelService.getLayout(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setLayoutItem(
				ParseXmlService.getLayoutItemMap(XMLRESULT));
		Constant.getObjectBean().setData(ConstantProperties.PATH_SOURCE);
		return SUCCESS;
	}
	public String getSlide() throws Exception{
		String id = Constant.getObjectBean().getData();
		String xml = XmlService.xmladminDelContentText(id);
		String xmlResult = ModelService.adminGetContentIDByForSlide(xml);
		Constant.getObjectBean().setContent(ParseXmlService.adminGetContentIDByForSlide(xmlResult));
		return SUCCESS;
	}
	public String ajaxGetLayout() throws RemoteException {
		String type = Constant.getObjectBean().getData();
		if ("-1".equals(type)) {
			// get layout by Username
			Map<String, Object> session = ActionContext.getContext().getSession();
			User user = (User)session.get(ApplyItemConstant.KEY_USER);
			XMLCREATE = XmlService.getLayoutFromGroup("-1", user.getCreator(), user.getParentCreator());
			XMLRESULT = ModelService.getLayoutName(XMLCREATE);
			logger.info(XMLRESULT);
			Constant.getObjectBean().setLayout(
					ParseXmlService.getLayoutName(XMLRESULT));
		} else {
			Map<String, Object> session = ActionContext.getContext().getSession();
			User user = (User)session.get(ApplyItemConstant.KEY_USER);
			XMLCREATE = XmlService.getLayoutNameType(type, user.getCreator(), user.getParentCreator());
			XMLRESULT = ModelService.getLayoutNameType(XMLCREATE);
			logger.info(XMLRESULT);
			Constant.getObjectBean().setLayout(
					ParseXmlService.getLayoutName(XMLRESULT));
		}
		// get layout item first
		String id = Constant.getObjectBean().getLayout().get(0).getId();
		XMLCREATE = XmlService.getItemFromGroupAndLayout("", id, "");
		XMLRESULT = ModelService.getLayout(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setLayoutItem(
				ParseXmlService.getLayoutItemMap(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxGetContentByType() throws Exception {
		JSONObject jsobj = new JSONObject(Constant.getObjectBean().getData());
		String type = jsobj.getString(ApplyItemConstant.KEY_TYPE);
		String subjectId = jsobj.getString(ApplyItemConstant.KEY_SUBJECT_ID);
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		// get all content
		if ("0".equals(type) && "0".equals(subjectId)) {
			XMLCREATE = XmlService.admingetContentByUser(user.getCreator(), user.getParentCreator());
			XMLRESULT = ModelService.getContentList(XMLCREATE);
			logger.info(XMLRESULT);
			Constant.getObjectBean().setContent(
					ParseXmlService.getGroupItemMap(XMLRESULT));
		} else if ("0".equals(type) && !"0".equals(subjectId)) {
			// get all content by subject id
			XMLCREATE = XmlService.getAllContent("-1", subjectId, user.getCreator(), user.getParentCreator());
			XMLRESULT = ModelService.adminGetAllContent(XMLCREATE);
			Constant.getObjectBean().setContent(
					ParseXmlService.getGroupItemMap(XMLRESULT));
		} else if (!"0".equals(type) && !"0".equals(subjectId)) {
			// get all content by subject id and type
			XMLCREATE = XmlService.getAllContent(type, subjectId, user.getCreator(), user.getParentCreator());
			XMLRESULT = ModelService.adminGetAllContent(XMLCREATE);
			Constant.getObjectBean().setContent(
					ParseXmlService.getGroupItemMap(XMLRESULT));
		} else if (!"0".equals(type) && "0".equals(subjectId)) {
			// get all content by subject id
			XMLCREATE = XmlService.getAllContent(type, "-1", user.getCreator(), user.getParentCreator());
			XMLRESULT = ModelService.adminGetAllContent(XMLCREATE);
			Constant.getObjectBean().setContent(
					ParseXmlService.getGroupItemMap(XMLRESULT));
		}
		// set content into abopContent using search autocomplete
		UtilBasic.abopContent = Constant.getObjectBean().getContent();
		return SUCCESS;
	}

	public String ajaxAutoComplete() throws Exception {
		JSONObject obj = new JSONObject(Constant.getObjectBean().getData());
		String type = obj.getString(ApplyItemConstant.KEY_TYPE);
		String text = obj.getString(ApplyItemConstant.KEY_TEXT);
		Constant.getObjectBean().setContent(UtilBasic.autoComplete(type, text));
		return SUCCESS;
	}

	public String ajaxGetLayoutItem() throws RemoteException {
		String id = Constant.getObjectBean().getData();
		XMLCREATE = XmlService.getItemFromGroupAndLayout("", id, "");
		XMLRESULT = ModelService.getLayout(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setLayoutItem(
				ParseXmlService.getLayoutItemMap(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxSavePlaylistItem() throws JSONException, RemoteException {
		try {
			JSONObject jsonObj = new JSONObject(Constant.getObjectBean()
					.getData());
			JSONArray jsonArray = jsonObj
					.getJSONArray(ApplyItemConstant.KEY_ITEMS);
			Map<String, Object> session = ActionContext.getContext().getSession();
			User user = (User)session.get(ApplyItemConstant.KEY_USER);
			String name = jsonObj.getString(ApplyItemConstant.KEY_NAME);
			String desc = jsonObj.getString(ApplyItemConstant.KEY_DESCRIPTION);
			// Create playlist name
			XMLCREATE = XmlService.xmladminNewPlaylistName("-1", name, desc, user.getCreator(), user.getParentCreator());
			int playlistId = ModelService.adminNewPlaylistName(XMLCREATE);
			// send to client
			Constant.getObjectBean().setData(String.valueOf(playlistId));
			// Create playlist item
			int length = jsonArray.length();
			ArrayList<ObjectABOP> obj = new ArrayList<ObjectABOP>();
			for (int i = 0; i < length; i++) {
				ObjectABOP ab = new ObjectABOP();
				ab.setName(jsonArray.getJSONObject(i).getString(
						ApplyItemConstant.KEY_NAME));
				// get id layout
				ab.setIdParent(jsonArray.getJSONObject(i).getString(
						ApplyItemConstant.KEY_ID_PARENT));
				ab.setId(jsonArray.getJSONObject(i).getString(
						ApplyItemConstant.KEY_CONTENT));
				ab.setStartTime(jsonArray.getJSONObject(i).getString(
						ApplyItemConstant.KEY_TIME_START));
				ab.setEndTime(jsonArray.getJSONObject(i).getString(
						ApplyItemConstant.KEY_TIME_END));
				// get id item layout
				ab.setIdSubLayout(jsonArray.getJSONObject(i).getString(
						ApplyItemConstant.KEY_ID));
				obj.add(ab);
			}

			// Create playlist item
			XMLCREATE = XmlService.adminCreatePlaylist(playlistId, obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		int result = ModelService.adminCreatePlaylist(XMLCREATE);
		if (result <= 0) {
			// return ERROR;
		}
		return SUCCESS;
	}

	public String ajaxDeletePlaylist() throws Exception {
		// delete Playlist
		String idPlaylist = Constant.getObjectBean().getData();
		XMLCREATE = XmlService.xmladminDelPlaylist(idPlaylist);
		ModelService.adminDelPlaylist(XMLCREATE);
		return SUCCESS;
	}

	public String ajaxUpdatePlaylistItem() throws RemoteException,
			JSONException {
		JSONObject jsonObj = new JSONObject(Constant.getObjectBean().getData());
		JSONArray jsonArray = jsonObj.getJSONArray(ApplyItemConstant.KEY_ITEMS);
		String id = jsonObj.getString(ApplyItemConstant.KEY_ID);
		String name = jsonObj.getString(ApplyItemConstant.KEY_NAME);
		String desc = jsonObj.getString(ApplyItemConstant.KEY_DESCRIPTION);
		String idPlaylistDelete = jsonObj.getString(ApplyItemConstant.KEY_ID_ITEM_DELETE);
		// Update playlist name
		XMLCREATE = XmlService.xmladminUpdatePlayListName(id, name, desc);
		ModelService.adminUpdatePlayListName(XMLCREATE);
		// Note: Update playlist item and Insert playlist item new
		int length = jsonArray.length();
		ArrayList<ObjectABOP> objInsert = new ArrayList<ObjectABOP>();
		ArrayList<ObjectABOP> objUpdate = new ArrayList<ObjectABOP>();
		ArrayList<ObjectABOP> objDelete = new ArrayList<ObjectABOP>();
		for (int i = 0; i < length; i++) {
			ObjectABOP ab = new ObjectABOP();
			ab.setName(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_NAME));
			// get id layout
			ab.setIdParent(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_ID_PARENT));
			ab.setId(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_CONTENT));
			ab.setStartTime(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_TIME_START));
			ab.setEndTime(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_TIME_END));
			// get id item layout
			ab.setIdSubLayout(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_ID));
			ab.setIdItem(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_ID_ITEM));
			if ("-1".equals(ab.getIdItem())) {
				objInsert.add(ab);
			} else {
				objUpdate.add(ab);
			}
		}
		if (objUpdate.size() > 0) {
			// Update playlist item update
			XMLCREATE = XmlService.xmladminUpdatePlaylistTime(objUpdate);
			ModelService.adminUpdatePlaylistTime(XMLCREATE);
		}
		// Insert playlist item new
		if (objInsert.size() > 0) {
			XMLCREATE = XmlService.adminCreatePlaylist(Integer.parseInt(id),
					objInsert);
			ModelService.adminCreatePlaylist(XMLCREATE);
		}
		// delete playlist items
		XMLCREATE = XmlService.abopDeletePlaylistItems(idPlaylistDelete);
		ModelService.abopDeletePlaylistItems(XMLCREATE);
		return SUCCESS;
	}

	public String ajaxDailyScheduleItem() throws RemoteException {
		String dailyId = Constant.getObjectBean().getData();
		// get list playlist by dailyId
		XMLCREATE = XmlService.xmlDailyScheduleItem(dailyId);
		XMLRESULT = ModelService.admingetScheduleDailyItem(XMLCREATE);
		Constant.getObjectBean().setPlaylist(
				ParseXmlService.admingetScheduleDailyGroupMap(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxSaveScheduleDaily() throws JSONException, RemoteException {
		// save item schedule daily
		JSONObject jsonObj = new JSONObject(Constant.getObjectBean().getData());
		JSONArray jsonArray = jsonObj.getJSONArray(ApplyItemConstant.KEY_ITEMS);
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String name = jsonObj.getString(ApplyItemConstant.KEY_NAME);
		String desc = jsonObj.getString(ApplyItemConstant.KEY_DESCRIPTION);
		// save name schedule daily
		XMLCREATE = XmlService.xmlDailyScheduleName(user.getCreator(),user.getParentCreator(), name, desc);
		String dailyId = ModelService.adminNewScheduleDailyName(XMLCREATE);
		// send to client
		Constant.getObjectBean().setData(dailyId);
		// save schedule daily item
		int length = jsonArray.length();
		ArrayList<ObjectABOP> obj = new ArrayList<ObjectABOP>();
		for (int i = 0; i < length; i++) {
			ObjectABOP ab = new ObjectABOP();
			ab.setName(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_NAME));
			ab.setId(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_ID));
			ab.setStartTime(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_TIME_START));
			ab.setEndTime(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_TIME_END));
			obj.add(ab);
		}
		XMLCREATE = XmlService.xmlScheduleDaily(dailyId, obj);
		ModelService.adminNewScheduleDaily(XMLCREATE);
		return SUCCESS;
	}

	public String ajaxUpdateScheduleDailyName() throws JSONException,
			RemoteException {
		JSONObject jsonObj = new JSONObject(Constant.getObjectBean().getData());
		String name = jsonObj.getString(ApplyItemConstant.KEY_NAME);
		String id = jsonObj.getString(ApplyItemConstant.KEY_ID);
		String desc = jsonObj.getString(ApplyItemConstant.KEY_DESCRIPTION);
		// update schedule daily name
		XMLCREATE = XmlService.adminUpdateScheduleDailyName(id, name, desc);
		ModelService.adminUpdateScheduleDailyName(XMLCREATE);

		return SUCCESS;
	}

	public String ajaxUpdateScheduleDailyItem() throws JSONException,
			RemoteException {
		JSONObject jsonObj = new JSONObject(Constant.getObjectBean().getData());
		JSONArray jsonArray = jsonObj.getJSONArray(ApplyItemConstant.KEY_ITEMS);
		String id = jsonObj.getString(ApplyItemConstant.KEY_ID);
		// update playlistitem and insert new playlist
		int length = jsonArray.length();
		ArrayList<ObjectABOP> objInsert = new ArrayList<ObjectABOP>();
		ArrayList<ObjectABOP> objUpdate = new ArrayList<ObjectABOP>();
		for (int i = 0; i < length; i++) {
			ObjectABOP ab = new ObjectABOP();
			ab.setName(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_NAME));
			ab.setId(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_ID));
			ab.setStartTime(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_TIME_START));
			ab.setEndTime(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_TIME_END));
			ab.setIdItem(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_ID_ITEM));
			if ("-1".equals(ab.getIdItem())) {
				objInsert.add(ab);
			} else {
				objUpdate.add(ab);
			}
		}
		if (objInsert.size() > 0) {
			// Insert new
			XMLCREATE = XmlService.xmlScheduleDaily(id, objInsert);
			Constant.getObjectBean().setData(ModelService.adminNewScheduleDaily(XMLCREATE));
		}
		if (objUpdate.size() > 0) {
			// Update
			XMLCREATE = XmlService.xmladminUpdateScheduleDailyTime(objUpdate);
			ModelService.adminUpdateScheduleDailyTime(XMLCREATE);
		}
		return SUCCESS;
	}

	public String ajaxDeleteItemDaily() throws RemoteException {
		String id = Constant.getObjectBean().getData();
		XMLCREATE = XmlService.xmladminDelSchduledailyTime(id);
		ModelService.adminDelSchduledailyTime(XMLCREATE);
		return SUCCESS;
	}

	public String ajaxDeleteDaily() throws RemoteException {
		String id = Constant.getObjectBean().getData();
		XMLCREATE = XmlService.xmlRemoveDailyName(id);
		ModelService.adminDelScheduleDailyName(XMLCREATE);
		return SUCCESS;
	}
	public String ajaxPeriodicScheduleItem() throws RemoteException {
		String groupId = Constant.getObjectBean().getData();
		// get schedule periodic by group id
		XMLCREATE = XmlService.admingetSchedulePeriodic(groupId);
		XMLRESULT = ModelService.admingetSchedulePeriodic(XMLCREATE);
		Constant.getObjectBean().setSchedulePeriodic(
				ParseXmlService.admingetSchedulePeriodic(XMLRESULT));
		Constant.getObjectBean().setData(groupId);
		return SUCCESS;
	}
	public String ajaxSaveSchedulePeriodic() throws JSONException,
			RemoteException {
		JSONObject obj = new JSONObject(Constant.getObjectBean().getData());
		String groupId = obj.getString(ApplyItemConstant.KEY_GROUP_ID);
		String dailyId = obj.getString(ApplyItemConstant.KEY_DAILY_ID);
		String name = obj.getString(ApplyItemConstant.KEY_NAME);
		String time = obj.getString("time");
		String startDate = obj.getString(ApplyItemConstant.KEY_START_DATE);
		String endData = obj.getString(ApplyItemConstant.KEY_END_DATE);
		// save schedule periodic name
		XMLCREATE = XmlService.xmladminNewSchedulePeri(groupId, name,
				startDate, endData);
		String periodicId = ModelService.adminNewSchedulePeriName(XMLCREATE);
		// save schedule daily into periodic
		XMLCREATE = XmlService.xmladminNewSchedulePeriItem(dailyId, periodicId);
		ModelService.adminNewSchedulePeri(XMLCREATE);
		Constant.getObjectBean().setData(groupId);
		if("-1".equals(time)){
			// Push content into settopbox
			XMLCREATE = XmlService.xmlPushContentIntoSTB(dailyId, groupId);
			ModelService.pushContentIntoSTBAuto(XMLCREATE);
		} else {
			Task<ObjectSchedule> task = Constant.getTaskConstant();
			ObjectSchedule schedule = new ObjectSchedule();
			schedule.setTimer(Integer.parseInt(time));
			schedule.setIdDaily(dailyId);
			schedule.setIdPeriodic(periodicId);
			schedule.setIdGroup(groupId);
			task.insert(schedule);
		}
		
		return SUCCESS;
	}

	public String ajaxUpdatePeriodic() throws Exception {
		JSONObject obj = new JSONObject(Constant.getObjectBean().getData());
		String idGroup = obj.getString(ApplyItemConstant.KEY_ID);
		String time = obj.getString("time");
		String periodicId = obj.getString(ApplyItemConstant.KEY_PERIODIC_ID);
		String name = obj.getString(ApplyItemConstant.KEY_NAME);
		String startDate = obj.getString(ApplyItemConstant.KEY_START_DATE);
		String endData = obj.getString(ApplyItemConstant.KEY_END_DATE);
		// update periodic
		XMLCREATE = XmlService.xmladminUpdateSchedulePeri(periodicId, time, name,
				endData, startDate);
		ModelService.adminUpdateSchedulePeri(XMLCREATE);
		// get daily id
		XMLCREATE = XmlService.xmladmingetItemScheduleDailyForPerio(periodicId);
		XMLRESULT = ModelService.admingetItemScheduleDailyForPerio(XMLCREATE);
		ArrayList<HashMap<String, String>> daily = ParseXmlService
				.viewadmingetItemScheduleDailyForPerio(XMLRESULT);
		
		if (daily.size() > 0) {
			String dailyId = daily.get(0).get(ApplyItemConstant.KEY_ID);
			if("-1".equals(time)){
				// Push content into settopbox
				XMLCREATE = XmlService.xmlPushContentIntoSTB(dailyId, idGroup);
				ModelService.pushContentIntoSTBAuto(XMLCREATE);
			} else {
				Task<ObjectSchedule> task = Constant.getTaskConstant();
				ObjectSchedule schedule = new ObjectSchedule();
				schedule.setTimer(Integer.parseInt(time));
				schedule.setIdDaily(dailyId);
				schedule.setIdPeriodic(periodicId);
				schedule.setIdGroup(idGroup);
				task.insert(schedule);
			}
			
		}

		return SUCCESS;
	}

	public String ajaxDeletePeriodic() throws Exception {
		JSONObject obj = new JSONObject(Constant.getObjectBean().getData());
		String periodicId = obj.getString(ApplyItemConstant.KEY_PERIODIC_ID);
		String groupId = obj.getString(ApplyItemConstant.KEY_GROUP_ID);
		// delete periodic
		XMLCREATE = XmlService.xmladminDelSchedulePeri(periodicId);
		ModelService.adminDelSchedulePeri(XMLCREATE);
		// delete content in stb
		XMLCREATE = XmlService.adminDeleteAllContentInSTB(groupId);
		ModelService.adminDeleteAllContentAllSTBInGroup(XMLCREATE);
		// check periodic in task
		Task<ObjectSchedule> task = Constant.getTaskConstant();
		ArrayList<ObjectSchedule> list = task.readItem();
		int length = list.size();
		for(int  i = 0; i < length; i++){
			if(periodicId.equals(list.get(i).getIdPeriodic())){
				task.removeItem(list.get(i).getIdPeriodic());	
			}
		}
		return SUCCESS;
	}

	@Override
	public ObjectBean getModel() {
		//Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

}
