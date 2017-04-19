package com.catherine.proxy;
/**
 * 假设我们要加载一张图，在ImageLoader初始化时加载，我们不希望每次加载同一张图都要重新创建一个ImageLoader（因为初始化费时之类的），
 * 利用一个ImageLoaderProxy来加载，一旦有了初始化过后的ImageLoader，就会用原先的ImageLoader物件继续操作。
 * 特別适用于
 * @author Catherine
 *
 */
public class ImageLoaderProxy implements Image {
	private ImageLoader iLoader;

	@Override
	public void display() {
		if (iLoader == null)
			iLoader = new ImageLoader();
		else
			displayCache();
	}

	private void displayCache() {
		System.out.println("Proxy: Loading images from cache.");
	}

}
