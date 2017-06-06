package com.catherine.composite_entity;

public class Cashier {
	private CompositeEntity entity;

	public Cashier() {
		entity = new CompositeEntity();
	}

	public void chectOut(String bread, String meat, String sauce) {
		entity.setHamburger(bread, meat, sauce);
	}

	public void printReceipt() {
		String[] info = entity.getHamburger();
		for (int i = 0; i < info.length; i++) {
			System.out.print(info[i] + " ");
		}
		System.out.print("\n");
	}
}
