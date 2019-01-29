package org.web.blog.comm.pattern.observer;

public class Demo {

	public static void main(String[] args) {
		Subject s = new Subject();
		Observer b = new Observer(s);
		b.updateActive(s);
	}
}
