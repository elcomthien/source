package elcom.abop.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import elcom.abop.bean.User;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.BasicAction;
import elcom.abop.common.Constant;
import elcom.abop.common.ConstantProperties;
import elcom.abop.util.ModelService;
import elcom.abop.util.ParseXmlService;
import elcom.abop.util.UtilBasic;
import elcom.abop.util.XmlService;

public class Authentication implements BasicAction, SessionAware,
		ServletRequestAware, ModelDriven<User> {
	private static final Logger logger = Logger.getLogger(Authentication.class);
	private HttpServletRequest request;
	private Map<String, Object> session = null;

	public String login() throws Exception {
		
		String username = Constant.getUserConstant().getUsername();
		String password = Constant.getUserConstant().getPassword();
		String parentPath = UtilBasic.getParentPath(request.getSession().getServletContext().getRealPath("/"));
		ApplyItemConstant.KEY_PATH_PROPERTIES_MSG = parentPath + ApplyItemConstant.KEY_PATH_PROPERTIES_MSG_ORG;
		ApplyItemConstant.KEY_PATH_PROPERTIES_CONFIG = parentPath + ApplyItemConstant.KEY_PATH_PROPERTIES_CONFIG_ORG;
		ApplyItemConstant.KEY_PATH_XML_FILE = parentPath + ApplyItemConstant.KEY_PATH_XML_FILE_ORIGINAL;
		// write log to file log.txt
		System.setProperty("log.dir", request.getSession().getServletContext().getRealPath(""));
		DOMConfigurator.configure(request.getSession().getServletContext().getRealPath("/WEB-INF/classes/log4j.xml"));
		logger.info("Path config file:" + ApplyItemConstant.KEY_PATH_PROPERTIES_CONFIG);
		String xml = XmlService.getUser(username, password);
		String result = ModelService.getUser(xml);
		
		if (!FAIL.equals(result)) {
			logger.info("Login success");
			session = ActionContext.getContext().getSession();
			session.put(ApplyItemConstant.KEY_USER, ParseXmlService.getUser(result));
			ApplyItemConstant.KEY_USER_NAME = username;
			// detect page home
			User user = (User)session.get(ApplyItemConstant.KEY_USER);
			if(SUPER_ADMIN.equals(user.getRole())){
				return "player";
			} else if(ADMIN.equals(user.getRole())){
				return "player";
			} else if(USER_VIEW.equals(user.getRole())){
				return "player";
			} else if(USER_EDIT.equals(user.getRole())){
				return "player";
			}
			return SUCCESS;
		}
		logger.info("Login fail");
		Constant.getUserConstant().setMsgError(ConstantProperties.E_LOGIN);
		
		return LOGIN;
	}
	
	public String logout() throws Exception {	
		session = ActionContext.getContext().getSession();
		session.remove(ApplyItemConstant.KEY_USER);
		session.clear();
		return LOGIN;
	}

	@Override
	public User getModel() {
		return Constant.getUserConstant();
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
