package elcom.abop.action;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;

import elcom.abop.bean.ObjectBean;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.BasicAction;
import elcom.abop.common.Constant;
import elcom.abop.util.ReadWriteFileUtil;
import elcom.abop.util.UtilBasic;

public class QMSAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(QMSAction.class);

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}
	
	public String getConfigQMS(){
		logger.info("Get QMS config");
		Constant.getObjectBean().setContentQMS(ReadWriteFileUtil.readFileXML(getPath()));
		return SUCCESS;
	}

	public String configQMS() {
		logger.info("Config QMS");
		String object = Constant.getObjectBean().getData();
		String bgheader = "";
		String bgbodyeven = "";
		String bgbodyodd = "";
		String clheader = "";
		String clbody = "";
		String fzheader = "";
		String fzbody = "";
		String font = "";
		String position = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			bgheader = jsonObj.getString("bgheader");
			bgbodyeven = jsonObj.getString("bgbodyeven");
			bgbodyodd = jsonObj.getString("bgbodyodd");
			clheader = jsonObj.getString("clheader");
			clbody = jsonObj.getString("clbody");
			fzheader = jsonObj.getString("fzheader");
			fzbody = jsonObj.getString("fzbody");
			font = jsonObj.getString("font");
			position = jsonObj.getString("position");
			String xml = createDataConfigQMS(bgheader, bgbodyeven, bgbodyodd,
					clheader, clbody, fzheader, fzbody, font, position);
			boolean flag = ReadWriteFileUtil.WriteFile(getPath(),
					xml, true);
			if (flag)
				Constant.getObjectBean().setData("true");
			else
				Constant.getObjectBean().setData("false");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
			logger.error("Add new text content error ", e);
		}
		return SUCCESS;
	}

	public String createDataConfigQMS(String bgheader, String bgbodyeven,
			String bgbodyodd, String clheader, String clbody, String fzheader,
			String fzbody, String font, String position) {
		String xml = "<?xml version='1.0'?>";
		xml += "\n<template pos = '" + position + "'>";
		xml += "\n\t<item id = 'header' col = 'quay' pos = '" + position + "'>";
		xml += "\n\t\t<background>" + bgheader + "</background>";
		xml += "\n\t\t<color>" + clheader + "</color>";
		xml += "\n\t\t<font>" + font + "</font>";
		xml += "\n\t\t<fontsize>" + fzheader + "</fontsize>";
		xml += "\n\t</item>";

		xml += "\n\t<item id = 'header' col = 'stt' pos = '" + position + "'>";
		xml += "\n\t\t<background>" + bgheader + "</background>";
		xml += "\n\t\t<color>" + clheader + "</color>";
		xml += "\n\t\t<font>" + font + "</font>";
		xml += "\n\t\t<fontsize>" + fzheader + "</fontsize>";
		xml += "\n\t</item>";

		xml += "\n\t<item id = 'body' col = 'chan'>";
		xml += "\n\t\t<background>" + bgbodyeven + "</background>";
		xml += "\n\t\t<color>" + clbody + "</color>";
		xml += "\n\t\t<font>" + font + "</font>";
		xml += "\n\t\t<fontsize>" + fzbody + "</fontsize>";
		xml += "\n\t</item>";

		xml += "\n\t<item id = 'body' col = 'le'>";
		xml += "\n\t\t<background>" + bgbodyodd + "</background>";
		xml += "\n\t\t<color>" + clbody + "</color>";
		xml += "\n\t\t<font>" + font + "</font>";
		xml += "\n\t\t<fontsize>" + fzbody + "</fontsize>";
		xml += "\n\t</item>";

		xml += "\n</template>";
		return xml;
	}
	
	public String getPath(){
		String path = "";
		path = UtilBasic.getResource(ApplyItemConstant.KEY_PATH_PROPERTIES_CONFIG, ApplyItemConstant.KEY_PATH_QMS);
		return path;
	}

}
