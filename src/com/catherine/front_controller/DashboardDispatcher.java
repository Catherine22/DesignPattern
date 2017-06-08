package com.catherine.front_controller;

import com.catherine.front_controller.dashboard.AuthenticationDashboard;
import com.catherine.front_controller.dashboard.AuthorizationDashboard;
import com.catherine.front_controller.dashboard.DashboardTemplate;
import com.catherine.front_controller.dashboard.GuestDashboard;

public class DashboardDispatcher {
	private DashboardTemplate authentication;
	private DashboardTemplate authorization;
	private DashboardTemplate guest;

	public DashboardDispatcher() {
		authentication = new AuthenticationDashboard();
		authorization = new AuthorizationDashboard();
		guest = new GuestDashboard();
	}

	public void dispatch(String token) {
		if (token == null)
			guest.show();
		else if (token.toLowerCase().contains("authentication"))
			authentication.show();
		else if (token.toLowerCase().contains("authorization"))
			authorization.show();
		else
			guest.show();
	}

}
