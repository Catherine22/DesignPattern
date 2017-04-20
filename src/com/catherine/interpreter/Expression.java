package com.catherine.interpreter;

/**
 * 用户输入代码后，通过Interpreter模式来处理，执行相应的功能。<br>
 * 可应用于处理SQL指令。
 * 
 * @author Catherine
 *
 */
public interface Expression {
	/**
	 * 
	 * 
	 * @param context
	 * @return true表示解释(interpret)成功
	 */
	public boolean interpret(String context);
}
