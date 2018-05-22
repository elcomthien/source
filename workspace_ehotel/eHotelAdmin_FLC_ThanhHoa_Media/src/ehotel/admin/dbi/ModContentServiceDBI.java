package ehotel.admin.dbi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.admin.model.VodSubjectService;

public class ModContentServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	static IMBroker2 broker2 = IMBroker2.getInstance();
	public static final String ADD_MOD = "BEGIN EMOD.ADDMOD(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String SET_URL= "BEGIN EMOD.setURL(?,?,?); END;";
	public static final String UPDATE_MOD_SUBJECT_SERVICE = "BEGIN EMOD.updateInviModSubSrv(?,?); END;";
	public static final String DELETE_MOD = "BEGIN EMOD.removeMod(?,?); END;";
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static final String REMOVE_SUBJECT = "BEGIN EMOD.removeSubject(?,?,?); END;";
	public static final String CHANGE_SUBJECT_MOD = "BEGIN EMOD.changeSubjectOfMod(?,?,?,?); END;";
	public static final String ADD_SUBJECT = "BEGIN EMOD.addSubject(?,?,?,?,?,?); END;";
	public static final String EDIT_SUBJECT = "BEGIN EMOD.addSubject(?,?,?,?,?,?,?); END;";
	public static final String UPDATE_MOD = "BEGIN EMOD.updateMod(?,?,?,?,?,?,?,?,?); END;";
	public static final String ADD_SERVICE_MOD = "BEGIN EMOD.addSvcMod(?,?,?); END;";
	public static final String REMOVE_SERVICE_MOD = "BEGIN EMOD.removeSvcMod(?,?,?); END;";
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	public String getFileNameModById(int modId) {
		String filename = "";
		String sql = "select distinct url from mod_content where contentid = " + modId;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				result.moveToFirst();
				filename = result.getParam("URL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	@SuppressWarnings("rawtypes")
	public String getSeqForMod() {
		String seq = "";
		String sql = "select SEQ_CONTENT_ID.nextval from dual";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			System.out.println(vector);
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			seq = result.getParam("NEXTVAL");
			System.out.println(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seq;
	}

	@SuppressWarnings("rawtypes")
	public String getMaxId() {
		String id = "";
		String sql = "select max(contentid) from mod_content";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			System.out.println(vector);
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			id = result.getParam("max(contentid)");
			System.out.println(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean updateUrlForMod(int id, String url) {
		boolean flag = true;
//		String sql = "update mod_content set url = '" + url + "' where contentid = " + id;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(url), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new BigDecimal(-1), SubProParam.OUT);
		params.add(in);
		try {
			broker.executeSubPro(SET_URL, params);
			broker2.executeSubPro(SET_URL, params);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int addModDbi(int subjectid, String title) {
		Vector params = new Vector(11);
		SubProParam in = null;
		in = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
		params.add(in); // 0
		in = new SubProParam(new java.lang.String(title), SubProParam.IN);
		params.add(in); // 1
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 2
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 3
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 4
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 5
		in = new SubProParam(new java.lang.String(title), SubProParam.IN);
		params.add(in); // 6
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 7
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 8
		in = new SubProParam(new BigDecimal(0), SubProParam.IN);
		params.add(in); // 9

		SubProParam out = new SubProParam(new String(), SubProParam.OUT);
		params.add(out); // 10
		try {
			params = broker.executeSubPro(ADD_MOD, params);
			broker2.executeSubPro(ADD_MOD, params);
			// System.out.println("B1");
			// out = (SubProParam)params.get(10);
			// System.out.println("B2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SubProParam paramOUT = (SubProParam) params.get(10);
		String temp = paramOUT.getString();
		int rs = Integer.parseInt(temp);
		// SubProParam paramOUT = (SubProParam) params.get(10);
		// String temp = paramOUT.getString();
		// System.out.println("ADD_NSD:" + temp);

		return rs;
	}
	
	@SuppressWarnings("rawtypes")
	public List<VodSubjectService> getVodSubjectService(int langid) {
		List<VodSubjectService> list = new ArrayList<VodSubjectService>();
		String sql = "select mo.subjectid,mo.subjectname,url.url_image,url.url_background,mo.parentid,mo.invisible " +
				"from mod_subject mo left join pms_imageurl url on mo.url_image = url.image_id  " +
				"where mo.style_id = 1 and mo.lang_id = " + langid;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				VodSubjectService vod = new VodSubjectService();
				vod.setSubjectid(result.getParam("SUBJECTID"));
				vod.setSubjectname(result.getParam("SUBJECTNAME"));
				vod.setSubjectimage(result.getParam("URL_IMAGE"));
				vod.setSubjectparent(result.getParam("PARENTID"));
				vod.setSubjectinvisible(result.getParam("INVISIBLE"));
				list.add(vod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean updateInvisibleModSubjectService(int id, int invi){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new BigDecimal(invi), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(UPDATE_MOD_SUBJECT_SERVICE, params);
			broker2.executeSubPro(UPDATE_MOD_SUBJECT_SERVICE, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void main(String[] args) {
		ModContentServiceDBI m = new ModContentServiceDBI();
		System.out.println(m.getVodSubjectService(2));
	}
	
	public void deleteModDbi(int modid) {
		int seqout = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(modid), SubProParam.IN);
		params.add(in); // 0
		SubProParam out = new SubProParam(new BigDecimal(seqout), SubProParam.OUT);
		params.add(out); // 1
		try {
			broker.executeSubPro(DELETE_MOD, params);
			broker2.executeSubPro(DELETE_MOD, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//////////////////dbi2//////////////////////////////////////////////////////
	
	public boolean removeSubject(int subjectId, String type) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(subjectId), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);
		SubProParam out = new SubProParam(new BigDecimal(-1), 1);
		params.add(out);
		try {
			broker2.executeSubPro(REMOVE_SUBJECT, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean changeSubjectOfMod(int modId, String str, String type) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(modId), 0);
		params.add(in);
		in = new SubProParam(new String(str), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);
		SubProParam out = new SubProParam(new BigDecimal(-1), 1);
		params.add(out);
		try {
			broker2.executeSubPro(CHANGE_SUBJECT_MOD, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean addNewSubject(String name, String image, String bg, int parent, String type){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(bg), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(parent), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);
		
		SubProParam out = new SubProParam(new BigDecimal(-1), 1);
		params.add(out);
		try {
			broker2.executeSubPro(ADD_SUBJECT, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean editSubject(int subId, String name, String image, String bg, int lang, String type){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(subId), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		in = new SubProParam(new String(bg), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(lang), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);
		
		SubProParam out = new SubProParam(new BigDecimal(-1), 1);
		params.add(out);
		try {
			broker2.executeSubPro(EDIT_SUBJECT, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateMod(int modId, String title, String singer, String composer, String lyric, String album, int lang, String type){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(modId), 0);
		params.add(in);
		in = new SubProParam(new String(title), 0);
		params.add(in);
		in = new SubProParam(new String(singer), 0);
		params.add(in);
		in = new SubProParam(new String(composer), 0);
		params.add(in);
		in = new SubProParam(new String(lyric), 0);
		params.add(in);
		in = new SubProParam(new String(album), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(lang), 0);
		params.add(in);
		in = new SubProParam(new String(type), 0);
		params.add(in);
		
		SubProParam out = new SubProParam(new BigDecimal(-1), 1);
		params.add(out);
		try {
			broker2.executeSubPro(UPDATE_MOD, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean addServiceMod(int subId, String str) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(subId), 0);
		params.add(in);
		in = new SubProParam(new String(str), 0);
		params.add(in);
		
		SubProParam out = new SubProParam(new BigDecimal(-1), 1);
		params.add(out);
		try {
			broker2.executeSubPro(ADD_SERVICE_MOD, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean removeServiceMod(int subId, String str) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(subId), 0);
		params.add(in);
		in = new SubProParam(new String(str), 0);
		params.add(in);
		
		SubProParam out = new SubProParam(new BigDecimal(-1), 1);
		params.add(out);
		try {
			broker2.executeSubPro(REMOVE_SERVICE_MOD, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	
}
