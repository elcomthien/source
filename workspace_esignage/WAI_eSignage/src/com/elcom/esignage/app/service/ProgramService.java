package com.elcom.esignage.app.service;

import java.io.File;

import com.elcom.esignage.app.dao.ProgramDao;

public class ProgramService {
	ProgramDao programDao = new ProgramDao();

	public String spgetChanngeTemplates(String ipClient, String sernumber, String mac, String timereq, String ramtotal, String ram, String cputotal,
			String cpu, String hddtotal, String hdd, String sdcardtotal, String sdcard, String cursizefile, String totalsizefile) {
		return programDao.spgetChanngeTemplates(ipClient, sernumber, mac, timereq, ramtotal, ram, cputotal, cpu, hddtotal, hdd, sdcardtotal, sdcard,
				cursizefile, totalsizefile);
	}

	public String getDownloadFileStb(String sernumber, String mac) {
		return programDao.getDownloadFileStb(sernumber, mac);
	}

	public String downloadComplate(String sernumber, String mac, String contentid, String process) {
		if (process.equals("Downloading") || process.equals("Fail")) {
			process = "0%";
		} else if (process.equals("Finish")) {
			process = "Finish";
		} else {
			Double size = new Double(process);
			String path = programDao.getPathFile(contentid);
			File file = new File(path);
			double bytes = file.length();
			int per_cur = (int) Math.round((size * 100) / bytes);
			process = per_cur + "%";
			System.out.println("pecent download of file : " + path + ": " + per_cur + "%");
		}
		return programDao.downloadComplate(sernumber, mac, contentid, process);
	}

	public String getCaptureCounterStb(String sernumber, String mac) {
		return programDao.getCaptureCounterStb(sernumber, mac);
	}

	public String spgetContentLayout(String sernumber, String mac, int playlistid, int changecontent) {
		return programDao.spgetContentLayout(sernumber, mac, playlistid, changecontent);
	}

	public String spgetInfoStb(String sernumber, String mac) {
		return programDao.spgetInfoStb(sernumber, mac);
	}

	public String spgetSchedule(String scheduleid, String sernumber, String mac) {
		return programDao.spgetSchedule(scheduleid, sernumber, mac);
	}

	public String sploginstb(String sernumber, String mac) {
		return programDao.sploginstb(sernumber, mac);
	}

	public String getSchedulePeri(String sernumber, String mac) {
		return programDao.getSchedulePeri(sernumber, mac);
	}

	public String sp_regstb(String namein, String sernumber, String mac, String savelocalmedia, String savelocalfile) {
		return programDao.sp_regstb(namein, sernumber, mac, savelocalmedia, savelocalfile);
	}

	public String spgetDefauleHome(String sernumber, String mac) {
		return programDao.spgetDefauleHome(sernumber, mac);
	}

	public String spiptvSubject() {
		return programDao.spiptvSubject();
	}

	public String spiptvContentSubject(String idsubject) {
		return programDao.spiptvContentSubject(idsubject);
	}
}
