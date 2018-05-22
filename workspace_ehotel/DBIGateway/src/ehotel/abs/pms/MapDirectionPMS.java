package ehotel.abs.pms;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.config.PMSContant;
import ehotel.domain.pms.eLocationMap;
import ehotel.domain.pms.eMenu;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class MapDirectionPMS extends PMSEHotel {

	private static Logger log = Logger.getLogger(MapDirectionPMS.class);

	/**
	 * get all menu map location ( Restaurant,ATMs,Coffe&Bar,Attraction)
	 * 
	 * @param langid
	 * @return
	 */
	public Vector<eMenu> getMenus(int langid) {
		return getMainMenu(PMSContant.MAP_DIRECTION, langid);
	}

	/**
	 * Get infomation of my location (=at hotel)
	 * 
	 * @param langid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public eLocationMap getMyLocation(int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		eLocationMap location = null;

		SubProParam subIFolio = new SubProParam(
				new java.math.BigDecimal(langid), SubProParam.IN);
		params.add(subIFolio);
		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = ("getMyLocation(sql=" + eHotelSql.sqlgetMyLocation
				+ ") with langid=" + langid + "] ");

		Vector<eLocationMap> vLocations = new Vector<eLocationMap>();
		try {
			params = executeSubPro(eHotelSql.sqlgetMyLocation, params);
			log.info(outScreen);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vLocations = DataUtils.LoadMapDirectionLocation(outParam);
		if (vLocations != null && vLocations.size() > 0) {
			location = vLocations.get(0);
		}
		return location;

	}

	/**
	 * get all location of menu
	 * 
	 * @param menuId
	 * @param fro
	 * @param tto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<eLocationMap> getLocations(int menuId, int langid, int fro,
			int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(fro), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eLocationMap> vImages = new Vector<eLocationMap>();
		try {
			params = executeSubPro(eHotelSql.sqlgetLocations, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(4);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadMapDirectionLocation(outParam);
		String outScreen = "[getFolioList(sql=" + eHotelSql.sqlgetLocations
				+ ") with params: from=" + fro + ",tto=" + tto
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	/**
	 * add new location map into menu
	 * 
	 * @param elocation
	 * @param menuId
	 * @return
	 */
	public int addLocaltionMap(eLocationMap elocation, int menuId) {
		int seqAdd = -1;
		if (elocation == null || elocation.getName() == null
				|| elocation.getX() == null) {
			log.info("Input param is null. or [Name,X,Y] is null");
			return seqAdd;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(elocation.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String addres = "";
		if (elocation.getAddress() != null)
			addres = elocation.getAddress();
		subPro = new SubProParam(addres, SubProParam.IN);
		params.add(subPro);// 3

		String phone = "";
		if (elocation.getPhone() != null)
			phone = elocation.getPhone();
		subPro = new SubProParam(phone, SubProParam.IN);
		params.add(subPro);// 4

		String distance = "";
		if (elocation.getDistance() != null)
			distance = elocation.getDistance();
		subPro = new SubProParam(distance, SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(elocation.getX(), SubProParam.IN);
		params.add(subPro);// 6

		subPro = new SubProParam(elocation.getY(), SubProParam.IN);
		params.add(subPro);// 7

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 8

		try {
			params = executeSubPro(eHotelSql.sqladdLocaltionMap, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(7);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addItem (sql=" + eHotelSql.sqladdLocaltionMap
					+ ") with params: " + elocation.toString()
					+ "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	/**
	 * 
	 * @param elocation
	 * @param langid
	 * @return
	 */
	public boolean editLocationMap(eLocationMap elocation, int langid) {
		boolean isEdit = false;
		if (elocation == null || elocation.getName() == null) {
			log.info("Input param is null.[name is null]");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				elocation.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(elocation.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String addres = "";
		if (elocation.getAddress() != null)
			addres = elocation.getAddress();
		subPro = new SubProParam(addres, SubProParam.IN);
		params.add(subPro);// 3

		String phone = "";
		if (elocation.getPhone() != null)
			phone = elocation.getPhone();
		subPro = new SubProParam(phone, SubProParam.IN);
		params.add(subPro);// 4

		String distance = "";
		if (elocation.getDistance() != null)
			distance = elocation.getDistance();
		subPro = new SubProParam(distance, SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 6

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 7

		try {
			params = executeSubPro(eHotelSql.sqleditLocationMap, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isEdit = true;
			}
			String outScreen = "[editLocationMap (sql="
					+ eHotelSql.sqleditLocationMap + ") with params: "
					+ elocation.toString() + "] returnValue(isEdit=" + isEdit
					+ ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getCause());
		}
		return isEdit;
	}

	/**
	 * edit coordinate of location
	 * 
	 * @param localtionId
	 * @param X
	 * @param Y
	 * @return
	 */
	public boolean editCoordinate(int localtionId, String X, String Y) {
		boolean isEdit = false;
		if (X == null || Y == null)
			return isEdit;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				localtionId), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(X, SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(Y, SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4
		try {
			params = executeSubPro(eHotelSql.sqleditCoordinate, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isEdit = true;
			}
			String outScreen = "[editCoordinate (sql="
					+ eHotelSql.sqleditCoordinate
					+ ") with params:localtionId= " + localtionId + ",X=" + X
					+ ",Y=" + Y + "] returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isEdit;
	}

	public boolean removeLocation(int locationId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				locationId), SubProParam.IN);
		params.add(subPro);// 1
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4
		try {
			params = executeSubPro(eHotelSql.sqlremoveLocationMap, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString().trim()) > 0)
					isRemove = true;
			}
			String outScreen = "[removeLocation (sql="
					+ eHotelSql.sqlremoveLocationMap
					+ ") with params:localtionId= " + locationId
					+ "] returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return isRemove;
	}

	@SuppressWarnings("unchecked")
	public Vector<eLocationMap> searchLocation(int menuId, String searchCode,
			int langId, int fro, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(searchCode, SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(fro), SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		Vector<eLocationMap> vImages = new Vector<eLocationMap>();
		try {
			params = executeSubPro(eHotelSql.sqlsearchLocation, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(5);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadMapDirectionLocation(outParam);
		String outScreen = "[getFolioList(sql=" + eHotelSql.sqlsearchLocation
				+ ") with params: from=" + fro + ",tto=" + tto
				+ "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	/**
	 * Count location in a menu
	 * 
	 * @param menuId
	 * @return
	 */
	public int countItem(int menuId) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountLocationMap, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[CountLocationMap(sql="
					+ eHotelSql.sqlCountLocationMap
					+ ") with no params returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	public int countItemSearch(int menuId, String searchCode) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(searchCode, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqlCountSearchLocationMap, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				count = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "countItemSearch(sql="
					+ eHotelSql.sqlCountSearchLocationMap
					+ ") with  params [menuId=" + menuId + ",searchCode="
					+ searchCode + ",returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}
}
