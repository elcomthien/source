package com.elcom.ehotel.ping.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.elcom.ehotel.ping.dao.PingDao;
import com.elcom.ehotel.ping.model.TVModel;

public class PingService {
	private PingDao pingDao = new PingDao();
	
	public void pingListIp(){
		List<TVModel> listip = new ArrayList<>();
		listip = pingDao.getListIp();
//		long startTime = System.currentTimeMillis();
//		int totalIps = 200;
		ExecutorService executor = Executors.newFixedThreadPool(listip.size());
		List<Future<PingResult>> list = new ArrayList<Future<PingResult>>();
		Callable<PingResult> callable = null;
		for (int i = 0; i < listip.size(); i++) {
			callable = new PingTask(listip.get(i).getIp()); // Get the ipAddres buttons[i].getText());
			Future<PingResult> future = executor.submit(callable);
			list.add(future);
		}
		for (int i = 0; i < list.size(); i++) {
			try {
				pingDao.updateStatus(listip.get(i).getKey(), list.get(i).get().getResultCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		for (Future<PingResult> fut : list) {
//			try {
//				System.out.println(new Date() + " - " + fut.get());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		executor.shutdown();
//		long endTime = System.currentTimeMillis();
//		long totalTime = endTime - startTime;
//		System.out.println("Total Time Run: " + totalTime);
	}
}
