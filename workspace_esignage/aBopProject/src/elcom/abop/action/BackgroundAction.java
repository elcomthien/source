package elcom.abop.action;

import java.util.Map;

import org.apache.log4j.Logger;
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

public class BackgroundAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(BackgroundAction.class);

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

	public String getAllBackground() throws Exception {
		logger.info("get all content background");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String object = Constant.getObjectBean().getData();
		String idsubject = "";
		JSONObject jsonObj = new JSONObject(object);
		idsubject = jsonObj.getString("idsubject");
		String xml = XmlService.getAllContent("7",idsubject, user.getCreator(), user.getParentCreator());
		String xmlResult = ModelService.adminGetAllContent(xml);
		Constant.getObjectBean().setContent(ParseXmlService.getAllContentText(xmlResult));
		return SUCCESS;
	}

	public String deleteBackground() {
		logger.info("delete content background");
		String object = Constant.getObjectBean().getData();
		String id = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			String xml = XmlService.xmladminDelContentText(id);
			ModelService.adminDelContentText(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("delete content background error " + e.getMessage());
		}
		return SUCCESS;
	}

	public String getListImage() throws Exception {
		logger.info("get all content images");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String object = Constant.getObjectBean().getData();
		String idsubject = "";
		JSONObject jsonObj = new JSONObject(object);
		idsubject = jsonObj.getString("idsubject");
		String xml = XmlService.getAllContent("3", idsubject, user.getCreator(), user.getParentCreator());
		String xmlResult = ModelService.adminGetAllContent(xml);
		Constant.getObjectBean().setContent(ParseXmlService.getAllContentText(xmlResult));
		return SUCCESS;
	}

	public String addNewBackground() {
		logger.info("add new background content");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String object = Constant.getObjectBean().getData();
		String idsubject = "";
		String name = "";
		String url = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			idsubject = jsonObj.getString("idsubject");
			name = jsonObj.getString("name");
			url = jsonObj.getString("url");
			String xml = XmlService.xmladminNewContentBg(idsubject, url, name, user.getCreator(), user.getParentCreator());
			ModelService.adminNewContentText(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("add new content background error ", e);
		}
		return SUCCESS;
	}

	public String editBackground() {
		logger.info("edit background content");
		String object = Constant.getObjectBean().getData();
		String id = "";
		String name = "";
		String url = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			name = jsonObj.getString("name");
			url = jsonObj.getString("url");
			String xml = XmlService.xmladminUpdateContentBg(url, id, name);
			ModelService.adminUpdateContentText(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("edit content background error ", e);
		}
		return SUCCESS;
	}
}
