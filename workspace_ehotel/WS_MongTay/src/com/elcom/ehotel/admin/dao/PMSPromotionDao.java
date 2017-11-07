package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.PMSPromotionModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSPromotionDao {
	@SuppressWarnings("unchecked")
	public List<PMSPromotionModel> getListPromotion(int langId) {
		List<PMSPromotionModel> list = new ArrayList<PMSPromotionModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);
		
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSPromotionDao.class.toString(), SQL.GET_LIST_PROMOTION, params, "langId", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			PMSPromotionModel pro = new PMSPromotionModel();
			pro.setId(outParam.get(i));
			pro.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			pro.setDef(UnicodeConverter.decodeUnicode(outParam.get(i + 2)));
			pro.setUrl(outParam.get(i + 3));
			pro.setImage(outParam.get(i + 4));
			pro.setInvisible(outParam.get(i + 5));
			list.add(pro);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public int addPromotion(PMSPromotionModel pro){
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(pro.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getDef()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getUrl()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getInvisible()), 0);
		params.add(in);
		

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LogUtil.logDao(PMSPromotionDao.class.toString(), SQL.ADD_PROMOTION, params, "name,def,url,image,icon,invisible", rs);

		return rs;
	}
	
	@SuppressWarnings("unchecked")
	public int editPromotion(PMSPromotionModel pro){
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(pro.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getDef()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getUrl()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(pro.getLangid()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LogUtil.logDao(PMSPromotionDao.class.toString(), SQL.EDIT_PROMOTION, params, "id,name,def,url,image,icon,invisible,langid", rs);

		return rs;
	}
	
	@SuppressWarnings("unchecked")
	public int deletePromotion(int id){
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_PROMOTION, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LogUtil.logDao(PMSPromotionDao.class.toString(), SQL.DELETE_PROMOTION, params, "id", rs);

		return rs;
	}
	
	public static void main(String[] args) {
		PMSPromotionDao p = new PMSPromotionDao();
//		String def = "Work. Connect. Succeed. When traveling, you need a partner that makes both work and relaxation easy. At Crowne Plaza Hotels Resorts, we focus on one thing and do it well making your stay a success. With more than 400 properties worldwide, our premium accommodations,business ready services and spaces, and fast and fresh meal options are designed to keep you productive, connected, rested, and restored during your stay. Whatever your business or personal needs, our expert staff is on it.";
		System.out.println(p.getListPromotion(2));
	}
}
