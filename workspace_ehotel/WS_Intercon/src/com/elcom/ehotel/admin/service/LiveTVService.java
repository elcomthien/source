package com.elcom.ehotel.admin.service;

import java.util.HashMap;
import java.util.List;

import com.elcom.ehotel.admin.dao.LiveTVDao;
import com.elcom.ehotel.admin.model.LiveTVChannelModel;
import com.elcom.ehotel.admin.model.LiveTVSubjectModel;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;

public class LiveTVService {
	private LiveTVDao liveTVDao = new LiveTVDao();

	public List<LiveTVSubjectModel> getListSubjectLiveTV(int langId) {
		return liveTVDao.getListSubjectLiveTV(langId);
	}

	public int addNewSubjectLiveTV(LiveTVSubjectModel livetv) {
		return liveTVDao.addNewSubjectLiveTV(livetv);
	}

	public int editSubjectLiveTV(LiveTVSubjectModel livetv) {
		return liveTVDao.editSubjectLiveTV(livetv);
	}

	public int deleteSubjcetLiveTV(int id) {
		return liveTVDao.deleteSubjectLiveTV(id);
	}

	public List<LiveTVChannelModel> getListChannel(int idSubject) {
		return liveTVDao.getListChannel(idSubject);
	}

	public int addNewChannel(LiveTVChannelModel livetv, String idSubject) {
		return liveTVDao.addNewChannel(livetv, idSubject);
	}

	public int editChannel(LiveTVChannelModel livetv) {
		return liveTVDao.editChannel(livetv);
	}

	public int deleteChannel(int id) {
		return liveTVDao.deleteChannel(id);
	}

	public List<LiveTVChannelModel> getListChannelAdvertise() {
		return liveTVDao.getListChannelAdvertise();
	}

	public int addChannelAdvertise(LiveTVChannelModel livetv) {
		livetv.setName(UnicodeConverter.encodeUnicode(livetv.getName()));
		return liveTVDao.addChannelAdvertise(livetv);
	}

	public int editChannelAdvertise(LiveTVChannelModel livetv) {
		return liveTVDao.editChannelAdvertise(livetv);
	}

	public int deleteChannelAdvertise(String channelId) {
		return liveTVDao.deleteChannelAdvertise(ConvertUtil.convertToInteger(channelId));
	}

	public HashMap<String, String> getNotify(String langId) {
		return liveTVDao.getNotify(langId);
	}

	public int editNotify(String size, String color, String status, String text, String langid) {
		text = UnicodeConverter.encodeUnicode(text);
		return liveTVDao.editNotify(size, color, status, text, langid);
	}

	public HashMap<String, String> getNotifyGroup(String langId, String idgroup) {
		return liveTVDao.getNotifyGroup(langId, idgroup);
	}

	public int editNotifyGroup(String size, String color, String status, String text, String langid, String idgroup) {
		text = UnicodeConverter.encodeUnicode(text);
		return liveTVDao.editNotifyGroup(size, color, status, text, langid, idgroup);
	}

	public List<LiveTVChannelModel> getListChannelAdvertiseGroup(String idGroup) {
		return liveTVDao.getListChannelAdvertiseGroup(idGroup);
	}

	public int addChannelAdvertiseGroup(LiveTVChannelModel livetv) {
		livetv.setName(UnicodeConverter.encodeUnicode(livetv.getName()));
		return liveTVDao.addChannelAdvertiseGroup(livetv);
	}

	public int editChannelAdvertiseGroup(LiveTVChannelModel livetv) {
		return liveTVDao.editChannelAdvertiseGroup(livetv);
	}

	public int deleteChannelAdvertiseGroup(String channelId, String idGroup) {
		return liveTVDao.deleteChannelAdvertiseGroup(ConvertUtil.convertToInteger(channelId), idGroup);
	}

	public List<LiveTVChannelModel> getListChannelGroup(int idSubject, String idGroup) {
		return liveTVDao.getListChannelGroup(idSubject, idGroup);
	}

	public int addNewChannelGroup(LiveTVChannelModel livetv, String idSubject) {
		return liveTVDao.addNewChannelGroup(livetv, idSubject);
	}

	public int editChannelGroup(LiveTVChannelModel livetv) {
		return liveTVDao.editChannelGroup(livetv);
	}

	public int deleteChannelGroup(int id, String idGroup) {
		return liveTVDao.deleteChannelGroup(id, idGroup);
	}
}
