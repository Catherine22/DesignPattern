package com.catherine.prototype;

import java.util.Random;

public class Red extends Color {

	private String serialID;

	public Red() {
		setType(Type.RED);
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;
		serialID = "red" + n;
	}

	@Override
	public void printID() {
		System.out.println("Prototype:your serialID is " + serialID);
	}

	@Override
	public void resetID() {
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;
		serialID = "red" + n;
		System.out.println("Prototype:generated serialID " + serialID);
	}
}
