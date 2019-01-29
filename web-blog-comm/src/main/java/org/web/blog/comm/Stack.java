package org.web.blog.comm;

/**
 * 链表-后进先出
 * @author hkrt
 *
 * @param <Item>
 */
public class Stack<Item> {

	private int N; 			//元素数量
	private Node first;		//栈顶
	
	//Node 为节点嵌套类
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null ; // or N == 0;
	}
	
	public int size() {
		return N;
	}
	
	//向栈顶添加元素
	public void push(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.next = oldFirst;
		first.item = item;
		N++;
	}
	
	//向栈顶删除元素
	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
}
