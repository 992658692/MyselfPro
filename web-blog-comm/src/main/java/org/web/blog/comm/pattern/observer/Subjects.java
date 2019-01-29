package org.web.blog.comm.pattern.observer;

public interface Subjects {

	void add(Object obj);

	void remove(Object obj);

	void notifyObserver();

	void setMessage(String msg);

	void setChange();

}
