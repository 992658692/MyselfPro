package org.web.blog.comm.pattern.singleton;

public class Demo {

	public volatile static Demo unDemo = new Demo();
	
	private Demo() {}
	
	public static Demo getInstance () {
		return unDemo;
	}
}
