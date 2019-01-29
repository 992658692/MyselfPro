package org.web.blog.comm.pattern.iterator;

import java.util.ArrayList;

public class Demo {
	
	public static void main(String[] args) {
		BreakFastMenu breakFast = new BreakFastMenu();
		ArrayList<MenuItem> breakFasts = breakFast.getMenuItems();
		
		for (MenuItem breas : breakFasts) {
			System.out.println(breas.getName());
		}
		
		DinerMenu diner = new DinerMenu();
		Iterator iterator = diner.getMenuItems();
		
		while (iterator.hasNext()) {
			MenuItem menuItem = (MenuItem) iterator.next();
			System.out.println(menuItem.getName());
		}
		
	}
}
