package org.web.blog.comm.pattern.factory;

public class Demo {

	public static void main(String[] args) {
		CoffeeSore coffeeSore = new NYCoffeeSore();
		Coffee c = coffeeSore.orderCoffee("NY");
		c.prepare();
	}
}
