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

public class SubjectAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(SubjectAction.class);

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

	public String addSubject() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		logger.info("Add new subject content!");
		String object = Constant.getObjectBean().getData();
		String name = "";
		String des = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			name = jsonObj.getString("name");
			des = jsonObj.getString("des");

			if (!name.equalsIgnoreCase("")) {
				String xml = XmlService.xmlAdminAddSubjectContent(name, des, user.getCreator(), user.getParentCreator());
				System.out.println(xml);
				ModelService.adminAddSubjectContent(xml);
				Constant.getObjectBean().setData("true");
			}
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("Add new subject content error ", e);
		}
		return SUCCESS;
	}

	public String allSubject() throws Exception {
		logger.info("Get all subject content!");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String xml = XmlService.adminGetAllSubjectContent(user.getCreator(), user.getParentCreator());
		String xmlResult = ModelService.adminGetSubjectByUser(xml);
		Constant.getObjectBean().setContent(ParseXmlService.adminGetSubjectByUser(xmlResult));
		return SUCCESS;
	}

	public String updateSubject() {
		logger.info("Update subject content!");
		String object = Constant.getObjectBean().getData();
		String id = "";
		String name = "";
		String des = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			name = jsonObj.getString("name");
			des = jsonObj.getString("des");

			if (!name.equalsIgnoreCase("")) {
				String xml = XmlService.xmlAdminUpdateSubjectContent(id, name, des);
				ModelService.adminUpdateSubjectContent(xml);
				Constant.getObjectBean().setData("true");
			}
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("Update subject content error ", e);
		}
		return SUCCESS;
	}

	public String deleteSubject() {
		logger.info("Delete subject content!");
		String object = Constant.getObjectBean().getData();
		String id = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			String xml = XmlService.xmlAdminDeleteSubjectContent(id);
			ModelService.adminDeleteSubjectContent(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("Delete subject content error ", e);
		}
		return SUCCESS;
	}
}
