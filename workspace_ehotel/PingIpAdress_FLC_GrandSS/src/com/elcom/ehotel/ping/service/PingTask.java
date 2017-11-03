package com.elcom.ehotel.ping.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class PingTask implements Callable<PingResult> {
	private String ipAddress;
	public String os = System.getProperty("os.name").toLowerCase();

	public PingTask(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public PingResult call() {
		// InetAddress inet = null;
		try {
			// inet = InetAddress.getByName(ipAddress);
			// System.out.println(inet.isReachable(5000));
			// System.out.println("ping ip: " + ipAddress);
			int resultCode = pingIp() ? 1 : 0;
			return new PingResult(ipAddress, resultCode);
		} catch (Exception e) {
			e.printStackTrace();
			return new PingResult(ipAddress, 0);
		}
	}

	public boolean pingIp() {
		boolean flag = false;
		if (isWindows()) {
			// do nothing, neu la windows thi ko can lam gi vi khong co ham
			String lsString = null;
			// command to ping ip
			String[] listCmd = new String[] { "cmd.exe ", "/c", " ping -n 1 " + ipAddress };
			Runtime run = Runtime.getRuntime();
			Process runtimeProcess = null;
			try {
				runtimeProcess = run.exec(listCmd);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
				while ((lsString = bufferedReader.readLine()) != null) {
					if (lsString.indexOf("Destination host unreachable") >= 0 || lsString.indexOf("Request timed out") >= 0
							|| lsString.indexOf("100% loss") >= 0) {
						return false;
					}

					else {
						flag = true;
					}
				}
				run.freeMemory();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (isUnix()) {
			// System.out.println("ip=" + ipAddress);
			if (ipAddress == null) {
				return false;
			} else {
				String lsString = null;
				// command to ping ip
				String[] listCmd = new String[] { "/bin/bash", "-c", "ping -c 1 " + ipAddress };
				Runtime run = Runtime.getRuntime();
				Process runtimeProcess = null;
				try {
					runtimeProcess = run.exec(listCmd);
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
					while ((lsString = bufferedReader.readLine()) != null) {
						// System.out.println(lsString);
						if (lsString.indexOf("Destination Host Unreachable") >= 0 || lsString.indexOf("Request timed out") >= 0
								|| lsString.indexOf("100% loss") >= 0) {
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

	// check os 21.9
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

	public static void main(String[] args) {
		PingTask p = new PingTask("172.16.9.9");
		// System.out.println(p.call());
		System.out.println(p.pingIp());
	}
}
