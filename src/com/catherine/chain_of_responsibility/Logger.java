package com.catherine.chain_of_responsibility;

/**
 * 打印log时，权重最高的{@code ERROR}在较次的权重过滤器中都能打印，反之权重最低的{@code DEBUG}，在其它权重过滤器时都不会被打印
 * 
 * @author Catherine
 *
 */
public abstract class Logger {
	public final static int DEBUG = 0;
	public final static int WARNING = 1;
	public final static int ERROR = 2;

	protected Logger nextLogger;
	protected int level;

	public void setNextLogger(Logger nextLogger) {
		this.nextLogger = nextLogger;
	}

	/**
	 * 利用迭代来打印较小权重的log
	 * 
	 * @param level
	 * @param message
	 */
	public void logMessage(int level, String message) {
		if(this.level<=level)
			write(message);
		
		if (nextLogger != null)
			nextLogger.logMessage(level, message);
	}

	public abstract void write(String message);
}
