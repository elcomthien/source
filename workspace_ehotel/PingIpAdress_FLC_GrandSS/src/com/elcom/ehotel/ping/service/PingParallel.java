package com.elcom.ehotel.ping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PingParallel {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		long startTime = System.currentTimeMillis();
		int totalIps = 200;
		ExecutorService executor = Executors.newFixedThreadPool(totalIps);
		List<Future<PingResult>> list = new ArrayList<Future<PingResult>>();
		Callable<PingResult> callable = null;
		for (int i = 0; i < totalIps; i++) {
			callable = new PingTask("172.16.9." + i); // Get the ipAddres
														// buttons[i].getText());
			Future<PingResult> future = executor.submit(callable);
			list.add(future);
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time Run: " + totalTime);
		System.out.println(list.get(100).get().getResultCode());
		
		// for (Future<PingResult> fut : list) {
		// try {
		// System.out.println(new Date() + " - " + fut.get().getResultCode());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		executor.shutdown();
		// long endTime = System.currentTimeMillis();
		// long totalTime = endTime - startTime;
		// System.out.println("Total Time Run: " + totalTime);
	}
}
