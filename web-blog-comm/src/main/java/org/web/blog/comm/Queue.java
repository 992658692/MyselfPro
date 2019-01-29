package org.web.blog.comm;

import java.util.Iterator;

/**
 * 队列 先进先出
 * @author hkrt
 *
 * @param <Item>
 */
public class Queue<Item> {

	private Node first;
	private Node last;
	private int N;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	//向尾部添加元素
	public void enQueue(Item item) {
		Node oldLast = last;
		last = new Node();
		//往尾部添加节点，要保证该队列不为空，如果该队列为空 oldLast 为null 则 oldLast.next 会出异常
		//所以在元素节点指向时要先判断该队列是否为空，如果为空则first 与last相等 该队列只有一个当前新增的元素
		//如果不为空则讲oldLast.next指向新增的节点last
		last.item = item;
		last.next = null;
		if (isEmpty()){
			first = last;
		} else {
			oldLast.next = last;
		}
		N++;
	}
	
	//向顶部删除元素
	public Item deQueue() {
		Item item = first.item;
		first = first.next;
		//判断first是否为空不放在之前，是因为既然删除那就标识该队列有元素可删，所以没有讲判断放在方法顶部，而是放在中间 用来判断该队列是否只有一个元素。
		//如果只有一个元素，那该元素被删除后该队列为null 则first 和last 都为null
		if (isEmpty()) {
			last = null;
		}
		N--;
		return item;
	}
	
	public Iterator<Item> iterator(){
		return null;
	}
	
	private class ListIterator implements Iterator<Item> {

//		private Node current = 
		
		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Item next() {
			return null;
		}
		
		public void remove (){};
		
	}
}
