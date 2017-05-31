package com.catherine.strategy;

public class OperationMultiply implements Operation {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 * num2;
	}

}