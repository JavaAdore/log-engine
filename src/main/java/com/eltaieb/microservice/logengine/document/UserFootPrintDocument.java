package com.eltaieb.microservice.logengine.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Document("USER_FOOT_PRINT")
public class UserFootPrintDocument implements Serializable{

	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long userId;
	
	
	
	private List<FootPrintDay> footPrintDays = new ArrayList<>();
	
	public UserFootPrintDocument(Long userId) {
		super();
		this.userId = userId;
	}
	
	
	public void addFootPrintDay(FootPrintDay footPrintDay)
	{
		footPrintDays.add(footPrintDay);
	}
	
	 
}
