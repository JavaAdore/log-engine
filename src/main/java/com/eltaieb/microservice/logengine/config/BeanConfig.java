package com.eltaieb.microservice.logengine.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean
	public Queue footPrintQueue() {
		return new Queue("footPrintQueue", true, false, false);

	}

	@Bean
	public DirectExchange logExchange() {
		return new DirectExchange("logExchange", false, false);
	}

	@Bean
	public Binding b1() {
		return BindingBuilder.bind(footPrintQueue()).to(logExchange()).with("footPrint");
		
	}
}
