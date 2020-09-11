package com.scorechecker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scorechecker.entity.CreditScore;

public interface CreditScoreRepository extends MongoRepository<CreditScore, String> {

	CreditScore findByTcIdentity(String tcIdentity);
	
}
