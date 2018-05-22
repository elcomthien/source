package com.elcom.mysql.delete.main;

import java.util.TimerTask;

import com.elcom.mysql.delete.service.ServiceDelete;

public class RunTemp  extends TimerTask  {
//	private static final Logger log = Logger.getLogger(RunMain.class);
	public void run() {
		ServiceDelete serviceDelete = new ServiceDelete();
		serviceDelete.deleteService();
	}
}
