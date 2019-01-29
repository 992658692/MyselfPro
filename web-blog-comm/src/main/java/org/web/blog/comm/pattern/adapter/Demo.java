package org.web.blog.comm.pattern.adapter;

public class Demo {
	
	public static void main(String[] args) {
		BosCat bCat = new BosCat();
		Dog d = new Adapter(bCat);
		d.bark();
	}
}
