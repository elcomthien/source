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

	public List<HashMap<String, String>> getListVideo() {
		return liveTVDao.getListVideo();
	}

	public int addVideo(String name, String url, String image) {
		name = UnicodeConverter.encodeUnicode(name);
		return liveTVDao.addVideo(name, url, image);
	}

	public int editVideo(String id, String name, String url, String status, String index) {
		name = UnicodeConverter.encodeUnicode(name);
		return liveTVDao.editVideo(id, name, url, status, index);
	}
	
	public int deleteVideo(String id) {
		return liveTVDao.deleteVideo(id);
	}
	
	public List<HashMap<String, String>> getChannelView(String from, String to) {
		return liveTVDao.getChannelView(from, to);
	}
}
