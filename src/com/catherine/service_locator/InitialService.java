package com.catherine.service_locator;

public class InitialService {

	public Service lookup(String action) {
		if (action.equalsIgnoreCase("playlist")) {
			System.out.println("initialize playlist service");
			return new PlaylistService();
		} else if (action.equalsIgnoreCase("history")) {
			System.out.println("initialize history service");
			return new HistoryService();
		} else
			return null;
	}

}
