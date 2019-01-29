package org.web.blog.controller;

public class Demo {
	public static void main(String[] args) {
		int i = 0;
		int j = 1;
		for (int x = 0 ; x <= 30; x++) {
			if (i == 0) {
				i = i+j;
				j = i-j;
				continue;
			} 
			System.out.println(i);
			i = i+j;
			j = i-j;
		}
	}
}
