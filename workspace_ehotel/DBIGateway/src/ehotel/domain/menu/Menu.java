package ehotel.domain.menu;

public class Menu {
	private int menuId;
	private String menuName;
	private String href;
	private String role;
	private int groupId;
	private int parentId;

	/**
	 * Constructor used to create this object.
	 */
	public Menu() {

	}

	public String toString() {
		return "Menu[Id=" + menuId + ",Name=" + menuName + ",href=" + href
				+ ",parentId=" + parentId + "]";
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
