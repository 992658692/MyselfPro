package org.web.blog.comm.pattern.state;

public class GumballMachine {

	State noQuarterState;
	
	State state = noQuarterState;
	int count = 0;
	
	public GumballMachine(int numberGumball) {
		noQuarterState = new NoQuarterState(this);
		this.count = numberGumball;
		if (numberGumball > 0) {
			state = noQuarterState;
		}
	}
	
	public void insertQuarter() {
		state.insertQuarter();
	}
	
	public void ejectQuarter() {
		state.ejectQuarter();
	}
	
	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}
	
	//在每个步骤下 调用该方法来进行下步操作
	void setState(State state) {
		this.state = state;
	}
	
	void releaseBall() {
		System.out.println("A gumball comes rolling out the slot ...");
		if (count != 0) {
			count = count - 1;
		}
	}
}
