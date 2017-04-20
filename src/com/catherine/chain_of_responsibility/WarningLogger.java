package com.catherine.chain_of_responsibility;

public class WarningLogger extends Logger {

	public WarningLogger() {
		level = WARNING;
	}

	@Override
	public void write(String message) {
		System.out.println("Chain of responsibility: WARNING MESSAGE:" + message);
	}

}