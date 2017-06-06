package com.catherine.business_delegate;

import com.catherine.business_delegate.ServiceType;

/**
 * 作为入口，用来访问BusinessService
 * 
 * @author Catherine
 *
 */
public class BusinessDelegate {
	private ServiceType type;

	public BusinessDelegate(ServiceType type) {
		this.type = type;
	}

	public void createTask() {
		BusinessLookUp blu = new BusinessLookUp();
		BusinessService service = blu.getBusinessService(type);
		service.bindService();
	}
}
