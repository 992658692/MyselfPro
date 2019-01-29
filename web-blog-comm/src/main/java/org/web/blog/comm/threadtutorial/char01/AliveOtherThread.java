package org.web.blog.comm.threadtutorial.char01;

public class AliveOtherThread extends Thread{

	public AliveOtherThread () {
		System.out.println("AliveOtherThread-------begin");
		System.out.println("Thread.getName()" + Thread.currentThread().getName());
		System.out.println("Thread.isAlive()" + Thread.currentThread().isAlive());
		System.out.println("this.getName()" + this.getName());
		System.out.println("this.isAlive()" + this.isAlive());
		System.out.println("AliveOtherThread-------end");
	}
	
	@Override
	public void run () {
		System.out.println("run----------begin");
		System.out.println("Thread.getName()" + Thread.currentThread().getName());
		System.out.println("Thread.isAlive()" + Thread.currentThread().isAlive());
		System.out.println("this.getName()" + this.getName());
		System.out.println("this.isAlive()" + this.isAlive());
		System.out.println("run----------end");
	}
}
