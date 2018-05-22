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

public class TextAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(TextAction.class);

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

	public String getAllText() throws Exception {
		logger.info("get all content text");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String object = Constant.getObjectBean().getData();
		String idsubject = "";
		JSONObject jsonObj = new JSONObject(object);
		idsubject = jsonObj.getString("idsubject");
		String xml = XmlService.getAllContent("5", idsubject, user.getCreator(), user.getParentCreator());
		String xmlResult = ModelService.adminGetAllContent(xml);
		Constant.getObjectBean().setContent(ParseXmlService.getAllContentText(xmlResult));
		return SUCCESS;
	}

	public String deleteText() throws Exception {
		logger.info("delete content text");
		String object = Constant.getObjectBean().getData();
		String id = "";
		JSONObject jsonObj = new JSONObject(object);
		id = jsonObj.getString("id");
		String xml = XmlService.xmladminDelContentText(id);
		ModelService.adminDelContentText(xml);
		return SUCCESS;
	}

	public String addNewText() {
		logger.info("add new content text");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String object = Constant.getObjectBean().getData();
		String name = "";
		String url = "";
		String font = "";
		String color = "";
		String size = "";
		String idsubject = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			name = jsonObj.getString("name");
			url = jsonObj.getString("url");
			font = jsonObj.getString("font");
			color = jsonObj.getString("color");
			size = jsonObj.getString("size");
			idsubject = jsonObj.getString("idsubject");
			String xml = XmlService.xmladminNewContentText(name, url, font, size, color, idsubject, user.getCreator(), user.getParentCreator());
			ModelService.adminNewContentText(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("Add new text content error ", e);
		}
		return SUCCESS;
	}

	public String getTextById() throws Exception {
		String object = Constant.getObjectBean().getData();
		String id = "";
		String idsubject = "";
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			idsubject = jsonObj.getString("idsubject");
			String xml = XmlService.getAllContent("5", idsubject, user.getCreator(), user.getParentCreator());
			String xmlResult = ModelService.adminGetAllContent(xml);
			Constant.getObjectBean().setContent(ParseXmlService.getContentText(xmlResult, id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateText(){
		logger.info("update content text");
		String object = Constant.getObjectBean().getData();
		String name = "";
		String url = "";
		String font = "";
		String color = "";
		String size = "";
		String idcontent = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			name = jsonObj.getString("name");
			url = jsonObj.getString("url");
			font = jsonObj.getString("font");
			color = jsonObj.getString("color");
			size = jsonObj.getString("size");
			idcontent = jsonObj.getString("id");
			String xml = XmlService.xmladminUpdateContentText(name, url, font, size, color, idcontent);
			ModelService.adminUpdateContentText(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("Add new text content error ", e);
		}
		return SUCCESS;
	}
}