package com.catherine.service_locator;

import java.util.List;

public class User {
	private int ID;
	private String name;
	private List<String> playlist;
	private List<String> historylist;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<String> playlist) {
		this.playlist = playlist;
	}

	public List<String> getHistorylist() {
		return historylist;
	}

	public void setHistorylist(List<String> historylist) {
		this.historylist = historylist;
	}

}
