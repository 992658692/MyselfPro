package org.web.blog.comm.pattern.observer;

import java.util.ArrayList;

/**
 * 主题
 * @author hkrt
 *
 */
public class Subject implements Subjects{
	
	private ArrayList<Object> observers;
	
	private String message;
	
	private boolean lock;
	
	public Subject() {
		observers = new ArrayList<>();
	}
	
	@Override
	public void add(Object obj) {
		observers.add(obj);
	}
	
	@Override
	public void remove(Object obj) {
		int i = observers.indexOf(obj);
		if (i > 0) {
			observers.remove(obj);
		}
	}
	
	@Override
	public void notifyObserver() {
		if (lock) {
			for (int i = 0; i < observers.size(); i++) {
				Observers observer = (Observers)observers.get(i);
				observer.update(message);
			}
			lock = false;
		}
	}
	
	public void change() {
		setChange();
		notifyObserver();
	}
	
	@Override
	public void setMessage(String msg) {
		this.message = msg;
		change();
	}
	
	@Override
	public void setChange() {
		lock = true;
	}
}
