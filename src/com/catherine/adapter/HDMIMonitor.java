package com.catherine.adapter;

public class HDMIMonitor implements Monitor {

	@Override
	public void inputVGA() {
		// do nothing
	}

	@Override
	public void inputHDMI() {
		System.out.println("Adapter:HDMIMonitor");
	}
}
