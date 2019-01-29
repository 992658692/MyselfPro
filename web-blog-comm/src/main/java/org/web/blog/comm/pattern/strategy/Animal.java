package org.web.blog.comm.pattern.strategy;

public abstract class Animal {

	public Color color;
	
	//公共行为
	public void live() {
		System.out.println("all Animal is live");
	}
	
	//独特行为
	public void animalForColor () {
		color.color();
	}
	
	public void setColor (Color colo) {
		color = colo;
	}
}
