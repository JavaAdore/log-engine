package com.eltaieb.microservice.logengine.service.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.eltaieb.microservice.base.model.FootPrintMessage;
import com.eltaieb.microservice.logengine.dao.JpaUserFootPrintDocumentDao;
import com.eltaieb.microservice.logengine.document.FootPrintDay;
import com.eltaieb.microservice.logengine.document.Request;
import com.eltaieb.microservice.logengine.document.UserFootPrintDocument;
import com.eltaieb.microservice.logengine.service.api.LogService;

import lombok.extern.java.Log;

@Log
@Service
public class LogServiceBean implements LogService {

	private JpaUserFootPrintDocumentDao jpaUserFootPrintDocumentDao;
	private MongoTemplate mongoTemplate;

	public LogServiceBean(JpaUserFootPrintDocumentDao jpaUserFootPrintDocumentDao, MongoTemplate mongoTemplate) {
		this.jpaUserFootPrintDocumentDao = jpaUserFootPrintDocumentDao;
		this.mongoTemplate = mongoTemplate;
	}

	public void saveFootPrintMessage(FootPrintMessage message) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(message.getUserId()));
		boolean userFootPrintExists = mongoTemplate.exists(query, UserFootPrintDocument.class);
		
		if (Boolean.FALSE == userFootPrintExists) {
			// user not exist
			UserFootPrintDocument userFootPrintDocument = new UserFootPrintDocument(message.getUserId());
			FootPrintDay footPrintDay = generateFootPrintDay(message);
			userFootPrintDocument.addFootPrintDay(footPrintDay);
			jpaUserFootPrintDocumentDao.save(userFootPrintDocument);
		}else
		{
			query = new Query();
			query.addCriteria(Criteria.where("_id").is(message.getUserId())
					.andOperator(Criteria.where("footPrintDays.date")
							.is(message.getEventStartDateTime().toLocalDate())));
			 
			boolean dayExistInUser=mongoTemplate.exists(query, UserFootPrintDocument.class);
			if(Boolean.FALSE == dayExistInUser)
			{
				query = new Query();
				query.addCriteria(Criteria.where("_id").is(message.getUserId()));
				Update update = new Update();
				update.addToSet("footPrintDays",generateFootPrintDay(message));
				mongoTemplate.updateFirst(query, update, UserFootPrintDocument.class);
			}else
			{
				Update update = new Update();
				update.addToSet("footPrintDays.$.requests",generateRequest(message));
				mongoTemplate.updateFirst(query, update, UserFootPrintDocument.class);
			}
		}

	}

	private FootPrintDay generateFootPrintDay(FootPrintMessage message) {
		FootPrintDay footPrintDay = new FootPrintDay(message.getEventStartDateTime().toLocalDate());
		footPrintDay.addRequest(generateRequest(message));
		return footPrintDay;
	}
 

	private Request generateRequest(FootPrintMessage message) {
		Request request = new Request();
		request.setRequestId(message.getRequestId());
		request.setUserLoginId(message.getUserLoginId());
		request.setEventName(message.getEventName());
		request.setInputParamReference(message.getInputParamReference());
		request.setEventType(message.getEventType());
		request.setOutParamReference(message.getOutParamReference());
		request.setEventStartDateTime(message.getEventStartDateTime());
		request.setEventEndtDateTime(message.getEventEndtDateTime());
		request.setEventDuration(message.getEventDuration());
 		return request;
	}

 

}
