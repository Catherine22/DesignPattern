package com.catherine.template;

/**
 * 假设Supercell推出新手游要买广告
 * 
 * @author Catherine
 *
 */
public class Supercell extends ProgrammaticBuying {

	@Override
	protected void setAdvertiser() {
		System.out.println("Advertiser: Supercell");
	}

	@Override
	protected void setAgencies() {
		System.out.println("Agencies: xx传媒");
	}

	@Override
	protected void setAdNetwork() {
		System.out.println("Ad Network: AdMob");
	}

	@Override
	protected void setAdExchange() {
		System.out.println("Ad Exchange: RTB");
	}

	@Override
	protected void pushToMobile() {
		System.out.println("push to: browser, WeChat");
	}

}
