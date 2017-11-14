package ehotel.admin.dbi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.admin.model.LuxuryModel;
import ehotel.admin.model.PMSMainMenu;

public class PMSServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	public static final String ADD_ITEM_DINING = "BEGIN EPMS.ADDROOMSVCITEM(?,?,?,?,?,?,?,?,?); END;";
	public static final String UPDATE_MAINMENU_INVISIBLE = "BEGIN EPMS.updateInviMainMenu(?,?); END;";
	public static final String UPDATE_DININGMENU_INVISIBLE = "BEGIN EPMS.updateInviDiningMenu(?,?); END;";
	public static final String ADD_SUBJECT = "BEGIN EPMS.addmenu(?,?,?,?,?,?,?,?); END;";
	public static final String ADD_MENU_DINING = "BEGIN EPMS.addMenuDining(?,?,?,?,?); END;";
	public static final String GET_LUXURY = "BEGIN EPMS.getInfoVideo(?,?); END;";

	@SuppressWarnings("unchecked")
	public int addItemDining(int menuid, String name, String def, String price, String price_large, String price_small, String url,
			String unit) {
		Vector<SubProParam> params = new Vector<SubProParam>(11);
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

	@SuppressWarnings("rawtypes")
	public List<PMSMainMenu> getMainMenu(int rvcid, int langid) {
		List<PMSMainMenu> list = new ArrayList<PMSMainMenu>();
		String sql = "select m.mmenuno,m.mmenuname,url.url_image,url.url_background,url.url_picicon,m.rvcservice_id,m.menutype, m.INVISIBLE "
				+ "from PMS_MAINMENU m left join pms_imageurl url on m.image_id=url.image_id "
				+ "where m.rvcservice_id= "
				+ rvcid
				+ " and lang_id= " + langid + " order by orderby";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				PMSMainMenu menu = new PMSMainMenu();
				menu.setMenuid(result.getParam("mmenuno"));
				menu.setMenuname(result.getParam("mmenuname"));
				menu.setMenuimage(result.getParam("url_image"));
				menu.setMenubackground(result.getParam("url_background"));
				menu.setMenurvcserviceid(result.getParam("rvcservice_id"));
				menu.setMenutype(result.getParam("menutype"));
				menu.setMenuinvisible(result.getParam("INVISIBLE"));
				list.add(menu);
			}
		}
		return list;
	}

	public boolean updateInvisibleMainMenu(int id, int invi) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new BigDecimal(invi), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(UPDATE_MAINMENU_INVISIBLE, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public boolean updateInvisibleDiningMenu(int id, int invi) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new BigDecimal(invi), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(UPDATE_DININGMENU_INVISIBLE, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public List<PMSMainMenu> getMenuDining(int id, int langid) {
		List<PMSMainMenu> list = new ArrayList<PMSMainMenu>();
		String sql = "select m.menuno,m.menuname,url.url_image,url.url_background,url.url_picicon,m.parent_id,m.menu_type, m.menuactive "
				+ "from PMS_MENU m left join pms_imageurl url on m.image_id=url.image_id " + "where m.mmmenuno=" + id + " and lang_id="
				+ langid;
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				PMSMainMenu menu = new PMSMainMenu();
				menu.setMenuid(result.getParam("menuno"));
				menu.setMenuname(result.getParam("menuname"));
				menu.setMenuimage(result.getParam("url_image"));
				menu.setMenubackground(result.getParam("url_background"));
				menu.setMenutype(result.getParam("menu_type"));
				menu.setMenurvcserviceid(result.getParam("parent_id"));
				menu.setMenuinvisible(result.getParam("menuactive"));
				System.out.println(menu.toString());
				list.add(menu);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean addSubjectHotel(String name, int rvcserviceid, int level, String image, String bg) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(rvcserviceid), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(-1), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(level), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(bg), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(-1), 0);
		params.add(in);

		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);

		try {
			params = broker.executeSubPro(ADD_SUBJECT, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public int addMenuDining(int parentid, String name, String image, String bg) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(parentid), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(bg), 0);
		params.add(in);

		SubProParam out = new SubProParam(new BigDecimal(-1), 1);
		params.add(out);

		try {
			params = broker.executeSubPro(ADD_MENU_DINING, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<LuxuryModel> getListLuxury(int langid) {
		List<LuxuryModel> list = new ArrayList<LuxuryModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langid), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_LUXURY, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < outParam.size(); i = i + 4) {
			LuxuryModel lux = new LuxuryModel();
			lux.setId(outParam.get(i));
			lux.setName(outParam.get(i + 1));
			lux.setDef(outParam.get(i + 2));
			lux.setType(outParam.get(i + 3));
			list.add(lux);
		}
		return list;
	}

	public static void main(String[] args) {
		PMSServiceDBI p = new PMSServiceDBI();
		System.out.println(p.getListLuxury(1));
	}
}
