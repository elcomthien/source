package ehotel.test;

import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;

public class TestLogin {
	static ILOGIN ilogin;

	public TestLogin() {
		ilogin = DBIGateway.getILogin();
	}

	// Phan login and check role
	public void login() {// ok
		ilogin.login("vuonghoa", "123", "172.16.9.36");
	}

	public void login2() {// ok
		ilogin.login("toanhv", "123", "172.16.9.75");
	}

	public void logout() {
		ilogin.logout("172.16.9.36");
	}

	public void isLogedinByAnotherUser() {
		ilogin.isLogedinByAnotherUser("172.16.9.75");
	}

	public void getLoginUserName() {
		ilogin.getLoginUserName("172.16.9.36");
	}

	public void checkRoleUser() {
		ilogin.checkRoleUser("172.16.9.36", "MOD$SERVICE");

	}

	public void isAdmin() {
		ilogin.isAdmin("172.16.9.36");
	}

	// iuser.checkRoleUser(ipAdress,"MOD$SERVICE")
	public static void main(String[] args) {
		TestLogin test = new TestLogin();
		test.login();
	}
}
