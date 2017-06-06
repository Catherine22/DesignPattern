package com.catherine.business_delegate;

public class BusinessLookUp {

	public BusinessService getBusinessService(ServiceType type) {
		if (type == ServiceType.EJB)
			return new EJBService();
		else
			return new JMSService();
	}
}
