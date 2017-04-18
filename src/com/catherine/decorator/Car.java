package com.catherine.decorator;

/**
 * 建立一个汽车的模组，用各品牌去实现Car的展示接口(show())， 装饰者模式的用意在于不破坏本体的模组前提下，对其进行修改，比如升级音响、换轮胎等，
 * 有点类似补丁的概念。
 * 
 * 必须实现本体是重点
 * 
 * @author Catherine
 *
 */
public interface Car {
	public void show();
}
