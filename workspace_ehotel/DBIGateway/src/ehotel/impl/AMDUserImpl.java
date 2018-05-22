package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eUserDao;
import ehotel.domain.group.eCategory;
import ehotel.domain.group.eGroup;
import ehotel.domain.group.eRole;
import ehotel.domain.group.eUser;
import ehotel.inter.IUser;

public class AMDUserImpl implements IUser {
	private eUserDao eDao;

	public AMDUserImpl() {
		eDao = new eUserDao();
	}

	@Override
	public Vector<eGroup> getGroupList(int langid) {
		// TODO Auto-generated method stub
		return eDao.getGroupList(langid);
	}

	@Override
	public eGroup getGroupInfo(int groupId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getGroupInfo(groupId, langid);
	}

	@Override
	public int addGroup(eGroup group) {
		// TODO Auto-generated method stub
		return eDao.addGroup(group);
	}

	@Override
	public boolean editGroup(eGroup group, int langid) {
		// TODO Auto-generated method stub
		return eDao.editGroup(group, langid);
	}

	@Override
	public boolean removeGroup(int groupId) {
		// TODO Auto-generated method stub
		return eDao.removeGroup(groupId);
	}

	@Override
	public Vector<eRole> getRolesInGroup(int groupId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getRolesInOutGroup(groupId, langid, "IN");
	}

	@Override
	public Vector<eRole> getRolesOutGroup(int groupId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getRolesInOutGroup(groupId, langid, "OUT");
	}

	@Override
	public boolean addRoleIntoGroup(int groupId, String str_cate_id) {
		// TODO Auto-generated method stub
		return eDao.addRoleIntoGroup(groupId, str_cate_id);
	}

	@Override
	public Vector<eUser> getUsers(int groupId, int fro, int tto) {
		// TODO Auto-generated method stub
		return eDao.getUsers(groupId, fro, tto);
	}

	@Override
	public eUser getUserInfo(int userId) {
		return eDao.getUserInfo(userId);
	}

	@Override
	public int addUser(int groupId, eUser user) {
		return eDao.addUser(groupId, user);
	}

	@Override
	public boolean editUser(eUser user) {
		return eDao.editUser(user);
	}

	@Override
	public boolean removeUser(int groupId, int userId) {
		return eDao.removeUser(groupId, userId);
	}

	@Override
	public boolean changeGroup(int userId, String str_group_id) {
		// TODO Auto-generated method stub
		return eDao.changeGroup(userId, str_group_id);
	}

	@Override
	public boolean changeActive(int userId) {
		// TODO Auto-generated method stub
		return eDao.changeActive(userId);
	}

	@Override
	public boolean changePassword(int userId, String newPass) {
		// TODO Auto-generated method stub
		return eDao.changePassword(userId, newPass);
	}

	@Override
	public boolean addRoleIntoUser(int userId, String str_role_id) {
		// TODO Auto-generated method stub
		return eDao.addRoleIntoUser(userId, str_role_id);
	}

	@Override
	public Vector<eRole> getRolesInUser(int userId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getRolesInOutUser(userId, langid, "IN");
	}

	@Override
	public Vector<eRole> getRolesOutUser(int userId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getRolesInOutUser(userId, langid, "OUT");
	}

	@Override
	public Vector<eCategory> getCategoryList(int langid) {
		// TODO Auto-generated method stub
		return eDao.getCategoryList(langid);
	}

	@Override
	public eCategory getCategoryInfo(int cateId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getCategoryInfo(cateId, langid);
	}

	@Override
	public int addCategory(int parentId, eCategory cate) {
		// TODO Auto-generated method stub
		return eDao.addCategory(parentId, cate);
	}

	@Override
	public boolean removeCategory(int cateId) {
		// TODO Auto-generated method stub
		return eDao.removeCategory(cateId);
	}

	@Override
	public boolean editCategory(eCategory cate, int langid) {
		// TODO Auto-generated method stub
		return eDao.editCategory(cate, langid);
	}

	@Override
	public Vector<eRole> getRolesInCate(int cateId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getRolesInOutCate(cateId, langid, "IN");
	}

	@Override
	public Vector<eRole> getRolesOutCate(int cateId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getRolesInOutCate(cateId, langid, "OUT");
	}

	@Override
	public boolean addRoleIntoCate(int cateId, String str_role_id) {
		// TODO Auto-generated method stub
		return eDao.addRoleIntoCate(cateId, str_role_id);
	}

	@Override
	public Vector<eRole> getRoleList(int langid) {
		// TODO Auto-generated method stub
		return eDao.getRoleList(langid);
	}

	@Override
	public int countUsers(int groupId) {
		// TODO Auto-generated method stub
		return eDao.countUsers(groupId);
	}

	@Override
	public Vector<eGroup> getGroupsInUser(int userId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getGroupsInOutUser(userId, langid, "IN");
	}

	@Override
	public Vector<eGroup> getGroupsOutUser(int userId, int langid) {
		// TODO Auto-generated method stub
		return eDao.getGroupsInOutUser(userId, langid, "OUT");

	}

}
