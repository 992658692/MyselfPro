package com.blog.base.lending.item;

public interface DataItem<E> {

	String getCode();
	
	String getName();
	
	String getDomain();
	
	E execute(Object ... params);
}
