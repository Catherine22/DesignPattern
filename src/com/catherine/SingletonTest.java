package com.catherine;

import com.catherine.singleton.BillPughSingleton;
import com.catherine.singleton.EagerInitializingSingleton;
import com.catherine.singleton.EnumSingleton;
import com.catherine.singleton.LazyInitializingSingleton;
import com.catherine.singleton.SafeLazyInitializingSingleton;

public class SingletonTest {
	private BillPughSingleton bInstance1;
	private EagerInitializingSingleton eInstance1;
	private EnumSingleton eunm1;
	private LazyInitializingSingleton lInstance1;
	private SafeLazyInitializingSingleton sInstance1;

	public SingletonTest() {
		bInstance1 = BillPughSingleton.getInstance();
		eInstance1 = EagerInitializingSingleton.getInstance();
		eunm1 = EnumSingleton.INSTANCE;
		lInstance1 = LazyInitializingSingleton.getInstance();
		sInstance1 = SafeLazyInitializingSingleton.getInstance();
	}

	public BillPughSingleton getBillPughSingleton() {
		return bInstance1;
	}

	public EagerInitializingSingleton getEagerInitializingSingleton() {
		return eInstance1;
	}

	public EnumSingleton getEnumSingleton() {
		return eunm1;
	}

	public LazyInitializingSingleton getLazyInitializingSingleton() {
		return lInstance1;
	}

	public SafeLazyInitializingSingleton getSafeLazyInitializingSingleton() {
		return sInstance1;
	}
}
