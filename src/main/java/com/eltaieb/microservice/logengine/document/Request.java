package com.eltaieb.microservice.logengine.document;

import java.time.Duration;
import java.time.LocalDateTime;

import com.eltaieb.microservice.base.enumuration.FootPrintEventType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor

public class Request {
	
	
	private String requestId;
	
	private Long userLoginId  ;
	private String eventName  ;
	private String inputParamReference;
	private FootPrintEventType eventType ;
	private String outParamReference;
	private LocalDateTime eventStartDateTime;
	private LocalDateTime eventEndtDateTime;
	private Duration eventDuration;


	public Request(String requestId) {
		super();
		this.requestId = requestId;
	}

	 
	
}
