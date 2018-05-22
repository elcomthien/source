package ehotel.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;
import ehotel.sql.eMODSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

/**
 * Hoavk
 */

public class eMODDao extends eBaseDao {
	private static Logger log = Logger.getLogger(eMODDao.class);

	// ------------------------SUBJECT--------------------------------
	public int addCntSubject(Subject subject, int parentId) {
		return addSubject(subject, parentId, this.CONTENT);
	}

	public int addSvcSubject(Subject subject, int parentId) {
		return addSubject(subject, parentId, this.SERVICE);
	}

	public int editCntSubject(Subject subject, int langId) {
		return editSubject(subject, langId, this.CONTENT);
	}

	public int editSvcSubject(Subject subject, int langId) {
		return editSubject(subject, langId, this.SERVICE);
	}

	public Subject getCntSubjectInfo(int subjId, int langid) {
		return getSubjectInfo(subjId, langid, this.CONTENT);
	}

	public Subject getSvcSubjectInfo(int subjId, int langid) {
		return getSubjectInfo(subjId, langid, this.SERVICE);
	}

	public Vector<Subject> getCntSubjects(int langid) {
		return getSubjects(langid, this.CONTENT);
	}

	public Vector<Subject> getSvcSubjects(int langid) {
		return getSubjects(langid, this.SERVICE);
	}

	public int removeCntSubject(int subjId) {
		return removeSubject(subjId, this.CONTENT);
	}

	public int removeSvcSubject(int subjId) {
		return removeSubject(subjId, this.SERVICE);
	}

	public boolean changeCntSubjectOfVod(int modId, String str_subj_id) {
		// TODO Auto-generated method stub
		return changeSubjectOfMod(modId, str_subj_id, this.CONTENT);
	}

	public boolean changeSvcSubjectOfMod(int modId, String str_subj_id) {
		// TODO Auto-generated method stub
		return changeSubjectOfMod(modId, str_subj_id, this.SERVICE);
	}

	// ---------------------------MOD----------------------------------

	public Vector<Mod> getCntMods(int subjId, int langid, int frRow, int toRow) {
		// TODO Auto-generated method stub
		return getMods(subjId, langid, this.CONTENT, frRow, toRow);
	}

	public Vector<Mod> getSvcMods(int subjId, int langid, int frRow, int toRow) {
		// TODO Auto-generated method stub
		return getMods(subjId, langid, this.SERVICE, frRow, toRow);
	}

	public boolean removeModsOfSvc(int subjId, String strModId) {
		return removeSvcMod(subjId, strModId, this.SERVICE);
	}

	public boolean updateCntMod(Mod mod, int langid) {
		return updateMod(mod, langid, "CONTENT");
	}

	public boolean updateSvcMod(Mod mod, int langid) {
		return updateMod(mod, langid, "SERVICE");
	}

	public Vector<Mod> searchCntMod(int subjId, String modName, int langid,
			int frRow, int toRow) {

		return searchMod(subjId, modName, langid, this.CONTENT, frRow, toRow);
	}

	public Vector<Mod> searchSvcMod(int subjId, String modName, int langid,
			int frRow, int toRow) {
		// TODO Auto-generated method stub
		return searchMod(subjId, modName, langid, this.SERVICE, frRow, toRow);
	}

	public boolean addSvcMod(int subjId, String modId) {
		boolean isremove = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(modId, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlAddSvcMod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int aret = Utils.parseInt(paramOUT.getString().trim());
				if (aret == 1)
					isremove = true;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return isremove;
	}

	/**
	 * 
	 * @param subjName
	 * @param parentId
	 * @param type
	 * @return seq subject
	 */
	private int addSubject(Subject subject, int parentId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = -1;
		if (subject == null) {
			log.info("Input param is null");
			return ret;
		}
		SubProParam subPro = new SubProParam(subject.getName(), SubProParam.IN);
		params.add(subPro);

		String urlImage = "";
		if (subject.getUrlImage() != null)
			urlImage = subject.getUrlImage();
		subPro = new SubProParam(urlImage, SubProParam.IN);
		params.add(subPro);

		String urlBg = "";
		if (subject.getUrlBg() != null)
			urlBg = subject.getUrlBg();
		subPro = new SubProParam(urlBg, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(parentId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlAddSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				ret = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[--> addSubject  with params: "
					+ subject.toString() + " : returnValue(isAdd =" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	private int removeSubject(int subjId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = -1;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlRemoveSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				ret = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[--> removeSubject  with params: [subjId="
					+ subjId + "] : returnValue(isRemove =" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	private int editSubject(Subject subject, int langId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = -1;
		if (subject == null) {
			log.info("Input param is null");
			return ret;
		}
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				subject.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(subject.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String urlimage = "";
		if (subject.getUrlImage() != null)
			urlimage = subject.getUrlImage();
		subPro = new SubProParam(urlimage, SubProParam.IN);
		params.add(subPro);// 3

		String urlBg = "";
		if (subject.getUrlBg() != null)
			urlBg = subject.getUrlBg();
		subPro = new SubProParam(urlBg, SubProParam.IN);
		params.add(subPro);// 4

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 6

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 7

		try {
			params = executeSubPro(eMODSql.sqlEditSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				ret = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[--> editSubject  with params:"
					+ subject.toString() + ",langId=" + langId
					+ "] : returnValue(isEdit =" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public Subject getSubjectInfo(int subjId, int langid, String type) {
		Subject aSubject = null;
		Vector<Subject> vVod = new Vector<Subject>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlGetSubjectInfo, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();

			}
			vVod = DataUtils.LoadSubjectMod(outParam);
			String outScreen = "[getSubjectInfo with params: subjId=" + subjId
					+ "] )";
			if (vVod != null && vVod.size() > 0) {
				aSubject = vVod.get(0);
			}
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return aSubject;
	}

	@SuppressWarnings("unchecked")
	public Vector<Subject> getSubjects(int langid, String type) {
		Vector<Subject> vSubject = new Vector<Subject>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlGetSubjects, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vSubject = DataUtils.LoadSubjectMod(outParam);
		String outScreen = "[getSubjects with params: langid=" + langid
				+ ",type=" + type + "] : returnValue(size=" + vSubject.size()
				+ ")";
		log.info(outScreen);
		return vSubject;
	}

	@SuppressWarnings("unchecked")
	public Vector<Subject> getSubjectsOutMod(int modId, int langid, String type) {
		Vector<Subject> vSubject = new Vector<Subject>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(modId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlGetOutSubjects, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vSubject = DataUtils.LoadSubjectMod(outParam);
		String outScreen = "[getSubjectsOutMod with params: langid=" + langid
				+ ",modId=" + modId + "] : returnValue(size=" + vSubject.size()
				+ ")";
		log.info(outScreen);
		return vSubject;
	}

	@SuppressWarnings("unchecked")
	public Vector<Subject> getSubjectsInMod(int modId, int langid, String type) {
		Vector<Subject> vSubject = new Vector<Subject>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(modId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlGetINSubjects, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vSubject = DataUtils.LoadSubjectMod(outParam);
		String outScreen = "[getSubjectsInMod with params: langid=" + langid
				+ ",modId=" + modId + "] : returnValue(size=" + vSubject.size()
				+ ")";
		log.info(outScreen);
		return vSubject;
	}

	private boolean updateMod(Mod mod, int langid, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean ret = false;
		if (mod == null)
			return false;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				mod.getId()), SubProParam.IN);
		params.add(subPro);// 0

		subPro = new SubProParam(mod.getTitle(), SubProParam.IN);
		params.add(subPro);// 1

		String singer = "";
		if (mod.getSinger() != null) {
			singer = mod.getSinger();
		}
		subPro = new SubProParam(singer, SubProParam.IN);
		params.add(subPro);// 2

		String composer = "";
		if (mod.getComposer() != null) {
			composer = mod.getComposer();
		}
		subPro = new SubProParam(composer, SubProParam.IN);
		params.add(subPro);// 3

		String lyric = "";
		if (mod.getLyric() != null) {
			lyric = mod.getLyric();
		}
		subPro = new SubProParam(lyric, SubProParam.IN);
		params.add(subPro);// 4

		String album = "";
		if (mod.getAlbum() != null) {
			album = mod.getAlbum();
		}
		subPro = new SubProParam(album, SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 6

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 7

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 8

		try {
			params = executeSubPro(eMODSql.sqlUpdateMod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				int aret = Utils.parseInt(paramOUT.getString().trim());
				System.out.println("updateMod :aret=" + aret);
				if (aret == 1)
					ret = true;
			}
			String outScreen = "[--> updateMod  with params: ["
					+ mod.toString() + "] : returnValue(isUpdate =" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	private boolean removeSvcMod(int subjId, String str_modid, String type) {
		boolean isremove = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(str_modid, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlRemoveSvcMod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int aret = Utils.parseInt(paramOUT.getString().trim());
				if (aret == 1)
					isremove = true;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isremove;
	}

	public boolean removeEASMod(int modId) {
		boolean isremove = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(modId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			String outScreen = "[removeEASMod(eMODSql.sqlRemoveMod="
					+ eMODSql.sqlRemoveMod + " with params: modId=" + modId
					+ "] )";
			log.info(outScreen);
			params = executeSubPro(eMODSql.sqlRemoveMod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				int aret = Utils.parseInt(paramOUT.getString());
				if (aret == 1)
					isremove = true;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isremove;
	}

	@SuppressWarnings("unchecked")
	public Mod getModInfo(int modId, int langid, String type) {
		Mod aMod = null;
		Vector<Mod> vVod = new Vector<Mod>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(modId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlGetModInfo, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
			vVod = DataUtils.LoadMod(outParam);
			String outScreen = "[getModInfo with params: modId=" + modId
					+ "] )";
			if (vVod != null && vVod.size() > 0) {
				aMod = vVod.get(0);
				outScreen += " : returnValue(" + aMod.toString() + ")";
			}
			log.info(outScreen);

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return aMod;
	}

	public int addMod(int subjId, Mod mod, int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int seq = -1;
		if (mod == null)
			return seq;

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(mod.getTitle(), SubProParam.IN);
		params.add(subPro);// 2

		String duration = "";
		if (mod.getDuration() != null) {
			duration = mod.getDuration();
		}
		subPro = new SubProParam(duration, SubProParam.IN);
		params.add(subPro);// 3

		String singer = "";
		if (mod.getSinger() != null) {
			singer = mod.getSinger();
		}
		subPro = new SubProParam(singer, SubProParam.IN);
		params.add(subPro);// 4

		String composer = "";
		if (mod.getComposer() != null) {
			composer = mod.getComposer();
		}
		subPro = new SubProParam(composer, SubProParam.IN);
		params.add(subPro);// 5

		String lyric = "";
		if (mod.getLyric() != null) {
			lyric = mod.getLyric();
		}
		subPro = new SubProParam(lyric, SubProParam.IN);
		params.add(subPro);// 6

		String url = "";
		if (mod.getUrl() != null) {
			url = mod.getUrl();
		}
		subPro = new SubProParam(url, SubProParam.IN);
		params.add(subPro);// 7

		subPro = new SubProParam(new java.math.BigDecimal(mod.getLength()),
				SubProParam.IN);
		params.add(subPro);// 8

		String fileName = mod.getFileName();
		if (fileName == null)
			fileName = mod.getUrl();
		subPro = new SubProParam(mod.getFileName(), SubProParam.IN);
		params.add(subPro);// 9

		subPro = new SubProParam(new java.math.BigDecimal(mod.getVodNum()),
				SubProParam.IN);
		params.add(subPro);// 10

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 11

		try {
			params = executeSubPro(eMODSql.sqlAddMod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(10);
				seq = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[--> addMod  with params: [" + mod.toString()
					+ "] : returnValue(isAdd =" + seq + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return seq;
	}

	@SuppressWarnings("unchecked")
	public Vector<Mod> getMods(int subjId, int langid, String type, int from,
			int tto) {
		Vector<Mod> vMods = new Vector<Mod>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlGetMods, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();

			}

			vMods = DataUtils.LoadMod(outParam);

			String outScreen = "[getMods with params: subjId=" + subjId
					+ ",langid=" + langid + ",type=" + type
					+ "] ): returnValue(" + vMods.size() + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return vMods;
	}

	public boolean changeSubjectOfMod(int modId, String str_subj_id, String type) {
		boolean ischange = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(modId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(str_subj_id, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlChangeSubjectOfMod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				int aret = Utils.parseInt(paramOUT.getString().trim());
				if (aret == 1)
					ischange = true;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return ischange;
	}

	@SuppressWarnings("unchecked")
	public Vector<Mod> searchMod(int subjId, String modName, int langid,
			String type, int from, int tto) {
		Vector<Mod> vMods = new Vector<Mod>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(modName, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlSearchMod, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(6);
				outParam = subOut.getVector();
			}
			vMods = DataUtils.LoadMod(outParam);
			String outScreen = "[searchMod with params: subjId=" + subjId
					+ ",langid=" + langid + "] : returnValue(" + vMods.size()
					+ ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return vMods;
	}

	public int countModOfSubject(int subjId, String type) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlCountMod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countModOfSubject with params: subjId="
					+ subjId + ",type=" + type + "] : returnValue(count="
					+ count + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return count;
	}

	public boolean setUrl(int modId, String url) {
		boolean isset = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(modId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(url, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMODSql.sqlSetUrl, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int out = Utils.parseInt(paramOUT.getString().trim());
				if (out == 1)
					isset = true;
			}
			String outScreen = "[setUrl with params: modId=" + modId + ",url="
					+ url + "] : returnValue(isset=" + isset + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return isset;
	}

	public boolean setClip(String fileName, int subjId, int vodiId) {
		boolean isset = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(vodiId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(fileName, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = executeSubPro(eMODSql.sqlSetClip, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				int out = Utils.parseInt(paramOUT.getString().trim());
				if (out == 1)
					isset = true;
			}
			String outScreen = "[setClip with params: vodiId=" + vodiId
					+ ",fileName=" + fileName + "] : returnValue(isset="
					+ isset + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return isset;
	}
}
