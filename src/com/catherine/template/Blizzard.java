package com.catherine.template;

/**
 * 假设Blizzard推出新网游要买广告
 * 
 * @author Catherine
 *
 */
public class Blizzard extends ProgrammaticBuying {

	@Override
	protected void setAdvertiser() {
		System.out.println("Advertiser: Blizzard");
	}

	@Override
	protected void setAgencies() {
		System.out.println("Agencies: xx广告");
	}

	@Override
	protected void setAdNetwork() {
		System.out.println("Ad Network: AdSense");
	}

	@Override
	protected void setAdExchange() {
		System.out.println("Ad Exchange: RTB");
	}

	@Override
	protected void pushToMobile() {
		System.out.println("push to: browser, twitter, facebook");
	}

}
