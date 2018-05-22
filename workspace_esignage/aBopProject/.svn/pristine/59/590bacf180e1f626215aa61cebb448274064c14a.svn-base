package elcom.abop.action;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;

import elcom.abop.bean.ObjectBean;
import elcom.abop.common.BasicAction;
import elcom.abop.common.Constant;
import elcom.abop.util.ModelService;
import elcom.abop.util.ParseXmlService;
import elcom.abop.util.XmlService;

public class ConfigFTPAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(ConfigFTPAction.class);

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

	public String getConfig() throws Exception {
		logger.info("Get config ftp server!");
		String xmlftpconfig = ModelService.admingetConfig();
		Constant.getObjectBean().setConfig(ParseXmlService.parseXMLConfig(xmlftpconfig));
		return SUCCESS;
	}

	public String updateConfig() {
		logger.info("Update config ftp server!");
		String object = Constant.getObjectBean().getData();
		String host = "";
		String port = "";
		String user = "";
		String pass = "";
		String ipserver = "";
		String srcfile = "";
		String localfile = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			host = jsonObj.getString("host");
			port = jsonObj.getString("port");
			user = jsonObj.getString("username");
			pass = jsonObj.getString("password");
			ipserver = jsonObj.getString("ipserver");
			srcfile = jsonObj.getString("srcfile");
			localfile = jsonObj.getString("srclocal");
			
			String xml = XmlService.xmladminUpdateConfig(host, port, srcfile, localfile, ipserver, user, pass);
			ModelService.adminUpdateConfig(xml);
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
