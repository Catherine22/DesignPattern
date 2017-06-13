package com.catherine.service_locator;

import java.util.ArrayList;
import java.util.List;

public class Cache {
	private List<Service> caches = new ArrayList<>();

	public void cacheService(Service service) {
		if (service != null)
			caches.add(service);
	}

	public Service getCache(String action) {
		Service target = null;
		for (Service s : caches) {
			if (action.equalsIgnoreCase("playlist") && s instanceof PlaylistService) {
				System.out.println("return cached playlist service");
				target = s;
			} else if (action.equalsIgnoreCase("history") && s instanceof HistoryService) {
				System.out.println("return cached history service");
				target = s;
			}
		}

		return target;
	}

	public void clearCache() {
		caches.clear();
	}
}
