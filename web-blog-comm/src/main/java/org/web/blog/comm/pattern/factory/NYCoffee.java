package org.web.blog.comm.pattern.factory;

public class NYCoffee extends Coffee{

	ConffeeIngredent coffeeIngredent;
	
	public NYCoffee(ConffeeIngredent coffeeIngreden) {
		coffeeIngredent = coffeeIngreden;
		sweentness = "3fen";
		spec = coffeeIngredent.CoffeeBean();
		toppings.add("NyCoffee's");
	}

	@Override
	void prepares() {
		
	}
}
