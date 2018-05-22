package com.elcom.pms.smile.service;

import java.util.ArrayList;
import java.util.List;

import com.elcom.Log.FileLog;
import com.elcom.pms.smile.dao.PMSSmileDAO;
import com.elcom.pms.smile.util.BillModel;
import com.elcom.pms.smile.util.PostSimple;

public class PMSSmileService {
	//private static final Logger log = Logger.getLogger(PMSOperaService.class);
	static final FileLog log = new FileLog("log_PMSOperaService.txt");
	private static PMSSmileDAO dao = new PMSSmileDAO();

	public static boolean functionGI(String GTH, String RN, String GF, String GN, String GT, String GA, String GD, String RTH, String GS) {
		log.write("FunctionGI | G#: " + GTH + " | RN: " + RN + " | GF: " + GF + " | GN: " + GN + " | GT: " + GT + " | GA: " + GA + " | GD: " + GD + " | R#: " + RTH + " | GS: " + GS);
		return dao.functionGI(GTH, RN, GF, GN, GT, GA, GD, RTH, GS);
	}
	
	public static boolean functionGO(String RTH) {
		log.write("FunctionGO | RTH: " + RTH);
		return dao.functionGO(RTH);
	}
	
	public static boolean functionRO(String RN) {
		log.write("FunctionRO | RN: " + RN);
		return dao.functionRO(RN);
	}

	public static boolean functionGC(String GTH, String GF, String GN, String GT, String GA, String GL, String GD, String RTH, String RN, String GS) {
		log.write("FunctionGC | G#: " + GTH + " | GF: " + GF + " | GN: " + GN + " | GT: " + GT + " | GA: " + GA + " | GL: " + GL + " | GD: " + GD + " | R#: " + RTH + " | RN: " + RN  + " | GS: " + GS);
		return dao.functionGC(GTH, GF, GN, GT, GA, GL, GD, RTH, RN, GS);
	}
	
	public static boolean functionRG(String RTH, String RN) {
		log.write("FunctionRG | R#: " + RTH + " | RN: " + RN);
		return dao.functionRG(RTH,RN);
	}
	
	public static boolean functionRR(String RO, String RN) {
		log.write("FunctionRG | RO: " + RO + " | RN: " + RN);
		return dao.functionRR(RO,RN);
	}

	public static boolean functionXL(String RN, String GTH, String MI, String MT, String DA, String TI, String MC) {
		log.write("FunctionXL | RN: " + RN + " | G#: " + GTH + " | MI: " + MI + " | MT: " + MT + " | DA: " + DA + " | TI: " + TI + " | MC: "
				+ MC);
		return dao.functionXL(RN, GTH, MI, MT, DA, TI, MC);
	}

	public static boolean functionXD(String MI) {
		log.write("FunctionXD | MI: " + MI);
		return dao.functionXD(MI);
	}
	
	public static boolean functionXI(String RN, String GTH, String TT, String TC, String BI, String BD, String BA, String DA, String TI) {
		log.write("FunctionXI | RN: " + RN + " | G#: " + GTH + " | TT: " + TT + " | TC: " + TC + " | BI: " + BI + " | BD: " + BD + " | BA: "
				+ BA + " | DA: " + DA  + " | TI: " + TI);
		return dao.functionXI(RN, GTH, TT, TC, BI, BD, BA, DA, TI);
	}
	
	public static boolean functionXB(String RN, String GTH, String BA, String DA, String TI) {
		log.write("FunctionXB | RN: " + RN + " | G#: " + GTH + " | BA: " + BA + " | DA: " + DA + " | TI: " + TI);
		return true;
	}

	public static List<BillModel> functionXR() {
		log.write("FunctionXR");
		List<BillModel> list = new ArrayList<BillModel>();
		list = dao.functionXR();
		return list;
	}

	// PostSimple
	public static List<PostSimple> functionPS() {
		log.write("FunctionPS");
		List<PostSimple> list = dao.functionPS();
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println("PMSSmileService");
	}
}
