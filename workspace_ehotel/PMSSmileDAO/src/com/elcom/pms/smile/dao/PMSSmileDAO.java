package com.elcom.pms.smile.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;
import com.elcom.Log.FileLog;
import com.elcom.pms.smile.dbi.IMBroker;
import com.elcom.pms.smile.util.BillModel;
import com.elcom.pms.smile.util.PostSimple;
import com.elcom.pms.smile.util.SQL;
import com.elcom.pms.smile.util.UnicodeConverter;

public class PMSSmileDAO {
	//private static final Logger log = Logger.getLogger(PMSOperaDAO.class);
	static final FileLog log = new FileLog("log_PMSSmileDao.txt");
	private static IMBroker broker = IMBroker.getInstance();

	@SuppressWarnings("unchecked")
	public boolean functionGI(String GTH, String RN, String GF, String GN, String GT, String GA, String GD, String RTH, String GS) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		GT = UnicodeConverter.encodeUnicode(GT);
		GF = UnicodeConverter.encodeUnicode(GF);
		GN = UnicodeConverter.encodeUnicode(GN);
		param = new SubProParam(new String(GTH), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(RN), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GF), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GN), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GT), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GA), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GD), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(RTH), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GS), SubProParam.IN);
		params.add(param);

		log.write("COM_NEW_GUEST | " + GTH + " | " + RN + " | " + GF + " | " + GN + " | " + GT + " | " + GA + " | " + GD + " | " + RTH + " | " + GS);
		try {
			params = broker.executeSubPro(SQL.COM_NEW_GUEST, params);
		} catch (Exception e) {
			flag = false;
			log.write("Checkin error?????" + e);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public boolean functionGO(String RTH) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(RTH), SubProParam.IN);
		params.add(param);
		log.write("COM_DELETE_GUEST | " + RTH);
		try {
			params = broker.executeSubPro(SQL.COM_DELETE_GUEST, params);
		} catch (Exception e) {
			flag = false;
			log.write("Checkout guest error?????" + e);
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public boolean functionRO(String RN) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(RN), SubProParam.IN);
		params.add(param);
		log.write("COM_DELETE_ROOM | " + RN);
		try {
			params = broker.executeSubPro(SQL.COM_DELETE_ROOM, params);
		} catch (Exception e) {
			flag = false;
			log.write("Checkout room error?????" + e);
		}
		return flag;
	}


	@SuppressWarnings("unchecked")
	public boolean functionGC(String GTH, String GF, String GN, String GT, String GA, String GL, String GD, String RTH, String RN, String GS) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		GT = UnicodeConverter.encodeUnicode(GT);
		GF = UnicodeConverter.encodeUnicode(GF);
		GN = UnicodeConverter.encodeUnicode(GN);
		param = new SubProParam(new String(GTH), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GF), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GN), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GT), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GA), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GL), SubProParam.IN); 
		params.add(param);
		param = new SubProParam(new String(GD), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(RTH), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(RN), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GS), SubProParam.IN);
		params.add(param);
		log.write("COM_UPDATE_GUEST | " + GTH + " | " + GF + " | " + GN + " | " + GT + " | " + GA + " | " + GL + " | " + GD + " | " + RTH + " | " + RN + " | " + GS);
		try {
			params = broker.executeSubPro(SQL.COM_UPDATE_GUEST, params);
		} catch (Exception e) {
			flag = false;
			log.write("Update guest error?????" + e);
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public boolean functionRG(String RTH, String RN) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(RTH), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(RN), SubProParam.IN);
		params.add(param);
		log.write("COM_MOVE_GUEST| " + RTH + " | " + RN);
		try {
			params = broker.executeSubPro(SQL.COM_MOVE_GUEST, params);
		} catch (Exception e) {
			flag = false;
			log.write("Move guest error?????" + e);
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public boolean functionRR(String RO, String RN) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(RO), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(RN), SubProParam.IN);
		params.add(param);
		log.write("COM_MOVEALL_GUEST| " + RO + " | " + RN);
		try {
			params = broker.executeSubPro(SQL.COM_MOVEALL_GUEST, params);
		} catch (Exception e) {
			flag = false;
			log.write("Move all guest error?????" + e);
		}
		return flag;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean functionXL(String RN, String GTH, String MI, String MT, String DA, String TI, String MC) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		MT = UnicodeConverter.encodeUnicode(MT);
		MT = UnicodeConverter.encodeUnicode(MT);
		MC = UnicodeConverter.encodeUnicode(MC);
		param = new SubProParam(new String(RN), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GTH), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(MI), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(MT), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(DA), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(TI), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(MC), SubProParam.IN);
		params.add(param);
		log.write("COM_NEW_MESSAGE | " + RN + " | " + GTH + " | " + MI + " | " + MT + " | " + DA + " | " + TI + " | " + MC);
		try {
			params = broker.executeSubPro(SQL.COM_NEW_MESSAGE, params);
		} catch (Exception e) {
			flag = false;
			log.write("Send message to guest error?????" + e);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public boolean functionXD(String MI) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(MI), SubProParam.IN); // message id
		params.add(param);
		log.write("COM_DELETE_MESSAGE | "+ MI);
		try {
			params = broker.executeSubPro(SQL.COM_DELETE_MESSAGE, params);
		} catch (Exception e) {
			flag = false;
			log.write("Delete message error?????" + e);
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public boolean functionXI(String RN, String GTH, String TT, String TC, String BI, String BD, String BA, String DA, String TI) {
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam param = null;
		param = new SubProParam(new String(RN), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(GTH), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(TT), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(TC), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(BI), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(BD), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(BA), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(DA), SubProParam.IN);
		params.add(param);
		param = new SubProParam(new String(TI), SubProParam.IN);
		params.add(param);
		log.write("COM_NEW_BILL_ITEM | " + RN + " | " + GTH + " | " + TT + " | " + TC + " | " + BI + " | " + BD + " | " + BA + " | " + DA + " | " + TI);
		try {
			params = broker.executeSubPro(SQL.COM_NEW_BILL_ITEM, params);
		} catch (Exception e) {
			flag = false;
			log.write("Get bill request for guest error?????" + e);
		}
		return flag;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BillModel> functionXR() {
		log.write("XR");
		Vector outParam = new Vector();
		List<BillModel> list = new ArrayList<BillModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		
		try {
			params = broker.executeSubPro(SQL.FUNCTION_XR, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
				log.write("Data from DB: " + outParam);
				for (int i = 0; i < outParam.size(); i = i + 2) {
					BillModel bill = new BillModel();
					bill.setRN(outParam.get(i).toString());
					bill.setGTH(outParam.get(i + 1).toString());
					list.add(bill);
				}
			}
		} catch (Exception e) {
			log.write("getFunctionReq write?????" + e);
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PostSimple> functionPS() {
		log.write("PS");
		
		Vector outParam = new Vector();
		List<PostSimple> list = new ArrayList<PostSimple>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(SQL.FUNCTION_PS, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
				log.write("Data from DB: " + outParam);
				System.out.println(outParam);
				for (int i = 0; i < outParam.size(); i = i + 8) {
					PostSimple post = new PostSimple();
					post.setRN(outParam.get(i).toString());
					post.setTA(outParam.get(i + 1).toString());
					post.setPT(outParam.get(i + 2).toString());
					post.setPTH(outParam.get(i + 3).toString());
					post.setDA(outParam.get(i + 4).toString());
					post.setTI(outParam.get(i + 5).toString());
					post.setDU(outParam.get(i + 6).toString());
					post.setSO(outParam.get(i + 7).toString());
					post.setCU(outParam.get(i + 8).toString());
					log.write("Data post: " + post.toString());
//					System.out.println(post.toString());
					list.add(post);
				}
			}
		} catch (Exception e) {
			log.write("Post simple write?????" + e);
			e.printStackTrace();
		}
		return list;
	}


	public static void main(String[] args) {
		PMSSmileDAO p = new PMSSmileDAO();
		p.functionPS();
	}

}
