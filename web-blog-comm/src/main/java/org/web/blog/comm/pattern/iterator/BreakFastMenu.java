package org.web.blog.comm.pattern.iterator;

import java.util.ArrayList;

public class BreakFastMenu {

	ArrayList<MenuItem> menuItems;
	
	public BreakFastMenu() {
		menuItems = new ArrayList<>();
		
		addItem("waffles", "waffles, with your choice of ", 
				true, 3.49);
		
	}
	
	public void addItem(String name, String descriprtion,
			boolean vegetarian, double price) {
		
		MenuItem menuItem = new MenuItem(name, descriprtion, vegetarian, price);
		menuItems.add(menuItem);
	}
	
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
}
