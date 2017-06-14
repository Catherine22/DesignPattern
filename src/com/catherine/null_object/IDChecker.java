package com.catherine.null_object;

/**
 * Null object模式：处理对象为Null的情况
 * 
 * @author Catherine
 *
 */
public class IDChecker {
	private final static String[] IDs = { "12351", "62343", "34261" };
	private final static String[] NAMEs = { "Dan Zhou", "Yi Liu", "Wu Wang" };

	public String getName(String ID) {
		for (int i = 0; i < IDs.length; i++) {
			if (IDs[i].equalsIgnoreCase(ID)) {
				RealID rId = new RealID(NAMEs[i]);
				return rId.getName();
			}
		}
		NullID nId = new NullID();
		return nId.getName();
	}
}
