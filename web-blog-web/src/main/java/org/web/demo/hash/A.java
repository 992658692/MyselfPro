package org.web.demo.hash;

public class A {

	private int q = 1;
	
	private int w = 2;
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj instanceof A) {
			A obj1 = (A)obj;
			
			if (this.q == obj1.q && this.w == obj1.w) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new A();
		
		System.out.println(a1.hashCode());
		System.out.println(a2.hashCode());
		System.out.println(a1.equals(a2));
	}
}
