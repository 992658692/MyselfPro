package org.web.demo.thread;

import java.util.ArrayList;

public class ThreadPoolManager {

	private int threadCount;//启动的线程数
	
	private  WorkThread[] handlers;//线程数组
	
	private ArrayList<Runnable> taskVector = new ArrayList<Runnable>();
	
	public ThreadPoolManager(int threadCount) {
		this.threadCount = threadCount;
		for (int i = 0; i< threadCount; i++) {
			handlers[i] = new WorkThread();
			handlers[i].start();
		}
	}
	
	void shutdown() {
		synchronized (taskVector) {
			while (!taskVector.isEmpty()) {
				taskVector.remove(0);//清空任务队列
			}
		}
		
		for (int i = 0; i < threadCount; i++) {
			handlers[i] = new WorkThread();
			handlers[i].interrupt();//结束线程
		}
	}
	
	//增加新任务
	void execute(Runnable newTask) {
		synchronized (taskVector) {
			taskVector.add(newTask);
			taskVector.notifyAll();
		}
	}
	
	
	private class WorkThread extends Thread {
		public void run () {
			Runnable task = null;
			for (;;) {
				synchronized (taskVector) {
					if (taskVector.isEmpty()) {
						try {
							taskVector.wait();
							task = taskVector.remove(0);
						} catch (InterruptedException e) {
							break;
						}
					}
					task.run();
				}
			}
		}
	}
}
