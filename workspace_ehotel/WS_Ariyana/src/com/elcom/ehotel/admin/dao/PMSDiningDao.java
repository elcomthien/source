package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.PMSDiningDetailModel;
import com.elcom.ehotel.admin.model.PMSDiningItemModel;
import com.elcom.ehotel.admin.model.PMSDiningSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSDiningDao {

	@SuppressWarnings("unchecked")
	public List<PMSDiningSubjectModel> getSubjectDining(int parentId, int langId) {
		List<PMSDiningSubjectModel> list = new ArrayList<PMSDiningSubjectModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(parentId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_DINING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.GET_SUBJECT_DINING, params, "parentId,langid", outParam.size() / 8);
		for (int i = 0; i < outParam.size(); i = i + 8) {
			PMSDiningSubjectModel dn = new PMSDiningSubjectModel();
			dn.setId(outParam.get(i));
			dn.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			dn.setActive(outParam.get(i + 2));
			dn.setMenuno(outParam.get(i + 3));
			dn.setImage(outParam.get(i + 4));
			dn.setImageIC(outParam.get(i + 5));
			dn.setIndex(outParam.get(i + 6));
			dn.setParent(outParam.get(i + 7));
			dn.setLangId(String.valueOf(langId));
			list.add(dn);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addSubjectDining(PMSDiningSubjectModel sub) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(sub.getParent()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImageIC()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(sub.getActive()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(sub.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_SUBJECT_DINING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.ADD_SUBJECT_INFO, params, "parentId,name,image,imageic,active,index", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editSubjectDining(PMSDiningSubjectModel sub) {
		// System.out.println("editdiningdao");
		int rs = -1;
		// System.out.println("1");
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(sub.getId()), 0);
		params.add(in);
		// System.out.println("2");
		in = new SubProParam(new String(sub.getName()), 0);
		params.add(in);
		// System.out.println("3");
		in = new SubProParam(new String(sub.getImage()), 0);
		params.add(in);
		// System.out.println("4");
		in = new SubProParam(new String(sub.getImageIC()), 0);
		params.add(in);
		// System.out.println("5");
		in = new SubProParam(new String(sub.getActive()), 0);
		params.add(in);
		// System.out.println("6");
		in = new SubProParam(new String(sub.getIndex()), 0);
		params.add(in);
		// System.out.println("7");
		in = new SubProParam(new String(sub.getLangId()), 0);
		params.add(in);
		// System.out.println("8");

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		// System.out.println("9");
		// LogUtil.logDao(PMSDiningDao.class.toString(), SQL.EDIT_SUBJECT_DINING, params,
		// "subjectid,name,image,imageic,active,index,langid", 0);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_SUBJECT_DINING, params);
			// System.out.println("run sql");
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.EDIT_SUBJECT_DINING, params, "subjectid,name,image,imageic,active,index,langid",
				rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteSubjectDining(String menuId) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(menuId), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_SUBJECT_DINING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.DELETE_SUBJECT_DINING, params, "subjectid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<PMSDiningItemModel> getItemDining(String subjectId, String langId) {
		List<PMSDiningItemModel> list = new ArrayList<PMSDiningItemModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(subjectId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_ITEM_DINING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.GET_ITEM_DINING, params, "subjectId,langid", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			PMSDiningItemModel item = new PMSDiningItemModel();
			item.setId(outParam.get(i));
			item.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			item.setDef(UnicodeConverter.decodeUnicode(outParam.get(i + 2)));
			item.setActive(outParam.get(i + 3));
			item.setPrice(outParam.get(i + 4));
			item.setIunit(outParam.get(i + 5));
			item.setIndex(outParam.get(i + 6));
			item.setImage(outParam.get(i + 7));
			item.setDetail(outParam.get(i + 8));
			item.setSubjectId(subjectId);
			list.add(item);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addItemDining(PMSDiningItemModel item) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(item.getSubjectId()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getDef()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getPrice()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getIunit()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(item.getActive()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(item.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_ITEM_DINING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.ADD_ITEM_DINING, params, "subject,name,def,price,image,iunit,active,index", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editItemDining(PMSDiningItemModel item) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(item.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getDef()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getPrice()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getIunit()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(item.getActive()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(item.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(item.getLangid()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_ITEM_DINING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(9);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.EDIT_ITEM_DINING, params,
				"itemId,name,def,price,image,iunit,active,index,langid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteItemDining(String itemId) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(itemId), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_ITEM_DINING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.DELETE_ITEM_DINING, params, "itemId", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<PMSDiningDetailModel> getItemDetail(String id, String langid) {
		List<PMSDiningDetailModel> list = new ArrayList<PMSDiningDetailModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_ITEM_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.GET_ITEM_DETAIL, params, "itemid,langid", outParam.size() / 4);
		for (int i = 0; i < outParam.size(); i = i + 4) {
			System.out.println(UnicodeConverter.decodeUnicode(outParam.get(i + 2)));
			PMSDiningDetailModel item = new PMSDiningDetailModel(outParam.get(i), id, UnicodeConverter.decodeUnicode(outParam.get(i + 1)),
					outParam.get(i + 2), outParam.get(i + 3), langid);
			list.add(item);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addItemDetail(PMSDiningDetailModel item) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(item.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getDetail()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_ITEM_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.ADD_ITEM_DETAIL, params, "itemId,def,invisible,index", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editItemDetail(PMSDiningDetailModel item) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(item.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getDetail()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(item.getLangid()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_ITEM_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.EDIT_ITEM_DETAIL, params, "iddetail,def,invisible,index,langid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteItemDetail(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_ITEM_DETAIL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSDiningDao.class.toString(), SQL.DELETE_ITEM_DETAIL, params, "iddetail", rs);
		return rs;
	}

	public static void main(String[] args) {
		PMSDiningDao p = new PMSDiningDao();
		// System.out.println(p.getSubjectDining(1, 2));
		// PMSDiningSubjectModel sub = new PMSDiningSubjectModel();
		// sub.setId("14");
		// sub.setName("Beverages");
		// sub.setImage("/Main/1496823388568.png");
		// sub.setImageIC("null");
		// sub.setActive("1");
		// sub.setIndex("5");
		// sub.setLangId("2");
		// id=14, name=Beverages, active=1, menuno=, image=/Main/1496823388568.png, imageIC=null, index=5, parent=, langId=2]
		System.out.println(p.getItemDetail("1442", "1"));
		// System.out.println(p.getItemDining("6", "2"));
	}
}
