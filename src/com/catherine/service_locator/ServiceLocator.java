package com.catherine.service_locator;

/**
 * 服务定位器模式用在一种情况，假设用户访问某service获取数据，此时将该service做缓存，下次访问时直接导向缓存以提高效能。
 * 
 * @author Catherine
 *
 */
public class ServiceLocator {
	private static Cache cache;
	private InitialService iService;
	static {
		cache = new Cache();
	}

	public Service lookup(String action) {
		Service target = cache.getCache(action);
		if (target == null) {
			System.out.println("No cache");
			iService = new InitialService();
			target = iService.lookup(action);
			cache.cacheService(target);
		}
		return target;
	}
}
