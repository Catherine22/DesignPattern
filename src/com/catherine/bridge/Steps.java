package com.catherine.bridge;

public abstract class Steps {
	protected Garage garage;
	protected ColorSet colorSet;

	protected Steps(Garage garage, ColorSet colorSet) {
		this.garage = garage;
		this.colorSet = colorSet;
	}

	public abstract void addToCart();

}
