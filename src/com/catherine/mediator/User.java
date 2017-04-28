package com.catherine.mediator;

/**
 * 通过这个class作为中介，负责沟通另外两个classes(Main和ChatRoom)
 * 
 * @author Catherine
 *
 */
public class User {
	private ChatRoom chatRoom;
	private String name;
	private String ID;

	public User(String iD, String name) {
		chatRoom = new ChatRoom();
		this.name = name;
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public void sendMessage(String message) {
		chatRoom.sendMessage(String.format("%s: %s", name, message));
	}

}
