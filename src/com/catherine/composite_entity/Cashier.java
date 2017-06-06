package com.catherine.composite_entity;

/**
 * 举个例，bread, meat, sauce不能单独存在，必须三者合并成为hamburger才有意义，用组合实体模式进行hamburger的设置和引用。
 * 
 * @author Catherine
 *
 */
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
