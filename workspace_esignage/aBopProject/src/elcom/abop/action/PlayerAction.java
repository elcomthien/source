package elcom.abop.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import elcom.abop.bean.ObjectABOP;
import elcom.abop.bean.ObjectBean;
import elcom.abop.bean.User;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.BasicAction;
import elcom.abop.common.Constant;
import elcom.abop.common.ConstantProperties;
import elcom.abop.util.ModelService;
import elcom.abop.util.ParseXmlService;
import elcom.abop.util.UtilBasic;
import elcom.abop.util.XmlService;

public class PlayerAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(PlayerAction.class);
	private String XMLCREATE = "";
	private String XMLRESULT = "";

	public String player() throws Exception {
		// get session current
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		// get group by username
		XMLCREATE = XmlService.getGroupByUser(user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.admingetGroupList(XMLCREATE);
		Constant.getObjectBean().setGroup(
				ParseXmlService.getGroupItemName(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxplayer() throws Exception {
		logger.info("request: " + Constant.getObjectBean().getData());
		// get list stb in group
		String groupId = Constant.getObjectBean().getData();
		return getSetupBox(groupId);
	}

	public String ajaxAddToList() throws Exception {
		// add setupbox from ungroup to group current
		logger.info("data: " + Constant.getObjectBean().getData());
		JSONObject json = new JSONObject(Constant.getObjectBean().getData());
		String groupId = json.getString(ApplyItemConstant.KEY_GROUP_ID);
		String playerId = json.getString(ApplyItemConstant.KEY_PLAYER_ID);
		XMLCREATE = XmlService.addStbIntoGroupXml(groupId, playerId);
		logger.info("XML: " + XMLCREATE);
		ModelService.addStbIntoGroup(XMLCREATE);
		// reload group DB
		return getSetupBox(groupId);
	}

	public String ajaxRemoveFromList() throws Exception {
		// move setupbox from group current to ungroup
		JSONObject json = new JSONObject(Constant.getObjectBean().getData());
		String groupId = json.getString(ApplyItemConstant.KEY_GROUP_ID);
		String playerId = json.getString(ApplyItemConstant.KEY_PLAYER_ID);
		XMLCREATE = XmlService.adminRemoveSTBOutToGroup(playerId);
		ModelService.adminRemoveSTBOutToGroup(XMLCREATE);
		// reload group DB
		return getSetupBox(groupId);
	}

	public String ajaxDetailPlayer() throws RemoteException, JSONException {
		// get content setopbox
		String playerId = Constant.getObjectBean().getData();
		XMLCREATE = XmlService.admingetContentStb(playerId);
		XMLRESULT = ModelService.admingetContentStb(XMLCREATE);
		Constant.getObjectBean().setContent(
				ParseXmlService.getContentStb(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxDeleteContentPlayer() throws JSONException,
			RemoteException {
		JSONObject obj = new JSONObject(Constant.getObjectBean().getData());
		String idPlayer = obj.getString(ApplyItemConstant.KEY_ID_PLAYER);
		String idContent = obj.getString(ApplyItemConstant.KEY_CONTENT);
		XMLCREATE = XmlService.abopDeleteContentFromSTB(idPlayer, idContent);
		ModelService.abopDeleteContentFromSTB(XMLCREATE);
		return SUCCESS;
	}

	public String ajaxUpdatePlayer() throws JSONException, RemoteException {
		JSONObject json = new JSONObject(Constant.getObjectBean().getData());
		String name = json.getString(ApplyItemConstant.KEY_NAME);
		String ip = json.getString(ApplyItemConstant.KEY_IP);
		String idPlayer = json.getString(ApplyItemConstant.KEY_ID);
		XMLCREATE = XmlService.xmladminUPdateStb(idPlayer, name, ip);
		ModelService.adminUPdateStb(XMLCREATE);
		return SUCCESS;
	}

	private String getSetupBox(String groupId) throws RemoteException {
		// get session current
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		// get list stb in group
		XMLCREATE = XmlService.getStbList(groupId, user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.getStbList(XMLCREATE);
		Constant.getObjectBean()
				.setPlayer(ParseXmlService.getStbMap(XMLRESULT));
		// get list stb ungroup
		XMLCREATE = XmlService.getStbUnGroup(user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.getStbList(XMLCREATE);
		Constant.getObjectBean().setPlayerOutGroup(
				ParseXmlService.getStbMap(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxSaveNameGroup() throws RemoteException {
		String name = Constant.getObjectBean().getData();
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		XMLCREATE = XmlService.createGroupXml("-1", name, user.getCreator(), user.getParentCreator());
		String groupId = ModelService.addNewGroup(XMLCREATE);
		Constant.getObjectBean().setData(groupId);
		return SUCCESS;
	}

	public String ajaxChangeNameGroup() throws JSONException, RemoteException {
		JSONObject obj = new JSONObject(Constant.getObjectBean().getData());
		String groupId = obj.getString(ApplyItemConstant.KEY_ID);
		String groupName = obj.getString(ApplyItemConstant.KEY_NAME);
		XMLCREATE = XmlService.renameGroup(groupId, groupName);
		ModelService.renameGroup(XMLCREATE);
		return SUCCESS;
	}

	public String ajaxDetailGroup() throws Exception {
		String groupId = Constant.getObjectBean().getData();
		// get periodic by groupid
		XMLCREATE = XmlService.admingetSchedulePeriodic(groupId);
		XMLRESULT = ModelService.admingetSchedulePeriodic(XMLCREATE);
		// send to client
		Constant.getObjectBean().setSchedulePeriodic(
				ParseXmlService.admingetSchedulePeriodic(XMLRESULT));
		// get daily in periodic
		if (Constant.getObjectBean().getSchedulePeriodic().size() > 0) {
			String periodicId = Constant.getObjectBean().getSchedulePeriodic()
					.get(0).getId();
			XMLCREATE = XmlService
					.xmladmingetItemScheduleDailyForPerio(periodicId);
			XMLRESULT = ModelService
					.admingetItemScheduleDailyForPerio(XMLCREATE);
			ArrayList<HashMap<String, String>> daily = ParseXmlService
					.viewadmingetItemScheduleDailyForPerio(XMLRESULT);
			if (daily.size() > 0) {
				// send daily name to client
				Constant.getObjectBean().setData(
						daily.get(0).get(ApplyItemConstant.KEY_NAME));
				String dailyId = daily.get(0).get(ApplyItemConstant.KEY_ID);
				// get list playlist in daily
				XMLCREATE = XmlService.xmlDailyScheduleItem(dailyId);
				XMLRESULT = ModelService.admingetScheduleDailyItem(XMLCREATE);
				// send to client
				Constant.getObjectBean().setPlaylist(
						ParseXmlService
								.admingetScheduleDailyGroupMap(XMLRESULT));
				// get layout in playlist[0]
				if (Constant.getObjectBean().getPlaylist().size() > 0) {
					String playlistId = Constant.getObjectBean().getPlaylist()
							.get(0).getId();
					// get playlist item by playlist id
					XMLCREATE = XmlService
							.xmladmingetPlayListLayout(playlistId);
					XMLRESULT = ModelService.admingetPlayListLayout(XMLCREATE);
					Constant.getObjectBean().setLayout(
							ParseXmlService.getPlayListLayouItem(XMLRESULT));
					// get item layout by layoutId
					String layoutId = Constant.getObjectBean().getLayout()
							.get(0).getIdParent();
					XMLCREATE = XmlService.getItemFromGroupAndLayout("",
							layoutId, "");
					XMLRESULT = ModelService.getLayout(XMLCREATE);
					logger.info(XMLRESULT);
					// send to client
					Constant.getObjectBean().setLayoutItem(
							ParseXmlService.getLayoutItemMap(XMLRESULT));
					// get list layout using detect direction TV
					Map<String, Object> session = ActionContext.getContext().getSession();
					User user = (User)session.get(ApplyItemConstant.KEY_USER);
					XMLCREATE = XmlService.getLayoutFromGroup("-1", user.getCreator(), user.getParentCreator());
					XMLRESULT = ModelService.getLayoutName(XMLCREATE);
					logger.info(XMLRESULT);
					// send to client
					Constant.getObjectBean().setLayout(
							ParseXmlService.getLayoutName(XMLRESULT));
				}
			}

		}

		return SUCCESS;
	}

	public String ajaxPlaylistDialog() throws Exception {
		String playlistId = Constant.getObjectBean().getData();
		// get playlist item by playlist id
		XMLCREATE = XmlService.xmladmingetPlayListLayout(playlistId);
		XMLRESULT = ModelService.admingetPlayListLayout(XMLCREATE);
		Constant.getObjectBean().setLayout(
				ParseXmlService.getPlayListLayouItem(XMLRESULT));
		// get item layout by layoutId
		String layoutId = Constant.getObjectBean().getLayout().get(0)
				.getIdParent();
		XMLCREATE = XmlService.getItemFromGroupAndLayout("", layoutId, "");
		XMLRESULT = ModelService.getLayout(XMLCREATE);
		logger.info(XMLRESULT);
		// send to client
		Constant.getObjectBean().setLayoutItem(
				ParseXmlService.getLayoutItemMap(XMLRESULT));
		// get list layout using detect direction TV
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		XMLCREATE = XmlService.getLayoutFromGroup("-1", user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.getLayoutName(XMLCREATE);
		logger.info(XMLRESULT);
		// send to client
		Constant.getObjectBean().setLayout(
				ParseXmlService.getLayoutName(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxDeleteGroup() throws RemoteException {
		String groupId = Constant.getObjectBean().getData();
		XMLCREATE = XmlService.deleteGroup(groupId, "");
		ModelService.adminDeleteGroup(XMLCREATE);
		return SUCCESS;
	}

	public String ajaxDeleteSTB() throws Exception {
		String stbId = Constant.getObjectBean().getData();
		XMLCREATE = XmlService.xmladminDelStb(stbId);
		ModelService.adminDelStb(XMLCREATE);
		return SUCCESS;
	}

	public String ajaxMonitor() {
		ArrayList<ObjectABOP> capture = new ArrayList<ObjectABOP>();
		// get serinumber of stb
		String serinumber = Constant.getObjectBean().getData();
		// format name file: serinumber_x_y_w_h_0.png ||
		// serinumber_x_y_w_h_1.png && serinumber2_x_y_w_h_1.png
		String nameFile = UtilBasic
				.getImageCaptureScreenNewest(serinumber, "1");
		// serinumber_x_y_w_h_0.png
		if (nameFile.indexOf(nameFile + "_") < 0) {
			String xywh = nameFile.substring(
					nameFile.indexOf("_", nameFile.indexOf("_") + 1),
					nameFile.lastIndexOf("."));
			logger.info("x_y_w_h: " + xywh);
			String[] str = xywh.split("_");
			String flag = str[5];
			// TV show image(not vieo)
			if ("0".equals(flag)) {
				ObjectABOP abop = new ObjectABOP();
				abop.setUrl(ConstantProperties.DMSV_FILE_CAPTURE + nameFile);
				abop.setLeft(str[1]);
				abop.setTop(str[2]);
				abop.setWidth(str[3]);
				abop.setHeight(str[4]);
				abop.setRandom(UtilBasic.getSequence());
				capture.add(abop);
				Constant.getObjectBean().setLayout(capture);
				return SUCCESS;
			}
			// TV show container video
			else {
				String nameFiles = UtilBasic.getImageCaptureScreenNewest(
						serinumber, "2");
				String[] strName = nameFiles.split(";");
				UtilBasic.getInfoImage(strName);
			}
		}
		// TV show only video
		else if (nameFile.indexOf(nameFile + "2_") == 0) {
			String nameFiles = UtilBasic.getImageCaptureScreenNewest(
					serinumber, "2");
			String[] strName = nameFiles.split(";");
			UtilBasic.getInfoImage(strName);
		}
		return SUCCESS;
	}
	public String ajaxMonitoring() throws JSONException, RemoteException{
		JSONObject json = new JSONObject(Constant.getObjectBean().getData());
		String flag = json.getString(ApplyItemConstant.KEY_FLAG);
		String idSTB = json.getString(ApplyItemConstant.KEY_ID);
		XMLCREATE = XmlService.abopSetMonitoring(flag, idSTB);
		ModelService.abopSetMonitoring(XMLCREATE);
		return SUCCESS;
	}
	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

}
