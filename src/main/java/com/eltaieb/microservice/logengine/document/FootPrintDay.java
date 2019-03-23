package com.eltaieb.microservice.logengine.document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor

public class FootPrintDay {
	
 	private LocalDate date;
	
 	
 	
	private List<Request> requests =new ArrayList<>();
	
	
	 public FootPrintDay(LocalDate date) {
		super();
		this.date = date;
	}
	
	 
	public void addRequest(Request request)
	{
		this.requests.add(request);
	}

	
}
