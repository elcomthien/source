package elcom.abop.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import elcom.abop.bean.ObjectABOP;
import elcom.abop.bean.ObjectBean;
import elcom.abop.bean.User;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.BasicAction;
import elcom.abop.common.Constant;
import elcom.abop.util.ModelService;
import elcom.abop.util.ParseXmlService;
import elcom.abop.util.UtilBasic;
import elcom.abop.util.XmlService;

public class LayoutAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(LayoutAction.class);
	private static final long serialVersionUID = -8396751284811176976L;
	private String XMLCREATE = "";
	private String XMLRESULT = "";

	public String layout() throws RemoteException {
		logger.info("Inside Layout Action class");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		XMLCREATE = XmlService.getLayoutFromGroup("-1",user.getCreator(), user.getParentCreator());
		XMLRESULT = ModelService.getLayoutName(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setLayout(
				ParseXmlService.getLayoutName(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxlayout() throws RemoteException {
		logger.info("ajaxlayout");
		// get layout item
		String id = Constant.getObjectBean().getData();
		logger.info("Data: " + id);
		XMLCREATE = XmlService.getItemFromGroupAndLayout("", id, "");
		XMLRESULT = ModelService.getLayout(XMLCREATE);
		logger.info(XMLRESULT);
		Constant.getObjectBean().setLayoutItem(
				ParseXmlService.getLayoutItemMap(XMLRESULT));
		return SUCCESS;
	}

	public String ajaxSaveLayout() throws JSONException, RemoteException {
		JSONObject jsonObj = new JSONObject(Constant.getObjectBean().getData());
		JSONArray jsonArray = jsonObj.getJSONArray(ApplyItemConstant.KEY_ITEMS);
		String name = jsonObj.getString(ApplyItemConstant.KEY_NAME);
		String direction = jsonObj.getString(ApplyItemConstant.KEY_DIRECTION);
		String size = jsonObj.getString(ApplyItemConstant.KEY_SIZE);
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get(ApplyItemConstant.KEY_USER);
		// Save layout name
		XMLCREATE = XmlService.createLayoutName("-1", name, direction, size, user.getCreator(), user.getParentCreator());
		String layoutId = ModelService.adminInsertLayoutName(XMLCREATE);
		// save item layout
		String layoutInfo = XmlService.createLayoutXml(name, layoutId);
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			ObjectABOP ab = new ObjectABOP();
			ab.setName(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_NAME));
			ab.setType(UtilBasic.convertTypeFile(jsonArray.getJSONObject(i)
					.getString(ApplyItemConstant.KEY_TYPE)));
			ab.setLeft(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_LEFT));
			ab.setTop(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_TOP));
			ab.setWidth(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_WIDTH));
			ab.setHeight(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_HEIGHT));
			XMLCREATE = XmlService.createItemLayout(layoutInfo, ab.getName(),
					"-1", ab.getType(), "abop-default", ab.getLeft(),
					ab.getTop(), ab.getWidth(), ab.getHeight(), "0");
			ModelService.addLayout(XMLCREATE);
		}
		Constant.getObjectBean().setData(layoutId);
		return SUCCESS;
	}

	public String ajaxUpdateLayout() throws JSONException, RemoteException {
		JSONObject jsonObj = new JSONObject(Constant.getObjectBean().getData());
		JSONArray jsonArray = jsonObj.getJSONArray(ApplyItemConstant.KEY_ITEMS);
		JSONArray deletes = jsonObj.getJSONArray(ApplyItemConstant.KEY_DELETE);
		String name = jsonObj.getString(ApplyItemConstant.KEY_NAME);
		String size = jsonObj.getString(ApplyItemConstant.KEY_SIZE);
		String id = jsonObj.getString(ApplyItemConstant.KEY_ID);
		// update layout name
		XMLCREATE = XmlService.renameLayout(id, name, size);
		ModelService.renameLayout(XMLCREATE);
		// update layout item and insert  layout item new
		String layoutInfo = XmlService.createLayoutXml(name, id);
		int length = jsonArray.length();
		ArrayList<ObjectABOP> objUpdate = new ArrayList<ObjectABOP>();
		for (int i = 0; i < length; i++) {
			ObjectABOP ab = new ObjectABOP();
			ab.setId(jsonArray.getJSONObject(i).getString(ApplyItemConstant.KEY_ID));
			ab.setName(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_NAME));
			ab.setType(UtilBasic.convertTypeFile(jsonArray.getJSONObject(i)
					.getString(ApplyItemConstant.KEY_TYPE)));
			ab.setLeft(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_LEFT));
			ab.setTop(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_TOP));
			ab.setWidth(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_WIDTH));
			ab.setHeight(jsonArray.getJSONObject(i).getString(
					ApplyItemConstant.KEY_HEIGHT));
			// Insert new
			if("-1".equals(ab.getId())){ 
				XMLCREATE = XmlService.createItemLayout(layoutInfo, ab.getName(),
						"-1", ab.getType(), "abop-default", ab.getLeft(),
						ab.getTop(), ab.getWidth(), ab.getHeight(), "0");
				ModelService.addLayout(XMLCREATE);
			} else { // Update
				objUpdate.add(ab);
			}
		}
		// Update layout item
		if(objUpdate.size() > 0){
			XMLCREATE = XmlService.xmladminUpdateLayoutCoor(objUpdate);
			ModelService.adminUpdateLayoutCoor(XMLCREATE);
		}
		// Delete item
		length = deletes.length();
		if(length > 0){
			for(int i = 0; i < length; i++){
				String idItem = deletes.getJSONObject(i).getString(ApplyItemConstant.KEY_ID);
				XMLCREATE = XmlService.xmladminDelLayOutItem(idItem);
				ModelService.adminDelLayOutItem(XMLCREATE);
			}
			
		}
		Constant.getObjectBean().setData(id);
		return SUCCESS;
	}

	public String ajaxDeleteLayout() throws RemoteException {
		String id = Constant.getObjectBean().getData();
		// delete layout name
		XMLCREATE = XmlService.deleteLayoutXml(id, "");
		ModelService.deleteLayout(XMLCREATE);
		return SUCCESS;
	}

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}
}
