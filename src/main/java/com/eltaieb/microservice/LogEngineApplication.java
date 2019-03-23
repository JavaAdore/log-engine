package com.eltaieb.microservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
 
@EnableRabbit
@EnableAsync
@SpringBootApplication
@PropertySource(value = {"classpath:mongo.properties","classpath:rabbit-mq.properties"})
public class LogEngineApplication {

 
	public static void main(String[] args) {
		SpringApplication.run(LogEngineApplication.class, args);
	}
	
 	 
}

