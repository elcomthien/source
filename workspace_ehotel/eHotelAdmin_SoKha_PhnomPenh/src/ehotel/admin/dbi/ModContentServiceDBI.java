package ehotel.admin.dbi;

import java.math.BigDecimal;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

public class ModContentServiceDBI {
	static IMBroker broker = IMBroker.getInstance();
	public static final String ADD_MOD = "BEGIN EMOD.ADDMOD(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String SET_URL= "BEGIN EMOD.setURL(?,?,?); END;";

	@SuppressWarnings("rawtypes")
	public String getFileNameModById(int modId) {
		String filename = "";
		String sql = "select distinct url from mod_content where contentid = " + modId;
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			if (vector.size() > 2) {
				ResultDB result = new ResultDB(vector);
				result.moveToFirst();
				filename = result.getParam("URL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	@SuppressWarnings("rawtypes")
	public String getSeqForMod() {
		String seq = "";
		String sql = "select SEQ_CONTENT_ID.nextval from dual";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			System.out.println(vector);
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			seq = result.getParam("NEXTVAL");
			System.out.println(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seq;
	}

	@SuppressWarnings("rawtypes")
	public String getMaxId() {
		String id = "";
		String sql = "select max(contentid) from mod_content";
		Vector vector = new Vector();
		try {
			vector = broker.executeSelect(sql, null);
			System.out.println(vector);
			ResultDB result = new ResultDB(vector);
			result.moveToFirst();
			id = result.getParam("max(contentid)");
			System.out.println(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean updateUrlForMod(int id, String url) {
		boolean flag = true;
//		String sql = "update mod_content set url = '" + url + "' where contentid = " + id;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new String(url), SubProParam.IN);
		params.add(in);
		in = new SubProParam(new BigDecimal(-1), SubProParam.OUT);
		params.add(in);
		try {
			broker.executeSubPro(SET_URL, params);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		ModContentServiceDBI m = new ModContentServiceDBI();
		System.out.println(m.updateUrlForMod(1105, "1105.mp3"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int addModDbi(int subjectid, String title) {
		Vector params = new Vector(11);
		SubProParam in = null;
		in = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
		params.add(in); // 0
		in = new SubProParam(new java.lang.String(title), SubProParam.IN);
		params.add(in); // 1
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 2
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 3
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 4
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 5
		in = new SubProParam(new java.lang.String(title), SubProParam.IN);
		params.add(in); // 6
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 7
		in = new SubProParam(new java.lang.String(""), SubProParam.IN);
		params.add(in); // 8
		in = new SubProParam(new BigDecimal(0), SubProParam.IN);
		params.add(in); // 9

		SubProParam out = new SubProParam(new String(), SubProParam.OUT);
		params.add(out); // 10
		try {
			params = broker.executeSubPro(ADD_MOD, params);
			// System.out.println("B1");
			// out = (SubProParam)params.get(10);
			// System.out.println("B2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SubProParam paramOUT = (SubProParam) params.get(10);
		String temp = paramOUT.getString();
		int rs = Integer.parseInt(temp);
		// SubProParam paramOUT = (SubProParam) params.get(10);
		// String temp = paramOUT.getString();
		// System.out.println("ADD_NSD:" + temp);

		return rs;
	}

	
}
