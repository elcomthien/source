package elcom.abop.action;

import java.rmi.RemoteException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import elcom.abop.bean.ObjectBean;
import elcom.abop.bean.User;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.BasicAction;
import elcom.abop.common.Constant;
import elcom.abop.util.ModelService;
import elcom.abop.util.ParseXmlService;
import elcom.abop.util.XmlService;

public class ContentAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(ContentAction.class);
	private String XMLCREATE = "";
	private String XMLRESULT = "";

	public String content() throws RemoteException {
		return SUCCESS;
	}

	public String ajaxContent() throws RemoteException {
		String groupId = Constant.getObjectBean().getData();
		// get all group
		XMLCREATE = XmlService.getAllGroup();
		XMLRESULT = ModelService.admingetGroupList(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setGroup(
				ParseXmlService.getGroupItemName(XMLRESULT));
		// get content un group
//		XMLCREATE = XmlService.admingetContentOutGroup(groupId);
		XMLRESULT = ModelService.getContentListNoGroup(XMLCREATE);
		Constant.getObjectBean().setContentOutGroup(
				ParseXmlService.getGroupItemMap(XMLRESULT));
		// get content in-group
//		XMLCREATE = XmlService.admingetContentInGroup(groupId);
		XMLRESULT = ModelService.getContentList(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setContent(
				ParseXmlService.getGroupItemMap(XMLRESULT));
		return SUCCESS;
	}
	public String ajaxAddContent() throws JSONException, RemoteException{
		// get session current
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		// add content to group
		JSONObject json =  new JSONObject(Constant.getObjectBean().getData());
		String groupId = json.getString(ApplyItemConstant.KEY_GROUP_ID);
		String contentId = json.getString(ApplyItemConstant.KEY_CONTENT);
		XMLCREATE = XmlService.createXmlForContentGroup(groupId, contentId);
		ModelService.addContentIntoGroup(XMLCREATE);
		// add content to settopbox
		// get list stb in group
		XMLCREATE = XmlService.getStbList(groupId, user.getCreator(), user.getParentCreator());
		
		XMLRESULT = ModelService.getStbList(XMLCREATE);
		ModelService.pushXml(XMLCREATE);
		return SUCCESS;
	}
	public String ajaxRemoveContent() throws JSONException, RemoteException{
		// get session current
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		JSONObject json =  new JSONObject(Constant.getObjectBean().getData());
		String groupId = json.getString(ApplyItemConstant.KEY_GROUP_ID);
		String contentId = json.getString(ApplyItemConstant.KEY_CONTENT);
		// remove content to group
		XMLCREATE = XmlService.createXmlForContentGroup(groupId, contentId);
		ModelService.removeContentIntoGroup(XMLCREATE);
		// get list stb in group
		XMLCREATE = XmlService.getStbList(groupId, user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.getStbList(XMLCREATE);
		// remove content in stb
		ModelService.removeXml1(groupId, contentId);
		return SUCCESS;
	}
	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

}
