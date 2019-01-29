package org.web.blog.comm.pattern.factory;

public abstract class CoffeeSore {

	public Coffee orderCoffee(String type) {
		Coffee coffee;
		coffee = createCoffee(type);
		
		coffee.prepare();
		return coffee;
	}
	
	public abstract Coffee createCoffee(String type);
}
