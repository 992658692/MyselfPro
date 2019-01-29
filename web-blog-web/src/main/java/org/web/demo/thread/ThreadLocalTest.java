package org.web.demo.thread;


/**
 * 
 * ThreadLocal 和 synchronized(同步) 的区别
 * 
 * 同步机制:通过对象锁的机制，使得同一个时间内 只能有一个线程访问该对象的变量，该对象变量是多个线程共享的，但是如果你想使用这个变量
 * 	你就得排队，等待持有该对象锁的线程释放该对象锁，下个线程才能访问。使用同步机制的时候，需要考虑什么时候该持有对象锁，什么
 * 	时候要释放该对象锁，会相对的增加编程的复杂度。
 * 
 * ThreadLocal:为每个线程分配一个对象变量，可以保证每个线程都有自己的变量可以使用，不会影响到其他的线程，既然每个线程
 * 	都有变量可以使用，也就不需要去访问外部的其他线程的变量了。
 * 
 * 2者之间的区别是：同步让多个线程访问同个变量，让资源最小化，但是会影响线程执行的速度。ThreadLocal是让每个线程都可以独立执行，
 * 	虽然速度增加了， 但是由于每个线程都要分配变量，使得分配的资源大大的增加了。
 * 
 * ThreadLocal应用场景：
 * 	Spring 单利却又不是同步 共享，靠的就是ThreadLocal
 * 
 * @author xin
 *
 */
public class ThreadLocalTest {

	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	ThreadLocal<String> stringLocal = new ThreadLocal<String>();
	
	public void test() {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}
	
	public long getLong () {
		return longLocal.get();
	}
	
	public String getString () {
		return stringLocal.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
		final ThreadLocalTest test = new ThreadLocalTest();
		
		System.out.println(test.getLong());
		System.out.println(test.getString());
		
		Thread thread1 = new Thread() {
			public void run () {
				test.test();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			};
		};
		
		
		Thread thread2 = new Thread() {
			public void run () {
				test.test();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			};
		};
		
		thread2.start();
		thread2.join();
		thread1.start();
		thread1.join();
		
		
		System.out.println(test.getLong());
		System.out.println(test.getString());
	}
}
