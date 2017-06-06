package com.catherine.composite_entity;

/**
 * 粗颗粒就是把几个对象（细颗粒）给组合在一起管理
 * 
 * @author Catherine
 *
 */
public class CoarseGrainedHamburger {
	private DependentBread breadEntity;
	private DependentMeat meatEntity;
	private DependentSauce sauceEntity;

	public CoarseGrainedHamburger() {
		breadEntity = new DependentBread();
		meatEntity = new DependentMeat();
		sauceEntity = new DependentSauce();
	}

	public void setHamburger(String bread, String meat, String sauce) {
		breadEntity.setBread(bread);
		meatEntity.setMeat(meat);
		sauceEntity.setSauce(sauce);
	}

	public String[] getHamburger() {
		return new String[] { breadEntity.getBread(), meatEntity.getMeat(), sauceEntity.getSauce() };
	}
}
