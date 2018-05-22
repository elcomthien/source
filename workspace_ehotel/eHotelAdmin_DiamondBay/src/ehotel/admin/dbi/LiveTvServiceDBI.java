package ehotel.admin.dbi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.admin.LiveTV.LiveModel;
import ehotel.domain.liveTV.LiveTV;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class LiveTvServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	public static final String ADD_CHANNEL = "BEGIN ELIVETV.addChannelForHotel(?,?,?); END;";
	public static final String DELETE_CHANNEL = "BEGIN ELIVETV.deleteChannelForHotel(?); END;";
	public static final String UPDATE_CHANNEL = "BEGIN ELIVETV.updateChannelForHotel(?,?,?); END;";
	public static final String UPDATE_LIVETV = "BEGIN ELIVETV.updateChannel(?,?,?,?,?); END;";
	public static final String GET_CHANENELS = "BEGIN eLiveTV.getChannelOfSubject(?,?,?,?); END;";
	public static final String GET_CHANENEL_CODE = "BEGIN eLiveTV.getChannelCode(?); END;";
	public static final String UPDATE_CHANENEL_CODE = "BEGIN eLiveTV.updateChannelCode(?,?,?); END;";

	public static Vector<String> outParam = new Vector<String>();

	public static boolean addChannelForHotel(String channelname, String channellink, String channelimage) {
		System.out.println("insert channel for hotel");
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(channelname), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(channellink), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(channelimage), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(ADD_CHANNEL, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public static List<LiveModel> getListChannelAdvertise() {
		System.out.println("Get list channel advertise");
		List<LiveModel> list = new ArrayList<LiveModel>();
		String sql = "select * from BC_CHANNELS where INSTR(PHYSICAL_ADDRESS, 'Video') > 0";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				while (result.hasNext()) {
					result.next();
					LiveModel live = new LiveModel();
					live.setChannelId(result.getParam("CHANNELID"));
					live.setChannelName(result.getParam("CHANNELNAME"));
					live.setImage(result.getParam("URL_IMAGE"));
					live.setUrl(result.getParam("PHYSICAL_ADDRESS"));
					list.add(live);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean deleteChannelAdvertise(int id) {
		System.out.println("Delete channel advertise");
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(DELETE_CHANNEL, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean updateChannel(int id, String name, String image) {
		System.out.println("Update channel advertise for hotel");
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(name), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(image), SubProParam.IN);
		params.add(in);
		try {
			broker.executeSubPro(UPDATE_CHANNEL, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public static int updateLiveTV(int channelid, String name, String link, String image) {
		int req = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(channelid), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(link), 0);
		params.add(in);
		in = new SubProParam(new String(image), 0);
		params.add(in);
		SubProParam out = new SubProParam(new String(), 1);
		params.add(out);
		try {
			params = broker.executeSubPro(UPDATE_LIVETV, params);
			if (params != null && params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				req = Utils.parseInt(paramOUT.getString().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return req;
	}

	@SuppressWarnings("unchecked")
	public static Vector<LiveTV> getChannelsSubject(int subjId, int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(subjId), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(subOut);

		Vector<LiveTV> vChannels = new Vector<LiveTV>();
		try {
			params = broker.executeSubPro(GET_CHANENELS, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
				System.out.println(outParam);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		vChannels = DataUtils.LoadChannels(outParam);
		String outScreen = "[getChannel with params:subjId=" + subjId + "] : returnValue(size=" + vChannels.size() + ")";
		System.out.println(outScreen);
		return vChannels;
	}

	@SuppressWarnings("unchecked")
	public static List<HashMap<String, String>> getChannelCode() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_CHANENEL_CODE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < outParam.size(); i = i + 3) {
			HashMap<String, String> hmap = new HashMap<String, String>();
			hmap.put("channelid", outParam.get(i));
			hmap.put("channelname", outParam.get(i + 1));
			hmap.put("channelcode", outParam.get(i + 2));
			list.add(hmap);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static String updateChannelCode(String channelid, String channelcode) {
		String rs = "-1";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(channelid), 0);
		params.add(in);
		in = new SubProParam(new String(channelcode), 0);
		params.add(in);

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = broker.executeSubPro(UPDATE_CHANENEL_CODE, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				rs = paramOUT.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args) {
		// System.out.println(getListChannelAdvertise());
		System.out.println(updateChannelCode("1", "1"));
	}
}
