package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.VodContentModel;
import com.elcom.ehotel.admin.model.VodPercentModel;
import com.elcom.ehotel.admin.model.VodSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;

public class TestDao {

	@SuppressWarnings("unchecked")
	public List<VodSubjectModel> getListSubjectVod(int langId, String type) {
		List<VodSubjectModel> list = new ArrayList<VodSubjectModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_VOD_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.GET_LIST_VOD_SUBJECT, params, "langid,type", outParam.size() / 7);
		for (int i = 0; i < outParam.size(); i = i + 7) {
			VodSubjectModel vod = new VodSubjectModel();
			vod.setId(outParam.get(i));
			vod.setName(outParam.get(i + 1));
			vod.setCreatedate(outParam.get(i + 2));
			vod.setImage(outParam.get(i + 3));
			vod.setIndex(outParam.get(i + 4));
			vod.setParent(outParam.get(i + 5));
			vod.setInvisible(outParam.get(i + 6));
			list.add(vod);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addVodSubject(VodSubjectModel vod) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(vod.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(vod.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(vod.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new String(vod.getType()), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_VOD_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.ADD_VOD_SUBJECT, params, "name,image,invisible,type", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editVodSubject(VodSubjectModel vod) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(vod.getId()), 0);
		params.add(in);
		in = new SubProParam(new String(vod.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(vod.getImage()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(vod.getLangid()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(vod.getInvisible()), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_VOD_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.EDIT_VOD_SUBJECT, params, "idsubject,name,image,invisible,type", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteVodSubject(int idSubject) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_VOD_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.DELETE_VOD_SUBJECT, params, "idsubject", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<VodContentModel> getListContent(VodContentModel vod) {
		List<VodContentModel> list = new ArrayList<VodContentModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(vod.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(vod.getLangid()), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_VOD_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.GET_LIST_VOD_CONTENT, params, "idsubject,langid", outParam.size() / 15);
		for (int i = 0; i < outParam.size(); i = i + 15) {
			VodContentModel con = new VodContentModel();
			con.setIdSubject(outParam.get(i));
			con.setIdContent(outParam.get(i + 1));
			con.setName(outParam.get(i + 2));
			con.setProductor(outParam.get(i + 3));
			con.setDirector(outParam.get(i + 4));
			con.setActor(outParam.get(i + 5));
			con.setPoster(outParam.get(i + 6));
			con.setPlot(outParam.get(i + 7));
			con.setPrice(outParam.get(i + 8));
			con.setIunit(outParam.get(i + 9));
			con.setUrl(outParam.get(i + 10));
			con.setInvisible(outParam.get(i + 11));
			con.setIsnew(outParam.get(i + 12));
			con.setLangid(outParam.get(i + 13));
			con.setSubtitle(outParam.get(i + 14));
			list.add(con);
		}
		return list;
	}

	// subjectidin in number,namein in varchar2,productorin in
	// varchar2,directorin in varchar2,
	// actorin in varchar2,posterin in varchar2,plotin in varchar2, pricein in
	// varchar2,
	// iunitin in varchar2,urlin in varchar2,invisiblein in number,isnewin in
	// number,seq out number

	@SuppressWarnings("unchecked")
	public int addNewMovie(VodContentModel con) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(con.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getProductor()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getDirector()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getActor()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getPoster()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getPlot()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getPrice()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getIunit()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getUrl()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(con.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(con.getIsnew()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_VOD_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(12);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.ADD_VOD_CONTENT, params,
				"idsubject,name,production,director,actor,poster,plot,price,iunit,url,invisible,isnew", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editMovie(VodContentModel con) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(con.getIdSubject()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(con.getIdContent()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getProductor()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getDirector()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getActor()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getPoster()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getPlot()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getPrice()), 0);
		params.add(in);
		in = new SubProParam(new String(con.getIunit()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(con.getInvisible()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(con.getIsnew()), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(con.getLangid()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_VOD_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(13);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.EDIT_VOD_CONTENT, params,
				"idsubject,idcontent,name,production,director,actor,poster,plot,price,iunit,invisible,isnew,langid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteMovie(int idContent) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idContent), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_VOD_CONTENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.DELETE_VOD_CONTENT, params, "idcontent", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int insertVodRemote(VodPercentModel per) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(per.getFilename()), 0);
		params.add(in);
		in = new SubProParam(new String(per.getUuid()), 0);
		params.add(in);
		in = new SubProParam(new String(per.getNameview()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.INSERT_VOD_REMOTE_PERCENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.INSERT_VOD_REMOTE_PERCENT, params, "filename,uuid,title", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteVodRemotePercent(String uuid) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(uuid), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELTE_VOD_REMOTE_PERCENT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.DELTE_VOD_REMOTE_PERCENT, params, "uuid", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<VodPercentModel> getListVodRemotePercent() {
		List<VodPercentModel> list = new ArrayList<VodPercentModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_VOD_REMOTE_PERCENT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.GET_VOD_REMOTE_PERCENT, params, "none", outParam.size() / 3);
		for (int i = 0; i < outParam.size(); i = i + 3) {
			VodPercentModel per = new VodPercentModel();
			per.setFilename(outParam.get(i));
			per.setNameview(outParam.get(i + 1));
			per.setUuid(outParam.get(i + 2));
			list.add(per);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getSubtitle(int vodId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(vodId), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBTITLE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.GET_SUBTITLE, params, "vodId", outParam.size() / 3);
		for (int i = 0; i < outParam.size(); i = i + 3) {
			HashMap<String, String> hmap = new HashMap<String, String>();
			hmap.put("langid", outParam.get(i));
			String url = outParam.get(i + 1);
			if (url == null)
				hmap.put("filepath", "");
			else
				hmap.put("filepath", url);
			hmap.put("langname", outParam.get(i + 2));
			list.add(hmap);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addOrEditSubtitle(int vodId, String listSub) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(vodId), 0);
		params.add(in);
		in = new SubProParam(new String(listSub), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_OR_EDIT_SUBTITLE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.ADD_OR_EDIT_SUBTITLE, params, "vodId,listSub", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteSubtitle(int subId) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(subId), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_SUBTITLE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(TestDao.class.toString(), SQL.DELETE_SUBTITLE, params, "subId", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public String getlistbill(String sc) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(sc), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro("BEGIN PMSAPP.getBills(?,?); END;", params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
				System.out.println(outParam);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		TestDao v = new TestDao();
		System.out.println(v.getlistbill("CPCLEH4SPKOAO"));
	}
}
