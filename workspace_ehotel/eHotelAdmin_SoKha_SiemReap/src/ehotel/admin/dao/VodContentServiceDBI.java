package ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.admin.Vod.InfoUpload;
import ehotel.admin.Vod.SubjectMovieModel;
import ehotel.admin.Vod.VodPercent;
import ehotel.admin.dbi.IMBroker;
import ehotel.domain.vod.Subject;

public class VodContentServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	public static final String DELETE_VOD_PERCENT = "BEGIN EVOD.deleteVodTransferPercent; END;";
	public static final String UPDATE_STATUS_VOD = "BEGIN EVOD.updateStatusInvisibleVod(?,?); END;";
	public static final String DELETE_VOD_BY_ID = "BEGIN EVOD.deleteVodById(?); END;";
	public static final String UPDATE_URL_FOR_VOD = "BEGIN EVOD.updateUrlForVod(?,?); END;";
	public static final String INSERT_VOD_REMOTE_PERCENT = "BEGIN EVOD.insertVodRemotePercent(?,?,?); END;";
	public static final String DELETE_SUBTITLE_BY_URL = "BEGIN EVOD.deleteSubVodByUrl(?); END;";
	public static final String UPDATE_IP_SERVER = "BEGIN EVOD.updateIpServerVod(?); END;";
	public static final String ADD_VOD = "BEGIN EVOD.addVod(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_VOD = "BEGIN EVOD.removeVod(?,?); END;";
	public static final String ADD_SUBTITLE = "BEGIN EVOD.addSubtitle(?,?,?,?,?); END;";
	public static final String DELETE_SUBTITLE = "BEGIN EVOD.removeSubtitle(?,?); END;";

	private InfoUpload infoUpload = new InfoUpload();

	public boolean updateIpServerVod(String ipserver) {
		String sql = "update vod_contents set ipserver = '"
				+ ipserver
				+ "' where CONTENTID = (select max(CONTENTID) from VOD_CONTENTS)";
		boolean flag = true;
		try {
			broker.executeBlockSQLStmt(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public String getFileNameContentById(int contentId) {
		String filename = "";
		String sql = "select distinct filepath from vod_contents where contentid = "
				+ contentId;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				result.moveToFirst();
				filename = result.getParam("FILEPATH");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}
	
	@SuppressWarnings("rawtypes")
	public static String getContentNameContentById(String contentId) {
		String filename = "";
		String sql = "select CONTENTNAME from VOD_CONTENTS where LANG_ID = 2 and CONTENTID = " + contentId;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				result.moveToFirst();
				filename = result.getParam("CONTENTNAME");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	@SuppressWarnings("rawtypes")
	public String getHostPortContentById(int contentId) {
		String ipserver = "";
		String sql = "select distinct ipserver from vod_contents where contentid = "
				+ contentId;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				result.moveToFirst();
				ipserver = result.getParam("IPSERVER");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipserver;
	}

	public boolean insertVodRemote(String filename, UUID id, String nameview) {
		System.out.println("insert vod remote percent");
		// String sql =
		// "insert into VOD_REMOTE_PERCENT(FILENAME, STATUS, NAME, UUID) values('"
		// + filename + "', 0, '" + nameview + "', '"
		// + id + "')";
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(filename), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(id.toString()), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(nameview), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(INSERT_VOD_REMOTE_PERCENT, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public List<VodPercent> getVodTransferPercent() {
		List<VodPercent> list = new ArrayList<VodPercent>();
		// String sqldelete = "delete from VOD_REMOTE_PERCENT where STATUS = 1";
		String sql = "select FILENAME, NAME, UUID from VOD_REMOTE_PERCENT where STATUS = 0";
		Vector vector = new Vector();
		try {
			broker.executeSubPro(DELETE_VOD_PERCENT, null);
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					VodPercent vod = new VodPercent();
					vod.setFilename(result.getParam("FILENAME"));
					vod.setNameview(result.getParam("NAME"));
					vod.setUid(UUID.fromString(result.getParam("UUID")));
					list.add(vod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<VodPercent> getInfoUpload() {
		return infoUpload.getlistVod();
	}

	public boolean deleteVodRemote(String filename) {
		return true;
	}

	@SuppressWarnings("static-access")
	public boolean saveInfoUpload(String filename, UUID uid) {
		VodPercent vod = new VodPercent();
		vod.setFilename(filename);
		vod.setUid(uid);
		vod.setSeq(0);
		infoUpload.addNewUpload(filename, vod);
		return true;
	}

	@SuppressWarnings("rawtypes")
	public List<String> getAllSubForVod(int vodId) {
		List<String> list = new ArrayList<String>();
		String sql = "select url from vod_subtitle where contentid = " + vodId;
		String sqldelete = "delete vod_subtitle where contentid = " + vodId;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			broker.executeBlockSQLStmt(sqldelete);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					list.add(result.getParam("URL"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public String getSubVodById(int vodId) {
		String filename = "";
		String sql = "select url from vod_subtitle where id = " + vodId;
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

	public boolean deleteSubVodByUrl(String filename) {
		boolean flag = true;
		// String sql = "delete vod_subtitle where url = '" + filename + "'";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(filename), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(DELETE_SUBTITLE_BY_URL, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public String getMaxIdVodContent() {
		String id = "";
		String sql = "select max(contentid) from vod_contents";
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

	public boolean updateUrlForVod(int id, String url) {
		boolean flag = true;
		// String sql = "update vod_contents set filepath = '" + url +
		// "' where contentid = " + id;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(url), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(UPDATE_URL_FOR_VOD, params);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public boolean updateFilePath() {
		List<Integer> listId = new ArrayList<Integer>();
		String sql = "select contentid from vod_contents order by contentid";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				listId.add(Integer.parseInt(result.getParam("contentid")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < listId.size(); i++) {
			String filename = getFileNameContentById(listId.get(i));

			String filetype = filename.substring(filename.lastIndexOf("."),
					filename.length());
			String nameupdate = listId.get(i) + filetype;
			System.out.println(listId.get(i) + " -------- " + nameupdate);
			updateUrlForVod(listId.get(i), nameupdate);
		}

		System.out.println(listId);
		return true;
	}

	@SuppressWarnings("rawtypes")
	public String getSrcVodToPlayVlc() {
		String text = "http://";
		String sql = "select host, path from vod_storage where id = 5";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			String host = result.getParam("HOST");
			String path = result.getParam("PATH");
			text += host + path + "/";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	// public boolean updateInvisibleVod(int vodId) {
	// boolean flag = true;
	// String sql = "update vod_contents set invisible = 0 where contentid = " +
	// vodId;
	// try {
	// broker.executeBlockSQLStmt(sql);
	// } catch (Exception e) {
	// e.printStackTrace();
	// flag = false;
	// }
	// return flag;
	// }

	@SuppressWarnings("rawtypes")
	public int countSubtitleVod(int vodId) {
		int rs = 0;
		String sql = "select count(*) amount from VOD_SUBTITLE where CONTENTID = "
				+ vodId;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			rs = Integer.parseInt(result.getParam("AMOUNT"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean deleteVodContentById(int vodId) {
		System.out.println("delete vod content id  = = = " + vodId);
		Vector<SubProParam> param = new Vector<SubProParam>();

		SubProParam in = new SubProParam(new BigDecimal(vodId), SubProParam.IN);
		param.add(in);
		// String sql = "delete from vod_contents where contentid = " + vodId;
		try {
			broker.executeSubPro(DELETE_VOD_BY_ID, param);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateStatusInvisibleVod(int vodId, String filename) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(vodId), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(filename), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(UPDATE_STATUS_VOD, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	@SuppressWarnings("unchecked")
	public void addVodDbi(int subjectid, int storageid, String url_multicast,
			String title, String director, String actor, String plot,
			String poster, String currency, String iunit, String duration,
			String ipserver, int seqout) {
		System.out.println("add vod dbi");
		Vector<SubProParam> params = new Vector<SubProParam>(12);
		SubProParam in = null;
		in = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
		params.add(in); // 0
		in = new SubProParam(new BigDecimal(storageid), SubProParam.IN);
		params.add(in); // 1
		in = new SubProParam(new java.lang.String(url_multicast),
				SubProParam.IN);
		params.add(in); // 2
		in = new SubProParam(new java.lang.String(title), SubProParam.IN);
		params.add(in); // 3
		in = new SubProParam(new java.lang.String(director), SubProParam.IN);
		params.add(in); // 4
		in = new SubProParam(new java.lang.String(actor), SubProParam.IN);
		params.add(in); // 5
		in = new SubProParam(new java.lang.String(plot), SubProParam.IN);
		params.add(in); // 6
		in = new SubProParam(new java.lang.String(poster), SubProParam.IN);
		params.add(in); // 7
		in = new SubProParam(new java.lang.String(currency), SubProParam.IN);
		params.add(in); // 8
		in = new SubProParam(new java.lang.String(iunit), SubProParam.IN);
		params.add(in); // 9
		in = new SubProParam(new java.lang.String(duration), SubProParam.IN);
		params.add(in); // 10
		in = new SubProParam(new java.lang.String(ipserver), SubProParam.IN);
		params.add(in); // 11
		// in = new SubProParam(new java.lang.String(type), SubProParam.IN);
		// params.add(in); // 12

		SubProParam out = new SubProParam(new BigDecimal(seqout),
				SubProParam.OUT);
		params.add(out); // 12

		try {
			params = broker.executeSubPro(ADD_VOD, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int deleteSubtitleDbi(int contentid) {
		int seqout = -1;
		Vector<SubProParam> params = new Vector<SubProParam>(2);
		SubProParam in = new SubProParam(new BigDecimal(contentid),
				SubProParam.IN);
		params.add(in); // 0
		SubProParam out = new SubProParam(new BigDecimal(seqout),
				SubProParam.OUT);
		params.add(out); // 1
		try {
			broker.executeSubPro(DELETE_SUBTITLE, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(seqout);
		return seqout;
	}

	public int addSubtitleDbi(int contentid, String url, int langid) {
		int seqout = -1;
		Vector<SubProParam> params = new Vector<SubProParam>(5);
		SubProParam in = null;
		in = new SubProParam(new BigDecimal(contentid), SubProParam.IN);
		params.add(in); // 0
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 1
		in = new SubProParam(new java.lang.String(url), SubProParam.IN);
		params.add(in); // 2
		in = new SubProParam(new BigDecimal(langid), SubProParam.IN);
		params.add(in); // 3
		SubProParam out = new SubProParam(new BigDecimal(seqout),
				SubProParam.OUT);
		params.add(out); // 4
		try {
			broker.executeSubPro(ADD_SUBTITLE, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqout;
	}

	public int deleteVodDbi(int sunjectId) {
		int seqout = -1;
		Vector<SubProParam> params = new Vector<SubProParam>(2);
		SubProParam in = new SubProParam(new BigDecimal(sunjectId),
				SubProParam.IN);
		params.add(in); // 0
		SubProParam out = new SubProParam(new BigDecimal(seqout),
				SubProParam.OUT);
		params.add(out); // 1
		try {
			broker.executeSubPro(DELETE_VOD, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqout;
	}

	@SuppressWarnings("rawtypes")
	public int checkFilmName(String name) {
		int count = 0;
		String sql = "select count(*) as amount from vod_contents where contentname = '"
				+ name + "'";
		System.out.println(sql);
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			count = Integer.parseInt(result.getParam("AMOUNT"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("rawtypes")
	public String getIpserver(int id) {
		String ip = "";
		String sql = "select ipserver from vod_contents where lang_id = 2 and contentid = "
				+ id;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			ip = result.getParam("IPSERVER");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}

	// get list subject service movie main
	@SuppressWarnings("rawtypes")
	public List<SubjectMovieModel> getSubjectServiceVod(int lang) {
		List<SubjectMovieModel> list = new ArrayList<SubjectMovieModel>();
		String sql = "select SUBJECTID, SUBJECTNAME, PARENT_ID, URL_IMAGE from vod_servicesubject where SUBJECTID in (1,2) and lang_id = " + lang;
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				SubjectMovieModel sub = new SubjectMovieModel();
				sub.setId(result.getParam("SUBJECTID"));
				sub.setName(result.getParam("SUBJECTNAME"));
				sub.setImage(result.getParam("URL_IMAGE"));
				sub.setParent(result.getParam("PARENT_ID"));
				
				list.add(sub);
			}
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List<SubjectMovieModel> getSubjectByIdParent(String id, int lang){
		List<SubjectMovieModel> list = new ArrayList<SubjectMovieModel>();
		String sql = "";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if(vector.size() >2){
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()){
				result.next();
				SubjectMovieModel sub = new SubjectMovieModel();
				sub.setId(result.getParam("SUBJECTID"));
				sub.setName(result.getParam("SUBJECTNAME"));
				sub.setImage(result.getParam("URL_IMAGE"));
				sub.setParent(result.getParam("PARENT_ID"));
				
				list.add(sub);
			}
		}
		return list;
	}
	
	/////////////////////////////////////////////////////them muc phim tinh phi cho sokha /////////
	
	@SuppressWarnings("rawtypes")
	public List<Subject> getSubjectServiceCharge(int lang){
		List<Subject> list = new ArrayList<Subject>();
		String sql = "select subjectid,subjectname,url_image,parent_id from vod_servicesubject where parent_id in (23,34) and SUBJECTID not in (1,34) and lang_id="+lang+" order by SUBJECTID";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if(vector.size()>2){
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()){
				result.next();
				Subject sub = new Subject();
				sub.setId(Integer.parseInt(result.getParam("subjectid")));
				sub.setName(result.getParam("subjectname"));
				sub.setUrlImage(result.getParam("url_image"));
				list.add(sub);
			}
		}
		return list;
	}

	
	
	public static void main(String[] args) {
		VodContentServiceDBI c = new VodContentServiceDBI();
		System.out.println(c.getIpserver(1230));
	}
}