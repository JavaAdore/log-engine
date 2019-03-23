package com.eltaieb.microservice.logengine.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.eltaieb.microservice.base.model.FootPrintMessage;
import com.eltaieb.microservice.logengine.facade.LogEngineFacade;
import com.google.gson.Gson;

@Service
public class FootPrintMessageListener {

	private LogEngineFacade logEngineFacade;
	public FootPrintMessageListener(LogEngineFacade logEngineFacade)
	{
		this.logEngineFacade=logEngineFacade;
	}
	
	/**
	 * Message listener  
	 * 
	 * @param UserDetails a user defined object used for deserialization of message
	 */
	@RabbitListener(queues = "footPrintQueue")
	public void receiveMessageForApp1(final String messageAsGson) {
		
		FootPrintMessage message = new Gson().fromJson(messageAsGson, FootPrintMessage.class);
		logEngineFacade.saveFootPrintMessage(message);
 	}	
}
