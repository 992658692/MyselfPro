package org.web.blog.comm.pattern.observer;

public class Observer implements Observers{
	
	public Observer(Subject s) {
		s.add(this);
	}
	
	//被动订阅
	@Override
	public void update(String message) {
		System.out.println("订阅主题发布的消息：" + message);
	}
	
	//主动拉取
	//像固定的主题索取消息
	public void updateActive(Subjects sb) {
		if (sb instanceof Subject) {
			System.out.println("订阅者主动向主题拉取数据");
		}
	}

}
