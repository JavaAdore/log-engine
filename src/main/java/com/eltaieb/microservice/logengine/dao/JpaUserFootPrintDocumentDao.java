package com.eltaieb.microservice.logengine.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eltaieb.microservice.logengine.document.UserFootPrintDocument;

public interface JpaUserFootPrintDocumentDao  extends MongoRepository<UserFootPrintDocument,Long>{

}
