package org.web.blog.comm.pattern.state;

public interface State {

	//入币
	public void insertQuarter();
	
	//出币
	public void ejectQuarter();
	
	//转动手柄
	public void turnCrank();

	//出糖
	public void dispense();
}
