package org.web.blog.comm.pattern.iterator;

public class MenuItem {
	String name;
	String descriprtion;
	boolean vegetarian;
	double price;
	
	public MenuItem(String name, String descriprtin, boolean vegetarian, double price) {
		this.name = name;
		this.descriprtion = descriprtin;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescriprtion() {
		return descriprtion;
	}

	public boolean getVegetarian() {
		return vegetarian;
	}
	
	public double getPrice() {
		return price;
	}

	
}
