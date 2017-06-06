package com.catherine.business_delegate;

public class Client {
	BusinessDelegate bd;

	public Client(BusinessDelegate bd) {
		this.bd = bd;
	}

	public void createTask() {
		bd.createTask();
	}
}
