package com.catherine.observer;

public class OctalObserver extends Observer {

	public OctalObserver(BroadcastManager manager) {
		broadcastManager = manager;
		broadcastManager.attach(this);
	}

	@Override
	public void update() {
		System.out.println(String.format("Observer: (Octal)%s", Integer.toOctalString(broadcastManager.getState())));
	}

}