package com.ridonit.alm.mapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class C2AWatcher {

	RunnableReader runnable;
	
	 

	public void createThread(long refreshTime, String threadName) {
		log.warn("start thread .... " + threadName);
		runnable = new RunnableReader(threadName, refreshTime);
		runnable.start();
		log.warn("thread started: " + threadName);
	}

	public void closeThreads() {
		runnable.setFinish(true);
		while (runnable.isRunning()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted.");
			}
		}
	}
}