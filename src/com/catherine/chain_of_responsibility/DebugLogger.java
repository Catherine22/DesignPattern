package com.catherine.chain_of_responsibility;

public class DebugLogger extends Logger {

	public DebugLogger() {
		level = DEBUG;
	}

	@Override
	public void write(String message) {
		System.out.println("Chain of responsibility: DEBUG MESSAGE:" + message);
	}

}
