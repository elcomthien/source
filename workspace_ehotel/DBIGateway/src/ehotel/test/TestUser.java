package ehotel.test;

import java.util.Vector;

import ehotel.domain.group.eCategory;
import ehotel.domain.group.eGroup;
import ehotel.domain.group.eRole;
import ehotel.domain.group.eUser;
import ehotel.inter.IUser;
import ehotel.render.DBIGateway;

public class TestUser {
	static IUser iuser;

	public TestUser() {
		iuser = DBIGateway.getIUser();
	}

	public void getGroupList() {// ok
		Vector<eGroup> vGroup = iuser.getGroupList(2);
		for (eGroup g : vGroup) {
			System.out.println(g.toString());
		}
	}

	public void getGroupInfo() {// ok
		eGroup agroup = iuser.getGroupInfo(1, 2);
		if (agroup != null)
			System.out.println(agroup.toString());
	}

	public void addGroup() {// ok
		eGroup group = new eGroup();
		group.setName("Content");
		iuser.addGroup(group);
	}

	public void editGroup() {
		eGroup group = new eGroup();
		group.setGroupId(86);
		group.setName("Contents");
		iuser.editGroup(group, 2);
	}

	public void removeGroup() {// ok
		iuser.removeGroup(86);
	}

	public void getRolesInGroup() {// ok
		Vector<eRole> vRoles = iuser.getRolesInGroup(343, 2);
		for (eRole g : vRoles) {
			System.out.println(g.toString());
		}
	}

	public void getRolesOutGroup() {// ok
		Vector<eRole> vRoles = iuser.getRolesOutGroup(343, 2);
		for (eRole g : vRoles) {
			System.out.println(g.toString());
		}
	}

	public void getRolesOutUser() {
		Vector<eRole> vRoles = iuser.getRolesOutUser(421, 2);
		for (eRole g : vRoles) {
			System.out.println(g.toString());
		}
	}

	public void getRoleList() {// ok
		Vector<eRole> vRoles = iuser.getRoleList(2);
		for (eRole g : vRoles) {
			System.out.println(g.toString());
		}
	}

	public void addRoleIntoGroup() {// ok
		iuser.addRoleIntoGroup(87, "(1,2)");
	}

	public void getUsers() {
		Vector<eUser> vUsers = iuser.getUsers(379, -1, -1);
		for (eUser g : vUsers) {
			System.out.println(g.toString());
		}
	}

	public void getUserInfo() {
		eUser user = iuser.getUserInfo(1);
		System.out.println(user.toString());
	}

	public void addUser() {// ok
		eUser u = new eUser();
		u.setName("Ho Viet Toan");
		u.setAddress("HiepPhu-q9");
		u.setDepartment("SW");
		u.setAccount("toanhv");
		u.setPasswd("123");// luu y chuoi nay se duoc ma hoa truoc khi gui
							// xuong db
		iuser.addUser(1, u);
	}

	public void getGroupsInOutUser() {
		Vector<eGroup> vGroups = iuser.getGroupsOutUser(376, 2);
		for (eGroup g : vGroups) {
			System.out.println(g.toString());
		}
	}

	public void removeUser() {
		iuser.removeUser(1, 378);

	}

	public void countUsers() {
		iuser.countUsers(372);
	}

	public void getRolesInOutCate() {
		Vector<eRole> vRole_ = iuser.getRolesOutCate(1, 2);
		for (eRole g : vRole_) {
			System.out.println(g.toString());
		}
	}

	public void getRolesInCate() {
		Vector<eRole> vRole_ = iuser.getRolesInCate(1, 2);
		for (eRole g : vRole_) {
			System.out.println(g.toString());
		}
	}

	public void getCategoryList() {
		Vector<eCategory> vCate = iuser.getCategoryList(1);
		for (eCategory g : vCate) {
			System.out.println(g.toString());
		}
	}

	public void addCategory() {
		eCategory cate = new eCategory();
		cate.setName("Serviceee");
		iuser.addCategory(-1, cate);// cap 1
	}

	public void editCategory() {
		eCategory catee = new eCategory();
		catee.setCateId(1);
		catee.setName("Systems");
		iuser.editCategory(catee, 2);
	}

	public void addRoleIntoCate() {
		iuser.addRoleIntoUser(890, "(2,3,4,5,10,11)");
	}

	public void remove() {
		iuser.removeCategory(102);
	}

	// iuser.checkRoleUser(ipAdress,"MOD$SERVICE")

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestUser test = new TestUser();
		// test.getRolesOutGroup();
		// test.addRoleIntoCate();
		test.addRoleIntoCate();

	}
}
