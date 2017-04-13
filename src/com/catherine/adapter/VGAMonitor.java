package com.catherine.adapter;

public class VGAMonitor implements Monitor {

	@Override
	public void inputVGA() {
		System.out.println("Adapter:VGAMonitor");
	}

	@Override
	public void inputHDMI() {
		// do nothing
	}

}
