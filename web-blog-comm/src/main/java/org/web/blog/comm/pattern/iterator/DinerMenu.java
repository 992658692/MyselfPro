package org.web.blog.comm.pattern.iterator;

public class DinerMenu {

	static final int MAX_ITEMS = 1;
	int numberOfItems = 0;
	MenuItem[] menuItems;
	
	public DinerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];
		
		addItem("Hotdog", "A hot dog", false, 3.05);
	}
	
	public void addItem (String name, String descriprtion,
			boolean vegetarian, double price) {
		
		MenuItem menuItem = new MenuItem(name, descriprtion, 
				vegetarian, price);
		if (numberOfItems >= MAX_ITEMS) {
			System.out.println("Sorry, menu is full!");
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1;
		}
	}
	
	public Iterator getMenuItems() {
		return new DinerMenuIterator(menuItems);
	}
}
