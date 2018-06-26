package ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.abs.pms.PMSEHotel;
import ehotel.admin.dbi.IMBroker;
import ehotel.domain.pms.eMenu;
import ehotel.utils.DataUtils;

public class PMSServiceDBI extends PMSEHotel {
	static IMBroker broker = IMBroker.getInstance();
	public static final String ADD_ITEM_DINING = "BEGIN EPMS.ADDROOMSVCITEM(?,?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_ITEM_DINING = "BEGIN EPMS.editRoomSvcItem(?,?,?,?,?,?,?,?,?,?); END;";
	public static String sqlGetSubjectInOutNew = "{call ePMS.getSubjectInOutNew(?,?,?,?,?)}";
	public static final String UPDATE_MESSAGE_WELCOME = "BEGIN EPMS.updateWelcomeMessage(?); END;";
	public static final String GET_MESSAGE_WELCOME = "BEGIN EPMS.getWelcomeMessage(?); END;";
	public static final String GET_TIME_PROMOTION = "BEGIN EPMS.getTimePromotion(?); END;";
	public static final String UPDATE_TIME_PROMOTION = "BEGIN EPMS.updateTimePromotion(?,?,?); END;";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int addItemDining(int menuid, String name, String def, String price,
			String price_large, String price_small, String url, String unit) {
		Vector params = new Vector(11);
		SubProParam in = null;
		in = new SubProParam(new BigDecimal(menuid), SubProParam.IN);
		params.add(in); // 0
		in = new SubProParam(new java.lang.String(name), SubProParam.IN);
		params.add(in); // 1
		in = new SubProParam(new java.lang.String(def), SubProParam.IN);
		params.add(in); // 2
		in = new SubProParam(new java.lang.String(price), SubProParam.IN);
		params.add(in); // 3
		in = new SubProParam(new java.lang.String(price_large), SubProParam.IN);
		params.add(in); // 4
		in = new SubProParam(new java.lang.String(price_small), SubProParam.IN);
		params.add(in); // 5
		in = new SubProParam(new java.lang.String(url), SubProParam.IN);
		params.add(in); // 6
		in = new SubProParam(new java.lang.String(unit), SubProParam.IN);
		params.add(in); // 7
		SubProParam out = new SubProParam(new String(), SubProParam.OUT);
		params.add(out); // 8
		try {
			params = broker.executeSubPro(ADD_ITEM_DINING, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SubProParam paramOUT = (SubProParam) params.get(8);
		String temp = paramOUT.getString();
		int rs = Integer.parseInt(temp);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public boolean editItemDining(int id, String name, String def,
			String price, String image, String iunit, int lang, String user) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(def), 0);
		params.add(in);
		in = new SubProParam(new String(price), 0);
		params.add(in);
		in = new SubProParam(new String(user), 0);
		params.add(in);
		in = new SubProParam(new String(price), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(iunit), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(lang), 0);
		params.add(in);
		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);
		try {
			params = broker.executeSubPro(EDIT_ITEM_DINING, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public int addMenuService(eMenu menu, int idmenu) {
		int seq = -1;
		if (menu == null) {
			return seq;
		} else {
			seq = addMenu(menu, idmenu, -1, 1, 1);
			return seq;
		}
	}

	@SuppressWarnings("rawtypes")
	public Vector getMenuService(int langId, int idmenu) {
		System.out.println("getMenus");
		return getMainMenu(idmenu, langId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector getSubjectInOut(int itemId, int langId, String type,
			int idmenu) {
		Vector params = new Vector();
		SubProParam subPro = new SubProParam(new BigDecimal(itemId), 0);
		params.add(subPro);
		subPro = new SubProParam(new BigDecimal(langId), 0);
		params.add(subPro);
		subPro = new SubProParam(type, 0);
		params.add(subPro);
		subPro = new SubProParam(new BigDecimal(idmenu), 0);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		Vector vImages = new Vector();
		try {
			params = broker.executeSubPro(sqlGetSubjectInOutNew, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		vImages = DataUtils.LoadMenuPms(outParam);
		// String outScreen = (new
		// StringBuilder("[getSubjectInOut with params: itemId=")).append(itemId).append(",type=").append(type).append("] : returnValue(size=").append(vImages.size()).append(")").toString();
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public String getSubject(int LangID, int idmenu) {
		Vector<eMenu> subject = getMenuService(LangID, idmenu);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < subject.size(); i++) {
			eMenu item = subject.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getMenuName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getMenuId();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<imagebg>\n";
			mData += item.getUrlBg();
			mData += "</imagebg>";
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}

	@SuppressWarnings("unchecked")
	public String getSuboutHotel(int itemId, int LangID, int idmenu) {
		Vector<eMenu> menu = getSubjectsOutItem(itemId, LangID, idmenu);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < menu.size(); i++) {
			eMenu item = menu.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getMenuName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getMenuId();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	@SuppressWarnings("unchecked")
	public String getSubinHotel(int itemId, int LangID, int idmenu) {
		Vector<eMenu> menu = getSubjectsInItem(itemId, LangID, idmenu);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < menu.size(); i++) {
			eMenu item = menu.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getMenuName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getMenuId();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	@SuppressWarnings("rawtypes")
	public Vector getSubjectsInItem(int itemId, int langId, int idmenu) {
		return getSubjectInOut(itemId, langId, "IN", idmenu);
	}

	@SuppressWarnings("rawtypes")
	public Vector getSubjectsOutItem(int itemId, int langId, int idmenu) {
		return getSubjectInOut(itemId, langId, "OUT", idmenu);
	}

	public boolean UpdateMessage(String mess) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = null;
		in = new SubProParam(new String(mess), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(UPDATE_MESSAGE_WELCOME, params);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getMessage() {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		Vector vImages = new Vector();
		try {
			params = broker.executeSubPro(GET_MESSAGE_WELCOME, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		vImages = DataUtils.LoadMenuPms(outParam);
		System.out.println(vImages);
		return "";
	}

	@SuppressWarnings({ "rawtypes" })
	public String getWelcomeMessage() {
		String mess= "";
		String sql = "select value from ehotel_config where key = 'message_content'";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			mess=result.getParam("VALUE");
//			System.out.println(mess);
		}
		return mess;
	}
	
	@SuppressWarnings("unchecked")
	public String getTimePromotion(){
		String rs = "";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_TIME_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	@SuppressWarnings("unchecked")
	public boolean updateTimePromotion(String timeshow, String timehide){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(timeshow), 0);
		params.add(in);
		in = new SubProParam(new String(timehide), 0);
		params.add(in);
		
		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);
		try {
		params = broker.executeSubPro(UPDATE_TIME_PROMOTION, params);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	public static void main(String[] args) {
		PMSServiceDBI p = new PMSServiceDBI();
		System.out.println(p.getTimePromotion());
	}
}
