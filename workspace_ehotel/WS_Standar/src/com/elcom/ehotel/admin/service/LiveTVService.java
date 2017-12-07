package com.elcom.ehotel.admin.service;

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
	
	public List<LiveTVChannelModel> getLAllChannel() {
		return liveTVDao.getListAllChannel();
	}
}
