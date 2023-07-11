package com.ridonit.alm.mapper;

public class RunnableReader implements Runnable {
	private Thread t;
	private String threadName;
	private long sleepTime;
	private boolean finish = false;
	private boolean running = false;

	protected RunnableReader(String threadName, long refreshTime) {
		this.threadName = threadName;
		this.sleepTime = refreshTime;
	}

	public void run() {
		running = true;
		while (!finish) {
			try {
				//TODO @HAUKE/BERND hier muss der Api Call rein, der die CalmProcesses pullt.
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (Exception e) {

			} finally {

			}
		}
		running = false;
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public boolean isRunning() {
		return running;
	}
}