package com.catherine;

import com.catherine.observer_premium.BroadcastManager;
import com.catherine.observer_premium.Receiver;

public class ObserverPlusTest {
	private BroadcastManager manager;
	private Receiver receiver;

	public ObserverPlusTest() {
		manager = new BroadcastManager();
	}

	public void registerReceiver() {
		receiver = new Receiver() {

			@Override
			public void onReceive(String content) {
				System.out.println(String.format("Observer plus: (ObserverPlusTest)%s", content));
			}
		};
		manager.register(receiver);
	}

	public void unregisterReceiver() {
		manager.unregister(receiver);
	}

}
