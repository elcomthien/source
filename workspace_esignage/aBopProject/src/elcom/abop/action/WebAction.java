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

public class WebAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(WebAction.class);

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

	public String getAllWeb() throws Exception {
		logger.info("get all content web");
		String object = Constant.getObjectBean().getData();
		String idsubject = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			idsubject = jsonObj.getString("idsubject");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String xml = XmlService.getAllContent("6", idsubject, user.getCreator(), user.getParentCreator());
		String xmlResult = ModelService.adminGetAllContent(xml);
		Constant.getObjectBean().setContent(ParseXmlService.getAllContentText(xmlResult));
		return SUCCESS;
	}

	public String addNewWeb() {
		logger.info("add new content web");
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

			if (!name.equalsIgnoreCase("") && !url.equalsIgnoreCase("")) {
				String xml = XmlService.xmladminNewContentWebSubject(url, name, idsubject, user.getCreator(), user.getParentCreator());
				ModelService.adminNewContentText(xml);
				Constant.getObjectBean().setData("true");
			}
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("Add new web content error ", e);
		}
		return SUCCESS;
	}

	public String updateWeb() {
		logger.info("update content web");
		String object = Constant.getObjectBean().getData();
		String id = "";
		String name = "";
		String url = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			name = jsonObj.getString("name");
			url = jsonObj.getString("url");
			String xml = XmlService.xmladminUpdateContentWeb(url, id, name);
			ModelService.adminUpdateContentText(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("Add new web content error ", e);
		}
		return SUCCESS;
	}

	public String deleteWeb() {
		logger.info("delete content web");
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
			logger.error("delete web content error ", e);
		}
		return SUCCESS;
	}

	public static void main(String[] args) {
		WebAction t = new WebAction();
		try {
			System.out.println(t.getAllWeb());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
