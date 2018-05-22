package ehotel.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.Encryptor;

import ehotel.domain.group.eCategory;
import ehotel.domain.group.eGroup;
import ehotel.domain.group.eRole;
import ehotel.domain.group.eUser;
import ehotel.sql.eHotelSql;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class eUserDao extends eBaseDao {
	private static Logger log = Logger.getLogger(eUserDao.class);

	@SuppressWarnings("unchecked")
	public Vector<eGroup> getGroupList(int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<eGroup> vImages = new Vector<eGroup>();
		try {
			params = executeSubPro(eHotelSql.sqlgetGroupUserList, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadGroups(outParam);
		String outScreen = "[getFolioList(sql=" + eHotelSql.sqlgetGroupUserList
				+ ") with params: langid=" + langid + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public eGroup getGroupInfo(int groupId, int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		eGroup group = null;
		Vector<eGroup> vImages = new Vector<eGroup>();
		String outScreen = "[getGroupInfo(sql=" + eHotelSql.sqlgetGroupInfo
				+ ") with params: groupId=" + groupId + "] ";
		log.info(outScreen);

		try {
			params = executeSubPro(eHotelSql.sqlgetGroupInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadGroups(outParam);
		if (vImages != null && vImages.size() > 0) {
			group = vImages.get(0);
			outScreen = "--> returnValue(size=" + vImages.size() + ")";
			log.info(outScreen);
		}
		return group;
	}

	public int addGroup(eGroup group) {
		int seqAdd = -1;
		if (group == null || group.getName() == null) {
			log.info("Param input[group] is null.");
			return seqAdd;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(group.getName(), SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 8

		try {
			params = executeSubPro(eHotelSql.sqladdGroup, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addGroup (sql=" + eHotelSql.sqladdGroup
					+ ") with params: " + group.toString()
					+ "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean editGroup(eGroup group, int langid) {
		boolean isEdit = false;
		if (group == null || group.getName() == null) {
			log.info("Param input[group] is null.");
			return isEdit;
		}
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				group.getGroupId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(group.getName(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 8

		try {
			params = executeSubPro(eHotelSql.sqleditGroup, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isEdit = true;
				else if (Utils.parseInt(paramOUT.getString()) == 2)
					log.info("Name is duplicate");
			}
			String outScreen = "[editGroup (sql=" + eHotelSql.sqleditGroup
					+ ") with params: " + group.toString()
					+ "]  : returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isEdit;
	}

	public boolean removeGroup(int groupId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);
		params.add(subPro);// 1
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlremoveGroup, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isRemove = true;

			}
			String outScreen = "[removeGroup (sql=" + eHotelSql.sqlremoveGroup
					+ ") with params: groupId=" + groupId
					+ "]  : returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean addRoleIntoGroup(int groupId, String str_role_id) {
		boolean isAdd = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(str_role_id, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqladdRoleIntoGroup, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isAdd = true;
			}
			String outScreen = "[addRoleIntoUser (sql="
					+ eHotelSql.sqladdRoleIntoUser + ") with params: groupId="
					+ groupId + ",str_role_id=" + str_role_id
					+ "]  : returnValue(isAdd=" + isAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isAdd;
	}

	@SuppressWarnings("unchecked")
	public Vector<eRole> getRolesInOutGroup(int groupId, int langid, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<eRole> vImages = new Vector<eRole>();
		try {
			params = executeSubPro(eHotelSql.sqlgetRolesInOutGroup, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadRoles(outParam);
		String outScreen = "[getRolesInGroup(sql="
				+ eHotelSql.sqlgetRolesInOutGroup + ") with params: groupId="
				+ groupId + "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public Vector<eUser> getUsers(int groupId, int fro, int tto) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(fro), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 4

		Vector<eUser> vImages = new Vector<eUser>();
		try {
			params = executeSubPro(eHotelSql.sqlgetUsers, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadUsers(outParam);
		String outScreen = "[getUsers(sql=" + eHotelSql.sqlgetUsers
				+ ") with params: groupId=" + groupId + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public eUser getUserInfo(int userId) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(userId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		eUser user = null;
		Vector<eUser> vImages = new Vector<eUser>();
		String outScreen = "[getUserInfo(sql=" + eHotelSql.sqlgetUserInfo
				+ ") with params: userId=" + userId + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		try {
			params = executeSubPro(eHotelSql.sqlgetUserInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadUsers(outParam);
		if (vImages.size() > 0) {
			user = vImages.get(0);
		}
		outScreen = "-->returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return user;
	}

	public int addUser(int groupId, eUser user) {
		int seqAdd = -1;
		if (user == null || user.getName() == null) {
			log.info("Param input[user] is null.");
			return seqAdd;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(user.getName(), SubProParam.IN);
		params.add(subPro);// 2

		String address = "";
		if (user.getAddress() != null)
			address = user.getAddress();
		subPro = new SubProParam(address, SubProParam.IN);
		params.add(subPro);// 3

		String department = "";
		if (user.getDepartment() != null)
			department = user.getDepartment();
		subPro = new SubProParam(department, SubProParam.IN);
		params.add(subPro);// 4

		subPro = new SubProParam(user.getAccount(), SubProParam.IN);
		params.add(subPro);// 5

		subPro = new SubProParam(Encryptor.encryptPassword(user.getPasswd()),
				SubProParam.IN);
		params.add(subPro);// 6

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 7

		try {
			params = executeSubPro(eHotelSql.sqladdUser, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addUser (sql=" + eHotelSql.sqladdUser
					+ ") with params: " + user.toString()
					+ "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean editUser(eUser user) {
		boolean isEdit = false;
		if (user == null || user.getName() == null) {
			log.info("Param input[user] is null.");
			return isEdit;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				user.getId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(user.getName(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(user.getAddress(), SubProParam.IN);
		params.add(subPro);// 3

		subPro = new SubProParam(user.getDepartment(), SubProParam.IN);
		params.add(subPro);// 4

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 5

		try {
			params = executeSubPro(eHotelSql.sqleditUser, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isEdit = true;
			}
			String outScreen = "[editUser (sql=" + eHotelSql.sqleditUser
					+ ") with params: " + user.toString()
					+ "]  : returnValue(isEdit=" + isEdit + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isEdit;
	}

	public boolean removeUser(int groupId, int userId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);// 0
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(userId),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlremoveUser, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isRemove = true;

			}
			String outScreen = "[removeUser (sql=" + eHotelSql.sqlremoveUser
					+ ") with params: userId=" + userId
					+ "]  : returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	public boolean changeActive(int userId) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(userId),
				SubProParam.IN);
		params.add(subPro);// 1
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlchangeActive, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isChange = true;

			}
			String outScreen = "[changeActive (sql="
					+ eHotelSql.sqlchangeActive + ") with params: userId="
					+ userId + "]  : returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isChange;
	}

	public boolean changeGroup(int userId, String str_group_id) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(userId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(str_group_id, SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqlchangeGroup, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isChange = true;

			}
			String outScreen = "[changeGroup (sql=" + eHotelSql.sqlchangeGroup
					+ ") with params: userId=" + userId + ",str_group_id="
					+ str_group_id + "]  : returnValue(isChange=" + isChange
					+ ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isChange;
	}

	public boolean changePassword(int userId, String newPass) {
		boolean isChange = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(userId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(Encryptor.encryptPassword(newPass),
				SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqlchangePassword, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isChange = true;

			}
			String outScreen = "[changePassword (sql="
					+ eHotelSql.sqlchangePassword + ") with params: userId="
					+ userId + ",newPass=" + newPass
					+ "]  : returnValue(isChange=" + isChange + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isChange;
	}

	public boolean addRoleIntoUser(int userId, String str_role_id) {
		boolean isAdd = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(userId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(str_role_id, SubProParam.IN);
		params.add(subPro);// 2
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 3

		try {
			params = executeSubPro(eHotelSql.sqladdRoleIntoUser, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isAdd = true;
			}
			String outScreen = "[addRoleIntoUser (sql="
					+ eHotelSql.sqladdRoleIntoUser + ") with params: userId="
					+ userId + ",str_role_id=" + str_role_id
					+ "]  : returnValue(isAdd=" + isAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isAdd;
	}

	@SuppressWarnings("unchecked")
	public Vector<eRole> getRolesInOutUser(int userId, int langid, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(userId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<eRole> vImages = new Vector<eRole>();
		try {
			params = executeSubPro(eHotelSql.sqlgetRolesInOutUser, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadRoles(outParam);
		String outScreen = "[getRolesInOutUser(sql="
				+ eHotelSql.sqlgetRolesInOutUser + ") with params: userId="
				+ userId + "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public Vector<eGroup> getGroupsInOutUser(int userId, int langid, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(userId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 4

		Vector<eGroup> vImages = new Vector<eGroup>();
		try {
			params = executeSubPro(eHotelSql.sqlgetGroupsInOutUser, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadGroups(outParam);
		String outScreen = "[getGroupsInOutUser(sql="
				+ eHotelSql.sqlgetGroupsInOutUser + ") with params: userId="
				+ userId + ",type=" + type + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	// nhom quyen
	@SuppressWarnings("unchecked")
	public Vector<eCategory> getCategoryList(int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<eCategory> vImages = new Vector<eCategory>();
		try {
			params = executeSubPro(eHotelSql.sqlgetCategoryList, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadCategories(outParam);
		String outScreen = "[getCategoryList(sql="
				+ eHotelSql.sqlgetCategoryList + ") with params: langid="
				+ langid + "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	@SuppressWarnings("unchecked")
	public eCategory getCategoryInfo(int cateId, int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(cateId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 3

		eCategory cate = null;
		Vector<eCategory> vImages = new Vector<eCategory>();
		String outScreen = "[getUserInfo(sql=" + eHotelSql.sqlgetCategoryInfo
				+ ") with params: cateId=" + cateId + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		try {
			params = executeSubPro(eHotelSql.sqlgetCategoryInfo, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadCategories(outParam);
		if (vImages.size() > 0) {
			cate = vImages.get(0);
		}
		outScreen = "-->returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return cate;
	}

	public int addCategory(int parentId, eCategory cate) {
		int seqAdd = -1;
		if (cate == null || cate.getName() == null) {
			log.info("Param input[cate] is null.");
			return seqAdd;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(
				new java.math.BigDecimal(parentId), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(cate.getName(), SubProParam.IN);
		params.add(subPro);// 2

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 7

		try {
			params = executeSubPro(eHotelSql.sqladdCategory, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				seqAdd = Utils.parseInt(paramOUT.getString());
			}
			String outScreen = "[addUser (sql=" + eHotelSql.sqladdCategory
					+ ") with params: " + cate.toString()
					+ "]  : returnValue(seq=" + seqAdd + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return seqAdd;
	}

	public boolean editCategory(eCategory cate, int langid) {
		boolean isEdit = false;
		if (cate == null || cate.getName() == null) {
			log.info("Param input[cate] is null.");
			return isEdit;
		}

		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(
				cate.getCateId()), SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(cate.getName(), SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 4

		try {
			params = executeSubPro(eHotelSql.sqleditCategory, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				if (Utils.parseInt(paramOUT.getString()) > 0)
					isEdit = true;
			}
			String outScreen = "[editCategory (sql="
					+ eHotelSql.sqleditCategory + ") with params: "
					+ cate.toString() + "]  : returnValue(isEdit=" + isEdit
					+ ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isEdit;
	}

	public boolean removeCategory(int cateId) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(cateId),
				SubProParam.IN);
		params.add(subPro);// 1
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlremoveCategory, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isRemove = true;

			}
			String outScreen = "[removeCategory (sql="
					+ eHotelSql.sqlremoveCategory + ") with params: cateId="
					+ cateId + "]  : returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	@SuppressWarnings("unchecked")
	public Vector<eRole> getRolesInOutCate(int cateId, int langid, String type) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(cateId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 2

		subPro = new SubProParam(type, SubProParam.IN);
		params.add(subPro);// 3

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<eRole> vImages = new Vector<eRole>();
		try {
			params = executeSubPro(eHotelSql.sqlgetRolesInOutCate, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(3);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadRoles(outParam);
		String outScreen = "[getRolesInOutCate(sql="
				+ eHotelSql.sqlgetRolesInOutCate + ") with params: cateId="
				+ cateId + "] : returnValue(size=" + vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	public boolean addRoleIntoCate(int cateId, String str_role_id) {
		boolean isRemove = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(cateId),
				SubProParam.IN);
		params.add(subPro);// 1

		subPro = new SubProParam(str_role_id, SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqladdRoleIntoCate, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				if (Utils.parseInt(paramOUT.getString()) == 1)
					isRemove = true;

			}
			String outScreen = "[addRoleIntoCate (sql="
					+ eHotelSql.sqladdRoleIntoCate + ") with params: cateId="
					+ cateId + ",str_role_id=" + str_role_id
					+ "]  : returnValue(isRemove=" + isRemove + ")";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return isRemove;
	}

	@SuppressWarnings("unchecked")
	public Vector<eRole> getRoleList(int langid) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(new java.math.BigDecimal(langid),
				SubProParam.IN);
		params.add(subPro);// 1

		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);// 2

		Vector<eRole> vImages = new Vector<eRole>();
		try {
			params = executeSubPro(eHotelSql.sqlgetRoleList, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vImages = DataUtils.LoadRoles(outParam);
		String outScreen = "[getRoleList(sql=" + eHotelSql.sqlgetRoleList
				+ ") with params: langid=" + langid + "] : returnValue(size="
				+ vImages.size() + ")";
		log.info(outScreen);
		return vImages;
	}

	public int countUsers(int groupId) {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new java.math.BigDecimal(groupId),
				SubProParam.IN);
		params.add(subOut);// 2

		subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2

		try {
			params = executeSubPro(eHotelSql.sqlCountUser, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
			String outScreen = "[countUsers(sql=" + eHotelSql.sqlCountUser
					+ ") with no params -->returnValue=" + count + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return count;
	}

	public boolean login(String account, String passwd, String markAddress) {
		boolean islogin = false;
		int userId = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(account, SubProParam.IN);
		params.add(subparam);// 1

		subparam = new SubProParam(Encryptor.encryptPassword(passwd),
				SubProParam.IN);
		params.add(subparam);// 2

		subparam = new SubProParam(markAddress, SubProParam.IN);
		params.add(subparam);// 3

		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 4

		try {
			params = executeSubPro(eHotelSql.sqllogin, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				userId = Utils.parseInt(paramOUT.getString().trim());
				if (userId > 0)
					islogin = true;
			}
			String outScreen = "[login(sql=" + eHotelSql.sqllogin
					+ ")  -->returnValue=" + islogin + ",userId=" + userId
					+ "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return islogin;
	}

	public void logout(String markAddress) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(markAddress, SubProParam.IN);
		params.add(subparam);// 1

		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 4
		try {
			params = executeSubPro(eHotelSql.sqllogout, params);
			String outScreen = "[logout(sql=" + eHotelSql.sqllogout
					+ ") with param[markAddress=" + markAddress + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause().toString());
		}
	}

	public boolean isAdmin(String markAdrress) {
		boolean isAdmin = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(markAdrress, SubProParam.IN);
		params.add(subparam);// 1

		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 2

		try {
			params = executeSubPro(eHotelSql.sqlisadmin, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret > 0) {
					isAdmin = true;
				}
			}
			String outScreen = "[isAdmin(sql=" + eHotelSql.sqllogin
					+ ") with no params -->returnValue=" + isAdmin + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
			ex.printStackTrace();
		}
		return isAdmin;
	}

	public boolean checkRoleUser(String markAddress, String role) {
		boolean isCheck = false;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(markAddress, SubProParam.IN);
		params.add(subparam);// 1

		subparam = new SubProParam(role, SubProParam.IN);
		params.add(subparam);// 2

		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 3

		try {
			params = executeSubPro(eHotelSql.sqlcheckRoleUser, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				int ret = Utils.parseInt(paramOUT.getString());
				if (ret == 1)
					isCheck = true;
			}
			String outScreen = "[checkRoleUser(sql="
					+ eHotelSql.sqlcheckRoleUser
					+ ") with no params -->returnValue=" + isCheck + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isCheck;
	}

	public String getLoginUserName(String markAddress) {
		String userName = "";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(markAddress, SubProParam.IN);
		params.add(subparam);// 1

		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 2

		try {
			params = executeSubPro(eHotelSql.sqlgetLoginUserName, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				userName = paramOUT.getString();

			}
			String outScreen = "[isAdmin(sql=" + eHotelSql.sqlgetLoginUserName
					+ ") with no params -->returnValue=" + userName + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause());
		}
		return userName;
	}

	public boolean isLogedinByAnotherUser(String markAddress) {
		boolean isCheck = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(markAddress, SubProParam.IN);
		params.add(subparam);// 1

		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 2

		try {
			params = executeSubPro(eHotelSql.sqlisLogedinByAnotherUser, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				int ret = Utils.parseInt(paramOUT.getString().trim());
				if (ret == 1)
					isCheck = false;
			}
			String outScreen = "[isAdmin(sql="
					+ eHotelSql.sqlisLogedinByAnotherUser
					+ ") with no params -->returnValue=" + isCheck + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause().toString());
		}
		return isCheck;
	}

	public String getHrefPageOfUser(String markAddress) {
		String href = null;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subparam = new SubProParam(markAddress, SubProParam.IN);
		params.add(subparam);// 1

		subparam = new SubProParam(new String(), SubProParam.OUT);
		params.add(subparam);// 2

		try {
			params = executeSubPro(eHotelSql.sqlgetHrefPageOfUser, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(1);
				href = paramOUT.getString().trim();
			}
			String outScreen = "[isAdmin(sql=" + eHotelSql.sqlgetHrefPageOfUser
					+ ") with params[markAddress=" + markAddress
					+ "] -->returnValue=" + href + "]";
			log.info(outScreen);
		} catch (Exception ex) {
			log.error(ex.getCause().toString());
		}
		return href;
	}
}
