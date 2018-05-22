package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.pms.eBillCharge;
import ehotel.domain.pms.eFolio;
import ehotel.domain.pms.eGuest;
import ehotel.domain.pms.eMessage;
import ehotel.domain.pms.eSTB;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class FolioStbPMS extends PMSEHotel {
	private static Logger log = Logger.getLogger(FolioStbPMS.class);

	/**
	 * get all room
	 * 
	 * @param from
	 * @param tto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<eFolio> getFolioList(int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(from),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eFolio> vImages = new Vector<eFolio>();
		try {
			params = executeSubPro(eHotelSql.sqlGetFolioList, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadFolios(outParam);
		String outScreen = "[getFolioList(sql=" + eHotelSql.sqlGetFolioList
				+ ") with params: from=" + from + ",tto=" + tto
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public Vector<eFolio> searchFolio(String searchCode, int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(searchCode, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eFolio> vImages = new Vector<eFolio>();
		try {
			params = executeSubPro(eHotelSql.sqlsearchFolio, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadFolios(outParam);
		String outScreen = "[getFolioList(sql=" + eHotelSql.sqlsearchFolio
				+ ") with params:searchCode=" + searchCode + ", from=" + from
				+ ",tto=" + tto + "] : returnValue(size=" + vImages.size()
				+ ")";
		log.info(outScreen);
		return vImages;
	}

	public int countSearchFolio(String searchCode) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(searchCode, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountSearchFolio, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countSearchFolio(sql="
					+ eHotelSql.sqlCountSearchFolio
					+ ") with no params returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	/**
	 * Get all guest in house at room
	 * 
	 * @param folioNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<eGuest> getGuests(int folioNum) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eGuest> vImages = new Vector<eGuest>();
		try {
			params = executeSubPro(eHotelSql.sqlGetGuests, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadGuests(outParam);
		String outScreen = "[getGuests(sql=" + eHotelSql.sqlGetGuests
				+ ") with params: folioCode=" + folioNum
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	/**
	 * Get all settop box.
	 * 
	 * @param folio
	 *            If folio=-1. get all stb.Otherwise,get all stb which is not
	 *            setup for folio.
	 * @param from
	 * @param tto
	 * @return
	 */
	public Vector<eSTB> getSTBListOut(int folioNum, int from, int tto) {
		return getSTBList(folioNum, from, tto, "OUT");
	}

	/**
	 * get all settop box in room
	 * 
	 * @param folioNum
	 * @return
	 */
	public Vector<eSTB> getSTBListIn(int folioNum) {
		return getSTBList(folioNum, -1, -1, "IN");
	}

	public Vector<eSTB> getAllSTB(int from, int tto) {
		return getSTBList(-1, from, tto, "OUT");
	}

	@SuppressWarnings("unchecked")
	private Vector<eSTB> getSTBList(int folioNum, int from, int tto, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eSTB> vImages = new Vector<eSTB>();
		try {
			params = executeSubPro(eHotelSql.sqlGetSTBList, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadSTBList(outParam);
		String outScreen = "[getGuests(sql=" + eHotelSql.sqlGetSTBList
				+ ") with params: from=" + from + ",tto=" + tto
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	/**
	 * Setup STB into Folio.
	 * 
	 * @param smartcard
	 * @param folioNum
	 * @return
	 */
	public String addSTBIntoFolio(String smartcard, int folioNum) {
		return addOrUpdateSBTIntoFolio(smartcard, folioNum, "ADD");
	}

	/**
	 * Update new STB into Folio
	 * 
	 * @param smartcard
	 *            The param is a new STB which set up into Folio again.
	 * @param folioNum
	 * @return
	 */
	public String editSTBIntoFolio(String smartcard, int folioNum) {
		return addOrUpdateSBTIntoFolio(smartcard, folioNum, "EDIT");
	}

	private String addOrUpdateSBTIntoFolio(String smartcard, int folioNum,
			String type) {
		String answer = "Not Success";
		if (smartcard == null) {
			log.info("Input param[smartcard] is null.");
			return answer;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(smartcard, SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(folioNum),
				SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3
		if (type != null & type.equals("ADD"))
			SqlQuery = eHotelSql.sqladdSBTIntoFolio;
		else
			SqlQuery = eHotelSql.sqlEditSBTIntoFolio;
		try {
			params = executeSubPro(SqlQuery, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				String code = paramOUT.getString();
				if (code != null)
					answer = code;

			}
			String outScreen = "[addOrUpdateSBTIntoFolio (sql=" + SqlQuery
					+ ") with params: smartcard=" + smartcard + ",folioNum="
					+ folioNum + "]  : returnValue(answer=" + answer + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return answer;
	}

	/**
	 * Remove setup STB out Folio
	 * 
	 * @param smartcard
	 * @return
	 */
	public boolean removeSTBOutFolio(String smartcard) {
		boolean isRemove = false;
		if (smartcard == null) {
			log.info("Input param[smartcard] is null.");
			return isRemove;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(smartcard, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqlremoveSTBOutFolio, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isRemove = true;
			}
			String outScreen = "[addItem (sql="
					+ eHotelSql.sqlremoveSTBOutFolio
					+ ") with params: smartcard=" + smartcard
					+ "]  : returnValue(isAdd=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	/**
	 * checkout guest out room
	 * 
	 * @param guestId
	 * @return
	 */
	public boolean checkOutGuest(int guestId) {
		boolean isCheckOut = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(guestId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2
		try {
			params = executeSubPro(eHotelSql.sqlcheckOutGuest, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isCheckOut = true;
			}
			String outScreen = "[checkOutGuest (sql="
					+ eHotelSql.sqlcheckOutGuest + ") with params: guestId="
					+ guestId + "]  : returnValue(isCheckOut=" + isCheckOut
					+ ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}

		return isCheckOut;
	}

	/**
	 * checkout all guest out room( checkout room)
	 * 
	 * @param folioCode
	 * @return
	 */
	public boolean checkOutFolio(int folioCode) {
		boolean isCheckOut = false;

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioCode), SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		String outScreen = "[checkOutFolio (sql=" + eHotelSql.sqlcheckOutFolio
				+ ") with params: folioCode=" + folioCode;
		log.info(outScreen);
		try {

			params = executeSubPro(eHotelSql.sqlcheckOutFolio, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isCheckOut = true;
			}

			outScreen = "--> returnValue(isCheckOut=" + isCheckOut + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isCheckOut;
	}

	/**
	 * reset new pincode for guest
	 * 
	 * @param folioCode
	 * @param newPinCode
	 * @param accountUser
	 * @return
	 */
	public boolean resetPincode(int folioCode, int newPinCode,
			String accountUser) {
		boolean isUpdate = false;

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioCode), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(newPinCode),
				SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(accountUser, SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlresetPincode, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isUpdate = true;
			}
			String outScreen = "[resetPincode (sql="
					+ eHotelSql.sqlresetPincode + ") with params: folioCode="
					+ folioCode + ",accountUser=" + accountUser
					+ "]  : returnValue(isUpdate=" + isUpdate + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isUpdate;
	}

	/**
	 * Count the number of rooms
	 * 
	 * @return
	 */
	public int countFolio() {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountFolio, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countFolio(sql=" + eHotelSql.sqlCountFolio
					+ ") with no params returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	/**
	 * Count the number of STb
	 * 
	 * @param folioNum
	 *            Neu folioNum=-1 dem tat ca stb trong he thong.Nguoc lai,dem
	 *            tat ca stb khong thuoc phong
	 * @return
	 */
	public int countSTB(int folioNum) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountSTB, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[countSTB(sql=" + eHotelSql.sqlCountSTB
					+ ") with no params returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	/**
	 * Count the number of row in Bill
	 * 
	 * @param folioNum
	 * @return
	 */
	public int countBill(int folioNum) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlcountBill, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "[countFolio(sql=" + eHotelSql.sqlcountBill
					+ ") with no params returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	/**
	 * search stb by code
	 * 
	 * @param folioNum
	 * @param seachCode
	 * @param from
	 * @param tto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<eSTB> searchSTB(int folioNum, String seachCode, int from,
			int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(seachCode, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eSTB> vImages = new Vector<eSTB>();
		try {
			params = executeSubPro(eHotelSql.sqlsearchSTB, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadSTBList(outParam);
		String outScreen = "[searchSTB(sql=" + eHotelSql.sqlsearchSTB
				+ ") with params: from=" + from + ",tto=" + tto
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	public int countSearchSTB(int folioNum, String seachCode) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(seachCode, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlcountSearchSTB, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				count = Utils.parseInt(paramOUT.getString().trim());

			}
			String outScreen = "countSearchSTB(sql="
					+ eHotelSql.sqlcountSearchSTB + ") with  params [folioNum="
					+ folioNum + ",seachCode=" + seachCode
					+ "] -->returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	/**
	 * GET BILL of room
	 * 
	 * @param folioNum
	 * @param from
	 * @param tto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<eBillCharge> getBillCharges(int folioNum, int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eBillCharge> vImages = new Vector<eBillCharge>();
		try {
			params = executeSubPro(eHotelSql.sqlgetBillCharges, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadBillCharges(outParam);
		String outScreen = "[getBillCharges(sql=" + eHotelSql.sqlgetBillCharges
				+ ") with params:folioNum=" + folioNum + ", from=" + from
				+ ",tto=" + tto + "] : returnValue(size=" + vImages.size()
				+ ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public Vector<eMessage> getMessages(int folioNum, int from, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(folioNum), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(from), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eMessage> vImages = new Vector<eMessage>();
		try {
			params = executeSubPro(eHotelSql.sqlgetMessages, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadMessages(outParam);
		String outScreen = "[searchSTB(sql=" + eHotelSql.sqlgetMessages
				+ ") with params:folioNum=" + folioNum + ", from=" + from
				+ ",tto=" + tto + "] : returnValue(size=" + vImages.size()
				+ ")";
		log.info(outScreen);
		return vImages;
	}
	
	public static void main(String[] args) {
		FolioStbPMS f = new FolioStbPMS();
//		System.out.println(f.countBill(1245));
		System.out.println(f.getBillCharges(1245, 0, 6));
	}

}
