package org.web.blog.comm.pattern.strategy;

public class Demo {
	public static void main(String[] args) {
		Animal a = new Dog();
		a.setColor(new DogColor());
		a.animalForColor();
	}
}
