package com.elcom.adcenter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoxUtil {
	public String os = System.getProperty("os.name").toLowerCase();

	public void rebootBox(String str) {
		String[] arr = str.split(",");
		for (int i = 0; i < arr.length; i = i + 2) {
			if (pingIp(arr[i]) && arr[i + 1].equals("1")) {
//				disconnect(arr[i]);
				System.out.println("reboot: " + arr[i]);
				rebootIp(arr[i]);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				disconnect(arr[i]);
			}
		}
	}

	public boolean pingIp(String ip) {
		boolean flag = false;
		if (isWindows()) {
			String lsString = null;
			String[] listCmd = new String[] { "cmd.exe ", "/c",
					" ping -n 1 " + ip };
			Runtime run = Runtime.getRuntime();
			Process runtimeProcess = null;
			try {
				runtimeProcess = run.exec(listCmd);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(runtimeProcess.getInputStream()));
				int i = 0;
				while ((lsString = bufferedReader.readLine()) != null) {
					System.out.println(lsString);
					if (i == 2) {
						if (lsString.indexOf("Destination host unreachable") >= 0) {
							return false;
						} else if (lsString.indexOf("Request timed out") >= 0) {
							return false;
						} else if (lsString.indexOf("100% loss") >= 0) {
							return false;
						} else {
							return true;
						}
					}
					i++;
				}
				run.freeMemory();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (isUnix()) {
			if (ip == null) {
				return false;
			} else {
				String lsString = null;
				String[] listCmd = new String[] { "/bin/bash", "-c",
						"ping -c 1 " + ip };
				Runtime run = Runtime.getRuntime();
				Process runtimeProcess = null;
				try {
					runtimeProcess = run.exec(listCmd);
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(
									runtimeProcess.getInputStream()));
					while ((lsString = bufferedReader.readLine()) != null) {
						if (lsString.indexOf("Destination Host Unreachable") >= 0) {
							return false;
						} else if (lsString.indexOf("Request timed out") >= 0) {
							return false;
						} else if (lsString.indexOf("100% loss") >= 0) {
							return false;
						} else {
							flag = true;
						}
					}
					run.freeMemory();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public boolean isWindows() {
		return (os.indexOf("win") >= 0);
	}

	public boolean isUnix() {
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}

	public void rebootIp(String ip) {
		String[] listCmd = new String[] { "cmd.exe ", "/c",
				"D:/Run/reboot.bat " + ip };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		String lsString = null;
		try {
			runtimeProcess = run.exec(listCmd);
//			BufferedReader bufferedReader = new BufferedReader(
//					new InputStreamReader(runtimeProcess.getInputStream()));
//			 while ((lsString = bufferedReader.readLine()) != null) {
//			 System.out.println(lsString);
//			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//Taskkill /IM adb.exe /F
	public void disconnect(String ip) {
//		String[] listCmd = new String[] { "cmd.exe ", "/c", "adb disconnect " + ip };
		String[] listCmd = new String[] { "cmd.exe ", "/c", "Taskkill /IM adb.exe /F"};
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		String lsString = null;
		System.out.println("adb disconnect " + ip);
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(runtimeProcess.getInputStream()));
			while ((lsString = bufferedReader.readLine()) != null) {
				System.out.println(lsString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BoxUtil b = new BoxUtil();
		// System.out.println(b.os);
		// System.out.println(b.pingIp("172.16.9.91"));
		b.rebootBox("172.16.9.91,1,172.16.9.99,1");
		// b.rebootIp("172.16.9.91");
		// b.disconnect();
		// String[] listCmd = new String[] { "cmd.exe ", "/c",
		// "D:/Run/reboot.bat 172.16.9.91" };
		// Runtime run = Runtime.getRuntime();
		// Process runtimeProcess = null;
		// String lsString = null;
		// try {
		// runtimeProcess = run.exec(listCmd);
		// BufferedReader bufferedReader = new BufferedReader(
		// new InputStreamReader(runtimeProcess.getInputStream()));
		// while ((lsString = bufferedReader.readLine()) != null) {
		// System.out.println(lsString);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

}
