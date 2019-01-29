package org.web.blog.comm.transactional;

public class Demo {

	public static void main(String[] args)  {
		System.out.println(7|14);
	}
	
	
	 public static int hammingDistance(int x, int y) {
		 int count = 0;
		 int a = 0;
		 a = x^y;
		 while (a>0) {
			 count++;
			 a= a&a-1;
		 }
		 return count;
	 }
}
