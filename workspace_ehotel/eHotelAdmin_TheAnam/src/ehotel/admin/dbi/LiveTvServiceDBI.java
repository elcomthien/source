package ehotel.admin.dbi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.admin.LiveTV.LiveModel;
import ehotel.utils.Utils;

public class LiveTvServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	public static final String ADD_CHANNEL = "BEGIN ELIVETV.addChannelForHotel(?,?,?); END;";
	public static final String DELETE_CHANNEL = "BEGIN ELIVETV.deleteChannelForHotel(?); END;";
	public static final String UPDATE_CHANNEL = "BEGIN ELIVETV.updateChannelForHotel(?,?,?); END;";
	public static final String UPDATE_LIVETV = "BEGIN ELIVETV.updateChannel(?,?,?,?,?); END;";
	
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
		String sql = "select * from BC_CHANNELS where INSTR(PHYSICAL_ADDRESS, '5000') > 0";
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
	
	public static boolean deleteChannelAdvertise(int id){
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
	
	public static boolean updateChannel(int id, String name, String image){
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
	public static int updateLiveTV(int channelid, String name, String link, String image){
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
			 if(params != null && params.size() > 0)
	            {
	                SubProParam paramOUT = (SubProParam)params.get(4);
	                req = Utils.parseInt(paramOUT.getString().trim());
	            }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return req;
	}
	
	public static void main(String[] args) {
		System.out.println(updateChannel(2249,"Test","Test"));
	}
}
