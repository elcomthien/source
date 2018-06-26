package ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.jcp.xml.dsig.internal.dom.Utils;

import com.elcom.DBI.SubProParam;

import ehotel.admin.dbi.IMBroker;
import ehotel.admin.model.DiningPromotionModel;
import ehotel.admin.util.UtilString;

public class DiningDao {
	static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LIST_DINING_PRO = "BEGIN EPMS.getDiningPromotion(?,?,?,?,?); END;";
	public static final String ADD_DINING_PRO = "BEGIN EPMS.addItemDiningPromotion(?,?,?,?,?,?,?,?); END;";
	public static final String EDIT_DINING_PRO = "BEGIN EPMS.editItemDiningPromotion(?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_DINING_PRO = "BEGIN EPMS.deleteItemDiningPromotion(?,?); END;";
	public static final String CHANGE_INVI_DINING_PRO = "BEGIN EPMS.updateInviItemDiningPromotion(?,?); END;";

	@SuppressWarnings("unchecked")
	public List<DiningPromotionModel> getListDiningPromotion(int subjectId, int langId, int from, int to) {
		List<DiningPromotionModel> list = new ArrayList<DiningPromotionModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(subjectId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(from), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(to), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_LIST_DINING_PRO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 8) {
			DiningPromotionModel itm = new DiningPromotionModel();
			itm.setId(outParam.get(i));
			itm.setName(outParam.get(i + 1));
			itm.setDef(outParam.get(i + 2));
			itm.setNewPrice(outParam.get(i + 3));
			itm.setOldPrice(outParam.get(i + 4));
			itm.setIunit(outParam.get(i + 5));
			itm.setImage(outParam.get(i + 6));
			itm.setInvisible(outParam.get(i + 7));
			list.add(itm);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public int addItemDingningPromotion(DiningPromotionModel itm){
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(itm.getSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getDef()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getNewPrice()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getOldPrice()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getIunit()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getImage()), 0);
		params.add(in);
		
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = broker.executeSubPro(ADD_DINING_PRO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = UtilString.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	@SuppressWarnings("unchecked")
	public int editItemDingningPromotion(DiningPromotionModel itm){
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(itm.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getDef()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getNewPrice()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getOldPrice()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getIunit()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(itm.getLangId()), 0);
		params.add(in);
		
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = broker.executeSubPro(EDIT_DINING_PRO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = UtilString.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	@SuppressWarnings("unchecked")
	public int deleteItemDiningPromotion(String id){
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = broker.executeSubPro(DELETE_DINING_PRO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = UtilString.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	@SuppressWarnings("unchecked")
	public int changeInviItemDiningPromotion(String id){
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = broker.executeSubPro(CHANGE_INVI_DINING_PRO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = UtilString.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args) {
		DiningDao d = new DiningDao();
		System.out.println(d.getListDiningPromotion(325, 2, 1, 6));
	}
}
