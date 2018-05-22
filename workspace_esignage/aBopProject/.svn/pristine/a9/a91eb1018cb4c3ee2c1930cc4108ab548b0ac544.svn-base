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

public class SlideAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(SlideAction.class);

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

	public String getImages() throws Exception {
		logger.info("get images for slide");
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
		String xml = XmlService.getAllContent("3", idsubject, user.getCreator(), user.getParentCreator());
		String xmlResult = ModelService.adminGetAllContent(xml);
		Constant.getObjectBean().setContent(ParseXmlService.getAllContentMedia(xmlResult));
		return SUCCESS;
	}

	public String newSlide() {
		logger.info("add new slide content");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String object = Constant.getObjectBean().getData();
		String idsubject = "";
		String name = "";
		String effect = "";
		String lstord = "";
		String lstcnt = "";
		String lsttime = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			idsubject = jsonObj.getString("idsubject");
			name = jsonObj.getString("name");
			effect = jsonObj.getString("effect");
			lstord = jsonObj.getString("listorder");
			lstcnt = jsonObj.getString("listcontent");
			lsttime = jsonObj.getString("listtime");
			String xml = XmlService.adminAddNewSlideContent(idsubject, name, effect, lstord, lstcnt, lsttime, user.getCreator(), user.getParentCreator());
			ModelService.adminAddNewSlideContent(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String allSlide() {
		logger.info("get all slide content");
		String object = Constant.getObjectBean().getData();
		String idsubject = "";
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		try {
			JSONObject jsonObj = new JSONObject(object);
			idsubject = jsonObj.getString("idsubject");
			String xml = XmlService.getAllContent("8", idsubject, user.getCreator(), user.getParentCreator());
			String xmlResult = ModelService.adminGetAllContent(xml);
			Constant.getObjectBean().setContent(ParseXmlService.getAllContentText(xmlResult));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String deleteSlide(){
		logger.info("delete slide content");
		String object = Constant.getObjectBean().getData();
		String id = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			String xml = XmlService.xmladminDelContentText(id);
			ModelService.adminDeleteSlideContent(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getContentForSlide(){
		logger.info("get content images for slide");
		String object = Constant.getObjectBean().getData();
		String id = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			String xml = XmlService.xmladminDelContentText(id);
			String xmlResult = ModelService.adminGetContentIDByForSlide(xml);
			Constant.getObjectBean().setContent(ParseXmlService.adminGetContentIDByForSlide(xmlResult));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateSlide() {
		logger.info("update slide content");
		String object = Constant.getObjectBean().getData();
		String idcontent = "";
		String name = "";
		String effect = "";
		String lstord = "";
		String lstcnt = "";
		String lsttime = "";
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			User user = (User)session.get(ApplyItemConstant.KEY_USER);
			JSONObject jsonObj = new JSONObject(object);
			idcontent = jsonObj.getString("id");
			name = jsonObj.getString("name");
			effect = jsonObj.getString("effect");
			lstord = jsonObj.getString("listorder");
			lstcnt = jsonObj.getString("listcontent");
			lsttime = jsonObj.getString("listtime");
			String xml = XmlService.adminAddNewSlideContent(idcontent, name, effect, 
					lstord, lstcnt, lsttime, user.getCreator(), user.getParentCreator());
			ModelService.adminUpdateSlideContent(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public static void main(String[] args) throws Exception {
		String xml = XmlService.xmladminDelContentText("688");
		String xmlResult = ModelService.adminGetContentIDByForSlide(xml);
		Constant.getObjectBean().setContent(ParseXmlService.adminGetContentIDByForSlide(xmlResult));
	}
}
