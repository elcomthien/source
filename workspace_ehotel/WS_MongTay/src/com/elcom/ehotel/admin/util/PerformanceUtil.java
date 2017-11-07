package com.elcom.ehotel.admin.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.elcom.ehotel.admin.model.PerformanceModel;

public class PerformanceUtil {
	public String getMemoryUsage() {
		String line = null;
		String result = "";
		String[] listCmd = new String[] { "/bin/bash", "-c", "df -m /data" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				result = line;
			}
			run.freeMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public PerformanceModel getInfoMemory() {
		PerformanceModel per =  new PerformanceModel();
		String info = getMemoryUsage();
		// String info = "                        2286767 1853387    317213  86% /data";
		info = replaceSpace(info);
		String[] arr = info.split(" ");
		long used = Long.parseLong(arr[1]);
		long free = Long.parseLong(arr[2]);
		long total = used + free;
		int usedper = Integer.parseInt(arr[3]);
		int freeper = 100 - usedper;
		per.setName("HDD");
		per.setUsed(String.valueOf(used));
		per.setFree(String.valueOf(free));
		per.setTotal(String.valueOf(total));
		per.setUsedPercent(String.valueOf(usedper));
		per.setFreePercent(String.valueOf(freeper));
		return per;
	}

	public String getRamUsage() {
		String result = "";
		String line = null;
		String[] listCmd = new String[] { "/bin/bash", "-c", "free -m" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
			int i = 1;
			while ((line = bufferedReader.readLine()) != null) {
				if (i == 2)
					result = line;
				i++;
			}
			run.freeMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public PerformanceModel getInfoRam() {
		PerformanceModel per =  new PerformanceModel();
		String info = getRamUsage();
		// String info = "Mem:          3878       3831         46          0        251       1729";
		info = replaceSpace(info);
		String[] arr = info.split(" ");
		long total = Long.parseLong(arr[1]);
		long used = Long.parseLong(arr[2]);
		long free = Long.parseLong(arr[3]);
		int usedper = Math.round((used * 100) / total);
		int freeper = 100 - usedper;
		per.setName("RAM");
		per.setUsed(String.valueOf(used));
		per.setFree(String.valueOf(free));
		per.setTotal(String.valueOf(total));
		per.setUsedPercent(String.valueOf(usedper));
		per.setFreePercent(String.valueOf(freeper));
		return per;
	}

	public String getCPUUsage() {
		String result = "";
		String line = null;
		String[] listCmd = new String[] { "/bin/bash", "-c",
				"grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage}'" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				result += line;
			}
			run.freeMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public PerformanceModel getInfoCPU() {
		PerformanceModel per =  new PerformanceModel();
		String info = getCPUUsage();

		float total = 100;
		float used = Float.parseFloat(info);
		float free = total - used;
		per.setName("CPU");
		per.setUsed(String.valueOf(used));
		per.setFree(String.valueOf(free));
		per.setTotal(String.valueOf(total));
		per.setUsedPercent(String.valueOf(used));
		per.setFreePercent(String.valueOf(free));
		return per;
	}

	public String replaceSpace(String info) {
		while (info.indexOf("  ") >= 0) {
			info = info.replaceAll("  ", " ");
		}
		info = info.substring(1);
		info = info.replace("%", "");
		return info;
	}
}
