package com.elcom.esignage.app.dbi;

import java.util.Vector;

import com.elcom.esignage.app.dbi.SubProParam;

public class ConnectionUtil {
	private static IMBroker broker = IMBroker.getInstance();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getStringGenerals(String query, Vector paramiv,
			int paramout) {
		String result = "";
		int size = paramiv.size();
		SubProParam out_data;

		try {
			Vector<SubProParam> params;
			if (paramout == 1) {
				params = new Vector<SubProParam>(size + 1);
			} else
				params = new Vector<SubProParam>(size);
			SubProParam param = null;
			for (int i = 0; i < size; i++) {
				String para = (String) paramiv.get(i);
				param = new SubProParam(new String(para), SubProParam.IN);
				params.add(i, param);
			}
			if (paramout == 1) {
				out_data = new SubProParam(new String(), SubProParam.OUT);
				params.add(paramiv.size(), out_data);
			}
			params = broker.executeSubPro(query, params);
			if (paramout == 1) {
				out_data = (SubProParam) params.get(size);
				result = (String) out_data.getValue();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return result;
	}

}
