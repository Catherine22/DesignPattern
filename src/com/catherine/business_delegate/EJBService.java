package com.catherine.business_delegate;

public class EJBService implements BusinessService {

	@Override
	public void bindService() {
		System.out.println("bind EJBService");

	}

}
