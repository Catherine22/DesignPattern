package com.catherine.observer_plus;

import java.util.ArrayList;
import java.util.List;

/**
 * 同观察者模式，做了些调整，让用户自定义观察者收到的信息和之后的行为。
 * 
 * @author Catherine
 *
 */
public class BroadcastManager {
	private static List<Receiver> receivers = new ArrayList<>();

	public void sendMessage(String content) {
		notifyAllReceivers(content);
	}

	public void register(Receiver receiver) {
		receivers.add(receiver);
	}

	public void unregister(Receiver receiver) {
		receivers.remove(receiver);
	}

	private void notifyAllReceivers(String content) {
		for (Receiver res : receivers)
			res.onReceive(content);
	}
}
