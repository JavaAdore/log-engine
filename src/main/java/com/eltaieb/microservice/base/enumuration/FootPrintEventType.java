package com.eltaieb.microservice.base.enumuration;

public enum FootPrintEventType {
	STARTING, BUSINESS_EXCEPTION, UNHANDLE_EXCEPTION, SUCCESS;
	
	public boolean isBusinessException()
	{
		return BUSINESS_EXCEPTION == this;
	}
}
 