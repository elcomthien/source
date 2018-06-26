package ehotel.admin.dbi;

import java.util.Vector;

import com.elcom.DBI.SubProParam;

public class SystemServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	public static final String UPDATE_BG_VIDEO = "BEGIN EPMS.editBackgroundVideo(?); END;";

	@SuppressWarnings({ "unchecked" })
	public static boolean updateBGVideo(String name) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subIn = new SubProParam(new String(name), SubProParam.IN);
		params.add(subIn);

		try {
			params = broker.executeSubPro(UPDATE_BG_VIDEO, params);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args) {

	}

}
