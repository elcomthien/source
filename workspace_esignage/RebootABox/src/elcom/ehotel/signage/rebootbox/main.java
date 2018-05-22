package elcom.ehotel.signage.rebootbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
	public static void main(String[] args) throws Exception {
		// Runtime.getRuntime().exec("cmd.exe C:/Users/sang_/Desktop");
		// Runtime.getRuntime().exec("C:/Users/sang_/Desktop/reboot.bat 172.16.9.91");
		 //Runtime.getRuntime().exec("D:/Test/reboot.bat 172.16.9.91");
		 //Runtime.getRuntime().exec("ping google.com");
		String[] listCmd = new String[] {"Taskkill /IM adb.exe /F"};
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		String lsString = null;
		for (int i = 0; i < listCmd.length; i++) {			
			String cmd = listCmd[i];
			System.out.println(cmd);
			runtimeProcess = run.exec(cmd);
		}
		
		try {			
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(runtimeProcess.getInputStream()));
			while ((lsString = bufferedReader.readLine()) != null) {
				System.out.println(lsString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
