package org.web.blog.comm.pattern.adapter;

public class Adapter implements Dog{

	private Cat cat;
	
	public Adapter(Cat c) {
		this.cat = c;
	}
	
	//让狗发出猫叫 - -！！
	@Override
	public void bark() {
		cat.call();
	}

	
}
