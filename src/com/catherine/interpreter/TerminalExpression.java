package com.catherine.interpreter;

public class TerminalExpression implements Expression {
	private String codes;

	public TerminalExpression(String codes) {
		this.codes = codes;
	}

	@Override
	public boolean interpret(String context) {
		return context.contains(codes);
	}

}
