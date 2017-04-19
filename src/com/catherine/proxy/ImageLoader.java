package com.catherine.proxy;

class ImageLoader implements Image {

	ImageLoader() {
		display();
	}

	@Override
	public void display() {
		System.out.println("Proxy: Loading images from disk.");
	}

}
