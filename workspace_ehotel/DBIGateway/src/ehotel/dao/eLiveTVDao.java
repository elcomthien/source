package ehotel.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.vod.Subject;
import ehotel.sql.eLiveTVSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class eLiveTVDao extends eBaseDao {
	private static Logger log = Logger.getLogger(eMODDao.class);

	public int addCntSubject(Subject subjName, int parentId) {
		return addSubject(subjName, parentId, this.CONTENT);
	}

	public int addSvcSubject(Subject subjName, int parentId) {
		return addSubject(subjName, parentId, this.SERVICE);
	}

	public boolean removeCntSubject(int subjId) {
		return removeSubject(subjId, this.CONTENT);
	}

	public boolean removeSvcSubject(int subjId) {
		return removeSubject(subjId, this.SERVICE);
	}

	public int editCntSubject(Subject subject, int langId) {
		return editSubject(subject, langId, this.CONTENT);
	}

	public Vector<Subject> getCntSubjects(int langid) {
		return getSubjects(langid, this.CONTENT);
	}

	public Vector<Subject> getSvcSubjects(int langid) {
		return getSubjects(langid, this.SERVICE);
	}

	public int editSvcSubject(Subject subject, int langId) {
		return editSubject(subject, langId, this.SERVICE);
	}

	private int addSubject(Subject subject, int parentId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = -1;
		if (subject == null || subject.getName() == null) {
			log.info("Param[subject] is null");
			return ret;
		}
		SubProParam subPro = new SubProParam(subject.getName(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(subject.getUrlImage(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(parentId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eLiveTVSql.sqlAddSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
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
			params = executeSubPro(eLiveTVSql.sqlRemoveSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 0)
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

	private int editSubject(Subject subject, int langId, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		int ret = -1;
		if (subject == null)
			return ret;
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				subject.getId()), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(subject.getName(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(subject.getUrlImage(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eLiveTVSql.sqlEditSubject, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				ret = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[--> editSubject  with params: ["
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
			params = executeSubPro(eLiveTVSql.sqlGetSubjectInfo, params);
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
	private Vector<Subject> getSubjects(int langid, String type) {
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
			params = executeSubPro(eLiveTVSql.sqlGetSubjects, params);
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

	// ===================CHANNEL=================================
	@SuppressWarnings("unchecked")
	public Vector<LiveTV> getChannels(int subjId, int langid, int from,
			int tto, String type) {

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<LiveTV> vChannels = new Vector<LiveTV>();
		try {
			params = executeSubPro(eLiveTVSql.sqlgetChannels, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vChannels = DataUtils.LoadChannels(outParam);
		String outScreen = "[getChannels with params:subjId=" + subjId
				+ ",langid=" + langid + "type=" + type
				+ "] : returnValue(size=" + vChannels.size() + ")";
		log.info(outScreen);
		return vChannels;
	}

	public boolean addLiveTV(int subjId, String str_channelId, String type) {
		boolean ret = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(str_channelId, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eLiveTVSql.sqladdLiveTV, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				if (Utils.parseInt(paramOUT.getString().trim()) == 1) {
					ret = true;
				}

			}
			String outScreen = "[--> addLiveTV(" + eLiveTVSql.sqladdLiveTV
					+ ")  with params: [subjId=" + subjId + ",str_channelId="
					+ str_channelId + "] : returnValue(isAdd =" + ret + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public LiveTV getChannelInfo(int channelId, int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(channelId), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		LiveTV channel = null;
		Vector<LiveTV> vChannels = new Vector<LiveTV>();
		String outScreen = "[getChannelInfo with params: channelId="
				+ channelId + "]";
		try {

			params = executeSubPro(eLiveTVSql.sqlgetChannelInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
		vChannels = DataUtils.LoadChannels(outParam);
		if (vChannels.size() > 0) {
			channel = vChannels.get(0);
			outScreen = "-->returnValue(" + channel.toString() + ")";
			log.info(outScreen);
		}
		return channel;
	}

	public int updateChannel(LiveTV channel, int langid) {
		int isEdit = -1;
		if (channel == null || channel.getServicename() == null)
			return isEdit;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				channel.getChannelid()), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(channel.getServicename(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(channel.getUrlImage(), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eLiveTVSql.sqleditChannel, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				isEdit = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[--> editChannel  with params: ["
					+ channel.toString() + "] : returnValue(isEdit =" + isEdit
					+ "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isEdit;
	}

	public boolean removeChannel(int subjId, String str_channel_id, String type) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(str_channel_id, SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eLiveTVSql.sqlremoveChannel, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[" + eLiveTVSql.sqlremoveChannel
					+ " with params: subjId=" + subjId + ",str_channel_id="
					+ str_channel_id + "] returnValue(isRemove=" + isRemove
					+ ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isRemove;
	}

	public boolean changeSubject(int livetvId, String str_subj_id, String type) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(livetvId), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(str_subj_id, SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eLiveTVSql.sqlchangeSubject, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[" + eLiveTVSql.sqlchangeSubject
					+ " with params: livetvId=" + livetvId + ",str_channel_id="
					+ str_subj_id + "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isChange;
	}

	public boolean changStatus(int subjctId, int livetvId, String type) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(subjctId), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(livetvId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eLiveTVSql.sqlchangStatus, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isChange = true;
			}
			String outScreen = "[SQL=(" + eLiveTVSql.sqlchangStatus
					+ ") with params: livetvId=" + livetvId
					+ "] returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return isChange;
	}

	@SuppressWarnings("unchecked")
	public Vector<LiveTV> searchChannel(int subjId, String liveName, int from,
			int tto, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(liveName, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<LiveTV> vChannels = new Vector<LiveTV>();
		try {
			params = executeSubPro(eLiveTVSql.sqlsearchChannel, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vChannels = DataUtils.LoadChannels(outParam);
		String outScreen = "[searchChannel with params:subjId=" + subjId
				+ ",liveName=" + liveName + "type=" + type
				+ "] : returnValue(size=" + vChannels.size() + ")";
		log.info(outScreen);
		return vChannels;
	}

	public int countChannels(int subjId, String type) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eLiveTVSql.sqlcountChannels, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[countChannels(sql="
					+ eLiveTVSql.sqlcountChannels + ") with params[subjId="
					+ subjId + "mtype=" + type + "  -->returnValue=" + count
					+ "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	public int countSearchChannel(int subjId, String liveName) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(liveName, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eLiveTVSql.sqlcountSearchChannel, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[countSearchChannel(sql="
					+ eLiveTVSql.sqlcountSearchChannel
					+ ") with params[subjId=" + subjId + "liveName=" + liveName
					+ "  -->returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public Vector<LiveTV> getChannelContentOutSuject(int subjectId, int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(subjectId), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<LiveTV> vChannels = new Vector<LiveTV>();
		try {
			params = executeSubPro(eLiveTVSql.sqlgetLiveTVContentOutSuject,
					params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vChannels = DataUtils.LoadChannels(outParam);
		String outScreen = "[getLiveTVContentOutSuject with params:subjectId="
				+ subjectId + ",langid=" + langid + "] : returnValue(size="
				+ vChannels.size() + ")";
		log.info(outScreen);
		return vChannels;

	}
}
