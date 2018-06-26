package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.ModContentModel;
import com.elcom.ehotel.admin.model.ModSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class ModDao {

	@SuppressWarnings("unchecked")
	public List<ModSubjectModel> getListSubjectMod(int langid) {
		List<ModSubjectModel> list = new ArrayList<ModSubjectModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langid), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_MOD_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.GET_LIST_MOD_SUBJECT, params, "langid", outParam.size() / 6);

		for (int i = 0; i < outParam.size(); i = i + 6) {
			ModSubjectModel mod = new ModSubjectModel();
			mod.setIdSubject(outParam.get(i));
			mod.setName(outParam.get(i + 1));
			mod.setImage(outParam.get(i + 2));
			mod.setImageIC(outParam.get(i + 3));
			mod.setIndex(outParam.get(i + 4));
			mod.setInvisible(outParam.get(i + 5));
			list.add(mod);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addSubjectMod(ModSubjectModel sub) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(sub.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImageIC()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getInvisible()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_MOD_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.ADD_MOD_SUBJECT, params, "name,image,imageic,invisible", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editSubjectMod(ModSubjectModel sub) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(sub.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImageIC()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getLangid()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_MOD_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.EDIT_MOD_SUBJECT, params, "idSubject,name,image,imageic,invisible,langid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteSubjectMod(int idSubject) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_MOD_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.DELETE_MOD_SUBJECT, params, "idSubject", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<ModContentModel> getListContent(int idSubject, int langid) {
		List<ModContentModel> list = new ArrayList<ModContentModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langid), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_MOD_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.GET_LIST_MOD_CONTENT, params, "idSubject,langid", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			ModContentModel mod = new ModContentModel();
			mod.setIdSubject(outParam.get(i));
			mod.setIdContent(outParam.get(i + 1));
			mod.setName(outParam.get(i + 2));
			mod.setUrl(outParam.get(i + 3));
			mod.setInvisible(outParam.get(i + 4));
			list.add(mod);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addMod(ModContentModel con) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(con.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getUrl()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getInvisible()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_MOD_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.ADD_MOD_CONTENT, params, "idSubject,name,url,invisible", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editMod(ModContentModel con) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(con.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(con.getIdContent()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getLangid()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_MOD_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.EDIT_MOD_CONTENT, params, "idSubject,idContent,name,invisible,langid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteMod(int idMod) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idMod), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_MOD_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.DELETE_MOD_CONTENT, params, "idmod", rs);
		return rs;
	}

	// /////////////////////Group/////////////////////////////////
	@SuppressWarnings("unchecked")
	public List<ModSubjectModel> getListSubjectModGroup(int langid, String idgroup) {
		List<ModSubjectModel> list = new ArrayList<ModSubjectModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langid), 0);
		params.add(in);
		in = new SubProParam(new String(idgroup), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_MOD_SUBJECT_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.GET_LIST_MOD_SUBJECT_GROUP, params, "langid,idgroup", outParam.size() / 6);

		for (int i = 0; i < outParam.size(); i = i + 6) {
			ModSubjectModel mod = new ModSubjectModel();
			mod.setIdSubject(outParam.get(i));
			mod.setName(outParam.get(i + 1));
			mod.setImage(outParam.get(i + 2));
			mod.setImageIC(outParam.get(i + 3));
			mod.setIndex(outParam.get(i + 4));
			mod.setInvisible(outParam.get(i + 5));
			list.add(mod);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addSubjectModGroup(ModSubjectModel sub) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(sub.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImageIC()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getIdGroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_MOD_SUBJECT_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.ADD_MOD_SUBJECT_GROUP, params, "name,image,imageic,invisible,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editSubjectModGroup(ModSubjectModel sub) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(sub.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getImageIC()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getLangid()), 0);
		params.add(in);
		in = new SubProParam(new String(sub.getIdGroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_MOD_SUBJECT_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.EDIT_MOD_SUBJECT_GROUP, params,
				"idSubject,name,image,imageic,invisible,langid,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteSubjectModGroup(int idSubject, String idgroup) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		in = new SubProParam(new String(idgroup), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_MOD_SUBJECT_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.DELETE_MOD_SUBJECT_GROUP, params, "idSubject,idgorup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<ModContentModel> getListContentGroup(int idSubject, int langid, String idgroup) {
		List<ModContentModel> list = new ArrayList<ModContentModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langid), 0);
		params.add(in);
		in = new SubProParam(new String(idgroup), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_MOD_CONTENT_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.GET_LIST_MOD_CONTENT_GROUP, params, "idSubject,langid,idgroup", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			ModContentModel mod = new ModContentModel();
			mod.setIdSubject(outParam.get(i));
			mod.setIdContent(outParam.get(i + 1));
			mod.setName(outParam.get(i + 2));
			mod.setUrl(outParam.get(i + 3));
			mod.setInvisible(outParam.get(i + 4));
			list.add(mod);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addModGroup(ModContentModel con) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(con.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getUrl()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getIdGroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_MOD_CONTENT_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.ADD_MOD_CONTENT_GROUP, params, "idSubject,name,url,invisible,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editModGroup(ModContentModel con) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(con.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(con.getIdContent()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getLangid()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getIdGroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_MOD_CONTENT_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.EDIT_MOD_CONTENT_GROUP, params, "idSubject,idContent,name,invisible,langid,idgroup", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteModGroup(int idMod, String idGroup) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idMod), 0);
		params.add(in);
		in = new SubProParam(new String(idGroup), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_MOD_CONTENT_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(ModDao.class.toString(), SQL.DELETE_MOD_CONTENT_GROUP, params, "idmod,idgroup", rs);
		return rs;
	}
}
