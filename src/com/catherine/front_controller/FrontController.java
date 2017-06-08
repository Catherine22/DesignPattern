package com.catherine.front_controller;

/**
 * 前端控制器模式就是通过调度器返回不同的视图或处理程序。
 * 
 * @author Catherine
 *
 */
public class FrontController {
	private DashboardDispatcher dispatcher;

	public FrontController() {
		dispatcher = new DashboardDispatcher();
	}

	public String login(String account, String pw) {
		return "authorization" + account;
	}

	public String inputCaptcha(String captcha) {
		return "authentication" + captcha;
	}

	public void dispatchView(String request) {
		dispatcher.dispatch(request);
	}
}
