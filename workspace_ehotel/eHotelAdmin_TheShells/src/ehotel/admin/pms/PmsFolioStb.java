package ehotel.admin.pms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PmsFolioStb {
	
	public PmsFolioStb() { }
	
	public String os = System.getProperty("os.name").toLowerCase();
	
	public boolean[] pingIp1(String[] str, final boolean[] kq) {
		//final boolean[] kq = {false,false,false,false,false,false};
		for (int i = 0; i < str.length; i++) {
			final String temp = str[i];
			final int j = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					System.out.println(j);
					System.out.println(temp);
					kq[j] = pingIp(temp);
					System.out.println(j + " = " + kq[j]);
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {}
				}
			});
			t.start();
		}
		return kq;
	}
	
	public boolean pingIp(String ip)
	{
		boolean flag = false;
		if ( isWindows() ) {
			//do nothing, neu la windows thi ko can lam gi vi khong co ham
			String lsString = null;
			//command to ping ip
			String[] listCmd = new String[] {"cmd.exe ","/c"," ping -n 1 " + ip};
			Runtime run = Runtime.getRuntime();
			Process runtimeProcess = null;
			try {
				runtimeProcess = run.exec(listCmd); 
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
				while ((lsString = bufferedReader.readLine()) != null)
				{
					if (lsString.indexOf("Destination Host Unreachable") >= 0) {
						return false;
					}
					else if (lsString.indexOf("Request timed out") >= 0) {
						return false;
					}
					else if (lsString.indexOf("100% loss") >= 0) {
						return false;
					}
					else {
						flag = true;
					}
				}
				run.freeMemory();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if ( isUnix() ) {
			System.out.println("ip=" + ip);
			if (ip == null) {
				return false;
			}
			else {
				String lsString = null;
				//command to ping ip
				String[] listCmd = new String[] {"/bin/bash","-c","ping -c 1 " + ip};
				Runtime run = Runtime.getRuntime();
				Process runtimeProcess = null;
				try {
					runtimeProcess = run.exec(listCmd); 
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
					while ((lsString = bufferedReader.readLine()) != null)
					{
						System.out.println(lsString);
						if (lsString.indexOf("Destination Host Unreachable") >= 0) {
							return false;
						}
						else if (lsString.indexOf("Request timed out") >= 0) {
							return false;
						}
						else if (lsString.indexOf("100% loss") >= 0) {
							return false;
						}
						else {
							flag = true;
						}
					}
					run.freeMemory();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	//check os 21.9
	public boolean isWindows() {
		return (os.indexOf("win") >= 0);
	}
	public boolean isMac() {
		return (os.indexOf("mac") >= 0);
	}
	public boolean isUnix() {
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}
	public boolean isSolaris() {
		return (os.indexOf("sunos") >= 0);
	}
}