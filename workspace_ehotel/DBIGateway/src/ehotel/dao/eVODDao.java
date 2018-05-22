package ehotel.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.vod.EVSStorage;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Trailer;
import ehotel.domain.vod.Vod;
import ehotel.sql.eVODSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

/**
 * Hoavk
 */

public class eVODDao extends eBaseDao {
	private static Logger log = Logger.getLogger(eVODDao.class);

	@SuppressWarnings("unchecked")
	public Vector<EVSStorage> getEVSStorages(int vodId) {
		Vector<EVSStorage> vEVSStorages = new Vector<EVSStorage>();

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlGetEVSStorages, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vEVSStorages = DataUtils.LoadEVSStorage(outParam);
		String outScreen = "[getSubjects with params: "
				+ "] : returnValue(size=" + vEVSStorages.size() + ")";
		log.info(outScreen);
		return vEVSStorages;
	}

	/**
	 * add content vod
	 * 
	 * @param subjectId
	 * @param vod
	 * @return : seq VodID
	 */

	public int addVod(int subjectId, int storageId, String url_muticast, Vod vod) {
		int seqadd = -1;
		if (vod == null)
			return seqadd;

		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(subjectId), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(storageId),
				SubProParam.IN);
		params.add(subPro);// 2

		if (url_muticast == null)
			url_muticast = "";
		subPro = new SubProParam(url_muticast, SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(vod.getTitle(), SubProParam.IN);
		params.add(subPro);// 4

		String director = "";
		if (vod.getDirector() != null)
			director = vod.getDirector();
		subPro = new SubProParam(director, SubProParam.IN);
		params.add(subPro);// 5

		String actor = "";
		if (vod.getActors() != null)
			actor = vod.getActors();
		subPro = new SubProParam(actor, SubProParam.IN);
		params.add(subPro);// 6

		String plot = "";
		if (vod.getPlot() != null)
			plot = vod.getPlot();
		subPro = new SubProParam(plot, SubProParam.IN);
		params.add(subPro);// 7

		String poster = "";
		if (vod.getPoster() != null)
			poster = vod.getPoster();
		subPro = new SubProParam(poster, SubProParam.IN);
		params.add(subPro);// 8

		String currency = "0";
		if (vod.getCurrency() != null)
			currency = vod.getCurrency();
		subPro = new SubProParam(currency, SubProParam.IN);
		params.add(subPro);// 9

		String unit = "USD";
		if (vod.getIUnit() != null)
			unit = vod.getIUnit();
		subPro = new SubProParam(unit, SubProParam.IN);
		params.add(subPro);// 10

		String duration = "0";
		if (vod.getDuration() != null)
			duration = vod.getDuration();
		subPro = new SubProParam(duration, SubProParam.IN);
		params.add(subPro);// 11

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 12

		try {
			params = executeSubPro(eVODSql.sqlAddVod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(11);
				seqadd = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[--> addVod  with params: " + "[subjectId="
					+ subjectId + ",=" + vod.toString()
					+ "] : returnValue(seqadd =" + seqadd + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
		return seqadd;
	}

	public boolean setUrl(int vodId, String url) {
		boolean isSet = false;
		if (url == null) {
			log.info("Url is null.SetUrl is not success.");
			return false;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(url, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eVODSql.sqlSetUrl, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int seqadd = Utils.parseInt(paramOUT.getString().trim());
				if (seqadd == 1)
					isSet = true;
			}
			String outScreen = "[--> setUrl with params: [vodId=" + vodId
					+ ",url=" + url + "] : returnValue(isUrl =" + isSet + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isSet;
	}

	public String getUrl(int seqId, String vodInfo) {
		String url = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(seqId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(vodInfo, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eVODSql.sqlGetUrl, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				url = paramOUT.getString().trim();
				if (url == "NOTEXIST")
					log.info(vodInfo + "[Id=" + seqId + "] is not exist.");
			}
			String outScreen = "[--> getUrl  with params: [vodId=" + seqId
					+ ",vodInfo=" + vodInfo + "] : returnValue(url =" + url
					+ "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
		return url;
	}

	public int getVodId(int seqId, String vodInfo) {
		int VodId = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(seqId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(vodInfo, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eVODSql.sqlGetVodId, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				VodId = Utils.parseInt(paramOUT.getString().trim());
				if (VodId == -1)
					log.info(vodInfo + "[Id=" + seqId + "] is not exist.");
			}
			String outScreen = "[--> getVodId  with params: [vodId=" + seqId
					+ ",vodInfo=" + vodInfo + "] : returnValue(VodId =" + VodId
					+ "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
		return VodId;
	}

	@SuppressWarnings("unchecked")
	public int[] getIDSubtitle(int vodId) {
		int[] IdSub = new int[0];
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlGetIDSubtitle, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		IdSub = DataUtils.LoadIDSubtitle(outParam);
		String outScreen = "[getIDSubtitle with params: "
				+ "] : returnValue(size=" + IdSub.length + ")";
		log.info(outScreen);
		return IdSub;

	}

	public int getIDTrailer(int vodId) {
		int trailerId = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eVODSql.sqlGetIDTrailer, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				trailerId = Utils.parseInt(paramOUT.getString().trim());
				if (trailerId == -1)
					log.info("VOD[vodId=" + vodId + "] is not have trailer.");
			}
			String outScreen = "[--> getIDTrailer  with params: [vodId="
					+ vodId + "] : returnValue(trailerId =" + trailerId + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
		return trailerId;
	}

	public int addTrailer(int vodId, Trailer trailer) {
		int seqadd = -1;
		if (trailer == null)
			return -1;

		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(trailer.getTrailerUrl(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(
				new java.math.BigDecimal(trailer.getDuration()), SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eVODSql.sqlAddTrailer, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				seqadd = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[--> addTrailer  with params: [vodId=" + vodId
					+ "," + trailer.toString() + "] : returnValue(seqadd ="
					+ seqadd + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return seqadd;
	}

	/**
	 * 
	 * @param vodId
	 * @param vSubTitles
	 * @return -1: vodId ko ton tai. 0=sucess
	 */
	public Integer[] addSubtitle(int vodId, Vector<SubTitle> vSubTitles) {
		// Vector<Integer> seqTitle = new Vector<Integer>();
		Integer[] seqTitle = null;
		int seq = -1;
		int i = 0;
		if (vSubTitles != null && vSubTitles.size() > 0) {
			seqTitle = new Integer[vSubTitles.size()];
			for (SubTitle aTitle : vSubTitles) {
				seq = addSubtitle(vodId, aTitle);
				seqTitle[i] = (new Integer(seq));
				i++;
			}
		} else {
			log.info("Tham so truyen vao(vSubTitles) la null");
		}
		return seqTitle;
	}

	/**
	 * 
	 * @param vodId
	 * @param subTitle
	 * @return -1: vodId ko ton tai.
	 */
	private int addSubtitle(int vodId, SubTitle subTitle) {
		// TODO Auto-generated method stub
		int seqadd = -1;
		if (subTitle == null) {
			log.info("Subtitle is null");
			return -1;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);// 1

		String subtile = "";
		if (subTitle.getName() != null)
			subtile = subTitle.getName();
		subPro = new SubProParam(subtile, SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(subTitle.getUrl(), SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(
				new java.math.BigDecimal(subTitle.getLangId()), SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 5

		try {
			params = executeSubPro(eVODSql.sqlAddSubtitle, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				seqadd = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[--> addSubtitle  with params: [vodId=" + vodId
					+ "," + subTitle.toString() + "] : returnValue(seqadd ="
					+ seqadd + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return seqadd;
	}

	public boolean removeVod(int vodId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean isRemove = false;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);// 1
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eVODSql.sqlRemoveVod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isRemove = true;
			}
			String outScreen = "[--> removeVod  with params: [vodId=" + vodId
					+ "] : returnValue(isRemove =" + isRemove + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean removeTrailer(int trailerId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean isRemove = false;
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(trailerId), SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eVODSql.sqlRemoveTrailer, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isRemove = true;
			}
			String outScreen = "[--> removeTrailer  with params: [trailerId="
					+ trailerId + "] : returnValue(isRemove =" + isRemove + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean removeSubtitle(int subtileId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean isRemove = false;
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(subtileId), SubProParam.IN);
		params.add(subPro);// 1
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eVODSql.sqlRemoveSubtitle, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isRemove = true;
			}
			String outScreen = "[--> removeSubtitle  with params: [subtileId="
					+ subtileId + "] : returnValue(isRemove =" + isRemove + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	// ============PHAN ADMIN=====================

	/**
	 * add subj
	 * 
	 * @param subjName
	 * @return -1=false; -2=duplicate ;0=success
	 */
	public int addCntSubject(String subjName, String urlImage, int parentId) {
		return addSubject(subjName, urlImage, parentId, this.CONTENT);
	}

	public int addSvcSubject(String subjName, String urlImage, int parentId) {
		return addSubject(subjName, urlImage, parentId, this.SERVICE);
	}

	/**
	 * 
	 * @param subjId
	 * @param subjName
	 * @param langId
	 * @return -1 trung name
	 */
	public int editCntSubject(int subjId, String subjName, String urlImage,
			int langId) {
		return editSubject(subjId, subjName, urlImage, langId, this.CONTENT);
	}

	public int editSvcSubject(int subjId, String subjName, String urlImage,
			int langId) {
		return editSubject(subjId, subjName, urlImage, langId, this.SERVICE);
	}

	/**
	 * 
	 * @param subjId
	 * @return -1=false; ;0=success
	 */
	public boolean removeCntSubject(int subjId) {
		return removeSubject(subjId, this.CONTENT);
	}

	public boolean removeSvcSubject(int subjId) {
		return removeSubject(subjId, this.SERVICE);
	}

	@SuppressWarnings("unchecked")
	public Subject getSubjectInfo(int subjId, int langid, String type) {
		Subject vSubject = null;
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
			params = executeSubPro(eVODSql.sqlGetSubjectInfo, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
			vVod = DataUtils.LoadSubject(outParam);
			String outScreen = "[getSubjectInfo with params: subjId=" + subjId
					+ "] )";
			if (vVod != null && vVod.size() > 0) {
				vSubject = vVod.get(0);
			}
			log.info(outScreen);

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return vSubject;
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
			params = executeSubPro(eVODSql.sqlGetSubjects, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vSubject = DataUtils.LoadSubject(outParam);
		String outScreen = "[getSubjects with params: langid=" + langid
				+ "type=" + type + "] : returnValue(size=" + vSubject.size()
				+ ")";
		log.info(outScreen);
		return vSubject;
	}

	public boolean addSvcVod(int subjId, String vodId) {
		boolean isAdd = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(vodId, SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlAddSvcVod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isAdd = true;

			}
			String outScreen = "[--> addSvcVod  with params: [subjId=" + subjId
					+ ",vodId=" + vodId + "] : returnValue(isAdd =" + isAdd
					+ "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isAdd;
	}

	/**
	 * 
	 * @param subjId
	 * @param str_vodId
	 *            : cac id ngan cach nhau dau ";"
	 * @return
	 */
	public boolean removeSvcVods(int subjId, String str_vodId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean isremove = false;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(str_vodId, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlRemoveSvcVod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isremove = true;

			}
			String outScreen = "[--> removeSvcVods  with params: [subjId="
					+ subjId + ",str_vodId=" + str_vodId
					+ "] : returnValue(isremove =" + isremove + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isremove;
	}

	public boolean changeSvcSubjectOfVod(int vodId, String str_subj_id) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean ischange = false;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(str_subj_id, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlChangeSvcSubjectOfVod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1) {
					ischange = true;
				}

			}
			String outScreen = "[--> changeSvcSubjectOfVod  with params: "
					+ "[vodId=" + vodId + ",str_subj_id=" + str_subj_id
					+ "] : returnValue(ischange =" + ischange + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ischange = false;
		}
		return ischange;
	}

	public boolean changeCntSubjectOfVod(int vodId, int newSubjId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean ischange = false;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(newSubjId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlChangeSubjectOfVod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1) {
					ischange = true;
				}
			}
			String outScreen = "[--> changeSubjectOfVod  with params: "
					+ "[vodId=" + vodId + ",newSubjId=" + newSubjId
					+ "] : returnValue(ischange =" + ischange + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ischange = false;
		}
		return ischange;
	}

	/**
	 * get information detail
	 * 
	 * @param vodId
	 * @param langid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vod getVodInfo(int vodId, int langid) {
		Vod aVod = null;
		Vector<Vod> vVod = new Vector<Vod>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlGetVodInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadVod(outParam);
		String outScreen = "[getVodInfo with params: vodId=" + vodId + "] )";
		if (vVod != null && vVod.size() > 0) {
			aVod = vVod.get(0);
			outScreen += ": returnValue(" + aVod.toString() + ")";
		}
		log.info(outScreen);
		return aVod;
	}

	@SuppressWarnings("unchecked")
	public Vod getVodInfo_svc(int subjId, int vodId, int langid) {
		Vod aVod = null;
		Vector<Vod> vVod = new Vector<Vod>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlGetVodInfo_svc, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadVod(outParam);
		String outScreen = "[getVodInfo_svc with params: vodId=" + vodId
				+ "] )";
		if (vVod != null && vVod.size() > 0) {
			aVod = vVod.get(0);
			outScreen += ": returnValue(" + aVod.toString() + ")";
		}
		log.info(outScreen);
		return aVod;
	}

	/**
	 * update info vod
	 * 
	 * @param vod
	 * @param langid
	 * @return
	 */
	public boolean updateCntVod(Vod vod, int langid) {
		return updateVod(vod, langid, this.CONTENT);
	}

	public boolean updateSvcVod(Vod vod, int langid) {
		return updateVod(vod, langid, this.SERVICE);
	}

	public Vector<Vod> getCntVods(int subjId, int langid, int from, int tto) {
		return getVodsOfSubject(subjId, langid, this.CONTENT, from, tto);
	}

	public Vector<Vod> getSvcVods(int subjId, int langid, int from, int tto) {
		return getVodsOfSubject(subjId, langid, this.SERVICE, from, tto);
	}

	public Vector<Vod> searchCntVod(int subjId, String vodName, int langid,
			int from, int tto) {
		return searchVod(subjId, vodName, langid, this.CONTENT, from, tto);
	}

	public Vector<Vod> searchSvcVod(int subjId, String vodName, int langid,
			int from, int tto) {
		return searchVod(subjId, vodName, langid, this.SERVICE, from, tto);
	}

	@SuppressWarnings("unchecked")
	public Vector<SubTitle> getSubtitles(int vodId) {
		Vector<SubTitle> vVod = new Vector<SubTitle>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlGetSubtitles, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadSubtitle(outParam);
		String outScreen = "[getSubtitles with params: vodId=" + vodId
				+ "] : returnValue(size=" + vVod.size() + ")";
		log.info(outScreen);
		return vVod;
	}

	public boolean updateSubtitleLang(int subtitleId, int langId) {
		boolean isEdit = false;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				subtitleId), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlUpdateSubtitleLang, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				int ret = Utils.parseInt(subOut.getString().trim());
				if (ret == 1) {
					isEdit = true;
				}
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		String outScreen = "[updateSubtitleLang with params: subtitleId="
				+ subtitleId + ",langId=" + langId + "] : returnValue(isEdit="
				+ isEdit + ")";
		log.info(outScreen);
		return isEdit;
	}

	public boolean setVisileStatus(int vodId, int subjId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean isrm = false;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlSetVisileStatus, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isrm = true;
			}
			String outScreen = "[--> setVisileStatus  with params: [subjId="
					+ subjId + ",vodId=" + vodId + "] : returnValue(isRemove ="
					+ isrm + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isrm;
	}

	public boolean setNewReleased(int vodId, int subjId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean isrm = false;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlSetNewReleased, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isrm = true;
			}
			String outScreen = "[--> setNewReleased  with params: [subjId="
					+ subjId + ",vodId=" + vodId + "] : returnValue(isRemove ="
					+ isrm + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isrm;
	}

	// helper
	private int addSubject(String subjName, String urlImage, int parentId,
			String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = -1;
		SubProParam subPro = new SubProParam(subjName, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(urlImage, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(parentId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlAddSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				ret = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[--> addSubject  with params: [subjName="
					+ subjName + "] : returnValue(isAdd =" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	private boolean removeSubject(int subjId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		boolean isrm = false;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlRemoveSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isrm = true;
			}
			String outScreen = "[--> removeSubject  with params: [subjId="
					+ subjId + ",type=" + type + "] : returnValue(isRemove ="
					+ isrm + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isrm;
	}

	private int editSubject(int subjId, String subjName, String urlImage,
			int langId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = -1;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(subjName, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(urlImage, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlEditSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				ret = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[--> editSubject  with params: [subjId="
					+ subjId + ",subjName=" + subjName + ",langId=" + langId
					+ "] : returnValue(isEdit =" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	private Vector<Vod> getVodsOfSubject(int subjId, int langid, String type,
			int from, int tto) {
		Vector<Vod> vVod = new Vector<Vod>();
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
			params = executeSubPro(eVODSql.sqlGetVodsOfSubject, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadVod(outParam);
		String outScreen = "[getVodsOfSubject with params: subjId=" + subjId
				+ "] : returnValue(size=" + vVod.size() + ")";
		log.info(outScreen);
		return vVod;
	}

	@SuppressWarnings("unchecked")
	private Vector<Vod> searchVod(int subjId, String vodName, int langid,
			String type, int from, int tto) {
		Vector<Vod> vVod = new Vector<Vod>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(vodName, SubProParam.IN);
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
			params = executeSubPro(eVODSql.sqlSearchVod, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadVod(outParam);
		String outScreen = "[searchVod with params: subjId=" + subjId
				+ ",vodName=" + vodName + "] : returnValue(size=" + vVod.size()
				+ ")";
		log.info(outScreen);
		return vVod;
	}

	private boolean updateVod(Vod vod, int langid, String type) {
		boolean isUpdate = false;
		if (vod != null) {
			Vector<SubProParam> params = new Vector<SubProParam>();
			SubProParam subPro = new SubProParam(new java.math.BigDecimal(
					vod.getId()), SubProParam.IN);
			params.add(subPro);// 1

			subPro = new SubProParam(vod.getTitle(), SubProParam.IN);
			params.add(subPro);// 2

			String director = "";
			if (vod.getDirector() != null)
				director = vod.getDirector();
			subPro = new SubProParam(director, SubProParam.IN);
			params.add(subPro);// 3

			String actor = "";
			if (vod.getActors() != null)
				actor = vod.getActors();
			subPro = new SubProParam(actor, SubProParam.IN);
			params.add(subPro);// 4

			String plot = "";
			if (vod.getPlot() != null)
				plot = vod.getPlot();
			subPro = new SubProParam(plot, SubProParam.IN);
			params.add(subPro);// 5

			String poster = "";
			if (vod.getPoster() != null)
				poster = vod.getPoster();
			subPro = new SubProParam(poster, SubProParam.IN);
			params.add(subPro);// 6

			String currency = "0";
			if (vod.getCurrency() != null)
				currency = vod.getCurrency();
			subPro = new SubProParam(currency, SubProParam.IN);
			params.add(subPro);

			String unit = vod.getIUnit();
			if (unit == null)
				unit = "USD";
			subPro = new SubProParam(unit, SubProParam.IN);
			params.add(subPro);

			subPro = new SubProParam(type, SubProParam.IN);
			params.add(subPro);

			SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
			params.add(subOut);

			try {
				params = executeSubPro(eVODSql.sqlUpdateVod, params);
				if (params != null && params.size() > 0) {
					SubProParam paramOUT = (SubProParam) params.get(9);
					int ret = Utils.parseInt(paramOUT.getString().trim());
					if (ret == 1) {
						isUpdate = true;
					}
				}
				String outScreen = "[--> updateVod  with params: ["
						+ vod.toString() + "] : returnValue(isUpdate ="
						+ isUpdate + " at side " + type + "]";
				log.info(outScreen);
			} catch (Exception ex) {
				log.error(ex.getMessage());
				isUpdate = false;
			}
		}
		return isUpdate;

	}

	public int countVodOfSubject(int subjId, String type) {
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
			params = executeSubPro(eVODSql.sqlCountVod, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return count;
	}

	@SuppressWarnings("unchecked")
	public Vector<Subject> getSubjectsOutInVod(int vodId, String type,
			int langid) {
		Vector<Subject> vSubject = new Vector<Subject>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vodId),
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
			params = executeSubPro(eVODSql.sqlGetOutInSubjects, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vSubject = DataUtils.LoadSubject(outParam);
		String outScreen = "[getSubjectsOutInVod with params: langid=" + langid
				+ "type=" + type + "] : returnValue(size=" + vSubject.size()
				+ ")";
		log.info(outScreen);
		return vSubject;
	}

	// mytv
	public Vector<Vod> getVascVods(int subj_cnt, int subj_svc, int langid,
			int frRow, int toRow) {
		Vector<Vod> vVod = new Vector<Vod>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(subj_cnt), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(subj_svc),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(frRow),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(toRow),
				SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlgetVascVods, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadVod(outParam);
		String outScreen = "[getSvcVods with params: subj_cnt=" + subj_cnt
				+ ",subj_svc=" + subj_svc + "] : returnValue(size="
				+ vVod.size() + ")";
		log.info(outScreen);
		return vVod;
	}

}
