package com.catherine.interpreter;

/**
 * 建立规则
 * 
 * @author Catherine
 *
 */
public class Toolkits {

	public Expression getStates() {
		Expression michigan = new TerminalExpression("Michigan");
		Expression florida = new TerminalExpression("Florida");
		Expression pennsylvania = new TerminalExpression("Pennsylvania");

		Expression orExpression = new OrExpression(michigan, florida);
		return new OrExpression(orExpression, pennsylvania);
	}

	public Expression getVotingLimitation() {
		Expression age = new TerminalExpression("adult");
		Expression citizenship = new TerminalExpression("citizen");

		return new AndExpression(age, citizenship);
	}

}
