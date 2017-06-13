package com.catherine.front_controller.dashboard;
/**
 * Authentication = login + password (who you are)
 * @author Catherine
 *
 */
public class AuthenticationDashboard extends DashboardTemplate {
	@Override
	public void show() {
		System.out.println("--------Hi user---------");
		System.out.println("-You can read something-");
		System.out.println("------------------------");
	}
}
