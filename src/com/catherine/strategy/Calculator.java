package com.catherine.strategy;

/**
 * 策略模式的用途在于执行阶段时可进行不同的运算
 * 
 * @author Catherine
 *
 */
public class Calculator {
	private Operation strategy;

	public Calculator(Operation strategy) {
		this.strategy = strategy;
	}

	public int execute(int num1, int num2) {
		return strategy.doOperation(num1, num2);
	}
}
