package com.catherine.prototype;

import java.util.Random;

public class Blue extends Color {
	private String serialID;

	public Blue() {
		setType(Type.BLUE);
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;
		serialID = "blue" + n;
	}

	@Override
	public void printID() {
		System.out.println("Prototype:your serialID is " + serialID);
	}

	@Override
	public void resetID() {
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;
		serialID = "blue" + n;
		System.out.println("Prototype:generated serialID " + serialID);
	}
}
