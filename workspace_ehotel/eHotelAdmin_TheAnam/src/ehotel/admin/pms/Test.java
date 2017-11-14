package ehotel.admin.pms;

public class Test {
	static PmsFolioStb pfs = new PmsFolioStb();

	public static void main(String[] args) {
		String[] str = {"192.168.32.22","192.168.32.21","192.168.12.4","192.168.32.18","192.168.32.36","192.168.12.7"};
		final boolean[] kq = {false,false,false,false,false,false};
		
		String[] str1 = new String[6];
		final boolean[] kq1 = new boolean[6];
		str1[0] = "192.168.32.22";
		str1[1] = "192.168.32.21";
		str1[2] = "192.168.32.4";
		str1[3] = "192.168.32.18";
		str1[4] = "192.168.32.36";
		str1[5] = "192.168.32.7";
		kq1[0] = false;
		kq1[1] = false;
		kq1[2] = false;
		kq1[3] = false;
		kq1[4] = false;
		kq1[5] = false;
		/*for (int i = 0; i < 6; i++) {
			final String temp = str[i];
			final int j = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					System.out.println(j);
					System.out.println(temp);
					kq[j] = pfs.pingIp(temp);
					System.out.println(j + " = " + kq[j]);
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {}
				}
			});
			t.start();
		}*/
		pfs.pingIp1(str, kq);
	}
}