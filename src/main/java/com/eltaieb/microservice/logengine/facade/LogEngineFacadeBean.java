package com.eltaieb.microservice.logengine.facade;

import org.springframework.stereotype.Service;

import com.eltaieb.microservice.base.model.FootPrintMessage;
import com.eltaieb.microservice.logengine.service.api.LogService;

@Service()
public class LogEngineFacadeBean implements LogEngineFacade {

	private LogService logService;
	public LogEngineFacadeBean(LogService logService)
	{
		this.logService=logService;
	} 
	
	@Override
	public void saveFootPrintMessage(FootPrintMessage message) {
		
		logService.saveFootPrintMessage( message);
 		
	}

}
