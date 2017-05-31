package com.catherine.template;

/**
 * 模版模式以程序化购买为例，每个阶段都会有些许不同，但是整体来说都是一样的步骤。<br>
 * 1. 分别实作每个阶段<br>
 * 2. 用final定义模版方法（整体流程）<br>
 * <br>
 * 程序化购买
 * 
 * @author Catherine
 *
 */
public abstract class ProgrammaticBuying {
	/**
	 * 广告主
	 */
	protected abstract void setAdvertiser();

	/**
	 * 广告代理
	 */
	protected abstract void setAgencies();

	/**
	 * 主要渠道——广告网络
	 */
	protected abstract void setAdNetwork();

	/**
	 * 次要渠道——广告交易平台
	 */
	protected abstract void setAdExchange();

	/**
	 * 发布媒体
	 */
	protected abstract void pushToMobile();

	/**
	 * 投放广告（模版方法）
	 */
	public final void buyAds() {
		System.out.print("STEP1. ");
		setAdvertiser();
		System.out.print("STEP2. ");
		setAgencies();
		System.out.print("STEP3.1. ");
		setAdNetwork();
		System.out.print("STEP3.2. ");
		setAdExchange();
		System.out.print("STEP4. ");
		pushToMobile();
	}
}
