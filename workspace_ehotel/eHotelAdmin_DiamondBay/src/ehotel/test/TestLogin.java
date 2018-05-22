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
		ilogin.login("toanhv", "123", "0:0:0:0:0:0:0:1");
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
	/*public static void main(String[] args) {
		TestLogin test = new TestLogin();
		test.login();
	}*/
	public static void main(String[] args) {
       /* String hello = "HelloxxxHelloxxxHello";
        Pattern pattern = Pattern.compile("Hello");
        Matcher matcher = pattern.matcher(hello);
        int count = 0;
        while (matcher.find())
            count++;
        System.out.println(count);    // prints 3
        */        
		/*String hello = "-450000";
		String hello2 = "";
		if (hello.indexOf("-") >=0) {
			hello2 = hello.substring(1);
		}
		else {
			hello2 = hello;
		}
		int count = 0;
		StringBuffer a = new StringBuffer();
		for (int i = hello2.length()-1; i >= 0; i--) {
			System.out.println("vi tri:" + i + " - kytu:" +hello2.charAt(i));
			a.append(hello2.charAt(i));
			count++;
			if (count % 3 == 0 && count < hello2.length()) {
				a.append(",");
			}
		}
		System.out.println(a);
		if (hello.indexOf("-") >=0) {
			a.append("-");
		}
		a.reverse();
		System.out.println(a);*/
		TestLogin t = new TestLogin();
		t.login2();
		
//		String a = "Indigo%21%40%23Philao%40123";
//		System.out.println(a);
//		System.out.println(a.trim());
    }
}