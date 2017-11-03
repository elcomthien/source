package com.elcom.ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.admin.model.LiveTVChannelModel;
import com.elcom.ehotel.admin.model.LiveTVSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.SQL;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class LiveTVDao {
	@SuppressWarnings("unchecked")
	public List<LiveTVSubjectModel> getListSubjectLiveTV(int langId) {
		List<LiveTVSubjectModel> list = new ArrayList<LiveTVSubjectModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(langId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_LIVETV_SUBJECT,
					params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_LIST_LIVETV_SUBJECT,
				params, "langid", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			LiveTVSubjectModel sub = new LiveTVSubjectModel();
			sub.setIdSubject(outParam.get(i));
			sub.setName(outParam.get(i + 1));
			sub.setImage(outParam.get(i + 2));
			sub.setStatus(outParam.get(i + 3));
			sub.setModifyDate(outParam.get(i + 4));
			sub.setOderby(outParam.get(i + 5));
			sub.setLangId(Integer.toString(langId));
			list.add(sub);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addNewSubjectLiveTV(LiveTVSubjectModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getListadd()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_LIVETV_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_LIVETV_SUBJECT,
				params, "name,image,status,listadd", rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editSubjectLiveTV(LiveTVSubjectModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(livetv.getIdSubject()),
				0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getOderby()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLangId()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getListadd()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getListremove()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_LIVETV_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		LogUtil.logDao(LiveTVDao.class.toString(), SQL.EDIT_LIVETV_SUBJECT,
				params,
				"idSubject,name,image,index,langid,status,listadd,listremove",
				rs);

		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteSubjectLiveTV(int id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker
					.executeSubPro(SQL.DELETE_LIVETV_SUBJECT, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.DELETE_LIVETV_SUBJECT,
				params, "idSubject", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<LiveTVChannelModel> getListChannel(int idSubject) {
		List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_LIVETV_CHANNEL,
					params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_LIST_LIVETV_CHANNEL,
				params, "idSubject", outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			LiveTVChannelModel ch = new LiveTVChannelModel();
			ch.setIdChannel(outParam.get(i));
			ch.setName(outParam.get(i + 1));
			ch.setCode(outParam.get(i + 2));
			ch.setLink(outParam.get(i + 3));
			ch.setStatus(outParam.get(i + 4));
			ch.setImage(outParam.get(i + 5));
			ch.setMaxindex(outParam.get(i + 6));
			ch.setLanguage(outParam.get(i + 7));
			ch.setSubtitle(outParam.get(i + 8));
			list.add(ch);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addNewChannel(LiveTVChannelModel livetv, String idSubject) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(idSubject), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLanguage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getSubtitle()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_LIVETV_CHANNEL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_LIVETV_CHANNEL,
				params,
				"idSubject,name,code,link,image,status,language,subtitle", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editChannel(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(livetv.getIdChannel()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getCode()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLanguage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getSubtitle()), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_LIVETV_CHANNEL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(8);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.EDIT_LIVETV_CHANNEL,
				params,
				"idChannel,name,code,link,image,status,language,subtitle", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteChannel(int id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker
					.executeSubPro(SQL.DELETE_LIVETV_CHANNEL, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.DELETE_LIVETV_CHANNEL,
				params, "idChannel", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<LiveTVChannelModel> getListChannelAdvertise() {
		List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_CHANNEL_ADVERTISE,
					params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(),
				SQL.GET_LIST_CHANNEL_ADVERTISE, params, "idSubject",
				outParam.size() / 9);
		for (int i = 0; i < outParam.size(); i = i + 9) {
			LiveTVChannelModel ch = new LiveTVChannelModel();
			ch.setIdChannel(outParam.get(i));
			ch.setName(outParam.get(i + 1));
			ch.setCode(outParam.get(i + 2));
			ch.setLink(outParam.get(i + 3));
			ch.setStatus(outParam.get(i + 4));
			ch.setImage(outParam.get(i + 5));
			ch.setMaxindex(outParam.get(i + 6));
			ch.setLanguage(outParam.get(i + 7));
			ch.setSubtitle(outParam.get(i + 8));
			list.add(ch);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addChannelAdvertise(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getLink()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker
					.executeSubPro(SQL.ADD_CHANNEL_ADVERTISE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_CHANNEL_ADVERTISE,
				params, "name,link,image,status", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editChannelAdvertise(LiveTVChannelModel livetv) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(livetv.getIdChannel()),
				0);
		params.add(in);
		in = new SubProParam(new String(livetv.getName()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getImage()), 0);
		params.add(in);
		in = new SubProParam(new String(livetv.getStatus()), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_CHANNEL_ADVERTISE,
					params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_CHANNEL_ADVERTISE,
				params, "channelId,name,link,image,status", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteChannelAdvertise(int id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_CHANNEL_ADVERTISE,
					params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(),
				SQL.DELETE_CHANNEL_ADVERTISE, params, "idChannel", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public String getListChannel() {
		String result = "";
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(
					"BEGIN ELIVETV.getLinkChannelAdvertise(?); END;", params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i++) {
			if (outParam.get(i).length() > 1)
				result += outParam.get(i);
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListVideo() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_VIDEO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_LIST_VIDEO, params,
				"none", outParam.size() / 6);
		for (int i = 0; i < outParam.size(); i = i + 6) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", outParam.get(i));
			map.put("name", UnicodeConverter.decodeUnicode(outParam.get(i + 1)));
			map.put("url", outParam.get(i + 2));
			map.put("image", outParam.get(i + 3));
			map.put("status", outParam.get(i + 4));
			map.put("index", outParam.get(i + 5));
			list.add(map);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int addVideo(String name, String url, String status) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(url), 0);
		params.add(in);
		in = new SubProParam(new String(status), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.ADD_VIDEO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.ADD_VIDEO, params,
				"name,url,status", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int editVideo(String id, String name, String url, String status,
			String index) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(url), 0);
		params.add(in);
		in = new SubProParam(new String(status), 0);
		params.add(in);
		in = new SubProParam(new String(index), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.EDIT_VIDEO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.EDIT_VIDEO, params,
				"id,name,url,status,index", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int deleteVideo(String id) {
		int rs = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(id), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.DELETE_VIDEO, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				rs = ConvertUtil.convertToInteger(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.DELETE_VIDEO, params,
				"id", rs);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getChannelView(String from, String to) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(from), 0);
		params.add(in);
		in = new SubProParam(new String(to), 0);
		params.add(in);

		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_CHANNEL_VIEW, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LogUtil.logDao(LiveTVDao.class.toString(), SQL.GET_CHANNEL_VIEW,
				params, "from,to", outParam.size() / 5);
		for (int i = 0; i < outParam.size(); i = i + 5) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", outParam.get(i));
			map.put("name", outParam.get(i + 1));
			map.put("url", outParam.get(i + 2));
			map.put("image", outParam.get(i + 3));
			map.put("views", outParam.get(i + 4));
			list.add(map);
		}
		return list;
	}

	public static void main(String[] args) {
		// LiveTVDao l = new LiveTVDao();
		// System.out.println(l.getListChannel());
		// String t =
		// "http://ehotel.elcom.vn/loop_video/abc.mp4[@]http://ehotel.elcom.vn/loop_video/xyz.mp4";
		// String[] arr = t.split("\\[@\\]");
		// System.out.println(arr[0]);
		// System.out.println(arr[1]);
		// String filename = "video_1496816910678.mp4";
		// System.out.println(l.addVideo(filename));
	}
}