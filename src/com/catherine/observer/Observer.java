package com.catherine.observer;

public abstract class Observer {
	protected BroadcastManager broadcastManager;

	public abstract void update();
}