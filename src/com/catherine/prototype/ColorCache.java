package com.catherine.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Catherine
 *
 */

/**
 * 用prototype的时机在于，假如物件创建时耗费大量资源，用户不希望每次使用时都要重新创建，用prototype模式可以只创建一次，以后要用都用克隆。
 * <br>
 * 其实就是拷贝的意思，但是不是返回内存地址的引用，而是一个拷贝的物件，拥有独立的内存空间。 <br>
 *
 */
public class ColorCache {

	/**
	 * 保存本体
	 */
	private static final Map<Type, Color> prototypes = new HashMap<>();

	/**
	 * 本体只需要初始化一次
	 */
	static {
		Color red = new Red();
		Color blue = new Blue();
		prototypes.put(red.getType(), red);
		prototypes.put(blue.getType(), blue);
	}

	/**
	 * 返回本体的新拷贝，但无论如何本体都是安全的，不会被修改到。
	 * 
	 * @param type
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Color getColor(Type type) throws CloneNotSupportedException {
		return (Color) prototypes.get(type).clone();
	}
}
