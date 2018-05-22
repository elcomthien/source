package elcom.abop.action;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

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

public class UserManagerAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(Authentication.class);

	public String execute() {
		return SUCCESS;
	}

	public String getListUser() throws Exception {
		logger.info("execute method getListUser");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		String xml = XmlService.getListUser(user.getCreator(), user.getParentCreator());
		String result = ModelService.getListUser(xml);
		logger.info(result);
		Constant.getObjectBean().setUsers(
				ParseXmlService.abopGetListUser(result));

		return SUCCESS;
	}

	public String ajaxGetRole() throws ParserConfigurationException,
			SAXException, IOException {
		// get list role
		String result = ModelService.abopGetRole();
		logger.info("***********" + result);
		Constant.getObjectBean().setRole(ParseXmlService.abopGetRole(result));
		return SUCCESS;
	}

	public String ajaxCreateUser() throws RemoteException {
		String xml = Constant.getObjectBean().getData();
		String result = ModelService.abopCreateUser(xml);
		Constant.getObjectBean().setData(result);
		return SUCCESS;
	}

	public String ajaxCheckUsername() throws RemoteException {
		String username = Constant.getObjectBean().getData();
		// check username
		String result = ModelService.abopCheckUser(username);

		Constant.getObjectBean().setData(result);
		return SUCCESS;
	}

	public String ajaxUpdateUser() throws RemoteException {
		String xml = Constant.getObjectBean().getData();
		String result = ModelService.abopUpdateUser(xml);
		Constant.getObjectBean().setData(result);

		return SUCCESS;
	}

	public String ajaxUserUpdate() throws RemoteException {
		String xml = Constant.getObjectBean().getData();
		String result = ModelService.abopUpdateUser(xml);
		Constant.getObjectBean().setData(result);
		return SUCCESS;
	}

	public String ajaxDeleteUser() throws RemoteException {
		String username = Constant.getObjectBean().getData();
		String result = ModelService.abopDeleteUser(username);
		Constant.getObjectBean().setData(result);

		return SUCCESS;
	}
	public String ajaxGetStb(){
		// get session current
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		// get list stb ungroup
		String XMLCREATE = XmlService.getStbUnGroup(user.getCreator(), user.getParentCreator());
		String XMLRESULT;
		try {
			XMLRESULT = ModelService.getStbList(XMLCREATE);
			Constant.getObjectBean().setPlayerOutGroup(
					ParseXmlService.getStbMap(XMLRESULT));
		} catch (RemoteException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return SUCCESS;
	}
	public String ajaxAddStbUser(){
		String xml = Constant.getObjectBean().getData();
		try {
			String result = ModelService.abopAddStbUser(xml);
			Constant.getObjectBean().setData(result);
		} catch (RemoteException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return SUCCESS;
	}
	@Override
	public ObjectBean getModel() {
		// /Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

}
