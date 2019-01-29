package org.web.blog.comm.pattern.factory;

public class NYCoffeeSore extends CoffeeSore{

	@Override
	public Coffee createCoffee(String type) {
		
		if (type.equals("NY")) {
			return new NYCoffee(new NYCoffeeIngredent());
		}
		return null;
	}
}
