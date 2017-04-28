package com.catherine.observer;

public class BinaryObserver extends Observer {

	public BinaryObserver(BroadcastManager manager) {
		broadcastManager = manager;
		broadcastManager.attach(this);
	}

	@Override
	public void update() {
		System.out.println(String.format("Observer: (Binary)%s", Integer.toBinaryString(broadcastManager.getState())));
	}

}
