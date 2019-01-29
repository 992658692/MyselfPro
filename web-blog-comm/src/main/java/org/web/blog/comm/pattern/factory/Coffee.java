package org.web.blog.comm.pattern.factory;

import java.util.ArrayList;

public abstract class Coffee {

	String spec;
	
	String sweentness;
	
	ArrayList<String> toppings = new ArrayList<String>();
	
	abstract void prepares();
	
	public void prepare() {
		System.out.println("Coffee's spec" + spec);
		System.out.println("Coffee's sweent" + sweentness);
		for (String l : toppings) {
			System.out.println("  " + l);
		}
	}
}