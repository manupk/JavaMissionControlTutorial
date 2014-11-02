package com.oracle.example;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomEventsLab {
	private final static int THREAD_POOL_SIZE = 4;
	private final static ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
	
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < THREAD_POOL_SIZE; i++) {
			EXECUTOR.scheduleAtFixedRate(new WorkerThread(), 0L, 1L, TimeUnit.SECONDS);
		}
		//
		System.out.println("Press enter to quit!");
		System.in.read();
	}
}
