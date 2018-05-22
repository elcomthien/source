package ehotel.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.abs.pms.FolioStbPMS;
import ehotel.domain.pms.eSTB;
import ehotel.domain.vod.Vod;
import ehotel.render.VOD;
import ehotel.sql.eVODSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class eVideoSTBDao extends eBaseDao {
	private static Logger log = Logger.getLogger(eVideoSTBDao.class);

	public Vector<eSTB> getSTBList() {
		FolioStbPMS stblist = new FolioStbPMS();
		return stblist.getAllSTB(-1, -1);
	}

	@SuppressWarnings("unchecked")
	public Vector<Vod> getVodsOutStb(int subjectId, String serial_num,
			int langid) {
		Vector<Vod> vVod = new Vector<Vod>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(subjectId), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlgetVodsOutStb, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadVod_syn(outParam);
		String outScreen = "[" + eVODSql.sqlGetVodsOfSubject
				+ " with params: subjId=" + subjectId + "] : returnValue(size="
				+ vVod.size() + ")";
		log.info(outScreen);
		return vVod;
	}

	@SuppressWarnings("unchecked")
	public Vector<Vod> getVodsInStb(String serial_num, int langid, int from,
			int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<Vod> vVod = new Vector<Vod>();
		try {
			params = executeSubPro(eVODSql.sqlgetVodsOfStb, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();

			}
			vVod = DataUtils.LoadVod_syn(outParam);
			String outScreen = "[getVodsOfStb with params: serial_num="
					+ serial_num + ",size=" + vVod.size() + "] )";
			log.info(outScreen);

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return vVod;
	}

	@SuppressWarnings("unchecked")
	public Vector<Vod> getVods_UnSynCompleted(String serial_num, int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<Vod> vVod = new Vector<Vod>();
		try {
			params = executeSubPro(eVODSql.sqlgetVods_UnSynCompleted, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();

			}
			vVod = DataUtils.LoadVod_syn(outParam);
			String outScreen = "[getVods_UnSynCompleted with params: serail_num="
					+ serial_num + "] )";
			log.info(outScreen);

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return vVod;
	}

	public boolean insertSynVodSTB(String serial_num, int vod_id,
			String session_core_id, String file_size) {
		boolean isadd = false;
		if (serial_num == null) {
			log.info("serail_num or str_vod_id  is null.addSynVodSTB is not success.");
			return false;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(vod_id),
				SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(session_core_id, SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(new java.math.BigDecimal(vod_id),
				SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eVODSql.sqlinsertSynVodSTB, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				int seqadd = Utils.parseInt(paramOUT.getString().trim());
				if (seqadd == -2) {
					isadd = false;
					log.info("Vod_id[" + vod_id + "] is exist.");
				}
				if (seqadd > 0)
					isadd = true;
			}
			String outScreen = "[--> insertSynVodSTB with params: [serial_num="
					+ serial_num + ",vod_id=" + vod_id
					+ "] : returnValue(isadd =" + isadd + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isadd;
	}

	public boolean setSynStatus(String serial_num, String session_id) {
		boolean isadd = false;
		if (serial_num == null) {
			log.info("serail_num  is null.setSynStatus is not success.");
			return false;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(session_id, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eVODSql.sqlsetSynStatus, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int seqadd = Utils.parseInt(paramOUT.getString().trim());
				if (seqadd == 1)
					isadd = true;
			}
			String outScreen = "[--> setSynStatus with params: [serial_num="
					+ serial_num + ",session_id=" + session_id
					+ "] : returnValue(isadd =" + isadd + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return isadd;
	}

	public int countVodOnSTB(String serial_num) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eVODSql.sqlcountVodOnSTB, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[--> setSynStatus with params: [serial_num="
					+ serial_num + "] : returnValue(count =" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return count;
	}

	public String getSessionId(String serial_num, int vod_id) {
		String sessId = "0";
		if (serial_num == null) {
			log.info("serail_num  is null.setSynStatus is not success.");
			return sessId;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(vod_id),
				SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eVODSql.sqlgetSessionId, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				sessId = paramOUT.getString().trim();

			}
			String outScreen = "[--> getSessionId with params: [serial_num="
					+ serial_num + ",vod_id=" + vod_id
					+ "] : returnValue(sessId =" + sessId + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return sessId;
	}

	@SuppressWarnings("unchecked")
	public Vector<Vod> searchVodOnStb(String serial_num, String searchCode,
			int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(searchCode, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<Vod> vVod = new Vector<Vod>();
		try {
			params = executeSubPro(eVODSql.sqlsearchVodOnStb, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();

			}
			vVod = DataUtils.LoadVod_syn(outParam);
			String outScreen = "[searchVodOnStb with params: serail_num="
					+ serial_num + "] )";
			log.info(outScreen);

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return vVod;
	}

	public String getFilePath(int vod_id) {
		String filePath = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(vod_id),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(VOD.VOD, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eVODSql.sqlgetFilePath, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				filePath = paramOUT.getString().trim();

			}
			String outScreen = "[--> getFilePath with params: [vod_id="
					+ vod_id + "] : returnValue(filePath =" + filePath + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return filePath;
	}

	public String getIpStb(String serial_num) {
		String ip_stb = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(serial_num, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eVODSql.sqlgetIpStb, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				ip_stb = paramOUT.getString().trim();
			}
			String outScreen = "[--> getIpStb with params: [serial_num="
					+ serial_num + "] : returnValue(ip_stb =" + ip_stb + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ip_stb;
	}

	public String getEvs_storage(String protocol) {
		String evs_dir_storage = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(protocol, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eVODSql.sqlgetEvs_storage, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				evs_dir_storage = paramOUT.getString().trim();
			}
			String outScreen = "[--> getEvs_storage with params: [protocol="
					+ protocol + "] : returnValue(evs_dir_storage ="
					+ evs_dir_storage + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return evs_dir_storage;
	}

}
