package com.catherine.chain_of_responsibility;

public class ErrorLogger extends Logger {

	public ErrorLogger() {
		level = ERROR;
	}

	@Override
	public void write(String message) {
		System.out.println("Chain of responsibility: ERROR MESSAGE:" + message);
	}

}
