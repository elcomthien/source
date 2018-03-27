package elcom.com.core.read;

import java.util.*;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description: Dinh tap lenh cac fiedl giao tiep co trong tung command. Chi dinh nghia cac field ma IPTV nhan tu PMS
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class ParamMap {
	@SuppressWarnings("rawtypes")
	protected static Map ICHECKIN_GC_MAP = new HashMap();
	@SuppressWarnings("rawtypes")
	protected static Map ICHECKOUT_MAP = new HashMap();
	@SuppressWarnings("rawtypes")
	protected static Map IMOVEGUEST_MAP = new HashMap();
	@SuppressWarnings("rawtypes")
	protected static Map IRECHECHINOUT_MAP = new HashMap();
	@SuppressWarnings("rawtypes")
	protected static Map IMESSAGE_MAP = new HashMap(); // message online or offline
	@SuppressWarnings("rawtypes")
	protected static Map IMESSAGE_DEL_MAP = new HashMap(); // delete
	@SuppressWarnings("rawtypes")
	protected static Map IBILL_DEL_MAP = new HashMap(); // delete
	@SuppressWarnings("rawtypes")
	protected static Map IBILL_MAP = new HashMap(); // nhan transaction online or offline
	@SuppressWarnings("rawtypes")
	protected static Map IBALANCE_MAP = new HashMap();
	@SuppressWarnings("rawtypes")
	protected static Map IANSWERTRANSACTION = new HashMap(); // updatePostTransaction

	public ParamMap() {
		ICHECKIN_MAP();
		ICHECKOUT_MAP();
		IMOVEGUEST_MAP();
		IRECHECHINOUT_MAP();
		IMESSAGE_MAP();
		IMESSAGE_DEL_MAP();
		IBILL_MAP();
		IBILL_DEL_MAP();
		IBALANCE_MAP();
		IANSWERTRANSACTION();
	}

	@SuppressWarnings("unchecked")
	private void ICHECKIN_MAP() { // 13
		ICHECKIN_GC_MAP.put("G#", 1); // 1: thuoc, 0=khong nam trong tap lenh
		ICHECKIN_GC_MAP.put("GA", 1);
		ICHECKIN_GC_MAP.put("GD", 1);
		ICHECKIN_GC_MAP.put("GF", 1);
		ICHECKIN_GC_MAP.put("RN", 1);
		ICHECKIN_GC_MAP.put("R#", 1);
		ICHECKIN_GC_MAP.put("GG", 1);
		ICHECKIN_GC_MAP.put("GL", 1);
		ICHECKIN_GC_MAP.put("GN", 1);
		ICHECKIN_GC_MAP.put("GP", 1);
		ICHECKIN_GC_MAP.put("GT", 1);
		ICHECKIN_GC_MAP.put("GS", 1);
		ICHECKIN_GC_MAP.put("GE", 1);
	}

	@SuppressWarnings("unchecked")
	private void ICHECKOUT_MAP() { // 1
		ICHECKOUT_MAP.put("R#", 1);
	}

	@SuppressWarnings("unchecked")
	private void IMOVEGUEST_MAP() { // 3
		IMOVEGUEST_MAP.put("R#", 1);
		IMOVEGUEST_MAP.put("RN", 1);
		IMOVEGUEST_MAP.put("RO", 1);
	}

	@SuppressWarnings("unchecked")
	private void IRECHECHINOUT_MAP() {
		IRECHECHINOUT_MAP.put("R#", 1);
	}

	@SuppressWarnings("unchecked")
	private void IMESSAGE_MAP() {
		IMESSAGE_MAP.put("MI", 1);
		IMESSAGE_MAP.put("MT", 1);
		IMESSAGE_MAP.put("G#", 1);
		IMESSAGE_MAP.put("DA", 1);
		IMESSAGE_MAP.put("TI", 1);
		IMESSAGE_MAP.put("RN", 1);
	}

	@SuppressWarnings("unchecked")
	private void IMESSAGE_DEL_MAP() {
		IMESSAGE_DEL_MAP.put("MI", 1);
		IMESSAGE_DEL_MAP.put("G#", 1);
		IMESSAGE_DEL_MAP.put("RN", 1);
		// IMESSAGE_DEL_MAP.put("MS",1); phan dung cho iptv day qua ben pms
	}

	@SuppressWarnings("unchecked")
	private void IBILL_MAP() {
		IBILL_MAP.put("RN", 1);
		IBILL_MAP.put("G#", 1);
		IBILL_MAP.put("BD", 1);
		IBILL_MAP.put("BI", 1);
		IBILL_MAP.put("BA", 1);
		IBILL_MAP.put("T#", 1);
		IBILL_MAP.put("TC", 1);
		IBILL_MAP.put("DA", 1);
		IBILL_MAP.put("TI", 1);
		IBILL_MAP.put("CU", 1);
	}

	@SuppressWarnings("unchecked")
	private void IBILL_DEL_MAP() {
		IBILL_DEL_MAP.put("T#", 1);
		IBILL_DEL_MAP.put("RN", 1);
		IBILL_DEL_MAP.put("BA", 1);
		IBILL_DEL_MAP.put("G#", 1);
	}

	@SuppressWarnings("unchecked")
	private void IBALANCE_MAP() {
		IBALANCE_MAP.put("RN", 1);
		IBALANCE_MAP.put("G#", 1);
		IBALANCE_MAP.put("BA", 1);
		IBALANCE_MAP.put("DA", 1);
		IBALANCE_MAP.put("TI", 1);
	}

	@SuppressWarnings("unchecked")
	private void IANSWERTRANSACTION() {
		IANSWERTRANSACTION.put("RN", 1);
		IANSWERTRANSACTION.put("P#", 1);
		IANSWERTRANSACTION.put("AS", 1);
		IANSWERTRANSACTION.put("DA", 1);
		IANSWERTRANSACTION.put("TI", 1);
	}

}
