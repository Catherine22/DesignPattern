package com.catherine.front_controller.dashboard;
/**
 * Authorization = permissions (what you are allowed to do)
 * @author Catherine
 *
 */
public class AuthorizationDashboard extends DashboardTemplate {
	@Override
	public void show() {
		System.out.println("---Hi standard user---");
		System.out.println("-You can do something-");
		System.out.println("----------------------");
	}
}
