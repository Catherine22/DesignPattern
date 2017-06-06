package com.catherine.composite_entity;

/**
 * 获取多个粗颗粒（在此只用Hamburger一个粗颗粒作为示范）
 * 
 * @author Catherine
 *
 */
public class CompositeEntity {
	private CoarseGrainedHamburger hamburger;

	public CompositeEntity() {
		hamburger = new CoarseGrainedHamburger();
	}

	public void setHamburger(String bread, String meat, String sauce) {
		hamburger.setHamburger(bread, meat, sauce);
	}

	public String[] getHamburger() {
		return hamburger.getHamburger();
	}
}
