package org.web.blog.comm;

import java.util.Iterator;

import org.web.blog.comm.transactional.Compensable;

/**
 * 背包bag  后进先出
 * 由于背包bag的特性不支持删除 只支持添加所以没有删除方法 而添加,size,isEmpty方法的实现与栈的实现一样
 * @author hkrt
 *
 */
public class Bag<Item> {
	
	private Node first;
	private int N;
	
	private class Node {
		Item item;
		Node next;
	}
	
	@Compensable
	public void add(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}
	
	@Compensable
	public Iterator<Item> iterator () {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {}
		
	}

}
