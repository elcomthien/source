package ehotel.inter;

import java.util.Vector;

import ehotel.domain.group.eCategory;
import ehotel.domain.group.eGroup;
import ehotel.domain.group.eRole;
import ehotel.domain.group.eUser;

public interface IUser {
	// group
	/**
	 * Lay danh sach nhom user
	 * 
	 * @param langid
	 * @return
	 */
	public Vector<eGroup> getGroupList(int langid);

	/**
	 * Lay thong tin nhom user
	 * 
	 * @param groupId
	 * @param langid
	 * @return
	 */
	public eGroup getGroupInfo(int groupId, int langid);

	/**
	 * Tao moi nhom user
	 * 
	 * @param group
	 * @return seq trong table. 1=thanh cong; -1=khong thanh cong,-2= trung ten
	 */
	public int addGroup(eGroup group);

	/**
	 * Thay doi thong tin nhom user
	 * 
	 * @param group
	 * @param langid
	 * @return
	 */
	public boolean editGroup(eGroup group, int langid);

	/**
	 * Xoa nhom user
	 * 
	 * @param groupId
	 * @return
	 */
	public boolean removeGroup(int groupId);

	/**
	 * Lay danh sach quyen co trong nhom user
	 * 
	 * @param groupId
	 * @param langid
	 * @return
	 */
	public Vector<eRole> getRolesInGroup(int groupId, int langid);

	/**
	 * Lay ds quyen ma nhom user chua co
	 * 
	 * @param groupId
	 * @param langid
	 * @return
	 */
	public Vector<eRole> getRolesOutGroup(int groupId, int langid);

	/**
	 * Gan quyen cho nhom user
	 * 
	 * @param groupId
	 * @param str_role_id
	 *            : la chuoi cac id role,cach nhau boi dau phay. Vd: (1,23,4,5)
	 * @return
	 */
	public boolean addRoleIntoGroup(int groupId, String str_role_id);

	// user
	/**
	 * Lay ds user trong nhom
	 * 
	 * @param groupId
	 * @param fro
	 * @param tto
	 * @return
	 */
	public Vector<eUser> getUsers(int groupId, int fro, int tto);

	/**
	 * Lay thong tin user
	 * 
	 * @param userId
	 * @return
	 */
	public eUser getUserInfo(int userId);

	/**
	 * Tao moi user vao trong nhom
	 * 
	 * @param groupId
	 * @param user
	 * @return seq trong table. -1=khong thanh cong,-2= trung account
	 */
	public int addUser(int groupId, eUser user);

	/**
	 * Thay doi thong tin user
	 * 
	 * @param user
	 * @return
	 */
	public boolean editUser(eUser user);

	/**
	 * Xoa user
	 * 
	 * @param groupId
	 *            GoupId=-1 thi xoa ra khoi he thong. GroupId>0 thi xoa khoi
	 *            group van ton tai trong he thong
	 * @param userId
	 * @return
	 */
	public boolean removeUser(int groupId, int userId);

	/**
	 * Set mat khau cho user.Neu oldPass=null, admin reset lai cho cac user khac
	 * 
	 * @param userId
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	public boolean changePassword(int userId, String newPass);

	/**
	 * Thay doi nhom cho user
	 * 
	 * @param userId
	 * @param str_group_id
	 * @return
	 */
	public boolean changeGroup(int userId, String str_group_id);

	public Vector<eGroup> getGroupsInUser(int userId, int langid);

	public Vector<eGroup> getGroupsOutUser(int userId, int langid);

	/**
	 * Chuyen doi trang thai active-deactive
	 * 
	 * @param userId
	 * @return
	 */
	public boolean changeActive(int userId);

	/**
	 * Gan quyen cho user
	 * 
	 * @param userId
	 * @param str_role_id
	 * @return
	 */
	public boolean addRoleIntoUser(int userId, String str_role_id);

	/**
	 * Lay ds quyen ma user dang co
	 * 
	 * @param userId
	 * @param langid
	 * @return
	 */
	public Vector<eRole> getRolesInUser(int userId, int langid);

	/**
	 * Lay ds quyen ma user chua co
	 * 
	 * @param userId
	 * @param langid
	 * @return
	 */
	public Vector<eRole> getRolesOutUser(int userId, int langid);

	// category
	/**
	 * lay ds nhom quyen
	 * 
	 * @param langid
	 * @return
	 */
	public Vector<eCategory> getCategoryList(int langid);

	/**
	 * 
	 * @param cateId
	 * @param langid
	 * @return
	 */
	public eCategory getCategoryInfo(int cateId, int langid);

	/**
	 * 
	 * @param parentId
	 * @param cate
	 * @return
	 */
	public int addCategory(int parentId, eCategory cate);

	/**
	 * Xoa nhom quyen
	 * 
	 * @param cateId
	 * @return
	 */
	public boolean removeCategory(int cateId);

	/**
	 * Thay doi thong tin nhom quyen
	 * 
	 * @param cate
	 * @param langid
	 * @return
	 */
	public boolean editCategory(eCategory cate, int langid);

	/**
	 * Lay ds quyen trong nhom quyen
	 * 
	 * @param cateId
	 * @param langid
	 * @return
	 */
	public Vector<eRole> getRolesInCate(int cateId, int langid);

	/**
	 * Lay ds quyen mà nhom quyen chua co
	 * 
	 * @param cate
	 * @param langid
	 * @return
	 */
	public Vector<eRole> getRolesOutCate(int cate, int langid);

	/**
	 * Gan quyen vao nhom quyen
	 * 
	 * @param cateId
	 * @param str_role_id
	 * @return
	 */
	public boolean addRoleIntoCate(int cateId, String str_role_id);

	/**
	 * Lay ds quyen trong he thong
	 * 
	 * @param langid
	 * @return
	 */
	public Vector<eRole> getRoleList(int langid);

	public int countUsers(int groupId);

}
