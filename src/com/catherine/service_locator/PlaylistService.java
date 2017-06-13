package com.catherine.service_locator;

public class PlaylistService implements Service {

	@Override
	public String getServiceName() {
		return PlaylistService.class.getSimpleName();
	}

	@Override
	public String response(int request) {
		Database database = new Database();
		return database.getPlaylist(request).toString();
	}

}
