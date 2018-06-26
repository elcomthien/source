package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.PMSInfoContentModel;
import com.elcom.ehotel.admin.model.PMSInfoSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class PMSInfoDao {

	@SuppressWarnings("unchecked")
	public List<PMSInfoSubjectModel> getListSubjectInfo(int serviceId, int langId) {
		List<PMSInfoSubjectModel> list = new ArrayList<PMSInfoSubjectModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(serviceId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.GET_SUBJECT_INFO, params, "serviceid,langid", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			PMSInfoSubjectModel info = new PMSInfoSubjectModel();
			info.setSubjectId(outParam.get(i));
			info.setSubjectName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			info.setServiceId(outParam.get(i + 2));
			info.setInvisible(outParam.get(i + 3));
			info.setImage(outParam.get(i + 4));
			info.setImageIC(outParam.get(i + 5));
			info.setType(outParam.get(i + 6));
			info.setIndex(outParam.get(i + 7));
			info.setLangId(outParam.get(i + 8));
			list.add(info);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addSubjectInfo(PMSInfoSubjectModel info) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(info.getServiceId()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getSubjectName()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getImageIC()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_SUBJECT_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.ADD_SUBJECT_INFO, params, "serviceid,name,image,imageic,invisible,index", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editSubjectInfo(PMSInfoSubjectModel info) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(info.getServiceId()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(info.getSubjectId()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getSubjectName()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getImageIC()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getLangId()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_SUBJECT_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.EDIT_SUBJECT_INFO, params,
				"serviceId,subjectId,name,image,imageic,invisible,index,langid", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteSubjectInfo(int serviceId, int subjectId) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(serviceId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(subjectId), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_SUBJECT_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.DELETE_SUBJECT_INFO, params, "serviceId,subjectId", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<PMSInfoContentModel> getListContentInfo(int subjectId, int langId) {
		List<PMSInfoContentModel> list = new ArrayList<PMSInfoContentModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(subjectId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_CONTENT_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.GET_CONTENT_INFO, params, "subjectid,langid", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			PMSInfoContentModel info = new PMSInfoContentModel();
			info.setSubjectId(outParam.get(i));
			info.setContentId(outParam.get(i + 1));
			info.setName(UnicodeConverter.decodeUnicode(outParam.get(i + 2)));
			info.setDescription(outParam.get(i + 3));
			// info.setImage(outParam.get(i + 4));
			info.setInvisible(outParam.get(i + 4));
			info.setIndex(outParam.get(i + 5));
			list.add(info);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addContentInfo(PMSInfoContentModel info) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(info.getSubjectId()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getDescription()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_CONTENT_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.ADD_CONTENT_INFO, params, "subjectId,name,description,invisible,index", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editContentInfo(PMSInfoContentModel info) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(info.getContentId()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getLangId()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getIndex()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_CONTENT_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.EDIT_CONTENT_INFO, params, "contentId,name,invisible,langId,index", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteContentInfo(int contentId) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(contentId), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_CONTENT_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.DELETE_CONTENT_INFO, params, "contentid", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<PMSInfoSubjectModel> getListSubjectInfoGroup(int serviceId, int langId, String idgroup) {
		List<PMSInfoSubjectModel> list = new ArrayList<PMSInfoSubjectModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(serviceId), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);
		in = new SubProParam(new String(idgroup), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBJECT_INFO_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.GET_SUBJECT_INFO_GROUP, params, "serviceid,langid,idgroup", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			PMSInfoSubjectModel info = new PMSInfoSubjectModel();
			info.setSubjectId(outParam.get(i));
			info.setSubjectName(UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			info.setServiceId(outParam.get(i + 2));
			info.setInvisible(outParam.get(i + 3));
			info.setImage(outParam.get(i + 4));
			info.setImageIC(outParam.get(i + 5));
			info.setType(outParam.get(i + 6));
			info.setIndex(outParam.get(i + 7));
			info.setLangId(outParam.get(i + 8));
			list.add(info);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addSubjectInfoGroup(PMSInfoSubjectModel info) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(info.getServiceId()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getSubjectName()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getImageIC()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getIndex()), 0);
		params.add(in);
		in = new SubProParam(new String(info.getIdgroup()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_SUBJECT_INFO_GROUP, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(PMSInfoDao.class.toString(), SQL.ADD_SUBJECT_INFO_GROUP, params, "serviceid,name,image,imageic,invisible,index,idgroup", rs);

		return rs;
	}

	public static void main(String[] args) {
		PMSInfoDao p = new PMSInfoDao();
		System.out.println(p.getListSubjectInfo(25, 2));
	}
}
