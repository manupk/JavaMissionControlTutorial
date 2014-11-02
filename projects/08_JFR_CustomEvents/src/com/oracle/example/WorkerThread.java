package com.oracle.example;

import java.util.Random;

public class WorkerThread implements Runnable {
	private final static Random RND = new Random();
	
	@Override
	public void run() {
		int sleepTime = 200 + RND.nextInt(700);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Slept " + sleepTime);
	}
}
