package ehotel.sql;

public class eMenuSql {
	public static final String sqlGetGroups = "{call eMenu.getGroupMenu(?,?,?)}";
	public static final String sqlGetMenuList = "{call eMenu.getMenuList(?,?,?)}";
	public static final String sqlGetSubMenuList = "{call eMenu.getSubMenuList(?,?,?)}";
}
