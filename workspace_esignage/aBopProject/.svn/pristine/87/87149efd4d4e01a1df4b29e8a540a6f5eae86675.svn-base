package elcom.abop.authentication;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import elcom.abop.bean.User;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.Constant;

public class AuthenticationInterceptor implements Interceptor {
	private static final long serialVersionUID = -7211130307971104979L;
	private static final Logger logger = Logger.getLogger(AuthenticationInterceptor.class);
	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		logger.info("inside auth interceptor");
		HttpServletRequest request = ServletActionContext.getRequest();
		String uri = request.getRequestURI();
		String url = "login.elcom";
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession(); 
        if(session.get(ApplyItemConstant.KEY_USER) == null || (uri.endsWith(url))){
            return Action.LOGIN;
        }
        return actionInvocation.invoke();
	}

}
