package com.catherine.service_locator;

import java.util.ArrayList;
import java.util.List;

public class Database {
	private static User Adam;
	private static User Caroline;
	static {
		List<String> playlistAdam = new ArrayList<>();
		playlistAdam.add("pop");
		playlistAdam.add("soft");
		playlistAdam.add("hip-hop");
		List<String> historyListAdam = new ArrayList<>();
		historyListAdam.add("pop song 1");
		historyListAdam.add("soft song 2");
		historyListAdam.add("pop song 1");
		List<String> playlistCaroline = new ArrayList<>();
		playlistCaroline.add("indie");
		playlistCaroline.add("k-pop");
		List<String> historyListCaroline = new ArrayList<>();
		historyListCaroline.add("funk song 1");
		historyListCaroline.add("funk song 2");
		historyListCaroline.add("funk song 3");
		Adam = new User();
		Adam.setID(1);
		Adam.setName("Adam");
		Adam.setPlaylist(playlistAdam);
		Adam.setHistorylist(historyListAdam);
		Caroline = new User();
		Caroline.setID(2);
		Caroline.setName("Caroline");
		Caroline.setPlaylist(playlistCaroline);
		Caroline.setHistorylist(historyListCaroline);
	}

	public List<String> getSong(int ID, String playlist) {
		List<String> songs = new ArrayList<>();
		if (ID == 1) {
			if (playlist.equalsIgnoreCase("pop")) {
				songs.add("pop song 1");
				songs.add("pop song 2");
			} else if (playlist.equalsIgnoreCase("soft")) {
				songs.add("soft song 1");
				songs.add("soft song 2");
			} else if (playlist.equalsIgnoreCase("hip-hop")) {
				songs.add("hip-hop song 1");
				songs.add("hip-hop song 2");
			}
		} else if (ID == 2) {
			if (playlist.equalsIgnoreCase("indie")) {
				songs.add("indie song 1");
				songs.add("indie song 2");
			} else if (playlist.equalsIgnoreCase("k-pop")) {
				songs.add("k-pop song 1");
				songs.add("k-pop song 2");
			}
		}
		return songs;
	}

	public List<String> getHistory(int ID) {
		if (ID == 1)
			return Adam.getHistorylist();
		else if (ID == 2)
			return Caroline.getHistorylist();
		else
			return null;
	}

	public List<String> getPlaylist(int ID) {
		if (ID == 1)
			return Adam.getPlaylist();
		else if (ID == 2)
			return Caroline.getPlaylist();
		else
			return null;
	}
}
