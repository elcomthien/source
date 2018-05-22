package ehotel.domain.menu;

/**
 * Group class provides necessary properties to create and contain menu GROUP
 * information.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
public class Group {
	private int groupId;
	private String groupName;
	private int parentId;
	private String roleValue;

	/**
	 * Constructor used to create this object.
	 */
	public Group() {
	}

	public String toString() {
		return "Group[Id=" + groupId + ",Name=" + groupName + ",parentId="
				+ parentId + "]";
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

}
