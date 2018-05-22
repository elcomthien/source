package ehotel.inter;

import java.util.Vector;

import ehotel.domain.menu.Group;
import ehotel.domain.menu.Menu;

/**
 * AMDMenu interface supply functions to manipulate database in Admin-Menu side.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
public interface AMDMenu {
	/**
	 * get list of menu group which system supports. (such as
	 * CONTENT,SERVICE,PMS,USER)
	 * 
	 * @param langid
	 * @return Vector empty if there is no menu group in the system.
	 *         <code>Vector<Group></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public Vector<Group> getGroups(int langid, String addressIp);

	/**
	 * get all menu of group
	 * 
	 * @param groupId
	 * @param langid
	 * @return Vector empty if there is no menu in the group.
	 *         <code>Vector<Menu></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public Vector<Menu> getMenuList(int groupId, int langid);

	/**
	 * get all submenu of menu
	 * 
	 * @param menuId
	 * @param langid
	 * @return Vector empty if there is no menu in the group.
	 *         <code>Vector<Menu></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public Vector<Menu> getSubMenuList(int menuId, int langid);
}
