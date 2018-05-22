package ehotel.inter;

public interface ILOGIN {
	// PHAN QUAN LY SESSION USER
	public boolean login(String account, String passwd, String markAddress);

	public void logout(String markAddress);

	/**
	 * kiem tra user phai la account adim ko
	 * 
	 * @param markAdrress
	 * @return
	 */
	public boolean isAdmin(String markAdrress);

	/**
	 * kiem tra quyen han cua user thuc thi chuc nang
	 * 
	 * @param markAddress
	 * @param role
	 * @return
	 */
	public boolean checkRoleUser(String markAddress, String role);

	/**
	 * Lay ten dang nhap cua user
	 * 
	 * @param markAddress
	 * @return
	 */
	public String getLoginUserName(String markAddress);

	/**
	 * kiem tra account duoc dang nhap boi IP khac ko?
	 * 
	 * @param markAddress
	 * @return
	 */
	public boolean isLogedinByAnotherUser(String markAddress);

	public String getHrefPageOfUser(String markAddress);

}
