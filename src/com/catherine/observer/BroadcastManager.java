package com.catherine.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册几个观察者，一旦状态改变就通知所有观察者
 * 
 * @author Catherine
 *
 */
public class BroadcastManager {
	private List<Observer> observers;
	private int state;

	public BroadcastManager() {
		observers = new ArrayList<>();
	}

	public void setState(int state) {
		this.state = state;
		notifyAllObservers();
	}

	public int getState() {
		return state;
	}

	public void attach(Observer observer) {
		observers.add(observer);
	}

	private void notifyAllObservers() {
		for (Observer obs : observers)
			obs.update();
	}
}
