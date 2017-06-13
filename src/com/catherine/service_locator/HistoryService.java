package com.catherine.service_locator;

public class HistoryService implements Service {

	@Override
	public String getServiceName() {
		return HistoryService.class.getSimpleName();
	}

	@Override
	public String response(int request) {
		Database database = new Database();
		return database.getHistory(request).toString();
	}

}
