package com.catherine.observer;

public class HexObserver extends Observer {

	public HexObserver(BroadcastManager manager) {
		broadcastManager = manager;
		broadcastManager.attach(this);
	}

	@Override
	public void update() {
		System.out.println(String.format("Observer: (Hex)%s", Integer.toHexString(broadcastManager.getState())));
	}

}