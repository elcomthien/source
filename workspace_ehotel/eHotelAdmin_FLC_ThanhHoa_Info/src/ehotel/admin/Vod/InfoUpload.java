package ehotel.admin.Vod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoUpload {
	private static Map<String, VodPercent> infoMap = null;

	public static VodPercent getInfoUpload(String idUpload) {
		if (infoMap == null) {
			return null;
		}
		return infoMap.get(idUpload);
	}

	public static void addNewUpload(String idUpload, VodPercent vod) {
		infoMap.put(idUpload, vod);
	}
 
	public List<VodPercent> getlistVod() {
		List<VodPercent> list = new ArrayList<VodPercent>();
		for (String key : infoMap.keySet()) {
			list.add(infoMap.get(key));
		}
		return list;
	}
	
	public static void main(String[] args) {
	}
}
