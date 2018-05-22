package ehotel.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.menu.Group;
import ehotel.domain.menu.Menu;
import ehotel.sql.eMenuSql;
import ehotel.utils.DataUtils;

public class eMenuDAO extends eBaseDao {
	private static Logger log = Logger.getLogger(eMenuDAO.class);

	@SuppressWarnings("unchecked")
	public Vector<Group> getGroups(int langid, String addressIp) {
		Vector<Group> vVod = new Vector<Group>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(addressIp, SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMenuSql.sqlGetGroups, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadMenuGroup(outParam);
		String outScreen = "[getGroups with params: langid=" + langid
				+ "] : returnValue(size=" + vVod.size() + ")";
		log.info(outScreen);
		return vVod;
	}

	@SuppressWarnings("unchecked")
	public Vector<Menu> getMenuList(int groupId, int langid) {
		Vector<Menu> vVod = new Vector<Menu>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMenuSql.sqlGetMenuList, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadMenu(outParam);
		String outScreen = "[getGroups with params: langid=" + langid
				+ "] : returnValue(size=" + vVod.size() + ")";
		log.info(outScreen);
		return vVod;
	}

	@SuppressWarnings("unchecked")
	public Vector<Menu> getSubMenuList(int menuId, int langid) {
		Vector<Menu> vVod = new Vector<Menu>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subPro = new SubProParam(new java.math.BigDecimal(menuId),
				SubProParam.IN);
		params.add(subPro);

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eMenuSql.sqlGetSubMenuList, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vVod = DataUtils.LoadMenu(outParam);
		String outScreen = "[getGroups with params: langid=" + langid
				+ "] : returnValue(size=" + vVod.size() + ")";
		log.info(outScreen);
		return vVod;
	}

	public static void main(String[] args) {
		eMenuDAO dao = new eMenuDAO();
		Vector<Group> groups = dao.getGroups(2, "172.16.9.36");
		for (Group g : groups) {
			System.out.println(g.toString());
		}
	}
}
