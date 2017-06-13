package com.catherine.bridge;

/**
 * 举个例，假如用户要购买一辆新车，他选择任意的车型与颜色再结账，所以在建构模组时，实作颜色和车型的接口。
 * 
 * @author Catherine
 *
 */
public class BuyerSGuide extends Steps {

	public BuyerSGuide(Garage garage, ColorSet colorSet) {
		super(garage, colorSet);
	}

	@Override
	public void addToCart() {
		System.out.println("Bridge: Your car looks like...");
		garage.showVehicle();
		colorSet.draw();
	}
}
